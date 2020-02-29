package com.epam.lab.controller;

import com.epam.lab.dto.AuthorDTO;
import com.epam.lab.dto.NewsDTO;
import com.epam.lab.dto.SearchCriteriaDTO;
import com.epam.lab.dto.TagDTO;
import com.epam.lab.service.NewsService;
import com.epam.lab.validation.NewEntity;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsController {

    private NewsService newsService;

    @Autowired
    NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/news")
    public NewsDTO createNews(@Validated(NewEntity.class) @RequestBody NewsDTO news) {
        return newsService.create(news);
    }

    @GetMapping(value = "/news")
    public List<NewsDTO> readNews(SearchCriteriaDTO searchCriteriaDTO) {
        return newsService.readNews(searchCriteriaDTO);
    }

    @GetMapping(value = "/news/{id}")
    public NewsDTO readNews(@PathVariable("id") Long id) {
        return newsService.read(new NewsDTO().setId(id));
    }

    @PutMapping(value = "/news/{id}")
    public void updateNews(@PathVariable("id") Long id, @Valid @RequestBody NewsDTO news) {
        newsService.update(news.setId(id));
    }

    @DeleteMapping(value = "/news/{id}")
    public void deleteNews(@PathVariable("id") Long id) {
        newsService.delete(new NewsDTO().setId(id));
    }

    @GetMapping(value = "/news/count")
    public long countNews() {
        return newsService.countNews();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/news/{newsId}/author/{authorId}")
    public void linkAuthor(@PathVariable("newsId") Long newsId, @PathVariable("authorId") Long authorId) {
        newsService.linkAuthor(new NewsDTO().setId(newsId), new AuthorDTO().setId(authorId));
    }

    @DeleteMapping(value = "/news/{newsId}/author/{authorId}")
    public void unlinkAuthor(@PathVariable("newsId") Long newsId, @PathVariable("authorId") Long authorId) {
        newsService.unlinkAuthor(new NewsDTO().setId(newsId), new AuthorDTO().setId(authorId));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/news/{id}/tags")
    public void linkTags(@PathVariable("id") Long newsId, @RequestParam List<Long> link) {
        List<TagDTO> tagsToLink = link.stream().map(x -> new TagDTO().setId(x)).collect(Collectors.toList());
        newsService.linkTags(new NewsDTO().setId(newsId), tagsToLink);
    }

    @DeleteMapping(value = "/news/{id}/tags")
    public void unlinkTags(@PathVariable("id") Long newsId, @RequestParam List<Long> unlink) {
        List<TagDTO> tagsToUnlink = unlink.stream().map(x -> new TagDTO().setId(x)).collect(Collectors.toList());
        newsService.unlinkTags(new NewsDTO().setId(newsId), tagsToUnlink);
    }
}
