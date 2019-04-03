package com.smola;

import java.math.BigDecimal;
import java.util.List;

class Order {
    private String product;
    private BigDecimal price;
    private Double nbOfProducts;

    public Order(String product, BigDecimal price, Double nbOfProducts) {
        this.product = product;
        this.price = price;
        this.nbOfProducts = nbOfProducts;
    }

    @Override
    public String toString() {
        return "Order{" +
                "product='" + product + '\'' +
                ", nbOfProducts=" + nbOfProducts +
                '}';
    }
}
