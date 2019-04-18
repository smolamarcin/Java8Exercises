package com.smola.shopping;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;

import static java.util.stream.Collectors.toList;

class ClientsServiceImpl implements ClientService{
    private ClientRepository clientRepository;
    private ClientsFileParser clientsFileReader;

    ClientsServiceImpl(ClientRepository clientRepository, ClientsFileParser clientsFileReader) {
        this.clientRepository = clientRepository;
        this.clientsFileReader = clientsFileReader;
    }


    public Client findByFullName(String fullName) {
        return clientRepository.findByFullName(fullName)
                .orElseThrow(() -> new RuntimeException("Client does not exists!"));
    }

    public Collection<Client> getClientsSortedByFullname() {
        Comparator<Client> byNamesAndIdsComparator = Comparator.comparing(Client::getFullName)
                .thenComparing(Client::getId);
        return clientRepository.findAll()
                .stream()
                .sorted(byNamesAndIdsComparator).collect(toList());
    }

    public Collection<Client> getClientsSortedByOrdersValue() {
        Comparator<Client> byTotalPriceComparator = (o1, o2) -> {
            BigDecimal firstClientSumOfOrder = calculateClientTotalOrderPrice(o1);
            BigDecimal secondClientSumOfOrder = calculateClientTotalOrderPrice(o2);
            return secondClientSumOfOrder.compareTo(firstClientSumOfOrder);
        };
        return clientRepository.findAll()
                .stream()
                .sorted(byTotalPriceComparator).collect(toList());
    }


    public Collection<Order> getAllClientOrder(Client client) {
        return clientRepository.findAll()
                .stream()
                .filter(e -> e.equals(client))
                .map(Client::getOrders)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    public BigDecimal calculateClientTotalOrderPrice(Client client) {
        return client.getOrders()
                .stream()
                .map(e -> {
                    BigDecimal singleProductPrice = e.getProduct().getPrice();
                    Double nbOfProducts = e.getNbOfProducts();
                    return singleProductPrice.multiply(BigDecimal.valueOf(nbOfProducts));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Collection<Client> getAll() {
        return clientRepository.findAll();
    }
}
