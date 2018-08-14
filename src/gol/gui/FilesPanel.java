package gol.gui;

import gol.Game;
import gol.Main;
import gol.io.FileSystem;
import gol.patterns.Patterns;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import library.io.Serializer;
import library.javafx.HorizontalSpacer;
import library.javafx.Layout;
import library.javafx.VerticalSpacer;
import library.lang.SimpleAction;

import java.io.File;
import java.io.IOException;

public class FilesPanel extends HBox {
    private TextField textField;
    private Label textLabel;
    private Parent nameAsker;

    public FilesPanel(){
        super();

        ListView<Game> list = new ListView<>();
        list.setItems(FXCollections.observableArrayList(get()));
        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        list.setOnMouseClicked(event -> {
            if(event.getClickCount() < 2){
                return;
            }

            Main.getGui().loadGame(list.getSelectionModel().getSelectedItem());
            list.getSelectionModel().clearSelection();
        });

        VBox.setVgrow(list, Priority.ALWAYS);

        HBox bottomHBox = new HBox();
        Label plusLabel = new Label("+");
        plusLabel.setPickOnBounds(true);
        Label minusLabel = new Label("-");
        minusLabel.setPickOnBounds(true);
        Label exportLabel = new Label(">");
        exportLabel.setPickOnBounds(true);
        Label importLabel = new Label("<");
        importLabel.setPickOnBounds(true);

        minusLabel.setOnMouseClicked(event -> {
            Game selection = list.getSelectionModel().getSelectedItem();
            if(selection == null){
                return;
            }

            delete(selection);

            list.getItems().remove(selection);
        });
        plusLabel.setOnMouseClicked(event -> {
            showNameAsker(() -> {
                Game game = Main.getGui().getGame();
                game.setName(textField.getText());
                save(game);
                list.getItems().add(game);
            });
        });
        exportLabel.setOnMouseClicked(event -> {
            DirectoryChooser fileChooser = new DirectoryChooser();
            fileChooser.setTitle("Export game");
            File file = fileChooser.showDialog(Main.getStage());

            if(file == null){
                return;
            }

            showNameAsker(() -> {
                Game game = Main.getGui().getGame();
                game.setName(textField.getText());
                saveTo(game, file.getPath());
            });
        });
        importLabel.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Import game");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Games",
                    "*" + FileSystem.SAVED_GAMES_EXTENSION));
            File file = fileChooser.showOpenDialog(Main.getStage());

            if(file == null){
                return;
            }

            Game game = openFile(file);
            if(game == null){
                return;
            }

            save(game);
            list.getItems().add(game);
        });


        bottomHBox.getChildren().addAll(plusLabel, minusLabel, new HorizontalSpacer(), importLabel, exportLabel);
        bottomHBox.setSpacing(Main.getScreen().getWidthProportion(20));
        bottomHBox.setPadding(Main.getScreen().getInsets(0, 20, 0, 20));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(list, bottomHBox);
        VBox.setVgrow(vBox, Priority.ALWAYS);

        this.getChildren().addAll(
                new HorizontalSpacer(),
                vBox,
                new HorizontalSpacer()
        );

        nameAsker = getNameAsker();


        this.setId("back");
        Main.addStylizedObject(this, "files");
        Main.addStylizedObject(plusLabel, "label");
        Main.addStylizedObject(minusLabel, "label");
        Main.addStylizedObject(importLabel, "label");
        Main.addStylizedObject(exportLabel, "label");
        Main.addStylizedObject(list, "list");
        Main.addStylizedObject(nameAsker, "over");
        Main.addStylizedObject(textLabel, "label");
        Main.addStylizedObject(textField, "textfield");
    }

    public static Game[] get(){
        File folder = new File(FileSystem.SAVED_GAMES);
        if(!folder.exists()){
            create();
        }

        File[] files = folder.listFiles();
        if(files.length == 0){
            create();
            files = folder.listFiles();
        }

        Game[] games = new Game[files.length];
        for (int i = 0; i < files.length; i++) {
            games[i] = openFile(files[i]);
        }

        return games;
    }
    public static Game openFile(File file){
        Game game = null;
        try {
            game = Serializer.deserialize(file, Game.class);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int x = 0; x < game.getGame().length; x++) {
            for (int y = 0; y < game.getGame()[x].length; y++) {
                if(game.getGame()[x][y]){
                    System.out.println(x + " " + y + ": true");
                }
                System.out.println();
            }
        }

        return game;
    }
    public static void create(){
        for(Game game : Patterns.patterns) {
            save(game);
        }
    }
    public static void save(Game game){
        saveTo(game, FileSystem.SAVED_GAMES);
    }
    public static void saveTo(Game game, String to){
        try {
            Serializer.serialize(
                    game,
                    to,
                    game.toString() + FileSystem.SAVED_GAMES_EXTENSION
            );
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
    public static void delete(Game game){
        File file = new File(FileSystem.SAVED_GAMES + FileSystem.SEPARATOR +
                game.toString() + FileSystem.SAVED_GAMES_EXTENSION);
        file.delete();
    }

    public Parent getNameAsker(){
        textField = new TextField();
        textLabel = new Label("Enter the game name");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(textLabel, textField);
        vBox.setPadding(Main.getScreen().getInsets(20, 20, 20, 20));
        vBox.setId("back");

        return Layout.hCenter(Layout.vLayout(vBox, new VerticalSpacer()));
    }
    public void showNameAsker(SimpleAction action){
        textField.setOnAction(event -> {
            if(textField.getText() == null || textField.getText().equals("")){
                return;
            }

            action.run();
            Main.getStackPane().getChildren().remove(nameAsker);
        });
        Main.getStackPane().getChildren().add(nameAsker);
    }
}
