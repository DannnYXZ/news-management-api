package com.epam.lab.service.impl;

import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.SortCriteria;
import com.epam.lab.model.Tag;

import java.util.*;

class SortCriteriaComparator {

    private static Map<SortCriteria, Comparator<News>> comparators = new HashMap<>();

    static {
        comparators.put(SortCriteria.AUTHOR, (a, b) -> {
            Author author1 = a.getAuthor();
            Author author2 = b.getAuthor();
            return author1 == null ? 1 : author1.compareTo(author2);
        });
        comparators.put(SortCriteria.TAG, (a, b) -> {
            List<Tag> tags1 = a.getTags();
            List<Tag> tags2 = b.getTags();
            return tags1 == null ? 1 : tags2 == null ? -1 : tags2.size() - tags1.size();
        });
        comparators.put(SortCriteria.DATE, (a, b) -> {
            Date date1 = a.getCreationDate();
            Date date2 = b.getCreationDate();
            return date1 == null ? 1 : date1.compareTo(date2);
        });
        comparators.put(null, (a, b) -> 0);
    }

    static Comparator<News> getComparator(SortCriteria criteria) {
        return comparators.get(criteria);
    }
}
