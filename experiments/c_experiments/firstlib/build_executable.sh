#!/bin/bash

gcc -c ./*.c
ar rsv funlib.a fun1.o fun2.o
ld -r -o all_linked.o main.o fun1.o fun2.o funlib.a
gcc all_linked.o
