#!/usr/bin/env bash

echo "*************Running entry.sh file**********"

pwd
ls


echo "Starting JMeter tests on ${SERVICE_HOST}:${SERVICE_PORT}"



until $(curl --output /dev/null --silent --head --fail http://${SERVICE_HOST}:${SERVICE_PORT}/showUsers); do
    echo 'sleeping'
    sleep 5
done



#Command
ls
sh  bin/jmeter.sh -n -t   build-adv-web-test-plan_micronaut.jmx -l 'jmeter.log'  -e -o  reports --forceDeleteResultFile


echo "********entry.sh file RAN SUCCESSFULLY*******"