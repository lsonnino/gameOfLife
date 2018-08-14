package library.image.lang;

import java.io.Serializable;

public class Color implements Serializable {
    private float red, green, blue, alpha; // Values between 0 and 1

    public static Color BLACK = new Color(0, 0, 0);
    public static Color WHITE = new Color(1, 1, 1);
    public static Color TRANSPARENT = new Color(0, 0, 0, 0);

    // ====================
    // === CONSTRUCTORS ===
    // ====================

    /**
     * Default constructor of the class Color
     */
    public Color(){
        this(0, 0, 0);
    }
    /**
     * Constructor of the class color
     * @param red the red value between 0 and 1
     * @param green the green value between 0 and 1
     * @param blue the blue value between 0 and 1
     */
    public Color(float red, float green, float blue){
        this(red, green, blue, 1);
    }
    /**
     * Constructor of the class color
     * @param red the red value between 0 and 1
     * @param green the green value between 0 and 1
     * @param blue the blue value between 0 and 1
     * @param alpha the transparency value between 0 and 1
     */
    public Color(float red, float green, float blue, float alpha){
        this.red = checkValue(red);
        this.green = checkValue(green);
        this.blue = checkValue(blue);
        this.alpha = checkValue(alpha);
    }

    /**
     * Duplicate the color
     * @param color the color to duplicate
     */
    public Color(Color color){
        this.red = color.red;
        this.green = color.green;
        this.blue = color.blue;
        this.alpha = color.alpha;
    }

    // =============
    // === ALPHA ===
    // =============

    /**
     * Set the ALPHA value of the color
     * @param alpha the new ALPHA value (between 0 and 1)
     */
    public void setAlpha(float alpha){
        this.alpha = checkValue(alpha);
    }

    /**
     * Get the ALPHA value of the color
     * @return a value from 0 to 1 representing the ALPHA value of the color
     */
    public float getAlpha() {
        return alpha;
    }

    // ===========
    // === RGB ===
    // ===========

    /**
     * Set the RED value of the color
     * @param red the new RED value (between 0 and 1)
     */
    public void setRed(float red){
        this.red = checkValue(red);
    }

    /**
     * Get the RED value of the color
     * @return a value from 0 to 1 representing the RED value of the color
     */
    public float getRed() {
        return red;
    }

    /**
     * Set the GREEN value of the color
     * @param green the new GREEN value (between 0 and 1)
     */
    public void setGreen(float green) {
        this.green = checkValue(green);
    }
    /**
     * Get the GREEN value of the color
     * @return a value from 0 to 1 representing the GREEN value of the color
     */
    public float getGreen() {
        return green;
    }

    /**
     * Set the BLUE value of the color
     * @param blue the new BLUE value (between 0 and 1)
     */
    public void setBlue(float blue) {
        this.blue = checkValue(blue);
    }
    /**
     * Get the BLUE value of the color
     * @return a value from 0 to 1 representing the BLUE value of the color
     */
    public float getBlue() {
        return blue;
    }

    // ===========
    // === HSB ===
    // ===========

    /**
     * Set the hue of the color
     * @param hue the new hue (between 0 and 1)
     */
    public void setHue(float hue){
        float[] hsb = rgbToHsb(red, green, blue);
        float[] rgb = hsbToRgb(checkValue(hue), hsb[1], hsb[2]);

        setRed(rgb[0]);
        setGreen(rgb[1]);
        setBlue(rgb[2]);
    }
    /**
     * Get the hue of the color
     * @return a value from 0 to 1 representing the hue of the color
     */
    public float getHue(){
        return rgbToHsb(red, green, blue)[0];
    }

    /**
     * Set the saturation of the color
     * @param saturation the new saturation value (between 0 and 1)
     */
    public void setSaturation(float saturation){
        float[] hsb = rgbToHsb(red, green, blue);
        float[] rgb = hsbToRgb(hsb[0], checkValue(saturation), hsb[2]);

        setRed(rgb[0]);
        setGreen(rgb[1]);
        setBlue(rgb[2]);
    }
    /**
     * Get the saturation of the color
     * @return a value from 0 to 1 representing the saturation of the color
     */
    public float getSaturation(){
        return rgbToHsb(red, green, blue)[1];
    }

