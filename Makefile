all:
	(cd WEB-INF/classes; make)
copy:
	sudo cp * /var/lib/tomcat7/webapps/BookStore -r
.PHONY: clean
clean:
	(cd WEB-INF/classes; make clean)
	rm *~
