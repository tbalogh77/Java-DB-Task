# Java-DB-Task

Java language Database task

Simulating an order processing application.

Few notes:

- You can use exported DataBase "data/Orders.sql" or
   - To create empty database from MySQL prompt  "source utils/CreateDB.sql"
   - To export existing database run "utils/mysqli_export.sh"

- Files (properties, csv) needed to run app can be founded in "data" directory

- External jars nedded for App (like mysql-connector, etc) can be found in "lib"

- inputFile example can be found in "data" directory

- responseFile will be written to "data" directory &  uploaded to FTP server "speedtest.tele2.net" as user "anonymous"


- IF SOMETHING GOES WRONG

   - Because I'm a bit uncertain with eclipse workspace, I've also added a comlete "workspace.tgz" to "backup" directory this is a MUST WORK :)

   - Worst Case scenario:
	- command line compile: 
            javac -cp "lib/*.jar" src/com/tom/JavaDBTask/*.java 	
        - command line run:
            java  -cp "lib/*.jar" com/tom/JavaDBTask/JavaDBTaskMain

- What's missing?

        - Much more error handling
        - Unit tests


Please feel free to ask any questions or add any comments to "balogh00@gmail.com"
