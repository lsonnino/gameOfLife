package gol.gui;

import gol.Main;
import javafx.scene.shape.Rectangle;

public class Case extends Rectangle {
    private boolean alive;

    public Case(){
        super();
        double sizeProportion32 = ((double) Main.getParam().getSize())/32;
        setPickOnBounds(true);
        setHeight(Main.getScreen().getHeightProportion(20*sizeProportion32));
        widthProperty().bind(heightProperty());

        setOnMouseClicked(event -> setAlive(!isAlive()));

        setAlive(false);
    }

    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;

        setId(alive ? "alive" : "dead");
    }

    public static double getCaseSize(){
        double w = Main.getWidth() / Main.getParam().getSize();
        double h = Main.getHeight() / Main.getParam().getSize();

        return (w < h ? w : h);
    }
}
