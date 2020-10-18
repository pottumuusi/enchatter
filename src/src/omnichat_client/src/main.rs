use std::io;
use std::env;
use std::io::prelude::*;
use std::net::TcpStream;

use omnichat_utils;

const CFG_DEBUG_ON: bool = true; // TODO make global over the files

// TODO rename file

fn main() {
    // TODO handle 0 number of arguments. Currently will crash if no arguments
    // provided and client is ran.
    let args: Vec<String> = env::args().collect();
    let arg1 = &args[1];

    println!("arg1 is: {}", arg1);

    omnichat_utils::omnichat_args::try_me();

    return;

    // No handling for user input yet
    let read_input:bool = false;

    // TODO
    // * send queries to servers non-interactively
    // * address and port as parameters
    // * error handling for connection
    // * network connection in a separate thread?
    //      - Will need to read user input while chat text may be updated.
    println!("Connecting to server");
    do_connect();

    loop {
        if !read_input {
            break;
        }

        read_user_input();
    }

    println!("Exiting");
}

fn read_user_input() {
    let stdin = io::stdin();
    let mut buf:String = String::new();
    let mut buf_sanitized:String = String::new();

    buf.clear();
    stdin.lock().read_line(&mut buf); // TODO handle return value

    #[cfg(CFG_DEBUG_ON)]
    {
        println!("buf is: {}", buf);
        println!("buf.trim().to_string() is: {}", buf.trim().to_string());
    }

    buf_sanitized.clear();
    buf_sanitized = buf.trim().to_string();

    #[cfg(CFG_DEBUG_ON)]
    {
        println!("buf_sanitized is: {}", buf_sanitized);
    }
}

fn do_connect() -> std::io::Result<()> {
    let mut stream = TcpStream::connect("127.0.0.1:7878")?; // TODO error handling

    // h -> 104
    // e -> 101
    // l -> 108
    // o -> 111
    stream.write(&[104, 101, 108, 108, 111, 0])?;
    Ok(())
}
