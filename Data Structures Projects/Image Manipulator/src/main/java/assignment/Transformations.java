package assignment;

/*
 *
 * CS314H Programming Assignment 1 - Java image processing
 *
 * Included is the Invert effect from the assignment.  Use this as an
 * example when writing the rest of your transformations.  For
 * convenience, you should place all of your transformations in this file.
 *
 * You can compile everything that is needed with
 * javac -d bin src/main/java/assignment/*.java
 *
 * You can run the program with
 * java -cp bin assignment.JIP
 *
 * Please note that the above commands assume that you are in the prog1
 * directory.
 */

import java.util.ArrayList;
import java.util.Collections;


//Helper class for any effects that need to remove certain colors from all the pixels
//Contains static method used for other color modifying effects
//Default behavior is removing all color; i.e. creating a black image
class RemoveColor extends ImageEffect {
	
	//Removes all color from an image; creates a black image
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
        boolean[] doRemove = { true, true, true };
        return remove(pixels, doRemove);
    }
    
    //Method for removing any selected colors from all the pixels in an image
    public static int[][] remove(int[][] pixels, boolean[] doRemove) {
		int rows = pixels.length;
		if (rows == 0) return pixels;
        int cols = pixels[0].length;
        if (cols == 0) return pixels;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
            	
            	int pixel = pixels[r][c];
            	int[] colors = new int[3]; //Stores values for red, green, and blue in that order
            	
            	//Sets color values to zero for whichever colors are specified to be removed
            	if (!doRemove[0]) colors[0] = getRed(pixel);
            	if (!doRemove[1]) colors[1] = getGreen(pixel);
            	if (!doRemove[2]) colors[2] = getBlue(pixel);
            	
            	pixels[r][c] = makePixel(colors[0], colors[1], colors[2]);
            	
            }
        }
        
        return pixels;
	}
    
}


//Removes all red from an image
class NoRed extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
        boolean[] doRemove = { true, false, false };
        return RemoveColor.remove(pixels, doRemove);
    }
    
}


//Removes all green from an image
class NoGreen extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	boolean[] doRemove = { false, true, false };
        return RemoveColor.remove(pixels, doRemove);
    }
    
}


//Removes all blue from an image
class NoBlue extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	boolean[] doRemove = { false, false, true };
        return RemoveColor.remove(pixels, doRemove);
    }
    
}


//Removes all green and blue from an image
class RedOnly extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	boolean[] doRemove = { false, true, true };
        return RemoveColor.remove(pixels, doRemove);
    }
    
}


//Removes all red and blue from an image
class GreenOnly extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	boolean[] doRemove = { true, false, true };
        return RemoveColor.remove(pixels, doRemove);
    }
    
}


//Removes all red and green from an image
class BlueOnly extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	boolean[] doRemove = { true, true, false };
        return RemoveColor.remove(pixels, doRemove);
    }
    
}


//Replaces all pixel RGB values with the average of the three values, result is a grayscaled image
class BlackAndWhite extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
		int rows = pixels.length;
		if (rows == 0) return pixels;
        int cols = pixels[0].length;
        if (cols == 0) return pixels;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
            	
            	int pixel = pixels[r][c];
            	//Individual RGB values replaced with the average of RGB values
            	int averagePixel = (getRed(pixel) + getGreen(pixel) + getBlue(pixel)) / 3;
            	pixels[r][c] = makePixel(averagePixel, averagePixel, averagePixel);
            	
            }
        }
        
        return pixels;
    }
    
}


//Flips an image along a vertical line in the middle
class VerticalReflect extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
		int rows = pixels.length;
		if (rows == 0) return pixels;
        int cols = pixels[0].length;
        if (cols == 0) return pixels;

        for (int r = 0; r < rows; r++) {
        	//Only loop through first half of columns, flip pixels on both sides of the middle vertical line
            for (int c = 0; c < cols / 2; c++) {
            	
            	int pixel = pixels[r][c];
            	
            	//corresponding column to the right of the reflection line to be flipped with current one
            	int oppositeCol = cols - c - 1;
            	int oppositePixel = pixels[r][oppositeCol];
            	pixels[r][c] = oppositePixel;
            	pixels[r][oppositeCol] = pixel;
            	
            }
        }
        
        return pixels;
    }
    
}


