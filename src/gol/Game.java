package gol;

import gol.gui.Case;

import java.io.Serializable;

public class Game implements Serializable {
    private boolean[][] game;
    private String name;

    public Game(int size, String name){
        game = new boolean[size][size];
        this.name = name;
    }
    public Game(Case[][] cases){
        if(cases.length == 0){
            return;
        }

        game = new boolean[cases.length][cases[0].length];
        for (int x = 0; x < cases.length; x++) {
            for (int y = 0; y < cases[x].length; y++) {
                game[x][y] = cases[x][y].isAlive();
            }
        }

        name = "Simple Game";
    }

    public void setGame(boolean[][] game) {
        this.game = game;
    }
    public boolean[][] getGame() {
        return game;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
