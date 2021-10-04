#!/bin/bash

echo "select the algorithm of choice: \\n 1. Bakery Custom lock \\n 2. Filter lock \\n 3. Re-entrant lock \\n 4. Bakery with non atomic"

read choice 

echo "enter no of threads"

read threads
aff="$((threads-1))"


export iter=10
echo "enter no of iterations: (default 10) "
read iter
taskset -c 0-$aff java -jar ./dist/Mutex.jar $choice $threads $iter