package gitlab.foxminded.task6;

import java.util.List;
import java.util.stream.Collectors;

public class Formatter {

    private String SPACE = " ";

    private void validate(List a) {
        if (a == null) {
            throw new IllegalArgumentException("should be list parameter");
        }
    }

    private String getBorder() {
        return ("------------------------------------------------------------------------\n");
    }

    private String adddSpaces(int index) {
        String returnString = "";
        for (int i = 0; i < index; i++) {
            returnString += SPACE;
        }
        return returnString;
    }

    private String addRacers(List<Racer> list){
        StringBuilder returnString = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            int spaceIndex = 22;
            if (list.get(i).getPlace() > 9) {
                spaceIndex--;
            }
            returnString.append(list.get(i).getPlace() + "." + list.get(i).getName() + adddSpaces(spaceIndex - list.get(i).getName().length()) + "|" + SPACE + list.get(i).getCompany() + adddSpaces(30 - list.get(i).getCompany().length()) + "|" + SPACE);

            long durationInMillis = list.get(i).getTime();
            long millis = durationInMillis % 1000;
            long second = (durationInMillis / 1000) % 60;
            long minute = (durationInMillis / (1000 * 60)) % 60;

            returnString.append(String.format("%02d:%02d.%d", minute, second, millis) + "\n");
        }

        return returnString.toString();





    }

    public String format(List<Racer> list) {
        validate(list);
        StringBuilder returnString = new StringBuilder();
         returnString.append(addRacers(list.stream().filter(x -> x.getPlace() < 16).collect(Collectors.toList())));
         returnString.append(getBorder());
        returnString.append(addRacers(list.stream().filter(x -> x.getPlace() >= 16).collect(Collectors.toList())));
        return returnString.toString();
    }


}
