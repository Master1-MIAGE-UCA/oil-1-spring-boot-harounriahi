package com.example.playerservice;

import com.example.playerservice.Entity.Player;
import com.example.playerservice.Repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlayerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerServiceApplication.class, args);
    }

    // Chargement des données initiales via CommandLineRunner
    @Bean
    public CommandLineRunner loadData(PlayerRepository repository) {
        return (args) -> {
            System.out.println("Début du chargement des joueurs de test...");

            // Chargement des données de test via Player.builder()
            repository.save(Player.builder().pseudo("Neo").score(500).build());
            repository.save(Player.builder().pseudo("Trinity").score(750).build());
            repository.save(Player.builder().pseudo("Morpheus").score(1000).build());

            System.out.println("-> " + repository.count() + " joueurs de test chargés.");
        };
    }
}