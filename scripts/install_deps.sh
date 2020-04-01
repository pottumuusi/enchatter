#!/bin/bash

# TODO support for different distributions

required_ubuntu_packages=""
required_ubuntu_packages+="curl"

echo "Running sudo command next"
sudo apt-get install "${required_ubuntu_packages}"
curl https://sh.rustup.rs -sSf | sh
