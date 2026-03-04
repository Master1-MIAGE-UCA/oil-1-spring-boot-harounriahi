package com.example.questioncatalogservice;

import com.example.questioncatalogservice.entity.Question;
import com.example.questioncatalogservice.service.QuestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class QuestionCatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionCatalogServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(QuestionService questionService) {
        return args -> {
            questionService.saveQuestion(Question.builder()
                    .texte("Quelle est la capitale de la France ?")
                    .reponseCorrecte("Paris")
                    .propositions(Arrays.asList("Marseille", "Lyon", "Paris"))
                    .categorie("Géographie")
                    .build());
            questionService.saveQuestion(Question.builder()
                    .texte("Quel est le langage de programmation principal pour Android ?")
                    .reponseCorrecte("Kotlin")
                    .propositions(Arrays.asList("Java", "Kotlin", "Swift"))
                    .categorie("Programmation")
                    .build());
            questionService.saveQuestion(Question.builder()
                    .texte("Combien de planètes dans notre système solaire ?")
                    .reponseCorrecte("8")
                    .propositions(Arrays.asList("7", "8", "9"))
                    .categorie("Astronomie")
                    .build());
        };
    }
}
