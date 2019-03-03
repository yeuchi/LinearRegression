# LinearRegression
This is an exercise of the following skills.
- Kotlin/java mix 
- Least squares approximation (linear regression) 
- JetPack databinding
- Path/Line drawing 

### Path/Line
Using paint class to render touch point and regression line (more than 2 points)
<img width="400" src="https://user-images.githubusercontent.com/1282659/53699207-6c77c000-3dab-11e9-8740-ed8db2cc91eb.png">

### Least squares approximation
Find a common line by calculating the orthogonal projections of shortest total distance.
This code is defined in Numerical Analysis text referenced below.

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

