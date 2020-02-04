package com.epam.lab.specification.impl;

import com.epam.lab.model.Author;
import com.epam.lab.model.SearchCriteria;
import com.epam.lab.model.SortCriteria;
import com.epam.lab.model.Tag;
import com.epam.lab.specification.EntitySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class NewsBySearchCriteriaSpecification implements EntitySpecification {

    private static final String SQL_SELECT_ALL_NEWS = "SELECT * FROM news ";
    private static final String SQL_INNER_JOIN_BY_AUTHOR = "INNER JOIN " +
            "news_author ON news.id = news_author.news_id INNER JOIN " +
            "author ON author.id = news_author.author_id AND author.name = {0} ";
    private static final String SQL_INNER_JOIN_BY_TAGS = "INNER JOIN " +
            "news_tag ON news.id = news_tag.news_id INNER JOIN " +
            "tag ON tag.id = news_tag.tag_id AND ";
    private static final String SQL_SORT_BY = "ORDER BY {0}";
    private SearchCriteria criteria;

    public NewsBySearchCriteriaSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public PreparedStatementCreator specified() {
        return connection -> {
            StringBuilder sql = new StringBuilder(SQL_SELECT_ALL_NEWS);
            Author author = criteria.getAuthor();
            if (author != null && author.getName() != null) {
                sql.append(MessageFormat.format(SQL_INNER_JOIN_BY_AUTHOR, "'" + author.getName() + "'"));
            }
            List<Tag> tags = criteria.getTags();
            if (tags != null) {
                String tagExpr = tags.stream()
                        .map(name -> "tag.name = '" + name.getName() + "'")
                        .collect(Collectors.joining(" OR "));
                sql.append(SQL_INNER_JOIN_BY_TAGS).append("(").append(tagExpr).append(") ");
            }
            SortCriteria sortCriteria = criteria.getSortCriteria();
            if (sortCriteria != null) {
                sql.append(MessageFormat.format(SQL_SORT_BY, sortCriteria.toString().toLowerCase()));
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            return preparedStatement;
        };
    }
}
