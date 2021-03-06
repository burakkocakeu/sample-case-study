INSERT INTO DEPARTMENT VALUES (1, 'FINANCE');
INSERT INTO DEPARTMENT VALUES (2, 'HUMAN RESOURCE');
INSERT INTO DEPARTMENT VALUES (3, 'SUPPLY CHAIN');
INSERT INTO DEPARTMENT VALUES (4, 'CUSTOMER');
INSERT INTO DEPARTMENT VALUES (5, 'MANUFACTURER');

INSERT INTO OFFICE_LOCATION VALUES (1, 'ISTANBUL');
INSERT INTO OFFICE_LOCATION VALUES (2, 'OTTAWA');
INSERT INTO OFFICE_LOCATION VALUES (3, 'PARIS');
INSERT INTO OFFICE_LOCATION VALUES (4, 'LONDON');

INSERT INTO EMPLOYEE (IDENTITY, NAME, SURNAME, TN, EMAIL) VALUES (87543377832, 'Josh', 'Smith', 54432262214, 'josh.smith@business.com');
INSERT INTO EMPLOYEE_INFORMATION (IDENTITY, DATE_STARTED, TITLE, DEPARTMENT_ID, LOCATION_ID, SALARY, REMAINED_PAID_LEAVE, ADDRESS, BORN) VALUES (87543377832, TO_DATE(CURRENT_TIMESTAMP,'yyyy-MM-dd'), 'Sales Manager', 1, 1, 8000, 14, 'Ohio', 1992);

INSERT INTO EMPLOYEE (IDENTITY, NAME, SURNAME, TN, EMAIL) VALUES (33214423223, 'Cristina', 'Gonzales', 4431555323, 'cristina.gonzales@business.com');
INSERT INTO EMPLOYEE_INFORMATION (IDENTITY, DATE_STARTED, TITLE, DEPARTMENT_ID, LOCATION_ID, SALARY, REMAINED_PAID_LEAVE, ADDRESS, BORN) VALUES (33214423223, TO_DATE(CURRENT_TIMESTAMP,'yyyy-MM-dd'), 'CTO', 1, 1, 12000, 14, 'Chicago', 1972);

COMMIT;