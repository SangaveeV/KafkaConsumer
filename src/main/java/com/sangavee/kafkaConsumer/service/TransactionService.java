package com.sangavee.kafkaConsumer.service;

import com.sangavee.kafkaConsumer.entity.Transaction;

public interface TransactionService {
	
	void saveTransaction(Transaction transaction);
}
