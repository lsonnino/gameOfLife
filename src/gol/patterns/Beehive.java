package gol.patterns;

import gol.Game;

public class Beehive extends Game {
    public Beehive(){
        super(32, "Beehive");
        getGame()[14][15] = true;

        getGame()[15][14] = true;
        getGame()[15][15] = true;
        getGame()[15][16] = true;

        getGame()[16][14] = true;
        getGame()[16][16] = true;

        getGame()[17][15] = true;
    }
}
