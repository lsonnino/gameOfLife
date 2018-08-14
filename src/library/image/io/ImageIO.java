package library.image.io;

import library.image.lang.Color;
import library.image.lang.Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageIO {
    // ==========
    // === IN ===
    // ==========

    /*public static Image read(String path) throws IllegalArgumentException {
        return new Image(new javafx.scene.image.Image(path));
    }*/
    public static Image read(String path) throws IOException {
        BufferedImage bufferedImage = javax.imageio.ImageIO.read(new File(path));
        Image image = new Image(bufferedImage);
        bufferedImage.flush();

        return image;
    }

    // ===========
    // === OUT ===
    // ===========

    public static void write(Image image, String format, String file) throws IOException {
        write(image, format, new File(file));
    }
    public static void write(Image image, String format, File file) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB
        );

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = image.get(x, y);
                java.awt.Color awtColor = new java.awt.Color(
                        Math.round(color.getRed()*255),
                        Math.round(color.getGreen()*255),
                        Math.round(color.getBlue()*255),
                        Math.round(color.getAlpha()*255)
                );
                bufferedImage.setRGB(x, y, awtColor.getRGB());
            }
        }

        javax.imageio.ImageIO.write(bufferedImage, format, file);

        bufferedImage.flush();
    }
}
