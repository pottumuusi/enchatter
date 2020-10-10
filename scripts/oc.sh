#!/bin/bash

cd "$(dirname $0)"

source ../include/variables.sh
source ${scripts_base}/include/util.sh
source ${scripts_base}/include/oc_impl.sh
source ${scripts_base}/include/oc_args.sh

debug_print "pwd is: $(pwd)"

if [ ! -f "${client_bin}" ] ; then
	error_exit "Client binary (${client_bin}) not found."
fi

process_args "$@"

if [ "TRUE" = "${SEND_MESSAGE_DO}" ] ; then
	send_message ${SEND_MESSAGE_VAL}
fi
