import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.etherfuse.server.core.Device;
import java.sql.Timestamp;
import java.util.TimeZone;

public class DeviceTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    

    @Test
    public void serializesToJSON() throws Exception {

        MAPPER.setTimeZone(TimeZone.getDefault());
        final Device device = new Device("80:80:80:80:80", 3232235777L, 0, Timestamp.valueOf("2016-07-04 00:00:00"), Timestamp.valueOf("2016-07-07 00:00:00"), "none", "temp", "Linux", 0, "Pumphouse");

        final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/device.json"), Device.class));

        assertThat(MAPPER.writeValueAsString(device)).isEqualTo(expected);
    } 

    @Test
    public void deserializesFromJSON() throws Exception {
        final Device device = new Device("80:80:80:80:80", 3232235777L, 0, Timestamp.valueOf("2016-07-04 00:00:00"), Timestamp.valueOf("2016-07-07 00:00:00"), "none", "temp", "Linux", 0, "Pumphouse");
        assertThat(MAPPER.readValue(fixture("fixtures/device.json"), Device.class)).isEqualTo(device);
    }       
}