import gitlab.foxminded.task6.Formatter;
import gitlab.foxminded.task6.Racer;
import gitlab.foxminded.task6.RacersTime;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FormatterTest {
    private  RacersTime RacersTimeSpy = Mockito.spy(RacersTime.class);
    private Formatter formatter = new Formatter();
    private String expectedStandart = "1.Sebastian Vettel      | FERRARI                       | 01:04.415\n" + "------------------------------------------------------------------------\n";
    @Test
    public void standartTest() throws Exception {
        Racer racer = new Racer();
        racer.setPlace(1);
        racer.setName("Sebastian Vettel");
        racer.setCompany("FERRARI");
        racer.setTime(64415);
        List<Racer> list = new ArrayList<>();
        list.add(racer);
        String actual = formatter.format(list);
        assertEquals(expectedStandart, actual);
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNullsPassed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            formatter.format(null);
        });
        final String expectedMessage = "should be list parameter";
        final String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


}
