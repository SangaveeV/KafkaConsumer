package com.sangavee.kafkaConsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import com.sangavee.kafkaConsumer.entity.Transaction;
import com.sangavee.kafkaConsumer.service.TransactionService;

@Controller
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
//	@KafkaListener(topics = "${kafka.topic.name}")
//	@KafkaListener(topics = "${kafka.topic.name}",containerFactory = "kafkaListenerContainerFactory")
	
	/*to listen from particular partition
	@KafkaListener( topicPartitions = @TopicPartition(topic = "${kafka.topic.name}", partitions = {"0","1"})) */
	
	@KafkaListener(topics = "${kafka.topic.name}",containerFactory = "kafkaListenerContainerFactory")
	public void consumerTransaction(@Payload Transaction transaction,@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partitionId) {
		
		System.out.println("Message received from topic : " +transaction.toString() +" Partition ID : " +partitionId);
		System.out.println("Transaction Id : " +transaction.getId());
		System.out.println("Sender Name : " +transaction.getSenderName());
		System.out.println("Receiver Name : " +transaction.getReceiverName());
		System.out.println("Amount : " +transaction.getAmount());
		System.out.println("Transaction Date : " +transaction.getTransactionDate());
		
		transactionService.saveTransaction(transaction);
		
	}
}
