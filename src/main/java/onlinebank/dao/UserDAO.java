package onlinebank.dao;

import onlinebank.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    public UserDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<User> getAllUsers() {
        return namedParameterJdbcTemplate.query("SELECT * FROM bankuser", new BeanPropertyRowMapper<>(User.class));

    }

}
