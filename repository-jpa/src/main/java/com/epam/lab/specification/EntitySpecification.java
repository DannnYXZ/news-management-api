package com.epam.lab.specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public interface EntitySpecification<T> {

    CriteriaQuery<T> specified(EntityManager manager);
}
