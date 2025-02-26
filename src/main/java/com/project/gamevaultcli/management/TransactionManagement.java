/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.gamevaultcli.management;

import com.project.gamevaultcli.entities.Transaction;
import com.project.gamevaultcli.storage.TransactionStorage;

import java.util.List;

public class TransactionManagement {

    private final TransactionStorage transactionStorage;

    public TransactionManagement(TransactionStorage transactionStorage) {
        this.transactionStorage = transactionStorage;
    }

    public Transaction getTransaction(int transactionId) {
        return transactionStorage.findById(transactionId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionStorage.findAll();
    }

    public void addTransaction(Transaction transaction) {
        transactionStorage.save(transaction);
    }
}
