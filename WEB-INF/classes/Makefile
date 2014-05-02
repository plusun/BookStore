generatef:
	javac -cp .:/usr/share/java/tomcat7/servlet-api.jar  servlet/LoadIndex.java
testDB:
	@javac test/TestDB.java
	@java -cp driver/mysql-connector-java-5.1.30-bin.jar:. test.TestDB
	@rm -f test/*.class
.PHONY: clean
clean:
	rm -f test/*~ *~ database/*~ database/*.class servlet/*.class servlet/*~
