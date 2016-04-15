package Timed;

/*
Zachary Betters
CIS150
Timed Traffic Light
This program will simulate a traffic light with a timer.
Each light will be a different time
 */

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle[] circle = new Circle[3];

        //array of circles created for each light
        circle[0] = new Circle();
        circle[0].setRadius(30);
        circle[0].setStroke(Color.BLACK);
        circle[0].setFill(Color.DARKRED);

        circle[1] = new Circle();
        circle[1].setRadius(30);
        circle[1].setStroke(Color.BLACK);
        circle[1].setFill(Color.DARKGOLDENROD);

        //green is first to be lit up
        circle[2] = new Circle();
        circle[2].setRadius(30);
        circle[2].setStroke(Color.BLACK);
        circle[2].setFill(Color.LIGHTGREEN);

        VBox LightPane = new VBox(10);
        LightPane.setAlignment(Pos.CENTER);
        LightPane.setPadding(new Insets(10));
        //circles are added to vbox
        LightPane.getChildren().addAll(circle[0], circle[1], circle[2]);

        Scene scene = new Scene(LightPane);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Traffic Light");
        primaryStage.setScene(scene);
        primaryStage.show();

        //event handler for when circles are different colors
        EventHandler<ActionEvent> lightChange = e -> {
            if (circle[2].getFill() == Color.LIGHTGREEN) {
                circle[0].setFill(Color.DARKRED);
                circle[1].setFill(Color.YELLOW);
                circle[2].setFill(Color.FORESTGREEN);
            }
            else if (circle[1].getFill() == Color.YELLOW) {
                circle[0].setFill(Color.RED);
                circle[1].setFill(Color.DARKGOLDENROD);
                circle[2].setFill(Color.FORESTGREEN);
            }
            else if (circle[0].getFill() == Color.RED) {
                circle[0].setFill(Color.DARKRED);
                circle[1].setFill(Color.DARKGOLDENROD);
                circle[2].setFill(Color.LIGHTGREEN);
            }
    };

        //3 timelines created, each for separate timers
        Timeline green = new Timeline(new KeyFrame(Duration.seconds(15), lightChange));
        Timeline yellow = new Timeline(new KeyFrame(Duration.seconds(2), lightChange));
        Timeline red = new Timeline(new KeyFrame(Duration.seconds(10), lightChange));

        //all timelines added to a loop
        SequentialTransition loop = new SequentialTransition(green, yellow, red);

        //SequentialTransition loop cycles through the timers endlessly
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }
}