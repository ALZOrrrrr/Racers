package gitlab.foxminded.task6;

public class Main {
    public static void main(String[] args) throws Exception {
        RacersTime r = new RacersTime();
        Formatter f = new Formatter();
        System.out.println(f.format(r.calculate()));
    }
}
