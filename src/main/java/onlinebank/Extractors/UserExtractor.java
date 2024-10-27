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

            /*
            // extract lists of mortgages and autoloans from JSONB
            String mortgageJson = rs.getString("mortgagelist");
            String autoLoanJson = rs.getString("autoloanlist");

            // Преобразование JSON в список объектов
            List<Mortgage> mortgages = parseMortgages(mortgageJson);
            List<AutoLoan> autoLoans = parseAutoLoans(autoLoanJson);

            user.setMortgageList((ArrayList<Mortgage>) mortgages);
            user.setAutoLoanList((ArrayList<AutoLoan>) autoLoans);
            */
            users.add(user);
        }
        return users;
    }
    /*
    private List<Mortgage> parseMortgages(String json) {
        List<Mortgage> mortgages = new ArrayList<>();

        return mortgages;
    }

    private List<AutoLoan> parseAutoLoans(String json) {
        List<AutoLoan> autoLoans = new ArrayList<>();

        return autoLoans;
    }
    */

}