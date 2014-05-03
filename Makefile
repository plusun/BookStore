APPDIR = /var/lib/tomcat7/webapps/BookStore

all:
	(cd WEB-INF/classes; make)
copy:
	sudo rm -rf $(APPDIR)
	sudo mkdir $(APPDIR)
	sudo cp * $(APPDIR) -r
.PHONY: clean
clean:
	(cd WEB-INF/classes; make clean)
	rm -f *~
