# Newton's Fractal

Link to an explanation of the fractal:
https://www.youtube.com/watch?v=-RdOwhmqP5s

the user input is entered into the program through a text file.

the image can be saved as a .png or a .jpg

|
|
Explanation of text file format:

|
|

Line 1: pixel width x pixel height (no spaces)  

Example: 1280x720

|
|
Line 2: "The complex number in bottom left corner of the picture", 
"The complex number in top right corner of the picture"

* (Input the number in the form: real + imaginary)
* (if the imaginary part is negative input +-)
* (you can't input just i you must instead input 1i)

Example: -4 + -2.25i, 4 + 2.25i

|
|
Line 3: The number of steps

Example: 100

|
|
Line 4: The roots of the inputted polynomial.
* (separate each root by commas) 
* (input each root in the form: real + imaginary)
* (if the imaginary part is negative input +-)
* (you can't input just i you must instead input 1i)

Example: -2 + 1i, -2 + -1i, 2 + 0i

This is equivalent to the polynomial:
* y = (x - (-2 + 1i)) * (x - (-2 - 1i)) * (x - (2 + 0i))

|
|
Line 5: the colors associated with each root.
* (separate each color by commas)
* (must have the same number of colors as roots)
* (color must be in the form: RRGGBB

Example: FF0000, 00FF00, 0000FF

|

|
Full Example:

1280x720

-4 + -2.25i, 4 + 2.25i

100

-2 + 1i, -2 + -1i, 2 + 0i

FF0000, 00FF00, 0000FF