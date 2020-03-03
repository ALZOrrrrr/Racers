package gitlab.foxminded.task6;

import java.io.File;
import java.io.FileReader;
import java.util.*;

public class RacersTime {











    private void sort(List<String[]> list){



    }

    public List<String[]> calculate() throws Exception {


        File abbreviationsFile = new File("C:\\Users\\alzoz\\IdeaProjects\\task6\\src\\main\\resources\\abbreviations.txt");
        File startFile = new File("C:\\Users\\alzoz\\IdeaProjects\\task6\\src\\main\\resources\\start.log");
        File endFile = new File("C:\\Users\\alzoz\\IdeaProjects\\task6\\src\\main\\resources\\end.log");

        FileReader fr = new FileReader(abbreviationsFile);
        Scanner sc = new Scanner(fr);

        List<String[]> returnList = new ArrayList<>();

        List<String[]> abbreviationsList = new ArrayList<>();
        while (sc.hasNextLine()) {
            abbreviationsList.add(sc.nextLine().split("_"));
        }
        Map<String, String> nameMap = new HashMap<>();
        Map<String, String> carMap = new HashMap<>();

        for(String[] a : abbreviationsList){
            nameMap.put(a[0],a[1]);
            carMap.put(a[0],a[2]);
        }
        sc.close();

        fr = new FileReader(startFile);
        sc = new Scanner(fr);
        List<String[]> startList = new ArrayList<>();
        while (sc.hasNextLine()) {
            startList.add(sc.nextLine().split("_"));
        }
        sc.close();

        fr = new FileReader(endFile);
        sc = new Scanner(fr);

        List<String[]> endList = new ArrayList<>();
        while (sc.hasNextLine()) {
            endList.add(sc.nextLine().split("_"));
        }


        Map<String, String> startMap = new HashMap<>();
        Map<String, String> endMap = new HashMap<>();

        for (int i = 0; i < startList.size(); i++) {
            String[] start = startList.get(i);
            String[] end = endList.get(i);
            start[0] = start[0].substring(0, 3);
            end[0] = end[0].substring(0, 3);
            startMap.put(start[0], start[1]);
            endMap.put(end[0], end[1]);
        }
        int iterator = 1;
        for(String[] a : abbreviationsList){

            String abbreviation = a[0];
            String startTime = startMap.get(abbreviation);
            String endTime = endMap.get(abbreviation);

            String[] startInts = startTime.split(":");
            int minStart = Integer.parseInt(startInts[1]);
            Float secondsStart = Float.parseFloat(startInts[2]);

            String[] endInts = endTime.split(":");
            int minEnd = Integer.parseInt(endInts[1]);
            Float secondsEnd = Float.parseFloat(endInts[2]);

            if(secondsEnd<secondsStart){
                minEnd--;
                secondsEnd += 60;
            }

            String trueTime = minEnd - minStart + ":" + (secondsEnd - secondsStart);

            String[] returnString = {Integer.toString(iterator), nameMap.get(abbreviation), carMap.get(abbreviation), trueTime, abbreviation};

            returnList.add(returnString);

            iterator++;


        }

          sc.close();


        return returnList;
    }


}
