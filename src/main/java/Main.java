import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
        Stage window;
        Scene scene;
        @Override
        public void start(Stage stage) throws IOException {
            window =stage;
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Layouts/login.fxml"));
            scene = new Scene(loader.load());
            stage.setTitle("Đăng nhập");
            stage.setScene(scene);
            stage.show();
            System.out.println("ádjaskdja");

        }
        public static void main(String[] args) {
            launch();
        }

    }
