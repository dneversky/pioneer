package dev.dneversky.pioneer.user.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.errors.TimeoutException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group.team");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "dev.dneversky.pioneer.user.MessageModel");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "dev.dneversky.pioneer.*");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public CommonErrorHandler commonErrorHandler() {
        return new DefaultErrorHandler();
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRetryTemplate(retryTemplate());
        return factory;
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        /* here retry policy is used to set
        1. the number of attempts to retry
        2. what exceptions you wanted to retry
        3. what you don't want to retry .*/
        retryTemplate.setRetryPolicy(getSimpleRetryPolicy());
        return retryTemplate;
    }

//    @Bean
//    public ReplyingKafkaTemplate<String, MessageModel, MessageModel> replyingKafkaTemplate
//            (ProducerFactory<String, MessageModel> producerFactory,
//             ConcurrentKafkaListenerContainerFactory<String, MessageModel> factory) {
//        ConcurrentMessageListenerContainer<String, MessageModel> replyContainer = factory.createContainer(replyTopic);
//        replyContainer.getContainerProperties().setMissingTopicsFatal(false);
//        replyContainer.getContainerProperties().setGroupId("group.team");
//        return new ReplyingKafkaTemplate<>(producerFactory, replyContainer);
//    }

    @Bean
    public SimpleRetryPolicy getSimpleRetryPolicy() {
        Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();
        exceptionMap.put(IllegalArgumentException.class, false);
        exceptionMap.put(TimeoutException.class, true);
        return new SimpleRetryPolicy(3, exceptionMap, true);
    }
}