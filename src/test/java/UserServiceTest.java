
import onlinebank.dao.UserDAO;
import onlinebank.models.Sex;
import onlinebank.models.User;
import onlinebank.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIndex() {
        // Arrange
        List<User> expectedUsers = Arrays.asList(
                new User("Tom", "Baker", LocalDate.of(1996, 8, 22), Sex.M, 5895256, new ArrayList<>(), new ArrayList<>()),
                new User("Bob", "Taylor", LocalDate.of(1991, 5, 13), Sex.M, 5425270, new ArrayList<>(), new ArrayList<>()),
                new User("Mike", "Smith", LocalDate.of(1989, 2, 18), Sex.M, 1225381, new ArrayList<>(), new ArrayList<>()),
                new User("Katy", "Butcher", LocalDate.of(1990, 11, 7), Sex.F, 8629943, new ArrayList<>(), new ArrayList<>())
        );

        when(userDAO.getAllUsers()).thenReturn(expectedUsers);

        // Act
        List<User> actualUsers = userService.getAllUsers();

        // Assert
        assertEquals(expectedUsers, actualUsers, "The list of users should match the expected list.");
    }
}
