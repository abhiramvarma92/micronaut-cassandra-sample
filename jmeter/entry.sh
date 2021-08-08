#!/bin/bash
echo "*************Running entry.sh file**********"

pwd
ls

export CQLVERSION=${CQLVERSION:-"3.4.4"}
export CQLSH_HOST=${CQLSH_HOST:-"cassandra"}
export CQLSH_PORT=${CQLSH_PORT:-"9042"}



until  nc -z cassandra 9042
do
    echo 'sleeping for cassandra'
    sleep 10
done

until $(curl --output /dev/null --silent --head --fail http://${SERVICE_HOST}:${SERVICE_PORT}/showUsers); do
    echo 'sleeping'
    sleep 5
done




#Command
ls
timestamp=$(date +%d-%m-%Y_%H-%M-%S)
rm -rf reports
sh  bin/jmeter.sh -n -t   build-adv-web-test-plan_micronaut.jmx -l jmeter.log${timestamp}  -e -o   reports --forceDeleteResultFile




echo "********entry.sh file RAN SUCCESSFULLY*******"