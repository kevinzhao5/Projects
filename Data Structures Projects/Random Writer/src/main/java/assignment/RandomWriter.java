package assignment;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * CS 314H Assignment 2 - Random Writing
 *
 * Your task is to implement this RandomWriter class
 */
public class RandomWriter implements TextProcessor {
	
	
	//protected instance variables for a RandomWriter
	protected int level;
	protected StringBuilder inputText;
	protected HashMap<SeedWrapper, ArrayList<NextSeed>> map;
	protected ArrayList<Integer> hashCodes;
	
	//Constants to be set before program run based on how you want input newlines to be parsed
	protected static final boolean replaceNewLineWithSpace = true;
	protected static final boolean addNewLineCharacters = false;
	protected static final boolean insertNewLinesInOutput = false;
	
	
	//Check to see that the command line arguments are specified properly
	public static boolean validateCommandLineArguments(String[] args) {
				
		//Check for null argument
		if (args == null) {
    		System.err.println("Command line arguments are null!");
    		return false;
    	}
		
		
		//Check for correct number of command line arguments
    	if (args.length != 4) {
    		System.err.println("Incorrect number of command line arguments!");
    		return false;
    	}
    	
    	
    	//Make sure the command line arguments are not null
    	for (int i = 0; i < 4; i++) {
    		if (args[i] == null) {
    			System.err.println("Argument " + i + " is null! Please fix.");
    			return false;
    		}
    	}
    	
    	return true;
    	
	}
	
	
	//Check input file validity
	public static boolean validateInputFile(File inputFile) {
		
    	//Validate input file is readable
		if (!Files.isReadable(inputFile.toPath())) {
    		System.err.println("Input file is not readable!");
    		return false;
    	}
		
		return true;
		
	}
	
