import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        primaryStage.setTitle("ImagePrepTool");
        primaryStage.setScene(new Scene(root, 800, 600));

        primaryStage.setMinWidth(325);
        primaryStage.setMinHeight(400);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
