# LinearRegression
This is an exercise of the following skills.
- Kotlin/java mix 
- Least squares approximation (linear regression) 
- JetPack databinding
- Path/Line drawing 

### Path/Line
Using paint class to render touch point and regression line (more than 2 points)
<img width="500" src="https://user-images.githubusercontent.com/1282659/53699693-c038d800-3db0-11e9-9fe2-217c47332fb6.png">

### Least squares approximation
Find a common line by calculating the orthogonal projections of shortest total distance.
This code is defined in Numerical Analysis text referenced below.

<em><a href="https://www.codecogs.com/eqnedit.php?latex=least&space;squares:&space;\sum_{10}^{i=1}y_{i}&space;-(ax_{i}&space;&plus;&space;b)])^{2}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?least&space;squares:&space;\sum_{10}^{i=1}y_{i}&space;-(ax_{i}&space;&plus;&space;b)])^{2}" title="least squares: \sum_{10}^{i=1}y_{i} -(ax_{i} + b)])^{2}" /></a></em>


### Orthogonal lines
Shortest distance from point to regression line is orthogonal.  We can find their intersection with the following slope-intercept formulas.

<em><a href="https://www.codecogs.com/eqnedit.php?latex=m&space;=&space;\frac{(P1.y-P0.y))}{P1.x-P0.x}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?m&space;=&space;\frac{(P1.y-P0.y))}{P1.x-P0.x}" title="m = \frac{(P1.y-P0.y))}{P1.x-P0.x}" /></a></em>

<em><a href="https://www.codecogs.com/eqnedit.php?latex=b&space;=&space;\frac{(P1.y-P0.y))}{P1.x-0}&space;=&space;P1.y&space;-&space;m*&space;P1.x" target="_blank"><img src="https://latex.codecogs.com/gif.latex?b&space;=&space;\frac{(P1.y-P0.y))}{P1.x-0}&space;=&space;P1.y&space;-&space;m*&space;P1.x" title="b = \frac{(P1.y-P0.y))}{P1.x-0} = P1.y - m* P1.x" /></a></em>


<em><a href="https://www.codecogs.com/eqnedit.php?latex=line1:&space;P0.y&space;=&space;m*P0.x&space;&plus;&space;b" target="_blank"><img src="https://latex.codecogs.com/gif.latex?line1:&space;P0.y&space;=&space;m*P0.x&space;&plus;&space;b" title="line1: P0.y = m*P0.x + b" /></a></em>


<em><a href="https://www.codecogs.com/eqnedit.php?latex=Orthogonal&space;line:&space;P3.y&space;=&space;m'*P3.x&space;&plus;&space;b'" target="_blank"><img src="https://latex.codecogs.com/gif.latex?Orthogonal&space;line:&space;P3.y&space;=&space;m'*P3.x&space;&plus;&space;b'" title="Orthogonal line: P3.y = m'*P3.x + b'" /></a></em>


<em><a href="https://www.codecogs.com/eqnedit.php?latex=m'&space;=&space;\frac{1}{m}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?m'&space;=&space;\frac{1}{m}" title="m' = \frac{1}{m}" /></a></em>


<em><a href="https://www.codecogs.com/eqnedit.php?latex=Intersection&space;point.x&space;=&space;\frac{(b-b'))}{m'-m}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?Intersection&space;point.x&space;=&space;\frac{(b-b'))}{m'-m}" title="Intersection point.x = \frac{(b-b'))}{m'-m}" /></a></em>


<em><a href="https://www.codecogs.com/eqnedit.php?latex=Intersection&space;point.y&space;=&space;mx&plus;b" target="_blank"><img src="https://latex.codecogs.com/gif.latex?Intersection&space;point.y&space;=&space;mx&plus;b" title="Intersection point.y = mx+b" /></a></em>

### Kotlin
This is an exercise of mixing Kotlin with java while using data-binding.

### NOTE
This code base does not handle the following failure conditions.
1. rotation - nothing has been implemented to preserve the points nor re-render.
2. sorting - points are 'not' sorted; so touch screen (add points) from left -> right !

## References

1. Numerical Analysis 5th edition by Burden 
   Chapter 8.1 Page 436-442 Discrete Least Squares Approximation
    
2. Exercise demo in Adobe Flex
   http://www.ctyeung.com/flex/math/linearRegression/linearRegression.html

