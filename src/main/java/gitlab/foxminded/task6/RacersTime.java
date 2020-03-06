package gitlab.foxminded.task6;

import java.io.File;
import java.io.FileReader;
import java.io.StreamCorruptedException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacersTime {

    public String calculate() throws Exception {
        StringBuilder returnString = new StringBuilder();
        Formatter formatter = new Formatter();

        File abbreviationsFile = new File("C:\\Users\\alzoz\\IdeaProjects\\task6\\src\\main\\resources\\abbreviations.txt");
        File startFile = new File("C:\\Users\\alzoz\\IdeaProjects\\task6\\src\\main\\resources\\start.log");
        File endFile = new File("C:\\Users\\alzoz\\IdeaProjects\\task6\\src\\main\\resources\\end.log");

        FileReader fr = new FileReader(abbreviationsFile);
        Scanner sc = new Scanner(fr);

        List<Racer> returnList = new ArrayList<>();

        List<String[]> abbreviationsList = new ArrayList<>();
        while (sc.hasNextLine()) {
            abbreviationsList.add(sc.nextLine().split("_"));
        }
        Map<String, String> nameMap = new HashMap<>();
        Map<String, String> carMap = new HashMap<>();
        for (String[] a : abbreviationsList) {
            nameMap.put(a[0], a[1]);
            carMap.put(a[0], a[2]);
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
        for (String[] a : abbreviationsList) {

            Racer racer = new Racer();
            String abbreviation = a[0];
            String startTime = startMap.get(abbreviation);
            String endTime = endMap.get(abbreviation);

            String[] startInts = startTime.split(":");
            int minStart = Integer.parseInt(startInts[1]);
            String[] minsAndSecondsStart = startInts[2].split("\\.");
            int secondsStart = Integer.parseInt(minsAndSecondsStart[0]);
            int miliSecondsStart = Integer.parseInt(minsAndSecondsStart[1]);
            long finalStartMiliseconds = minStart * 60000 + secondsStart * 1000 + miliSecondsStart;

            String[] endInts = endTime.split(":");
            int minEnd = Integer.parseInt(endInts[1]);
            String[] minsAndSecondsEnd = endInts[2].split("\\.");
            int secondsEnd = Integer.parseInt(minsAndSecondsEnd[0]);
            int miliSecondsEnd = Integer.parseInt(minsAndSecondsEnd[1]);
            long finalEndMiliseconds = minEnd * 60000 + secondsEnd * 1000 + miliSecondsEnd;

            racer.setName(nameMap.get(abbreviation));
            racer.setCompany(carMap.get(abbreviation));
            racer.setTime(finalEndMiliseconds - finalStartMiliseconds);

            returnList.add(racer);
        }
        Collections.sort(returnList);
        for (int i = 0; i < returnList.size(); i++) {
            returnList.get(i).setPlace(i + 1);
        }
        sc.close();
        returnString.append(formatter.format(returnList.stream().filter(x -> x.getPlace() < 16).collect(Collectors.toList())));
        returnString.append(formatter.getBorder());
        returnString.append(formatter.format(returnList.stream().filter(x -> x.getPlace() >= 16).collect(Collectors.toList())));
        int a = 0;
        return returnString.toString();
    }


}
