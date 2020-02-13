package com.epam.lab.specification;

import org.springframework.jdbc.core.PreparedStatementCreator;

public interface EntitySpecification {

    PreparedStatementCreator specified();
}
