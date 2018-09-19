#!/bin/bash

gcc -c ./*.c
ar rsv libfun.a fun1.o fun2.o
ld -r -o all_linked.o main.o fun1.o fun2.o libfun.a
gcc all_linked.o
