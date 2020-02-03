package com.epam.lab.service.impl;

import com.epam.lab.dto.Tag;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.service.TagService;
import com.epam.lab.specification.impl.TagsByIdSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagServiceImpl implements TagService {
    @Autowired
    private EntityRepository<Tag> tagRepository;

    @Override
    public Tag create(Tag element) {
        Tag identifiedTag = tagRepository.create(element);
        return identifiedTag;
    }

    @Override
    public Tag read(Tag element) {
        List<Tag> tags = tagRepository.query(new TagsByIdSpecification(element.getId()));
        return tags.get(0);
    }

    @Override
    public void update(Tag element) {
        tagRepository.update(element);
    }

    @Override
    public void delete(Tag element) {
        tagRepository.delete(element);
    }
}
