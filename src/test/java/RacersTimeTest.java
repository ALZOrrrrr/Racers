import gitlab.foxminded.task6.Racer;
import gitlab.foxminded.task6.RacersTime;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RacersTimeTest {
    private Racer expectedStandart = new Racer();
    private RacersTime racersTime = new RacersTime();
    @Test
    public void standartTest() throws Exception{
        List<Racer> actual = racersTime.getRacers("abbreviations.txt", "start.log", "end.log");
        assertEquals(expectedStandart, actual.get(0));
    }
}
