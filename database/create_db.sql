START TRANSACTION;


CREATE SCHEMA IF NOT EXISTS accident_app_schema;
SET search_path = "accident_app_schema";


DROP TABLE IF EXISTS USER_ROLE CASCADE;
CREATE TABLE USER_ROLE(
    USER_ROLE_ID SERIAL PRIMARY KEY,
    USER_ROLE_NAME TEXT NOT NULL
);


DROP TABLE IF EXISTS USERS CASCADE;
CREATE TABLE USERS(
    USER_ID SERIAL PRIMARY KEY,
    USER_PHONE_NUMBER TEXT NOT NULL,
    USER_PASSWORD TEXT NOT NULL,
    USER_ROLE INTEGER REFERENCES USER_ROLE (USER_ROLE_ID) ON DELETE CASCADE NOT NULL
);


DROP TABLE IF EXISTS DRIVER_LICENSE_CATEGORY CASCADE;
CREATE TABLE DRIVER_LICENSE_CATEGORY(
    DRIVER_LICENSE_CATEGORY_ID SERIAL PRIMARY KEY,
    DRIVER_LICENSE_CATEGORY TEXT NOT NULL
);


DROP TABLE IF EXISTS DRIVER_LICENSE CASCADE;
CREATE TABLE DRIVER_LICENSE(
    DRIVER_LICENSE_ID SERIAL PRIMARY KEY,
    DRIVER_LICENSE_NUMBER TEXT NOT NULL,
    DRIVER_LICENSE_CATEGORY INTEGER REFERENCES DRIVER_LICENSE_CATEGORY (DRIVER_LICENSE_CATEGORY_ID) ON DELETE CASCADE NOT NULL,
    DRIVER_LICENSE_DATE_OF_ISSUE TEXT NOT NULL
);


DROP TABLE IF EXISTS USER_PROFILE CASCADE;
CREATE TABLE USER_PROFILE(
    USER_PROFILE_ID SERIAL PRIMARY KEY,
    "USER" INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    USER_FULL_NAME TEXT NOT NULL,
    USER_DATE_OF_BIRTH DATE NOT NULL,
    USER_RESIDENTIAL_ADDRESS TEXT NOT NULL,
    USER_PLACE_OF_WORK TEXT NOT NULL,
    USER_POSITION_AT_WORK TEXT NOT NULL,
    USER_WORK_PHONE_NUMBER TEXT NOT NULL,
    USER_DRIVER_LICENSE INTEGER REFERENCES DRIVER_LICENSE (DRIVER_LICENSE_ID) ON DELETE CASCADE NOT NULL
);


DROP TABLE IF EXISTS VEHICLE_INSURANCE_POLICY CASCADE;
CREATE TABLE VEHICLE_INSURANCE_POLICY(
    VEHICLE_INSURANCE_POLICY_ID SERIAL PRIMARY KEY,
    VEHICLE_INSURANCE_POLICY_USER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    VEHICLE_INSURANCE_COMPANY TEXT NOT NULL,
    VEHICLE_INSURANCE_POLICY_NUMBER TEXT NOT NULL,
    VEHICLE_INSURANCE_POLICY_EXPIRATION_DATE TEXT NOT NULL
);


DROP TABLE IF EXISTS VEHICLE_PROFILE;
CREATE TABLE VEHICLE_PROFILE(
    VEHICLE_PROFILE_ID SERIAL PRIMARY KEY,
    VEHICLE_PROFILE_USER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    VEHICLE_BRAND TEXT NOT NULL,
    VEHICLE_VIN TEXT NOT NULL,
    VEHICLE_REGISTRATION_SIGN TEXT NOT NULL,
    VEHICLE_REGISTRATION_CERTIFICATE TEXT NOT NULL,
    VEHICLE_OWNER_FULL_NAME TEXT NOT NULL,
    VEHICLE_OWNER_RESIDENTIAL_ADDRESS TEXT NOT NULL,
    VEHICLE_DRIVER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    VEHICLE_INSURANCE_POLICY INTEGER REFERENCES VEHICLE_INSURANCE_POLICY (VEHICLE_INSURANCE_POLICY_ID) ON DELETE CASCADE NOT NULL
);


