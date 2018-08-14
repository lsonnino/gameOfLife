package gol.gui;

import gol.GolParam;
import gol.Main;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import library.javafx.HorizontalSpacer;
import library.javafx.Layout;
import library.javafx.VerticalSpacer;

public class PropertyPanel extends HBox {
    public PropertyPanel(){
        super();

        VBox vBox = new VBox();
        vBox.setId("back");

        VBox propertiesVBox = new VBox();
        propertiesVBox.setSpacing(Main.getScreen().getHeightProportion(20));
        propertiesVBox.setPadding(Main.getScreen().getInsets(100, 100, 100, 100));



        CheckBox bordersAlive = new CheckBox("Alive borders");
        bordersAlive.setSelected(Main.getParam().isSideAlive());
        bordersAlive.setId("check-box");
        bordersAlive.setOnAction(event -> Main.getParam().setSideAlive(bordersAlive.isSelected()));

        Label speedLabel = new Label("Refresh speed");
        Slider speedSlider = new Slider(100, 1000, Main.getParam().getSpeed());
        speedSlider.valueProperty().addListener(event -> Main.getParam().setSpeed((int) speedSlider.getValue()));
        speedSlider.setMajorTickUnit(100);
        speedSlider.setShowTickMarks(true);

        propertiesVBox.getChildren().addAll(
                Layout.hLayout(bordersAlive, new HorizontalSpacer()),
                Layout.hLayout(speedLabel, new HorizontalSpacer()),
                Layout.hCenter(speedSlider)
        );

        VBox outterPropertiesVBox = new VBox();
        outterPropertiesVBox.setId("outter-properties-vbox");
        outterPropertiesVBox.getChildren().addAll(
                propertiesVBox,
                new VerticalSpacer()
        );
        VBox.setVgrow(outterPropertiesVBox, Priority.ALWAYS);

        vBox.getChildren().add(outterPropertiesVBox);

        Label saveLabel = new Label("v");
        saveLabel.setPickOnBounds(true);
        Label resetLabel = new Label("r");
        resetLabel.setPickOnBounds(true);
        saveLabel.setOnMouseClicked(event -> GolParam.saveSettings(Main.getParam()));
        resetLabel.setOnMouseClicked(event -> {
            boolean darkmode = Main.getParam().isDarkmode();
            Main.setParam(new GolParam());
            Main.getParam().setDarkmode(darkmode);
            speedSlider.setValue(Main.getParam().getSpeed());
            bordersAlive.setSelected(Main.getParam().isSideAlive());
        });

        HBox bottomHBox = Layout.hLayout(
                saveLabel,
                new HorizontalSpacer(),
                resetLabel
        );
        bottomHBox.setPadding(Main.getScreen().getInsets(0, 20, 0, 20));

        vBox.getChildren().add(bottomHBox);

        this.getChildren().addAll(
                new HorizontalSpacer(),
                vBox,
                new HorizontalSpacer()
        );



        Main.addStylizedObject(bordersAlive, "checkBox");
        Main.addStylizedObject(speedSlider, "slider");
        Main.addStylizedObject(speedLabel, "label");
        Main.addStylizedObject(vBox, "properties");
        Main.addStylizedObject(saveLabel, "label");
        Main.addStylizedObject(resetLabel, "label");
    }
}
