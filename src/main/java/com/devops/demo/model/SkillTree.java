package com.devops.demo.model;

import java.util.ArrayList;
import java.util.List;

public class SkillTree {
    private List<Skill> skills;

    public SkillTree() {
        this.skills = new ArrayList<>();
        initializeSkills();
    }

    private void initializeSkills() {
        // Initialisation des compétences disponibles dans l'arbre
        skills.add(new Skill("Multiplicateur de cookies x2", "Doublez la quantité de cookies obtenus par clic.", 50));
        skills.add(new Skill("Auto-click", "Clic automatique toutes les 5 secondes.", 100));
        skills.add(new Skill("Bonus passif", "Obtenez +5 cookies par seconde sans cliquer.", 200));
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void unlockSkill(int skillIndex, int currentCookies) {
        Skill skill = skills.get(skillIndex);
        if (currentCookies >= skill.getCost()) {
            skill.unlock();
        }
    }

    public void displaySkills() {
        for (Skill skill : skills) {
            System.out.println(skill);
        }
    }
}
