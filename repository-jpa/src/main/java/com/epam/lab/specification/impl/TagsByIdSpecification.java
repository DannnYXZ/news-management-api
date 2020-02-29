package com.epam.lab.specification.impl;

import com.epam.lab.model.Tag;
import com.epam.lab.specification.EntitySpecification;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TagsByIdSpecification implements EntitySpecification<Tag> {

    private long id;

    public TagsByIdSpecification(long id) {
        this.id = id;
    }

    public CriteriaQuery<Tag> specified(EntityManager manager) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> model = query.from(Tag.class);
        query.where(criteriaBuilder.equal(model.get("id"), this.id));
        return query;
    }
}
