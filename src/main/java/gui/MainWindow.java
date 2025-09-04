package gui;

import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pepero.Pepero;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Pepero pepero;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image peperoImage = new Image(this.getClass().getResourceAsStream("/images/Pepero.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setPepero(Pepero p) {
        pepero = p;
        printWelcomeMessage();
    }

    private void printWelcomeMessage() {
        dialogContainer.getChildren().add(
                DialogBox.getPeperoDialog("Hello! I'm Pepero. How can I help you today?", peperoImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pepero.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPeperoDialog(response, peperoImage)
        );
        userInput.clear();

        if (input.trim().equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
