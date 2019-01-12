# MySudokuValidator
Quick implementation of validating Sudoku using brute force approach with backtracking.

Example of usage:

`validate.bat SudokuPuzzleExample.txt`

Default lower limit of clues is 17 :)
But it can be configured with 2nd command line argument, e.g.:

`validate.bat SudokuPuzzleExample16Clues.txt 16`

Requires Java 8 or later.

TODO implement the validation with Exact Cover approach using the following information sources (in order of knowledge extension):
- https://en.wikipedia.org/wiki/Sudoku
- https://en.wikipedia.org/wiki/Sudoku_solving_algorithms
- https://en.wikipedia.org/wiki/Exact_cover
- https://www.stolaf.edu/people/hansonr/sudoku/exactcovermatrix.htm
- https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X
- https://en.wikipedia.org/wiki/Dancing_Links
- Donald Knuth's "Dancing links" document (http://www-cs-faculty.stanford.edu/~uno/papers/dancing-color.ps.gz)
- Solving Sudoku efficiently with Dancing Links https://www.kth.se/social/files/58861771f276547fe1dbf8d1/HLaestanderMHarrysson_dkand14.pdf
