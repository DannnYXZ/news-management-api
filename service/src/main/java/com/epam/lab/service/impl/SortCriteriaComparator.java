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
            Author auth1 = a.getAuthor(), auth2 = b.getAuthor();
            return auth1 == null ? 1 : auth1.compareTo(auth2);
        });
        comparators.put(SortCriteria.TAG, (a, b) -> {
            List<Tag> tags1 = a.getTags(), tags2 = b.getTags();
            return tags1 == null ? 1 : tags2 == null ? -1 : tags2.size() - tags1.size();
        });
        comparators.put(SortCriteria.DATE, (a, b) -> {
            Date date1 = a.getCreationDate(), date2 = b.getCreationDate();
            return date1 == null ? 1 : date1.compareTo(date2);
        });
        comparators.put(null, (a, b) -> 0);
    }

    static Comparator<News> getComparator(SortCriteria criteria) {
        return comparators.get(criteria);
    }
}
