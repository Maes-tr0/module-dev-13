package ua.maestr0.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.maestr0.model.Ticket;


public class TicketDAO extends CRUDRepository<Ticket, Long> {
    private static final Logger logger = LoggerFactory.getLogger(TicketDAO.class);

    @Override
    public Ticket get(Long id) {
        Transaction tx = null;
        Ticket ticket = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
            Root<Ticket> ticketRoot = cq.from(Ticket.class);

            ticketRoot.fetch("client");
            ticketRoot.fetch("fromPlanet");
            ticketRoot.fetch("toPlanet");

            cq.select(ticketRoot).where(cb.equal(ticketRoot.get("id"), id));

            ticket = session.createQuery(cq).uniqueResult();
            tx.commit();

            if (ticket != null) {
                logger.info("Ticket with associations retrieved successfully: {}", ticket);
            } else {
                logger.warn("No ticket found with id: {}", id);
            }
        } catch (Exception e) {
            if (tx != null && tx.getStatus() == TransactionStatus.ACTIVE) {
                tx.rollback();
            }
            logger.error("Error getting ticket with id: {}", id, e);
        }
        return ticket;
    }
}
