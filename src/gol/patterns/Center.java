package gol.patterns;

import gol.Game;

public class Center extends Game {
    public Center(){
        super(32, "Center");
        getGame()[15][15] = true;
        getGame()[16][15] = true;
        getGame()[15][16] = true;
        getGame()[16][16] = true;
    }
}
