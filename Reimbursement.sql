
--drop schema if exists Reimbursement cascade;

--create schema reimbursement;
set schema 'reimbursement';

insert into "ERS_USERS" ("ERS_USERNAME", "ERS_PASSWORD","USER_FIRST_NAME",
						"USER_LAST_NAME","USER_EMAIL", "USER_ROLE_ID") values
						('Guru','1469','Nanak', 'Bedi','nanak@gmail.com', '2')

--INSERT INTO "ERS_REIMBURSEMENT_TYPE" ("REIMB_TYPE_ID", "REIMB_TYPE") values();

--INSERT INTO "ERS_REIMBURSEMENT" ("REIMB_AMOUNT", "REIMB_DESCRIPTION", 
							--"REIMB_SUBMITTED", "REIMB_RESOLVED", "REIMB_AUTHOR", 
							--"REIMB_RESOLVER", "REIMB_STATUS_ID", "REIMB_TYPE_ID")
 							--VALUES(?, ?, ?, ?, ?, ?, ?, ?)
 							
insert into "ERS_USERS" ("ERS_USERNAME", "")

 							
 CREATE TABLE "ERS_REIMBURSEMENT_STATUS" (

    "REIMB_STATUS_ID" int PRIMARY KEY,
    "REIMB_STATUS" VARCHAR(10) NOT NULL

);


CREATE TABLE "ERS_REIMBURSEMENT_TYPE" (

    "REIMB_TYPE_ID" int PRIMARY KEY,
    "REIMB_TYPE" VARCHAR(10) NOT NULL

);


CREATE TABLE "ERS_USER_ROLES" (

    "ERS_USER_ROLE_ID" int PRIMARY KEY,
    "USER_ROLE" VARCHAR(10) NOT NULL

);



CREATE TABLE "ERS_USERS" (

    "ERS_USERS_ID" serial PRIMARY KEY,
    "ERS_USERNAME" VARCHAR(50) UNIQUE NOT NULL,
   "ERS_PASSWORD" VARCHAR(50) NOT NULL,
    "USER_FIRST_NAME" VARCHAR(100) NOT NULL,
    "USER_LAST_NAME" VARCHAR(100) NOT NULL,
    "USER_EMAIL" VARCHAR(150) UNIQUE NOT NULL,
    "USER_ROLE_ID" int NOT NULL,
    
    FOREIGN KEY ("USER_ROLE_ID") REFERENCES "ERS_USER_ROLES"("ERS_USER_ROLE_ID")

);

create table "ERS_REIMBURSEMENT" (

    "REIMB_ID" serial PRIMARY KEY,
    "REIMB_AMOUNT" numeric(10) NOT NULL,
    "REIMB_SUBMITTED" timestamp(9),
    "REIMB_RESOLVED" timestamp(9),
    "REIMB_DESCRIPTION" varchar(250) NOT NULL,
    "REIMB_AUTHOR" int,
    "REIMB_RESOLVER" int,
    "REIMB_STATUS_ID" int NOT NULL,
    "REIMB_TYPE_ID" int NOT NULL,
    
    FOREIGN KEY ("REIMB_AUTHOR") REFERENCES "ERS_USERS"("ERS_USERS_ID"),
    FOREIGN KEY ("REIMB_RESOLVER") REFERENCES "ERS_USERS"("ERS_USERS_ID"),
    FOREIGN KEY ("REIMB_STATUS_ID") REFERENCES "ERS_REIMBURSEMENT_STATUS"("REIMB_STATUS_ID"),
    FOREIGN KEY ("REIMB_TYPE_ID") REFERENCES "ERS_REIMBURSEMENT_TYPE"("REIMB_TYPE_ID")
    
    
);






