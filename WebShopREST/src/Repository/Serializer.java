package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.Serializable;

public class Serializer<T extends Serializable> {
    private CharSequence dELIMITER = "|";

    public void ToCSV(String fileName, List<T> objects){
        try {
            FileWriter sw= new FileWriter(fileName);
            BufferedWriter streamWriter = new BufferedWriter(sw);
            for (Serializable obj : objects) {
                String line = String.join(dELIMITER, obj.ToCSV());
                streamWriter.write(line);
                streamWriter.newLine();
            }
            streamWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<List<String>> FromCSV(String filename){
        List<List<String>> objects = new ArrayList<List<String>>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(filename));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String delimiterString ="\\" + dELIMITER.toString();
                List<String> data = Arrays.asList(row.split(delimiterString));
                objects.add(data);
            }
            csvReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return objects;
    }

    public void ToCSVAppend(String fileName, List<T> objects){
        try {
            FileWriter sw= new FileWriter(fileName, true);
            BufferedWriter streamWriter = new BufferedWriter(sw);
            for (Serializable obj : objects) {
                String line = String.join(dELIMITER, obj.ToCSV());
                streamWriter.write(line);
                streamWriter.newLine();
            }
            streamWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
