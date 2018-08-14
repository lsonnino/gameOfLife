package gol.gui;

import gol.Game;
import gol.Main;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import library.javafx.Layout;

public class Field extends StackPane {
    private Case[][] cases;
    private VBox backVBox;

    public Field() {
        super();
        this.setId("field-panel");
        Main.addStylizedObject(this, "field");
    }

    public void fill(){
        this.getChildren().clear();
        Pane backPane = new Pane();
        backPane.setId("back");
        backPane.setEffect(new InnerShadow(
                Main.getScreen().getWidthProportion(15),
                Color.rgb(0, 0, 0, 0.5)
        ));
        VBox.setVgrow(backPane, Priority.ALWAYS);
        HBox.setHgrow(backPane, Priority.ALWAYS);
        this.getChildren().add(backPane);


        cases = new Case[Main.getParam().getSize()][Main.getParam().getSize()];

        for (int x = 0; x < cases.length; x++) {
            for (int y = 0; y < cases[x].length; y++) {
                cases[x][y] = new Case();
            }
        }

        backVBox = new VBox();
        backVBox.setId("field"); // Changed in setRunning
        backVBox.setSpacing(0);
        for (int x = 0; x < cases.length; x++) {
            HBox hBox = new HBox();
            hBox.setSpacing(0);
            for (int y = 0; y < cases.length; y++) {
                hBox.getChildren().add(cases[x][y]);
            }
            backVBox.getChildren().add(hBox);
        }
        this.getChildren().add(Layout.vCenter(Layout.hCenter(backVBox)));

        setRunning(false);
    }

    public Case[][] getCases() {
        return cases;
    }

    public void loadGame(Game game){
        if(cases.length != game.getGame().length || cases[0].length != game.getGame()[0].length){
            Main.getParam().setSize(game.getGame().length);
            fill();
        }

        for (int x = 0; x < cases.length; x++) {
            for (int y = 0; y < cases[x].length; y++) {
                cases[x][y].setAlive(game.getGame()[x][y]);
            }
        }
    }

    public void setRunning(boolean running){
        if(running){
            backVBox.setId("running-field");
        }
        else {
            backVBox.setId("field");
        }
    }
}
