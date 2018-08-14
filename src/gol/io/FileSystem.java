package gol.io;

import gol.Constants;

import java.io.File;

public class FileSystem {
    public static final String SEPARATOR = System.getProperty("file.separator");

    public static final String RESOURCES = "res";

        public static final String FONTS = RESOURCES + SEPARATOR + "fonts";

    public static final String SETTINGS_FILE_NAME = "settings.gol";
    public static final String SETTINGS = getApplicationFolder().getPath() + SEPARATOR + SETTINGS_FILE_NAME;

    public static final String SAVED_GAMES_EXTENSION = ".game";
    public static final String SAVED_GAMES = getApplicationFolder().getPath() + SEPARATOR + "games";

    public static File getApplicationFolder(){
        String os = System.getProperty("os.name").toLowerCase();
        String home = System.getProperty("user.home");

        String projectName = Constants.PROJECT_NAME.replaceAll("\\s", "").toLowerCase();

        if(os.contains("win")){ // Windows
            return new File(home + SEPARATOR + "AppData" + SEPARATOR + "Local" + SEPARATOR + "." + Constants.PROJECT_NAME);
        }
        else if(os.contains("mac")){ // macOS
            return new File(home + SEPARATOR + "Library" + SEPARATOR + "Application Support" + SEPARATOR + "org.alfcorp." + projectName);
        }
        else { // Linux
            return new File(home + SEPARATOR + "." + projectName);
        }
    }
}
