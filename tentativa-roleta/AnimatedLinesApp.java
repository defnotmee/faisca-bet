import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimatedLinesApp extends Application {

	int x;
	public AnimatedLinesApp(int x)
	{
		this.x = x;
	}
    @Override
    public void start(Stage primaryStage) {
        // Create a vertical line
        Line line = new Line();
        line.setStartX(x);
        line.setStartY(100);
        line.setEndX(x);
        line.setEndY(200);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(2);

        // Create a Pane to hold the line
        Pane pane = new Pane();
        pane.getChildren().add(line);

        // Create the animation
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Move the line horizontally across the window
        KeyValue keyValueStartX = new KeyValue(line.startXProperty(), 600);
        KeyValue keyValueEndX = new KeyValue(line.endXProperty(), 600);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValueStartX, keyValueEndX);

        timeline.getKeyFrames().add(keyFrame);

        // When the animation completes, reset the line's position
        timeline.setOnFinished(event -> {
            line.setStartX(0);
            line.setEndX(0);
            timeline.playFromStart(); // Restart the animation
        });

        // Start the animation
        timeline.play();

        // Create a Scene and add the Pane to it
        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setTitle("Animated Line");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

