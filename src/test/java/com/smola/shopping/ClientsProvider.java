package com.smola.shopping;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class ClientsProvider {
    static final Product MILK = new Product("milk", new BigDecimal(10.0));
    static final Product BUTTER = new Product("butter", new BigDecimal(20.0));
    static final Product SODA = new Product("soda", new BigDecimal(30.0));
    static final List<Order> FIRST_CLIENT_ORDERS = Arrays.asList(new Order(MILK, 2.0), new Order(BUTTER, 3.0));
    static final List<Order> SECOND_CLIENT_ORDERS = Arrays.asList(new Order(MILK, 2.0));
    static final List<Order> THIRD_CLIENT_ORDERS = Arrays.asList(new Order(MILK, 2.0), new Order(BUTTER, 5.0), new Order(SODA, 10.0));
    static final Client FIRST_CLIENT = new Client("c00002", "Kowalski Jan", FIRST_CLIENT_ORDERS);
    static final Client SECOND_CLIENT = new Client("c00001", "Kowalski Jan", SECOND_CLIENT_ORDERS);
    static final Client THIRD_CLIENT = new Client("z00010", "Abrowski Michal", THIRD_CLIENT_ORDERS);


}
