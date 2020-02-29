package com.epam.lab.specification.impl;

import com.epam.lab.model.Author;
import com.epam.lab.specification.EntitySpecification;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AuthorsByIdSpecification implements EntitySpecification<Author> {

    private long id;

    public AuthorsByIdSpecification(long id) {
        this.id = id;
    }

    public CriteriaQuery<Author> specified(EntityManager manager) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Author> query = criteriaBuilder.createQuery(Author.class);
        Root<Author> model = query.from(Author.class);
        query.where(criteriaBuilder.equal(model.get("id"), this.id));
        return query;
    }
}
