#!/usr/bin/env python
import sys

# input comes from standard input (STDIN)
for line in sys.stdin:
    line = line.strip()           # remove leading/trailing spaces
    words = line.split()          # split line into words
    for word in words:
        print('%s\t%s' % (word,1))       # output key-value pair to STDOUT
