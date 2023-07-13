package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Booking;

@ApplicationScoped
public class BookingService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Booking createBuchung(Booking buchung) {
        return entityManager.merge(buchung);
    }

    @Transactional
    public void deleteBuchung(Long id) {
        var entity = entityManager.find(Booking.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public Booking updateBuchung(Long id, Booking buchung) {
        buchung.setId(id);
        return entityManager.merge(buchung);
    }

    public List<Booking> findAll() {
        var query = entityManager.createQuery("FROM Buchung", Booking.class);
        return query.getResultList();
    }
}
