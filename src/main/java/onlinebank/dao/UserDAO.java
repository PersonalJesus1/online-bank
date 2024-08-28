package onlinebank.dao;

import onlinebank.models.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private List<User> users;

    {
        users = new ArrayList<>();
        users.add(new User("Tom", "Baker", LocalDate.of(1996, 8, 22), Sex.M, 5895256, new ArrayList<Mortgage>(), new ArrayList<AutoLoan>()));
        users.add(new User("Bob", "Taylor", LocalDate.of(1991, 5, 13), Sex.M, 5425270, new ArrayList<Mortgage>(), new ArrayList<AutoLoan>()));
        users.add(new User("Mike", "Smith", LocalDate.of(1989, 2, 18), Sex.M, 1225381, new ArrayList<Mortgage>(), new ArrayList<AutoLoan>()));
        users.add(new User("Katy", "Butcher", LocalDate.of(1990, 11, 7), Sex.F, 8629943, new ArrayList<Mortgage>(), new ArrayList<AutoLoan>()));
    }

    public List<User> getAllUsers() {
        return users;
    }
}
