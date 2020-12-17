package com.ecommerce.ggshop.repository;

import com.ecommerce.ggshop.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
