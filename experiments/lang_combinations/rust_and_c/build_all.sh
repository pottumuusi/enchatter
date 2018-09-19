#!/bin/bash

mydir=$(cd $(dirname $0) && pwd)
pushd ./
cd $mydir/c_code
./build_executable.sh
popd

cp $mydir/c_code/funlib.a $mydir/rust_code/lib/

pushd ./
cd rust_code
rustdir=$(pwd)
RUSTFLAGS="-L $rustdir/lib" cargo build
popd

cp rust_code/target/debug/foreign_function_interface ./
