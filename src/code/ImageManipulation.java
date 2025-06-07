package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage something = new APImage("cyberpunk2077.jpg");
        something.draw();

        grayScale("cyberpunk2077.jpg");
        blackAndWhite("cyberpunk2077.jpg");
        edgeDetection("cyberpunk2077.jpg", 20);
        reflectImage("cyberpunk2077.jpg");
        rotateImage("cyberpunk2077.jpg");
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage something = new APImage(pathOfFile);
        APImage grayscale = something.clone();

        for (int x = 0; x < something.getWidth(); x++) {
            for (int y = 0; y < something.getHeight(); y++) {
                Pixel curr = something.getPixel(x, y);
                int gray_val = getAverageColour(curr);
                grayscale.setPixel(x, y, new Pixel(gray_val, gray_val, gray_val));
            }
        }

        grayscale.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        return (pixel.getBlue() + pixel.getRed() + pixel.getGreen()) / 3;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage something = new APImage(pathOfFile);
        APImage bw = something.clone();

        for (int x = 0; x < something.getWidth(); x++) {
            for (int y = 0; y < something.getHeight(); y++) {
                Pixel curr = something.getPixel(x, y);
                int avg = getAverageColour(curr);
                if (avg < 128) {
                    bw.setPixel(x, y, new Pixel(0, 0, 0));
                } else {
                    bw.setPixel(x, y, new Pixel(255, 255, 255));
                }
            }
        }

        bw.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage something = new APImage(pathToFile);
        APImage edge = something.clone();
        int left, avg, bottom;

        for (int x = 0; x < something.getWidth(); x++) {
            for (int y = 0; y < something.getHeight(); y++) {
                avg = getAverageColour(something.getPixel(x, y));

                left = 0;
                if (x > 0) {
                    left = getAverageColour(something.getPixel(x - 1, y));
                }

                bottom = 0;
                if (y < something.getHeight() - 1) {
                    bottom = getAverageColour(something.getPixel(x, y + 1));
                }

                if (Math.abs(avg - left) > threshold || Math.abs(avg - bottom) > threshold) {
                    edge.setPixel(x, y, new Pixel(0, 0, 0));
                } else {
                    edge.setPixel(x, y, new Pixel(255, 255, 255));
                }
            }

            edge.draw();
        }
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage something = new APImage(pathToFile);
        APImage reflected = something.clone();
        Pixel curr;

        for (int x = 0; x < something.getWidth(); x++) {
            for (int y = 0; y < something.getHeight(); y++) {
                curr = something.getPixel(x, y);
                reflected.setPixel(something.getWidth() - x - 1, y, curr);
            }
        }

        reflected.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage something = new APImage(pathToFile);
        APImage rotated = new APImage(something.getHeight(), something.getWidth());
        Pixel curr;

        for (int x = 0; x < something.getWidth(); x++) {
            for (int y = 0; y < something.getHeight(); y++) {
                curr = something.getPixel(x, y);
                rotated.setPixel(rotated.getWidth() - y - 1, x, curr);
            }
        }

        rotated.draw();
    }

}
