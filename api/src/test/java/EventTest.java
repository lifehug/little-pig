import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.etherfuse.server.core.Event;
import java.sql.Timestamp;
import java.util.TimeZone;

public class EventTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    

    @Test
    public void serializesToJSON() throws Exception {

        MAPPER.setTimeZone(TimeZone.getDefault());
        final Event event = new Event(1, 2886926339L, 2886926339L, "Dave", "Taylor", "Pumphouse", new Timestamp(1469582113000L), 1L, 69L);
        final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/event.json"), Event.class));
        assertThat(MAPPER.writeValueAsString(event)).isEqualTo(expected);
    } 

    @Test
    public void deserializesFromJSON() throws Exception {
        final Event event = new Event(1, 2886926339L, 2886926339L, "Dave", "Taylor", "Pumphouse", new Timestamp(1469582113000L), 1L, 69L);
        assertThat(MAPPER.readValue(fixture("fixtures/event.json"), Event.class)).isEqualTo(event);
    }       
}