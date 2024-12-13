package com.devops.demo;

import com.almasb.fxgl.ui.ProgressBar;
import com.devops.demo.model.Player;
import com.devops.demo.service.GameService;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.cell.ProgressBarTreeTableCell;
import javafx.scene.control.skin.ProgressBarSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MainController {

    private GameService gameService;
    private Player player;
    public ImageView cookieImage;

    @FXML
    private Label scoreLabel;  // Affichage du score du joueur
    @FXML
    private Label levelLabel;  // Affichage du niveau du joueur
    @FXML
    private Label multiplierLabel;  // Affichage du multiplicateur
    @FXML
    private Label cookiesLabel;
    @FXML
    private Label skillLabel;

    @FXML
    private Button cookieButton;  // Bouton pour ajouter des cookies
    @FXML
    private Button levelUpButton; // Bouton pour augmenter le niveau
    @FXML
    private Button buySkillButton; // Bouton pour acheter une compétence

    @FXML
    private void initialize() {

        // Remplacez par le chemin de votre image
        cookieImage = new ImageView("file:/Z:/Bureau/Projet Java/demo/target/classes/com/devops/demo/images/cookie.jpg");  // Crée un ImageView avec l'image

        // Initialisation du joueur et du service de jeu
        this.gameService = new GameService();
        this.player = new Player("Player1");

        // Vérifiez si les labels et boutons sont bien initialisés
        if (scoreLabel == null || levelLabel == null || multiplierLabel == null || cookiesLabel == null || skillLabel == null ||
                cookieButton == null || levelUpButton == null || buySkillButton == null) {
            System.out.println("Erreur: Les éléments ne sont pas correctement initialisés dans le FXML.");
            return;
        }

        updateUI();
    }

    @FXML
    private void handleClick() {
        System.out.println("Cookie clicked!");
        // Animation de clic : ajoutez une transition de "scaling"
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), cookieImage);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();

        gameService.addCookies(player, 10);  // Ajoute des cookies au joueur avec multiplicateur
        updateUI();
    }

    @FXML
    private void handleLevelUp() {
        System.out.println("Level up!");
        gameService.levelUp(player);  // Gère la montée de niveau du joueur
        updateUI();

    }

    // Méthode qui affiche un popup de récompense
    private void showPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Récompense");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleBuySkill() {
        player.buySkill(0);  // Exemple d'achat de la première compétence
        cookiesLabel.setText("Cookies: " + player.getCookies());
        skillLabel.setText("Compétence disponible: " + player.getSkillTree().getSkills().get(0).getName());
    }

    @FXML
    private void handleClaimDailyReward() {
        player.claimDailyReward();  // Le joueur réclame sa récompense quotidienne
        updateUI();  // Met à jour l'interface utilisateur après avoir réclamé la récompense
    }


    private void updateUI() {
        // Mise à jour de l'interface avec un visuel attractif
        scoreLabel.setText("Cookies: " + player.getCookies());
        levelLabel.setText("Level: " + player.getLevel());
        multiplierLabel.setText("Multiplier: " + player.getCookieMultiplier());

    }

    public MainController() {
        this.player = new Player("Player1");
    }

}
