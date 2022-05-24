START TRANSACTION;


SET search_path = "accident_app_schema";


INSERT INTO user_role VALUES ('1', 'USER');
INSERT INTO user_role VALUES ('2', 'TRAFFIC OFFICER');
INSERT INTO user_role VALUES ('3', 'ADMIN');


INSERT INTO car_accident_entity_state VALUES ('1', 'Черновик заявления');
INSERT INTO car_accident_entity_state VALUES ('2', 'Необработанное заявление');
INSERT INTO car_accident_entity_state VALUES ('3', 'Обработанное заявление');
INSERT INTO car_accident_entity_state VALUES ('4', 'Расследуемое заявление');
INSERT INTO car_accident_entity_state VALUES ('5', 'Закрытое заявление');


INSERT INTO driver_license_category VALUES ('1', 'A');
INSERT INTO driver_license_category VALUES ('2', 'B');
INSERT INTO driver_license_category VALUES ('3', 'C');
INSERT INTO driver_license_category VALUES ('4', 'D');
INSERT INTO driver_license_category VALUES ('5', 'M');


INSERT INTO user_state VALUES ('1', 'Пользователь');
INSERT INTO user_state VALUES ('2', 'Готов к работе');
INSERT INTO user_state VALUES ('3', 'В работе');
INSERT INTO user_state VALUES ('4', 'На отдыхе');


COMMIT;