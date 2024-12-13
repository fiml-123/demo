package com.devops.demo.service;

import com.devops.demo.model.Player;
import com.devops.demo.model.Skill;

import java.util.Random;

public class GameService {
    private Random random;

    public GameService() {
        this.random = new Random();
    }

    // Méthode pour ajouter des cookies en fonction du multiplicateur du joueur
    public void addCookies(Player player, int amount) {
        int totalCookies = (int) (amount * player.getCookieMultiplier());  // Utilise le multiplicateur de cookies
        player.addCookies(totalCookies);  // Ajoute les cookies au joueur
    }

    // Méthode pour augmenter le niveau du joueur
    public void levelUp(Player player) {
        int requiredCookiesForLevelUp = player.getLevel() * 1000; // Exemple de calcul du coût de montée de niveau
        if (player.getCookies() >= requiredCookiesForLevelUp) {
            player.addCookies(-requiredCookiesForLevelUp);  // Déduit les cookies pour la montée de niveau
            player.setLevel(player.getLevel() + 2);  // Augmente le niveau
            System.out.println("Niveau augmenté à : " + player.getLevel());
        } else {
            System.out.println("Pas assez de cookies pour monter de niveau !");
        }
    }

    // Vérifie si le joueur peut réclamer une récompense quotidienne et la lui accorde
    public void claimDailyReward(Player player) {
        if (player.checkDailyReward()) {
            player.addCookies(100);  // Exemple : 100 cookies gratuits
            System.out.println("Récompense quotidienne reçue : 100 cookies !");
        } else {
            System.out.println("Vous avez déjà récupéré votre récompense aujourd'hui.");
        }
    }

    // Méthode pour lancer un événement aléatoire
    public void startRandomEvent(Player player) {
        int eventType = random.nextInt(3);  // Choisit un événement aléatoire parmi 3 types

        switch (eventType) {
            case 0:
                activateDoubleCookiesEvent(player);  // Exemple d'événement "double cookies"
                break;
            case 1:
                activateBonusCookiesEvent(player);  // Un autre type d'événement (à définir)
                break;
            case 2:
                activateMultiplierEvent(player);  // Un événement de multiplicateur temporaire
                break;
        }
    }

    // Active l'événement "double cookies" pendant 30 secondes
    private void activateDoubleCookiesEvent(Player player) {
        System.out.println("Événement activé : Double cookies pendant 30 secondes !");
        new Thread(() -> {
            try {
                int originalCookies = player.getCookies();
                Thread.sleep(30000);  // Attendre 30 secondes
                player.addCookies(originalCookies);  // Double les cookies gagnés pendant cette période
                System.out.println("Événement terminé : les cookies doublés sont terminés.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Exemple d'un autre événement qui donne un bonus de cookies
    private void activateBonusCookiesEvent(Player player) {
        int bonusCookies = 500;
        player.addCookies(bonusCookies);  // Donne un bonus de 500 cookies
        System.out.println("Événement : " + bonusCookies + " cookies supplémentaires ajoutés !");
    }

    // Exemple d'événement qui augmente temporairement le multiplicateur de cookies
    private void activateMultiplierEvent(Player player) {
        double originalMultiplier = player.getCookieMultiplier();
        player.setCookieMultiplier(originalMultiplier * 2);  // Double le multiplicateur pendant 30 secondes
        System.out.println("Événement : Multiplicateur de cookies doublé !");
        new Thread(() -> {
            try {
                Thread.sleep(30000);  // Attendre 30 secondes
                player.setCookieMultiplier(originalMultiplier);  // Restaure le multiplicateur original
                System.out.println("Événement terminé : le multiplicateur est revenu à la normale.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
