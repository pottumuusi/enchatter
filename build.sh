#!/bin/bash

if [ ! -d ./out ] ; then
	mkdir out
	echo "made out dir"
fi

javac -d out/ src/enchatter/*.java
