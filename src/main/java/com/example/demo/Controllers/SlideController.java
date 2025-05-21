package com.example.demo.Controllers;

import com.example.demo.Dtos.SlidesDto.SlideDto;
import com.example.demo.Dtos.SlidesDto.SlideRequestDto;
import com.example.demo.Models.Slide;
import com.example.demo.Response.SuccessResponse;
import com.example.demo.Services.SlideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="/slides")
@Tag(name = "Slide")
public class SlideController {

    @Autowired
    private SlideService slideService;


    @GetMapping
    @Operation(description = "Show all slides")
    public ResponseEntity<SuccessResponse<List<SlideDto>>> index(){

        List<SlideDto> slideDtos=slideService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Slide found successfully",slideDtos));

    }


    @GetMapping(value="/{id}")
    @Operation(description = "show an sigle slide")
    public ResponseEntity<SuccessResponse<SlideDto>> show(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Slide found successfully",slideService.findSlide(id)));
    }

    @PostMapping
    @Operation(description = "create a new slide")
    public ResponseEntity<SuccessResponse<SlideDto>> store(@ModelAttribute SlideRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Slide found successfully",slideService.saveSlide(dto)));

    }

    @PutMapping(value="/{id}")
    @Operation(description = "update a slide")
    public ResponseEntity<SuccessResponse<SlideDto>>updade(@PathVariable UUID id, @ModelAttribute SlideRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                new SuccessResponse<>("Slide updated successfully",slideService.updateSlide(requestDto,id)));
    }

    @DeleteMapping(value="/{id}")
    @Operation(description = "delete a slide")
    public ResponseEntity<Void> destroy(@PathVariable UUID id){
        slideService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
