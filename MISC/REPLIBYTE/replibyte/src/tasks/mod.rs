use std::io::Error;

pub mod full_dump;
pub mod full_restore;

pub type TransferredBytes = usize;
pub type MaxBytes = usize;

pub trait Task {
    fn run<F: FnMut(TransferredBytes, MaxBytes)>(self, progress_callback: F) -> Result<(), Error>;
}

/// inter-thread message for Source/Destination and Datastore
#[derive(Debug, Clone)]
enum Message<T> {
    Data(T),
    EOF,
}
