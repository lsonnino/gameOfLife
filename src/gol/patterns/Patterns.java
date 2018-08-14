package gol.patterns;

import gol.Game;

public class Patterns {
    public static Game[] patterns = new Game[]{
            new Game32(),
            new Game16(),
            new Center(),
            new Beehive(),
            new Pulsar(),
            new TheKing(),
            new Pentadecathlon()
    };
}
