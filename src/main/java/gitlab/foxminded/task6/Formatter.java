package gitlab.foxminded.task6;

import java.util.List;

public class Formatter {

    private String SPACE = " ";


    private String adddSpaces(int index){
        String returnString="";
        for(int i = 0; i <index; i++){
            returnString += SPACE;
        }
        return returnString;
    }

    public String format(List<String[]> list){
         StringBuilder returnString = new StringBuilder();

        for(int i = 0; i < 15; i++) {
            returnString.append(list.get(i)[0] + "." + list.get(i)[1] + adddSpaces(22-list.get(i)[1].length()) + "|" + SPACE + list.get(i)[2] + adddSpaces(30 - list.get(i)[2].length()) + "|" + SPACE + list.get(i)[3] + "\n");
        }
        for(int i = 15; i<list.size(); i++){
            returnString.append(list.get(i)[0] + "." + list.get(i)[1] + adddSpaces(22-list.get(i)[1].length()) + "|" + SPACE + list.get(i)[2] + adddSpaces(30 - list.get(i)[2].length()) + "|" + SPACE + list.get(i)[3] + "\n");
        }



         return returnString.toString();

    }








}
