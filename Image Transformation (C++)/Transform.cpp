//---------------------------------------------------------------------------
// Transform.CPP
// Uses the given image library to transform an image test.gif with the 
// ability to shear, rotate, scale, and translate test.gif and output the
// newly transformed image as output.gif.
// Yezen Rashid
//---------------------------------------------------------------------------

#include "Image.h"
#include <math.h>
#include <stdio.h>
#include <iostream>
using namespace std;

void initializeImage(Image &out);
void rotateImage(double degrees, double &x, double &y);
void scaleImage(double scaleFactorX, double scaleFactorY, double &x, double &y);
void shearImage(double shear, double &x, double &y);
byte interpolate(double a, double b, byte point1, byte point2, byte point3, 
	byte point4);

// main
// Preconditions: char *argv[] must contain the x_scale, y_scale, x_translate,
//				  y_translate, rotate, and shear in that order. test.gif exists
//				  and is a correctly formatted GIF image.
// Postconditions: Creates an image as output.gif by transforming test.gif 
//				   through a rotation, x and y scaling, translation, and 
//				   shearing.
void main(int argc, char *argv[]) {
	double x_scale;
	double y_scale;
	double x_translate;
	double y_translate;
	double rotate;
	double shear;

	sscanf_s(argv[1], "%lf", &x_scale);
	sscanf_s(argv[2], "%lf", &y_scale);
	sscanf_s(argv[3], "%lf", &x_translate);
	sscanf_s(argv[4], "%lf", &y_translate);
	sscanf_s(argv[5], "%lf", &rotate);
	sscanf_s(argv[6], "%lf", &shear);

	Image input("test.gif");
	Image output("test.gif");
	initializeImage(output);

	int rows = output.getRows();
	int cols = output.getCols();

	double pi = atan(1) * 4; // The value of pi
	double angle = rotate * (pi / 180); // converts degrees into radians

	for (int r = 0; r < rows; r++) {
		for (int c = 0; c < cols; c++) {
			double point_x = c - input.getCols() / 2;
			double point_y = r - input.getRows() / 2;

			point_x -= x_translate; // translate image by cols
			point_y -= y_translate; // translate image by rows

			scaleImage(x_scale, y_scale, point_x, point_y); // applies scale
			shearImage(shear, point_x, point_y); // applies shear
			rotateImage(angle, point_x, point_y); // applies rotate

			point_x += cols / 2; // recenters cols
			point_y += rows / 2; // recenters rows

			if (point_y < rows && point_x < cols &&	point_y >= 0 && point_x >= 0) {
				double a = point_y - (int)point_y;
				double b = point_x - (int)point_x;

				if (point_y + 1 < rows && point_y + 1 >= 0 && point_x + 1 < 
					cols && point_x + 1 >= 0) {
					byte red = interpolate(a, b, 
						input.getPixel(point_y, point_x).red,
						input.getPixel(point_y + 1, point_x).red,
						input.getPixel(point_y, point_x + 1).red,
						input.getPixel(point_y + 1, point_x + 1).red);

					byte green = interpolate(a, b, 
						input.getPixel(point_y, point_x).green,
						input.getPixel(point_y + 1, point_x).green,
						input.getPixel(point_y, point_x + 1).green,
						input.getPixel(point_y + 1, point_x + 1).green);

					byte blue = interpolate(a, b, 
						input.getPixel(point_y, point_x).blue,
						input.getPixel(point_y + 1, point_x).blue,
						input.getPixel(point_y, point_x + 1).blue,
						input.getPixel(point_y + 1, point_x + 1).blue);

					output.setPixel(r, c, red, green, blue);
				} else { // if the point is an edge there is no interpolation
					pixel p = input.getPixel(point_y, point_x);
					output.setPixel(r, c, p);
				}
			}
		}
	}

	output.writeImage("output.gif");
}

// initializeImage
// Preconditions: the input image must exist.
// Postconditions: Initilizes all the pixels in the output image to black.
void initializeImage(Image &out) {
	for (int r = 0; r < out.getRows(); r++) {
		for (int c = 0; c < out.getCols(); c++) {
			out.setInt(r, c, 0);
		}
	}
}

// rotateImage
// Preconditions: for the correct rotation the angle must be in radians.
// Postconditions: applies an inverse rotate for an output pixel to find a
//				   corresponding input pixel based upon the degree of
//				   rotation.
void rotateImage(double angle, double &x, double &y) {
	double result_x = cos(angle) * x + sin(angle) * y;
	double result_y = -sin(angle) * x + cos(angle) * y;

	x = result_x;
	y = result_y;
}

// scaleImage
// Preconditions: scaleFactorX and scaleFactorY cannot be 0.
// Postconditions: applies an inverse scale for an output pixel to find a
//				   corresponding input pixel based upon the scale factor.
void scaleImage(double scaleFactorX, double scaleFactorY, double &x, double &y) {
	if (scaleFactorX != 0 && scaleFactorY != 0) {
		x = x / scaleFactorX;
		y = y / scaleFactorY;
	} else {
		cerr << "The scale factor cannot be 0" << endl;
	}
}

// shearImage
// Preconditions: none
// Postconditions: applies an inverse shear for an output pixel to find a
//				   corresponding input pixel based upon the shear factor.
void shearImage(double shear, double &x, double &y) {
	x = x - (shear * y);
}

// interpolate
// Preconditions: row and col are greater than (or equal to) zero
//				  row < getRow(), col < getCol()
// Postconditions: takes the color values for the four sourrounding points 
//				   and applies a bilinear interpolation to average and smooth 
//				   out the pixel.  Returns a byte representing the color of the
//				   pixel.
byte interpolate(double a, double b, byte point1, byte point2, byte point3,
	byte point4) {
	double interpolate = (1 - a) * (1 - b) * point1 + a * (1 - b) *
		point2 + (1 - a) * b * point3 + a * b * point4;

	return static_cast<byte>(interpolate);
}