package gitlab.foxminded.task6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;


    public class RacersTime {
        private static Map<String, LocalTime> getRacesTime(String path){
            Map<String, LocalTime> resultMap = new HashMap<>();
            try {
                Files.lines(Paths.get(path))
                        .forEach(x -> resultMap.put(x.substring(0,3),LocalTime.parse(x.split("_")[1])));
            } catch (IOException e) {
                System.err.println(e);
            }
            return resultMap;
        }

    public List<Racer> getRacers(String abbreviations, String startTime, String endTime)throws Exception {

        Map<String, LocalTime> startMap = getRacesTime(startTime);

        Map<String, LocalTime> endMap = getRacesTime(endTime);

        return Files.lines(Paths.get(abbreviations))
                .map(x -> x.split("_"))
                .map(x -> new Racer(x[1], x[2], endMap.get(x[0]).getLong(ChronoField.MILLI_OF_DAY) - startMap.get(x[0])
                .getLong(ChronoField.MILLI_OF_DAY)))
                .collect(Collectors.toList());
    }
}
