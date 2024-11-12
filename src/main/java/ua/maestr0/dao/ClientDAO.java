package ua.maestr0.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.maestr0.model.Client;


public class ClientDAO extends CRUDRepository<Client, Long> {
    private static final Logger logger = LoggerFactory.getLogger(ClientDAO.class);

    @Override
    public Client get(Long id) {
        Transaction tx = null;
        Client client = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Client> cq = cb.createQuery(Client.class);
            Root<Client> clientRoot = cq.from(Client.class);

            clientRoot.fetch("tickets", JoinType.LEFT);

            cq.select(clientRoot).where(cb.equal(clientRoot.get("id"), id));

            client = session.createQuery(cq).uniqueResult();
            tx.commit();

            if (client != null) {
                logger.info("Client with associations retrieved successfully: {}", client);
            } else {
                logger.warn("No client found with id: {}", id);
            }
        } catch (Exception e) {
            if (tx != null && tx.getStatus() == TransactionStatus.ACTIVE) {
                tx.rollback();
            }
            logger.error("Error getting client with id: {}", id, e);
        }
        return client;
    }
}
