package com.smola.shopping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

final class Client {
    private final String id;
    private final String fullName;
    private final List<Order> orders;

    Client(String id, String fullName, List<Order> orders) {
        this.id = id;
        this.fullName = fullName;
        this.orders = new ArrayList<>(orders);
    }

    Client(Client client, List<Order> orders) {
        this.id = client.getId();
        this.fullName = client.getFullName();
        this.orders = new ArrayList<>();
        this.orders.addAll(client.getOrders());
        this.orders.addAll(orders);
    }

    String getId() {
        return id;
    }

    String getFullName() {
        return fullName;
    }

    List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
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


}
