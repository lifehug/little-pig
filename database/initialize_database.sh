#!/bin/bash
PASS=password_here
EXPECTED_ARGS=1
E_BADARGS=65
MYSQL=`which mysql`

if [ $# -ne $EXPECTED_ARGS ]
then
  echo "Usage: $0 password" 
  exit $E_BADARGS
fi

Q1="CREATE DATABASE snort;"
Q2="USE snort;"
Q3="SOURCE create_mysql;"
Q4="CREATE USER 'snort'@'localhost' IDENTIFIED BY '$PASS';"
Q5="CREATE USER 'snort'@'%' IDENTIFIED BY '$PASS';"
Q6="GRANT ALL PRIVILEGES ON snort.* TO 'snort'@'localhost';"
Q7="GRANT ALL PRIVILEGES ON snort.* TO 'snort'@'%';"
Q8="FLUSH PRIVILEGES;"
SQL="${Q1}${Q2}${Q3}${Q4}${Q5}${Q6}${Q7}${Q8}"

$MYSQL -u root -p -e "$SQL"

# stop service so that a container can use the database
service mysql stop