//Flips an image along a horizontal line in the middle
class HorizontalReflect extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
		int rows = pixels.length;
		if (rows == 0) return pixels;
        int cols = pixels[0].length;
        if (cols == 0) return pixels;

        //Only lopp through first half of the rows, process pixels on both sides of horizontal line
        for (int r = 0; r < rows / 2; r++) {
            for (int c = 0; c < cols; c++) {
            	
            	int pixel = pixels[r][c];
            	
            	//Corresponding row under the reflection line to be flipped with current one
            	int oppositeRow = rows - r - 1;
            	int oppositePixel = pixels[oppositeRow][c];
            	pixels[r][c] = oppositePixel;
            	pixels[oppositeRow][c] = pixel;
            	
            }
        }
        
        return pixels;
    }
    
}


//Creates a new image with twice the dimensions, each pixel enlarging to four of the same pixel
class Grow extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
		int rows = pixels.length;
		if (rows == 0) return pixels;
        int cols = pixels[0].length;
        if (cols == 0) return pixels;
        
        int[][] newImage = new int[rows * 2][cols * 2]; //New array to store larger image

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
            	
            	int pixel = pixels[r][c];
            	
            	//The four new pixels' indices are a 2x2 matrix located at twice the old indices
            	newImage[r * 2][c * 2] = pixel;
            	newImage[r * 2 + 1][c * 2] = pixel;
            	newImage[r * 2][c * 2 + 1] = pixel;
            	newImage[r * 2 + 1][c * 2 + 1] = pixel;
            	
            }
        }
        
        return newImage;
    }
    
}


//Creates a new image with half the dimensions, each pixel an average of four pixels in the original image
class Shrink extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
		int rows = pixels.length;
		if (rows == 0) return pixels;
        int cols = pixels[0].length;
        if (cols == 0) return pixels;
        
        //Make sure to not create a null image, don't change the image instead
        if (rows / 2 == 0 || cols / 2 == 0) return pixels;
        
        int[][] newImage = new int[rows / 2][cols / 2]; //New array to store the smaller image

        for (int r = 0; r < rows / 2; r++) {
            for (int c = 0; c < cols / 2; c++) {
            	
            	//The four original pixels that make up one pixel on the smaller image
            	int[] originalPixels = new int[4];
            	originalPixels[0] = pixels[r * 2][c * 2];
            	originalPixels[1] = pixels[r * 2 + 1][c * 2];
            	originalPixels[2] = pixels[r * 2][c * 2 + 1];
            	originalPixels[3] = pixels[r * 2 + 1][c * 2 + 1];
            	
            	//Computing the sum and then average of all RGB values
            	int totalRed = 0, totalGreen = 0, totalBlue = 0;
            	for (int pixel : originalPixels) {
            		totalRed += getRed(pixel);
            		totalGreen += getGreen(pixel);
            		totalBlue += getBlue(pixel);
            	}
            	int newPixel = makePixel(totalRed / 4, totalGreen / 4, totalBlue / 4);
            	
            	newImage[r][c] = newPixel;
            	
            }
        }
        
        return newImage;
    }
    
}


//Replaces RGB values with either 0 or 255 depending on whether the value was larger than a threshold or not
class Threshold extends ImageEffect {

	//One parameter, prompting user for the threshold value, which must be between 0 and 255
    public Threshold() {
        params = new ArrayList<>();
        params.add(new ImageEffectParam("Threshold value",
                "The threshold value for an RGB component to be displayed on the final image",
                127, 0, 255));
    }

    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
		int rows = pixels.length;
		if (rows == 0) return pixels;
        int cols = pixels[0].length;
        if (cols == 0) return pixels;
        
