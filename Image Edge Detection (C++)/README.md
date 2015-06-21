Uses the given image library to first smooth and then find its edges. The number of times to smooth the image is passed in through a bat file. It then uses the smoothed image to find the edges.  The greater the number of times the image is smoothed the finer the edges.  It prints out two images, one of the smoothed image and one of the edge image. 

I have also included the gradient magnitude image as well as the images for the gradient in the y and x direction. 

Methodology:
- First convolves the Image with a smoothing kernel the number of times indicated by the bat file.  (The more you smooth the image the less edges that you find)
- The image is then convolved with a gradient kernel in the x and y directions.
- The x gradient image and y gradient image are then used to create a gradient magnitude image.
- The x gradient, y gradient, and gradient magnitude images are used for non-maxima supression and bilinear interpolation in order to determine the edges of the image.
- The image is then converted into a grey image and is printed.
