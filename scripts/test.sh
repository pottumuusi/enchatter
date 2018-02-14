#!/bin/bash

rundir=$(cd $(dirname $0) && pwd)
cd $rundir

source ./vars.sh
cd $absolute_project_root_path

project_root=$absolute_project_root_path
example_configuration_path="examples/test_configuration.xml"
absolute_example_configuration_path="$project_root/$example_configuration_path"

test_classes=$(ls $test_out | sed -e 's/.class//g')

cd $app_out

echo "Running enchatter with example configuration..."
java enchatter.Enchatter "$absolute_example_configuration_path" || \
	echo "[FAILED] Running enchatter with example configuration"

echo "Running Junit tests..."
java -cp ".:$absolute_junit_path:$absolute_hamcrest_path:$test_out" \
	org.junit.runner.JUnitCore \
	$test_classes
