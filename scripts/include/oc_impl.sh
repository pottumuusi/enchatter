readonly SEND_MESSAGE_ARG="send-message"

function send_message() {
	local -r message="$1"

	${client_bin} --${SEND_MESSAGE_ARG}="${SEND_MESSAGE_VAL}"
}

function matches_send_message_long() {
	try_match="$1"

	# egrep that:
	# begins with --
	# has ${SEND_MESSAGE_ARG} in the middle
	# has something after =
	if [ -n "$(echo "${try_match}" | egrep "^--${SEND_MESSAGE_ARG}=.+$")" ] ; then
		echo "TRUE"
		return
	fi

	echo "FALSE"
}

function matches_send_message_short() {
	echo "${FUNCNAME[0]}: Not yet implemented"
}
