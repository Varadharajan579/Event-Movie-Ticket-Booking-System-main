package pbl.project.beans;

public class Ticket {
    private int ticket_id;

    private String event_name;
    private String event_date;
    private String venue_name;
    private String ticket_holder_name;
    private Long ticket_holder_contact;
    private String ticket_type;
    private String booking_reference;

    private double ticket_price;

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getTicket_holder_name() {
        return ticket_holder_name;
    }

    public void setTicket_holder_name(String ticket_holder_name) {
        this.ticket_holder_name = ticket_holder_name;
    }

    public Long getTicket_holder_contact() {
        return ticket_holder_contact;
    }

    public void setTicket_holder_contact(Long ticket_holder_contact) {
        this.ticket_holder_contact = ticket_holder_contact;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public String getBooking_reference() {
        return booking_reference;
    }

    public void setBooking_reference(String booking_reference) {
        this.booking_reference = booking_reference;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

}
