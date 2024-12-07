import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static HashMap<Integer, HashSet<Integer>> beforeThis = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input.txt"));

        HashMap<Integer, HashSet<Integer>> afterThis = new HashMap<>();

        HashMap<Integer, Integer> strengthValue = new HashMap<>();

        int min = Integer.MAX_VALUE; // get edge case
        int max = Integer.MIN_VALUE;

        // get the rules
        while (scan.hasNext()) {
            String line = scan.nextLine();
            if (line.isBlank()) {
                break;
            }
            // ##|##
            String[] parts = line.split("\\|");
            int before = Integer.parseInt(parts[0]);
            int after = Integer.parseInt(parts[1]);

            if (after < min) {
                min = after;
            }

            if (before > max) {
                max = before;
            }

            updateMap(beforeThis, after, before);

            updateMap(afterThis, before, after);

        }

        for (Integer i : beforeThis.keySet()) {
            strengthValue.put(i, beforeThis.get(i).size());
        }

        strengthValue.put(min, 0); // add in that edge case

     //   findStrength(beforeThis, strengthValue, max);

        int result = 0;

        ArrayList<ArrayList<NewInteger>> listsToFix = new ArrayList<>();

        // get the updates
        // correct the invalid ones
        scannerLoop: while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] parts = line.split(",");
            ArrayList<NewInteger> intList = new ArrayList<>();
            for (String part : parts) {
                intList.add(new NewInteger(Integer.parseInt(part),
                        strengthValue.getOrDefault(Integer.parseInt(part), 99999)));
            }

            // is intList valid?
            for (int i = 0; i < intList.size(); i++) {
                // check if each int doesn't have any "before" numbers after it
                int num = intList.get(i).getValue();
                for (int j = i + 1; j < intList.size(); j++) {
                    int questionNum = intList.get(j).getValue();
                    if (beforeThis.containsKey(num) && beforeThis.get(num).contains(questionNum)) {
                        // swap(intList, num, questionNum, strengthValue);
                        listsToFix.add(intList);
                        continue scannerLoop;
                    }
                }
                // works for that num
            }
        }

        for (ArrayList<NewInteger> list : listsToFix) {
            list.sort(null);
            result += list.get((int) Math.floor(list.size() / 2.0)).getValue();
            System.out.println((int) Math.floor(list.size() / 2.0));
        }

        System.out.println(listsToFix);
        System.out.println(result);
        // System.out.println(beforeThis);
        System.out.println(strengthValue);
    }

    public static void findStrength(HashMap<Integer, HashSet<Integer>> beforeThis,
                                    HashMap<Integer, Integer> strengthValue, int i) {
        // base case
        if(beforeThis.getOrDefault(i, new HashSet<Integer>()).isEmpty()) {
            return;
        }

        // change per level
        for (Integer j : beforeThis.getOrDefault(i, new HashSet<Integer>())) {
            // for every value in the list of befores
            // if every before of that is not already included within the before list
            for(Integer k : beforeThis.getOrDefault(j, new HashSet<Integer>())) {
                if (!beforeThis.getOrDefault(i, new HashSet<Integer>()).contains(k)) {
                    HashSet<Integer> newSet = beforeThis.getOrDefault(i, new HashSet<>());
                    newSet.add(k);
                    beforeThis.put(i, newSet);
                }
            }
        }

        //findStrength(beforeThis, strengthValue, j);
        System.out.println("what");
    }

    public static void swap(ArrayList<NewInteger> list, int value1, int value2, HashMap<Integer, Integer> strengths) {
        NewInteger newValue1 = new NewInteger(value1, strengths.getOrDefault(value1, 99999));

        NewInteger newValue2 = new NewInteger(value2, strengths.get(value2));

        // int helper = list.get(list.indexOf(newValue1)).getValue();
        int index1 = list.indexOf(newValue1);

        list.remove(newValue1);
        list.add(index1, newValue2);
        int index2 = list.indexOf(newValue2);
        list.remove(new NewInteger(value2, strengths.get(value2)));
        list.add(index2, new NewInteger(value1, strengths.getOrDefault(value1, 99999)));
    }

    public static boolean checkValidity(ArrayList<NewInteger> intList, HashMap<Integer, HashSet<Integer>> beforeThis) {
        for (int i = 0; i < intList.size(); i++) {
            // check if each int doesn't have any "before" numbers after it
            int num = intList.get(i).getValue();
            for (int j = i + 1; j < intList.size(); j++) {
                int questionNum = intList.get(j).getValue();
                if (beforeThis.containsKey(num) && beforeThis.get(num).contains(questionNum)) {
                    return false;
                }
            }
            // works for that num
        }
        return true;
    }

    public static void updateMap(HashMap<Integer, HashSet<Integer>> map, int key, int newValue) {
        HashSet<Integer> list = map.getOrDefault(key, new HashSet<>());
        list.add(newValue);
        map.put(key, list);
    }
}