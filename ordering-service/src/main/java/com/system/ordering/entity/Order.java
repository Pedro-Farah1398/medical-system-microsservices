package com.system.ordering.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    private Long medicineId;
    private String medicineName;
    private Date createdAt;
    private Date updatedAt;
    private Integer quantity;
    private String supplier;
    private OrderStatus orderStatus;
    private String priority;
    private String requestedBy;
    private String notes;
}
