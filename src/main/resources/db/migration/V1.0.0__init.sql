DROP TABLE IF EXISTS REST_DUMMY;
CREATE TABLE REST_DUMMY(
  id          VARCHAR(255) PRIMARY KEY,
  name        VARCHAR(255),
  description VARCHAR(255),
  content     VARCHAR(102400));


--CREATE TABLE REST_AUDIT(
--  id          VARCHAR(255) INDEX,
--  date        DATETIME ,
--  apiCall VARCHAR(255));


--DROP TABLE IF EXISTS TEST;
--CREATE TABLE TEST (ID INT PRIMARY KEY, NAME VARCHAR(255));
--INSERT INTO TEST VALUES(1, 'Hello');
--INSERT INTO TEST VALUES(2, 'World');