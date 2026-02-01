package com.example.questioncatalogservice.controller;

import com.example.questioncatalogservice.entity.Question;
import com.example.questioncatalogservice.service.QuestionService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Question> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Question getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question create(@RequestBody Question question) {
        return service.create(question);
    }

    @PutMapping("/{id}")
    public Question update(
            @PathVariable Long id,
            @RequestBody Question question) {
        return service.update(id, question);
    }

    @PatchMapping("/{id}")
    public Question patch(
            @PathVariable Long id,
            @RequestParam String categorie) {
        return service.patch(id, categorie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
