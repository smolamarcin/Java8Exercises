package com.smola.shopping;

class Order {
    private Product product;
    private Double nbOfProducts;

    public Order(Product product, Double nbOfProducts) {
        this.product = product;
        this.nbOfProducts = nbOfProducts;
    }

    public Product getProduct() {
        return new Product(product.getProductName(), product.getPrice());
    }

    public Double getNbOfProducts() {
        return nbOfProducts;
    }

    @Override
    public String toString() {
        return "Order{" +
                "product=" + product +
                ", nbOfProducts=" + nbOfProducts +
                '}';
    }
}
