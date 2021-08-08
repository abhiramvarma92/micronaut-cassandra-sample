#!/bin/bash
#!/usr/bin/env bash
export CQLVERSION=${CQLVERSION:-"3.4.4"}
export CQLSH_HOST=${CQLSH_HOST:-"cassandra"}
export CQLSH_PORT=${CQLSH_PORT:-"9042"}

cqlsh=( cqlsh --cqlversion ${CQLVERSION} )

# test connection to cassandra
echo "Checking connection to cassandra..."
until  nc -z cassandra 9042
do
  echo "Can't establish connection, will retry again in 2 seconds"
  sleep 2
done


# iterate over the cql files in /scripts folder and execute each one
for file in /scripts/*.cql; do
  [ -e "$file" ] || continue
  echo "Executing $file..."
  "${cqlsh[@]}" -f "$file"
done

echo "Done."
