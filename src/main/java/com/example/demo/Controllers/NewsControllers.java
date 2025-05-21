package com.example.demo.Controllers;

import com.example.demo.Dtos.NewsDto;
import com.example.demo.Dtos.NewsRequestDto;
import com.example.demo.Models.News;
import com.example.demo.Services.NewsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="/news")
@Tag(name="News")
public class NewsControllers {
    @Autowired
    private NewsService service;

    @GetMapping
    public ResponseEntity<List<NewsDto>> index(){

        List<NewsDto>newsDtos=service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(newsDtos);
    }


    @PostMapping
    public ResponseEntity<NewsDto> store(@ModelAttribute NewsRequestDto newsDto){

        NewsDto news=service.saveNews(newsDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(news);
    }

    public ResponseEntity<NewsDto> update(@PathVariable UUID id,@ModelAttribute NewsRequestDto newsDto){
        NewsDto news=service.updateNews(id,newsDto);

        return ResponseEntity.status(HttpStatus.OK).body(news);


    }

    @GetMapping(value="/{id}")
    public ResponseEntity<NewsDto> show(@PathVariable UUID id){
        NewsDto news=service.getOne(id);

        return ResponseEntity.status(HttpStatus.OK).body(news);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> destroy(@PathVariable UUID id){
       service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
