testDB:
	@javac test/TestDB.java
	@java -cp driver/mysql-connector-java-5.1.30-bin.jar:. test.TestDB
	@rm -f test/*.class
.PHONY: clean
clean:
	rm -f test/*~ *~ database/*~ database/*.class
