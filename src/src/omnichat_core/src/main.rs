extern crate libc;
use libc::int32_t;

#[link(name = "ktls", kind = "static")]
extern {
    fn fun1() -> int32_t;
    fn fun2() -> int32_t;
}

fn main() {
    let ret1 = unsafe { fun1() };
    let ret2 = unsafe { fun2() };
    println!("fun1 returned: {}", ret1);
    println!("fun2 returned: {}", ret2);
}
