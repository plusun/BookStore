testDB: test/database.properties test/TestDB.java
	javac test/TestDB.java
	java -cp driver/mysql-connector-java-5.1.30-bin.jar:. test.TestDB
.PHONY: clean
clean:
	rm -f test/*.class
