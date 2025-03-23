/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.gamevaultcli.storage;


import com.project.gamevaultcli.entities.Transaction;
import com.project.gamevaultcli.interfaces.StorageInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionStorage implements StorageInterface<Transaction, Integer> {

    private final Map<Integer, Transaction> transactions = new HashMap<>(); // In-memory storage

    public TransactionStorage() {
        // Initialize with some hardcoded data
        transactions.put(1, new Transaction(1, 1, 1, "Purchase", 52.3f, LocalDateTime.now()));
    }

    @Override
    public Transaction findById(Integer transactionId) {
        return transactions.get(transactionId);
    }

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(transactions.values());
    }

    @Override
    public void save(Transaction transaction) {
        int nextId = transactions.keySet().stream().max(Integer::compare).orElse(0) + 1;
        transaction.setTransactionId(nextId);
        transactions.put(nextId, transaction);
    }

    @Override
    public void update(Transaction transaction) {
        // Assuming the transaction already exists. If not, this will overwrite.
        transactions.put(transaction.getTransactionId(), transaction);
    }

    @Override
    public void delete(Integer transactionId) {
        transactions.remove(transactionId);
    }
}