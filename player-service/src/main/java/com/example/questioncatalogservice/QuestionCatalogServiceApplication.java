package com.example.questioncatalogservice;

import com.example.questioncatalogservice.entity.Question;
import com.example.questioncatalogservice.repository.QuestionRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class QuestionCatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionCatalogServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner loadQuestions(QuestionRepository repo) {
        return args -> {
            repo.save(Question.builder()
                    .texte("Quelle est la JVM ?")
                    .reponseCorrecte("Machine virtuelle Java")
                    .propositions(List.of("Navigateur", "OS", "CPU"))
                    .categorie("Java")
                    .build());

            repo.save(Question.builder()
                    .texte("REST utilise quel protocole ?")
                    .reponseCorrecte("HTTP")
                    .propositions(List.of("FTP", "SMTP"))
                    .categorie("Architecture")
                    .build());

            repo.save(Question.builder()
                    .texte("Spring est un ?")
                    .reponseCorrecte("Framework")
                    .propositions(List.of("Langage", "IDE"))
                    .categorie("Java")
                    .build());

            System.out.println("-> questions chargées : " + repo.count());
        };
    }
}
