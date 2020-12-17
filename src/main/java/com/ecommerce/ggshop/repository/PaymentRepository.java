package com.ecommerce.ggshop.repository;

import com.ecommerce.ggshop.model.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
    List<Payment> findPaymentsByCustomerId(long id);

    Payment findPaymentByOrderId(long id);
}
