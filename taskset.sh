#!/bin/bash

echo "enter no of threads"

read threads
aff="$((threads-1))"
echo "select the algorithm of choice: \n 1. Bakery Custom lock \n 2. Bakery java lock \n 3. Filter custom lock \n 4. filter java lock"

read choice 

export iter=10
echo "enter no of iterations: (default 10) "
read iter
taskset -c 0-$aff java -jar ./dist/Mutex.jar $choice $threads $iter