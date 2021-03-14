package org.example.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

/**
 * @program: maven-javafx-ProgressBar
 * @description: 主类逻辑类
 * @author: Curtain
 * @create: 2020-12-23 15:05
 */
public class AppMain extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample.fxml")));
        primaryStage.setTitle("签到软件");
        primaryStage.setScene(new Scene(root, 360, 310));
        primaryStage.show();
    }
}
