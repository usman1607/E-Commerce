package com.ecommerce.ggshop.repository;

import com.ecommerce.ggshop.model.OrderProduct;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {

}
