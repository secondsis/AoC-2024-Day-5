import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input.txt"));

        HashMap<Integer, HashSet<Integer>> beforeThis = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> afterThis = new HashMap<>();
        // get the rules
        while(scan.hasNext()) {
            String line = scan.nextLine();
            if(line.isBlank()) {
                break;
            }
            // ##|##
            String[] parts = line.split("\\|");
            int before = Integer.parseInt(parts[0]);
            int after = Integer.parseInt(parts[1]);

            updateMap(beforeThis, after, before);

            updateMap(afterThis, before, after);

//            if(!list.contains(before) && !list.contains(after)) {
//                list.add(before);
//                list.add(after);
//                continue;
//            }
//            if(list.contains(before)) {
//                if(!list.contains(after)) {
//                    updateMap(beforeThis, after, before);
//
//                    updateMap(afterThis, before, after);
//                    list.add(list.indexOf(before) + 1, after);
//                } else {
//
//                }
//            }
        }
        int result = 0;
        // get the updates and find the valid ones
        scannerLoop:
        while(scan.hasNext()) {
            String line = scan.nextLine();
            String[] parts = line.split(",");
            ArrayList<Integer> intList = new ArrayList<>();
            for(String part : parts) {
                intList.add(Integer.parseInt(part));
            }

            // is intList valid?
            for (int i = 0; i < intList.size(); i++) {
                // check if each int doesn't have any "before" numbers after it
                int num = intList.get(i);
                for (int j = i + 1; j < intList.size(); j++) {
                    int questionNum = intList.get(j);
                    if(beforeThis.containsKey(num) && beforeThis.get(num).contains(questionNum)) {
                        continue scannerLoop;
                    }
                }
                // works for that num
            }
            // should be valid!
            // add the mid number to the sum
            System.out.println((int) Math.floor(intList.size() / 2.0));
            result += intList.get((int) Math.floor(intList.size() / 2.0));
        }
        System.out.println(result);
    }

    public static void updateMap(HashMap<Integer, HashSet<Integer>> map, int key, int newValue) {
        HashSet<Integer> list = map.getOrDefault(key, new HashSet<>());
        list.add(newValue);
        map.put(key, list);
    }
}