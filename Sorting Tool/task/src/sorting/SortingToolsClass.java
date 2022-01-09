package sorting;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.text.CollationElementIterator;
import java.util.*;
import java.util.stream.Collectors;

class SortingTools <Y extends Comparable<Y>>  {
    private final String dataType;
    private final String sortingType;
    private final String inputFile;
    private final String outputFile;
    private ArrayList<Y> input = new ArrayList<>();;

    public SortingTools(String dataType, String sortingType, String inputFile, String outputFile) {
        this.dataType = dataType;
        this.sortingType = sortingType;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    private class Box <T> implements Comparable {
        public final T key;
        public final Integer freak;

        Box(T key, Integer freak) {
            this.key = key;
            this.freak = freak;
        }

        @Override
        public int compareTo(@NotNull Object box) {
            if (!(box instanceof Box)) return -1;

            return freak.compareTo(((Box<T>)box).freak);
        }

    }

    private void byCount() {

        TreeMap<Y, Integer> tmap = new TreeMap<>();
        for (Y in : input) {
            tmap.putIfAbsent(in,0);
            tmap.put(in, tmap.get(in) + 1);
        }
        ArrayList<Box<Y>> arli = new ArrayList<>();
        for ( var entry: tmap.entrySet()) {
            arli.add(new Box<Y>(entry.getKey(), entry.getValue()));
        }

        Collections.sort(arli);
        StringBuilder sb = new StringBuilder("Total numbers: " + input.size() + "\n");
        for ( Box<Y> box : arli) {
            sb.append(box.key).append(": ").append(box.freak).append(" time(s), ")
                    .append((int) (100.0 * box.freak / input.size())).append("%\n");
        }
        output(sb.toString());
    }

    private Scanner getScanner() {
        if (!("".equals(inputFile))) {
            try {
                return new Scanner(new File(inputFile));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return new Scanner(System.in);
    }
    private void output(String data) {
        if ("".equals(outputFile)) {
            System.out.println(data);
        } else {
            try (FileWriter fileWriter = new FileWriter(outputFile)){
                fileWriter.write(data);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public void sort() {
        StringBuilder sb = new StringBuilder();
        if (sortingType.equals("natural")) {
            Collections.sort(input);
            sb.append("Total numbers: ").append(input.size()).append(".\n");
            if ("line".equals(dataType) ) {
                sb.append("Sorted data: \n");
                input.forEach(num -> sb.append(num).append("\n"));
            }else {
                sb.append("Sorted data: ");
                input.forEach(num -> sb.append(num).append(" "));
            }
        } else {
            byCount();
        }
        output(sb.toString());
    }

    public void scan() {
        Scanner scanner = getScanner();
        switch (dataType) {
            case "long": {
                while (scanner.hasNext()) {
                    String lon = scanner.next();
                    try {
                        input.add((Y) Long.valueOf(Long.parseLong(lon)));
                    } catch (Exception ex) {
                        System.out.println(lon + " is not a long. It will be skipped.");
                    }
                }
                break;
            }
            case "line": {
                while (scanner.hasNextLine()) {
                    input.add((Y) scanner.nextLine());
                }
                break;
            }
            case "word": {
                while (scanner.hasNext()) {
                    input.add((Y) scanner.next());
                }
                break;
            }
        }
        scanner.close();
    }

    static boolean checkCorrectDataType(String dType) {
        ArrayList<String> dataTypes = new ArrayList<>(List.of("line", "word", "long"));
        return dataTypes.contains(dType);
    }
    static boolean checkCorrectSortingType(String dType) {
        ArrayList<String> sortingTypes = new ArrayList<>(List.of("natural", "byCount"));
        return sortingTypes.contains(dType);
    }
}
