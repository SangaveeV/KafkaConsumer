package com.sangavee.kafkaConsumer.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="TRAN_SEQ",sequenceName = "trans_sequence",allocationSize = 1)
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAN_SEQ")
	private BigDecimal id;
	
	private String senderName;
	private String receiverName;
	private BigDecimal amount;
	private Date transactionDate;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(BigDecimal id, String senderName, String receiverName, BigDecimal amount, Date transactionDate) {
		super();
		this.id = id;
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
