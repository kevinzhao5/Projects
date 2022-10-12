/**
 * 
 */
package assignment;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class RandomWriterTest {
	
	
	RandomWriter writer;
	File errorFile;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		//Reset the System.err stream
		System.setErr(System.err);
		
	}

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		setUpErrorStream();
	}

	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		deleteErrorStream();
	}
	
	
	//Set up the error file
	public void setUpErrorStream() {
		
		//Set the System.err stream to a file so that error messages can be checked
		errorFile = new File("./test_files/error_stream.txt");
		if (errorFile.exists()) {
			errorFile.delete();
		}
		try {
			errorFile.createNewFile();
			PrintStream errorStream = new PrintStream(errorFile);
			System.setErr(errorStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//Delete the error file
	public void deleteErrorStream() {
		errorFile.delete();
	}
	
	
	//Reset the error file by deleting and recreating
	public void resetErrorStream() {
		deleteErrorStream();
		setUpErrorStream();
	}
	
	
	//Read the text inside a file and return as a string
	public String readFile(String path) {
		
		//Read all lines in the file and store as a string
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String str = "";
			while (in.ready()) {
				str += in.readLine();
				if (in.ready()) str += "\n";
			}
			in.close();
			return str;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "Could not read file";
		
	}

	
	//Helper method for generic black box tests on the RandomWriter main method
	public void runMain(String[] args, String expectedError, String errorMessage) {
		RandomWriter.main(args);
		String actualError = readFile(errorFile.getPath());
		assertEquals(errorMessage, expectedError, actualError);
		resetErrorStream();
	}
	
	
	//Helper method for creating a RandomWriter object
	public RandomWriter createRandomWriter(int level) {
		TextProcessor processor = RandomWriter.createProcessor(level);
		if (processor instanceof RandomWriter) {
			RandomWriter writer = (RandomWriter) processor;
			return writer;
		}
		return null;
	}
	
	
	@Test
	//Test that correct command line arguments give no error
	public void testCorrectArgs() {
		
		String[] args3 = { "./test_files/basicInput.txt", "./test_files/basicOutput.txt", "10", "1000" };
		runMain(args3, "", "Doesn't recognize correct path");
		
	}
	
	
	@Test
	//Test that null command line arguments are properly dealt with
	public void testNullArguments() {
		
		//Test a null array
		runMain(null, "Command line arguments are null!", "Doesn't properly deal with null command line argument");
		
		//Test an array with a null value at each individual index
		for (int i = 0; i < 4; i++) {
			
			//Create the specified array
			String[] args = new String[4];
			Arrays.fill(args, "");
			args[i] = null;
			
			//Run the test
			runMain(args, "Argument " + i + " is null! Please fix.", "Doesn't properly deal with null arg at index " + i);
			
		}
		
	}
	
	
	@Test
	//Test that improper length command line arguments are dealt with
	public void testArgsLength() {
		
		for (int i = 0; i < 10; i++) {
			
			if (i == 4) continue;
			
			String[] args = new String[i];
			runMain(args, "Incorrect number of command line arguments!", "Doesn't properly deal with args of length " + i);
			
		}
		
	}
	
	
	@Test
	//Test that bad input files are dealt with
	public void testInputFileValidation() {
		
		//Test for nonexistent input file
		String[] args1 = { "./test_files/file_does_not_exist.txt", "./test_files/basicOutput.txt", "10", "1000" };
		runMain(args1, "Input file is not readable!", "Doesn't properly deal with nonexistent input file");
		
		//Test for nonsense path
		String[] args2 = { "sdkfjhiewrouw34h308229312", "./test_files/basicOutput.txt", "10", "1000" };
		runMain(args2, "Input file is not readable!", "Doesn't properly deal with nonsense path");
				
	}
	
	
	@Test
	//Test that bad output files are dealt with
	public void testOutputFileValidation() {
		
		//Test for creation of nonexistent output file
		String testFilePath = "./test_files/file_does_not_exist.txt";
		File testFile = new File(testFilePath);
		if (testFile.exists()) testFile.delete();
		String[] args1 = { "./test_files/basicInput.txt", testFilePath, "10", "1000" };
		runMain(args1, "", "Can't properly create the output file");
		assertEquals("Output file was not created", true, testFile.exists());
		if (testFile.exists()) testFile.delete();
		
		//Test for nonsense path being caught
		String[] args2 = { "./test_files/basicInput.txt", "/thisfolderdoesntexist/anotherone/fuhi2493oiejwndksx,daao8", "10", "1000" };
		runMain(args2, "Could not create the output file!", "Doesn't properly deal with nonsense path");
		
		//Test for read only file
		String[] args3 = { "./test_files/basicInput.txt", "./test_files/restrictedOutput.txt", "10", "1000" };
		runMain(args3, "Output file is not writable!", "Doesn't properly deal with no write file");
				
	}
	
	
	@Test
	//Test that the level of analysis is validated correctly
	public void testLevelOfAnalysisValidation() {
		
		//Test for improper int format
		String[] args1 = { "./test_files/basicInput.txt", "./test_files/basicOutput.txt", "hello", "1000" };
		runMain(args1, "Not a valid int format argument!", "Doesn't properly deal with improper ints for analysis level");

		String[] args2 = { "./test_files/basicInput.txt", "./test_files/basicOutput.txt", "0192380128379182739182372837", "1000" };
		runMain(args2, "Not a valid int format argument!", "Doesn't properly deal with improper ints for analysis level");
		
		
		//Test for negative level of analysis
		String[] args3 = { "./test_files/basicInput.txt", "./test_files/basicOutput.txt", "-1", "1000" };
		runMain(args3, "The level of analysis should not be negative!", "Doesn't properly deal with negative analysis level");
		
		//Test for zero level of analysis
		String[] args4 = { "./test_files/basicInput.txt", "./test_files/basicOutput.txt", "0", "1000" };
		runMain(args4, "", "Doesn't properly deal with zero analysis level");
		
		//Test for level of analysis larger than input file length
		String[] args5 = { "./test_files/basicInput.txt", "./test_files/basicOutput.txt", "100000", "1000" };
		runMain(args5, "The level of analysis is too large!", "Doesn't properly deal with large analysis level");
		
	}
	
	
	@Test
	//Test that the output length is validated correctly
	public void testOutputLengthValidation() {
		
		//Test for improper int format
		String[] args1 = { "./test_files/basicInput.txt", "./test_files/basicOutput.txt", "10", "hello" };
		runMain(args1, "Not a valid int format argument!", "Doesn't properly deal with improper ints for output length");
		
		String[] args2 = { "./test_files/basicInput.txt", "./test_files/basicOutput.txt", "10", "0192380128379182739182372837" };
		runMain(args2, "Not a valid int format argument!", "Doesn't properly deal with improper ints for output length");
		
		
		//Test for negative output length
		String[] args3 = { "./test_files/basicInput.txt", "./test_files/basicOutput.txt", "10", "-1" };
		runMain(args3, "The output length should not be negative!", "Doesn't properly deal with negative output length");
		
		
	}
	
	
	@Test
	//Test that the math precomputation function works as intended
	public void testSeedWrapperPower() {
		
		//Test powers of the multiplier are correct
		int power = 5;
		RandomWriter.SeedWrapper.initPowers(power);
		int[] multPower = { 1, 173, 29929, 5177717, 895745041, 963891015 };
		for (int i = 0; i <= power; i++) {
			assertEquals("Powers not calculated correctly!", multPower[i], RandomWriter.SeedWrapper.multPower[i]);
		}
		
		//Test char value products are correct
		int[] partialCharProducts = new int[130];
		partialCharProducts[97] = 497427804;
		assertEquals("Char products not calculated correctly!", partialCharProducts[97], RandomWriter.SeedWrapper.charProducts[97]);
		partialCharProducts[98] = 461318812;
		assertEquals("Char products not calculated correctly!", partialCharProducts[98], RandomWriter.SeedWrapper.charProducts[98]);
		
	}
	
	
	@Test
	//Test that the equality function for the seed wrapper works as intended
	public void testSeedWrapperEquality() throws IOException {
		
		int level = 3;
		RandomWriter writer = createRandomWriter(level);
		writer.readText("./test_files/repeatedWords.txt");
		RandomWriter.SeedWrapper word1 = new RandomWriter.SeedWrapper(0, writer); //word = in0
		RandomWriter.SeedWrapper word2 = new RandomWriter.SeedWrapper(4, writer); //word = an0
		RandomWriter.SeedWrapper word3 = new RandomWriter.SeedWrapper(8, writer); //word = in0
		RandomWriter.SeedWrapper word4 = new RandomWriter.SeedWrapper(12, writer); //word = an1
		
		assertEquals("Equality check is incorect", true, word1.equals(word3));
		assertEquals("Equality check is incorect", false, word1.equals(word2));
		assertEquals("Equality check is incorect", false, word2.equals(word4));
		assertEquals("Equality check is incorect", false, word3.equals(word4));
		
	}
	
	
	@Test
	//Test that the hash code function for the seed wrapper works as intended for normal input
	public void testHashCodeNormal() throws IOException {
		
		int level = 3;
		RandomWriter writer = createRandomWriter(level);
		writer.readText("./test_files/repeatedWords.txt");
		RandomWriter.SeedWrapper word1 = new RandomWriter.SeedWrapper(0, writer); //word = in0
		RandomWriter.SeedWrapper word2 = new RandomWriter.SeedWrapper(1, writer); //word = n0 
		RandomWriter.SeedWrapper word3 = new RandomWriter.SeedWrapper(2, writer); //word = 0 a
		
		assertEquals("Normal hash code is incorect", 3161623, word1.hashCode());
		assertEquals("Normal hash code is incorect", 3300526, word2.hashCode());
		assertEquals("Normal hash code is incorect", 1442225, word3.hashCode());

		
	}
	
	
	@Test
	//Test that the hash code function for the seed wrapper works as intended for low input
	public void testHashCodeLow() throws IOException {
		
		int level = 1;
		RandomWriter writer = createRandomWriter(level);
		writer.readText("./test_files/repeatedWords.txt");
		RandomWriter.SeedWrapper word1 = new RandomWriter.SeedWrapper(0, writer); //word = i
		RandomWriter.SeedWrapper word2 = new RandomWriter.SeedWrapper(1, writer); //word = n 
		RandomWriter.SeedWrapper word3 = new RandomWriter.SeedWrapper(2, writer); //word = 0
		
		assertEquals("Low hash code is incorect", 105, word1.hashCode());
		assertEquals("Low hash code is incorect", 110, word2.hashCode());
		assertEquals("Low hash code is incorect", 48, word3.hashCode());

	}
	
	
	//Helper method for running a generic black box test with output
	public void assertOutput(String args[], File outputFile, String expectedOutput) {
		
		runMain(args, "", outputFile.getName() + " should not produce error");
		String output = readFile(args[1]);
		assertEquals(outputFile.getName() + " produces incorrect output", expectedOutput, output);
		if (outputFile.exists()) outputFile.delete();

	}
	
	
	@Test
	//Black box test on file with only 'a' chars
	public void testAllSameChar() {
		
		String name = "allA";
		File outputFile = new File("./test_files/" + name + "Output.txt");
		String[] args = { "./test_files/" + name + ".txt", outputFile.getPath(), "2", "10" };
		assertOutput(args, outputFile, "aaaaaaaaaa");

	}

	@Test
	//Black box test on file with only 'a' chars and analysis level 0
	public void testAllSameCharWithZero() {
		
		String name = "allA";
		File outputFile = new File("./test_files/" + name + "Output.txt");
		String[] args = { "./test_files/" + name + ".txt", outputFile.getPath(), "0", "10" };
		assertOutput(args, outputFile, "aaaaaaaaaa");

	}
	

	@Test
	//Black box test on file with only 'a' chars and analysis level = length - 1
	public void testHighAnalysisLevel() {
		
		String name = "allA";
		File outputFile = new File("./test_files/" + name + "Output.txt");
		String[] args = { "./test_files/" + name + ".txt", outputFile.getPath(), "9", "10" };
		assertOutput(args, outputFile, "aaaaaaaaaa");

	}
	
	
	@Test
	//Black box test on file with lower case alphabet and level of analysis zero
	public void testZeroLevelLowAlphabet() {
		
		String name = "alphabet";
		File outputFile = new File("./test_files/" + name + "Output.txt");
		String[] args = { "./test_files/" + name + ".txt", outputFile.getPath(), "0", "1000000" };
		runMain(args, "", outputFile.getName() + " should not produce error");
		String output = readFile(args[1]);
		
		//Make sure the characters are relatively evenly randomly distributed
		int[] counts = new int[26];
		for (int i = 0; i < output.length(); i++) {
			counts[output.charAt(i) - 'a']++;
		}
		for (int i = 0; i < 26; i++) {
			assertEquals("Char " + (char) (i + 'a') + " is not evenly distributed", 1.0 / 26.0, counts[i] * 1.0 / output.length(), 0.01);
		}
		
		if (outputFile.exists()) outputFile.delete();

	}
	
	
	@Test
	//Black box test on file with upper case alphabet and level of analysis zero
	public void testZeroLevelHighAlphabet() {
		
		String name = "capitalAlphabet";
		File outputFile = new File("./test_files/" + name + "Output.txt");
		String[] args = { "./test_files/" + name + ".txt", outputFile.getPath(), "0", "1000000" };
		runMain(args, "", outputFile.getName() + " should not produce error");
		String output = readFile(args[1]);
		
		//Make sure the characters are relatively evenly randomly distributed
		int[] counts = new int[26];
		for (int i = 0; i < output.length(); i++) {
			counts[output.charAt(i) - 'A']++;
		}
		for (int i = 0; i < 26; i++) {
			assertEquals("Char " + (char) (i + 'A') + " is not evenly distributed", 1.0 / 26.0, counts[i] * 1.0 / output.length(), 0.01);
		}
		
		if (outputFile.exists()) outputFile.delete();

	}
	
	
	@Test
	//Black box test on file with numbers and level of analysis zero
	public void testZeroLevelNumbers() {
		
		String name = "numbers";
		File outputFile = new File("./test_files/" + name + "Output.txt");
		String[] args = { "./test_files/" + name + ".txt", outputFile.getPath(), "0", "1000000" };
		runMain(args, "", outputFile.getName() + " should not produce error");
		String output = readFile(args[1]);
		
		//Make sure the numbers are relatively evenly randomly distributed
		int[] counts = new int[10];
		for (int i = 0; i < output.length(); i++) {
			counts[output.charAt(i) - '0']++;
		}
		for (int i = 0; i < 10; i++) {
			assertEquals("Char " + (char) (i + '0') + " is not evenly distributed", 1.0 / 10.0, counts[i] * 1.0 / output.length(), 0.01);
		}
		
		if (outputFile.exists()) outputFile.delete();

	}
	
	
	@Test
	//Black box test on file with repeated strings and numbers following them, validate next seed functionality
	public void testRepeatedNumbers() {
		
		String name = "repeatedNumbers";
		File outputFile = new File("./test_files/" + name + "Output.txt");
		String[] args = { "./test_files/" + name + ".txt", outputFile.getPath(), "4", "1000000" };
		runMain(args, "", outputFile.getName() + " should not produce error");
		String output = readFile(args[1]);
		
		//Make sure the numbers are relatively evenly randomly distributed
		int[] counts = new int[3];
		int totalNumbers = 0;
		for (int i = 0; i < output.length(); i++) {
			char c = output.charAt(i);
			if (c >= '1' && c <= '3') {
				counts[c - '1']++;
				totalNumbers++;
			}
		}
		for (int i = 0; i < 3; i++) {
			assertEquals("Char " + (char) (i + '1') + " is not evenly distributed", 1.0 / 3.0, counts[i] * 1.0 / totalNumbers, 0.05);
		}
		
		if (outputFile.exists()) outputFile.delete();

	}
	
	
	@Test
	//Black box test on length of output with numbers
	public void testOutputLength() {
		
		String name = "numbers";
		File outputFile = new File("./test_files/" + name + "Output.txt");
		String[] args = { "./test_files/" + name + ".txt", outputFile.getPath(), "2", "1000000" };
		runMain(args, "", outputFile.getName() + " should not produce error");
		String output = readFile(args[1]);
		assertEquals("Output length is not correct for testOutputLength", 1000000, output.length());
		if (outputFile.exists()) outputFile.delete();

	}
	
	
	@Test
	//Black box test on length of output when specification is zero
	public void testOutputLengthZero() {
		
		String name = "numbers";
		File outputFile = new File("./test_files/" + name + "Output.txt");
		String[] args = { "./test_files/" + name + ".txt", outputFile.getPath(), "2", "0" };
		runMain(args, "", outputFile.getName() + " should not produce error");
		String output = readFile(args[1]);
		assertEquals("Output length is not correct for testOutputLengthZero", 0, output.length());
		if (outputFile.exists()) outputFile.delete();

	}
	
	
	@Test
	//Black box test on length of output when specification is badly formatted
	public void testOutputLengthBad() {
		
		String name = "badFormat";
		File outputFile = new File("./test_files/" + name + "Output.txt");
		String[] args = { "./test_files/" + name + ".txt", outputFile.getPath(), "2", "100" };
		runMain(args, "", outputFile.getName() + " should not produce error");
		String output = readFile(args[1]);
		assertEquals("Output length is not correct for testOutputLengthBad", 100, output.length());
		if (outputFile.exists()) outputFile.delete();

	}

	
}
