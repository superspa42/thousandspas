--------------------------------
--       Owner & profile
--------------------------------
CREATE TABLE spa (
  spaid int NOT NULL AUTO_INCREMENT,
  spaname varchar(200),
  primaryemail varchar(80) NOT NULL UNIQUE,
  secondaryemail varchar(80) NOT NULL,
  businessphone1 varchar(20),
  businessphone2 varchar(20),
  password varchar(80) ,
  city varchar(80) ,
  area varchar(100) ,
  
  ownerfirstname varchar(50),
  ownerlastname varchar(50) ,
  mobile varchar(20) ,

  joindate date ,
  isprofilecreated int ,
  profilecreatedate date ,
  profilemodifieddate date,
  isHidden int ,

  
  photofilename varchar(100) ,
  shortdescription text,
  longdescription text,
  
  websiteurl text,
  fbprofile text,
    
  servicesprovided text,
  
  addressline1 varchar(200),
  addressline2 varchar(200),
  state varchar(100),
  pincode int,
  country varchar(100),
  
  noofvisittoeditprofilepage int,
  
  PRIMARY KEY (spaid)
) AUTO_INCREMENT=1120 


-------------------------------
--   Tracking table
--   Should be merged with GA
-------------------------------
CREATE TABLE tracking (
  trackid int NOT NULL AUTO_INCREMENT,
  spaid int ,
  customerid int ,
  email varchar(80) ,
  mobile varchar(20) ,
  ipaddress varchar(80) ,
  trackdate date ,
  feature varchar(100),
  
  PRIMARY KEY (trackid)
)

