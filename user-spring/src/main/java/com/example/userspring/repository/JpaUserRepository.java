package com.example.userspring.repository;

import com.example.userspring.domain.User;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

//@Repository
public class JpaUserRepository implements UserRepository{

    private final EntityManager entityManager;

    public JpaUserRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public User add(User user) {
        entityManager.persist(user);
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        List<User> result = entityManager.createQuery("Select u from User u where u.id = :id", User.class)
                .setParameter("id",id)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return  entityManager.createQuery("select u from User u", User.class).getResultList();
    }
}
