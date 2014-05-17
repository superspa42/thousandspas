/*   ==================================
Owner & profile
================================== */

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
    spashostname varchar(200), /* Will create URL like www.superspas.in/aromaspa or better yet aromaspa.superspas.in */


    ownerfirstname varchar(50),
    ownerlastname varchar(50) ,
    mobile varchar(20) ,

    joindate date ,

    isprofilecreated int ,

    profilecreatedate date ,
    profilemodifieddate date,
    isHidden int ,

    shortdescription text,
    longdescription text,

    websiteurl text,
    fbprofile text,
    gplusprofile text,

    addressline1 varchar(200),
    addressline2 varchar(200),
    state varchar(100),
    pincode int,
    country varchar(100),

    noofvisittoeditprofilepage int,
    noofuniqueprofilevisits int,

    locationlat FLOAT(10,6),
    locationlong FLOAT(10,6),

    isprofilecomplete int,


    PRIMARY KEY (spaid)
) AUTO_INCREMENT=1120 ;

/*
 This is the table to store the images
 for each spa. One spa can have multiple
 images associated with them.
 */

CREATE TABLE images(
    imageid int NOT NULL AUTO_INCREMENT,
    spaid int,

    imageurl text,

    PRIMARY KEY (imageid),
    FOREIGN KEY (spaid) REFERENCES spa(spaid)
);

/*
 Generic services provided by the spas and salons.
 Whenever a spa adds a new service, add it to this 
 table first. Then this table is referenced in 
 N X N spas_services table
 */

CREATE TABLE service(
    serviceid int NOT NULL AUTO_INCREMENT,
    servicename varchar(200),

    PRIMARY KEY (serviceid)
);


/*
 This N X N table stores spas and the services 
 they provide. For e.g. 
 spa1 - Foot Massage
 spa1 - Head Massage
 spa2 - Foot Massage
 */
CREATE TABLE spa_service(
    spaserviceid int NOT NULL AUTO_INCREMENT,
    spaid int,
    serviceid int,

    PRIMARY KEY (spaserviceid),
    FOREIGN KEY (spaid) REFERENCES spa(spaid),
    FOREIGN KEY (serviceid) REFERENCES service(serviceid)
);

/*
   Tracking table for the activities, that happen
   Before the customer or spa is loggedon
   Explore whether GA has better tracking, of 
   "WHO" did it.
*/
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
);

