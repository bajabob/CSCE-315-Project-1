## CSCE-315-Project-1 Traveling Salesman

### Notes
Received an A on this project, but this was not the fastest implementation when compared to my peers. Next time I attempt this problem I will implement a linear programming solution instead of a greedy algorithm. 

### Guidelines
Your program should read from the standard input a list of points
and find the shortest closed path connecting the points, in the shortest time.
The input will be the x and y coordinates of the points as non-negative
integers separated by white space (space, tab, newline, etc.); end of file is 
indicated by Control-D for console input.  Any line of input beginning with # 
is a comment, which your program should ignore.

### Compile & Run
To run: 
 - Open a terminal and navigate to this directory.
 - Type: `make`
 - Type: `java ShortestCircuit < tests/d.txt`

You can change the test files to be whatever you please,
"a.txt" - 1000 points
"b.txt" - 10 points
"c.txt" - sample provided by homework
"d.txt" - 500 points

### Sample output (on tests/b.txt):
```
8
4
0
2
3
1
6
9
7
5
8
297.48784942831895
```

### Overview of Data Structures employed
 * - Read User Input
 * - Calculate distance between points in ONLY the upper triangle of the
 *    distance matrix while concurrently storing points and distances in
 *    a priority queue. O((V^2)/2)
 * - Greedily select the next point to attempt to add to final graph out 
 *    of the priority queue. If the point can be added to a disjoint set
 *    without creating a cycle then do so. O(1) - best case, O(logV) - worst case
 * - Search for the chosen point in each of the created disjoint sets in 
 *    O(log(V)) time, since all points are stored in a Binary Search Tree.
 * - Lastly, match points in O((V^2)/2) time and output. 

### License
Copyright (c) 2015 Robert Timm

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
