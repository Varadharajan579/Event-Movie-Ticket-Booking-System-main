INSERT INTO Tickets
    (event_name, event_date, venue_name, ticket_holder_name, ticket_holder_contact, ticket_type, booking_reference, ticket_price)
VALUES
    ('Concert in the Park', '2023-08-15', 'Central Park', 'Tanish', 9358174158, 'GENERAL', 'https://dummy.com/concert', 50.00),
    ('Movie Night', '2023-08-20', 'Cinema City', 'Utkarsh', 19876543211, 'STANDARD', 'https://dummy.com/movies', 12.50),
    ('Tech Conference', '2023-09-05', 'Convention Center', 'Tanish', 19876543212, 'VIP', 'https://dummy.com/conference', 250.00),
    ('Art Exhibition', '2023-09-10', 'Gallery Hall', 'Utkarsh', 19876543213, 'GENERAL', 'https://dummy.com/art', 20.00),
    ('Live Comedy Show', '2023-09-18', 'Laugh Factory', 'Yogesh', 13335557777, 'PREMIUM', 'https://dummy.com/comedy', 35.00),
    ('Sports Game', '2023-09-25', 'Stadium Arena', 'Tanish', 19876543210, 'VIP', 'https://dummy.com/sports', 75.00),
    ('Fashion Runway', '2023-10-05', 'Fashion Pavilion', 'Ujjwal', 19876543211, 'GENERAL', 'https://dummy.com/fashion', 30.00),
    ('Music Festival', '2023-10-12', 'Festival Grounds', 'Yogesh', 17894561230, 'STANDARD', 'https://dummy.com/music', 65.00),
    ('Science Expo', '2023-11-03', 'Science Center', 'Utkarsh', 19876543213, 'GENERAL', 'https://dummy.com/science', 15.00),
    ('Dance Performance', '2023-11-15', 'City Theater', 'Ujjwal', 13335557777, 'PREMIUM', 'https://dummy.com/dance', 40.00);

INSERT INTO Users
    (user_name, password, user_email, user_contact, user_role)
VALUES
    ('Tanish', '$2a$10$RNTZ8u0y.MvZHPASac/9rOPZX4nqDNBakruhyGICXzxPjnZ2HjDs2', 'gtanish544@gmail.com', 9358174158, 'ROLE_VENDER');
INSERT INTO Users
    (user_name, password, user_email, user_contact, user_role)
VALUES
    ('Utkarsh', '$2a$10$RNTZ8u0y.MvZHPASac/9rOPZX4nqDNBakruhyGICXzxPjnZ2HjDs2', 'utkarshsirohi13@gmail.com', 19876543210, 'ROLE_GUEST');
INSERT INTO Users
    (user_name, password, user_email, user_contact, user_role)
VALUES
    ('Ujjwal', '$2a$10$RNTZ8u0y.MvZHPASac/9rOPZX4nqDNBakruhyGICXzxPjnZ2HjDs2', 'ujjwal189@gmail.com', 19876543211, 'ROLE_GUEST');
INSERT INTO Users
    (user_name, password, user_email, user_contact, user_role)
VALUES
    ('Yogesh', '$2a$10$RNTZ8u0y.MvZHPASac/9rOPZX4nqDNBakruhyGICXzxPjnZ2HjDs2', 'ysr2002@gmail.com', 19876543212, 'ROLE_GUEST');