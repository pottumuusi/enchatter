#!/bin/bash

rundir=$(cd $(dirname $0) && pwd)
cd $rundir

source ./vars.sh
cd $absolute_project_root_path

if [ ! -d ./out ] ; then
	mkdir out
	echo "made out dir"
fi

if [ ! -d $enchatter_out ] ; then
	mkdir $enchatter_out
	echo "made out dir"
fi

if [ ! -d $test_out ] ; then
	mkdir $test_out
	echo "made out dir"
fi

echo "Building app..."
javac -d $enchatter_out $app_src/*.java

echo "Building tests..."
javac -cp ".:$absolute_junit_path:$enchatter_out" -d $test_out $test_src/*.java
