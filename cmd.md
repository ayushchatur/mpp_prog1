# Running via command line

> Target build JAVA 8 , rlogin has java 11. but we did not find any issue in building. If the issues persist please use JDK 8 .


> Assuming you have cloned it to a directory named **test**
after cloning the git repository follow these steps 

run: 

``` sh 

mkdir -p test 

cd test 

git clone git@github.com:ayushchatur/mpp_prog1.git
cd mpp_prog1

ant -f . -Dnb.internal.action.name=rebuild clean jar

java -jar dist/Mutex.jar <Choice of Algorithm> <No of threads> <iterations> 
```

The utility asks for the type of algorithm to run following are the options 
Choice of Algorithm: 

1. Bakery via Custom Lock <default:> 
2. Filter Custom Lock
3. Re-entrant Lock
4. Bakery via non atomic variables
  
 No of threads: Integer value <default: 4> 
 



### Using Taskset

In order to faciliate runs with taskset we are providing [taskset.sh](./taskset.sh) bash file. 
the syntax for running it are also similar: 


after cloning the git repository follow these steps 
``` sh 
cd test 

mkdir -p test 

cd test 

git clone git@github.com:ayushchatur/mpp_prog1.git
cd mpp_prog1

ant -f . -Dnb.internal.action.name=rebuild clean jar

sh taskset.sh 


for i in {0..4}; do echo "time by thread ${i}: "; cat result.out | grep "by thread id: ${i} " | awk '{total += $8;count++} END {print
 total/count}'; done ; cat result.out | grep "correct"
 
``` 

> please change the range in for statement above(instead of 4 use number of threads that you entered in taskset)  
> 
The script outputs the task affinity allocated before running the jar file