        int thresholdValue = params.get(0).getValue(); //Threshold value as entered by user

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
            	
            	int pixel = pixels[r][c];
            	
            	//Replace RGB values according to threshold
            	int red = 0, green = 0, blue = 0;
            	if (getRed(pixel) > thresholdValue) red = 255;
            	if (getGreen(pixel) > thresholdValue) green = 255;
            	if (getBlue(pixel) > thresholdValue) blue = 255;
            	
            	pixels[r][c] = makePixel(red, green, blue);
            	
            }
        }
        
        return pixels;
    }
    
}


//Sample invert transformation
class Invert extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[y][x] = ~pixels[y][x];
            }
        }
        return pixels;
    }
}


//Inserts random colored pixels randomly around the image
class RandomNoise extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
		int rows = pixels.length;
		if (rows == 0) return pixels;
        int cols = pixels[0].length;
        if (cols == 0) return pixels;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
            	
            	//Only create a random pixel in the current spot with probability 1/100
            	double rand = Math.random();
        		if (rand > 0.01) continue;
        		
        		//Randomize the RGB values for the current pixel
            	pixels[r][c] = makePixel((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
            	
            }
        }
        
        return pixels;
    }
    
}


//Helper class for most effects implementing a convolution filter
//Contains static method with several parameters for specifying a convolution effect
//Default behavior is nothing
class Convolution extends ImageEffect {
	
	//Helper arrays for navigating through a 3x3 square around x
    static final int[] rowDiff = { 1, 1, 1, 0, 0, 0, -1, -1, -1 };
    static final int[] colDiff = { 1, 0, -1, 1, 0, -1, 1, 0, -1 };
    
	//A call to the default convolution effect will keep the image as is
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	double[][] convMatrix = {
        		{ 0, 0, 0 },
        		{ 0, 1, 0 },
        		{ 0, 0, 0 }
        };
        return convolute(pixels, convMatrix, 1, false);
    }
    
    //The convMatrix 2D array has weights for each of the pixels in a 3x3 grid around the given pixel
    //The overallMultiplier is a value to multiply all RGB values by after doing convolution
    //doAbsoluteValue is a boolean for whether or not negative RGB values should be made positive
    public static int[][] convolute(int[][] pixels, double[][] convMatrix, double overallMultiplier, boolean doAbsoluteValue) {
		int rows = pixels.length;
		if (rows == 0) return pixels;
        int cols = pixels[0].length;
        if (cols == 0) return pixels;
        
        int[][] newPixels = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
            	
            	double weightUsed = 0;
            	double[] colorSum = new double[3]; //Weighted sum of red, green, and blue values from the 3x3 grid
            	
            	for (int i = 0; i < 9; i++) {
            		
            		//newr and newc are the indices of one cell in the 3x3 neighboring grid
            		int newr = r + rowDiff[i];
            		int newc = c + colDiff[i];
            		
            		//Out of bounds check
            		if (newr < 0 || newr >= rows || newc < 0 || newc >= cols) continue;
            		
            		//Determine weight of current cell based on convolution
            		double weight = convMatrix[rowDiff[i] + 1][colDiff[i] + 1];
            		weightUsed += weight;
            		
            		//Add the weighted values onto the total sums
            		int pixel = pixels[newr][newc];
            		colorSum[0] += weight * getRed(pixel);
            		colorSum[1] += weight * getGreen(pixel);
            		colorSum[2] += weight * getBlue(pixel);
            		
            	}
            	
            	//Apply overall multiplier
            	double[] colorValues = new double[3];
            	for (int i = 0; i < 3; i++) {
            		colorValues[i] = colorSum[i] * overallMultiplier;
            	}
            	
            	//The total weight is a measure of "how many pixels were used," so unless the total weight was zero,
            	//divide the values by the total weight for a more accurate representation of a single pixel
            	if (Math.abs(weightUsed) > 0.0000001) {
            		for (int i = 0; i < 3; i++) {
            			colorValues[i] /= weightUsed;
            		}
            	}
            	
            	//Take absolute value of RGB values if specified
            	if (doAbsoluteValue) {
            		for (int i = 0; i < 3; i++) {
            			colorValues[i] = Math.abs(colorValues[i]);
            		}
            	}
            	
            	//Confine the RGB values to ints ranging from 0 to 255
            	int[] pixelValues = new int[3];
            	for (int i = 0; i < 3; i++) {
            		pixelValues[i] = (int) colorValues[i];
            		if (pixelValues[i] < 0) pixelValues[i] = 0;
            		else if (pixelValues[i] > 255) pixelValues[i] = 255;
            	}
            	
            	//Insert pixel into new grid
            	int averagePixel = makePixel(pixelValues[0], pixelValues[1], pixelValues[2]);
            	newPixels[r][c] = averagePixel;
            	
            }
        }
        
        return newPixels;
    }
    
}


