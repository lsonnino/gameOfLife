package gol.patterns;

import gol.Game;

public class Pulsar extends Game {
    public Pulsar(){
        super(32, "Pulsar");
        getGame()[9][11] = true;
        getGame()[9][12] = true;
        getGame()[9][18] = true;
        getGame()[9][19] = true;

        getGame()[10][12] = true;
        getGame()[10][13] = true;
        getGame()[10][17] = true;
        getGame()[10][18] = true;

        getGame()[11][9] = true;
        getGame()[11][12] = true;
        getGame()[11][14] = true;
        getGame()[11][16] = true;
        getGame()[11][18] = true;
        getGame()[11][21] = true;

        getGame()[12][9] = true;
        getGame()[12][10] = true;
        getGame()[12][11] = true;
        getGame()[12][13] = true;
        getGame()[12][14] = true;
        getGame()[12][16] = true;
        getGame()[12][17] = true;
        getGame()[12][19] = true;
        getGame()[12][20] = true;
        getGame()[12][21] = true;

        getGame()[13][10] = true;
        getGame()[13][12] = true;
        getGame()[13][14] = true;
        getGame()[13][16] = true;
        getGame()[13][18] = true;
        getGame()[13][20] = true;

        getGame()[14][11] = true;
        getGame()[14][12] = true;
        getGame()[14][13] = true;
        getGame()[14][17] = true;
        getGame()[14][18] = true;
        getGame()[14][19] = true;


        getGame()[16][11] = true;
        getGame()[16][12] = true;
        getGame()[16][13] = true;
        getGame()[16][17] = true;
        getGame()[16][18] = true;
        getGame()[16][19] = true;

        getGame()[17][10] = true;
        getGame()[17][12] = true;
        getGame()[17][14] = true;
        getGame()[17][16] = true;
        getGame()[17][18] = true;
        getGame()[17][20] = true;

        getGame()[18][9] = true;
        getGame()[18][10] = true;
        getGame()[18][11] = true;
        getGame()[18][13] = true;
        getGame()[18][14] = true;
        getGame()[18][16] = true;
        getGame()[18][17] = true;
        getGame()[18][19] = true;
        getGame()[18][20] = true;
        getGame()[18][21] = true;

        getGame()[19][9] = true;
        getGame()[19][12] = true;
        getGame()[19][14] = true;
        getGame()[19][16] = true;
        getGame()[19][18] = true;
        getGame()[19][21] = true;

        getGame()[20][12] = true;
        getGame()[20][13] = true;
        getGame()[20][17] = true;
        getGame()[20][18] = true;

        getGame()[21][11] = true;
        getGame()[21][12] = true;
        getGame()[21][18] = true;
        getGame()[21][19] = true;
    }
}
