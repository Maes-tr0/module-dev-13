package ua.maestr0.service;

import ua.maestr0.dao.TicketDAO;
import ua.maestr0.model.Ticket;

public class TicketService extends EntityService<Ticket, Long> {
    public TicketService() {
        super(new TicketDAO());
    }
}
