package com.henrique.csvReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVReader {
    public static final String delimiter = ",";
    public static String[][] readResource(String resourcePath) {
        try {
            String[][] out;
            URL url = CSVReader.class.getResource(resourcePath);
            if(url == null){
                System.out.println("Error: [" + resourcePath + "] Resource Not Found.");
                return null;
            }
            Path p = Paths.get(url.toURI());
            int lines = (int) Files.lines(p).count();
            BufferedReader in = new BufferedReader(new FileReader(p.toFile()));
            String line = "";
            String[] temp;
            if ((line = in.readLine()) == null){
                return new String[0][0];
            }
            temp = line.split(delimiter);
            int columns = temp.length;
            out = new String[lines][columns];
            out[0] = temp;
            for(int i = 1; (line = in.readLine()) != null; i++){
                temp = line.split(delimiter);
                out[i] = temp;
            }
            return out;
        } catch (URISyntaxException | IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
