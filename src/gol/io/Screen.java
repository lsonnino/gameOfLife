package gol.io;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;

public class Screen {
    public static final int[][] SUPPORTED_RESOLUTIONS = {
            {1920, 1080}
    };

    private Rectangle2D screenBounds;
    private int closestWidth, closestHeight;

    public Screen(){
        screenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();

        for(int[] res : SUPPORTED_RESOLUTIONS){
            boolean done = false;

            if(res[0] > closestWidth && res[1] > closestHeight){
                done =  true;
            }

            closestWidth = res[0];
            closestHeight = res[1];

            if(done){
                break;
            }
        }
    }

    public double getHeightProportion(double height){
        return screenBounds.getHeight()*height/1080;
    }
    public double getWidthProportion(double width){
        return screenBounds.getWidth()*width/1920;
    }

    public Rectangle2D getScreenBounds() {
        return screenBounds;
    }

    public int getClosestWidth() {
        return closestWidth;
    }
    public int getClosestHeight() {
        return closestHeight;
    }

    public Insets getInsets(int top, int right, int bottom, int left){
        return new Insets(
                getHeightProportion(top),
                getWidthProportion(right),
                getHeightProportion(bottom),
                getWidthProportion(left)
        );
    }
}

