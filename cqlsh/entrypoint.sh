export CQLVERSION=${CQLVERSION:-"3.4.4"}
export CQLSH_HOST=${CQLSH_HOST:-"cassandra"}
export CQLSH_PORT=${CQLSH_PORT:-"9042"}



# test connection to cassandra
echo "Checking connection to cassandra..."
for i in 10 20
do
  if cqlsh --cqlversion CQLSH_HOST="cassandra" ${CQLVERSION} -e "show host;" 2> /dev/null ;
  then
    break
  fi
  echo "Can't establish connection, will retry again in $i sconds"
  sleep $i
done

# iterate over the cql files in /scripts folder and execute each one
for file in /scripts/*.cql; do
  [ -e "$file" ] || continue
  echo "Executing $file..."
  cqlsh --cqlversion ${CQLVERSION} -f "$file"
done

echo "Done."
