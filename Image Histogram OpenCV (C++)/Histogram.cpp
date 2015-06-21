// Histogram.cpp
// This program uses OpenCV to first compute the color histogram of an image
// and replace the most found color in the foreground image with a background 
// image to replicate the green screen effect. The second part of this program
// demonstrates the use of OpenCV methods to find the edges of the background image.
// Yezen Rashid

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <iostream>
using namespace cv;

// main - based on the histogram of the buckets replaces the color with the most
//		  votes with the background image. Also uses OpenCV methods to detect
//		  edges in background.jpg
// precondition: foreground.jpg and background.jpg exists in the code directory 
//			     and is a valid JPG
// postconditions: The new overlay image is output as "overlay.jpg" and the
//					modified background image is output as "output.jpg"
//					Also displays the images on the screen
//				    Note: waits for a key press between each image display.
int main(int argc, char* argv[]) {
	const int size = 4;
	// size is a constant - the # of buckets in each dimension
	int dims[] = { size, size, size };
	// 3D histogram of integers initialized to zero
	Mat hist(3, dims, CV_32S, Scalar::all(0)); 

	Mat foreground = imread("foreground.jpg");
	Mat background = imread("background.jpg");
	int bucketSize = 256 / size;

	// Goes through the foreground image and assigns each pixel to a 
	// histogram bucket.
	for (int r = 1; r < foreground.rows - 1; r++) {
		for (int c = 1; c < foreground.cols - 1; c++) {
			int blue = foreground.at<Vec3b>(r, c)[0] / bucketSize;
			int green = foreground.at<Vec3b>(r, c)[1] / bucketSize;
			int red = foreground.at<Vec3b>(r, c)[2] / bucketSize;
			hist.at<int>(red, green, blue)++;
		}
	}

	// This part finds the histogram bin with the most votes.
	int r;
	int g;
	int b;
	int mostVotes = 0;
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			for (int k = 0; k < size; k++) {
				if (hist.at<int>(i, j, k) > mostVotes) {
					mostVotes = hist.at<int>(i, j, k);
					r = i;
					g = j;
					b = k;
				}
			}
		}
	}

	// The most common approximate colors
	int cRed = r * bucketSize + bucketSize / 2;
	int cGreen = g * bucketSize + bucketSize / 2;
	int cBlue = b * bucketSize + bucketSize / 2;

	// Replace every pixel in the foreground image that is close to the most 
	// common color (no more than bucketSize away in all three color bands)
	// with the corresponding pixel from the background image
	for (int row = 1; row < foreground.rows - 1; row++) {
		for (int col = 1; col < foreground.cols - 1; col++) {
			b = foreground.at<Vec3b>(row, col)[0];
			g = foreground.at<Vec3b>(row, col)[1];
			r = foreground.at<Vec3b>(row, col)[2];
			
			if (b + bucketSize >= cBlue && b - bucketSize <= cBlue &&
				g + bucketSize >= cGreen && g - bucketSize <= cGreen &&
				r + bucketSize >= cRed && r - bucketSize <= cRed) {
				
				int backgroundRow = row % background.rows;
				int backgroundCol = col % background.cols;
				foreground.at<Vec3b>(row, col) =
					background.at<Vec3b>(backgroundRow, backgroundCol);
			}
		}
	}

	imwrite("Overlay.jpg", foreground);
	namedWindow("Overlay");
	imshow("Overlay", foreground);
	waitKey(0);

	// Second Part - Find edges in background.jpg
	Mat modifiedImage = background.clone();
	flip(background, modifiedImage, 1); // flip image horizontally
	cvtColor(modifiedImage, modifiedImage, COLOR_BGR2GRAY); // convert to gray image
	GaussianBlur(modifiedImage, modifiedImage, Size(7, 7), 2.0, 2.0); // blur image
	Canny(modifiedImage, modifiedImage, 20, 60); // find the edges

	imwrite("output.jpg", modifiedImage);
	namedWindow("Output Background Edges");
	imshow("Output", modifiedImage);
	waitKey(0);

	return 0;
}