	//Check output file validity
	public static boolean validateOutputFile(File outputFile) {
		
		//Check for output file existence, and try to create if necessary
		if (!outputFile.isFile()) {
    		try {
    			outputFile.createNewFile();
    		} catch (IOException e) {
    			System.err.println("Could not create the output file!");
    			return false;
    		}
    	}
		
		
		//Validate that output file is writable
		if (!Files.isWritable(outputFile.toPath())) {
			System.err.println("Output file is not writable!");
			return false;
		}
		
		return true;
		
	}
	
	
	//Check level of analysis validity, return -1 if invalid
	public static int validateLevelOfAnalysis(String args2, String inputFileName) {
		
		//Validate that the level of analysis is a nonnegative int
    	int level = 0;
    	try {
    		level = Integer.parseInt(args2);
    	} catch (Exception e) {
    		System.err.println("Not a valid int format argument!");
    		return -1;
    	}
    	
    	if (level < 0) {
    		System.err.println("The level of analysis should not be negative!");
    		return -1;
    	}
    	
    	
    	//Validate that the level of analysis is smaller than the length of the file
		try {
			
			//Calculate the length of the input file
	    	BufferedReader in = new BufferedReader(new FileReader(inputFileName));
	    	int length = 0;
	    	while (in.ready()) {
	    		length += in.readLine().length();
	    		if (!in.ready()) break;
	       		if (replaceNewLineWithSpace || addNewLineCharacters) length++;
	    	}
	    	in.close();
	    	
	    	//Validate level of analysis length
	    	if (level >= length) {
	    		System.err.println("The level of analysis is too large!");
	    		return -1;
	    	}
	    	
		} catch (FileNotFoundException e1) {
			System.err.println("Could not find the input file!");
			return -1;
		} catch (IOException e2) {
			System.err.println("Could not read from the input file!");
			return -1;
		}
		
		return level;
		
	}
	
	
	//Check output length validity, return -1 if invalid
	public static int validateOutputLength(String args3) {
		
    	//Validate that the output length is an int
		int outputLength = 0;
    	try {
    		outputLength = Integer.parseInt(args3);
    	} catch (Exception e) {
    		System.err.println("Not a valid int format argument!");
    		return -1;
    	}
    	
    	//Vaidate that the output length is nonnegative
    	if (outputLength < 0) {
    		System.err.println("The output length should not be negative!");
    		return -1;
    	}
		
    	return outputLength;
    	
	}
	
	
    public static void main(String[] args) {

    	//Run preliminary validation on command line arguments
    	if (!validateCommandLineArguments(args)) return;
    	
    	
    	//Validate that the input file is specified properly
    	String inputFileName = args[0];
    	File inputFile = new File(inputFileName);
    	if (!validateInputFile(inputFile)) return;
    	
    	
    	//Check for output file's existence, and create if necessary
    	String outputFileName = args[1];
    	File outputFile = new File(outputFileName);
    	if (!validateOutputFile(outputFile)) return;
    	

    	//Validate that the level of analysis is valid
    	int level = validateLevelOfAnalysis(args[2], inputFileName);
    	if (level == -1) return;
    	
    	
    	//Validate that the output length is valid
    	int outputLength = validateOutputLength(args[3]);
    	if (outputLength == -1) return;
    	
    	
    	//Create the writer, read the input, and preprocess the text
    	TextProcessor writer = createProcessor(level);
    	try {
			writer.readText(inputFileName);
		} catch (IOException e) {
			System.err.println("An error occurred when reading the input file! Please make sure it is readable.");
			return;
		}
    	
    	
    	//Write the output
    	try {
			writer.writeText(outputFileName, outputLength);
		} catch (IOException e) {
			System.err.println("An error occurred when writing to the output file! Please make sure it is writable.");
		}
    	
    }
    
    
    // Unless you need extra logic here, you might not have to touch this method
    public static TextProcessor createProcessor(int level) {
    	return new RandomWriter(level);
    }

    
    private RandomWriter(int level) {
    	
    	//Initialize instance variables to default values
    	this.level = level;
    	inputText = new StringBuilder();
    	map = new HashMap<>();
    	hashCodes = new ArrayList<>();
    	
    	//Precompute numbers useful for calculating the hash codes
    	SeedWrapper.initPowers(level - 1);
    	
    }

    
    //Read the input and preprocess it into the HashMap
	public void readText(String inputFilename) throws IOException {
    	
		
    	//Storing contents of file into StringBuilder
    	BufferedReader in = new BufferedReader(new FileReader(inputFilename));
    	while (in.ready()) {
    		inputText.append(in.readLine());
    		if (!in.ready()) break;
    		
    		//Deal with new lines in the input file
    		if (replaceNewLineWithSpace) inputText.append(' ');
    		else if (addNewLineCharacters) inputText.append('\n');
    	}
    	in.close();
    	
    	
    	//Calculate the hash code for each possible seed using a rolling hash
    	int inputLen = inputText.length();
    	for (int i = 0; i <= inputLen - level; i++) {
    		SeedWrapper seed = new SeedWrapper(i, this);
    		hashCodes.add(seed.hashCode());
    	}
    	
    	
    	//Preprocess the input text by mapping each possible seed to all possible letters that can come after + the corresponding new seed
    	for (int i = 0; i <= inputLen - level; i++) {
    		
    		//Create a wrapper for the seed to avoid storing the seed string in the HashMap
    		SeedWrapper seed = new SeedWrapper(i, this);
    		
    		
    		//Special case of last seed in the input
    		if (i == inputLen - level) {
    			
    			if (!map.containsKey(seed)) {
        			map.put(seed, new ArrayList<NextSeed>());
    			}
    			
    			continue;
    			
    		}
    		
    		//This holds a pair of values, both the char that comes after the seed and the index of the start of the next seed
    		NextSeed connection = new NextSeed(inputText.charAt(i + level), i + 1);
    		
    		if (map.containsKey(seed)) {
    			
        		//If the seed has been seen before, add the new connection to the seed's list
    			map.get(seed).add(connection);
    			
    		} else {
    			
    			//Otherwise, create a new list for the seed and add it to the HashMap
    			ArrayList<NextSeed> connectionList = new ArrayList<>();
    			connectionList.add(connection);
    			map.put(seed, connectionList);
    			
    		}
    		
    	}
    	
    }
	

