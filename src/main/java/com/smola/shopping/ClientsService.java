package com.smola.shopping;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;

import static java.util.stream.Collectors.toList;

class ClientsService {
    private ClientRepository clientRepository;
    private ClientsFileReader clientsFileReader;

    ClientsService(ClientRepository clientRepository, ClientsFileReader clientsFileReader) {
        this.clientRepository = clientRepository;
        this.clientsFileReader = clientsFileReader;
    }


    Client findByFullName(String fullName) {
        return clientRepository.findByFullName(fullName)
                .orElseThrow(() -> new RuntimeException("Client does not exists!"));
    }

    Collection<Client> getClientsSortedByFullnames() {
        Comparator<Client> byNamesAndIdsComparator = Comparator.comparing(Client::getFullName)
                .thenComparing(Client::getId);
        return clientRepository.findAll()
                .stream()
                .sorted(byNamesAndIdsComparator).collect(toList());
    }

    Collection<Client> getClientsSortedByOrdersValue() {
        Comparator<Client> byTotalPriceComparator = (o1, o2) -> {
            BigDecimal firstClientSumOfOrder = calculateClientTotalOrderPrice(o1);
            BigDecimal secondClientSumOfOrder = calculateClientTotalOrderPrice(o2);
            return secondClientSumOfOrder.compareTo(firstClientSumOfOrder);
        };
        return clientRepository.findAll()
                .stream()
                .sorted(byTotalPriceComparator).collect(toList());
    }

    Collection<Order> getAllClientOrder(Client client) {
        return clientRepository.findAll()
                .stream()
                .filter(e -> e.equals(client))
                .map(Client::getOrders)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    BigDecimal calculateClientTotalOrderPrice(Client client) {
        return client.getOrders()
                .stream()
                .map(e -> {
                    BigDecimal singleProductPrice = e.getProduct().getPrice();
                    Double nbOfProducts = e.getNbOfProducts();
                    return singleProductPrice.multiply(BigDecimal.valueOf(nbOfProducts));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    Collection<Client> getAll() {
        return clientRepository.findAll();
    }
}
