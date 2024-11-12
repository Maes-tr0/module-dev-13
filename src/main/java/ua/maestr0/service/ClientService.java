package ua.maestr0.service;

import ua.maestr0.dao.ClientDAO;
import ua.maestr0.model.Client;

public class ClientService extends EntityService<Client, Long> {
    public ClientService() {
        super(new ClientDAO());
    }
}
