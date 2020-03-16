import gitlab.foxminded.task6.Racer;
import gitlab.foxminded.task6.RacersTime;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RacersTimeTest {
    private Racer expectedStandart = new Racer();
    private RacersTime racersTime = new RacersTime();
    @Test
    public void standartTest() {
        List<Racer> actual = racersTime.getRacers("abbreviations.txt", "start.log", "end.log");
        assertEquals(expectedStandart, actual.get(0));
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNullsPassed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            racersTime.getRacers(null, null, null);
        });
        final String expectedMessage = "null transferred";
        final String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void fileAbbreviationsNotFoundTest() {
        List actual = racersTime.getRacers("a", "start.log", "end.log");
        assertEquals(Collections.emptyList(), actual);
    }
    @Test
    public void fileStartTimeNotFoundTest() {
        List actual = racersTime.getRacers("abbreviations.txt", "a", "end.log");
        assertEquals(Collections.emptyList(), actual);
    }
    @Test
    public void fileNotFoundTest() {
        List actual = racersTime.getRacers("abbreviations.txt", "start.log", "a");
        assertEquals(Collections.emptyList(), actual);
    }
}

