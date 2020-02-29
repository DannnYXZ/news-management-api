package com.epam.lab.service.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.lab.configuration.ServiceTestConfiguration;
import com.epam.lab.dto.TagDTO;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.service.TagService;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceTestConfiguration.class})
public class TagServiceImplTest {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagService tagService;

    @Before
    public void init() {
        reset(tagRepository);
    }

    @Test
    public void testCreateTag() {
        long expectedId = 777;
        when(tagRepository.create(any())).thenReturn(new Tag().setId(expectedId));
        TagDTO inTag = new TagDTO().setName("Salami");
        TagDTO actualTag = tagService.create(inTag);
        Assert.assertEquals(actualTag.getId(), expectedId);
    }

    @Test
    public void testReadTag() {
        long id = 8976;
        when(tagRepository.query(Mockito.any())).thenReturn(Arrays.asList(new Tag().setId(id).setName("Hello Tag")));
        TagDTO expectedTag = new TagDTO().setId(id).setName("Hello Tag");
        TagDTO inTag = new TagDTO().setId(id);
        TagDTO actualTag = tagService.read(inTag);
        Assert.assertEquals(actualTag, expectedTag);
    }

    @Test
    public void testUpdateTag() {
        tagService.update(new TagDTO().setId(101));
        verify(tagRepository, times(1)).update(new Tag().setId(101));
    }

    @Test
    public void testDeleteTag() {
        tagService.delete(new TagDTO().setId(102));
        verify(tagRepository, times(1)).delete(new Tag().setId(102));
    }
}
