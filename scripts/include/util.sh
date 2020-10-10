function error_exit() {
	echo "$1 >>>> Exiting."
	exit 1
}

function debug_print() {
	echo "[DEBUG] $1"
}

function get_val_of_long_arg() {
	local -r arg="$1"

	local -r value="$(echo ${arg} | egrep -o "=.*$" | tr -d "=")"

	echo "${value}"
}
