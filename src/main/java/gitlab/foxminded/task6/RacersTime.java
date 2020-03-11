package gitlab.foxminded.task6;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

    public class RacersTime {
    private List<Racer> parseRacer(List<String[]> abbreviations, Map<String,LocalTime> startMap,Map<String,LocalTime> endMap){
       return abbreviations.stream().map(x -> new Racer(x[1], x[2], endMap.get(x[0]).getLong(ChronoField.MILLI_OF_DAY) - startMap.get(x[0]).getLong(ChronoField.MILLI_OF_DAY))).collect(Collectors.toList());
    }

    public List<Racer> getRacers(String abbreviations, String startTime, String endTime)throws Exception {

        List<String[]> abbreviationsList = Files.lines(Paths.get(abbreviations)).map(x -> x.split("_")).collect(Collectors.toList());

        Map<String, LocalTime> startMap = Files.lines(Paths.get(startTime)).collect(Collectors.toMap(x -> x.split("_")[0].substring(0,3), x -> LocalTime.parse(x.split("_")[1])));

        Map<String, LocalTime> endMap = Files.lines(Paths.get(endTime)).collect(Collectors.toMap(x -> x.split("_")[0].substring(0,3),x -> LocalTime.parse(x.split("_")[1])));

        List<Racer> returnList = parseRacer(abbreviationsList, startMap, endMap);

        Collections.sort(returnList);

        for (int i = 0; i < returnList.size(); i++) {
            returnList.get(i).setPlace(i + 1);
        }

        return returnList;
    }


}
