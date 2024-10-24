package onlinebank.dao;

import onlinebank.Extractors.UserExtractor;
import onlinebank.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM bankuser";
        return jdbcTemplate.query(sql, new UserExtractor());
    }


    public User show(int passportNumber) {
        return jdbcTemplate.query("SELECT * FROM bankuser WHERE passportNumber=?", new Object[]{passportNumber}, new UserExtractor())
                .stream().findAny().orElse(null);
    }
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO bankuser VALUES(?, ?, ?, ?, ?)",
                user.getName(),
                user.getSurname(),
                java.sql.Date.valueOf(user.getDateOfBirth()),
                user.getSex(),
                user.getPassportNumber());
    }

    public void update(int passportNumber, User updatedUser) {
        jdbcTemplate.update("UPDATE bankuser SET name=?, surname=?, dateofbirth=?,  sex=? WHERE passportnumber=?", updatedUser.getName(),
                updatedUser.getSurname(), updatedUser.getDateOfBirth(),  updatedUser.getSex(), passportNumber);
    }

    public void delete(int passportNumber) {
        jdbcTemplate.update("DELETE FROM bankuser WHERE passportNumber=?", passportNumber);
    }
}
