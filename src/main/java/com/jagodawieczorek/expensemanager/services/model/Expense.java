package com.jagodawieczorek.expensemanager.services.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Container(containerName = "expenses")
public class Expense {
    @Id
    private String id;
    @PartitionKey
    private String name;
    private Long amount;
    private String category;
}
