package com.devops.demo.model;

public class Skill {
    private String name;
    private String description;
    private int cost;  // Coût en cookies pour acheter cette compétence
    private boolean unlocked;  // Indique si la compétence est déverrouillée ou non

    public Skill(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.unlocked = false;  // Compétence verrouillée par défaut
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void unlock() {
        if (!unlocked && cost <= 0) {  // On débloque la compétence si on a suffisamment de cookies
            this.unlocked = true;
        }
    }

    @Override
    public String toString() {
        return "Skill{name='" + name + "', cost=" + cost + ", unlocked=" + unlocked + "}";
    }
}
