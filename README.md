# Sushi

## Features

- Easy file packing
- No external programs. Just the JAR.
- A rather small library



## Limitations

- Only up to 256 files per package
- Max data in the package is just under 2GB (Limited by signed integers)
- Reads bytes as ASCII, writes bytes as ASCII. This may be changed in the future.
- File names CANNOT contain SOH (0x01), EOT (0x04), FS (0x1C), or GS (0x1D) characters.



## How it works

I'm proud of myself. At the same time, I kinda feel like this is useless. Anyways, the file always starts with "Sushi Package *Version*\u0001". It generates a header after scanning the files' length and generates a small bit of data I like to call the "SushiData". It's the file name, the start position, and the end position. Each bit of data is separated by a GS character. Each file marker is separated by a FS character. The header always ends with an End Of Transmission character. Then, all the data follows after that. Rather basic, yes. But still rather useful.



## TODO

There are many things I need to add, even after the first release. For instance, encryption. And checksums. And flags for the decompiler.
Things I need to do:
[ ] Fix the problem with packing non-text based files
[ ] Encryption
[ ] Checksums
[ ] Flags
[ ] More byte grouping options (If possible)
[ ] File manager??
