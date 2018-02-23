# Java-DB-Task

Java language Database task

Simulating an order processing application.

Few notes:

- Repo contains Eclipse (3.8.1) workspace in dir "workspace"

   - Alco can be command line compiled ( somehow :-)	

- Project contains JUnit4 test cases for main functions like connecting to FTP, useing prop files, etc ...

- Repo contains database:
   
   - You can use exported DataBase "data/Orders.sql" or
   - To create empty database from MySQL prompt  "source utils/CreateDB.sql"
   - To export existing database run "utils/mysqli_export.sh"
   - To install MySQL server on Linux run "utils/mysql_install.sh" ( what an advance! :-) 

- Files (properties, csv) needed to run app can be founded in "data" directory

- External jars nedded for App (like mysql-connector, etc) can be found in "lib"

- inputFile example can be found in "data" directory

- responseFile will be written to "data" directory &  uploaded to FTP server "speedtest.tele2.net" as user "anonymous"


- IF SOMETHING GOES WRONG try Worst Case scenario:

        - command line compile: 
            javac -cp "lib/*.jar" src/com/tom/JavaDBTask/*.java 	

        - command line run:
            java  -cp "lib/*.jar" com/tom/JavaDBTask/JavaDBTaskMain

- What's missing?

        - Much more error handling
        - More unit testing


Please feel free to ask any questions or add any comments to "balogh00@gmail.com"
