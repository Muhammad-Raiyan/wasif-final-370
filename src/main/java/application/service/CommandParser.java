package application.service;

import application.Model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the CommandParser class parses the arguments passed in from the command line.
 * Possible flags:
 * -h on: use hashmap; off: use database
 * -d resource directory
 * -i input file path
 * -o output file path
 * -l log file path
 * -v very data path
 */
public class CommandParser {

    private Map<String, String> options;

    List<String> inputItems;

    public CommandParser() {
        options = new HashMap<>();
        inputItems = new ArrayList<>();
    }

    public void parse(List<String> args) throws IllegalArgumentException {
        for (int i = 0; i < args.size(); i += 2) {
            if (args.get(i).charAt(0) == '-') {
                if(args.get(i).charAt(1) != 'p'){
                    options.put(args.get(i).toLowerCase(), args.get(i + 1));
                } else {
                    options.put(args.get(i).toLowerCase(), args.get(i));
                }
            } else {
                throw new IllegalArgumentException("Not a valid argument " + args.get(i));
            }
        }
        handleQueries();
    }

    private void handleQueries() {
        if(options.containsKey("-i")){
            handleInputFile();
        }

        if(options.containsKey("-o")){
            handleOutputFile();
        }
    }

    private void handleOutputFile() {
        String outputFileName = options.get("-o");
        File outputFile = null;
        try {
            outputFile = new File(outputFileName).getCanonicalFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UrlServices urlServices = UrlServices.getInstance();
        Map<String, List<Product>> inputItemHistory = new HashMap<>();
        Map<String, List<Product>> searchItemHistory = urlServices.getSearchItemHistory();
        for(String item : inputItems){
            if(searchItemHistory.containsKey(item)){
                inputItemHistory.put(item, searchItemHistory.get(item));
            }
        }
        Gson gson = new GsonBuilder().create();
        String outputData = gson.toJson(searchItemHistory);
        /* -Output Dump- */
        System.out.println("Writing data to " + outputFile);
        writeOutput(outputFile, outputData);

    }

    private void handleInputFile() {
        String inputFileName = options.get("-i");
        File inputFile = null;
        try {
            inputFile = new File(inputFileName).getCanonicalFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Reading from file: " + inputFile);
        assert inputFile != null;
        inputItems =  getInputData(inputFile);
        UrlServices urlServices = UrlServices.getInstance();
        for(String item : inputItems){
            urlServices.searchForItem(item);
        }
    }

    public boolean onlyProcess(){
        if(options.containsKey("-p")){
            return true;
        } else {
            return false;
        }
    }

    private static List<String> getInputData(File inputFile){
        if(!inputFile.exists() || !inputFile.isFile()){
            System.out.println("Error: Invalid input file");
            System.exit(0);
        }

        List<String> inputData = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))){
            String st;
            while((st = br.readLine()) != null){
                inputData.add(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputData;
    }

    private static void writeOutput(File outputFile, String data){
        try(FileWriter fileWriter = new FileWriter(outputFile, true)) {
            fileWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Map<String, String> getOptions() {
        return options;
    }

    public List<String> getFlags() {
        return new ArrayList<String>(options.keySet());
    }
}
