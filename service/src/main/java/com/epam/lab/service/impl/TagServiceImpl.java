package com.epam.lab.service.impl;

import com.epam.lab.dto.TagDTO;
import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.service.TagService;
import com.epam.lab.specification.impl.TagsByIdSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private EntityRepository<Tag> tagRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TagServiceImpl(EntityRepository<Tag> tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TagDTO create(TagDTO element) {
        Tag identifiedTag = tagRepository.create(modelMapper.map(element, Tag.class));
        return modelMapper.map(identifiedTag, TagDTO.class);
    }

    @Override
    public TagDTO read(TagDTO element) {
        List<Tag> tags = tagRepository.query(new TagsByIdSpecification(element.getId()));
        if (!tags.isEmpty()) {
            return modelMapper.map(tags.get(0), TagDTO.class);
        } else {
            throw new EntityNotFoundException("No such tag.");
        }
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
