package gol.io;

import gol.Main;
import javafx.scene.text.Font;

import java.io.InputStream;
import java.net.URL;

public class IO {
    public static InputStream getResourceAsStream(String path){
        return Main.class.getClassLoader().getResourceAsStream(path);
    }
    public static URL getResource(String path){
        return Main.class.getResource("/" + path);
    }

    /**
     *
     * @param fontName the font name
     * @param size the size
     * @return the font
     */
    public static Font getFont(String fontName, int size){
        return Font.loadFont(getResourceAsStream(
                FileSystem.FONTS + FileSystem.SEPARATOR + fontName + ".ttf"
                ), size);
    }
}
