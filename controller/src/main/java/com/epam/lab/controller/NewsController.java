package com.epam.lab.controller;

import com.epam.lab.dto.AuthorDTO;
import com.epam.lab.dto.NewsDTO;
import com.epam.lab.dto.SearchCriteriaDTO;
import com.epam.lab.dto.TagDTO;
import com.epam.lab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsController {

    @Autowired
    NewsService newsService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/news")
    public NewsDTO createNews(@RequestBody NewsDTO news) {
        NewsDTO identifiedNews = newsService.create(news);
        return identifiedNews;
    }

    @GetMapping(value = "/news")
    public List<NewsDTO> readNews(SearchCriteriaDTO searchCriteriaDTO) {
        List<NewsDTO> news = newsService.readNews(searchCriteriaDTO);
        return news;
    }

    @GetMapping(value = "/news/{id}")
    public NewsDTO readNews(@PathVariable("id") Long id) {
        NewsDTO news = newsService.read(new NewsDTO().setId(id));
        return news;
    }

    @PutMapping(value = "/news/{id}")
    public void updateNews(@PathVariable("id") Long id, @RequestBody NewsDTO news) {
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
