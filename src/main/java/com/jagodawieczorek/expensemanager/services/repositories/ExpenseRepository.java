package com.jagodawieczorek.expensemanager.services.repositories;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.jagodawieczorek.expensemanager.services.model.Expense;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ExpenseRepository extends ReactiveCosmosRepository<Expense, String> {

    Flux<Expense> findByName(String name);
    Flux<Expense> findByCategory(String category);
    Flux<Expense> findByNameAndCategory(String name, String category);
}
