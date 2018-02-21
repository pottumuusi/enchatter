#!/bin/bash

rundir=$(cd $(dirname $0) && pwd)
cd $rundir

source ./vars.sh

if [ ! -f $lib_dir ] ; then
	mkdir $lib_dir
fi

cd $lib_dir

curl -O http://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
curl -O http://search.maven.org/remotecontent?filepath=junit/junit/4.12/junit-4.12.jar
