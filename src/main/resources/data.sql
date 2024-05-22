DROP TABLE IF exists addresses, users, posts;

CREATE TABLE addresses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(50),
    postal_code VARCHAR(20),
    city VARCHAR(35),
    country VARCHAR(35)
    );


CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(35),
    last_name VARCHAR(35),
    email VARCHAR(60),
    phone VARCHAR(25),
    member_type ENUM('STANDARD', 'ENHANCED', 'PREMIUM'),
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES addresses(id)
    );

CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    topic VARCHAR(255),
    content VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

INSERT INTO addresses (street, postal_code, city, country) VALUES
                                                               ('Fyrklövern 2', '449 34', 'Nödinge', 'Sweden'),
                                                               ('Alcon Molina 82', '30800', 'Lorca', 'Spain'),
                                                               ('Solkraftsvagen 23', '135 70', 'Stockholm', 'Sweden'),
                                                               ('Av. José Sánchez Peñate, 24', '35518', 'Las Palmas de Gran Canaria', 'Spain'),
                                                               ('Svartagatan 4', '128 45', 'Bagarmossen', 'Sweden');

INSERT INTO users (first_name, last_name, email, phone, member_type, address_id) VALUES
                                                                                  ('Jakob', 'Andersson', 'jandersson@gmail.com', '+46701111111', 'STANDARD', 1),
                                                                                  ('Hanna', 'Linn', 'hannasmail@icloud.com', '+34702222222', 'ENHANCED', 2),
                                                                                  ('Robin', 'Nilsson', 'r_nilsson@gmail.com', '+46703333333', 'PREMIUM', 3),
                                                                                  ('Patrik', 'Gustavsson', 'patrik@familjengustavsson.se', '+34704444444', 'PREMIUM', 4),
                                                                                  ('Lina', 'Haraldsdotter', 'lh@hotmail.com', '+46705555555', 'STANDARD', 4);

INSERT INTO posts (topic, content, user_id) VALUES
                                                ('How To Learn Java', 'Buy Coffeebeans, put into a grinder, crush, pour into coffeemachine, top up with water, let it cook, serve and enjoy.', 1),
                                                ('Food For Thought', 'Not interested in your thoughts. Im busy eating..', 2),
                                                ('Best In Test', 'Depends on what test you are looking at. The best at being worst is the worst, yet the best.', 3),
                                                ('How To Change A Tire', 'Screw the old one off, screw the new one on. Dont forget to buy a new tire unless you have a spare.', 4),
                                                ('Summer In Gothenburg', 'Hot, hot, hot!', 5);