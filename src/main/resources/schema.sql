/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  mstark
 * Created: Nov 13, 2021
 */

CREATE TABLE availability
    (availability_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
     user_id VARCHAR(30) NOT NULL,
     dancer_name VARCHAR(100) NOT NULL,
     location VARCHAR(75) NOT NULL,
     start_time TIMESTAMP NOT NULL,
     end_time TIMESTAMP NOT NULL,
     dance_limit INT NOT NULL DEFAULT -1,
     created_timestamp TIMESTAMP NOT NULL DEFAULT NOW());

CREATE TABLE dance_requests
    (dance_request_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
     availability_id BIGINT NOT NULL,
     requestor_name VARCHAR(255) NOT NULL,
     created TIMESTAMP NOT NULL,
     FOREIGN KEY (availability_id) REFERENCES availability (availability_id));