package com.alibou.security.news;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController {
    private NewsService newsService;

    @PostMapping("")
    public News createNews(@RequestBody News news) {
        return newsService.createNews(news);
    }

    @PatchMapping ("/{newsId}")
    public News updateNews(@PathVariable int newsId, @RequestBody News news) {
        return newsService.updateNews(newsId, news);
    }

    @DeleteMapping("/{newsId}")
    public void deleteNews(@PathVariable int newsId) {
        newsService.deleteNews(newsId);
    }

    @GetMapping("/{newsId}")
    public News getNewsById(@PathVariable int newsId) {
        return newsService.getNewsById(newsId);
    }

    @GetMapping
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }
    @GetMapping("/{newsType}")
    public ResponseEntity<List<News>> getByType(@PathVariable String newsType ){
        return newsService.getByType(newsType);
    }

}
