FROM ubuntu:latest

USER root
WORKDIR /jmeter

ENV VERSION=$version


RUN apt-get update
RUN apt-get -qq -y install curl  telnet wget  && apt-get install -y software-properties-common &&  apt-get -y install apt  && apt -y install zip && apt -y install unzip && apt install -y  wget && apt install -y gnupg
RUN wget -O- https://apt.corretto.aws/corretto.key | apt-key add - && add-apt-repository 'deb https://apt.corretto.aws stable main'
RUN apt-get install -y  netcat
RUN apt-get install -y java-1.8.0-amazon-corretto-jdk
RUN wget http://apache.stu.edu.tw//jmeter/binaries/apache-jmeter-5.2.1.tgz
RUN tar -xzf apache-jmeter-5.2.1.tgz
RUN mv apache-jmeter-5.2.1/* /jmeter
RUN rm -rf apache-jmeter-5.2.1*
RUN mkdir output
ENV JMETER_HOME=/jmeter
ENV JMETER_BIN=/jmeter/bin

COPY  . .
RUN chmod +x /jmeter/entry.sh
RUN chmod +x /jmeter



CMD ["/jmeter/entry.sh"]

#ENTRYPOINT ["tail", "-f", "/dev/null"]
