#!/bin/bash

rundir=$(cd $(dirname $0) && pwd)
cd $rundir

source ./vars.sh

cd $absolute_project_root_path

port=2000

cd out
java enchatter.Enchatter
