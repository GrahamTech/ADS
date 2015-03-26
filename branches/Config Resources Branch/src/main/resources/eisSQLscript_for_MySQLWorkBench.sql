create database if not exists eisdb;
use eisdb;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS eisdb.email_templates;
CREATE TABLE IF NOT EXISTS eisdb.email_templates (
	email_template_id int (11) NOT NULL AUTO_INCREMENT,
	email_template_name varchar (45) NOT NULL UNIQUE,
	email_subject varchar (75) NOT NULL,
	email_body varchar (516) NOT NULL,
	email_from varchar (75),
	last_modified_date date,
	PRIMARY KEY (email_template_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.contact_us;
CREATE TABLE IF NOT EXISTS eisdb.contact_us (
	contact_us_id int (11) NOT NULL AUTO_INCREMENT,
	email varchar (75) NOT NULL,
	name varchar (45),
	category enum ('General', 'Suggestion', 'Bug') NOT NULL,
	user_comment varchar (256) NOT NULL,
	last_modified_date date NOT NULL,
	PRIMARY KEY (contact_us_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.Role;
CREATE TABLE IF NOT EXISTS eisdb.Role (
	role_id int (11) NOT NULL AUTO_INCREMENT,
	role_name enum ('administrator', 'executive', 'manager', 'internal_system', 'external_system', 'read_only', 'restricted') NOT NULL,
	role_description varchar (255) NOT NULL,
	role_status enum('active', 'inactive'),
	PRIMARY KEY (role_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.risk_preferences;
CREATE TABLE IF NOT EXISTS eisdb.risk_preferences (
	risk_preference_id int (11) NOT NULL AUTO_INCREMENT,
	risk_preference_name varchar (45),
	last_modified_date date NOT NULL,
	overall_project_risk_tolerance decimal(6,2),
	overall_project_risk_weight decimal(6,2),
	overall_project_risk_priority enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	project_budget_variance_tolerance decimal(6,2),
	project_budget_variance_weight decimal(6,2),
	project_budget_variance_priority enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	project_schedule_variance_tolerance decimal(6,2),
	project_schedule_variance_weight decimal(6,2),
	project_schedule_variance_priority enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	project_fte_utilization_variance_tolerance decimal(6,2),
	project_fte_utilization_variance_weight decimal(6,2),
	project_fte_utilization_variance_priority enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	project_partner_risk_tolerance decimal(6,2),
	project_partner_risk_weight decimal(6,2),
	project_partner_risk_priority enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	project_product_risk_tolerance decimal(6,2),
	project_product_risk_weight decimal(6,2),
	project_product_risk_priority enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	PRIMARY KEY (risk_preference_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.projects;
CREATE TABLE IF NOT EXISTS eisdb.projects (
	project_id int (11) NOT NULL AUTO_INCREMENT,
	project_name varchar (75) NOT NULL UNIQUE,
	project_status enum ('active', 'archived'),
	PRIMARY KEY (project_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.project_details;
CREATE TABLE IF NOT EXISTS eisdb.project_details (
	project_details_id int (11) NOT NULL AUTO_INCREMENT,
	org_details varchar (255) NOT NULL,
	country_code enum('USA'),
	state_province enum ('AL', 'AK', 'AS', 'AZ', 'AR', 'CA', 'CO', 'CT', 'DE', 'DC', 'FL', 'GA', 'GU', 'HI', 'ID', 'IL', 'IN', 'IA', 'KS', 'KY', 'LA', 'ME', 'MD', 'MH', 'MA', 'MI', 'FM', 'MN', 'MS', 'MO', 'MT', 'NE', 'NV', 'NH', 'NJ', 'NM', 'NY', 'NC', 'ND', 'MP', 'OH', 'OK', 'OR', 'PW', 'PA', 'PR', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VT', 'VA', 'VI', 'WA', 'WV', 'WI', 'WY'),
	lessons_learned varchar (516),
	budget_variance enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	schedule_variance enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	fte_utilization_rate_variance enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	latitude decimal(18,14),
	longitude decimal(18,14),
	risk_context_plans_count int (11),
	risk_context_plans_rating enum ('gap', 'vulnerability', 'strength'),
	risk_identification_plans_count int (11),
	risk_identification_plans_rating enum ('gap', 'vulnerability', 'strength'),
	risk_analysis_plans_count int (11),
	risk_analysis_plans_rating enum ('gap', 'vulnerability', 'strength'),
	risk_plans_communicated_count int (11),
	risk_plans_communicated_rating enum ('gap', 'vulnerability', 'strength'),
	last_modified_date date NOT NULL,
	PRIMARY KEY (project_details_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.project_partners;
CREATE TABLE IF NOT EXISTS eisdb.project_partners (
	project_partner_id int (11) NOT NULL AUTO_INCREMENT,
	project_partner_name varchar (75) NOT NULL,
	project_partner_details varchar (255) NOT NULL,
	on_site enum('T', 'F') NOT NULL,
	project_participation_status enum('pending', 'active', 'inactive') NOT NULL,
	lessons_learned varchar (516),
	last_modified_date date NOT NULL,
	corp_leadership_history_rating enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	financial_viability_rating enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	market_and_labeling_rating enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	physical_security_rating enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	cyber_security_rating enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	insider_threat_rating enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	regional_stability_rating enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	latitude decimal(18,14),
	longitude decimal(18,14),
	PRIMARY KEY (project_partner_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.project_products;
CREATE TABLE IF NOT EXISTS eisdb.project_products (
	project_product_id int (11) NOT NULL AUTO_INCREMENT,
	project_product_name varchar (75) NOT NULL,
	product_state enum ('active', 'archived'),
	product_version varchar (45),
	product_type varchar (45),
	product_description varchar (255),
	lessons_learned varchar (516),
	last_modified_date date NOT NULL,
	severity decimal(6,2),
	cvss_base_score decimal(6,2),
	cvss_score decimal(6,2),
	cvss_exploit_sub_score decimal(6,2),
	cvss_impact_sub_score decimal(6,2),
	cvss_version decimal(6,2),
	cvss_publish_date decimal(6,2),
	latitude decimal(18,14),
	longitude decimal(18,14),
	PRIMARY KEY (project_product_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.project_vulnerabilities;
CREATE TABLE IF NOT EXISTS eisdb.project_vulnerabilities (
	project_vulnerability_id int (11) NOT NULL AUTO_INCREMENT,
	project_vulnerability_name varchar (75) NOT NULL,
	project_vulnerability_type varchar (45),
	description varchar (255),
	lessons_learned varchar (516),
	last_modified_date date NOT NULL,
	vulnerability_severity decimal(6,2),
	vulnerability_score decimal(6,2),
	vulnerability_exploit_sub_score decimal(6,2),
	vulnerability_impact_sub_score decimal(6,2),
	latitude decimal(18,14),
	longitude decimal(18,14),
	PRIMARY KEY (project_vulnerability_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.user_profiles;
CREATE TABLE IF NOT EXISTS eisdb.user_profiles (
	user_profile_id int (11) NOT NULL AUTO_INCREMENT,
	email varchar(100) NOT NULL UNIQUE,
	login_status enum ('pending', 'preliminary_login', 'active', 'inactive'),
	first_name varchar (45) NOT NULL,
	middle_name varchar (45),
	last_name varchar (45) NOT NULL,
	job_title varchar (45),
	org_details varchar (255) NOT NULL,
	phone varchar (45) NOT NULL,
	registration_project_association_request varchar (255) NOT NULL,
	system_access_justification varchar (255) NOT NULL,
	profile_expires_on date,
	lock_account_until date,
	pwd_hash varchar (516) NOT NULL,
	pwd_salt varchar (75) NOT NULL,
	password_failure_count int (11),
	challenge_question_failure_count int (11),
	challenge_question_one varchar (75),
	challenge_question_one_answer varchar (75),
	challenge_question_two varchar (75),
	challenge_question_two_answer varchar (75),
	challenge_question_three varchar (75),
	challenge_question_three_answer varchar (75),
	notification_frequency enum ('opt-out', 'daily', 'weekly', 'bi-weekly', 'monthly', 'quarterly', 'yearly'),
	PRIMARY KEY (user_profile_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.flagged_assets;
CREATE TABLE IF NOT EXISTS eisdb.flagged_assets (
	flagged_id int (11) NOT NULL AUTO_INCREMENT,
	flagged_reason varchar (255) NOT NULL,
	flagged_risk_state enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	flagged_date date NOT NULL,
	unflagged_risk_state enum('Very High', 'High', 'Medium', 'Low', 'Very Low', 'Unknown') NOT NULL,
	unflagged_date date NOT NULL,
	PRIMARY KEY (flagged_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.project_systems;
CREATE TABLE IF NOT EXISTS eisdb.project_systems (
	system_id int (11) NOT NULL AUTO_INCREMENT,
	system_name varchar (75) NOT NULL,
	description varchar (255),
	last_modified_date date NOT NULL,
	PRIMARY KEY (system_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE eisdb.user_profiles
	ADD role_fk_profile int (11) NOT NULL,
	ADD risk_preference_fk int (11) NOT NULL, 
	ADD project_fk_profiles int (11) NOT NULL;
	
ALTER TABLE eisdb.user_profiles
	ADD CONSTRAINT role_fk_profile FOREIGN KEY (role_fk_profile) REFERENCES Role (role_id),
    ADD CONSTRAINT project_fk_profiles FOREIGN KEY (project_fk_profiles) REFERENCES projects (project_id),
	ADD CONSTRAINT risk_preference_fk FOREIGN KEY (risk_preference_fk) REFERENCES risk_preferences (risk_preference_id)  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE eisdb.risk_preferences
	ADD risk_preference_owner_fk int (11) NOT NULL,
	ADD last_modified_by_fk int (11) NOT NULL, 
	ADD project_fk_prefs int (11) NOT NULL;
	
ALTER TABLE eisdb.risk_preferences
    ADD CONSTRAINT project_fk_prefs FOREIGN KEY (project_fk_prefs) REFERENCES projects (project_id),
	ADD CONSTRAINT risk_preference_owner_fk FOREIGN KEY (risk_preference_owner_fk) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT last_modified_by_fk FOREIGN KEY (last_modified_by_fk) REFERENCES user_profiles (user_profile_id);

ALTER TABLE eisdb.projects 
	ADD user_profile_fk int (11) NOT NULL, 
	ADD project_details_fk int (11) NOT NULL;

ALTER TABLE eisdb.projects 
	ADD CONSTRAINT user_profile_fk FOREIGN KEY (user_profile_fk) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT project_details_fk FOREIGN KEY (project_details_fk) REFERENCES project_details (project_details_id);

ALTER TABLE eisdb.project_details 
	ADD flagged_project_fk int (11) NOT NULL, 
	ADD last_modified_by_fk_details int (11) NOT NULL, 
	ADD primary_poc_manager_fk int (11) NOT NULL,
	ADD primary_poc_executive_fk int (11) NOT NULL;

ALTER TABLE eisdb.project_details 
ADD CONSTRAINT flagged_project_fk FOREIGN KEY (flagged_project_fk) REFERENCES flagged_assets (flagged_id),
	ADD CONSTRAINT last_modified_by_fk_details FOREIGN KEY (last_modified_by_fk_details) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT primary_poc_manager_fk FOREIGN KEY (primary_poc_manager_fk) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT primary_poc_executive_fk FOREIGN KEY (primary_poc_executive_fk) REFERENCES user_profiles (user_profile_id);

ALTER TABLE eisdb.project_partners 
	ADD flagged_by_fk_partners int (11),
	ADD last_modified_by_fk_partners int (11) NOT NULL, 
	ADD project_fk_partners int (11) NOT NULL;

ALTER TABLE eisdb.project_partners
	ADD CONSTRAINT flagged_by_fk_partners FOREIGN KEY (flagged_by_fk_partners) REFERENCES flagged_assets (flagged_id),
	ADD CONSTRAINT last_modified_by_fk_partners FOREIGN KEY (last_modified_by_fk_partners) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT project_fk_partners FOREIGN KEY (project_fk_partners) REFERENCES projects (project_id);

ALTER TABLE eisdb.project_products 
	ADD flagged_by_fk_products int (11),
	ADD last_modified_by_fk_products int (11) NOT NULL, 
	ADD project_fk_products int (11) NOT NULL,
	ADD project_system_fk_products int (11) NOT NULL;

ALTER TABLE eisdb.project_products 
	ADD CONSTRAINT flagged_by_fk_products FOREIGN KEY (flagged_by_fk_products) REFERENCES flagged_assets (flagged_id),
	ADD CONSTRAINT last_modified_by_fk_products FOREIGN KEY (last_modified_by_fk_products) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT project_fk_products FOREIGN KEY (project_fk_products) REFERENCES projects (project_id),
	ADD CONSTRAINT project_system_fk_products FOREIGN KEY (project_system_fk_products) REFERENCES project_systems (system_id);

ALTER TABLE eisdb.project_vulnerabilities 
	ADD last_modified_by_fk_vulnerabilities int (11) NOT NULL, 
	ADD project_fk_vulnerabilities int (11) NOT NULL;

ALTER TABLE eisdb.project_vulnerabilities
	ADD CONSTRAINT last_modified_by_fk_vulnerabilities FOREIGN KEY (last_modified_by_fk_vulnerabilities) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT project_fk_vulnerabilities FOREIGN KEY (project_fk_vulnerabilities) REFERENCES projects (project_id);
	
ALTER TABLE eisdb.flagged_assets 
	ADD flagged_by_fk int (11) NOT NULL, 
	ADD unflagged_by_fk int (11) NOT NULL;

ALTER TABLE eisdb.flagged_assets
	ADD CONSTRAINT flagged_by_fk FOREIGN KEY (flagged_by_fk) REFERENCES flagged_assets (flagged_id),
	ADD CONSTRAINT unflagged_by_fk FOREIGN KEY (unflagged_by_fk) REFERENCES flagged_assets (flagged_id);

ALTER TABLE eisdb.project_vulnerabilities 
	ADD flagged_by_fk_vuln int (11) NOT NULL, 
	ADD project_system_fk_vuln int (11) NOT NULL;

ALTER TABLE eisdb.project_vulnerabilities
	ADD CONSTRAINT flagged_by_fk_vuln FOREIGN KEY (flagged_by_fk_vuln) REFERENCES flagged_assets (flagged_id),
	ADD CONSTRAINT project_system_fk_vuln FOREIGN KEY (project_system_fk_vuln) REFERENCES project_systems (system_id);
	
ALTER TABLE eisdb.project_systems 
	ADD project_fk_systems int (11) NOT NULL, 
	ADD last_modified_by_fk_systems int (11) NOT NULL;

ALTER TABLE eisdb.project_systems
	ADD CONSTRAINT project_fk_systems FOREIGN KEY (project_fk_systems) REFERENCES projects (project_id),
	ADD CONSTRAINT last_modified_by_fk_systems FOREIGN KEY (last_modified_by_fk_systems) REFERENCES user_profiles (user_profile_id);
	
INSERT INtO eisdb.Role (role_name, role_description, role_status)
VALUES ('administrator', 'can do anything in the system', 'active'),
('executive', 'can modify all projects', 'active'),
('manager', 'can modify projects for which they are associated', 'active'),
('internal_systemrolesroles', 'similar to manager except actions are that of an internal system', 'active'),
('external_system', 'similar to a manager except actions are that of an external system', 'active'),
('read_only', 'cannot modify content within the system', 'active'),
('restricted', 'cannot modify content within the system and cannot view attributes marked as confidential', 'active');

INSERT INTO `eisdb`.`user_profiles`
(`user_profile_id`,
`email`,
`login_status`,
`first_name`,
`middle_name`,
`last_name`,
`job_title`,
`org_details`,
`phone`,
`registration_project_association_request`,
`system_access_justification`,
`profile_expires_on`,
`lock_account_until`,
`pwd_hash`,
`pwd_salt`,
`password_failure_count`,
`challenge_question_failure_count`,
`challenge_question_one`,
`challenge_question_one_answer`,
`challenge_question_two`,
`challenge_question_two_answer`,
`challenge_question_three`,
`challenge_question_three_answer`,
`notification_frequency`,
`role_fk_profile`,
`risk_preference_fk`,
`project_fk_profiles`)
VALUES
('1', 'email001@email.com',
'active',
'TesterFirst01',
'TesterMiddle01',
'TesterLast01',
'My Job Title',
'My Org Details',
'555-555-5555',
'Project XYZ',
'I would like to be associated with project XYZ as I am the project manager',
'20200101',
'',
'hashaaaaadfasfqwfasdfarfasdfasdf',
'saltaaaaqwefasgqwrfqwfqwrefqefq',
'0',
'0',
'challenge_question_one',
'challenge_question_one_answer',
'challenge_question_two',
'challenge_question_two_answer',
'challenge_question_three',
'challenge_question_three_answer',
'weekly',
'1',
'1',
'1');

INSERT INTO `eisdb`.`risk_preferences` (`risk_preference_id`, `risk_preference_name`, `last_modified_date`, `overall_project_risk_tolerance`, `overall_project_risk_weight`, `overall_project_risk_priority`, `project_budget_variance_tolerance`, `project_budget_variance_weight`, `project_budget_variance_priority`, `project_schedule_variance_tolerance`, `project_schedule_variance_weight`, `project_schedule_variance_priority`, `project_fte_utilization_variance_tolerance`, `project_fte_utilization_variance_weight`, `project_fte_utilization_variance_priority`, `project_partner_risk_tolerance`, `project_partner_risk_weight`, `project_partner_risk_priority`, `project_product_risk_tolerance`, `project_product_risk_weight`, `project_product_risk_priority`, `risk_preference_owner_fk`, `last_modified_by_fk`, `project_fk_prefs`) VALUES ('1', 'Risk Name 001', '', 'Low', 'Low', 'Low', 'Med', 'Med', 'Med', 'High', 'High', 'High', 'Low', 'Low', 'Low', 'Med', 'Med', 'Med', 'High', 'High', 'High', '1', '1', '1');

INSERT INTO `eisdb`.`project_vulnerabilities` (`project_vulnerability_id`, `project_vulnerability_name`, `project_vulnerability_type`, `description`, `lessons_learned`, `last_modified_date`, `vulnerability_severity`, `vulnerability_score`, `vulnerability_exploit_sub_score`, `vulnerability_impact_sub_score`, `latitude`, `longitude`, `last_modified_by_fk_vulnerabilities`, `project_fk_vulnerabilities`, `flagged_by_fk_vuln`, `project_system_fk_vuln`) VALUES ('1', 'Vul Name 001', 'network', 'An open port on the network', 'my lessons learned 001', '20150101', 'Med', 'Med', 'Med', 'Med', '38.8981', '77.0208', '1', '1', '1', '1');

INSERT INTO `eisdb`.`project_systems` (`system_id`, `system_name`, `description`, `last_modified_date`, `project_fk_systems`, `last_modified_by_fk_systems`) VALUES ('1', 'System A', 'System A description', '20150101', '1', '1');

INSERT INTO `eisdb`.`projects` (`project_id`, `project_name`, `project_status`, `user_profile_fk`, `project_details_fk`) VALUES ('1', 'Project A', 'active', '1', '1');

INSERT INTO `eisdb`.`project_products` (`project_product_id`, `project_product_name`, `product_state`, `product_version`, `product_type`, `product_description`, `lessons_learned`, `last_modified_date`, `severity`, `cvss_base_score`, `cvss_score`, `cvss_exploit_sub_score`, `cvss_impact_sub_score`, `cvss_version`, `cvss_publish_date`, `latitude`, `longitude`, `flagged_by_fk_products`, `last_modified_by_fk_products`, `project_fk_products`) VALUES ('1', 'Product A', 'active', '1.1', 'Virus Scanner', 'Scans inbound and outbound messages', 'my lessons learned 001', '20150101', 'Med', 'Med', 'Med', 'Med', 'Med', '1.0', '20150101', '38.8981', '77.0208', '1', '1', '1', '1');

INSERT INTO `eisdb`.`project_partners` (`project_partner_id`, `project_partner_name`, `project_partner_details`, `on_site`, `project_participation_status`, `lessons_learned`, `last_modified_date`, `corp_leadership_history_rating`, `financial_viability_rating`, `market_and_labeling_rating`, `physical_security_rating`, `cyber_security_rating`, `insider_threat_rating`, `regional_stability_rating`, `latitude`, `longitude`, `flagged_by_fk_partners`, `last_modified_by_fk_partners`, `project_fk_partners`) VALUES ('1', 'Partner A', 'partner details', 'Y', 'active', 'my lessons learned', '20150101', 'Med', 'Med', 'Med', 'Med', 'Med', 'Med', 'Med', '38.8981', '77.0208', '1', '1', '1');

INSERT INTO `eisdb`.`project_details` (`project_details_id`, `org_details`, `country_code`, `state_province`, `lessons_learned`, `budget_variance`, `schedule_variance`, `fte_utilization_rate_variance`, `latitude`, `longitude`, `risk_context_plans_count`, `risk_context_plans_rating`, `risk_identification_plans_count`, `risk_identification_plans_rating`, `risk_analysis_plans_count`, `risk_analysis_plans_rating`, `risk_plans_communicated_count`, `risk_plans_communicated_rating`, `last_modified_date`, `flagged_project_fk`, `last_modified_by_fk_details`, `primary_poc_manager_fk`, `primary_poc_executive_fk`) VALUES ('1', 'project details', 'USA', 'MD', 'my lessons learned', 'Med', 'Med', 'Med', '38.8981', '77.0208', '1', 'Med', '3', 'High', '3', 'Med', '3', 'High', '20150101', '1', '1', '1', '1');

INSERT INTO `eisdb`.`flagged_assets` (`flagged_id`, `flagged_reason`, `flagged_risk_state`, `flagged_date`, `unflagged_risk_state`, `unflagged_date`, `flagged_by_fk`, `unflagged_by_fk`) VALUES ('1', 'High risk', 'active', '20150101', '', '', '1', '');

INSERT INTO `eisdb`.`email_templates` (`email_template_id`, `email_template_name`, `email_subject`, `email_body`, `email_from`, `last_modified_date`) VALUES ('1', 'New Account Request', 'New EIS Account Request', 'Please create an account for user X given justification XYZ', 'somePerson@xyz.com', '20150101');

INSERT INTO `eisdb`.`contact_us` (`contact_us_id`, `email`, `name`, `category`, `user_comment`, `last_modified_date`) VALUES ('1', 'somePerson@xyz.com', 'Person A', 'comment', 'Great system. Very useful.', '20150101');


SET FOREIGN_KEY_CHECKS = 1;