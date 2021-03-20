CREATE TABLE EVENTS
(
    ID   INT PRIMARY KEY,
    DATE date,
    NAME text
);

CREATE TABLE CATEGORIES
(
    ID   INT PRIMARY KEY,
    NAME text
);

CREATE TABLE COMPANY
(
    ID    INT PRIMARY KEY,
    ADMIN INT,
    NAME  text
);
CREATE TABLE USERS
(
    ID         INT PRIMARY KEY,
    FIRST_NAME text,
    LAST_NAME  text,
    ROLE       text,
    COMPANY    INT,
    EMAIL      text,
    PASSWORD   text,
    CONSTRAINT fk_USERS_COMPANY
        FOREIGN KEY (COMPANY)
            REFERENCES COMPANY (ID)


);
CREATE TABLE REQUEST
(
    ID         INT PRIMARY KEY,
    REQUEST_BY  INT,
    APPROVED_BY INT,
    APPROVED_ON date,
    CONSTRAINT fk_REQUESTBY_USER
        FOREIGN KEY (REQUEST_BY)
            REFERENCES USERS (ID),
    CONSTRAINT fk_APPROVEDBY_USER
        FOREIGN KEY (APPROVED_BY)
            REFERENCES USERS (ID)

);

CREATE TABLE POSTING
(
    ID            INT PRIMARY KEY,
    POSTED_BY      INT,
    POSTED_AT      date,
    DEADLINE      date,
    NUMBER_OF_VIEWS INT,
    NAME          text,
    DESCRIPTION   text,
    CATEGORY_ID    INT,
    REQUIREMENTS  text,
    CONSTRAINT fk_POSTEDBY_USER
        FOREIGN KEY (POSTED_BY)
            REFERENCES USERS (ID),
    CONSTRAINT fk_CATEGORYID_CATEGORIES
        FOREIGN KEY (CATEGORY_ID)
            REFERENCES CATEGORIES (ID)
);

CREATE TABLE APPLICATIONS
(
    ID INT PRIMARY KEY,
    POSTING_ID INT ,
    USER_ID INT ,
    DATE_APPLIED date ,
    EXPERIENCE INT ,
    WORK_EXPERIENCE text,
    EDUCATION text,
    CONSTRAINT fk_APPLICATIONS_POSTING
        FOREIGN KEY (POSTING_ID)
            REFERENCES POSTING (ID),
    CONSTRAINT fk_APPLICATIONS_USER
        FOREIGN KEY (USER_ID)
            REFERENCES USERS (ID)
);
