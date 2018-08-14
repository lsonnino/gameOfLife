package gol.patterns;

import gol.Game;

public class Pentadecathlon extends Game {
    public Pentadecathlon(){
        super(32, "Pentadecathlon");
        getGame()[11][14] = true;
        getGame()[11][15] = true;
        getGame()[11][16] = true;

        getGame()[12][13] = true;
        getGame()[12][17] = true;

        getGame()[13][12] = true;
        getGame()[13][18] = true;

        getGame()[15][11] = true;
        getGame()[15][19] = true;

        getGame()[16][11] = true;
        getGame()[16][19] = true;

        getGame()[18][12] = true;
        getGame()[18][18] = true;

        getGame()[19][13] = true;
        getGame()[19][17] = true;

        getGame()[20][14] = true;
        getGame()[20][15] = true;
        getGame()[20][16] = true;
    }
}
