//---------------------------------------------------------------------------
// EdgeDetection.CPP
// Uses the given image library to first smooth and then find its edges.
// The number of times to smooth the image is passed in through a bat file.
// It then uses the smoothed image to find the edges.  The greater the number
// of times the image is smoothed the finer the edges.  It prints out two
// images, one of the smoothed image and one of the edge image.  
// Yezen Rashid, CSS 487
//---------------------------------------------------------------------------

#include "Image.h"
#include <math.h>
#include <stdio.h>
#include <iostream>
using namespace std;

void initializeImage(Image &output);
Image convolveImage(Image m, Image kernal, int midX, int midY);
Image createGMag(Image gx, Image gy);
Image nonMaximaSup(Image gMag, Image gx, Image gy);
float interpolatePoint(Image &im, float x, float y);

// main
// Preconditions: char *argv[] must contain the the number of iterations to 
//			      smooth. test.gif exists and is a correctly formatted GIF image.
// Postconditions: Creates two images as "smooth.gif" and "edge.gif" by applying 
//				   different levels of smoothing and detecting the edges in test.gif 
void main(int argc, char *argv[]) {
	double smoothTimes;
	sscanf_s(argv[1], "%lf", &smoothTimes);

	Image output("test.gif");

	//Initializes smoothing kernal for x direction
	Image Sx(1, 3);
	Sx.setFloat(0, 0, 0.25);
	Sx.setFloat(0, 1, 0.5);
	Sx.setFloat(0, 2, 0.25);

	//Initializes smoothing kernal for y direction
	Image Sy(3, 1);
	Sy.setFloat(0, 0, 0.25);
	Sy.setFloat(1, 0, 0.5);
	Sy.setFloat(2, 0, 0.25);

	//Initializes x gradient kernal
	Image kernelGx(1, 3);
	kernelGx.setFloat(0, 0, -1);
	kernelGx.setFloat(0, 1, 0);
	kernelGx.setFloat(0, 2, 1);

	//Initializes y gradient kernal
	Image kernelGy(3, 1);
	kernelGy.setFloat(0, 0, -1);
	kernelGy.setFloat(1, 0, 0);
	kernelGy.setFloat(2, 0, 1);

	initializeImage(output);

	Image smooth(output.getRows(), output.getCols());
	//Smooth number of times passed in bat file in x and y direction
	//for (int i = 0; i < smoothTimes; i++) {
	//	output = convolveImage(output, Sx, Sx.getCols() / 2, Sx.getRows() / 2);
	//	output = convolveImage(output, Sy, Sy.getCols() / 2, Sy.getRows() / 2);
	//}
	smooth = output;

	Image gx(output.getRows(), output.getCols());
	Image gy(output.getRows(), output.getCols());
	
	gx = convolveImage(output, kernelGx, kernelGx.getCols() / 2, kernelGx.getRows() / 2);
	gy = convolveImage(output, kernelGy, kernelGy.getCols() / 2, kernelGy.getRows() / 2);
	//gx.writeFloatImage("gx.gif");
	//gy.writeFloatImage("gy.gif");

	Image gMag = createGMag(gx, gy);
	//gMag.writeFloatImage("gMag.gif");

	output = nonMaximaSup(gMag, gx, gy);

	//convert back to grey
	for (int r = 0; r < output.getRows(); r++) {
		for (int c = 0; c < output.getCols(); c++) {
			byte greyEdge = static_cast<byte>(output.getFloat(r, c));
			output.setGrey(r, c, greyEdge);

			byte greySmooth = static_cast<byte>(smooth.getFloat(r, c));
			smooth.setGrey(r, c, greySmooth);
		}
	}
	smooth.writeGreyImage("smooth.gif");
	output.writeGreyImage("edge.gif");
}

// initializeImage
// Preconditions:  The input image must exist.
// Postconditions: Initilizes all the pixel float values in the passed image
//				   to grey.
void initializeImage(Image &output) {
	for (int r = 0; r < output.getRows(); r++) {
		for (int c = 0; c < output.getCols(); c++) {
			output.setFloat(r, c, output.getPixel(r, c).grey);
		}
	}
}

