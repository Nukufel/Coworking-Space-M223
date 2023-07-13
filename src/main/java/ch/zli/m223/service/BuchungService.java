package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Buchung;

@ApplicationScoped
public class BuchungService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Buchung createBuchung(Buchung buchung) {
        return entityManager.merge(buchung);
    }

    @Transactional
    public void deleteBuchung(Long id) {
        var entity = entityManager.find(Buchung.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public Buchung updateBuchung(Long id, Buchung buchung) {
        buchung.setId(id);
        return entityManager.merge(buchung);
    }

    public List<Buchung> findAll() {
        var query = entityManager.createQuery("FROM Buchung", Buchung.class);
        return query.getResultList();
    }
}
