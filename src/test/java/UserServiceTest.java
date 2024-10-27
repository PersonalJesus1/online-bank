import onlinebank.dao.UserDAO;
import onlinebank.models.User;
import onlinebank.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("John", "Doe", LocalDate.of(1985, 5, 15), "M", 123456, null, null);
    }

    @Test
    void getAllUsersTest() {
        List<User> users = Arrays.asList(user);
        when(userDAO.getAllUsers()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
        verify(userDAO, times(1)).getAllUsers();
    }

    @Test
    void saveUserTest() {
        userService.save(user);
        verify(userDAO, times(1)).save(user);
    }

    @Test
    void updateUserTest() {
        userService.update(123456, user);
        verify(userDAO, times(1)).update(123456, user);
    }

    @Test
    void showUserTest() {
        when(userDAO.show(123456)).thenReturn(user);

        User result = userService.show(123456);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(userDAO, times(1)).show(123456);
    }

    @Test
    void deleteUserTest() {
        userService.delete(123456);
        verify(userDAO, times(1)).delete(123456);
    }
}
