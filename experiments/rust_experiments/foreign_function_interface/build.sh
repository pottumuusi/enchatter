#!/bin/bash

mydir=$(cd $(dirname $0) && pwd)

RUSTFLAGS="-L $mydir/lib" cargo build
