package com.epam.lab.service.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.lab.configuration.ServiceTestConfiguration;
import com.epam.lab.dto.AuthorDTO;
import com.epam.lab.dto.NewsDTO;
import com.epam.lab.dto.SearchCriteriaDTO;
import com.epam.lab.dto.SortCriteriaDTO;
import com.epam.lab.dto.TagDTO;
import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.exception.TagsLinkageException;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.SearchCriteria;
import com.epam.lab.model.SortCriteria;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.service.NewsService;
import com.epam.lab.specification.impl.NewsByIdSpecification;
import com.epam.lab.specification.impl.NewsBySearchCriteriaSpecification;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {ServiceTestConfiguration.class})
public class NewsServiceImplTest {

    @ClassRule
    public static final SpringClassRule scr = new SpringClassRule();
    @Rule
    public final SpringMethodRule smr = new SpringMethodRule();
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    NewsService newsService;

    @Before
    public void setup() {
        reset(newsRepository);
    }

    @Test
    public void testCreateNews() {
        long expectedId = 333;
        String title = "Not Identified";
        when(newsRepository.create(any(News.class)))
            .thenReturn(new News().setId(expectedId));
        NewsDTO inDTO = new NewsDTO().setTitle(title);
        NewsDTO outDTO = newsService.create(inDTO);
        Assert.assertEquals(outDTO.getId(), expectedId);
    }

    @Test
    public void testReadAllNewsAndSortByAuthor() {
        SearchCriteria repositoryCriteria = new SearchCriteria().setSort(SortCriteria.AUTHOR);
        when(newsRepository.query(new NewsBySearchCriteriaSpecification(repositoryCriteria)))
            .thenReturn(Arrays.asList(
                new News().setAuthor(new Author().setName("D")),
                new News().setAuthor(new Author().setName("C")),
                new News().setAuthor(new Author().setName("B")),
                new News().setAuthor(new Author().setName("A"))
            ));

        SearchCriteriaDTO inCriteria = new SearchCriteriaDTO().setSort(SortCriteriaDTO.AUTHOR);
        List<NewsDTO> actualNews = newsService.readNews(inCriteria);
        List<NewsDTO> expectedNews = Arrays.asList(
            new NewsDTO().setAuthor(new AuthorDTO().setName("A")),
            new NewsDTO().setAuthor(new AuthorDTO().setName("B")),
            new NewsDTO().setAuthor(new AuthorDTO().setName("C")),
            new NewsDTO().setAuthor(new AuthorDTO().setName("D"))
        );
        Assert.assertEquals(actualNews, expectedNews);
    }

    @Test
    public void testReadNewsById() {
        when(newsRepository.query(new NewsByIdSpecification(35))).thenReturn(
            Arrays.asList(new News()
                .setId(35)
                .setAuthor(new Author().setName("Bazinga"))
                .setTitle("Ukulele"))
        );
        NewsDTO expectedNews = new NewsDTO()
            .setId(35)
            .setAuthor(new AuthorDTO().setName("Bazinga"))
            .setTitle("Ukulele");
        NewsDTO actualNews = newsService.read(new NewsDTO().setId(35));
        Assert.assertEquals(actualNews, expectedNews);
    }

    @Test
    public void testDeleteNews() {
        long targetId = 33;
        String targetTitle = "News to be deleted.";
        NewsDTO inDTO = new NewsDTO().setId(targetId).setTitle(targetTitle);
        newsService.delete(inDTO);
        verify(newsRepository, times(1))
            .delete(new News().setId(targetId).setTitle(targetTitle));
    }

    @Test
    public void testCountNews() {
        long expectedCount = 909L;
        when(newsRepository.count()).thenReturn(expectedCount);
        Assert.assertEquals(newsService.countNews(), expectedCount);
        verify(newsRepository).count();
    }


    @Captor
    ArgumentCaptor<News> newsCaptor;
    @Captor
    ArgumentCaptor<Tag> tagsCaptor;
    @Captor
    ArgumentCaptor<Author> authorCaptor;

    @Test
    public void testLinkTags() {
        NewsDTO inNewsDTO = new NewsDTO().setId(777);
        List<TagDTO> tagsDTO = Arrays.asList(
            new TagDTO().setId(121),
            new TagDTO().setId(232),
            new TagDTO().setId(343),
            new TagDTO().setId(454)
        );
        newsService.linkTags(inNewsDTO, tagsDTO);
        verify(newsRepository, times(4)).linkTag(newsCaptor.capture(), tagsCaptor.capture());
    }

    @Test
    public void testUnlinkTags() {
        NewsDTO inNewsDTO = new NewsDTO().setId(777);
        List<TagDTO> tagsDTO = Arrays.asList(
            new TagDTO().setId(121),
            new TagDTO().setId(555),
            new TagDTO().setId(343),
            new TagDTO().setId(454)
        );
        newsService.unlinkTags(inNewsDTO, tagsDTO);
        verify(newsRepository, times(4))
            .unlinkTag(newsCaptor.capture(), tagsCaptor.capture());
    }

    @Test(expected = TagsLinkageException.class)
    public void testUnlinkTagsException() {
        NewsDTO inNewsDTO = new NewsDTO().setId(777);
        List<TagDTO> inTagsDTO = Arrays.asList(
            new TagDTO().setId(121),
            new TagDTO().setId(232),
            new TagDTO().setId(343),
            new TagDTO().setId(454)
        );
        doThrow(EntityNotFoundException.class)
            .when(newsRepository)
            .unlinkTag(new News().setId(777), new Tag().setId(232));
        newsService.unlinkTags(inNewsDTO, inTagsDTO);
    }

    @Test
    public void testLinkAuthor() {
        NewsDTO inNewsDTO = new NewsDTO().setId(7788);
        AuthorDTO inAuthorDTO = new AuthorDTO().setId(33);
        newsService.linkAuthor(inNewsDTO, inAuthorDTO);
        verify(newsRepository, times(1))
            .linkAuthor(newsCaptor.capture(), authorCaptor.capture());
    }

    @Test
    public void testUnlinkAuthor() {
        NewsDTO inNewsDTO = new NewsDTO().setId(7788);
        AuthorDTO inAuthorDTO = new AuthorDTO().setId(33);
        newsService.unlinkAuthor(inNewsDTO, inAuthorDTO);
        verify(newsRepository, times(1))
            .unlinkAuthor(newsCaptor.capture(), authorCaptor.capture());
    }
}
