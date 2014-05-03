Connect to the ubuntu AWS instance.
ssh -i ~/Dropbox/amazonkey/accountantconnectkey.pem 54.213.167.55 -l ubuntu

Command to copy the binary to the AWS instance.
scp -i ~/Dropbox/amazonkey/accountantconnectkey.pem /Users/mpanda/work/export/AccountantConnect.war ubuntu@54.213.167.55:

Installing mysql and tomcat7
sudo apt-get install mysql-server tomcat7

For tomcat admin
sudo apt-get install tomcat6-admin

Config files can be found in
/etc/mysql
/etc/tomcat7
/etc/default/tomcat7.

Start and stop servers using
sudo /etc/init.d/tomcat7 start|stop
sudo /etc/init.d/mysql start|stop

tomcat deployment dir is
/var/lib/tomcat7

By default only root processes can listen to portnumber < 1024. So modify IP Tables to forward 8080 to 80.
sudo /sbin/iptables -t nat -I PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080

Include the proxyPort attribute in your HTTP connector config in server.xml:
<Connector port="8080" proxyPort="80" .../>

################################## UserNames & Passwords #########################

Github - ac-intuit/acintuit42
Amazon AWS - ac.intuit42@gmail.com/acintuit
Gmail - ac.intuit42/acintuit
Gmail - manas@accountantconnect.in/acintuit42
Gmail - contact@accountantconnect.in/acintuit42
mailChimp - accountantconnect/acintuit42
Google Analytics - ac.intuit42@gmail.com/acintuit

################################## END UserNames & Passwords #########################

In eclipse (or while starting tomcat from any system) set the environment to ENV=dev
In eclipse, go to "Run Configuration" -> Environment tab


