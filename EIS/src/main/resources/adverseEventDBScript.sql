create database if not exists adsdb;
use adsdb;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS adsdb.adverseDrugEventResults;
CREATE TABLE adsdb.adverseDrugEventResults (
  event_id int(11) NOT NULL AUTO_INCREMENT,
  safetyreportid varchar(150) NOT NULL UNIQUE,
  senderorganization varchar(150) DEFAULT '',
  last_modified_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  serious int(11),
  companynumb varchar(150) DEFAULT '',
  patient_reactions varchar(775) DEFAULT '',
  PRIMARY KEY (event_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO adsdb.adverseDrugEventResults (safetyreportid, senderorganization, serious, companynumb, patient_reactions)
VALUES ('00801200-7', 'The Test Sender', '1', '123456789', 'patient reaction 1, patient reaction 2');

SET FOREIGN_KEY_CHECKS = 1;