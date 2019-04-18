package com.smola.shopping;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;

import static java.util.stream.Collectors.toList;

public interface ClientService {
    Client findByFullName(String fullName);
    Collection<Client> getClientsSortedByFullname();
    Collection<Client> getClientsSortedByOrdersValue();
    Collection<Order> getAllClientOrder(Client client);
    BigDecimal calculateClientTotalOrderPrice(Client client);
    Collection<Client> getAll();
}
