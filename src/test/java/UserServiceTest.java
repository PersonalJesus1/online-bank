import onlinebank.dao.UserDAO;
import onlinebank.models.*;
import onlinebank.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    private User user;
    private List<User> userList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        user = new User();
        user.setPassportNumber(12345);
        user.setName("John");
        user.setSurname("Doe");
        user.setDateOfBirth(LocalDate.of(1980, 1, 1));
        user.setSex("M");

        userList = Arrays.asList(user);
    }

    @Test
    void testGetAllUsers() {
        when(userDAO.getAllUsers()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(user, result.get(0));

        verify(userDAO, times(1)).getAllUsers();
    }

    @Test
    void testSaveUser() {
        doNothing().when(userDAO).save(user);

        userService.save(user);

        verify(userDAO, times(1)).save(user);
    }

    @Test
    void testShowUser() {
        when(userDAO.show(user.getPassportNumber())).thenReturn(user);

        User result = userService.show(user.getPassportNumber());

        assertNotNull(result);
        assertEquals(user, result);

        verify(userDAO, times(1)).show(user.getPassportNumber());
    }

    @Test
    void testShowMortgages() {
        Mortgage mortgage = new Mortgage();
        List<Mortgage> mortgages = Arrays.asList(mortgage);
        when(userDAO.showMortgages(user.getPassportNumber())).thenReturn(mortgages);

        List<Mortgage> result = userService.showMortgages(user.getPassportNumber());

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(userDAO, times(1)).showMortgages(user.getPassportNumber());
    }

    @Test
    void testShowAutoLoans() {
        AutoLoan autoLoan = new AutoLoan();
        List<AutoLoan> autoLoans = Arrays.asList(autoLoan);
        when(userDAO.showAutoLoans(user.getPassportNumber())).thenReturn(autoLoans);

        List<AutoLoan> result = userService.showAutoLoans(user.getPassportNumber());

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(userDAO, times(1)).showAutoLoans(user.getPassportNumber());
    }

    @Test
    void testUpdateUser() {
        User updatedUser = new User();
        updatedUser.setName("Updated Name");
        updatedUser.setSurname("Updated Surname");

        doNothing().when(userDAO).update(user.getPassportNumber(), updatedUser);

        userService.update(user.getPassportNumber(), updatedUser);

        verify(userDAO, times(1)).update(user.getPassportNumber(), updatedUser);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userDAO).delete(user.getPassportNumber());

        userService.delete(user.getPassportNumber());

        verify(userDAO, times(1)).delete(user.getPassportNumber());
    }
}