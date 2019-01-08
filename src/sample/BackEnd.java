package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BackEnd {
    public Label text;
    public Label scores;
    public Button startButton;
    public Button button;
    private int timeLeft;
    private int timeLeft2;
    private int numClicks;

    public void pressedButton(javafx.event.ActionEvent actionEvent)
    {
        numClicks++;
        button.setStyle("-fx-background-color:rgb(0,0,200); -fx-padding:10 80 20 10; -fx-position:fixed;");
        timeLeft2 = 100;
        AnimationTimer a = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(timeLeft2 > 0)
                {
                    timeLeft2 -= 20;
                }
                else
                {
                    button.setStyle("-fx-background-color:rgb(0,0,82); -fx-padding:10 80 20 10; -fx-position:fixed;");
                    stop();
                }
            }
        };
        a.start();
    }

    public void start(ActionEvent actionEvent) {
        //showHighScores();
        startButton.setVisible(false);
        button.setVisible(true);
        numClicks = 0;
        timeLeft = 10000;
        AnimationTimer a = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(timeLeft > 0)
                {
                    timeLeft -= 15;
                    text.setText("Clicks : " + numClicks + " Time Left : " + timeLeft/1000);
                }
                else
                {
                    button.setMouseTransparent(false);
                    button.setVisible(false);
                    startButton.setVisible(true);
                    text.setText("You clicked " + numClicks + " times.");
                    stop();
                }
            }
        };
        a.start();
    }
    /**public void showHighScores()
    {
        String scoresString = "";
        Path pathToFile = Paths.get("highScores");
        try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII))
        {
            String line = br.readLine();
            while(line != null) {
                scoresString += line;
            }
            line = br.readLine();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        scores.setText(scoresString);
    }
     **/
}
