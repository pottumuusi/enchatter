function process_args() {
	given_args="$@"

	debug_print "printing all args"
	for arg in ${given_args}
	do
		debug_print "arg is: $arg"
	done

	for arg in ${given_args}
	do
		matches="$(matches_send_message_long "${arg}")"
		if [ "TRUE" = "${matches}" ] ; then
			readonly SEND_MESSAGE_DO="TRUE"
			readonly SEND_MESSAGE_VAL="$(get_val_of_long_arg "${arg}")"
		fi
	done
}
