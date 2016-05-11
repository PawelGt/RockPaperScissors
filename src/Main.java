import com.sun.xml.internal.bind.v2.TODO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.stage.*;
import javafx.util.Duration;

import java.nio.file.Paths;
import java.io.File;
import java.util.Random;


/**
 * Test Application :)
 *
 * Created by Tim on 21/04/16.
 */
public class Main extends Application {

    Scene scene1, scene2, scene3, scene4;
    Stage window;
    GridPane grid;
    Game game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Stage window = primaryStage; // primary stage
        window.setTitle("Rock Paper Scissors"); // Window title (top of screen)

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });



        // Layout 1 - Main menu

            //music for main menu
        String path = new File("src/Sounds/menumusic.mp3").getAbsolutePath();
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setStartTime(Duration.seconds(8));
        player.play();
        player.setVolume(0.08);

            //Button 1 (go to game)
        Button button1 = new Button("Click here to start"); // Create a button
        button1.setOnAction(e -> {
            game = new Game();
            window.setScene(scene2);
            player.stop();
        });


            // Button 3 (exit button)
        Button button3 = new Button("Exit");
        button3.setOnAction(e -> closeProgram());

        Label label1 = new Label("Welcome to Rock Paper Scissors!");

        MediaView mediaView = new MediaView(player);

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1, button3, mediaView);
        scene1 = new Scene(layout1, 1080, 960); // create the scene, set size of entire window



        // Layout 2 - Gameplay

        GridPane gamegrid = new GridPane();
        gamegrid.setPadding(new Insets(10,10,10,10));
        gamegrid.setVgap(8);
        gamegrid.setHgap(10);

        // Wins label
        Label winslabel = new Label("Wins: " + Game.getWins());
        gamegrid.setConstraints(winslabel, 1, 2);
        // Tie label
        Label tielabel = new Label("Ties: " + Game.getTies());
        gamegrid.setConstraints(tielabel, 2, 2);
        // Loss label
        Label losslabel = new Label("Losses: " + Game.getLosses());
        gamegrid.setConstraints(losslabel, 3, 2);
        // Totalgames label
        Label totallabel = new Label("Total Games played: " + Game.getTotalgames());
        gamegrid.setConstraints(totallabel, 0, 1);


        // Button 2 (to highscore button)
        Button button2 = new Button("End Game");
        button2.setOnAction(e -> window.setScene(scene3));
        gamegrid.setConstraints(button2, 12, 31);

        // Rock button
        Button rockbutton = new Button("Rock");

        rockbutton.setOnAction(e -> {
                int result = game.play(1); // choice: 1 = rock, 2 = paper, 3 = scissors
                winslabel.setText("Wins: " + Game.getWins());
                losslabel.setText("Losses: " + Game.getLosses());
                tielabel.setText("Ties: " + Game.getTies());
                totallabel.setText("Total Games Played: " + Game.getTotalgames());
                effectSelect(result);
            }
        );
        gamegrid.setConstraints(rockbutton, 11, 30);

        // Paper button
        Button paperbutton = new Button("Paper");

        paperbutton.setOnAction(e -> {
            int result = game.play(2); // choice: 1 = rock, 2 = paper, 3 = scissors
                    winslabel.setText("Wins: " + Game.getWins());
                    losslabel.setText("Losses: " + Game.getLosses());
                    tielabel.setText("Ties: " + Game.getTies());
                    totallabel.setText("Total Games Played: " + Game.getTotalgames());
                    effectSelect(result);
                }
        );
        gamegrid.setConstraints(paperbutton, 12, 30);

        // Scissor button
        Button scissorbutton = new Button("Scissor");

        scissorbutton.setOnAction(e -> {
                    int result = game.play(3); // choice: 1 = rock, 2 = paper, 3 = scissors
                    winslabel.setText("Wins: " + Game.getWins());
                    losslabel.setText("Losses: " + Game.getLosses());
                    tielabel.setText("Ties: " + Game.getTies());
                    totallabel.setText("Total Games Played: " + Game.getTotalgames());
                    effectSelect(result);
                }
        );
        gamegrid.setConstraints(scissorbutton, 13, 30);

        gamegrid.getChildren().addAll(button2, rockbutton, paperbutton, scissorbutton, winslabel,
                losslabel, tielabel, totallabel);
        scene2 = new Scene(gamegrid, 1080, 960);



        // Layout 3 - To highscore submit

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

            // Name Label
        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 1);

            // Name input
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);
        nameInput.setPromptText("Your name here");

            // Submitbutton - submit highscore
        Button submitbutton = new Button("Submit score");
        submitbutton.setOnAction(e -> {
            window.setScene(scene4);
        });
        GridPane.setConstraints(submitbutton, 1, 2);

            //title lable
        Label label2 = new Label("Enter your name to enter the highscore list");
        GridPane.setConstraints(label2, 0, 0);

        grid.getChildren().addAll(label2,nameLabel, nameInput, submitbutton);
        scene3 = new Scene(grid, 1080, 960);




        //Layout 4 - Highscore display

        GridPane highscoregrid = new GridPane();
        highscoregrid.setPadding(new Insets(10,10,10,10));
        highscoregrid.setVgap(8);
        highscoregrid.setHgap(10);

            // menubutton - back to menu
        Button menubutton = new Button("Back to main menu");
        menubutton.setOnAction(e -> {
            window.setScene(scene1);
            player.play();
        });
        GridPane.setConstraints(menubutton, 1, 2);

            //highscore title lable
        Label highscoretitle = new Label("The top 3 players are:");
        GridPane.setConstraints(highscoretitle, 1, 3);

        highscoregrid.getChildren().addAll(menubutton, highscoretitle);
        scene4 = new Scene(highscoregrid, 1080, 960);

            // TODO: button to return to menu or restart.


        // startup
        primaryStage.setScene(scene1); // set the main scene on stage
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Warning!", "Are you sure you want to exit?");

        if(answer){
            Platform.exit();
        }
    }

    private void playCheer(){
        String cheers[] = {"src/Sounds/Cheers/ayayay.wav","src/Sounds/Cheers/ole.mp3"};
        Random random = new Random();
        String path = new File(cheers[random.nextInt(cheers.length)]).getAbsolutePath();
        playSound(path);
    }

    private void playBoo(){
        String boos[] = {"src/Sounds/Boos/nonono.aif","src/Sounds/Boos/no.wav"};
        Random random = new Random();
        String path = new File(boos[random.nextInt(boos.length)]).getAbsolutePath();
        playSound(path);
    }

    private void playDraw(){
        String draws[] = {"src/Sounds/Draw/doitagain.wav"};
        Random random = new Random();
        String path = new File(draws[random.nextInt(draws.length)]).getAbsolutePath();
        playSound(path);
    }

    private void playSound(String path){
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.play();
        player.setVolume(0.5);
    }
    private void effectSelect(int result){
        if(result == 3){
            playCheer();
        }
        else if(result == 4){
            playBoo();
        }
        else {
            playDraw();
        }
    }

}
