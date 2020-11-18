#!/usr/bin/env bash

run() {
  ${1} clean compile && ${1} package
  java -jar ./target/HireRight*-jar-with-dependencies.jar
  exit 0;
}

#cd into the scripts source/ project root directory, if not already there
cd "${0%/*}" || exit 100

$(command -v java &> /dev/null) ||  { echo "java runtime not found"; exit 2; }
$(command -v javac &> /dev/null) || { echo "java compiler not found"; exit 2; }
$(command -v mvn &>/dev/null) && run $(command -v mvn)
$(command -v ./mvnw &>/dev/null) && run $(command -v ./mvnw)

#if it reaches this point then there was no maven found
echo "maven or maven wrapper was not found. please install maven and run this command again"
exit 3;
