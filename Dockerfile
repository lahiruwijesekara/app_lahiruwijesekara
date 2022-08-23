FROM tomcat
COPY devopssampleapplication.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
