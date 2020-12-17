package com.ecommerce.ggshop.model;

import javax.persistence.*;

@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Product product;
    private Double price;
    @ManyToOne
    private Order Order;
    private int quantity;

    public OrderProduct() {

    }

    public OrderProduct(Product product, Double price, com.ecommerce.ggshop.model.Order order, int quantity) {
        this.product = product;
        this.price = price;
        Order = order;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Order getOrder() {
        return Order;
    }

    public void setOrder(Order order) {
        Order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