DROP TABLE IF EXISTS CAR_ACCIDENT CASCADE;
CREATE TABLE CAR_ACCIDENT(
    CAR_ACCIDENT_ID SERIAL PRIMARY KEY,
    CAR_ACCIDENT_SCENE TEXT NOT NULL,
    CAR_ACCIDENT_DATE TEXT NOT NULL,
    CAR_ACCIDENT_TIME TEXT NOT NULL,
    CAR_ACCIDENT_PARTICIPANTS_NUMBER INTEGER NOT NULL,
    CAR_ACCIDENT_WITNESSES_NUMBER INTEGER NOT NULL
);


DROP TABLE IF EXISTS CAR_ACCIDENT_ENTITY_STATE CASCADE;
CREATE TABLE CAR_ACCIDENT_ENTITY_STATE(
    CAR_ACCIDENT_ENTITY_STATE_ID SERIAL PRIMARY KEY,
    CAR_ACCIDENT_ENTITY_STATE TEXT NOT NULL
);


DROP TABLE IF EXISTS CAR_ACCIDENT_ENTITY CASCADE;
CREATE TABLE CAR_ACCIDENT_ENTITY(
    CAR_ACCIDENT_ENTITY_ID SERIAL PRIMARY KEY,
    CAR_ACCIDENT_ENTITY_STATE INTEGER REFERENCES CAR_ACCIDENT_ENTITY_STATE (CAR_ACCIDENT_ENTITY_STATE_ID) ON DELETE CASCADE NOT NULL,
    CAR_ACCIDENT INTEGER REFERENCES CAR_ACCIDENT (CAR_ACCIDENT_ID) ON DELETE CASCADE NOT NULL,
    TRAFFIC_POLICE_OFFICER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL
);


DROP TABLE IF EXISTS CAR_ACCIDENT_ENTITY_CHANGELOG CASCADE;
CREATE TABLE CAR_ACCIDENT_ENTITY_CHANGELOG(
    CAR_ACCIDENT_ENTITY_CHANGELOG_ID SERIAL PRIMARY KEY,
    CAR_ACCIDENT_ENTITY INTEGER REFERENCES CAR_ACCIDENT_ENTITY (CAR_ACCIDENT_ENTITY_ID) ON DELETE CASCADE NOT NULL,
    CHANGE_DESCRIPTION TEXT NOT NULL
);


DROP TABLE IF EXISTS CAR_ACCIDENT_PARTICIPANT CASCADE;
CREATE TABLE CAR_ACCIDENT_PARTICIPANT(
    CAR_ACCIDENT_PARTICIPANT_ID SERIAL PRIMARY KEY,
    CAR_ACCIDENT_ENTITY INTEGER REFERENCES CAR_ACCIDENT_ENTITY (CAR_ACCIDENT_ENTITY_ID) ON DELETE CASCADE NOT NULL,
    CAR_ACCIDENT_PARTICIPANT INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL
);


DROP TABLE IF EXISTS CAR_ACCIDENT_WITNESS CASCADE;
CREATE TABLE CAR_ACCIDENT_WITNESS(
    CAR_ACCIDENT_WITNESS_ID SERIAL PRIMARY KEY,
    CAR_ACCIDENT_ENTITY INTEGER REFERENCES CAR_ACCIDENT_ENTITY (CAR_ACCIDENT_ENTITY_ID) ON DELETE CASCADE NOT NULL,
    WITNESS_FULL_NAME TEXT NOT NULL,
    WITNESS_RESIDENTIAL_ADDRESS TEXT NOT NULL,
    WITNESS_PHONE_NUMBER TEXT NOT NULL
);


DROP TABLE IF EXISTS CHAT CASCADE;
CREATE TABLE CHAT(
    CHAT_ID SERIAL PRIMARY KEY,
    CAR_ACCIDENT_PARTICIPANT INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    TRAFFIC_POLICE_OFFICER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    CAR_ACCIDENT_ENTITY INTEGER REFERENCES CAR_ACCIDENT_ENTITY (CAR_ACCIDENT_ENTITY_ID) ON DELETE CASCADE NOT NULL
);


DROP TABLE IF EXISTS MESSAGE CASCADE;
CREATE TABLE MESSAGE(
    MESSAGE_ID SERIAL PRIMARY KEY,
    SENDER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    ADDRESSEE INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    CHAT INTEGER REFERENCES CHAT (CHAT_ID) ON DELETE CASCADE NOT NULL,
    MESSAGE_CREATION_DATE TEXT NOT NULL,
    MESSAGE_CREATION_TIME TEXT NOT NULL,
    MESSAGE_UPDATE_DATE TEXT,
    MESSAGE_UPDATE_TIME TEXT
);


