use std::io;
use std::io::prelude::*;
use std::net::TcpStream;

// TODO rename file

fn main() {
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

    println!("buf is: {}", buf);
    println!("buf.trim().to_string() is: {}", buf.trim().to_string());

    buf_sanitized.clear();
    buf_sanitized = buf.trim().to_string();

    println!("buf_sanitized is: {}", buf_sanitized);
}

fn do_connect() -> std::io::Result<()> {
    let mut stream = TcpStream::connect("127.0.0.1:7878")?;
    stream.write(&[1])?;
    Ok(())
}
