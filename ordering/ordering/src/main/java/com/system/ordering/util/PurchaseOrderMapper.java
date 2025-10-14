package com.system.ordering.util;

import com.system.events.MedicineOutOfStockEvent;
import com.system.ordering.entity.Order;
import com.system.ordering.entity.OrderStatus;

import java.util.Date;
import java.util.Map;

import static com.system.ordering.util.Constants.DEFAULT_MEDICINE_QUANTITY;

public class PurchaseOrderMapper {

    public static Order from(MedicineOutOfStockEvent event) {
        Order order = new Order();

        order.setOrderStatus(OrderStatus.PENDING);
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());
        order.setQuantity(DEFAULT_MEDICINE_QUANTITY);
        order.setPriority(event.priority());
        order.setMedicineId(event.medicineId());
        order.setMedicineName(event.medicineName());

        return order;
    }
}
