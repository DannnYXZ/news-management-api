package com.epam.lab.specification.impl;

import com.epam.lab.model.Author;
import com.epam.lab.model.SearchCriteria;
import com.epam.lab.model.Tag;
import com.epam.lab.specification.EntitySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class NewsBySearchCriteriaSpecification implements EntitySpecification {

    private static final String SQL_SELECT_ALL_NEWS =
            "SELECT n.id, n.title, n.short_text, n.full_text, n.creation_date, n.modification_date FROM news as n ";
    private static final String SQL_INNER_JOIN_BY_AUTHOR =
            "INNER JOIN news_author ON n.id = news_author.news_id " +
            "INNER JOIN author ON author.id = news_author.author_id AND author.name = {0} ";
    private static final String SQL_INNER_JOIN_BY_TAGS =
            "INNER JOIN news_tag nt ON n.id = nt.news_id " +
            "INNER JOIN tag tg ON tg.id = nt.tag_id " +
            "WHERE tg.name IN ({0}) " +
            "group by n.id having count(*)={1} ";
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
                        .map(name -> "'" + name.getName() + "'")
                        .collect(Collectors.joining(", "));
                sql.append(MessageFormat.format(SQL_INNER_JOIN_BY_TAGS, tagExpr, tags.size()));
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            return preparedStatement;
        };
    }
}
