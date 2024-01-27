package com.henrique.image;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class MyBasicImage extends MyImage {
    BasicTextImage textImage = null;
    public MyBasicImage(){
        textImage = new BasicTextImage(0, 0);
    }

    public MyBasicImage(String name){
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
            textImage = new BasicTextImage(width, height);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int pixel = pixelGrid[i][j];
                    if (getAlpha(pixel) < 255) continue;
                    TextCharacter pixelChar = TextCharacter.fromCharacter(' ', BLACK, pixelColor(pixel))[0];
                    textImage.setCharacterAt(j, i, pixelChar);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public MyBasicImage(String name, TextColor fillColor){
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
            textImage = new BasicTextImage(width, height);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int pixel = pixelGrid[i][j];
                    TextColor color = getAlpha(pixel) < 255 ? fillColor : pixelColor(pixel);
                    TextCharacter pixelChar = TextCharacter.fromCharacter(' ', BLACK, color)[0];
                    textImage.setCharacterAt(j, i, pixelChar);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(TextGraphics graphics, int x, int y){
        graphics.drawImage(new TerminalPosition(x, y), textImage);
    }

    public void addImage(MyBasicImage img, int x, int y){
        BasicTextImage newImage = new BasicTextImage(Math.max(this.width, img.width + x), Math.max(this.height, img.height + y));
        this.textImage.copyTo(newImage, 0, this.height, 0, this.width, 0, 0);
        img.textImage.copyTo(newImage, 0, img.height, 0, img.width, y, x);
        this.textImage = newImage;
        this.width  = this.textImage.getSize().getColumns();
        this.height = this.textImage.getSize().getRows();
    }

    public static MyBasicImage renderFont(String s){
        MyBasicImage res = new MyBasicImage();
        int x = 0;
        for(CharacterIterator it = new StringCharacterIterator(s); it.current() != CharacterIterator.DONE; it.next()){
            MyBasicImage img = new MyBasicImage("font8/" + it.current() + ".png");
            if(img.textImage == null) continue;
            res.addImage(img, x, 0);
            x += img.width + 1;
        }
        return res;
    }
}
