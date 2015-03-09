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

DROP TABLE IF EXISTS eisdb.roles;
CREATE TABLE IF NOT EXISTS eisdb.roles (
	role_id int (11) NOT NULL AUTO_INCREMENT,
	role_name varchar (45) NOT NULL,
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
	PRIMARY KEY (project_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.project_details;
CREATE TABLE IF NOT EXISTS eisdb.project_details (
	project_details_id int (11) NOT NULL AUTO_INCREMENT,
	org_details varchar (255) NOT NULL,
	country_code enum('USA'),
	state_province enum ('AL', 'AK', 'AS', 'AZ', 'AR', 'CA', 'CO', 'CT', 'DE', 'DC', 'FL', 'GA', 'GU', 'HI', 'ID', 'IL', 'IN', 'IA', 'KS', 'KY', 'LA', 'ME', 'MD', 'MH', 'MA', 'MI', 'FM', 'MN', 'MS', 'MO', 'MT', 'NE', 'NV', 'NH', 'NJ', 'NM', 'NY', 'NC', 'ND', 'MP', 'OH', 'OK', 'OR', 'PW', 'PA', 'PR', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VT', 'VA', 'VI', 'WA', 'WV', 'WI', 'WY'),
	lessons_learned varchar (516),
	budget_variance int (11),
	schedule_variance int (11),
	fte_utilization_rate_variance int (11),
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
	PRIMARY KEY (project_partner_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.project_products;
CREATE TABLE IF NOT EXISTS eisdb.project_products (
	project_product_id int (11) NOT NULL AUTO_INCREMENT,
	project_product_name varchar (75) NOT NULL,
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
	PRIMARY KEY (project_product_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.user_profiles;
CREATE TABLE IF NOT EXISTS eisdb.user_profiles (
	user_profile_id int (11) NOT NULL AUTO_INCREMENT,
	email varchar(100) NOT NULL UNIQUE,
	login_status enum('pending', 'preliminary_login', 'active', 'inactive'),
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
	notification_frequency enum('opt-out', 'daily', 'weekly', 'bi-weekly', 'monthly', 'quarterly', 'yearly'),
	PRIMARY KEY (user_profile_id),
	role_id_fk int (11) NOT NULL,
	CONSTRAINT role_id_fk FOREIGN KEY (role_id_fk) REFERENCES roles (role_id),
	risk_preference_fk int (11) NOT NULL,
	CONSTRAINT risk_preference_fk FOREIGN KEY (risk_preference_fk) REFERENCES risk_preferences (risk_preference_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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
	ADD last_modified_by_fk_details int (11) NOT NULL, 
	ADD primary_poc_fk int (11) NOT NULL,
	ADD secondary_poc_fk int (11) NOT NULL;

ALTER TABLE eisdb.project_details 
	ADD CONSTRAINT last_modified_by_fk_details FOREIGN KEY (last_modified_by_fk_details) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT primary_poc_fk FOREIGN KEY (primary_poc_fk) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT secondary_poc_fk FOREIGN KEY (secondary_poc_fk) REFERENCES user_profiles (user_profile_id);

ALTER TABLE eisdb.project_partners 
	ADD last_modified_by_fk_partners int (11) NOT NULL, 
	ADD project_fk_partners int (11) NOT NULL;

ALTER TABLE eisdb.project_partners 
	ADD CONSTRAINT last_modified_by_fk_partners FOREIGN KEY (last_modified_by_fk_partners) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT project_fk_partners FOREIGN KEY (project_fk_partners) REFERENCES projects (project_id);

ALTER TABLE eisdb.project_products 
	ADD last_modified_by_fk_products int (11) NOT NULL, 
	ADD project_fk_products int (11) NOT NULL;

ALTER TABLE eisdb.project_products 
	ADD CONSTRAINT last_modified_by_fk_products FOREIGN KEY (last_modified_by_fk_products) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT project_fk_products FOREIGN KEY (project_fk_products) REFERENCES projects (project_id);

SET FOREIGN_KEY_CHECKS = 1;