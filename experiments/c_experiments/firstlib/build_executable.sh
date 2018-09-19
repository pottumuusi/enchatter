#!/bin/bash

gcc -c ./*.c
ld -r -o all_linked.o main.o fun1.o fun2.o funlib.a
gcc all_linked.o
