import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.ClassRule;
import static org.junit.Assert.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import com.etherfuse.server.db.ProfileDAO;
import com.etherfuse.server.core.Profile;
import com.etherfuse.server.resources.ProfileResource;

public class ProfileResourceTest {

    private static final ProfileDAO dao = mock(ProfileDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new ProfileResource(dao)).build();

    final Profile profile = new Profile(1, "David", "Taylor", "dave@etherfuse.com", false);

    @Before
    public void setup() {
        when(dao.findProfileByID(eq(1))).thenReturn(profile);
    }

    @After
    public void tearDown(){
        reset(dao);
    }

    @Test
    public void testGetPerson() {
        assertThat(resources.client().target("/profile/1").request().get(Profile.class)).isEqualTo(profile);
        verify(dao).findProfileByID(1);
    }
}