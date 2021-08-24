package com.arvindkgs.delivery;

import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class MessagingConfiguration {

	@Bean(name = "amazonSQS")
    @Primary
    public AmazonSQSAsync amazonSQSAsync(AWSCredentialsProvider awsCredentialsProvider) {
        return AmazonSQSAsyncClientBuilder.defaultClient();
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSQS){
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSQS);
        factory.setMaxNumberOfMessages(10);
        factory.setWaitTimeOut(5);
        return factory;
    }

    @Primary
    @Bean
    @Qualifier("threadPoolExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // number of threads initially started
        threadPoolTaskExecutor.setCorePoolSize(5);
        // max threads that can be created, after queue is full
        threadPoolTaskExecutor.setMaxPoolSize(10);
        // max tasks in queue after which new threads are created upto maxPoolSize
        threadPoolTaskExecutor.setQueueCapacity(50);
        return threadPoolTaskExecutor;
    }
}
