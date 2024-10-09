## Simple Shell

- We created a basic version of the Unix shell from scratch. A program that takes commands from the keyboard and gives them to the operating system to perform. The shell can perform commands such as listing files in current working directory by typing `ls`, `exit`, among others. It works in both interactive and non-interactive mode.

- The following are the allowed functions and system calls.
- `access`, `chdir`, `close`, `closedir`, `execve`, `exit`, `_exit`, `fflush`, `fork`,`free`, `getcwd`, `getline`, `getpid`, `isatty`, `kill`, `malloc`, `open`, `opendir`, `perror`, `read`, `readdir`, `signal`, `stat`, `lstat`, `fstat`, `strtok`, `wait`, `waitpid`, `wait3`, `wait4`, `write`

## File Descriptions

- `AUTHORS`: Has the names of this project's contributors.
- ```execute```: Contains the function that executes shell commands.
- `_strings.c`, `_strings2.c`, `_strings3.c`: Contains functions that are used to manipultate and get data about strings like finding string length and splitting a string.
- `built-in.c`: Includes functions to perform built-in shell command operations like `exit`.
- `holberton.h`: Holds all function prototypes and headers.
- `main.c`: The shell's entry point i.e contains the main method.
- `man_1_simple_shell`: A manual for the shell.
- `prompt.c`: Prints the shell's title to indicate the shell is ready to receive input.
- `readline`: Responsible for picking commands typed into the shell.
- `handle_path.c`: Contains functions used to handle cases when a command is entered into the shell instead of the path to the executable file. For example when a use types `ls` instead of `\bin\ls`;

## How to use the shell

- Install

```
(your_terminal)$ git clone <this repository>
(your_terminal)$ cd simple_shell
```
- Compile

```
gcc -Wall -Werror -Wextra -pedantic *.c -o hsh
```

- Usage: non-interactive mode

```
echo "/bin/ls" | ./hsh
```

- Usage: interactive mode

```
(your_terminal)$ ./hsh
```

## Example

```
#cisfun$ /bin/ls -l
total 68
-rw-rw-r-- 1 vagrant vagrant   168 Aug 15 06:39 AUTHORS
-rw-rw-r-- 1 vagrant vagrant  1761 Aug 21 06:59 README.md
-rw-rw-r-- 1 vagrant vagrant   887 Aug 20 10:27 _execute.c

#cisfun$ exit 100
(your_terminal)$
```
More info on our blog [here](https://www.linkedin.com/pulse/how-shell-works-collins-otieno)

## Authors
1. [Collins Otieno](https://github.com/collin-5)
2. [Nehemiah Kamolu](https://github.com/knehe/)