//Smoothes out the image by updating each pixel value to be the average of a 3x3 neighborhood
class Smooth extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	
    	//All pixels have equal weights in an average
        double[][] convMatrix = {
        		{ 1, 1, 1 },
        		{ 1, 1, 1 }, 
        		{ 1, 1, 1 }
        };
        return Convolution.convolute(pixels, convMatrix, 1, false);
    }
    
}


//Makes edges look more defined and bolder
class EmphasizeEdges extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	
    	//The pixel itself has a weight of 1, and neighboring pixels' weights sum to zero
    	//So if neighboring pixels are significantly different, that will cause the RGB values of this pixel to shift significantly
    	//And if neighboring pixels are similar, then this pixel won't change much
        double[][] convMatrix = {
        		{ -1, 1, -1 },
        		{ 1, 1, 1 },
        		{ -1, 1, -1 }
        };
        return Convolution.convolute(pixels, convMatrix, 1, true);
    }
    
}


//Replaces areas of low contrast with dark pixels, and areas of high contrast with colored pixels, can only detect left-right discrepancies well
class ColoredEdges extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	
    	//This pixel isn't considered, and only the left and right neighbors are considered
    	//If the left and right side of the neighborhood are significantly different, then this pixel will obtain a lighter color
    	//Otherwise, this pixel will be dark
    	//The effectiveness of this convolution matrix isn't very high since it only checks left to right differences, however
    	//This avoids producing images that have too much random noise
    	//Has the side effect of slightly shifting the image left
        double[][] convMatrix = {
        		{ -1, 0, 1 },
        		{ -1, 0, 1 },
        		{ -1, 0, 1 }
        };
        return Convolution.convolute(pixels, convMatrix, 1, false);
    }
    
}


//Brightens the image
class Brighten extends ImageEffect {
	
	//One parameter prompting the user for how much brightening they want
	public Brighten() {
        params = new ArrayList<>();
        params.add(new ImageEffectParam("Brighten Factor",
                "The multiplier by which to brighten this image by (in percent, min: 100, max: 1000)",
                100, 100, 1000));
    }
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	
    	//Only this pixel is considered, and all its RGB values are multiplied by the brighten factor
        double[][] convMatrix = {
        		{ 0, 0, 0 },
        		{ 0, 1, 0 },
        		{ 0, 0, 0 }
        };
        
        //Get the user entered parameter
        int percentBrightening = params.get(0).getValue();
        return Convolution.convolute(pixels, convMatrix, percentBrightening / 100.0, false);
    }
    
}


//Darkens the image
class Darken extends ImageEffect {
	
	//One parameter prompting the user for how much darkening they want
	public Darken() {
        params = new ArrayList<>();
        params.add(new ImageEffectParam("Darken Factor",
                "The percent darkening that you want to apply to this image (in percent, min: 0, max: 100)",
                0, 0, 100));
    }
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	
    	//Only this pixel is considered, and all its RGB values are halved to decrease intensity
        double[][] convMatrix = {
        		{ 0, 0, 0 },
        		{ 0, 1, 0 },
        		{ 0, 0, 0 }
        };
        
