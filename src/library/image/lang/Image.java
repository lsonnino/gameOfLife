package library.image.lang;

import javafx.scene.image.PixelReader;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Image implements Serializable {
    private Color[][] colors;

    // ====================
    // === CONSTRUCTORS ===
    // ====================

    /**
     * Constructor of the class Image
     * @param bufferedImage a BufferedImage containing the image
     */
    public Image(BufferedImage bufferedImage){
        Color[][] colors = new Color[bufferedImage.getWidth()][bufferedImage.getHeight()];

        for (int x = 0; x < bufferedImage.getWidth() ; x++) {
            for (int y = 0; y < bufferedImage.getHeight() ; y++) {
                int pixel = bufferedImage.getRGB(x, y);

                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                colors[x][y] = new Color(
                        (float)red/255,
                        (float)green/255,
                        (float)blue/255,
                        (float)alpha/255
                );
            }
        }

        this.colors = colors;
    }
    /**
     * Constructor of the class Image
     * @param image a JavaFX Image containing the image
     */
    public Image(javafx.scene.image.Image image){
        Color[][] colors = new Color[(int) image.getWidth()][(int) image.getHeight()];
        PixelReader pixelReader = image.getPixelReader();

        for (int x = 0; x < colors.length ; x++) {
            for (int y = 0; y < colors[0].length ; y++) {
                javafx.scene.paint.Color fxColor = pixelReader.getColor(x, y);

                colors[x][y] = new Color(
                        (float) fxColor.getRed(),
                        (float) fxColor.getGreen(),
                        (float) fxColor.getBlue(),
                        (float) fxColor.getOpacity()
                );
            }
        }

        this.colors = colors;
    }
    /**
     * Constructor of the class Image
     * @param colors the color array containing every color, pixel by pixel
     */
    public Image(Color[][] colors){
        this.colors = colors;
    }
    /**
     * Constructor of the class Image
     * @param image duplicate the image
     */
    public Image(Image image){
        Color[][] colors = new Color[image.getWidth()][image.getHeight()];

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                colors[x][y] = new Color(image.get(x, y));
            }
        }

        this.colors = colors;
    }

    // ===============
    // === METHODS ===
    // ===============

    /**
     * Set all the pixels
     * @param colors the new colors, pixel by pixel
     */
    public void setColors(Color[][] colors) {
        this.colors = colors;
    }

    /**
     * Get every pixels
     * @return an array containing the color for each pixel
     */
    public Color[][] getColors() {
        return colors;
    }

    /**
     * Set the color of a single pixel
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @param color the new color of the pixel
     */
    public void set(int x, int y, Color color){
        if(!checkCoordinates(x, y)){
            return;
        }

        colors[x][y] = color;
    }
    /**
     * Get the color of a single pixel
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @return the color of the pixel
     */
    public Color get(int x, int y){
        if(!checkCoordinates(x, y)){
            return null;
        }

        return colors[x][y];
    }

    /**
     * Get the width of the image
     * @return the width of the image
     */
    public int getWidth(){
        return colors.length;
    }
    /**
     * Get the height of the image
     * @return the height of the image
     */
    public int getHeight(){
        if(getWidth() == 0){
            return 0;
        }

        return colors[0].length;
    }

    /**
     * Check that a coordinate is within the bounds of the image
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if the coordinate id within the image
     *          false if the coordinate is outside the bounds of the image
     */
    public boolean checkCoordinates(int x, int y){
        return x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
    }

    /**
     * Invert the red, the green and the blue components of the image
     */
    public void invert(){
        ImageOperator operator = new ImageOperator(this) {
            @Override
            public Color compute(Color originalColor) {
                Color c = originalColor;
                c.invert();
                return c;
            }
        };

        operator.operate();
    }
}
