package gol.gui;

import gol.Main;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import library.javafx.HorizontalSpacer;
import library.javafx.Layout;

public class BottomBar extends HBox {
    private ToggleButton startButton;
    private CheckBox darkmodeCheckBox;

    public BottomBar(){
        super();

        startButton = new ToggleButton("Start");
        startButton.setOnAction(event -> {
            if(Main.getGui().isStarted()){
                Main.getGui().stop();
                startButton.setText("Start");
            }
            else {
                Main.getGui().start();
                startButton.setText("Stop");
            }
        });

        Label iterationsTextLabel = new Label("Iterations: ");
        Label iterationsLabel = new Label("0");
        iterationsLabel.textProperty().bind(Main.getGui().getUpdater().iterationsProperty().asString());

        darkmodeCheckBox = new CheckBox("Darkmode");
        darkmodeCheckBox.setId("check-box");
        darkmodeCheckBox.setSelected(Main.getParam().isDarkmode());
        darkmodeCheckBox.setOnAction(event -> Main.getGui().setDarkmode(darkmodeCheckBox.isSelected()));
        darkmodeCheckBox.setPickOnBounds(true);

        this.getChildren().addAll(
                Layout.vCenter(darkmodeCheckBox),
                new HorizontalSpacer(),
                Layout.vCenter(
                        Layout.hLayout(
                                iterationsTextLabel,
                                iterationsLabel
                        )
                ),
                new HorizontalSpacer(),
                startButton);
        this.setPadding(Main.getScreen().getInsets(5, 50, 5, 50));
        this.setId("back");


        Main.addStylizedObject(this, "bottomBar");
        Main.addStylizedObject(iterationsLabel, "label");
        Main.addStylizedObject(iterationsTextLabel, "label");
        Main.addStylizedObject(startButton, "button");
        Main.addStylizedObject(darkmodeCheckBox, "checkBox");
    }

    public void setButtonPress(boolean pressed){
        if(!pressed){
            startButton.setText("Start");
        }
        else {
            startButton.setText("Stop");
        }
        startButton.setSelected(pressed);
    }

    public void refreshDarkmode(){
        if(Main.getParam().isDarkmode() != darkmodeCheckBox.isSelected()){
            darkmodeCheckBox.setSelected(Main.getParam().isDarkmode());
        }
    }
}
