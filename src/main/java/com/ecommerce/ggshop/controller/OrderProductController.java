package com.ecommerce.ggshop.controller;

import com.ecommerce.ggshop.model.OrderProduct;
import com.ecommerce.ggshop.model.Payment;
import com.ecommerce.ggshop.repository.OrderProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderProductController {
    final OrderProductRepository orderProductRepository;

    public OrderProductController(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    //Get list of all orderProducts.
    @CrossOrigin(exposedHeaders = "http://localhost:8098")
    @RequestMapping(path = "/orderProducts", method = RequestMethod.GET)
    public List allOrderProducts() {

        return (List) orderProductRepository.findAll();
    }

    //Get the details of a orderProduct.
    @CrossOrigin(exposedHeaders = "http://localhost:8098")
    @RequestMapping(value = "/orderProduct/{id}", method = RequestMethod.GET)
    public OrderProduct orderProductDetails(@PathVariable("id") long id) {
        OrderProduct orderProduct = orderProductRepository.findById(id).get();

        return orderProduct;
    }
}