        //Get the user entered parameter and convert to a multiplier
        int percentDarkening = params.get(0).getValue();
        double darkenFactor = 1 - percentDarkening / 100.0;
        return Convolution.convolute(pixels, convMatrix, darkenFactor, false);
    }
    
}


//Blurs the image along the northwest/southeast diagonal
class BlurLeft extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	
    	//This pixel is ignored, and instead an average of the other two pixels on the northwest/southeast diagonal is considered
    	//Thus, blurring the image along that diagonal
        double[][] convMatrix = {
        		{ 1, 0, 0 },
        		{ 0, 0, 0 },
        		{ 0, 0, 1 }
        };
        return Convolution.convolute(pixels, convMatrix, 1, false);
    }
    
}


//Blurs the image along the northeast/southwest diagonal
class BlurRight extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	
    	//This pixel is ignored, and instead an average of the other two pixels on the northeast/southwest diagonal is considered
    	//Thus, blurring the image along that diagonal
        double[][] convMatrix = {
        		{ 0, 0, 1 },
        		{ 0, 0, 0 },
        		{ 1, 0, 0 }
        };
        return Convolution.convolute(pixels, convMatrix, 1, false);
    }
    
}


//Blurs the image vertically
class BlurVertical extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	
    	//This pixel is ignored, and instead an average of the other two pixels along the vertical is considered
    	//Thus, blurring the image vertically
        double[][] convMatrix = {
        		{ 0, 1, 0 },
        		{ 0, 0, 0 },
        		{ 0, 1, 0 }
        };
        return Convolution.convolute(pixels, convMatrix, 1, false);
    }
    
}


//Blurs the image horizontally
class BlurHorizontal extends ImageEffect {
	
  public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
  	
  	//This pixel is ignored, and instead an average of the other two pixels along the horizontal is considered
  	//Thus, blurring the image horizontally
      double[][] convMatrix = {
      		{ 0, 0, 0 },
      		{ 1, 0, 1 },
      		{ 0, 0, 0 }
      };
      return Convolution.convolute(pixels, convMatrix, 1, false);
  }
  
}


//"Increases" the number of edges/duplicates existing edges in the image
class IncreaseEdges extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	
    	//Essentially a smaller version of HighlightEdges, where only a subset of neighboring pixels are considered
    	//If neighboring pixels are significantly different, the current pixel will be significantly changed to look like the neighbors
    	//But because not all neighbors are considered, gaps are left between edges in the process, giving rise to the duplicate edge effect
        double[][] convMatrix = {
        		{ 0, 1, 0 },
        		{ 0, -2, 0 },
        		{ 1, 0, 1 }
        };
        return Convolution.convolute(pixels, convMatrix, 1, false);
    }
    
}


