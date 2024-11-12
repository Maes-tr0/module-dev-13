package ua.maestr0;

import ua.maestr0.model.Client;
import ua.maestr0.model.Planet;
import ua.maestr0.model.Ticket;
import ua.maestr0.service.ClientService;
import ua.maestr0.service.PlanetService;
import ua.maestr0.service.TicketService;
import ua.maestr0.util.database.FlywayConfig;

import static ua.maestr0.dao.CRUDRepository.logger;

public class Application {
    public static void main(String[] args) {
        FlywayConfig.migrate();

        ClientService clientService = new ClientService();
        PlanetService planetService = new PlanetService();
        TicketService ticketService = new TicketService();

        // CREATE
        Client newClient = new Client();
        newClient.setName("New Client");
        clientService.save(newClient);
        logger.info("Created new client: {}", newClient);

        Planet newPlanet = new Planet();
        newPlanet.setId("NEPTUNE");
        newPlanet.setName("Neptune");
        planetService.save(newPlanet);
        logger.info("Created new planet: {}", newPlanet);

        Ticket newTicket = new Ticket();
        newTicket.setClient(newClient);
        newTicket.setFromPlanet(newPlanet);
        newTicket.setToPlanet(planetService.get("MARS"));
        ticketService.save(newTicket);
        logger.info("Created new ticket: {}", newTicket);

        // READ
        Client retrievedClient = clientService.get(newClient.getId());
        logger.info("Retrieved Client: {}", retrievedClient);

        Planet retrievedPlanet = planetService.get("NEPTUNE");
        logger.info("Retrieved Planet: {}", retrievedPlanet);

        Ticket retrievedTicket = ticketService.get(newTicket.getId());
        logger.info("Retrieved Ticket: {}", retrievedTicket);

        // UPDATE
        retrievedClient.setName("Updated Client Name");
        clientService.update(retrievedClient);
        logger.info("Updated Client: {}", clientService.get(retrievedClient.getId()));

        retrievedPlanet.setName("Updated Neptune");
        planetService.update(retrievedPlanet);
        logger.info("Updated Planet: {}", planetService.get(retrievedPlanet.getId()));

        // DELETE
        ticketService.delete(newTicket.getId());
        logger.info("Ticket deleted with ID: {}", newTicket.getId());

        clientService.delete(newClient.getId());
        logger.info("Client deleted with ID: {}", newClient.getId());

        planetService.delete("NEPTUNE");
        logger.info("Planet deleted with ID: NEPTUNE");

        // FINAL READ CHECK
        logger.info("Final check for client existence: {}", clientService.get(newClient.getId()));
        logger.info("Final check for planet existence: {}", planetService.get("NEPTUNE"));
        logger.info("Final check for ticket existence: {}", ticketService.get(newTicket.getId()));
    }
}

