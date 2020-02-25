package com.epam.lab.specification.impl;

import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.SearchCriteria;
import com.epam.lab.model.Tag;
import com.epam.lab.specification.EntitySpecification;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NewsBySearchCriteriaSpecification implements EntitySpecification<News> {

    private SearchCriteria criteria;

    public NewsBySearchCriteriaSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public CriteriaQuery<News> specified(EntityManager manager) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<News> query = criteriaBuilder.createQuery(News.class);
        Root<News> root = query.from(News.class);
        List<Predicate> conditions = new ArrayList<>();
        if (this.criteria.getAuthor() != null) {
            Join<News, Author> authorsJoin = root.join("author");
            Predicate hasName = criteriaBuilder.equal(authorsJoin.get("name"), criteria.getAuthor().getName());
            conditions.add(hasName);
        }
        if (this.criteria.getTags() != null) {
            Join<News, Tag> tagsJoin = root.join("tags");
            In<String> inClause = criteriaBuilder.in(tagsJoin.get("name"));
            for (Tag tag : criteria.getTags()) {
                inClause.value(tag.getName());
            }
            conditions.add(inClause);
            Predicate count = criteriaBuilder.equal(criteriaBuilder.count(root), criteria.getTags().size());
            query.groupBy(root, root.get("author")).having(count);
        }
        return query.select(root).where(conditions.toArray(new Predicate[]{}));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsBySearchCriteriaSpecification that = (NewsBySearchCriteriaSpecification) o;
        return criteria != null ? criteria.equals(that.criteria) : that.criteria == null;
    }

    @Override
    public int hashCode() {
        return criteria != null ? criteria.hashCode() : 0;
    }
}
