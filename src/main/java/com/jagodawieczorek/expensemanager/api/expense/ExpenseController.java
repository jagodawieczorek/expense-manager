package com.jagodawieczorek.expensemanager.api.expense;

import com.jagodawieczorek.expensemanager.services.model.Expense;
import com.jagodawieczorek.expensemanager.services.repositories.ExpenseRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseRepository repository;

    public ExpenseController(ExpenseRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    private Mono<Expense> saveExpense(@RequestBody Expense expense) {
        expense.setId(UUID.randomUUID().toString());
        return repository.save(expense);
    }

    @GetMapping("/{id}")
    private Mono<Expense> getExpenseById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PutMapping("/{id}")
    private Mono<Expense> updateExpense(@RequestBody Expense expense) {
        return repository.save(expense);
    }

    @DeleteMapping("/{id}")
    private Mono<Void> deleteExpense(@PathVariable String id) {
        return repository.deleteById(id);
    }

    @GetMapping
    private Flux<Expense> getExpenses(@RequestParam Optional<String> category, @RequestParam Optional<String> name) {
        if (category.isPresent() && name.isPresent()) {
            return repository.findByNameAndCategory(name.get(), category.get());
        } else if (category.isPresent()) {
            return repository.findByCategory(category.get());
        } else if(name.isPresent()) {
            return repository.findByName(name.get());
        } else {
            return repository.findAll();
        }
    }
}