package com.sangavee.kafkaConsumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.sangavee.kafkaConsumer.entity.Transaction;

@Configuration
@EnableKafka
public class KafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
	
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	
	public String getBootstrapServer() {
		return bootstrapServer;
	}
	
    public String getGroupId() {
		return groupId;
	}

	@Bean
    public ConsumerFactory<String, Transaction> consumerFactory() {
		
		JsonDeserializer<Transaction> deserializer= new JsonDeserializer<>(Transaction.class);
		deserializer.setRemoveTypeHeaders(false);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);
		
        Map<String, Object> props = new HashMap<>();
        props.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, getBootstrapServer());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, getGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(),new ErrorHandlingDeserializer<>(deserializer));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Transaction> kafkaListenerContainerFactory() {
   
        ConcurrentKafkaListenerContainerFactory<String, Transaction> factory =
          new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        /*  filter messages. condition here will not be consumed
         
          factory.setRecordFilterStrategy(
        	      record -> record.value().getAmount().intValue()<=1000);
         */
    
        return factory;
    }

}
