# Simple determinant calculator
This project is a simple program that is able to read a matrix from a file
and calculate its determinant. 
The project was part of the teaching course of the Bachelor in Mathematics
course (Laurea in Matematica) at the university of Trento (IT), 
and follows the specification given by [Roberto Zunino](http://disi.unitn.it/~zunino/teaching/) 
in its course on Computer Science (Informatica) that can be found [here](http://disi.unitn.it/~zunino/teaching/informatica/progetto2022/progetto2022.html).
## Structure
The code is composed of three files:
- [Progetto.java](src/Progetto.java) which acts as a sort of "main method",
it holds the function `determinante` which "ties together" 
all the functions listed in the [Utils module](src/Utils.java).
- [Utils.java](src/Utils.java) holds most of the program logic,
it holds functions for reading matrices from `.txt` files and for computing the determinant
- [Test.java](src/Test.java) instead is an alternative class that automatically
executes the code on a sample set of matrices (obtained from the specification website)
and compares the computed determinants with their respective solution (available at the same website).
## Test cases
As stated previously, the test cases (along with their solution) were retrieved on the
professor's website, therefore the code runs on a very specific implementation of the problem.

#### Input format
The following is the input format for a matrix:

```
7
-1/2 1/1 1/4 -1/4 0/1 -1/5 1/2 
1/2 -1/1 1/2 -1/2 0/1 0/1 0/1 
0/1 1/5 -1/1 -1/8 -1/4 -3/5 1/1 
-5/9 1/6 0/1 1/3 -1/9 0/1 -3/5 
-1/3 -1/2 -1/1 1/2 3/2 -1/1 1/5 
-1/1 2/5 2/3 -1/3 4/5 -4/3 -2/9 
-1/3 3/8 1/3 -2/3 -1/4 -1/2 2/1 
```
And instead this is for the solution:
```
21432223/38880000
```

##### Filename format
The filename itself must respect the format `matrice-XXX.txt` for input matrices and `det_matrice-XXX.txt`
for the solution determinant, both must be placed in different text files and in the respective folder (`det` or `mat`), 
while having `XXX` replaced. The test class runs 100 test cases, so in case one wishes
to extend the test suite with additional cases, it would need also to modify the [Test.java](src/Test.java) file accordingly.

The code is available on [GitHub](https://github.com/Bjastkuliar/disi-unitn).
