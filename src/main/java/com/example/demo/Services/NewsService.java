package com.example.demo.Services;

import com.example.demo.Dtos.NewsDto;
import com.example.demo.Dtos.NewsRequestDto;
import com.example.demo.Enums.StatusEnum;
import com.example.demo.Exceptions.EntitiesNotFoundException;
import com.example.demo.Helpers.FileHelper;
import com.example.demo.Helpers.StatusHelper;
import com.example.demo.Mappers.NewsMappers;
import com.example.demo.Models.News;
import com.example.demo.Repositories.NewsRepository;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NewsService {
    @Autowired
    private NewsRepository repository;


    @Transactional
    public NewsDto saveNews(NewsRequestDto newsDto){
        News news = new News();
        news.setFile(FileHelper.fileupload(newsDto.file()));
        StatusEnum status= StatusHelper.parseStatus(newsDto.status().toString());
        news.setStatus(status != null ? newsDto.status():StatusEnum.ACTIVE);
        NewsMappers.dtoToModel(news,newsDto);
        news=repository.save(news);

        return NewsMappers.modelToDto(news);

    }

    @Transactional(readOnly = true)
    public List<NewsDto> getAll() {
        List<News> news=repository.findAll();
        return news.stream().map(NewsMappers::modelToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NewsDto getOne(UUID id) {

        News news=repository.findById(id).orElseThrow(()-> new EntitiesNotFoundException("News id doesn't exist"));

        return NewsMappers.modelToDto(news);
    }

    public void delete(UUID id) {

        News news=repository.findById(id).orElseThrow(()-> new EntitiesNotFoundException("News id doesn't exist"));
        repository.delete(news);
    }

    @Transactional
    public NewsDto updateNews(UUID id, NewsRequestDto newsDto) {
        News findNews=repository.findById(id).orElseThrow(()->new EntitiesNotFoundException("News id doesn't exist"));

        NewsMappers.dtoToModel(findNews,newsDto);

        if(newsDto.file() != null) {
            findNews.setFile(FileHelper.fileupload(newsDto.file()));
        }
        if(newsDto.status() != null) {
            findNews.setStatus(newsDto.status());
        }

        findNews=repository.save(findNews);
        return NewsMappers.modelToDto(findNews);

    }
}
