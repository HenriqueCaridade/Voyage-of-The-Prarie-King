package com.henrique.image;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyTransparentImage extends MyImage {
    private class Pixel {
        int x, y;
        Pixel(int a, int b){ x = a; y = b; }
        void draw(TextGraphics graphics, int x, int y){
            graphics.setCharacter(this.x + x, this.y + y, ' ');
        }
    }
    Map<TextColor, ArrayList<Pixel>> pixels;
    public MyTransparentImage(){
        pixels = new HashMap<>();
    }

    public MyTransparentImage(String name){
        pixels = new HashMap<>();
        try {
            URL url = MyBasicImage.class.getResource("/images/" + name);
            if(url == null){
                System.out.println("Error: [/images/" + name + "] Resource Not Found.");
                return;
            }
            BufferedImage image = ImageIO.read(url);
            width = image.getWidth();
            height = image.getHeight();
            final int[][] pixelGrid = convert2D(image);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int pixel = pixelGrid[i][j];
                    if (getAlpha(pixel) < 255) continue;
                    TextColor color = pixelColor(pixel);
                    if(pixels.containsKey(color)){
                        pixels.get(color).add(new Pixel(j, i));
                    } else {
                        ArrayList<Pixel> aux = new ArrayList<>();
                        aux.add(new Pixel(j, i));
                        pixels.put(color, aux);
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(TextGraphics graphics, int x, int y){
        for(Map.Entry<TextColor, ArrayList<Pixel>> entry : pixels.entrySet()){
            graphics.setBackgroundColor(entry.getKey());
            for(Pixel p : entry.getValue()) p.draw(graphics, x, y);
        }
    }
}
