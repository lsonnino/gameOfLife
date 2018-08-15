package gol.io;

import gol.Constants;

import java.io.File;

public class FileSystem {
    public static final short WINDOWS = 0;
    public static final short LINUX = 1;
    public static final short MACOS = 2;

    public static final String SEPARATOR = System.getProperty("file.separator");

    public static final String RESOURCES = "res";

        public static final String FONTS = RESOURCES + SEPARATOR + "fonts";

    public static final String SETTINGS_FILE_NAME = "settings.gol";
    public static final String SETTINGS = getApplicationFolder().getPath() + SEPARATOR + SETTINGS_FILE_NAME;

    public static final String SAVED_GAMES_EXTENSION = ".game";
    public static final String SAVED_GAMES = getApplicationFolder().getPath() + SEPARATOR + "games";

    public static File getApplicationFolder(){
        String home = System.getProperty("user.home");

        String projectName = Constants.PROJECT_NAME.replaceAll("\\s", "").toLowerCase();

        switch (getOS()){
            case WINDOWS:
                return new File(
                        home + SEPARATOR + "AppData" + SEPARATOR + "Local" + SEPARATOR + "." + projectName
                );
            case MACOS:
                return new File(
                        home + SEPARATOR + "Library" + SEPARATOR + "Application Support" + SEPARATOR + "org.alfcorp." + projectName
                );
            case LINUX:
                return new File(
                        home + SEPARATOR + "." + projectName
                );
            default:
                return new File(
                        home + SEPARATOR + "AppData" + SEPARATOR + "Local" + SEPARATOR + "." + projectName
                );
        }
    }

    public static short getOS(){
        String os = System.getProperty("os.name").toLowerCase();

        if(os.contains("win")){ // Windows
            return WINDOWS;
        }
        else if(os.contains("mac")){ // macOS
            return MACOS;
        }
        else { // Linux
            return LINUX;
        }
    }
}
