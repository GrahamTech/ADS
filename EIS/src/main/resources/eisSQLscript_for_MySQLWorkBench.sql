create database if not exists eisdb;
use eisdb;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS eisdb.nvd_entry_message;
CREATE TABLE IF NOT EXISTS eisdb.nvd_entry_message (
	entry_message_id int (11) NOT NULL AUTO_INCREMENT,
	cve_id varchar (45) NOT NULL UNIQUE,
	published_datetime TIMESTAMP,
	last_modified_datetime TIMESTAMP,
	generated_on_datetime TIMESTAMP,
	score decimal(6,2),
	access_vector enum ('UNKNOWN', 'LOCAL', 'NETWORK', 'ADJACENT_NETWORK') NOT NULL,
	access_complexity enum ('UNKNOWN', 'HIGH', 'MEDIUM', 'LOW') NOT NULL,
	authentication enum ('UNKNOWN', 'NONE', 'SINGLE_INSTANCE', 'MULTIPLE_INSTANCE') NOT NULL,
	confidentiality_impact enum ('UNKNOWN', 'NONE', 'PARTIAL', 'COMPLETE') NOT NULL,
	integrity_impact enum ('UNKNOWN', 'NONE', 'PARTIAL', 'COMPLETE') NOT NULL,
	availability_impact enum ('UNKNOWN', 'NONE', 'PARTIAL', 'COMPLETE') NOT NULL,
	source varchar (75),
	summary varchar (775),
	latitude decimal(18,14),
	longitude decimal(18,14),
	PRIMARY KEY (entry_message_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.nvd_entry_vulnerable_software;
CREATE TABLE IF NOT EXISTS eisdb.nvd_entry_vulnerable_software (
	vulnerable_software_id int (11) NOT NULL AUTO_INCREMENT,
	vulnerable_software_name varchar (125),
	PRIMARY KEY (vulnerable_software_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.email_templates;
CREATE TABLE IF NOT EXISTS eisdb.email_templates (
	email_template_id int (11) NOT NULL AUTO_INCREMENT,
	email_template_name varchar (45) NOT NULL UNIQUE,
	email_subject varchar (75) NOT NULL,
	email_body varchar (516) NOT NULL,
	email_from varchar (75),
	last_modified_date TIMESTAMP,
	PRIMARY KEY (email_template_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.contact_us;
CREATE TABLE IF NOT EXISTS eisdb.contact_us (
	contact_us_id int (11) NOT NULL AUTO_INCREMENT,
	email varchar (75) NOT NULL,
	name varchar (45),
	category enum ('GENERAL', 'SUGGESTION', 'BUG') NOT NULL,
	user_comment varchar (256) NOT NULL,
	last_modified_date TIMESTAMP NOT NULL,
	PRIMARY KEY (contact_us_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.roles;
CREATE TABLE IF NOT EXISTS eisdb.roles (
	role_id int (11) NOT NULL AUTO_INCREMENT,
	role_name enum ('ADMINISTRATOR', 'EXECUTIVE', 'MANAGER', 'INTERNAL_SYSTEM', 'EXTERNAL_SYSTEM', 'READ_ONLY', 'RESTRICTED') NOT NULL,
	role_description varchar (255) NOT NULL,
	role_status enum('ACTIVE', 'INACTIVE'),
	PRIMARY KEY (role_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.user_profiles;
CREATE TABLE IF NOT EXISTS eisdb.user_profiles (
	user_profile_id int (11) NOT NULL AUTO_INCREMENT,
	email varchar(100) NOT NULL UNIQUE,
	primary_role varchar(45) NOT NULL,
	login_status enum ('PENDING', 'PRELIMINARY_LOGIN', 'ACTIVE', 'INACTIVE'),
	first_name varchar (45),
	middle_name varchar (45),
	last_name varchar (45),
	job_title varchar (45),
	org_details varchar (255),
	phone varchar (45),
	system_access_justification varchar (255),
	profile_expires_on TIMESTAMP,
	lock_account_until TIMESTAMP,
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
	notification_frequency enum ('OPT_OUT', 'DAILY', 'WEEKLY', 'BI_WEEKLY', 'MONTHLY', 'QUARTERLY', 'YEARLY'),
	PRIMARY KEY (user_profile_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.user_profiles_roles;
CREATE TABLE  eisdb.user_profiles_roles (
  user_profile_id int(11) UNSIGNED NOT NULL,
  role_id int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (user_profile_id,role_id)
) DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.risk_preferences;
CREATE TABLE IF NOT EXISTS eisdb.risk_preferences (
	risk_preference_id int (11) NOT NULL AUTO_INCREMENT,
	risk_preference_name varchar (45) NOT NULL,
	preference_status enum('ACTIVE', 'INACTIVE') NOT NULL,
	last_modified_date TIMESTAMP NOT NULL,
	overall_project_risk_tolerance decimal(6,2),
	overall_project_risk_weight decimal(6,2),
	overall_project_risk_priority enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	project_budget_variance_tolerance decimal(6,2),
	project_budget_variance_weight decimal(6,2),
	project_budget_variance_priority enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	project_schedule_variance_tolerance decimal(6,2),
	project_schedule_variance_weight decimal(6,2),
	project_schedule_variance_priority enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	project_fte_utilization_variance_tolerance decimal(6,2),
	project_fte_utilization_variance_weight decimal(6,2),
	project_fte_utilization_variance_priority enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	project_partner_risk_tolerance decimal(6,2),
	project_partner_risk_weight decimal(6,2),
	project_partner_risk_priority enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	project_product_risk_tolerance decimal(6,2),
	project_product_risk_weight decimal(6,2),
	project_product_risk_priority enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	PRIMARY KEY (risk_preference_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.projects;
CREATE TABLE IF NOT EXISTS eisdb.projects (
	project_id int (11) NOT NULL AUTO_INCREMENT,
	project_name varchar (75) NOT NULL UNIQUE,
	project_status enum ('ACTIVE', 'ARCHIVED'),
	PRIMARY KEY (project_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.project_details;
CREATE TABLE IF NOT EXISTS eisdb.project_details (
	project_details_id int (11) NOT NULL AUTO_INCREMENT,
	org_details varchar (255) NOT NULL,
	country_code enum('USA'),
	state_province enum ('AL', 'AK', 'AS', 'AZ', 'AR', 'CA', 'CO', 'CT', 'DE', 'DC', 'FL', 'GA', 'GU', 'HI', 'ID', 'IL', 'IN', 'IA', 'KS', 'KY', 'LA', 'ME', 'MD', 'MH', 'MA', 'MI', 'FM', 'MN', 'MS', 'MO', 'MT', 'NE', 'NV', 'NH', 'NJ', 'NM', 'NY', 'NC', 'ND', 'MP', 'OH', 'OK', 'OR', 'PW', 'PA', 'PR', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VT', 'VA', 'VI', 'WA', 'WV', 'WI', 'WY'),
	lessons_learned varchar (516),
	budget_variance enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	schedule_variance enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	fte_utilization_rate_variance enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	latitude decimal(18,14),
	longitude decimal(18,14),
	risk_context_plans_count int (11),
	risk_context_plans_rating enum ('GAP', 'VULNERABILITY', 'STRENGTH'),
	risk_identification_plans_count int (11),
	risk_identification_plans_rating enum ('GAP', 'VULNERABILITY', 'STRENGTH'),
	risk_analysis_plans_count int (11),
	risk_analysis_plans_rating enum ('GAP', 'VULNERABILITY', 'STRENGTH'),
	risk_plans_communicated_count int (11),
	risk_plans_communicated_rating enum ('GAP', 'VULNERABILITY', 'STRENGTH'),
	last_modified_date TIMESTAMP NOT NULL,
	PRIMARY KEY (project_details_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.project_systems;
CREATE TABLE IF NOT EXISTS eisdb.project_systems (
	system_id int (11) NOT NULL AUTO_INCREMENT,
	system_name varchar (75) NOT NULL,
	description varchar (255),
	last_modified_date TIMESTAMP NOT NULL,
	PRIMARY KEY (system_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.project_partners;
CREATE TABLE IF NOT EXISTS eisdb.project_partners (
	project_partner_id int (11) NOT NULL AUTO_INCREMENT,
	project_partner_name varchar (75) NOT NULL,
	project_partner_details varchar (255) NOT NULL,
	on_site enum('ACTIVE', 'INACTIVE') NOT NULL,
	project_participation_status enum('PENDING', 'ACTIVE', 'INACTIVE') NOT NULL,
	last_modified_date TIMESTAMP NOT NULL,
	corp_leadership_history_rating enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	financial_viability_rating enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	market_and_labeling_rating enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	physical_security_rating enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	cyber_security_rating enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	insider_threat_rating enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	regional_stability_rating enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	lessons_learned varchar (516),
	latitude decimal(18,14),
	longitude decimal(18,14),
	PRIMARY KEY (project_partner_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.system_products;
CREATE TABLE IF NOT EXISTS eisdb.system_products (
	product_id int (11) NOT NULL AUTO_INCREMENT,
	product_name varchar (75) NOT NULL,
	product_state enum ('ACTIVE', 'ARCHIVED') NOT NULL,
	lessons_learned varchar (516),
	
	published_datetime TIMESTAMP,
	last_modified_datetime TIMESTAMP,
	generated_on_datetime TIMESTAMP,
	score decimal(6,2),
	access_vector enum ('UNKNOWN', 'LOCAL', 'NETWORK', 'ADJACENT_NETWORK') NOT NULL,
	access_complexity enum ('UNKNOWN', 'HIGH', 'MEDIUM', 'LOW') NOT NULL,
	authentication enum ('UNKNOWN', 'NONE', 'SINGLE_INSTANCE', 'MULTIPLE_INSTANCE') NOT NULL,
	confidentiality_impact enum ('UNKNOWN', 'NONE', 'PARTIAL', 'COMPLETE') NOT NULL,
	integrity_impact enum ('UNKNOWN', 'NONE', 'PARTIAL', 'COMPLETE') NOT NULL,
	availability_impact enum ('UNKNOWN', 'NONE', 'PARTIAL', 'COMPLETE') NOT NULL,
	source varchar (75),
	summary varchar (775),
	latitude decimal(18,14),
	longitude decimal(18,14),
	
	#product_version varchar (45),
	#product_type varchar (45),
	#product_description varchar (255),
	#severity decimal(6,2),
	#cvss_base_score decimal(6,2),
	#cvss_score decimal(6,2),
	#cvss_exploit_sub_score decimal(6,2),
	#cvss_impact_sub_score decimal(6,2),
	#cvss_version decimal(6,2),
	#cvss_publish_date TIMESTAMP,
	#latitude decimal(18,14),
	#longitude decimal(18,14),
	
	PRIMARY KEY (product_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.system_vulnerabilities;
CREATE TABLE IF NOT EXISTS eisdb.system_vulnerabilities (
	vulnerability_id int (11) NOT NULL AUTO_INCREMENT,
	vulnerability_name varchar (75) NOT NULL,
	vulnerability_type enum('API_Abuse', 'Authentication', 'Authorization', 'Availability', 'Code_Permission', 'Code_Quality', 'Configuration', 'Cryptographic', 'Encoding', 'Environmental', 'Error_Handling', 'General_Logic_Error', 'Input_Validation', 'Logging_and_Auditing', 'Password_Management', 'Path', 'Protocol_Errors', 'Range_and_Type_Error', 'Sensitive_Data_Protection', 'Session_Management', 'Synchronization_and_Timing', 'Unsafe_Mobile_Code', 'Use_of_Dangerous_API', 'Unknown') NOT NULL,
	lessons_learned varchar (516),
	
	published_datetime TIMESTAMP,
	last_modified_datetime TIMESTAMP,
	generated_on_datetime TIMESTAMP,
	score decimal(6,2),
	access_vector enum ('UNKNOWN', 'LOCAL', 'NETWORK', 'ADJACENT_NETWORK') NOT NULL,
	access_complexity enum ('UNKNOWN', 'HIGH', 'MEDIUM', 'LOW') NOT NULL,
	authentication enum ('UNKNOWN', 'NONE', 'SINGLE_INSTANCE', 'MULTIPLE_INSTANCE') NOT NULL,
	confidentiality_impact enum ('UNKNOWN', 'NONE', 'PARTIAL', 'COMPLETE') NOT NULL,
	integrity_impact enum ('UNKNOWN', 'NONE', 'PARTIAL', 'COMPLETE') NOT NULL,
	availability_impact enum ('UNKNOWN', 'NONE', 'PARTIAL', 'COMPLETE') NOT NULL,
	source varchar (75),
	summary varchar (775),
	latitude decimal(18,14),
	longitude decimal(18,14),
	
	#description varchar (255),
	#last_modified_date TIMESTAMP NOT NULL,
	#vulnerability_severity decimal(6,2),
	#vulnerability_score decimal(6,2),
	#vulnerability_exploit_sub_score decimal(6,2),
	#vulnerability_impact_sub_score decimal(6,2),
	#latitude decimal(18,14),
	#longitude decimal(18,14),
	
	PRIMARY KEY (vulnerability_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eisdb.flagged_assets;
CREATE TABLE IF NOT EXISTS eisdb.flagged_assets (
	flagged_id int (11) NOT NULL AUTO_INCREMENT,
	flagged_reason varchar (255) NOT NULL,
	flagged_risk_state enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	flagged_date TIMESTAMP NOT NULL,
	unflagged_risk_state enum('HIGH', 'MEDIUM', 'LOW', 'UNKNOWN') NOT NULL,
	unflagged_date TIMESTAMP NOT NULL,
	PRIMARY KEY (flagged_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE eisdb.system_vulnerabilities 
	ADD flagged_by_fk_vulnerabilities int (11),
    ADD project_system_fk_vulnerabilities int (11),
	ADD last_modified_by_fk_vulnerabilities2 int (11);

ALTER TABLE eisdb.system_vulnerabilities
	ADD CONSTRAINT flagged_by_fk_vulnerabilities FOREIGN KEY (flagged_by_fk_vulnerabilities) REFERENCES flagged_assets (flagged_id),
	ADD CONSTRAINT project_system_fk_vulnerabilities FOREIGN KEY (project_system_fk_vulnerabilities) REFERENCES project_systems (system_id),
	ADD CONSTRAINT last_modified_by_fk_vulnerabilities2 FOREIGN KEY (last_modified_by_fk_vulnerabilities2) REFERENCES user_profiles (user_profile_id);

ALTER TABLE eisdb.system_products 
	ADD flagged_by_fk_products int (11),
	ADD last_modified_by_fk_products int (11),
	ADD project_system_fk_products int (11);

ALTER TABLE eisdb.system_products 
	ADD CONSTRAINT flagged_by_fk_products FOREIGN KEY (flagged_by_fk_products) REFERENCES flagged_assets (flagged_id),
	ADD CONSTRAINT last_modified_by_fk_products FOREIGN KEY (last_modified_by_fk_products) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT project_system_fk_products FOREIGN KEY (project_system_fk_products) REFERENCES project_systems (system_id);

ALTER TABLE eisdb.nvd_entry_vulnerable_software
	ADD entry_message_id_fk int (11);
	
ALTER TABLE eisdb.nvd_entry_vulnerable_software
  	ADD CONSTRAINT entry_message_id_fk FOREIGN KEY (entry_message_id_fk) REFERENCES nvd_entry_message (entry_message_id);

ALTER TABLE eisdb.nvd_entry_message
	ADD flagged_by_fk_nvd int (11),
	ADD system_id_fk int (11);
	
ALTER TABLE eisdb.nvd_entry_message
	ADD CONSTRAINT flagged_by_fk_nvd FOREIGN KEY (flagged_by_fk_nvd) REFERENCES flagged_assets (flagged_id),
  	ADD CONSTRAINT system_id_fk FOREIGN KEY (system_id_fk) REFERENCES project_systems (system_id);
	
ALTER TABLE eisdb.user_profiles_roles
	ADD role_id_fk int (11),
	ADD user_profile_id_fk int (11);
	
ALTER TABLE eisdb.user_profiles_roles
	ADD CONSTRAINT role_id_fk FOREIGN KEY (role_id_fk) REFERENCES roles (role_id),
  	ADD CONSTRAINT user_profile_id_fk FOREIGN KEY (user_profile_id_fk) REFERENCES user_profiles (user_profile_id);
	
ALTER TABLE eisdb.user_profiles
	ADD risk_preference_fk int (11), 
	ADD project_fk_profiles int (11);
	
ALTER TABLE eisdb.user_profiles
    ADD CONSTRAINT project_fk_profiles FOREIGN KEY (project_fk_profiles) REFERENCES projects (project_id),
	ADD CONSTRAINT risk_preference_fk FOREIGN KEY (risk_preference_fk) REFERENCES risk_preferences (risk_preference_id)  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE eisdb.risk_preferences
	ADD risk_preference_owner_fk int (11),
	ADD last_modified_by_fk int (11), 
	ADD project_fk_prefs int (11);
	
ALTER TABLE eisdb.risk_preferences
    ADD CONSTRAINT project_fk_prefs FOREIGN KEY (project_fk_prefs) REFERENCES projects (project_id),
	ADD CONSTRAINT risk_preference_owner_fk FOREIGN KEY (risk_preference_owner_fk) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT last_modified_by_fk FOREIGN KEY (last_modified_by_fk) REFERENCES user_profiles (user_profile_id);

ALTER TABLE eisdb.projects 
	ADD flagged_fk_project int (11),
	ADD user_profile_fk int (11), 
	ADD project_details_fk int (11);

ALTER TABLE eisdb.projects 
	ADD CONSTRAINT flagged_fk_project FOREIGN KEY (flagged_fk_project) REFERENCES flagged_assets (flagged_id),
	ADD CONSTRAINT user_profile_fk FOREIGN KEY (user_profile_fk) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT project_details_fk FOREIGN KEY (project_details_fk) REFERENCES project_details (project_details_id);

ALTER TABLE eisdb.project_details  
	ADD last_modified_by_fk_details int (11), 
	ADD primary_poc_manager_fk int (11),
	ADD primary_poc_executive_fk int (11);

ALTER TABLE eisdb.project_details 
	ADD CONSTRAINT last_modified_by_fk_details FOREIGN KEY (last_modified_by_fk_details) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT primary_poc_manager_fk FOREIGN KEY (primary_poc_manager_fk) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT primary_poc_executive_fk FOREIGN KEY (primary_poc_executive_fk) REFERENCES user_profiles (user_profile_id);

ALTER TABLE eisdb.project_partners 
	ADD flagged_by_fk_partners int (11),
	ADD last_modified_by_fk_partners int (11), 
	ADD project_fk_partners int (11);

ALTER TABLE eisdb.project_partners
	ADD CONSTRAINT flagged_by_fk_partners FOREIGN KEY (flagged_by_fk_partners) REFERENCES flagged_assets (flagged_id),
	ADD CONSTRAINT last_modified_by_fk_partners FOREIGN KEY (last_modified_by_fk_partners) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT project_fk_partners FOREIGN KEY (project_fk_partners) REFERENCES projects (project_id);

ALTER TABLE eisdb.project_systems 
	ADD flagged_by_fk_systems int (11),
	ADD project_fk_systems int (11), 
	ADD last_modified_by_fk_systems int (11);

ALTER TABLE eisdb.project_systems
	ADD CONSTRAINT flagged_by_fk_systems FOREIGN KEY (flagged_by_fk_systems) REFERENCES flagged_assets (flagged_id),
	ADD CONSTRAINT project_fk_systems FOREIGN KEY (project_fk_systems) REFERENCES projects (project_id),
	ADD CONSTRAINT last_modified_by_fk_systems FOREIGN KEY (last_modified_by_fk_systems) REFERENCES user_profiles (user_profile_id);

ALTER TABLE eisdb.flagged_assets 
	ADD flagged_by_fk_assets int (11), 
	ADD unflagged_by_fk_assets int (11);

ALTER TABLE eisdb.flagged_assets
	ADD CONSTRAINT flagged_by_fk_assets FOREIGN KEY (flagged_by_fk_assets) REFERENCES user_profiles (user_profile_id),
	ADD CONSTRAINT unflagged_by_fk_assets FOREIGN KEY (unflagged_by_fk_assets) REFERENCES user_profiles (user_profile_id);

INSERT INTO `eisdb`.`system_vulnerabilities` (`vulnerability_id`, `vulnerability_name`, `vulnerability_type`, `lessons_learned`, `published_datetime`, `last_modified_datetime`, `generated_on_datetime`, `score`, `access_vector`, `access_complexity`, `authentication`, `confidentiality_impact`, `integrity_impact`, `availability_impact`, `source`, `summary`, `latitude`, `longitude`, `flagged_by_fk_vulnerabilities`, `project_system_fk_vulnerabilities`, `last_modified_by_fk_vulnerabilities2`)
VALUES ('1', 'API Denial of Service', 'API_Abuse', 'Lesson learned 001', '2015-01-01', '2015-01-01', '2015-01-01', '1.8', 'NETWORK', 'MEDIUM', 'SINGLE_INSTANCE', 'PARTIAL', 'COMPLETE', 'NONE', 'the source 001', 'The Summary 001', '38.8981', '77.0208',  '1', '1', '1');

INSERT INTO `eisdb`.`system_vulnerabilities` (`vulnerability_id`, `vulnerability_name`, `vulnerability_type`, `lessons_learned`, `published_datetime`, `last_modified_datetime`, `generated_on_datetime`, `score`, `access_vector`, `access_complexity`, `authentication`, `confidentiality_impact`, `integrity_impact`, `availability_impact`, `source`, `summary`, `latitude`, `longitude`, `flagged_by_fk_vulnerabilities`, `project_system_fk_vulnerabilities`, `last_modified_by_fk_vulnerabilities2`)
VALUES ('2', 'API Denial of Service', 'API_Abuse', 'Lesson learned 002', '2015-01-01', '2015-01-01', '2015-01-01', '1.8', 'NETWORK', 'MEDIUM', 'SINGLE_INSTANCE', 'PARTIAL', 'COMPLETE', 'NONE', 'the source 001', 'The Summary 001', '38.8981', '77.0208',  '1', '1', '1');

INSERT INTO `eisdb`.`system_products` (`product_id`, `product_name`, `product_state`, `lessons_learned`, `published_datetime`, `last_modified_datetime`, `generated_on_datetime`, `score`, `access_vector`, `access_complexity`, `authentication`, `confidentiality_impact`, `integrity_impact`, `availability_impact`, `source`, `summary`, `latitude`, `longitude`, `flagged_by_fk_products`, `last_modified_by_fk_products`, `project_system_fk_products`)
VALUES ('1', 'Product Name 001', 'ACTIVE', 'Lesson Learned 001', '2015-01-01', '2015-01-01', '2015-01-01', '1.8', 'NETWORK', 'MEDIUM', 'SINGLE_INSTANCE', 'PARTIAL', 'COMPLETE', 'NONE', 'the source 001', 'The summary 001', '38.8981', '77.0208', '1', '1', '1');

INSERT INTO `eisdb`.`system_products` (`product_id`, `product_name`, `product_state`, `lessons_learned`, `published_datetime`, `last_modified_datetime`, `generated_on_datetime`, `score`, `access_vector`, `access_complexity`, `authentication`, `confidentiality_impact`, `integrity_impact`, `availability_impact`, `source`, `summary`, `latitude`, `longitude`, `flagged_by_fk_products`, `last_modified_by_fk_products`, `project_system_fk_products`)
VALUES ('2', 'Product Name 002', 'ACTIVE', 'Lesson Learned 002', '2015-01-01', '2015-01-01', '2015-01-01', '1.8', 'NETWORK', 'MEDIUM', 'SINGLE_INSTANCE', 'PARTIAL', 'COMPLETE', 'NONE', 'the source 001', 'The summary 001', '38.8981', '77.0208', '1', '1', '1');

#INSERT INTO `eisdb`.`system_vulnerabilities` (`vulnerability_id`, `vulnerability_name`, `vulnerability_type`, `description`, `lessons_learned`, `last_modified_date`, `vulnerability_severity`, `vulnerability_score`, `vulnerability_exploit_sub_score`, `vulnerability_impact_sub_score`, `latitude`, `longitude`, `flagged_by_fk_vulnerabilities`, `last_modified_by_fk_vulnerabilities2`, `project_system_fk_vulnerabilities`) VALUES ('1', 'Vul Name 001', 'Unknown', 'An open port on the network', 'my lessons learned 001', '20150101', 'MEDIUM', 'MEDIUM', 'MEDIUM', 'MEDIUM', '38.8981', '77.0208', '1', '1', '1');
#INSERT INTO `eisdb`.`system_vulnerabilities` (`vulnerability_id`, `vulnerability_name`, `vulnerability_type`, `description`, `lessons_learned`, `last_modified_date`, `vulnerability_severity`, `vulnerability_score`, `vulnerability_exploit_sub_score`, `vulnerability_impact_sub_score`, `latitude`, `longitude`, `flagged_by_fk_vulnerabilities`, `last_modified_by_fk_vulnerabilities2`, `project_system_fk_vulnerabilities`) VALUES ('2', 'Vul Name 002', 'API_Abuse', 'Spam', 'my lessons learned 001', '20150101', 'MEDIUM', 'MEDIUM', 'MEDIUM', 'MEDIUM', '38.8981', '77.0208', '1', '1', '1');

#INSERT INTO `eisdb`.`system_products` (`product_id`, `product_name`, `product_state`, `product_version`, `product_type`, `product_description`, `lessons_learned`, `last_modified_date`, `severity`, `cvss_base_score`, `cvss_score`, `cvss_exploit_sub_score`, `cvss_impact_sub_score`, `cvss_version`, `cvss_publish_date`, `latitude`, `longitude`, `flagged_by_fk_products`, `last_modified_by_fk_products`, `project_system_fk_products`) VALUES ('1', 'Product A', 'ACTIVE', '1.0', 'virus scanner', 'scans incoming and outgoing messages', 'my lessons learned', '20150101', '.5', '.5', '.5', '.5', '.5', '1.1', '20150101', '17.42323', '18.24343', '1', '1', '1');
#INSERT INTO `eisdb`.`system_products` (`product_id`, `product_name`, `product_state`, `product_version`, `product_type`, `product_description`, `lessons_learned`, `last_modified_date`, `severity`, `cvss_base_score`, `cvss_score`, `cvss_exploit_sub_score`, `cvss_impact_sub_score`, `cvss_version`, `cvss_publish_date`, `latitude`, `longitude`, `flagged_by_fk_products`, `last_modified_by_fk_products`, `project_system_fk_products`) VALUES ('2', 'Product B', 'ACTIVE', '2.0', 'Server', 'Host server', 'my lessons learned', '20150101', '.5', '.5', '.5', '.5', '.5', '1.1', '20150101', '17.42323', '18.24343', '1', '1', '1');
	
INSERT INtO eisdb.roles (role_name, role_description, role_status)
VALUES ('ADMINISTRATOR', 'can do anything in the system', 'ACTIVE'),
('EXECUTIVE', 'can modify all projects', 'ACTIVE'),
('MANAGER', 'can modify projects for which they are associated', 'ACTIVE'),
('INTERNAL_SYSTEM', 'similar to manager except actions are that of an internal system', 'ACTIVE'),
('EXTERNAL_SYSTEM', 'similar to a manager except actions are that of an external system', 'ACTIVE'),
('READ_ONLY', 'cannot modify content within the system', 'ACTIVE'),
('RESTRICTED', 'cannot modify content within the system and cannot view attributes marked as confidential', 'ACTIVE');

INSERT INTO `eisdb`.`user_profiles`
(`user_profile_id`,
`email`,
`primary_role`,
`login_status`,
`first_name`,
`middle_name`,
`last_name`,
`job_title`,
`org_details`,
`phone`,
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
`risk_preference_fk`,
`project_fk_profiles`)
VALUES
('1',
'email001@email.com',
'ADMINISTRATOR',
'ACTIVE',
'TesterFirst01',
'TesterMiddle01',
'TesterLast01',
'My Job Title',
'My Org Details',
'555-555-5555',
'I would like to be associated with project XYZ as I am the project manager',
'20200101',
'20150101',
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
'WEEKLY',
'1',
'1');

INSERT INTO `eisdb`.`risk_preferences` (`risk_preference_id`, `risk_preference_name`, `preference_status`, `last_modified_date`, `overall_project_risk_tolerance`, `overall_project_risk_weight`, `overall_project_risk_priority`, `project_budget_variance_tolerance`, `project_budget_variance_weight`, `project_budget_variance_priority`, `project_schedule_variance_tolerance`, `project_schedule_variance_weight`, `project_schedule_variance_priority`, `project_fte_utilization_variance_tolerance`, `project_fte_utilization_variance_weight`, `project_fte_utilization_variance_priority`, `project_partner_risk_tolerance`, `project_partner_risk_weight`, `project_partner_risk_priority`, `project_product_risk_tolerance`, `project_product_risk_weight`, `project_product_risk_priority`, `risk_preference_owner_fk`, `last_modified_by_fk`, `project_fk_prefs`) VALUES ('1', 'Risk Name 001', 'ACTIVE', '20150101', '.50', '.50', 'low', '.50', '.50', 'MEDIUM', '.50', '.50', 'HIGH', '.50', '.50', 'low', '.50', '.50', 'MEDIUM', '.50', '.50', 'HIGH', '1', '1', '1');
INSERT INTO `eisdb`.`risk_preferences` (`risk_preference_id`, `risk_preference_name`, `preference_status`, `last_modified_date`, `overall_project_risk_tolerance`, `overall_project_risk_weight`, `overall_project_risk_priority`, `project_budget_variance_tolerance`, `project_budget_variance_weight`, `project_budget_variance_priority`, `project_schedule_variance_tolerance`, `project_schedule_variance_weight`, `project_schedule_variance_priority`, `project_fte_utilization_variance_tolerance`, `project_fte_utilization_variance_weight`, `project_fte_utilization_variance_priority`, `project_partner_risk_tolerance`, `project_partner_risk_weight`, `project_partner_risk_priority`, `project_product_risk_tolerance`, `project_product_risk_weight`, `project_product_risk_priority`, `risk_preference_owner_fk`, `last_modified_by_fk`, `project_fk_prefs`) VALUES ('2', 'Risk Name 001', 'INACTIVE', '20150101', '.50', '.50', 'low', '.50', '.50', 'MEDIUM', '.50', '.50', 'HIGH', '.50', '.50', 'low', '.50', '.50', 'MEDIUM', '.50', '.50', 'HIGH', '1', '1', '1');

INSERT INTO `eisdb`.`projects` (`project_id`, `project_name`, `project_status`, `flagged_fk_project`, `user_profile_fk`, `project_details_fk`) VALUES ('1', 'Project A', 'ACTIVE', '1', '1', '1');
INSERT INTO `eisdb`.`projects` (`project_id`, `project_name`, `project_status`, `flagged_fk_project`, `user_profile_fk`, `project_details_fk`) VALUES ('2', 'Project B', 'ACTIVE', '1', '1', '2');

INSERT INTO `eisdb`.`project_details` (`project_details_id`, `org_details`, `country_code`, `state_province`, `lessons_learned`, `budget_variance`, `schedule_variance`, `fte_utilization_rate_variance`, `latitude`, `longitude`, `risk_context_plans_count`, `risk_context_plans_rating`, `risk_identification_plans_count`, `risk_identification_plans_rating`, `risk_analysis_plans_count`, `risk_analysis_plans_rating`, `risk_plans_communicated_count`, `risk_plans_communicated_rating`, `last_modified_date`, `last_modified_by_fk_details`, `primary_poc_manager_fk`, `primary_poc_executive_fk`) VALUES ('1', 'project A details', 'USA', 'MD', 'my lessons learned', 'MEDIUM', 'MEDIUM', 'MEDIUM', '38.8981', '77.0208', '1', 'STRENGTH', '3', 'STRENGTH', '3', 'STRENGTH', '3', 'STRENGTH', '20150101', '1', '1', '1');
INSERT INTO `eisdb`.`project_details` (`project_details_id`, `org_details`, `country_code`, `state_province`, `lessons_learned`, `budget_variance`, `schedule_variance`, `fte_utilization_rate_variance`, `latitude`, `longitude`, `risk_context_plans_count`, `risk_context_plans_rating`, `risk_identification_plans_count`, `risk_identification_plans_rating`, `risk_analysis_plans_count`, `risk_analysis_plans_rating`, `risk_plans_communicated_count`, `risk_plans_communicated_rating`, `last_modified_date`, `last_modified_by_fk_details`, `primary_poc_manager_fk`, `primary_poc_executive_fk`) VALUES ('2', 'project B details', 'USA', 'VA', 'my lessons learned', 'MEDIUM', 'MEDIUM', 'MEDIUM', '38.8981', '77.0208', '1', 'STRENGTH', '3', 'STRENGTH', '3', 'STRENGTH', '3', 'STRENGTH', '20150101', '1', '1', '1');

INSERT INTO `eisdb`.`project_partners` (`project_partner_id`, `project_partner_name`, `project_partner_details`, `on_site`, `project_participation_status`, `lessons_learned`, `last_modified_date`, `corp_leadership_history_rating`, `financial_viability_rating`, `market_and_labeling_rating`, `physical_security_rating`, `cyber_security_rating`, `insider_threat_rating`, `regional_stability_rating`, `latitude`, `longitude`, `flagged_by_fk_partners`, `last_modified_by_fk_partners`, `project_fk_partners`) VALUES ('1', 'Partner A', 'partner B details', 'ACTIVE', 'ACTIVE', 'my lessons learned', '20150101', 'MEDIUM', 'MEDIUM', 'MEDIUM', 'MEDIUM', 'MEDIUM', 'MEDIUM', 'MEDIUM', '38.8981', '77.0208', '1', '1', '1');
INSERT INTO `eisdb`.`project_partners` (`project_partner_id`, `project_partner_name`, `project_partner_details`, `on_site`, `project_participation_status`, `lessons_learned`, `last_modified_date`, `corp_leadership_history_rating`, `financial_viability_rating`, `market_and_labeling_rating`, `physical_security_rating`, `cyber_security_rating`, `insider_threat_rating`, `regional_stability_rating`, `latitude`, `longitude`, `flagged_by_fk_partners`, `last_modified_by_fk_partners`, `project_fk_partners`) VALUES ('2', 'Partner B', 'partner B details', 'ACTIVE', 'ACTIVE', 'my lessons learned', '20150101', 'MEDIUM', 'MEDIUM', 'MEDIUM', 'MEDIUM', 'MEDIUM', 'MEDIUM', 'MEDIUM', '38.8981', '77.0208', '1', '1', '1');

INSERT INTO `eisdb`.`project_systems` (`system_id`, `system_name`, `description`, `last_modified_date`, `flagged_by_fk_systems`, `project_fk_systems`, `last_modified_by_fk_systems`) VALUES ('1', 'System A', 'System A description', '20150101', '1', '1', '1');
INSERT INTO `eisdb`.`project_systems` (`system_id`, `system_name`, `description`, `last_modified_date`, `flagged_by_fk_systems`, `project_fk_systems`, `last_modified_by_fk_systems`) VALUES ('2', 'System B', 'System B description', '20150101', '1', '1', '1');

INSERT INTO `eisdb`.`flagged_assets` (`flagged_id`, `flagged_reason`, `flagged_risk_state`, `flagged_date`, `unflagged_risk_state`, `unflagged_date`, `flagged_by_fk_assets`, `unflagged_by_fk_assets`) VALUES ('1', 'Schedule needs attention', 'HIGH', '20150101', 'unknown', '20150101', '1', '');
INSERT INTO `eisdb`.`flagged_assets` (`flagged_id`, `flagged_reason`, `flagged_risk_state`, `flagged_date`, `unflagged_risk_state`, `unflagged_date`, `flagged_by_fk_assets`, `unflagged_by_fk_assets`) VALUES ('2', 'Schedule needs attention', 'HIGH', '20150101', 'unknown', '20150101', '1', '');

INSERT INTO `eisdb`.`email_templates` (`email_template_id`, `email_template_name`, `email_subject`, `email_body`, `email_from`, `last_modified_date`) VALUES ('1', 'New Account Request', 'New EIS Account Request', 'Please create an account for user X given justification XYZ', 'somePerson@xyz.com', '20150101');

INSERT INTO `eisdb`.`contact_us` (`contact_us_id`, `email`, `name`, `category`, `user_comment`, `last_modified_date`) VALUES ('1', 'somePerson@xyz.com', 'Person A', 'comment', 'Great system. Very useful.', '20150101');

SET FOREIGN_KEY_CHECKS = 1;