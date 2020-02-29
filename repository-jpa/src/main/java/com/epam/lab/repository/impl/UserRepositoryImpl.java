package com.epam.lab.repository.impl;


import com.epam.lab.model.User;
import com.epam.lab.repository.UserRepository;
import com.epam.lab.specification.EntitySpecification;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User create(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public void update(User user) {
        User storedUser = entityManager.find(User.class, user.getId());
        if (storedUser == null) {
            throw new EntityNotFoundException("No such user.");
        }
        ObjectPatcher.patch(storedUser, user);
    }

    @Override
    public void delete(User user) {
        User storedUser = entityManager.find(User.class, user.getId());
        if (storedUser == null) {
            throw new EntityNotFoundException("No such user.");
        }
        entityManager.remove(storedUser);
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> query(EntitySpecification<User> specification) {
        return entityManager.createQuery(specification.specified(entityManager)).getResultList();
    }

}
