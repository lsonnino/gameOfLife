package gol;

import gol.gui.GUI;
import gol.gui.TouchBar;
import gol.io.FileSystem;
import gol.io.Screen;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage stage;
    private static GolParam param;
    private static Screen screen;
    private static GUI gui;
    private static StackPane stackPane;
    private static TouchBar touchBar;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        screen = new Screen();
        param = GolParam.open();
        stackPane = new StackPane();
        gui = new GUI();
        gui.fill();

        stackPane.getChildren().add(gui);

        primaryStage.setTitle(Constants.NAME);
        primaryStage.setScene(new Scene(
                stackPane,
                screen.getScreenBounds().getWidth(), screen.getScreenBounds().getHeight()
        ));
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> gui.kill());

        // [MAC ONLY] TouchBar
        if(FileSystem.getOS() == FileSystem.MACOS){
            touchBar = new TouchBar();
            //touchBar.show(stage);
        }
    }

    public static Stage getStage() {
        return stage;
    }
    public static StackPane getStackPane() {
        return stackPane;
    }
    public static GUI getGui() {
        return gui;
    }

    public static void refreshTouchBar(){
        if(touchBar != null){
            touchBar.refreshStatus();
        }
    }

    public static void addStylizedObject(Parent node, String style){
        gui.addStylizedObject(node, style);
    }

    public static Screen getScreen() {
        return screen;
    }
    public static double getWidth() {
        return stage.getWidth();
    }
    public static double getHeight() {
        return stage.getHeight();
    }

    public static void setParam(GolParam param) {
        Main.param = param;
    }
    public static GolParam getParam() {
        return param;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
