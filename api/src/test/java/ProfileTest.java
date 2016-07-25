import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.etherfuse.server.core.Profile;
import java.util.TimeZone;


public class ProfileTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();


    @Test
    public void serializesToJSON() throws Exception {
        
        MAPPER.setTimeZone(TimeZone.getDefault());
        final Profile profile = new Profile(1, "David", "Taylor", "dave@etherfuse.com", false);

        final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/profile.json"), Profile.class));

        assertThat(MAPPER.writeValueAsString(profile)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Profile profile = new Profile(1, "David", "Taylor", "dave@etherfuse.com", false);
        assertThat(MAPPER.readValue(fixture("fixtures/profile.json"), Profile.class)).isEqualTo(profile);
    }  
}