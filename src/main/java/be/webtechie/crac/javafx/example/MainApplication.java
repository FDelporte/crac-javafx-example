package be.webtechie.crac.javafx.example;

import be.webtechie.crac.javafx.example.ui.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainApplication extends Application {

    private static final Logger LOGGER = LogManager.getLogger(MainApplication.class);

    private static final double WIDTH = 625;
    private static final double HEIGHT = 300;
    private final MainWindow mainWindow = new MainWindow();
    private final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        LOGGER.info("Starting the stage");

        Scene scene = new Scene(mainWindow, WIDTH, HEIGHT);

        stage.setTitle("JavaFX Test Application");
        stage.setScene(scene);
        stage.centerOnScreen();

        // Let the application exit when the main stage is closed
        stage.setOnCloseRequest(e -> exit());

        stage.show();

        LOGGER.info("Stage was started");

        scheduledThreadPool.scheduleAtFixedRate(this::updateData, 1, 1, TimeUnit.SECONDS);

        LOGGER.info("Scheduled a task to update the data");
    }

    private void exit() {
        LOGGER.info("Stage got closed, ending the program");
        scheduledThreadPool.shutdown();
        Platform.exit();
    }

    private void updateData() {
        try {
            mainWindow.setRandomValue();
        } catch (Exception ex) {
            LOGGER.error("Can't update the data: " + ex.getMessage());
        }
    }
}