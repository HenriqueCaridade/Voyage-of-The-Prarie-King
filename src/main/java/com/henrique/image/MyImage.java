package com.henrique.image;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

abstract public class MyImage {
    static final TextColor BLACK = TextColor.Factory.fromString("#000000");
    private static final String[] hexMap = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    // final int[][] pixelGrid;
    protected int width, height;
    protected MyImage(){
        width = height = 0;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    abstract public void draw(TextGraphics graphics, int x, int y);

    static int getAlpha(int pixel){
        return ((pixel >>> 24) & 0xff);
    }
    static TextColor pixelColor(int pixel){
        StringBuilder res = new StringBuilder("");
        for(int i = 0; i < 6; i++){
            res.append(hexMap[pixel & 0xf]);
            pixel >>>= 4;
        }
        return TextColor.Factory.fromString("#" + res.reverse().toString());
    }

    static int[][] convert2D(BufferedImage image){
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel + 3 < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }
        return result;
    }
}
