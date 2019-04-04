package com.smola.shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Client {
    private final String id;
    private final String fullName;
    private List<Order> orders;

    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
        this.orders = new ArrayList<>();
    }

    public Client(String id, String fullName, List<Order> orders) {
        this.id = id;
        this.fullName = fullName;
        this.orders = orders;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public boolean addOrder(Order order) {
        return orders.add(order);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(fullName, client.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", orders=" + orders +
                '}';
    }

    public void addOrder(List<Order> orders) {
        for (Order order : orders) {
            this.addOrder(order);
        }
    }
}
