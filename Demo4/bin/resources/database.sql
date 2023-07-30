CREATE DATABASE IF NOT EXISTS petistaan_jdbc;
USE petistaan_jdbc;

CREATE TABLE owner_table (
    id INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    gender VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(10) NOT NULL UNIQUE,
    email_id VARCHAR(255) NOT NULL UNIQUE,
    pet_id INT NOT NULL,
    pet_name VARCHAR(255) NOT NULL,
    pet_date_of_birth DATE NOT NULL,
    pet_gender VARCHAR(255) NOT NULL,
    pet_type VARCHAR(255) NOT NULL 
);

SELECT * FROM owner_table;
INSERT INTO owner_table (id, first_name, last_name, gender, city, state, mobile_number, email_id, pet_id, pet_name, pet_date_of_birth, pet_gender, pet_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
SELECT * FROM owner_table WHERE id = ?;
UPDATE owner_table SET pet_name = ? WHERE id = ?;
DELETE FROM owner_table WHERE id = ?;
SELECT * FROM owner_table WHERE email_id = ? AND pet_date_of_birth = ?;
SELECT * FROM owner_table WHERE pet_type = ?;
UPDATE owner_table SET pet_name = 
CASE pet_gender
	 WHEN 'M' THEN CONCAT('Mr. ', pet_name)
	 WHEN 'F' THEN CONCAT('Miss ', pet_name)
	 ELSE pet_name
END
WHERE pet_type = ?;

DELIMITER $$
CREATE PROCEDURE `add_prefix_to_pet_name` (IN petType VARCHAR(255))
BEGIN
	UPDATE owner_table SET pet_name =
	CASE pet_gender
		WHEN 'M' THEN CONCAT('Mr. ', pet_name)
		WHEN 'F' THEN CONCAT('Miss ', pet_name)
		ELSE pet_name
	END            
	WHERE pet_type = petType;
	SELECT * FROM owner_table WHERE pet_type = petType;
END$$
DELIMITER ;

CALL add_prefix_to_pet_name(?);