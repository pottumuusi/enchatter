use std::net::TcpListener;
use std::net::TcpStream;
use std::io;
use std::io::Read;
use std::str;

const CFG_DISABLED: bool = false; // Mark unused code TODO could be called FIXME_PLS(?)
const CFG_DEBUG_ON: bool = true;
const CFG_EXAMPLE: bool = false;

// TODO rename file

fn main() {
    #[cfg(CFG_EXAMPLE)]
    {
        let full_addr_obj:String = String::from("127.0.0.1:7878");
    }
    let full_addr: &'static str = "127.0.0.1:7878";

    println!("Binding listener");

    let listener = TcpListener::bind(full_addr);
    let listener = match listener {
        Ok(listener) => listener,
        Err(e) => {
            panic!("Failed to bind to {}, error: {:?}", full_addr, e);
        },
    };

    println!("Listener bound");

    for stream in listener.incoming() {
        match stream {
            Ok(stream) => {
                println!("Connection established!");
                handle_client(stream);
            }
            Err(e) => {
                eprintln!("Connection failed: {}", e);
            }
        }
    }
}

fn handle_client(mut stream: std::net::TcpStream) -> std::io::Result<()> {
    let mut buf = [0; 6];

    #[cfg(CFG_DEBUG_ON)]
    {
        println!("Before reading from stream, buf is {:?}", buf);
    }

    stream.read(&mut buf)?; // TODO validate/check error handling

    #[cfg(CFG_DEBUG_ON)]
    {
        println!("str::from_utf8(&buf) is: {:?}", str::from_utf8(&buf));
    }

    #[cfg(CFG_DISABLED)]
    {
        // TODO fix or ditch, does not work as expected
        assert!(str::from_utf8(&buf).is_err());
    }

    let buf_as_str = str::from_utf8(&buf).unwrap(); // TODO error handling (drafted above and below)

    #[cfg(CFG_DISABLED)]
    {
        let buf_as_str = str::from_utf8(&buf);
        // TODO make this work
        let buf_as_str = match buf {
            Ok(buf_as_str) => buf_as_str.unwrap(),
            Err(e) => {
                println!("Failed to convert from utf8, error: {}", e);
            },
        };
    }

    // TODO actually handle the received string
    println!("After reading from stream, \n\tbuf is:\t\t{:?}, \n\tbuf_as_str is:\t{}", buf, buf_as_str);

    Ok(())
}
