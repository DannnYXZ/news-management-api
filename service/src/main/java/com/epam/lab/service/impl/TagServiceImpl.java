package com.epam.lab.service.impl;

import com.epam.lab.dto.TagDTO;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.service.TagService;
import com.epam.lab.specification.impl.TagsByIdSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagServiceImpl implements TagService {
    @Autowired
    private EntityRepository<Tag> tagRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public TagDTO create(TagDTO element) {
        Tag identifiedTag = tagRepository.create(modelMapper.map(element, Tag.class));
        return modelMapper.map(identifiedTag, TagDTO.class);
    }

    @Override
    public TagDTO read(TagDTO element) {
        List<Tag> tags = tagRepository.query(new TagsByIdSpecification(element.getId()));
        return modelMapper.map(tags.get(0), TagDTO.class);
    }

    @Override
    public void update(TagDTO element) {
        tagRepository.update(modelMapper.map(element, Tag.class));
    }

    @Override
    public void delete(TagDTO element) {
        tagRepository.delete(modelMapper.map(element, Tag.class));
    }
}
