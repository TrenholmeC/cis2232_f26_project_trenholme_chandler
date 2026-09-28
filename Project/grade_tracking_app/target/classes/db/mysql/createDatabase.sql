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
    actScore            FLOAT            NULL,
    weight              FLOAT            NOT NULL,
    name                VARCHAR(100)     NOT NULL,
    className           VARCHAR(100)     NOT NULL,
    priority            VARCHAR(100)     NOT NULL,
    type                VARCHAR(100)     NOT NULL,
    percent             FLOAT            NULL,
    PRIMARY KEY (id)
);

INSERT INTO item
(dueDate, maxScore, actScore, weight, name, className, priority, type, percent)
VALUES
    ('2026-09-20', 40, 40, 0.05, 'Topic 1 - Assignment', 'CIS-2225 Windows Programming', 'LOW', 'ASSIGNMENT', 1.00),
('2026-10-04', 40, NULL, 0.05, 'Topic 2 - Assignment', 'CIS-2225 Windows Programming', 'LOW', 'ASSIGNMENT', NULL);

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

