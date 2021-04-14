# axonspringcloud


Axon euraka server http://localhost:8761/
Axon euraka client 1 GiftCardEurekaClient  8080
Axon euraka client 2 GiftCardEurekaClient2  8081

 GiftCardEurekaClient http://localhost:8080/api/issuecard should invoke IssueCardCommand local command and UpdateCustomerComamnd running on port 8081 on GiftCardEurekaClient2.


How ever exception is thrown when  commandGateway.send(commandGateway.send(new UpdateCustomerCommand("1","updated"))); is invoked from GiftCardEurekaClient.UpdateCustomerCommand handler is running in GiftCardEurekaClient2.


org.axonframework.messaging.annotation.MessageHandlerInvocationException: Error handling event of type [class com.axon.axoneurakaclient.event.CardIssuedEvent] in aggregate
	at org.axonframework.modelling.command.inspection.AnnotatedAggregateMetaModelFactory$AnnotatedAggregateModel.lambda$doPublish$17(AnnotatedAggregateMetaModelFactory.java:568) ~[axon-modelling-4.5.jar:4.5]
	at java.base/java.util.Optional.ifPresent(Optional.java:183) ~[na:na]
	at org.axonframework.modelling.command.inspection.AnnotatedAggregateMetaModelFactory$AnnotatedAggregateModel.doPublish(AnnotatedAggregateMetaModelFactory.java:562) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.modelling.command.inspection.AnnotatedAggregateMetaModelFactory$AnnotatedAggregateModel.publish(AnnotatedAggregateMetaModelFactory.java:557) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.modelling.command.inspection.AnnotatedAggregate.publish(AnnotatedAggregate.java:378) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.eventsourcing.EventSourcedAggregate.publish(EventSourcedAggregate.java:253) ~[axon-eventsourcing-4.5.jar:4.5]
	at org.axonframework.modelling.command.inspection.AnnotatedAggregate.doApply(AnnotatedAggregate.java:443) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.eventsourcing.EventSourcedAggregate.doApply(EventSourcedAggregate.java:247) ~[axon-eventsourcing-4.5.jar:4.5]
	at org.axonframework.modelling.command.inspection.AnnotatedAggregate.lambda$doApply$9(AnnotatedAggregate.java:459) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.modelling.command.inspection.AnnotatedAggregate.lambda$registerRoot$0(AnnotatedAggregate.java:286) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.modelling.command.AggregateLifecycle.lambda$execute$0(AggregateLifecycle.java:189) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.messaging.Scope.executeWithResult(Scope.java:111) ~[axon-messaging-4.5.jar:4.5]
	at org.axonframework.modelling.command.AggregateLifecycle.execute(AggregateLifecycle.java:188) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.modelling.command.inspection.AnnotatedAggregate.registerRoot(AnnotatedAggregate.java:284) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.eventsourcing.EventSourcedAggregate.initialize(EventSourcedAggregate.java:120) ~[axon-eventsourcing-4.5.jar:4.5]
	at org.axonframework.eventsourcing.EventSourcingRepository.doCreateNewForLock(EventSourcingRepository.java:171) ~[axon-eventsourcing-4.5.jar:4.5]
	at org.axonframework.eventsourcing.EventSourcingRepository.doCreateNewForLock(EventSourcingRepository.java:52) ~[axon-eventsourcing-4.5.jar:4.5]
	at org.axonframework.modelling.command.LockingRepository.doCreateNew(LockingRepository.java:81) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.modelling.command.LockingRepository.doCreateNew(LockingRepository.java:52) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.modelling.command.AbstractRepository.newInstance(AbstractRepository.java:90) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.modelling.command.AggregateAnnotationCommandHandler$AggregateConstructorCommandHandler.handle(AggregateAnnotationCommandHandler.java:376) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.modelling.command.AggregateAnnotationCommandHandler$AggregateConstructorCommandHandler.handle(AggregateAnnotationCommandHandler.java:365) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.modelling.command.AggregateAnnotationCommandHandler.handle(AggregateAnnotationCommandHandler.java:174) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.modelling.command.AggregateAnnotationCommandHandler.handle(AggregateAnnotationCommandHandler.java:62) ~[axon-modelling-4.5.jar:4.5]
	at org.axonframework.messaging.DefaultInterceptorChain.proceed(DefaultInterceptorChain.java:57) ~[axon-messaging-4.5.jar:4.5]
	at org.axonframework.messaging.interceptors.CorrelationDataInterceptor.handle(CorrelationDataInterceptor.java:65) ~[axon-messaging-4.5.jar:4.5]
	at org.axonframework.messaging.DefaultInterceptorChain.proceed(DefaultInterceptorChain.java:55) ~[axon-messaging-4.5.jar:4.5]
	at org.axonframework.messaging.unitofwork.DefaultUnitOfWork.executeWithResult(DefaultUnitOfWork.java:74) ~[axon-messaging-4.5.jar:4.5]
	at org.axonframework.commandhandling.SimpleCommandBus.handle(SimpleCommandBus.java:177) ~[axon-messaging-4.5.jar:4.5]
	at org.axonframework.commandhandling.SimpleCommandBus.doDispatch(SimpleCommandBus.java:143) ~[axon-messaging-4.5.jar:4.5]
	at org.axonframework.commandhandling.SimpleCommandBus.dispatch(SimpleCommandBus.java:111) ~[axon-messaging-4.5.jar:4.5]
	at org.axonframework.extensions.springcloud.commandhandling.SpringHttpCommandBusConnector.send(SpringHttpCommandBusConnector.java:145) ~[axon-springcloud-4.4.jar:4.4]
	at org.axonframework.commandhandling.distributed.DistributedCommandBus.dispatch(DistributedCommandBus.java:177) ~[axon-messaging-4.5.jar:4.5]
	at org.axonframework.commandhandling.gateway.RetryingCallback$RetryDispatch.run(RetryingCallback.java:120) ~[axon-messaging-4.5.jar:4.5]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
Caused by: java.lang.NullPointerException: null
	at com.axon.axoneurakaclient.aggregate.GiftCard.handleCreatedEvent(GiftCard.java:34) ~[main/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor63.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.axonframework.messaging.annotation.AnnotatedMessageHandlingMember.handle(AnnotatedMessageHandlingMember.java:144) ~[axon-messaging-4.5.jar:4.5]
	at org.axonframework.messaging.annotation.AnnotatedHandlerInspector$NoMoreInterceptors.handle(AnnotatedHandlerInspector.java:372) ~[axon-messaging-4.5.jar:4.5]
	at org.axonframework.modelling.command.inspection.AnnotatedAggregateMetaModelFactory$AnnotatedAggregateModel.lambda$doPublish$17(AnnotatedAggregateMetaModelFactory.java:565) ~[axon-modelling-4.5.jar:4.5]
	... 39 common frames omitted