DROP TABLE IF EXISTS MESSAGE_CONTENT CASCADE;
CREATE TABLE MESSAGE_CONTENT(
    MESSAGE_CONTENT_ID SERIAL PRIMARY KEY,
    MESSAGE INTEGER REFERENCES MESSAGE (MESSAGE_ID) ON DELETE CASCADE NOT NULL,
    MESSAGE_TEXT TEXT NOT NULL,
    MESSAGE_FILE_LINK TEXT
);


DROP TABLE IF EXISTS CULPRIT_ADDITIONAL_INFO CASCADE;
CREATE TABLE CULPRIT_ADDITIONAL_INFO(
    CULPRIT_ADDITIONAL_INFO_ID SERIAL PRIMARY KEY,
    CULPRIT_ACTUAL_PLACE_OF_RESIDENCE TEXT NOT NULL
);


DROP TABLE IF EXISTS ADMINISTRATIVE_OFFENCE_CASE_PROTOCOL CASCADE;
CREATE TABLE ADMINISTRATIVE_OFFENCE_CASE_PROTOCOL(
    ADMINISTRATIVE_OFFENCE_CASE_PROTOCOL_ID SERIAL PRIMARY KEY,
    DATE_OF_FILL TEXT NOT NULL,
    TIME_OF_FILL TEXT NOT NULL,
    PLACE_OF_FILL TEXT NOT NULL,
    TRAFFIC_POLICE_OFFICER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    CAR_ACCIDENT_CULPRIT INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    CULPRIT_ADDITIONAL_INFO INTEGER REFERENCES CULPRIT_ADDITIONAL_INFO (CULPRIT_ADDITIONAL_INFO_ID) ON DELETE CASCADE NOT NULL,
    CAR_ACCIDENT_ENTITY INTEGER REFERENCES CAR_ACCIDENT_ENTITY (CAR_ACCIDENT_ENTITY_ID) ON DELETE CASCADE NOT NULL,
    LAW_VIOLATION_INFO TEXT NOT NULL,
    CASE_ADDITIONAL_INFO TEXT NOT NULL,
    PLACE_AND_TIME_OF_CASE_EXAMINATION TEXT NOT NULL,
    CHANGED_PLACE_OF_CASE_EXAMINATION TEXT NOT NULL,
    EXPLANATIONS_AND_REMARKS_OF_PROTOCOL TEXT NOT NULL
);


DROP TABLE IF EXISTS ADMINISTRATIVE_OFFENCE_CASE_DECISION CASCADE;
CREATE TABLE ADMINISTRATIVE_OFFENCE_CASE_DECISION(
    ADMINISTRATIVE_OFFENCE_CASE_DECISION_ID SERIAL PRIMARY KEY,
    DATE_OF_FILL TEXT NOT NULL,
    TIME_OF_FILL TEXT NOT NULL,
    PLACE_OF_FILL TEXT NOT NULL,
    TRAFFIC_POLICE_OFFICER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    CAR_ACCIDENT_CULPRIT INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    CULPRIT_ADDITIONAL_INFO INTEGER REFERENCES CULPRIT_ADDITIONAL_INFO (CULPRIT_ADDITIONAL_INFO_ID) ON DELETE CASCADE NOT NULL,
    CAR_ACCIDENT_ENTITY INTEGER REFERENCES CAR_ACCIDENT_ENTITY (CAR_ACCIDENT_ENTITY_ID) ON DELETE CASCADE NOT NULL,
    CASE_CIRCUMSTANCES TEXT NOT NULL,
    CASE_DECISION TEXT NOT NULL,
    DATE_OF_RECEIVING TEXT NOT NULL,
    EFFECTIVE_DATE TEXT NOT NULL
);


DROP TABLE IF EXISTS ADMINISTRATIVE_OFFENCE_CASE_INVESTIGATION CASCADE;
CREATE TABLE ADMINISTRATIVE_OFFENCE_CASE_INVESTIGATION(
    ADMINISTRATIVE_OFFENCE_CASE_INVESTIGATION_ID SERIAL PRIMARY KEY,
    DATE_OF_FILL TEXT NOT NULL,
    TIME_OF_FILL TEXT NOT NULL,
    PLACE_OF_FILL TEXT NOT NULL,
    TRAFFIC_POLICE_OFFICER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    CAR_ACCIDENT_ENTITY INTEGER REFERENCES CAR_ACCIDENT_ENTITY (CAR_ACCIDENT_ENTITY_ID) ON DELETE CASCADE NOT NULL,
    INVESTIGATION_REASON TEXT NOT NULL,
    LAW_VIOLATION_INFO TEXT NOT NULL
);


