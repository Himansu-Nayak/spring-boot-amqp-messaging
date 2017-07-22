package com.org.spring.boot.amqp.messaging;

import com.org.spring.boot.amqp.messaging.exception.MessageListenerExceptionHandler;
import com.org.spring.boot.amqp.processor.ProcessorAdapter;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MessagingConsumerConfiguration {

    @Autowired
    protected CachingConnectionFactory connectionFactory;

    @Autowired
    protected ProcessorAdapter processorAdapter;

    protected abstract String queue();

    protected abstract String exchange();

    protected SimpleMessageListenerContainer setupSimpleMessageListenerContainer() {
        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue());
        container.setConcurrentConsumers(5);
        container.setMaxConcurrentConsumers(10);
        container.setMessageListener(messageListener());
        container.setMessageConverter(jsonMessageConverted());
        container.setErrorHandler(new MessageListenerExceptionHandler(exchange(), queue()));
        container.setDefaultRequeueRejected(false);
        //container.setAdviceChain(new Advice[] {retryOperationsInterceptor()});
        return container;
    }

    /*@Bean
    RetryOperationsInterceptor retryOperationsInterceptor(
            RabbitTemplate rabbitTemplate) {

        Map<Class<? extends Throwable>, Boolean> retryableExceptions = new HashMap<>();
        retryableExceptions.put(IOException.class, true);
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy(3, retryableExceptions, true);

        return RetryInterceptorBuilder.stateless()
                                      .retryPolicy(simpleRetryPolicy)
                                      .backOffOptions(5, 2.0, 5000)
                                      .recoverer(new RepublishMessageRecoverer(rabbitTemplate, errorExchange, ""))
                                      .build();
    }*/

    public MessageListener messageListener() {
        return message -> processorAdapter.process(message);
    }


    protected JsonMessageConverter jsonMessageConverted() {
        return new JsonMessageConverter();
    }

}
