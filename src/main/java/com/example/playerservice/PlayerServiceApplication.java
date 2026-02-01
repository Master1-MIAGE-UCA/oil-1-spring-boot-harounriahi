package com.example.playerservice;

import com.example.playerservice.entity.Player;
import com.example.playerservice.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlayerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner loadData(PlayerRepository repository) {
        return args -> {
            repository.save(Player.builder().pseudo("Neo").score(500).build());
            repository.save(Player.builder().pseudo("Trinity").score(750).build());
            repository.save(Player.builder().pseudo("Morpheus").score(1000).build());

            System.out.println("-> joueurs chargés : " + repository.count());
        };
    }
}
