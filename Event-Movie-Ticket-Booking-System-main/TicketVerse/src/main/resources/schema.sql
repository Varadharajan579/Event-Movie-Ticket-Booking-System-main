CREATE TABLE Tickets
(
    ticket_id INT
    AUTO_INCREMENT,
    event_name VARCHAR
    (100) NOT NULL,
    event_date VARCHAR
    (100) NOT NULL,
    venue_name VARCHAR
    (255) NOT NULL,
    ticket_holder_name VARCHAR
    (100) NOT NULL,
    ticket_holder_contact BIGINT NOT NULL,
    ticket_type VARCHAR
    (50) NOT NULL,
    booking_reference VARCHAR
    (255) NOT NULL,
    ticket_price DOUBLE NOT NULL,
    PRIMARY KEY
    (ticket_id)
);

    CREATE TABLE Users
    (
        user_id INT
        AUTO_INCREMENT,
    user_name VARCHAR
        (100) NOT NULL,
    password VARCHAR
        (100) NOT NULL,
    user_email VARCHAR
        (100) NOT NULL,
    user_contact VARCHAR
        (100) NOT NULL,
    user_role VARCHAR
        (100) NOT NULL,
    PRIMARY KEY
        (user_id)
);