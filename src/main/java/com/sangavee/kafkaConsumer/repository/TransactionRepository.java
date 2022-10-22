package com.sangavee.kafkaConsumer.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sangavee.kafkaConsumer.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, BigDecimal>{

}
