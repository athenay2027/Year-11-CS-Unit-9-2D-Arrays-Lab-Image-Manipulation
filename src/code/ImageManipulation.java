package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    public static void main(String[] args) {
        APImage img = new APImage("cyberpunk2077.jpg");
        //img.draw();
        grayScale("cyberpunk2077.jpg");
        //blackAndWhite("cyberpunk2077.jpg");
        //edgeDetection("cyberpunk2077.jpg", 20);
        //reflectImage("cyberpunk2077.jpg");
        rotateImage("cyberpunk2077.jpg");
    }

    public static void grayScale(String pathOfFile) {
        APImage img = new APImage(pathOfFile);
        int width = img.getWidth();
        int height = img.getHeight();

        for (int i=0; i<width; i++){
            for (int j=0; j<height; j++){
                Pixel pixel = img.getPixel(i,j);
                int av = getAverageColour(pixel);
                pixel.setRed(av);
                pixel.setGreen(av);
                pixel.setBlue(av);
            }
        }
        img.draw();
    }

    private static int getAverageColour(Pixel pixel) {
        return (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
    }

    public static void blackAndWhite(String pathOfFile) {
        APImage img = new APImage(pathOfFile);
        int width = img.getWidth();
        int height = img.getHeight();
        for (int i=0; i<width; i++){
            for (int j=0; j<height; j++){
                Pixel pixel = img.getPixel(i,j);
                int av = getAverageColour(pixel);
                if (av < 128){
                    pixel.setRed(0);
                    pixel.setGreen(0);
                    pixel.setBlue(0);
                }
                else {
                    pixel.setRed(255);
                    pixel.setGreen(255);
                    pixel.setBlue(255);
                }
            }
        }
        img.draw();
    }

    public static void edgeDetection(String pathToFile, int threshold) {
        APImage img = new APImage(pathToFile);
        APImage img2 = new APImage(img.getWidth(), img.getHeight());

        for (int i=1; i<img.getWidth()-1; i++){
            for (int j=0; j<img.getHeight()-1; j++){
                Pixel nowPixel = img.getPixel(i,j);
                Pixel leftPixel = img.getPixel(i-1,j);
                Pixel downPixel = img.getPixel(i,j+1);

                int nowAv = (nowPixel.getRed() + nowPixel.getGreen() + nowPixel.getBlue())/3;
                int leftAv = (leftPixel.getRed() + leftPixel.getGreen() + leftPixel.getBlue())/3;
                int downAv = (downPixel.getRed() + downPixel.getGreen() + downPixel.getBlue())/3;

                if (Math.abs(nowAv-leftAv)>threshold || Math.abs(nowAv-downAv)>threshold){
                    img2.setPixel(i,j,new Pixel(0,0,0));
                }
                else {
                    img2.setPixel(i,j,new Pixel(255,255,255));
                }
            }
        }
        img2.draw();
    }

    public static void reflectImage(String pathToFile) {
        APImage img = new APImage(pathToFile);
        APImage img2 = new APImage(img.getWidth(), img.getHeight());
        for (int i=0; i<img.getWidth()-1; i++){
            for (int j=0; j<img.getHeight()-1; j++){
                Pixel pixel = img.getPixel(i,j);
                img2.setPixel(img.getWidth()-1-i, j, pixel);
            }
        }
        img2.draw();
    }

    public static void rotateImage(String pathToFile) {
        APImage img = new APImage(pathToFile);
        APImage img2 = new APImage(img.getHeight(), img.getWidth());

        for (int i=0; i<img.getWidth(); i++){
            for (int j=0; j<img.getHeight(); j++){
                Pixel pixel = img.getPixel(i,j);
                img2.setPixel(img.getHeight()-1-j, i, pixel);
            }
        }
        img2.draw();
    }
}