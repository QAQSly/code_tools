#!/bin/bash

# move dir
cd /root/code_tools/SpringTool

# maven 
mvn clean install

# checke

if [ $? -eq 0 ]; then
    echo "Maven build succeeded."
    java -jar target/SpringTool-1.0-SNAPSHOT.jar
    git add .
    git commit -m "update"
    git push
else
    echo "Maven build faild."
    exit 1
fi
