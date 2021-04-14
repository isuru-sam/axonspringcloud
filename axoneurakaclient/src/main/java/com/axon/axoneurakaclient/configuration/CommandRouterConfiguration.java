package com.axon.axoneurakaclient.configuration;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.axonframework.axonserver.connector.event.axon.AxonServerEventStore;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.distributed.AnnotationRoutingStrategy;
import org.axonframework.commandhandling.distributed.CommandBusConnector;
import org.axonframework.commandhandling.distributed.CommandRouter;
import org.axonframework.commandhandling.distributed.DistributedCommandBus;
import org.axonframework.commandhandling.distributed.RoutingStrategy;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.commandhandling.gateway.IntervalRetryScheduler;
import org.axonframework.commandhandling.gateway.RetryScheduler;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.extensions.springcloud.commandhandling.SpringCloudCommandRouter;
import org.axonframework.extensions.springcloud.commandhandling.SpringHttpCommandBusConnector;
import org.axonframework.extensions.springcloud.commandhandling.mode.CapabilityDiscoveryMode;
import org.axonframework.extensions.springcloud.commandhandling.mode.RestCapabilityDiscoveryMode;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.axonframework.springboot.DistributedCommandBusProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.thoughtworks.xstream.XStream;


@Configuration
public class CommandRouterConfiguration {
	 // Example function providing a Spring Cloud Connector
  @Bean
  @Primary

    public CommandRouter springCloudCommandRouter(DiscoveryClient discoveryClient,Registration localServiceInstance ,
          
             RoutingStrategy routingStrategy,
             DistributedCommandBusProperties distributedCommandBusProperties ,CapabilityDiscoveryMode cmode) {
	  
	return  SpringCloudCommandRouter.builder()
      .discoveryClient(discoveryClient)
      .localServiceInstance(localServiceInstance)
     .capabilityDiscoveryMode(cmode)
      .routingStrategy(routingStrategy)
      
      //.messageRoutingInformationEndpoint(distributedCommandBusProperties.getSpringCloud().getFallbackUrl())
      /*.contextRootMetadataPropertyName(
              distributedCommandBusProperties.getSpringCloud().
      )*/
      .serializer(safeXStreamSerializer())
    //  .enforceHttpDiscovery()
      .build();
       // return new SpringCloudCommandRouter(discoveryClient, new AnnotationRoutingStrategy());
    }
  @Bean
  public RestTemplate restTmeplate()  
  {
	  return new RestTemplate();
	  
	  
  }
  
  
  @Bean
  public CapabilityDiscoveryMode capabilityDiscoveryMode(RestTemplate restTemplate) {
      return RestCapabilityDiscoveryMode.builder()
                                        .restTemplate(restTemplate)
                                        .serializer(safeXStreamSerializer())
                                        // Allows changing the endpoint used to find member capabilities
                                        .messageCapabilitiesEndpoint("/test")
                                        .build();
  }
  
  @Bean
  public RoutingStrategy routingStratergy() {
	  return AnnotationRoutingStrategy.defaultStrategy();
  }
  
  @Bean
  public CommandBusConnector springHttpCommandBusConnector(@Qualifier("localSegment") CommandBus localSegment,
                                                           RestOperations restOperations,
                                                           Serializer serializer) {
      return SpringHttpCommandBusConnector.builder()
                                          .localCommandBus(localSegment)
                                          .restOperations(restOperations)
                                          .serializer(serializer)
                                       //   .executor(/* custom Executor */)
                                          .build();
  }
  
  @Bean    @Primary

  public XStreamSerializer safeXStreamSerializer(){
	  XStream xStream = new XStream();
	  xStream.setClassLoader(AxonConfiguration.class.getClassLoader());
	  xStream.allowTypesByWildcard(new String[] {"org.axonframework.extension.**"});
    
      return XStreamSerializer.builder().xStream(xStream).build();
  }
 
  /*  @Bean
    public CommandBusConnector springHttpCommandBusConnector(@Qualifier("localSegment") CommandBus localSegment,
                                                             RestOperations restOperations,
                                                             Serializer serializer) {
        return new SpringHttpCommandBusConnector(localSegment, restOperations, serializer);
    }
 
    @Primary // to make sure this CommandBus implementation is used for autowiring
    @Bean
    public DistributedCommandBus springCloudDistributedCommandBus(CommandRouter commandRouter, 
                                                                  CommandBusConnector commandBusConnector) {
        return new DistributedCommandBus(commandRouter, commandBusConnector);
    }*/
	
	@Bean
	public EventStorageEngine engineevents()
	{
		return new InMemoryEventStorageEngine();
	}
	
  @Bean
  @Primary
  public DistributedCommandBus distributedCommandBus(CommandRouter commandRouter,
                                                     CommandBusConnector commandBusConnector) {
      return DistributedCommandBus.builder()
                                  .commandRouter(commandRouter)
                                  .connector(commandBusConnector)
                                  .build();
  }
  
  @Bean
  public CommandGateway commandGateway(DistributedCommandBus distributedCommandBus){
  /*Configurer configurer = DefaultConfigurer.defaultConfiguration();
  CommandBus commandBus = configurer.buildConfiguration().commandBus();*/
  ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
  RetryScheduler rs = IntervalRetryScheduler.builder().retryExecutor(scheduledExecutorService)
  .maxRetryCount(5).retryInterval(20000).build();
  return DefaultCommandGateway.builder().commandBus(distributedCommandBus).retryScheduler(rs).build();
  }
}