DROP TABLE IF EXISTS ADMINISTRATIVE_OFFENCE_CASE_REFUSAL CASCADE;
CREATE TABLE ADMINISTRATIVE_OFFENCE_CASE_REFUSAL(
    ADMINISTRATIVE_OFFENCE_CASE_REFUSAL_ID SERIAL PRIMARY KEY,
    DATE_OF_FILL TEXT NOT NULL,
    TIME_OF_FILL TEXT NOT NULL,
    PLACE_OF_FILL TEXT NOT NULL,
    TRAFFIC_POLICE_OFFICER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    CAR_ACCIDENT_INFO_SENDER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    CAR_ACCIDENT_ESTABLISHED_INFO TEXT NOT NULL,
    REFUSAL_INFO TEXT NOT NULL,
    REFUSAL_LAW_INFO TEXT NOT NULL
);


DROP TABLE IF EXISTS WITNESSES_ADDITIONAL_INFO CASCADE;
CREATE TABLE WITNESSES_ADDITIONAL_INFO(
    WITNESSES_ADDITIONAL_INFO_ID SERIAL PRIMARY KEY,
    FIRST_WITNESS_FULL_NAME TEXT NOT NULL,
    FIRST_WITNESS_RESIDENTIAL_ADDRESS TEXT NOT NULL,
    FIRST_WITNESS_PHONE_NUMBER TEXT NOT NULL,
    SECOND_WITNESS_FULL_NAME TEXT NOT NULL,
    SECOND_WITNESS_RESIDENTIAL_ADDRESS TEXT NOT NULL,
    SECOND_WITNESS_PHONE_NUMBER TEXT NOT NULL
);


DROP TABLE IF EXISTS ADMINISTRATIVE_OFFENCE_SCENE_SCHEME CASCADE;
CREATE TABLE ADMINISTRATIVE_OFFENCE_SCENE_SCHEME(
    ADMINISTRATIVE_OFFENCE_SCENE_SCHEME_ID SERIAL PRIMARY KEY,
    DATE_OF_FILL TEXT NOT NULL,
    TIME_OF_FILL TEXT NOT NULL,
    PLACE_OF_FILL TEXT NOT NULL,
    TRAFFIC_POLICE_OFFICER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    SCHEME_FILE_LINK TEXT NOT NULL,
    SCHEME_CONVENTIONS TEXT NOT NULL,
    SCHEME_WITNESSES_INFO INTEGER REFERENCES WITNESSES_ADDITIONAL_INFO (WITNESSES_ADDITIONAL_INFO_ID) ON DELETE CASCADE NOT NULL
);


DROP TABLE IF EXISTS ADMINISTRATIVE_OFFENCE_SCENE_INSPECTION CASCADE;
CREATE TABLE ADMINISTRATIVE_OFFENCE_SCENE_INSPECTION(
    ADMINISTRATIVE_OFFENCE_SCENE_INSPECTION_ID SERIAL PRIMARY KEY,
    DATE_OF_FILL TEXT NOT NULL,
    TIME_OF_FILL TEXT NOT NULL,
    PLACE_OF_FILL TEXT NOT NULL,
    TRAFFIC_POLICE_OFFICER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    CAMERA_USAGE BOOLEAN NOT NULL,
    INSPECTION_WITNESSES_INFO INTEGER REFERENCES WITNESSES_ADDITIONAL_INFO (WITNESSES_ADDITIONAL_INFO_ID) ON DELETE CASCADE NOT NULL,
    SCENE_INSPECTION_INFO TEXT NOT NULL
);


