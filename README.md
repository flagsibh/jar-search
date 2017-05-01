# Jar Search
[Swing](http://docs.oracle.com/javase/tutorial/uiswing/) application that aids to find a java class within JAR or ZIP files.

It's an example of how to use multiple threads to recursively search within a directory. It uses a `BlockingQueue` to synchronize threads and the library [Awaitility](https://github.com/awaitility/awaitility) to wait for all threads to end.

It also shows how to use the **Observer** design pattern.  

## Run

1.  Compile & Package

    As a Maven project it's very easy to compile an package the application:
    
    `mvn clean package`
    
    And it will construct a the file `target/jar-search-1.0-SNAPSHOT.jar` 
2.  Run

    Simply run:
    
    `java -jar jar-search-1.0-SNAPSHOT.jar`
    
    Or use one of the files in the directory `run`, depending on your OS:
    + Windows: 
    
    `run\jar-search.bat`
    + Linux: 
    
    `run/jar-search.sh`
    