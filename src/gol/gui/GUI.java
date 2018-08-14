package gol.gui;

import gol.Game;
import gol.Main;
import gol.Updater;
import gol.io.IO;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import library.lang.Pair;

import java.util.ArrayList;

public class GUI extends BorderPane {
    private Field field;
    private Updater updater;
    private ArrayList<Pair<Parent, String>> stylizedObjects;

    private BottomBar bottomBar;

    public GUI(){
        super();
        stylizedObjects = new ArrayList<>();
    }
    public void fill(){
        setId("back");
        addStylizedObject(this, "back");

        field = new Field();
        field.fill();
        updater = new Updater(field);

        bottomBar = new BottomBar();

        this.setCenter(field);
        this.setBottom(bottomBar);
        this.setRight(new PropertyPanel());
        this.setLeft(new FilesPanel());

        maxWidthProperty().bind(Main.getStage().widthProperty());
        maxHeightProperty().bind(Main.getStage().heightProperty());
        field.maxWidthProperty().bind(widthProperty());
        field.maxHeightProperty().bind(heightProperty());

        loadTheme();
    }

    public Updater getUpdater() {
        return updater;
    }
    public boolean isStarted(){
        return updater.isStarted();
    }
    public void start(){
        field.setRunning(true);
        updater.start();
    }
    public void stop(){
        bottomBar.setButtonPress(false);
        field.setRunning(false);
        updater.cancel();
    }
    public void kill(){
        bottomBar.setButtonPress(false);
        field.setRunning(false);
        updater.quit();
    }

    public void setDarkmode(boolean darkmode) {
        if(Main.getParam().isDarkmode() != darkmode){
            Main.getParam().setDarkmode(darkmode);
            loadTheme();
        }
    }

    public void addStylizedObject(Parent node, String style){
        stylizedObjects.add(new Pair<>(node, style));
    }

    public void loadTheme(){
        String path = "style/" + (Main.getParam().isDarkmode() ? "dark" : "light") + "/";
        for (int i = 0; i < stylizedObjects.size(); i++) {
            stylizedObjects.get(i).getX().getStylesheets().setAll(
                    IO.getResource(path + stylizedObjects.get(i).getY() + ".css").toExternalForm()
            );
        }
    }

    public void loadGame(Game game){
        stop();
        field.loadGame(game);
    }
    public Game getGame(){
        return new Game(field.getCases());
    }
}
