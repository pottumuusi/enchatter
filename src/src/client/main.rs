use std::io;
use std::io::prelude::*;

fn main() {
    let stdin = io::stdin();
    let mut buf = String::new();
    let mut buf_sanitized = String::new();

    loop {
        buf.clear();
        stdin.lock().read_line(&mut buf); // TODO handle return value

        println!("buf is: {}", buf);
        println!("buf.trim().to_string() is: {}", buf.trim().to_string());

        buf_sanitized.clear();
        buf_sanitized = buf.trim().to_string();

        println!("buf_sanitized is: {}", buf_sanitized);
    }
}