	//Write the text to the output file one character at a time, using the populated HashMap
    public void writeText(String outputFilename, int length) throws IOException {
    	
    	//Writer for printing text efficiently
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFilename)));
    	
    	
    	//Keep track of the current seed, how many characters printed, and how many characters on a certain line across iterations
    	int currentSeed = -1;
    	int totalCharsPrinted = 0;
    	int charsInLine = 0;
    	
    	
    	//Special case of level = 0, just pick random characters in the string
    	if (level == 0) {
    		
        	for (int i = 0; i < length; i++) {
        		
        		char currentChar = inputText.charAt((int) (Math.random() * inputText.length()));
        		
        		out.print(currentChar);
        		
        		//Update persistent information, break out of the loop if length characters is reached
	    		totalCharsPrinted++;
	    		if (totalCharsPrinted == length) {
	    			break;
	    		}
	    		charsInLine++;
	    		
	    		if (insertNewLinesInOutput) {
    	    		//For readability purposes, keep number of characters on a single line between 100 and 120, separating preferentially at spaces
    	    		if (charsInLine >= 100 && currentChar == ' ' || charsInLine >= 120) {
    	    			out.println();
    	    			charsInLine = 0;
    	    			totalCharsPrinted++;
    	    		}
	    		}
        		
        	}
        	
        	out.close();
        	return;
        	
    	}

    	
    	//Print 'length' number of characters
    	while (totalCharsPrinted < length) {
    		
    		//If we need to find a new seed (either the first seed or because the previous one ran out of succeeding characters
    		if (currentSeed == -1) {
    			
    			//Generate a random seed from all possible seeds in the string
    	    	currentSeed = (int) (Math.random() * (inputText.length() - level + 1));
    	    	
    	    	//Print all of the characters in the seed
    	    	for (int i = 0; i < level; i++) {
    	    		
    	    		//Print the character out
    	    		char currentChar = inputText.charAt(currentSeed + i);
    	    		out.print(currentChar);
    	    		
    	    		
    	    		//Update persistent information, break out of the loop if length characters is reached
    	    		totalCharsPrinted++;
    	    		if (totalCharsPrinted == length) {
    	    			break;
    	    		}
    	    		charsInLine++;
    	    		
    	    		if (insertNewLinesInOutput) {
        	    		//For readability purposes, keep number of characters on a single line between 100 and 120, separating preferentially at spaces
        	    		if (charsInLine >= 100 && currentChar == ' ' || charsInLine >= 120) {
        	    			out.println();
        	    			charsInLine = 0;
        	    			totalCharsPrinted++;
        	    		}
    	    		}
    	    		
    	    	}
    	    	
    		} else {
    			
    			//Check to see if there is at least one character that comes after the current seed
    			SeedWrapper currentSeedWrapper = new SeedWrapper(currentSeed, this);
    			if (map.get(currentSeedWrapper).size() == 0) {
    				
    				//If no possibilities, generate a new seed
    				currentSeed = -1;
    				continue;
    				
    			}
    			
    			
    			//If there are possible next characters based on the input, then get the list of all possible next characters
    			ArrayList<NextSeed> listOfConnections = map.get(currentSeedWrapper);
    			
    			//Pick a random character out of that set, characters that appear multiple times have a higher probability of being picked
    			NextSeed nextConnection = listOfConnections.get((int) (Math.random() * listOfConnections.size()));
    			char currentChar = nextConnection.getChar();
    			
    			//Print the character and update persistent data
    			out.print(currentChar);
    			totalCharsPrinted++;
    			if (totalCharsPrinted == length) {
	    			break;
	    		}
    			charsInLine++;
    			
    			if (insertNewLinesInOutput) {
    				//For readability purposes, keep number of characters on a single line between 100 and 120, separating preferentially at spaces
    				if (charsInLine >= 100 && currentChar == ' ' || charsInLine >= 120) {
    					out.println();
    					charsInLine = 0;
    					totalCharsPrinted++;
    				}
    			}
    			
    			//Update seed to the next seed, corresponding to what character we picked
    			currentSeed = nextConnection.getIndex();
    			
    		}
    		
    	}
    	
    	//Close the printer
    	out.close();
    	
    }
    
    //Wrapper class for seeds; implements functionality for rolling hash and stores index of seed instead of the string itself (for efficiency)
    protected static class SeedWrapper {
    	
    	
    	//Constants for the hash code computation
    	protected static final int multiplier = 173;
    	protected static final int modulus = 1000000007;
    	
    	//multPower[i] stores (multiplier to the power of i) mod modulus, used for calculating the hash code
    	protected static int[] multPower;
    	
    	//charProducts[i] stores ((multiplier to the power of (level - 1)) * char with value i) mod modulus
    	//Used for getting rid of first character in the rolling hash
    	protected static int[] charProducts;
    	
    	//The index where the seed is located in the input and a reference to the writer object
    	protected int index;
    	protected RandomWriter writer;
    	
    	
    	public SeedWrapper(int index, RandomWriter writer) {
    		this.index = index;
    		this.writer = writer;
    	}
    	
    	
    	//Do all the math precomputations necessary for hash code calculations
    	public static void initPowers(int maxPower) {
    		
    		
    		//No need to calculate hashes if the seed is length zero, or nonexistent
    		if (maxPower < 0) return;
    		
    		
    		//Calculate the multPower array as described above
    		multPower = new int[maxPower + 1];
    		multPower[0] = 1;
    		for (int i = 1; i <= maxPower; i++) {
    			long tempMult = (long) (multPower[i - 1]) * multiplier;
    			multPower[i] = (int) (tempMult % modulus);
    		}
    		
    		
    		//Calculate the charProducts array as described above, large enough for any Unicode character
    		charProducts = new int[112065];
    		for (int i = 1; i < charProducts.length; i++) {
    			charProducts[i] = (charProducts[i - 1] + multPower[maxPower]) % modulus;
    		}
    		
    	}
    	
    	
    	//Override the equals method so HashMap does equality checks correctly; i.e., check the strings are equal, not the indices
    	public boolean equals(Object obj) {
    		
    		
    		//Check and cast obj to SeedWrapper
    		if (obj instanceof SeedWrapper) {
    			
    			//Compare the characters for both seeds one by one to check if the seeds are equal or not
    			SeedWrapper otherSeed = (SeedWrapper) obj;
    			for (int i = 0; i < writer.level; i++) {
    				
    				//The index of a seed is where the first char of the seed is, so the seed is located from index to index + level - 1
        			char char1 = writer.inputText.charAt(index + i);
        			char char2 = otherSeed.writer.inputText.charAt(otherSeed.index + i);
        			if (char1 != char2) return false;
        			
        		}
        		return true;
        		
    		}
			return false;
			
    	}
    	
    	
    	//Override the hashCode method so HashMap uses this rolling hash function to calculate hashes much more efficiently
    	public int hashCode() {    
    		
    		//If the level of analysis is zero, then the only possible seed is the empty string, so all seeds have the same hash
    		if (writer.level == 0) return 0;
    		
    		//If this is the first hash being computed, then we need to compute the entire hash
    		if (writer.hashCodes.size() == 0) {
    			
    			int hash = 0;
    			
    			//Hash formula: str[0] * multiplier ^ (level - 1) + str[1] * multiplier ^ (level - 2) + ... + str[level - 1] * multiplier ^ 0
    			for (int i = 0; i < writer.level; i++) {
    				long tempProduct = (long) (multPower[writer.level - i - 1]) * writer.inputText.charAt(i);
    				hash = (hash + (int) (tempProduct % modulus)) % modulus;
    			}
    			return hash;
    			
    		} else if (writer.hashCodes.size() > index) {
    			
    			//If the hash has already been calculated, no need to calculate it again
    			return writer.hashCodes.get(index);
    		}
    		
    		
    		//If the hash hasn't yet been computed, but it isn't the first, then we can calculate it quickly from previous seed's hash
    		//The previous seed is the seed that starts at one index prior to the current one
    		
    		//Get the previous seed's hash
    		int hash = writer.hashCodes.get(index - 1);
    		
    		//Remove the first character of the previous seed
    		hash = (hash + modulus - charProducts[writer.inputText.charAt(index - 1)]) % modulus;
    		
    		//Multiply the whole hash by multiplier; i.e. effectively shifting all chars left by one
    		long tempHash = (long) (hash) * multiplier;
    		hash = (int) (tempHash % modulus);
    		
    		//Add the last character of the current seed
    		hash += writer.inputText.charAt(index + writer.level - 1);
    		
    		return hash;
    		
    	}
    	
    }
    
    
    //Helper class to have a data type that stores a pair of values
    protected class NextSeed {
    	
    	
    	//The character following the seed, and the index of the start of the next seed
    	protected char nextChar;
    	protected int nextIndex;
    	
    	
    	public NextSeed(char nextChar, int nextIndex) {
    		this.nextChar = nextChar;
    		this.nextIndex = nextIndex;
    	}
    	
    	
    	public char getChar() {
    		return nextChar;
    	}
    	
    	
    	public int getIndex() {
    		return nextIndex;
    	}
    	
    }
    
}
