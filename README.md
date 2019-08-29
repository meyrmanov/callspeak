Calls Peak Application
==========================

Application reads input text file with the calls information in the following format [start:end] and detects the peak of simultaneous calls.

## Run
mvn compile exec:java -Dexec.args=samples/calls.txt

## Tests
mvn test