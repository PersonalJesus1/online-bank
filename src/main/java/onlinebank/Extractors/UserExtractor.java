package onlinebank.Extractors;

import onlinebank.models.*;

import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserExtractor implements ResultSetExtractor<List<User>> {

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());
            user.setSex(rs.getString("sex"));


            // extract passport number номера паспорта
            user.setPassportNumber(rs.getInt("passportnumber"));


            users.add(user);
        }
        return users;
    }
}