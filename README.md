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
