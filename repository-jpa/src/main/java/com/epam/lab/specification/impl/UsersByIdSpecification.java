package com.epam.lab.specification.impl;

import com.epam.lab.model.User;
import com.epam.lab.specification.EntitySpecification;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UsersByIdSpecification implements EntitySpecification<User> {

    private long id;

    public UsersByIdSpecification(long id) {
        this.id = id;
    }

    public CriteriaQuery<User> specified(EntityManager manager) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> model = query.from(User.class);
        query.where(criteriaBuilder.equal(model.get("id"), this.id));
        return query;
    }
}
