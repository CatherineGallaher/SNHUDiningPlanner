package com.example.budgetapprebuild;
import java.io.*;

public class ReadWriteFiles {

    public String[] readFile()
    {
        // The name of the file to open.
        String fileName = "temp.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String[] text = new String[2];
            int i = 0;

            while((line = bufferedReader.readLine()) != null) {
                text[i] = line;
                i++;
            }

            // Always close files.
            bufferedReader.close();
            return text;
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        return null;
    }
}
