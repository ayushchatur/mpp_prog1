# Running via command line

after cloning the git repository follow these steps 

run: 

``` sh 
java -jar dist/Mutex.jar <Choice of Algorithm> <No of threads> <Type of test> 
```

The utility asks for the type of algorithm to run following are the options 
Choice of Algorithm: 

1. Bakery via Custom Lock <default: 1> 
2. Bakery via Java Concurrent Lock
3. Filter via Custom Lock
4. Filter via Java Concurrent Lock
  
 No of threads: Integer value <default: 4> 
 
Type of test
1. counter test
2. Custom test 

> for details on custom test refer assignment report.

### Using Taskset

In order to faciliate runs with taskset we are providing [run.sh](./run.sh) bash file. 
the sytax for running it are also similar: 


after cloning the git repository follow these steps 
``` sh 
cd test 
sh run.sh <Choice of Algorithm> <No of threads> <Type of test> 
``` 
The script outputs the task affinity allocated before running the jar file

