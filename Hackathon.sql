CREATE TABLE organization (
Org_ID            VARCHAR(10)              PRIMARY KEY,
Company            CHAR(50)          DEFAULT 'undefined',
street            VARCHAR(20),
city              VARCHAR(20),
[state]           CHAR(2),
zip               CHAR(5),       
phone             VARCHAR(12),
lead_email        CHAR(25),

);


DROP TABLE organization;

INSERT into organization (Org_ID, Company, street, city, [state], zip, phone, lead_email) VALUES ('57892432','Lakeshore Foundation', 'Lakeshore Drive','Birmingham', 'AL', '35209', '315-228-3457', 'contact@foundation.com');
INSERT into organization(Org_ID, Company, street, city, [state], zip, phone, lead_email) VALUES ('74665027','United States', 'Home Wood','Birmingham', 'AL', '35244', '205-401-8741', 'contact@habhuminity.com');
INSERT into organization (Org_ID, Company, street, city, [state], zip, phone, lead_email) VALUES ('36374507','Hoover Pride', 'hoover','hoover', 'AL', '35244', '454-132-9475', 'contact@UWorldwide.com');

SELECT * from organization;


CREATE TABLE App_User(
    username        VARCHAR(10),
    [name]          VARCHAR(25)     NOT NULL,
    gender          CHAR(1)         NULL,
    age             CHAR(2)         NULL,
    phone           VARCHAR(12)     NULL,
    user_email      CHAR(25)
    
    CONSTRAINT username_pk PRIMARY KEY (username),
    --CONSTRAINT User_Event_pk2 FOREIGN KEY (User_Event_ID) REFERENCES User_Event (User_Event_ID)
);

DROP TABLE App_User;

INSERT into App_User (username, [name], gender, age, phone) VALUES ('jsmith', 'Jenny Smith', 'F','19', '702-334-3467');
INSERT into App_User (username, [name], gender, age, phone) VALUES ('mHotchner', 'Hampton Hotchner', 'M','25', '454-132-9475');
INSERT into App_User (username, [name], gender, age, phone) VALUES ('MichalG', 'Michal Ghnom', 'M','18', '205-454-4365');
INSERT into App_User (username, [name], gender, age, phone) VALUES ('MonterT', 'Meghan Tran', 'F','20', '263-346-4483');
INSERT into App_User (username, [name], gender, age, phone) VALUES ('HarryP', 'Harry Potter', 'M','40', '378-378-4833');
INSERT into App_User (username, [name], gender, age, phone) VALUES ('hermonG', 'Hermanie Granger', 'F','38', '894-734-4786');
INSERT into App_User (username, [name], gender, age, phone) VALUES ('DumbeldorS', 'Slivers Dumbledoor', 'M','78', '374-635-9633');
SELECT * from App_User;

CREATE TABLE Org_event(
  event_id char(3) NOT NULL,
  [Hours] int,
  [Description] VARCHAR(50) NOT NULL,
  Volunteer_Age CHAR(3) NOT NULL,
  Volunteer_gender VARCHAR(6) NOT NULL,
  CONSTRAINT event_pk PRIMARY key(event_id),
  CONSTRAINT OrgEventCalled_fk FOREIGN KEY (event_id) REFERENCES Org_event(event_id)
);

DROP TABLE Org_event;
INSERT into Org_event (event_id, [Hours], [Description], Volunteer_Age, Volunteer_gender) VALUES ('123', '6', 'Special Olympics 2018', '18+', 'F');
INSERT into Org_event (event_id, [Hours], [Description], Volunteer_Age, Volunteer_gender) VALUES ('456', '4', 'UnitedWay WorldWide', '18+', 'A');
INSERT into Org_event (event_id, [Hours], [Description], Volunteer_Age, Volunteer_gender) VALUES ('789', '1', 'Habiated For Humanity', '10+', 'A');
INSERT into Org_event (event_id, [Hours], [Description], Volunteer_Age, Volunteer_gender) VALUES ('501', '5', 'ICerve', '7+', 'A');
INSERT into Org_event (event_id, [Hours], [Description], Volunteer_Age, Volunteer_gender) VALUES ('408', '2', 'Charity Navigation', '18+', 'M');
INSERT into Org_event (event_id, [Hours], [Description], Volunteer_Age, Volunteer_gender) VALUES ('856', '3', 'Alzimers Charity', '18+', 'M');
INSERT into Org_event (event_id, [Hours], [Description], Volunteer_Age, Volunteer_gender) VALUES ('345', '7', 'Make-A-Wish', '5+', 'A');
SELECT * from Org_Event;




CREATE TABLE User_event(
    User_Event_ID   char(5) PRIMARY KEY,
    OrgEventCalled  char(3),
    [DATE] DATETIME not null,
    review VARCHAR(30),
    [HOURS] int NOT NULL,
    CONSTRAINT OrgEventCalled_fk2 FOREIGN KEY (OrgEventCalled) REFERENCES Org_event(event_id)
);

DROP TABLE User_event;

INSERT into user_event (User_Event_ID, [DATE], review, [Hours]) VALUES (3346, 4/20/18, 3, 2);
INSERT into user_event (User_Event_ID, [DATE], review, [Hours]) VALUES (5555, 6/22/18, 4, 3);
INSERT into user_event (User_Event_ID, [DATE], review, [Hours]) VALUES (8093, 12/15/18, 5, 2);
INSERT into user_event (User_Event_ID, [DATE], review, [Hours]) VALUES (2832, 7/30/18, 4, 5);
INSERT into user_event (User_Event_ID, [DATE], review, [Hours]) VALUES (9028, 5/10/18, 2, 1);
INSERT into user_event (User_Event_ID, [DATE], review, [Hours]) VALUES (6692, 3/23/18, 1, 1);
INSERT into user_event (User_Event_ID, [DATE], review, [Hours]) VALUES (5234, 2/4/19, 4, 4);
SELECT * from user_event;





