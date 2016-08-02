import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.ClassRule;
import static org.junit.Assert.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import com.etherfuse.server.db.EventDAO;
import com.etherfuse.server.core.Event;
import com.etherfuse.server.resources.EventResource;
import java.sql.Timestamp;
import java.util.ArrayList;

public class EventResourceTest {

    private static final EventDAO dao = mock(EventDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new EventResource(dao)).build();

    final Event event = new Event(1, 2886926339L, 2886926339L, "Dave", "Taylor", "Pumphouse", new Timestamp(1469582113000L), 1L, 69L);

    @Before
    public void setup() {
        when(dao.findEventByID(eq(1L), eq(69L))).thenReturn(event);

    }

    @After
    public void tearDown(){
        reset(dao);
    }

    @Test
    public void testGeteventByHost() {

        assertThat(resources.client().target("/event/1/69").request().get(Event.class)).isEqualTo(event);
        verify(dao).findEventByID(1L, 69L);
    }
            
}