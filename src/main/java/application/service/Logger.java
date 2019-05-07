package application.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
    private static Logger loggingService = null;
    private FileWriter fileWriter;
    private String logFileName = "TransactionLog.txt";
    private File logFile = null;
    private Logger(){
        try {
            logFile = new File(logFileName).getCanonicalFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance(){
        if(loggingService == null){
            loggingService = new Logger();
        }
        return loggingService;
    }

    public void log(String actionType, String data){
        String output = "LOG: Action: " + actionType + " Timestamp: " + new Date() + " Item: " + data;
        try {
            fileWriter = new FileWriter(logFile, true);
            fileWriter.write(output);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeLogger(){
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
