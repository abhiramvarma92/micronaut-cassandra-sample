FROM python:2.7-alpine3.8

WORKDIR /scripts
RUN chmod +x /scripts
COPY data.cql .

ENV CQLVERSION="3.4.4" \
    CQLSH_HOST="cassandra" \
    CQLSH_PORT="9042"

RUN pip install -Ivq cqlsh==5.0.4 \
    && apk add --no-cache bash \
    && echo 'alias cqlsh="cqlsh --cqlversion ${CQLVERSION} $@"' >> /.bashrc \
    && mkdir /.cassandra

COPY  entrypoint.sh   /usr/local/bin

COPY  entrypoint.sh   .


RUN chmod +x /scripts/entrypoint.sh


CMD ["/scripts/entrypoint.sh"]