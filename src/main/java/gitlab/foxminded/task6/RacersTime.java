package gitlab.foxminded.task6;

import java.io.File;
import java.io.FileReader;
import java.io.StreamCorruptedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacersTime {

    public String calculate() throws Exception {

        StringBuilder returnString = new StringBuilder();
        Formatter formatter = new Formatter();
        List<Racer> returnList = new ArrayList<>();

        Stream<String> abbreviationLines = Files.lines(Paths.get("C:\\Users\\alzoz\\IdeaProjects\\task6\\src\\main\\resources\\abbreviations.txt"));
        List<String[]> abbreviationsList = abbreviationLines.map(x -> x.split("_")).collect(Collectors.toList());

        Map<String, String> nameMap = abbreviationsList.stream().collect(Collectors.toMap(x -> x[0], x -> x[1]));
        Map<String, String> carMap = abbreviationsList.stream().collect(Collectors.toMap(x -> x[0], x -> x[2]));


        Stream<String> startLines = Files.lines(Paths.get("C:\\Users\\alzoz\\IdeaProjects\\task6\\src\\main\\resources\\start.log"));
        Map<String, String> startMap = startLines.collect(Collectors.toMap(x -> x.split("_")[0].substring(0,3), x -> x.split("_")[1]));

        Stream<String> endLines = Files.lines(Paths.get("C:\\Users\\alzoz\\IdeaProjects\\task6\\src\\main\\resources\\end.log"));
        Map<String, String> endMap = endLines.collect(Collectors.toMap(x -> x.split("_")[0].substring(0,3), x -> x.split("_")[1]));

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

        returnString.append(formatter.format(returnList.stream().filter(x -> x.getPlace() < 16).collect(Collectors.toList())));
        returnString.append(formatter.getBorder());
        returnString.append(formatter.format(returnList.stream().filter(x -> x.getPlace() >= 16).collect(Collectors.toList())));

        return returnString.toString();
    }


}
