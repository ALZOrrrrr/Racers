import gitlab.foxminded.task6.Racer;
import gitlab.foxminded.task6.RacersTime;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RacersTimeTest {
    private Racer racer = new Racer();
    private RacersTime racersTime = new RacersTime();
    @Test
    public void standartTest() throws Exception{
        racer.setPlace(1);
        racer.setName("Sebastian Vettel");
        racer.setCompany("FERRARI");
        racer.setTime(64415);
        List<Racer> list= racersTime.calculate();

        Racer actual = list.get(0);
        assertEquals(racer.getTime(), actual.getTime());
        assertEquals(racer.getCompany(), actual.getCompany());
        assertEquals(racer.getPlace(), actual.getPlace());
        assertEquals(racer.getName(), actual.getName());
    }
}
