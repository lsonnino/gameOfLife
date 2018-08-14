package gol;

import gol.io.FileSystem;
import library.io.Serializer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class GolParam implements Serializable {
    private int size;
    private boolean sideAlive;
    private int speed;
    private boolean darkmode;

    public GolParam(){
        size = 32;
        sideAlive = false;
        speed = 500;
        darkmode = false;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    public void setSideAlive(boolean sideAlive) {
        this.sideAlive = sideAlive;
    }
    public boolean isSideAlive() {
        return sideAlive;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getSpeed() {
        return speed;
    }

    public boolean isDarkmode() {
        return darkmode;
    }
    public void setDarkmode(boolean darkmode) {
        this.darkmode = darkmode;
    }

    public static GolParam open(){
        GolParam golParam = null;
        // Get settings
        try {
            golParam = Serializer.deserialize(new File(FileSystem.SETTINGS), GolParam.class);
        } catch (NullPointerException | ClassNotFoundException | IOException e) {
            golParam = new GolParam();
            saveSettings(golParam);
        }
        return golParam;
    }
    public static void saveSettings(GolParam golParam){
        try {
            Serializer.serialize(golParam, FileSystem.getApplicationFolder().getPath(), FileSystem.SETTINGS_FILE_NAME);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
