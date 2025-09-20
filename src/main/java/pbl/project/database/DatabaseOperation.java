package pbl.project.database;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import pbl.project.beans.Ticket;
import pbl.project.beans.User;

@Repository
public class DatabaseOperation {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc_connector;

    @Autowired
    private BCryptPasswordEncoder password_encoder;

    public void add_user(String username, String password, String email, Long contact) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql_query = "INSERT INTO Users (user_name, password, user_email, user_contact, user_role) VALUES (:user_name, :password, :user_email, :user_contact, :user_role)";
        parameters.addValue("user_name", username);
        parameters.addValue("password", password_encoder.encode(password));
        parameters.addValue("user_email", email);
        parameters.addValue("user_contact", contact);
        parameters.addValue("user_role", "ROLE_GUEST");
        jdbc_connector.update(sql_query, parameters);
    }

    public User get_user_info(String username) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql_query = "SELECT * FROM Users WHERE user_name = :user_name";
        parameters.addValue("user_name", username);
        ArrayList<User> users = (ArrayList<User>) jdbc_connector.query(sql_query, parameters,
                new BeanPropertyRowMapper<User>(User.class));
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public ArrayList<User> get_all_users() {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql_query = "SELECT * FROM Users";
        return (ArrayList<User>) jdbc_connector.query(sql_query, parameters,
                new BeanPropertyRowMapper<User>(User.class));
    }

    public void add_ticket(Ticket ticket) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql_query = "INSERT INTO Tickets (event_name, event_date, venue_name, ticket_holder_name, ticket_holder_contact, ticket_type, booking_reference, ticket_price) VALUES (:event_name, :event_date, :venue_name, :ticket_holder_name, :ticket_holder_contact, :ticket_type, :booking_reference, :ticket_price)";
        parameters.addValue("event_name", ticket.getEvent_name());
        parameters.addValue("event_date", ticket.getEvent_date());
        parameters.addValue("venue_name", ticket.getVenue_name());
        parameters.addValue("ticket_holder_name", ticket.getTicket_holder_name());
        parameters.addValue("ticket_holder_contact", ticket.getTicket_holder_contact());
        parameters.addValue("ticket_type", ticket.getTicket_type());
        parameters.addValue("booking_reference", ticket.getBooking_reference());
        parameters.addValue("ticket_price", ticket.getTicket_price());
        jdbc_connector.update(sql_query, parameters);
    }

    public void update_ticket(Ticket ticket) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql_query = "UPDATE Tickets SET event_name = :event_name, event_date = :event_date, venue_name = :venue_name, ticket_holder_contact = :ticket_holder_contact, ticket_type = :ticket_type, ticket_price = :ticket_price WHERE ticket_id = :ticket_id";
        parameters.addValue("event_name", ticket.getEvent_name());
        parameters.addValue("event_date", ticket.getEvent_date());
        parameters.addValue("venue_name", ticket.getVenue_name());
        parameters.addValue("ticket_holder_contact", ticket.getTicket_holder_contact());
        parameters.addValue("ticket_type", ticket.getTicket_type());
        parameters.addValue("ticket_price", ticket.getTicket_price());
        parameters.addValue("ticket_id", ticket.getTicket_id());
        jdbc_connector.update(sql_query, parameters);
    }

    public void delete_ticket(int ticket_id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql_query = "DELETE FROM Tickets WHERE ticket_id = :ticket_id";
        parameters.addValue("ticket_id", ticket_id);
        jdbc_connector.update(sql_query, parameters);
    }

    public Ticket get_ticket_by_id(int ticket_id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql_query = "SELECT * FROM Tickets WHERE ticket_id = :ticket_id";
        parameters.addValue("ticket_id", ticket_id);
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) jdbc_connector.query(sql_query, parameters,
                new BeanPropertyRowMapper<Ticket>(Ticket.class));
        if (tickets.size() > 0) {
            return tickets.get(0);
        }
        return null;
    }

    public Ticket get_ticket_by_event_name(String event_name) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql_query = "SELECT * FROM Tickets WHERE event_name = :event_name";
        parameters.addValue("event_name", event_name);
        ArrayList<Ticket> ticket_info = (ArrayList<Ticket>) jdbc_connector.query(sql_query, parameters,
                new BeanPropertyRowMapper<Ticket>(Ticket.class));
        if (ticket_info.size() > 0) {
            return ticket_info.get(0);
        }
        return null;
    }

    public ArrayList<Ticket> get_all_tickets(User user) {
        String sql_query = "SELECT * FROM Tickets";
        if (user.getUser_role().equals("ROLE_VENDER")) {
            return (ArrayList<Ticket>) jdbc_connector.query(sql_query,
                    new BeanPropertyRowMapper<Ticket>(Ticket.class));
        } else if (user.getUser_role().equals("ROLE_GUEST")) {
            sql_query = "SELECT * FROM Tickets WHERE ticket_holder_name = :ticket_holder_name";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("ticket_holder_name", new Object[] { user.getUser_name() });
            return (ArrayList<Ticket>) jdbc_connector.query(sql_query, parameters,
                    new BeanPropertyRowMapper<Ticket>(Ticket.class));
        }
        return new ArrayList<Ticket>();
    }
}
