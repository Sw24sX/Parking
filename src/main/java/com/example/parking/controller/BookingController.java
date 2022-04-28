package com.example.parking.controller;

import com.example.parking.dto.BookingDto;
import com.example.parking.service.CommonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("booking")
public class BookingController {
    private final CommonService commonService;

    public BookingController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping
    public List<BookingDto> getAll() {
        return commonService.getAll();
    }

    @PostMapping
    public BookingDto create(@RequestBody BookingDto dto) {
        return commonService.create(dto);
    }

    @PutMapping("{id}")
    public BookingDto update(@PathVariable(name = "id") Long id, @RequestBody BookingDto dto) {
        return commonService.update(dto, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        commonService.delete(id);
    }
}
