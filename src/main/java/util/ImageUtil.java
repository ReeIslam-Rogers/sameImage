package util;

import models.ImageFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static util.Config.CONFIG;

public class ImageUtil {
    private static int targetWidth=Integer.parseInt(CONFIG.get("resize.width")),
            targetHeight=Integer.parseInt(CONFIG.get("resize.height"));

    private ImageUtil(){}

    public static BufferedImage readImage(File f) throws IOException {
        BufferedImage img = ImageIO.read(f);
        Image resultingImage = img.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        // creates output image
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, img.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();

        return outputImage;
    }

    public static double getPercentageDiff(ImageFile imgF1, ImageFile imgF2){
        long diff = 0;
        BufferedImage img1 = imgF1.getImage();
        BufferedImage img2 = imgF2.getImage();

        for (int y = 0; y < targetHeight; y++) {
            for (int x = 0; x < targetWidth; x++) {
                //Getting the RGB values of a pixel
                int pixel1 = img1.getRGB(x, y);
                Color color1 = new Color(pixel1, true);
                int r1 = color1.getRed();
                int g1 = color1.getGreen();
                int b1 = color1.getBlue();
                int pixel2 = img2.getRGB(x, y);
                Color color2 = new Color(pixel2, true);
                int r2 = color2.getRed();
                int g2 = color2.getGreen();
                int b2 = color2.getBlue();
                //sum of differences of RGB values of the two images
                long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
                diff = diff + data;
            }
        }
        double avg = diff/(targetHeight*targetWidth*3);
        double percentage = (avg/255)*100;

        return percentage;
    }
}
