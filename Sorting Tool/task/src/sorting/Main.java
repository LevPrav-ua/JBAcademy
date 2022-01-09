package sorting;

import java.util.*;

import static sorting.SortingTools.checkCorrectDataType;
import static sorting.SortingTools.checkCorrectSortingType;

public class Main {
    public static void main(String[] args) {
        String sortingType = "natural";
        String dataType = "long";
        String inputFile = "", outputFile = "";

        boolean proc = true;
        for(int i = 0; i < args.length; i++) {
            try {
                if ("-sortingType".equals(args[i])) {
                    if (i + 1 >= args.length ||
                            !(checkCorrectSortingType(args[i+1]))) {
                        proc = false;
                        throw new Exception("No sorting type defined!");
                    }
                    sortingType = args[i + 1];
                } else if ("-dataType".equals(args[i])) {
                    if (i + 1 >= args.length ||
                            !(checkCorrectDataType(args[i+1]))) {
                        proc = false;
                        throw new Exception("No data type defined!");
                    }
                    dataType = args[i + 1];
                } else if ("-inputFile".equals(args[i])) {
                    inputFile = args[i + 1];
                } else if ("-outputFile".equals(args[i])) {
                    outputFile = args[i + 1];
                } else if (args[i].startsWith("-")) {
                    throw new Exception( args[i] + " is not a valid parameter. It will be skipped.");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        if (proc) {
            SortingTools sortingTools;
            if ("long".equals(dataType)) {
                sortingTools = new SortingTools<Long> (dataType, sortingType,
                        inputFile, outputFile);
            }else{
                sortingTools = new SortingTools<String> (dataType, sortingType,
                        inputFile, outputFile);
            }
            sortingTools.scan();
            sortingTools.sort();
        }
    }
}
