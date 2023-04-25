package com.alibou.security.news;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {
    private NewsRepository newsRepository;

    public News createNews(News news) {
        news.setCreated_at(LocalDateTime.now());
        news.setUpdated_at(LocalDateTime.now());
        return newsRepository.save(news);
    }

    public News updateNews(int newsId, News news) {
        News existingNews = getNewsById(newsId);
        existingNews.setTitle(news.getTitle());
        existingNews.setContent(news.getContent());
        existingNews.setNewsphoto(news.getNewsphoto());
        existingNews.setUpdated_at(LocalDateTime.now());
        return newsRepository.save(existingNews);
    }

    public void deleteNews(int newsId) {
        News existingNews = getNewsById(newsId);
        newsRepository.delete(existingNews);
    }

    public News getNewsById(int newsId) {
        return newsRepository.findById(newsId).get();

    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
    public ResponseEntity<List<News>> getByType(String newsType){
        return new ResponseEntity<>(newsRepository.findByNewsType(newsType),HttpStatusCode.valueOf(200));
    }


}
