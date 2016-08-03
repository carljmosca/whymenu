FROM tomcat:7.0.70-jre8
RUN rm -Rf /usr/local/tomcat/webapps/ROOT
RUN adduser --disabled-password --gecos '' tomcat && adduser tomcat sudo && echo '%sudo ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers
COPY whymenu-ui-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
RUN chown -R tomcat /usr/local/tomcat/
USER tomcat