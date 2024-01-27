package com.henrique.csvReader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVReaderTester {
    @Test
    void CSVReaderTest(){
        String[][] aux = CSVReader.readResource("/csv/test.csv");
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                assertEquals(Integer.toString(i + j + 1), aux[i][j]);
    }

    @Test
    void CSVReaderFileNotFoundTest(){
        String[][] aux = CSVReader.readResource("/csv/nonexistentFile.csv");
        assertNull(aux);
    }

    @Test
    void CSVReaderEmptyFileTest(){
        String[][] aux = CSVReader.readResource("/csv/emptyFile.csv");
        assertNotNull(aux);
        assertEquals(0, aux.length);
    }
}