//Increases the contrast in an image by brightening light pixels near dark pixels and darkening the latter
class Sharpen extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
		int rows = pixels.length;
		if (rows == 0) return pixels;
        int cols = pixels[0].length;
        if (cols == 0) return pixels;
        
        //Helper arrays for navigating through the 3x3 neighboring grid, stores changes in row and column
        final int[] rowDiff = { 1, 1, 1, 0, 0, -1, -1, -1 };
        final int[] colDiff = { 1, 0, -1, 1, -1, 1, 0, -1 };
        
        int[][] newPixels = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
            	
            	//Initializing several variables to keep track of RGB; colors array keeps track of this pixel's RGB
            	int numPixelsUsed = 0;
            	int pixel = pixels[r][c];
            	int[] colors = new int[3];
            	colors[0] = getRed(pixel);
            	colors[1] = getGreen(pixel);
            	colors[2] = getBlue(pixel);
            	
            	//Intensity as measured by taking the average of the three RGB values
            	int originalIntensity = (colors[0] + colors[1] + colors[2]) / 3;
            	
            	//sumOfDifferences measures the sum of differences in intensity between neighboring pixels and this pixel
            	//This measures how high the contrast is between the current pixel and neighboring pixels
            	int sumOfDifferences = 0;
            	
            	for (int i = 0; i < 8; i++) {
            		
            		//newr and newc are the coordinates of one neighboring pixel
            		int newr = r + rowDiff[i];
            		int newc = c + colDiff[i];
            		
            		//Bounds checking, just in case we are at the edge of an image
            		if (newr < 0 || newr >= rows || newc < 0 || newc >= cols) continue;
            		
            		//Add on the difference in intensity between the neighboring pixel and the original one
            		numPixelsUsed++;
            		pixel = pixels[newr][newc];
            		int intensity = (getRed(pixel) + getGreen(pixel) + getBlue(pixel)) / 3;
            		sumOfDifferences += originalIntensity - intensity;
            		
            	}
            	
            	//Find the average difference in intensity and add it to all RGB values to increase the contrast
            	int averageDifference = sumOfDifferences / numPixelsUsed;
            	for (int i = 0; i < 3; i++) {
            		colors[i] += averageDifference;
            		
            		//Confine the RGB values to values ranging from 0 to 255
            		if (colors[i] < 0) colors[i] = 0;
            		if (colors[i] > 255) colors[i] = 255;
            	}
            	
            	newPixels[r][c] = makePixel(colors[0], colors[1], colors[2]);
            	
            }
        }
        
        return newPixels;
    }
    
}


//Replace pixels with light pixels if they are an edge and dark pixels otherwise
class HighlightEdges extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
		int rows = pixels.length;
		if (rows == 0) return pixels;
        int cols = pixels[0].length;
        if (cols == 0) return pixels;
        
        //Helper arrays for navigating through the 3x3 neighboring grid, stores changes in row and column
        final int[] rowDiff = { 1, 1, 1, 0, 0, -1, -1, -1 };
        final int[] colDiff = { 1, 0, -1, 1, -1, 1, 0, -1 };
        
        int[][] newPixels = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
            	
            	int numPixelsUsed = 0;
            	
            	//totalSum keeps track of the sum of each RGB value
            	int[] totalSum = new int[3];
            	
            	for (int i = 0; i < 8; i++) {
            		
            		//newr and newc are the indices of a neighboring pixel
            		int newr = r + rowDiff[i];
            		int newc = c + colDiff[i];
            		
            		//Bounds checking in case we are at the edge of the image
            		if (newr < 0 || newr >= rows || newc < 0 || newc >= cols) continue;
            		
            		//Update sums
            		numPixelsUsed++;
            		int pixel = pixels[newr][newc];
            		totalSum[0] -= getRed(pixel);
            		totalSum[1] -= getGreen(pixel);
            		totalSum[2] -= getBlue(pixel);
            	}
            	
            	//The current pixel
            	int pixel = pixels[r][c];
            	
            	//Add the weighted current pixel's values onto the sum to determine
            	//how different the current pixel is from the neighboring ones
            	totalSum[0] += numPixelsUsed * getRed(pixel);
            	totalSum[1] += numPixelsUsed * getGreen(pixel);
            	totalSum[2] += numPixelsUsed * getBlue(pixel);
            	
            	//Determine the maximum difference in intensity for any RGB value
            	//This determines how light or dark the pixel is
            	int maxIntensityDiff = Math.max(totalSum[0], Math.max(totalSum[1], totalSum[2]));
            	
            	//Constrain the difference to values ranging from 0 to 255
            	if (maxIntensityDiff < 0) maxIntensityDiff = 0;
            	if (maxIntensityDiff > 255) maxIntensityDiff = 255;
            	
            	newPixels[r][c] = makePixel(maxIntensityDiff, maxIntensityDiff, maxIntensityDiff);
            	
            }
        }
        
        return newPixels;
    }
    
}


//Helper class for any image transformation needing to replace a pixel with a neighboring one
//Provides a static method where you can specify what neighbor to pick
//Default behavior is replacing each pixel with a random one in the 3x3 neighborhood
class ReplacePixel extends ImageEffect {
	
	//Pair class for keeping track of a pair of values
	static class pair implements Comparable<pair> {
		
