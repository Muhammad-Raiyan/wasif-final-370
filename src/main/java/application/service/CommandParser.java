package application.service;

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

    public CommandParser() {
        options = new HashMap<>();
    }

    public void parse(List<String> args) throws IllegalArgumentException {
        for (int i = 0; i < args.size(); i += 2) {
            if (args.get(i).charAt(0) == '-') {
                options.put(args.get(i).toLowerCase(), args.get(i + 1));
            } else {
                throw new IllegalArgumentException("Not a valid argument " + args.get(i));
            }
        }
    }

    private void handleQueries() {

    }


    public Map<String, String> getOptions() {
        return options;
    }

    public List<String> getFlags() {
        return new ArrayList<String>(options.keySet());
    }
}
