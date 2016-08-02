import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.ClassRule;
import static org.junit.Assert.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import com.etherfuse.server.db.DeviceDAO;
import com.etherfuse.server.core.Device;
import com.etherfuse.server.resources.DeviceResource;
import java.sql.Timestamp;

public class DeviceResourceTest {

    private static final DeviceDAO dao = mock(DeviceDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new DeviceResource(dao)).build();

    final Device device = new Device("80:80:80:80:80", 3232235777L, 0, Timestamp.valueOf("2016-07-04 00:00:00"), Timestamp.valueOf("2016-07-07 00:00:00"), "none", "temp", "Linux", 0, "Pumphouse");

    @Before
    public void setup() {
        when(dao.findNameByMAC(eq("80:80:80:80:80"))).thenReturn(device);
        when(dao.findNameByHostname(eq("Pumphouse"))).thenReturn(device);
        when(dao.findNameByIPAddress(eq("3232235777"))).thenReturn(device);
    }

    @After
    public void tearDown(){
        reset(dao);
    }

    @Test
    public void testGetDeviceByHost() {
        assertThat(resources.client().target("/device/hostname/Pumphouse").request().get(Device.class)).isEqualTo(device);
        verify(dao).findNameByHostname("Pumphouse");
    }

    @Test
    public void testGetDeviceByMac() {
        assertThat(resources.client().target("/device/mac/80:80:80:80:80").request().get(Device.class)).isEqualTo(device);
        verify(dao).findNameByMAC("80:80:80:80:80");
    }

    @Test
    public void testGetDeviceByIP() {
        assertThat(resources.client().target("/device/ip/3232235777").request().get(Device.class)).isEqualTo(device);
        verify(dao).findNameByIPAddress("3232235777");
    }
            
}