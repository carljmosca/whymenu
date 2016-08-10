FROM tomcat:7.0.70-jre8
RUN rm -Rf /usr/local/tomcat/webapps/ROOT
COPY whymenu-ui-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
RUN chmod -R 777 /usr/local/tomcat/