// Takes two images with "im" representing the image to be convolved and "kernal"
// which is a small image with the kernal values.  Also takes the origin of the
// kernal. This method accomplishes linear filtering through discrete convolution
// to apply the characteristics of the kernal to the image.
// Preconditions: The input image and kernal must exist. The origin of the kernal 
//				  must be valid and the kernal width and height must be greater 
//				  than 0.
// Postconditions: The passed image is modified using the kernal values. 
//				   If the kernal is out of bounds then the nearest neighbor is used.
Image convolveImage(Image im, Image kernal, int midX, int midY) {
	int rows = im.getRows();
	int cols = im.getCols();
	int kRows = kernal.getRows();
	int kCols = kernal.getCols();
	// Every value initialized as 0 for running total
	Image newImage(im.getRows(), im.getCols());

	for (int r = 0; r < rows; r++) {
		for (int c = 0; c < cols; c++) {
			// start from the end of matrix for flipped rows of kernal
			for (int i = kRows - 1; i >= 0; i--) {
				// start from the end of matrix for flipped Cols of kernal
				for (int j = kCols - 1; j >= 0; j--) {

					// x and y position in relation to center of kernal
					// kernal flipped back to original position
					int yPos = r + (kRows - i - 1) - midY;
					int xPos = c + (kCols - j - 1) - midX;

					// Check if out of bounds and adjust
					if (yPos < 0) {
						yPos = 0;
					}
					if (yPos >= rows) {
						yPos = rows - 1;
					}
					if (xPos < 0) {
						xPos = 0;
					}
					if (xPos >= cols) {
						xPos = cols - 1;
					}

					float runningTotal = newImage.getFloat(r, c) + 
						im.getFloat(yPos, xPos) * kernal.getFloat(i, j);
					newImage.setFloat(r, c, runningTotal);
				}
			}
		}
	}
	return newImage; // set current image to newly smoothed image
}

// createGMag
// Preconditions: The input images must exist. The images need to have the correct
//				  application on them or else this method wont work. All images
//				  must have the same dimensions and pixels.
// Postconditions: Returns a new image with the gradient magnitude using the passed 
//				   gradient images by finding the length of the vector.
Image createGMag(Image gx, Image gy) {
	int rows = gx.getRows();
	int cols = gx.getCols();
	Image gMag(rows, cols);

	for (int r = 0; r < rows; r++) {
		for (int c = 0; c < cols; c++) {
			float gMagVal = sqrt(pow(gx.getFloat(r, c), 2) +
				pow(gy.getFloat(r, c), 2));
			gMag.setFloat(r, c, gMagVal);
		}
	}

	return gMag;
}

//nonMaximaSup
// Takes three images representing the gradient magnitude, the gradient in the x
// direction, and the gradient in the y direction. It uses these to apply 
// non-maxima suppression to determine the edges in the image. 
// Preconditions: The input images must exist. The images need to have the correct
//				  application on them or else this method wont work. All images
//				  must have the same dimensions and pixels.
// Postconditions: Returns a new image with the found edges through non maxima
//				   suppression. Sets the edges that are found as white and 
//                 everything else as black.  
Image nonMaximaSup(Image gMag, Image gx, Image gy) {
	Image edge(gMag.getRows(), gMag.getCols());
	int rows = gMag.getRows();
	int cols = gMag.getCols();

	for (int r = 0; r < rows; r++) {
		for (int c = 0; c < cols; c++) {
			float x = 0;
			float y = 0;

			if (gMag.getFloat(r, c) != 0) {
				x = gx.getFloat(r, c) / gMag.getFloat(r, c);
				y = gy.getFloat(r, c) / gMag.getFloat(r, c);
			}

			// r point found in direction of the positive magnitude
			float rx = c + x;
			float ry = r + y;
			// p point found in direction of the negative magnitude
			float px = c - x;
			float py = r - y;

			float pVal = interpolatePoint(gMag, px, py);
			float rVal = interpolatePoint(gMag, rx, ry);

			// If it passes this criteria it is an edge
			if (gMag.getFloat(r, c) >= 10 && gMag.getFloat(r, c) >
				pVal && gMag.getFloat(r, c) > rVal) {
				edge.setFloat(r, c, 255);
			} else {
				edge.setFloat(r, c, 0);
			}
		}
	}
	return edge;
}

// interpolatePoint
// Preconditions: row and col are greater than (or equal to) zero
//				  row < getRow(), col < getCol()
// Postconditions: takes an image and the float of x and y values representing a
//				   point.  It then uses the color values for the four sourrounding 
//				   points and applies a bilinear interpolation to average and smooth 
//				   out the pixel.  Returns a float representing the color of the
//				   pixel.
float interpolatePoint(Image &im, float pointX, float pointY) {
	int rows = im.getRows();
	int cols = im.getCols();

	int x = (int)pointX;
	int y = (int)pointY;
	float a = pointY - y;
	float b = pointX - x;

	int yPlus = y + 1;
	int xPlus = x + 1;
	// Check if out of bounds and find nearest neighbor
	if (y < 0) {
		y = 0;
	}
	if (y >= rows) {
		y = rows - 1;
	}
	if (x < 0) {
		x = 0;
	}
	if (x >= cols) {
		x = cols - 1;
	}
	if (yPlus >= rows) {
		yPlus = rows - 1;
	}
	if (xPlus >= cols) {
		xPlus = cols - 1;
	}
	
	float interpolate = (1 - a) * (1 - b) * im.getFloat(y, x) + a * (1 - b) *
		im.getFloat(yPlus, x) + (1 - a) * b * im.getFloat(y, xPlus) +
		a * b * im.getFloat(yPlus, xPlus);
	return interpolate;
}
