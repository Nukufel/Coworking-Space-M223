package ch.zli.m223.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.User;

@ApplicationScoped
public class UserService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public User createUser(User user) {
        return entityManager.merge(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        var entity = entityManager.find(User.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public User updateUser(Long id, User user) {
        user.setId(id);
        return entityManager.merge(user);
    }

    public List<User> findAll() {
        var query = entityManager.createQuery("FROM user_entity", User.class);
        return query.getResultList();
    }

    public Optional<User> findByEmail(String email) {
        return entityManager
                .createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }
    
    public User findOne(Long id) {
        return entityManager.find(User.class, id);
    }
}
