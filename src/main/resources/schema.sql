CREATE TABLE user_info (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           email VARCHAR(255) NOT NULL,
                           phone VARCHAR(20) NOT NULL,
                           has_sent BOOLEAN NOT NULL,
                           create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE user_rating_page_access (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         user_id BIGINT NOT NULL,
                                         access_num INT NOT NULL,
                                         link_code VARCHAR(50) NOT NULL,
                                         create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                         FOREIGN KEY (user_id) REFERENCES user_info(id)
);

CREATE TABLE user_rating_page_action_record (
                                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                access_id BIGINT NOT NULL,
                                                link_access BOOLEAN NOT NULL,
                                                link_code VARCHAR(50) NOT NULL,
                                                mail_access BOOLEAN NOT NULL,
                                                action_type VARCHAR(50) NOT NULL,
                                                create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                FOREIGN KEY (access_id) REFERENCES user_rating_page_access(id)
);

INSERT INTO user_info (email, phone, has_sent, create_time, updated_time) VALUES
                                                                              ('user1@example.com', '1234567890', TRUE, '2023-07-01 10:00:00', '2023-07-01 10:00:00'),
                                                                              ('user2@example.com', '1234567891', FALSE, '2023-07-02 10:00:00', '2023-07-02 10:00:00'),
                                                                              ('user3@example.com', '1234567892', TRUE, '2023-07-03 10:00:00', '2023-07-03 10:00:00'),
                                                                              ('user4@example.com', '1234567893', FALSE, '2023-07-04 10:00:00', '2023-07-04 10:00:00'),
                                                                              ('user5@example.com', '1234567894', TRUE, '2023-07-05 10:00:00', '2023-07-05 10:00:00'),
                                                                              ('user6@example.com', '1234567895', FALSE, '2023-07-06 10:00:00', '2023-07-06 10:00:00'),
                                                                              ('user7@example.com', '1234567896', TRUE, '2023-07-07 10:00:00', '2023-07-07 10:00:00'),
                                                                              ('user8@example.com', '1234567897', FALSE, '2023-07-08 10:00:00', '2023-07-08 10:00:00'),
                                                                              ('user9@example.com', '1234567898', TRUE, '2023-07-09 10:00:00', '2023-07-09 10:00:00'),
                                                                              ('user10@example.com', '1234567899', FALSE, '2023-07-10 10:00:00', '2023-07-10 10:00:00'),
                                                                              ('user11@example.com', '1234567880', TRUE, '2023-07-11 10:00:00', '2023-07-11 10:00:00');

INSERT INTO user_rating_page_access (user_id, access_num, link_code, create_time, updated_time) VALUES
                                                                                                    (1, 5, 'link1', '2023-07-01 10:00:00', '2023-07-01 10:00:00'),
                                                                                                    (2, 3, 'link2', '2023-07-02 10:00:00', '2023-07-02 10:00:00'),
                                                                                                    (3, 7, 'link3', '2023-07-03 10:00:00', '2023-07-03 10:00:00'),
                                                                                                    (4, 2, 'link4', '2023-07-04 10:00:00', '2023-07-04 10:00:00'),
                                                                                                    (5, 4, 'link5', '2023-07-05 10:00:00', '2023-07-05 10:00:00'),
                                                                                                    (6, 6, 'link6', '2023-07-06 10:00:00', '2023-07-06 10:00:00'),
                                                                                                    (7, 8, 'link7', '2023-07-07 10:00:00', '2023-07-07 10:00:00'),
                                                                                                    (8, 1, 'link8', '2023-07-08 10:00:00', '2023-07-08 10:00:00'),
                                                                                                    (9, 9, 'link9', '2023-07-09 10:00:00', '2023-07-09 10:00:00'),
                                                                                                    (10, 10, 'link10', '2023-07-10 10:00:00', '2023-07-10 10:00:00'),
                                                                                                    (11, 12, 'link11', '2023-07-11 10:00:00', '2023-07-11 10:00:00');


INSERT INTO user_rating_page_action_record (access_id, link_access, link_code, mail_access, action_type, create_time, updated_time) VALUES
                                                                                                                                        (1, TRUE, 'link1', TRUE, 'button1', '2023-07-01 10:00:00', '2023-07-01 10:00:00'),
                                                                                                                                        (1, TRUE, 'link1', TRUE, 'button1', '2023-07-01 10:02:00', '2023-07-01 10:02:00'),
                                                                                                                                        (1, TRUE, 'link1', FALSE, 'button2', '2023-07-01 10:05:00', '2023-07-01 10:05:00'),
                                                                                                                                        (3, TRUE, 'link3', TRUE, 'button1', '2023-07-03 10:00:00', '2023-07-03 10:00:00'),
                                                                                                                                        (4, TRUE, 'link4', FALSE, 'button2', '2023-07-04 10:00:00', '2023-07-04 10:00:00'),
                                                                                                                                        (5, FALSE, 'link5', TRUE, 'button1', '2023-07-05 10:00:00', '2023-07-05 10:00:00'),
                                                                                                                                        (7, TRUE, 'link7', FALSE, 'button2', '2023-07-07 10:00:00', '2023-07-07 10:00:00'),
                                                                                                                                        (8, TRUE, 'link8', TRUE, 'button1', '2023-07-08 10:00:00', '2023-07-08 10:00:00'),
                                                                                                                                        (10, TRUE, 'link10', TRUE, 'button2', '2023-07-10 10:00:00', '2023-07-10 10:00:00'),
                                                                                                                                        (11, TRUE, 'link11', FALSE, 'button1', '2023-07-11 10:00:00', '2023-07-11 10:00:00'),
                                                                                                                                        (11, FALSE, 'link12', TRUE, 'button2', '2023-07-12 10:00:00', '2023-07-12 10:00:00'),
                                                                                                                                        (10, FALSE, 'link14', TRUE, 'button1', '2023-07-14 10:00:00', '2023-07-14 10:00:00'),
                                                                                                                                        (9, TRUE, 'link15', TRUE, 'button2', '2023-07-15 10:00:00', '2023-07-15 10:00:00');
