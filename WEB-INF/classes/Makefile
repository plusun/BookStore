SERVLETAPI = /usr/share/java/tomcat7/servlet-api.jar
SQLDRIVER = driver/mysql-connector-java-5.1.30-bin.jar
generate:
	javac -cp .:$(SERVLETAPI)  servlet/LoadIndex.java
	javac -cp .:$(SERVLETAPI) servlet/LogIn.java
	javac -cp .:$(SERVLETAPI) servlet/Register.java
	javac database/Database.java
testDB:
	@javac test/TestDB.java
	@java -cp $(SQLDRIVER):. test.TestDB
	@rm -f test/*.class
.PHONY: clean
clean:
	rm -f test/*~ *~ database/*~ database/*.class servlet/*.class servlet/*~
	rm -f bean/*~ bean/*.class
