package com.sangavee.kafkaConsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangavee.kafkaConsumer.entity.Transaction;
import com.sangavee.kafkaConsumer.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public void saveTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
		
	}
	
}
