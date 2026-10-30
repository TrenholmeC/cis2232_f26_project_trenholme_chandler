# For hccis.ca version of the database
# DROP DATABASE IF EXISTS bjmac_squash_skills_w26;
# CREATE DATABASE bjmac_squash_skills_w26;
# use bjmac_squash_skills_w26;

#For localhost
DROP DATABASE IF EXISTS cis2232_CT_grade_tracker;
CREATE DATABASE cis2232_CT_grade_tracker;
use cis2232_CT_grade_tracker;

-- ------------------------------------------------------------------------------
-- Note the table below to hold data associated with your project.  Expect one
-- table with 7-9 fields.
-- ------------------------------------------------------------------------------

CREATE TABLE item (
    id                  INT              NOT NULL AUTO_INCREMENT,
    dueDate             VARCHAR(10)      NOT NULL,
    maxScore            FLOAT            NOT NULL,
    actScore            FLOAT            NOT NULL,
    weight              FLOAT            NOT NULL,
    name                VARCHAR(100)     NOT NULL,
    className           VARCHAR(100)     NOT NULL,
    priority            VARCHAR(100)     NOT NULL,
    type                VARCHAR(100)     NOT NULL,
    percent             FLOAT            NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO item
(dueDate, maxScore, actScore, weight, name, className, priority, type, percent)
VALUES
    -- CIS-2225 Windows Programming
    ('2026-09-20', 40, 40, 0.05, 'Topic 1 - Assignment', 'CIS-2225 Windows Programming', 'LOW', 'ASSIGNMENT', 1.00),
    ('2026-10-04', 40, -1, 0.05, 'Topic 2 - Assignment', 'CIS-2225 Windows Programming', 'LOW', 'ASSIGNMENT', -1),
    ('2026-09-27', 20, 17, 0.05, 'Quiz 1', 'CIS-2225 Windows Programming', 'MEDIUM', 'QUIZ', 0.85),
    ('2026-10-11', 100, 85, 0.20, 'Midterm Exam', 'CIS-2225 Windows Programming', 'HIGH', 'EXAM', 0.85),
    ('2026-10-18', 30, 28, 0.05, 'Lab 1 - File IO', 'CIS-2225 Windows Programming', 'LOW', 'LAB', 0.93),
    ('2026-11-01', 100, -1, 0.30, 'Final Project', 'CIS-2225 Windows Programming', 'HIGH', 'PROJECT', -1),
    ('2026-11-15', 100, 92, 0.30, 'Group Project Milestone 1', 'CIS-2225 Windows Programming', 'HIGH', 'PROJECT', 0.92),

    -- CIS-2232 Capstone
    ('2026-09-22', 40, 36, 0.05, 'Sprint 1 Planning Doc', 'CIS-2232 Capstone', 'LOW', 'ASSIGNMENT', 0.90),
    ('2026-09-25', 20, 18, 0.05, 'Quiz 1', 'CIS-2232 Capstone', 'MEDIUM', 'QUIZ', 0.90),
    ('2026-10-09', 20, 20, 0.05, 'Quiz 2', 'CIS-2232 Capstone', 'MEDIUM', 'QUIZ', 1.00),
    ('2026-10-16', 30, 26, 0.05, 'Lab - UML Diagrams', 'CIS-2232 Capstone', 'LOW', 'LAB', 0.87),
    ('2026-10-30', 100, 78, 0.20, 'Midterm Exam', 'CIS-2232 Capstone', 'HIGH', 'EXAM', 0.78),
    ('2026-10-23', 50, -1, 0.10, 'Sprint 2 Deliverable', 'CIS-2232 Capstone', 'HIGH', 'PROJECT', -1),
    ('2026-11-13', 50, -1, 0.10, 'Sprint 3 Deliverable', 'CIS-2232 Capstone', 'HIGH', 'PROJECT', -1),
    ('2026-11-20', 50, 45, 0.10, 'Sprint 1 Deliverable', 'CIS-2232 Capstone', 'HIGH', 'PROJECT', 0.90),

    -- CIS-2234 Operating Systems
    ('2026-09-20', 40, 38, 0.05, 'Topic 1 - Processes', 'CIS-2234 Operating Systems', 'LOW', 'ASSIGNMENT', 0.95),
    ('2026-10-09', 20, 16, 0.05, 'Quiz 1 - Scheduling', 'CIS-2234 Operating Systems', 'MEDIUM', 'QUIZ', 0.80),
    ('2026-09-18', 30, 27, 0.05, 'Lab 1 - Threads', 'CIS-2234 Operating Systems', 'LOW', 'LAB', 0.90),
    ('2026-10-02', 30, 24, 0.05, 'Lab 2 - Synchronization', 'CIS-2234 Operating Systems', 'MEDIUM', 'LAB', 0.80),
    ('2026-10-16', 30, -1, 0.05, 'Lab 3 - Deadlock', 'CIS-2234 Operating Systems', 'MEDIUM', 'LAB', -1),
    ('2026-10-16', 100, 81, 0.20, 'Midterm Exam', 'CIS-2234 Operating Systems', 'HIGH', 'EXAM', 0.81),
    ('2026-11-06', 100, -1, 0.25, 'Final Exam', 'CIS-2234 Operating Systems', 'HIGH', 'EXAM', -1),
    ('2026-11-10', 100, 88, 0.25, 'Final Project - Mini OS', 'CIS-2234 Operating Systems', 'HIGH', 'PROJECT', 0.88);

# ALTER TABLE SkillsAssessmentSquashTechnical
#     ADD PRIMARY KEY (id);
# ALTER TABLE SkillsAssessmentSquashTechnical
#     MODIFY id int(4) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
#     AUTO_INCREMENT = 1;


# CREATE TABLE CodeType (codeTypeId int(3) COMMENT 'This is the primary key for code types',
#                        englishDescription varchar(100) NOT NULL COMMENT 'English description',
#                        frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
#                        createdDateTime datetime DEFAULT NULL,
#                        createdUserId varchar(20) DEFAULT NULL,
#                        updatedDateTime datetime DEFAULT NULL,
#                        updatedUserId varchar(20) DEFAULT NULL
# ) COMMENT 'This tables holds the code types that are available for the application';
#
# ALTER TABLE CodeType
#     ADD PRIMARY KEY (CodeTypeId);
#
# INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (1, 'User Types', 'User Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
# INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (2, 'Squash Technical Types', 'Squash Technical Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
#
#
#
# CREATE TABLE CodeValue (
#                            codeTypeId int(3) NOT NULL COMMENT 'see code_type table',
#                            codeValueSequence int(3) NOT NULL,
#                            englishDescription varchar(100) NOT NULL COMMENT 'English description',
#                            englishDescriptionShort varchar(20) NOT NULL COMMENT 'English abbreviation for description',
#                            frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
#                            frenchDescriptionShort varchar(20) DEFAULT NULL COMMENT 'French abbreviation for description',
#                            sortOrder int(3) DEFAULT NULL COMMENT 'Sort order if applicable',
#                            createdDateTime datetime DEFAULT NULL,
#                            createdUserId varchar(20) DEFAULT NULL,
#                            updatedDateTime datetime DEFAULT NULL,
#                            updatedUserId varchar(20) DEFAULT NULL
# ) COMMENT='This will hold code values for the application.';
#
# ALTER TABLE CodeValue
#     ADD PRIMARY KEY (CodeTypeId, codeValueSequence);
#
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (1, 2, 'Admin', 'Admin', 'Admin', 'Admin', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (2, 1, 'Forehand Drives', 'FH Drives', 'Forehand DrivesFR', 'FH DrivesFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (2, 2, 'Backhand Drives', 'BH Drives', 'Backhand DrivesFR', 'BH DrivesFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
#