		int pixel, intensity;
		
		public pair(int pixel1, int intensity1) {
			pixel = pixel1;
			intensity = intensity1;
		}

		//By default, this will sort in ascending order
		@Override
		public int compareTo(pair o) {
			return this.intensity - o.intensity;
		}
		
	}
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
        return replace(pixels, -1);
    }
    
    //type is expected to take one of four values: -1, 0, 1, and 2; explained more below
    public static int[][] replace(int[][] pixels, int type) {
		int rows = pixels.length;
		if (rows == 0) return pixels;
        int cols = pixels[0].length;
        if (cols == 0) return pixels;

        //Helper arrays for navigating through the 3x3 neighboring grid, stores changes in row and column
        final int[] rowDiff = { 1, 1, 1, 0, 0, 0, -1, -1, -1 };
        final int[] colDiff = { 1, 0, -1, 1, 0, -1, 1, 0, -1 };
        
        int[][] newPixels = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
            	
            	//Store all neighboring pixels inside an ArrayList along with their intensity value
            	ArrayList<pair> list = new ArrayList<>();
            	for (int i = 0; i < 9; i++) {
            		
            		//newr and newc are the indices of a neighboring pixel
            		int newr = r + rowDiff[i];
            		int newc = c + colDiff[i];
            		
            		//Bounds checking in case we are at the edge of the image
            		if (newr < 0 || newr >= rows || newc < 0 || newc >= cols) continue;
            		
            		//Calculating intensity value and inserting into ArrayList
            		int pixel = pixels[newr][newc];
            		int intensityValue = (getRed(pixel) + getGreen(pixel) + getBlue(pixel)) / 3;
            		list.add(new pair(pixel, intensityValue));
            		
            	}
            	
            	//Sort in ascending order
            	Collections.sort(list);
            	
            	//Pick the specific pixel that type tells us to
            	int pixel = 0;
            	switch(type) {
            	case(-1):
            		
            		//Pick random pixel, this is the default behavior
            		pixel = list.get((int) (Math.random() * list.size())).pixel;
            	break;
            	
            	case(0):
            		
            		//Pick the lowest intensity pixel
            		pixel = list.get(0).pixel;
            	break;
            	
            	case(1):
            		
            		//Pick the median intensity pixel
            		pixel = list.get(list.size() / 2).pixel;
            	break;
            	
            	case(2):
            		
            		//Pick the highest intensity pixel
            		pixel = list.get(list.size() - 1).pixel;
            	break;
            	
            	}
            	
            	newPixels[r][c] = pixel;
            	
            }
        }
        
        return newPixels;
    }
    
}


//Replaces each pixel with the median intensity pixel in the neighborhood
class DeNoise extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	
    	//Type is 1, indicates picking median intensity pixel
        return ReplacePixel.replace(pixels, 1);
    }
    
}


//Replaces each pixel with the lowest intensity pixel in the neighborhood
class Erode extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	
    	//Type is 0, indicates picking lowest intensity pixel
        return ReplacePixel.replace(pixels, 0);
    }
    
}


//Replaces each pixel with the highest intensity pixel in the neighborhood
class Dilate extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
    	
    	//Type is 2, indicates picking highest intensity pixel
        return ReplacePixel.replace(pixels, 2);
    }
    
}


//Widens the image by a factor of two without changing the height
class Widen extends ImageEffect {
	
    public int[][] apply(int[][] pixels, ArrayList<ImageEffectParam> params) {
		int rows = pixels.length;
		if (rows == 0) return pixels;
        int cols = pixels[0].length;
        if (cols == 0) return pixels;
        
        int[][] newImage = new int[rows][cols * 2];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
            	
            	//Copy each original pixel to two pixels on the final image, located at twice the column number of the original
            	int pixel = pixels[r][c];
            	newImage[r][c * 2] = pixel;
            	newImage[r][c * 2 + 1] = pixel;
            	
            }
        }
        
        return newImage;
    }
    
}
