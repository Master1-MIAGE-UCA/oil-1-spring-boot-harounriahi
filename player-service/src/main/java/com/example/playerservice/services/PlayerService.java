package com.example.playerservice.services;

import com.example.playerservice.entity.Player;
import com.example.playerservice.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public Player findPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur avec l'ID " + id + " non trouvé."));
    }

    public Player savePlayer(Player player) {
        if (player.getPseudo() == null || player.getPseudo().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le pseudo est manquant ou vide.");
        }
        if (player.getId() == null) {
            player.setScore(0);
        }
        return playerRepository.save(player);
    }
    public Player addScore(Long id, int scoreToAdd) {
        Player p = findPlayerById(id);
        p.setScore(p.getScore() + scoreToAdd);
        return savePlayer(p);
    }

    public Player updatePlayer(Long id, Player playerDetails) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur avec l'ID " + id + " non trouvé pour la mise à jour complète."));

        if (playerDetails.getPseudo() == null || playerDetails.getPseudo().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le pseudo est manquant pour la mise à jour complète.");
        }
        
        if (playerDetails.getScore() == null) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le score est manquant pour la mise à jour complète.");
        }

        player.setPseudo(playerDetails.getPseudo());
        player.setScore(playerDetails.getScore());
        return playerRepository.save(player);
    }

    public Player partialUpdatePlayer(Long id, Player playerDetails) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur avec l'ID " + id + " non trouvé pour la mise à jour partielle."));

        if (playerDetails.getPseudo() != null) {
            if (playerDetails.getPseudo().trim().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le pseudo ne peut pas être vide.");
            }
            player.setPseudo(playerDetails.getPseudo());
        }
        if (playerDetails.getScore() != null) {
            player.setScore(playerDetails.getScore());
        }
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur avec l'ID " + id + " non trouvé pour la suppression."));
        playerRepository.delete(player);
    }
}