DROP TABLE IF EXISTS CAR_ACCIDENT_ENTITY_DOCUMENTS CASCADE;
CREATE TABLE CAR_ACCIDENT_ENTITY_DOCUMENTS(
    CAR_ACCIDENT_ENTITY_DOCUMENTS_ID SERIAL PRIMARY KEY,
    CAR_ACCIDENT_ENTITY INTEGER REFERENCES CAR_ACCIDENT_ENTITY (CAR_ACCIDENT_ENTITY_ID) ON DELETE CASCADE NOT NULL,
    ADMINISTRATIVE_OFFENCE_CASE_PROTOCOL INTEGER REFERENCES ADMINISTRATIVE_OFFENCE_CASE_PROTOCOL (ADMINISTRATIVE_OFFENCE_CASE_PROTOCOL_ID) ON DELETE CASCADE NOT NULL,
    ADMINISTRATIVE_OFFENCE_CASE_DECISION INTEGER REFERENCES ADMINISTRATIVE_OFFENCE_CASE_DECISION (ADMINISTRATIVE_OFFENCE_CASE_DECISION_ID) ON DELETE CASCADE NOT NULL,
    ADMINISTRATIVE_OFFENCE_CASE_INVESTIGATION INTEGER REFERENCES ADMINISTRATIVE_OFFENCE_CASE_INVESTIGATION (ADMINISTRATIVE_OFFENCE_CASE_INVESTIGATION_ID) ON DELETE CASCADE NOT NULL,
    ADMINISTRATIVE_OFFENCE_CASE_REFUSAL INTEGER REFERENCES ADMINISTRATIVE_OFFENCE_CASE_REFUSAL (ADMINISTRATIVE_OFFENCE_CASE_REFUSAL_ID) ON DELETE CASCADE NOT NULL,
    ADMINISTRATIVE_OFFENCE_SCENE_SCHEME INTEGER REFERENCES ADMINISTRATIVE_OFFENCE_SCENE_SCHEME (ADMINISTRATIVE_OFFENCE_SCENE_SCHEME_ID) ON DELETE CASCADE NOT NULL,
    ADMINISTRATIVE_OFFENCE_SCENE_INSPECTION INTEGER REFERENCES ADMINISTRATIVE_OFFENCE_SCENE_INSPECTION (ADMINISTRATIVE_OFFENCE_SCENE_INSPECTION_ID) ON DELETE CASCADE NOT NULL
);


DROP TABLE IF EXISTS INTERVIEWED_PERSON_TYPE CASCADE;
CREATE TABLE INTERVIEWED_PERSON_TYPE(
    INTERVIEWED_PERSON_TYPE_ID SERIAL PRIMARY KEY,
    INTERVIEWED_PERSON_TYPE TEXT NOT NULL
);


DROP TABLE IF EXISTS EXPLANATION_DOCUMENT CASCADE;
CREATE TABLE EXPLANATION_DOCUMENT(
    EXPLANATION_DOCUMENT_ID SERIAL PRIMARY KEY,
    CAR_ACCIDENT_ENTITY_DOCUMENTS INTEGER REFERENCES CAR_ACCIDENT_ENTITY_DOCUMENTS (CAR_ACCIDENT_ENTITY_DOCUMENTS_ID) ON DELETE CASCADE NOT NULL,
    DATE_OF_FILL TEXT NOT NULL,
    TIME_OF_FILL TEXT NOT NULL,
    PLACE_OF_FILL TEXT NOT NULL,
    TRAFFIC_POLICE_OFFICER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    CAR_ACCIDENT_PARTICIPANT INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    INTERVIEWED_PERSON_TYPE INTEGER REFERENCES INTERVIEWED_PERSON_TYPE (INTERVIEWED_PERSON_TYPE_ID) ON DELETE CASCADE NOT NULL
);


DROP TABLE IF EXISTS CONFISCATION_OF_DOCUMENTS_PROTOCOL CASCADE;
CREATE TABLE CONFISCATION_OF_DOCUMENTS_PROTOCOL(
    CONFISCATION_OF_DOCUMENTS_PROTOCOL_ID SERIAL PRIMARY KEY,
    CAR_ACCIDENT_ENTITY_DOCUMENTS INTEGER REFERENCES CAR_ACCIDENT_ENTITY_DOCUMENTS (CAR_ACCIDENT_ENTITY_DOCUMENTS_ID) ON DELETE CASCADE NOT NULL,
    DATE_OF_FILL TEXT NOT NULL,
    TIME_OF_FILL TEXT NOT NULL,
    PLACE_OF_FILL TEXT NOT NULL,
    TRAFFIC_POLICE_OFFICER INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    CAR_ACCIDENT_PARTICIPANT INTEGER REFERENCES USERS (USER_ID) ON DELETE CASCADE NOT NULL,
    CONFISCATION_REASON TEXT NOT NULL,
    CONFISCATED_DOCUMENTS_INFO TEXT NOT NULL,
    CONFISCATION_PROCESS_FIXATION_METHOD TEXT NOT NULL,
    CONFISCATION_WITNESSES_INFO INTEGER REFERENCES WITNESSES_ADDITIONAL_INFO (WITNESSES_ADDITIONAL_INFO_ID) ON DELETE CASCADE NOT NULL
);


COMMIT;