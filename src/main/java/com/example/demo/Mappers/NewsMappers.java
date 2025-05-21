package com.example.demo.Mappers;

import com.example.demo.Dtos.NewsDto;
import com.example.demo.Dtos.NewsRequestDto;
import com.example.demo.Models.News;

import java.util.Optional;

public class NewsMappers {

    public static NewsDto modelToDto(News news){
        return new NewsDto(news.getId(),
                           news.getTitle(),
                           news.getDescription(),
                news.getStatus().toString(),
                news.getFile(),
                news.getCreatedAt());
    }

    public static void dtoToModel(News news, NewsRequestDto newsDto){

        //Optional.ofNullable(...) =>Cria um objeto Optional que pode conter null,Se o valor for null, o Optional fica vazio
        //Se não for null, ele envolve o valor

        //Executa a ação apenas se o valor estiver presente (ou seja, não for null).
        Optional.ofNullable(newsDto.title()).ifPresent(news::setTitle);
        Optional.ofNullable(newsDto.description()).ifPresent(news::setDescription);


    }
}
