#!/bin/bash

absolute_project_root_path=$(cd .. && pwd)
absolute_junit_path="$absolute_project_root_path/lib/junit-4.12.jar"
absolute_hamcrest_path="$absolute_project_root_path/lib/hamcrest-core-1.3.jar"

enchatter_out="$absolute_project_root_path/out/app/enchatter"
app_out="$absolute_project_root_path/out/app"
test_out="$absolute_project_root_path/out/test"

test_src="$absolute_project_root_path/src/test"
app_src="$absolute_project_root_path/src/app/enchatter"

lib_dir="$absolute_project_root_path/lib"