    /**
     * Set the brightness of the color
     * @param brightness the new brightness (between 0 to 1)
     */
    public void setBrightness(float brightness){
        float[] hsb = rgbToHsb(red, green, blue);
        float[] rgb = hsbToRgb(hsb[0], hsb[1], checkValue(brightness));

        setRed(rgb[0]);
        setGreen(rgb[1]);
        setBlue(rgb[2]);
    }
    /**
     * Get the brightness of the color
     * @return a value from 0 to 1 representing the brightness of the color
     */
    public float getBrightness(){
        return rgbToHsb(red, green, blue)[2];
    }

    // === OTHER ===
    /**
     * Compare two colors
     * @param color the color to compare
     * @return true if the colors contains the same values. Return false otherwise
     */
    public boolean equals(Color color) {
        return red == color.red && green == color.green && blue == color.blue;
    }

    /**
     * Set the color to the medium value of the RGBA components of this color with another one
     * @param color the other color
     */
    public void mix(Color color){
        Color mixed = Color.mix(this, color);

        setRed(mixed.getRed());
        setGreen(mixed.getGreen());
        setBlue(mixed.getBlue());
        setAlpha(mixed.getAlpha());
    }
    /**
     * Mix the color with another color giving more value to one of the two colors
     * @param color the second color
     * @param mix the weigh of the this color (from 0 to 1)
     * @return the mixed RGBA color
     */
    public void mix(Color color, float mix){
        Color mixed = Color.mix(this, color, mix);

        setRed(mixed.getRed());
        setGreen(mixed.getGreen());
        setBlue(mixed.getBlue());
        setAlpha(mixed.getAlpha());
    }

    /**
     * Invert the red, the green and the blue
     */
    public void invert(){
        red = 1-red;
        green = 1-green;
        blue = 1-blue;
    }

    // === STATIC ===
    /**
     * Convert the RGB values to HSB
     * @param red the red value, between 0 and 1
     * @param green the green value, between 0 and 1
     * @param blue the blue value, between 0 and 1
     * @return an array containing the hue, saturation and brightness values (in that order) between 0 and 1
     */
    public static float[] rgbToHsb(float red, float green, float blue){
        float[] hsb = new float[3];
        hsb = java.awt.Color.RGBtoHSB(
                Math.round(checkValue(red)*255),
                Math.round(checkValue(green)*255),
                Math.round(checkValue(blue)*255),
                hsb
        );

        return hsb;
    }

    /**
     * Convert the HSB values to RGB
     * @param hue the hue, between 0 and 1
     * @param sat the saturation, between 0 and 1
     * @param bri the brightness, between 0 and 1
     * @return an array containing the red, green and blue values (in that order) between 0 and 1
     */
    public static float[] hsbToRgb(float hue, float sat, float bri){
        int rgb = java.awt.Color.HSBtoRGB(
                checkValue(hue),
                checkValue(sat),
                checkValue(bri)
        );

        float[] result = new float[]{
                (float) ( (rgb >> 16) & 0xFF ),
                (float) ( (rgb >> 8) & 0xFF ),
                (float) ( rgb & 0xFF )
        };
        result[0] = result[0]/255;
        result[1] = result[1]/255;
        result[2] = result[2]/255;


        return result;
    }

    /**
     * Normalize the value to the range 0 to 1
     * @param value the value to normalize
     * @return if the value is bigger than 1, returns 1
     *          if the value is smaller than 0, returns 0
     *          otherwise, returns the value
     */
    public static float checkValue(float value){
        if(value < 0){
            return 0;
        }
        if(value > 1){
            return 1;
        }
        return value;
    }

    /**
     * Mix an array of colors
     * @param colors the colors to mix
     * @return the color containing the medium RGBA value
     */
    public static Color mix(Color ... colors){
        float red = 0;
        float green = 0;
        float blue = 0;
        float alpha = 0;

        for(int i=0 ; i < colors.length ; i++){
            red += colors[i].red;
            green += colors[i].green;
            blue += colors[i].blue;
            alpha += colors[i].alpha;
        }

        return new Color(red/colors.length, green/colors.length, blue/colors.length, alpha/colors.length);
    }

    /**
     * Mix two colors giving more value to one of the two colors
     * @param color1 the first color
     * @param color2 the second color
     * @param mix the weigh of the first color (from 0 to 1)
     * @return the mixed RGBA color
     */
    public static Color mix(Color color1, Color color2, float mix){
        mix = checkValue(mix);

        Color result = new Color();

        result.setRed(color1.red*mix + color2.red*(1-mix));
        result.setGreen(color1.green*mix + color2.green*(1-mix));
        result.setBlue(color1.blue*mix + color2.blue*(1-mix));
        result.setAlpha(color1.alpha*mix + color2.alpha*(1-mix));

        return result;
    }
}
