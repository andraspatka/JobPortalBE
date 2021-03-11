# JobPortalBE [![Build Status](https://travis-ci.com/andraspatka/JobPortalBE.svg?branch=main)](https://travis-ci.com/andraspatka/JobPortalBE)

Techstack: 
- BE: Java - Spring, Hibernate, maven, Postgresql
- FE: Angular
- Git: Github FE, Github BE
- Ci/CD: TravisCi

## Database

For instructions on quickly setting up a Postgres DB and PgAdmin in Docker, go to the "db" folder

## Java

Java version required: 11

Zulu JDK is recommended, get it 
[here](https://www.azul.com/downloads/zulu-community/?version=java-11-lts&os=windows&architecture=x86-64-bit&package=jdk).

## Maven

Newer version of Maven have support for a Maven wrapper.

To build the project, all you need to do is:

```
./mvnw package
```
You might get an error: "ERROR: JAVA_HOME is not set"
If you do, then set the JAVA_HOME environment variable to your JDK installation.
For Zulu it's: "C:\Program Files\Zulu\zulu-11"
