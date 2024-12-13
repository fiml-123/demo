package com.devops.demo.model;

import java.time.LocalDate;

public class Player {
    private String name;
    private int cookies;
    private int level;
    private double cookieMultiplier;  // Multiplicateur de cookies
    private SkillTree skillTree;
    private LocalDate lastRewardDate;

    public Player(String name) {
        this.name = name;
        this.cookies = 0;  // Initialisation avec 0 cookies
        this.level = 1;    // Le joueur commence au niveau 1
        this.cookieMultiplier = 1.0;  // Le joueur commence avec un multiplicateur de cookies de 1
        this.skillTree = new SkillTree();
        this.lastRewardDate = LocalDate.MIN;
    }

    public String getName() {
        return name;
    }

    public int getCookies() {
        return cookies;
    }

    public void addCookies(int amount) {
        this.cookies += amount;
    }

    public void setLevel(int level) {
        if (level > this.level) {
            this.level = level - 1;  // Met à jour le niveau uniquement si le nouveau niveau est supérieur
        } else {
            System.out.println("Le niveau doit être supérieur.");
        }
    }

    public int getLevel() {
        return level;  // Retourne le niveau actuel du joueur
    }

    public double getCookieMultiplier() {
        return cookieMultiplier;  // Retourne le multiplicateur de cookies
    }

    public void setCookieMultiplier(double cookieMultiplier) {
        if (cookieMultiplier > 0) {
            this.cookieMultiplier = cookieMultiplier;  // Définit le multiplicateur de cookies
        } else {
            System.out.println("Le multiplicateur de cookies doit être supérieur à 0.");
        }
    }

    public void buySkill(int skillIndex) {
        if (skillIndex < 0 || skillIndex >= skillTree.getSkills().size()) {
            System.out.println("Compétence invalide.");
            return;
        }

        Skill skill = skillTree.getSkills().get(skillIndex);
        if (cookies >= skill.getCost() && !skill.isUnlocked()) {
            addCookies(-skill.getCost());  // Déduire les cookies du joueur
            skill.unlock();  // Débloquer la compétence
            System.out.println("Compétence " + skill.getName() + " débloquée !");
        } else if (skill.isUnlocked()) {
            System.out.println("Cette compétence est déjà débloquée.");
        } else {
            System.out.println("Pas assez de cookies pour débloquer cette compétence.");
        }
    }
    public boolean checkDailyReward() {
        LocalDate currentDate = LocalDate.now();  // Obtenir la date actuelle

        // Vérifier si la récompense a déjà été réclamée aujourd'hui
        if (lastRewardDate.isEqual(currentDate)) {
            System.out.println("Vous avez déjà réclamé votre récompense aujourd'hui !");
            return false;  // La récompense a déjà été réclamée aujourd'hui
        } else {
            // Récompenser le joueur avec des cookies
            int reward = 100;  // Nombre de cookies offerts en récompense
            this.cookies += reward;
            this.lastRewardDate = currentDate;  // Enregistrer la date de la dernière récompense
            System.out.println("Vous avez reçu " + reward + " cookies en récompense quotidienne !");
            return true;  // Récompense accordée avec succès
        }
    }

    public boolean claimDailyReward() {
        LocalDate currentDate = LocalDate.now();  // Obtenir la date actuelle

        // Vérifier si la récompense a déjà été réclamée aujourd'hui
        if (lastRewardDate.isEqual(currentDate)) {
            System.out.println("Vous avez déjà réclamé votre récompense aujourd'hui !");
            return false;  // La récompense a déjà été réclamée aujourd'hui
        } else {
            // Récompenser le joueur avec des cookies
            int reward = 100;  // Nombre de cookies offerts en récompense
            this.cookies += reward;
            this.lastRewardDate = currentDate;  // Enregistrer la date de la dernière récompense
            System.out.println("Vous avez reçu " + reward + " cookies en récompense quotidienne !");
            return true;  // Récompense accordée avec succès
        }
    }

    public SkillTree getSkillTree() {
        return skillTree;
    }
}
