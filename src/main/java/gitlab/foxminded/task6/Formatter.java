package gitlab.foxminded.task6;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Formatter {
    private int place;

    private String SPACE = " ";

    private int getPlace(){
        return place++;
    }
    private void validate(List a) {
        if (a == null) {
            throw new IllegalArgumentException("should be list parameter");
        }
    }
    private String getBorder() {
        return ("------------------------------------------------------------------------\n");
    }
    private String addSpaces(int index) {
        String returnString = "";
        for (int i = 0; i < index; i++) {
            returnString += SPACE;
        }
        return returnString;
    }
    public String format(List<Racer> list) {
        place = 1;
        validate(list);
        StringBuilder returnString = new StringBuilder();
        list.stream().sorted().forEach(x -> {
            if(x.getPlace()==16){
            returnString.append(getBorder());
            }
            returnString.append(getPlace() + "." +
                                x.getName() + addSpaces(22 - x.getName().length()) + "|" + SPACE +
                                x.getCompany() + addSpaces(30 - x.getCompany().length()) + "|" + SPACE);
                                returnString.append(String.format("%02d:%02d.%d", (x.getTime() / (1000 * 60)) % 60, (x.getTime() / 1000) % 60, x.getTime() % 1000) + "\n");
        });
        return returnString.toString();
    }


}
