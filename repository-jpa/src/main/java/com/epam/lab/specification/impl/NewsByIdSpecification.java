package com.epam.lab.specification.impl;

import com.epam.lab.model.News;
import com.epam.lab.specification.EntitySpecification;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class NewsByIdSpecification implements EntitySpecification<News> {

    private long id;

    public NewsByIdSpecification(long id) {
        this.id = id;
    }

    public CriteriaQuery<News> specified(EntityManager manager) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<News> query = criteriaBuilder.createQuery(News.class);
        Root<News> model = query.from(News.class);
        query.where(criteriaBuilder.equal(model.get("id"), this.id));
        return query;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsByIdSpecification that = (NewsByIdSpecification) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
