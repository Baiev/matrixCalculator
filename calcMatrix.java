import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Tito Livio
 * @Id: 2018253
 */
public class calcMatrix {
	// This class will ask the user to choose a file with matrices inside of integer
	// numbers and operation for later showing a
	// result of operation with matrices;
	public calcMatrix() throws Exception {
		// Those 4 lines are just the header to explain to the users how the program
		// will work
		System.out.println("Operations with matrices from txt file of INTEGER NUMBERS");
		System.out.println(
				"It will be showed on screen two menus one for Choosing a File and another one for choosing a operation");
		System.out.println(
				"The menus will always show you a number on left, just type the number to choose the desired option");
		// Show a menu on screen where will offer some options of files and keep in the
		// variable "fileMenu"
		String fileName = chooseFile(
				"Choose a matrix file, the file should have 2 matrices of integer numbers in :\n1.Matrix.txt\n2.Matrix2.txt\n3.Type a name file:\n4.InvalidFile.txt\n5.InvalidFile2.txt\n6.Exit  ");
		// This method check if the file chosen is a valid file for: type of character,
		// @, two matrices
		checkDataFile(fileName);
		// declare a integer array variable Calling the method and storing
		int[][] matrixA = getMatrixA(fileName);
		int[][] matrixB = getMatrixB(fileName);
		// Show the matrices on screen from the variable "matrixA" and "matrixB";
		showMatrices("Matrix A", matrixA);
		showMatrices("Matrix B", matrixB);
		System.out.println();
		// Show on screen a menu where will offer some options of operations and keep
		// the options in the variable to use in the operations chosen
		menuMatrix("Choose a kind of operation: ", matrixA, matrixB);
		// This method is only to offer to the user the option to quit the program;
		exitProgram();
	}

	// This menu will show on screen to the user some options of operations
	private void menuMatrix(String prompt, int[][] matrixA, int[][] matrixB) throws Exception {
		// Start scanner to read form keyboard
		Scanner myScan1 = new Scanner(System.in);
		// Show the operation
		System.out.println(prompt);
		// Print the menu on screen
		System.out.println(
				"1.Sum\n2.Subtration\n3.Multiply\n4.Multiplication Scalar\n5.Inverse of 2x2 matrix\n6.Division Scalar\n7.Exit");
		// Read the user input and store in answer variable
		String answer = myScan1.next();
		// Check what is the user answer and take a decision, then Calling the
		// corresponding
		// method with their variables
		if (answer.equals("1")) {
			// Calling the addition method
			sumMatrices(matrixA, matrixB);
		} else if (answer.equals("2")) {
			// Calling the subtraction method
			subMatrices(matrixA, matrixB);
		} else if (answer.equals("3")) {
			// Calling the multiplication method
			mutiplyMatrices(matrixA, matrixB);
		} else if (answer.equals("4")) {
			// Calling the scalar multiplication twice with different matrix
			scalarMultiplyMatrices("Matrix A", matrixA);
			scalarMultiplyMatrices("Matrix B", matrixB);
		} else if (answer.equals("5")) {
			// Calling the calculation of inverse of the matrices
			inverseMatrices(matrixA, matrixB);
		} else if (answer.equals("6")) {
			// Calling the scalar division
			scalarDivideMatrices("Matrix A", matrixA);
			scalarDivideMatrices("Matrix B", matrixB);
		} else if (answer.equals("7")) {
			// Calling the method to quit the program if the user wants
			exitProgram();
		} else {
			// Show on screen for typing the valid options
			System.out.println("Please only type 1, 2, 3, 4, 5, 6, 7");
			// If the user type anything different what was offered the on screen the
			// program will Calling the menu again
			menuMatrix(answer, matrixB, matrixB);
		}
	}

	// This method calculate the scalar division
	public double[][] scalarDivideMatrices(String matrixName, int[][] matrix) throws IOException {
		// variable declaration Calling the scalar number through the method checkScalar
		// number
		double number = checkScalarNumber("Please type a integer number to make Scalar Division  of " + matrixName);
		// initializing the variable
		double invertedNumber = 0.0;
		// just inverting the variable for multiplying instead a make a division
		// directly
		invertedNumber = Math.pow(number, -1);
		// create a array 2d to store the value of scalar division in double format
		// based in a matrix from the file
		double[][] newMatrix = new double[matrix.length][matrix[0].length];
		// start loopFor to convert item by item
		for (int i = 0; i < newMatrix.length; i++) {
			for (int j = 0; j < newMatrix[i].length; j++) {
				// store the converted numbers in a new matrix
				newMatrix[i][j] = (double) matrix[i][j];
			}
		}
		// Start LoopFor to make a multiplication of the matrix by inverted number
		for (int i = 0; i < newMatrix.length; i++) {
			for (int j = 0; j < newMatrix[i].length; j++) {
				// making a multiplication item by item and rounding in the same time for three
				// decimal places
				newMatrix[i][j] = Math.round((newMatrix[i][j] * invertedNumber) * 1000) / 1000D;
			}
		}
		// Calling the method saveDoubleMatrix to save in a double format

		// Calling the method showMatrices Just print on screen the original matrices
		// from
		// file
		// Show the original matrix on screen before show the result
		showMatrices(matrixName, matrix);
		System.out.println();
		// the next five lines just print on screen the result of scalar division
		System.out.println("The Scalar Division of " + matrixName + " by " + number + " is:");
		for (int i = 0; i < newMatrix.length; i++) {
			for (int j = 0; j < newMatrix[i].length; j++) {
				System.out.print(newMatrix[i][j] + "\t");
			}
			System.out.println();
		}
		// Just for the user be aware that the numbers in the matrix were rounded in
		// three decimal places
		System.out.println("Please, note that the numbers were rounded in three decimals places");
		System.out.println();
		// Calling the method saveDoubleMatrix to save the result in file
		saveDoubleMatrix(newMatrix);
		return null;
	}

	// This method make a scalar multiplication of matrix
	public int[][] scalarMultiplyMatrices(String matrixName, int[][] matrix) {
		// Create a integer variable Calling a method to check if the number is a
		// integer
		int number = checkScalarNumber("Please type a integer number to make scalar multiplication of " + matrixName);
		// Show the number on screen
		System.out.println(number);
		// LoopFor to make a multiplication item by item
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				// Operation of multiplication
				matrix[i][j] = matrix[i][j] * number;
			}
		}
		// Show on the screen a heading of the result on screen
		System.out.println("The Scalar multiplication of " + matrixName + " is:");
		// Calling the method to show on screen the result of scalar multiplication of
		// the matrix
		showMatrices("", matrix);
		// Show on screen a space between the lines
		System.out.println();
		return matrix;
	}

	// Declaration of method for doing the inverse of matrices
	public int[][] inverseMatrices(int[][] matrixA, int[][] matrixB) throws IOException {
		// Check if the matrices has rank 2x2
		if (matrixA.length != 2 && matrixB.length != 2 && matrixA[0].length != 2 && matrixB[0].length != 2) {
			// Show on screen to the user that the matrix is not 2x2
			System.out.println("This program is only prepared to make inverse of 2X2 matrix");
			// if the rank of matrix is 2x2 then start the calculation
		} else {
			// Declare the variable to store the determinant of matrix A
			int determinantA;
			// Declare the matrix to store the matrix resulting
			double[][] adjA = new double[matrixA.length][matrixA.length];
			// Calculate the determinant
			determinantA = (matrixA[0][0] * matrixA[1][1]) - (matrixA[1][0] * matrixA[0][1]);
			// Check if the determinant is zero
			if (determinantA == 0) {
				// Show on screen that It is not possible to the inverse of matrix
				System.out.println("The determinant of Matrix A is zero, mathematicly is not possible ");
				// If the determinant is not zero, start doing the inverse
			} else {
				// Starting to make a Adjoint of matrix
				adjA[0][0] = (matrixA[1][1]) * (+1);
				adjA[0][1] = (matrixA[0][1]) * (-1);
				adjA[1][0] = (matrixA[1][0]) * (-1);
				adjA[1][1] = (matrixA[0][0]) * (+1);
				// Invert the determinant to multiply by the matrix after adjoint
				double detA = (Math.pow(determinantA, -1));
				// LoopFor to make a multiplication of determinant by matrix
				for (int i = 0; i < adjA.length; i++) {
					for (int j = 0; j < adjA[i].length; j++) {
						adjA[i][j] = adjA[i][j] * detA;
					}
				}
				// Show on screen the matrix A first of all
				showMatrices("Matrix A", matrixA);
				// Show on screen the determinant
				System.out.println("The determinant of matrix A: " + detA);
				// Show on screen the heading
				System.out.println("The inverse of matrix A is: ");
				// LoopFor to rounding the items of resulting matrix
				for (int i = 0; i < adjA.length; i++) {
					for (int j = 0; j < adjA[i].length; j++) {
						// rounding three decimal places
						adjA[i][j] = Math.round((adjA[i][j]) * 1000) / 1000D;
						System.out.print(adjA[i][j] + "\t");
					}
					System.out.println();
				}

			}
			// ------------------------------------------------------------------------------
			// Declare the variable to calculate a determinant
			int determinantB;
			// Calculate a determinant of matrixB
			determinantB = (matrixB[0][0] * matrixB[1][1]) - (matrixB[1][0] * matrixB[0][1]);
			// Declare a double matrix to store a result o inverse of matrix B
			double[][] adjB = new double[matrixB.length][matrixB.length];
			// Starting to make a Adjoint of matrix
			adjB[0][0] = (matrixB[1][1]) * (+1);
			adjB[0][1] = (matrixB[0][1]) * (-1);
			adjB[1][0] = (matrixB[1][0]) * (-1);
			adjB[1][1] = (matrixB[0][0]) * (+1);
			// Inverting a determinant of matrix B
			double detB = (Math.pow(determinantB, -1));
			// LoopFor to Multiplying the content of matrix by the inverted determinant
			for (int i = 0; i < adjB.length; i++) {
				for (int j = 0; j < adjB[i].length; j++) {
					adjB[i][j] = adjB[i][j] * detB;
				}
			}
			// Show the matrix B on screen
			showMatrices("Matrix B", matrixB);
			// Show on the screen the value of inverted determinant
			System.out.println("The determinant of matrix B: " + detB);
			// Show a heading of the result of calculation of Matrix B
			System.out.println("The inverse of matrix B is: ");
			// LoopFor to show on screen the result of inverse of Matrix B
			for (int i = 0; i < adjB.length; i++) {
				for (int j = 0; j < adjB[i].length; j++) {
					adjB[i][j] = Math.round((adjB[i][j]) * 1000) / 1000D;
					System.out.print(adjB[i][j] + "\t");
				}
				System.out.println();
			}
			// Just space for separating the result
			System.out.println();
			// Ask to the users where they want to save the result of matrix A
			System.out.println("Choose a place to save a inverse of Matrix A first: ");
			// Call the method to save the result of inverse of matrix A
			saveDoubleMatrix(adjA);
			// Say to the users where they want to save the result of matrix B
			System.out.println("Choose a place to save a inverse of Matrix B: ");
			// Call the method to save the result of inverse of matrix B
			saveDoubleMatrix(adjB);
		}
		return null;
	}

	// Method to calculate a subtraction of matrices
	public int[][] subMatrices(int[][] matrixA, int[][] matrixB) throws IOException {
		// Declaration of array to store the matrix result of subtraction
		int[][] sub = new int[matrixA.length][matrixB.length];
		// Check if the two matrices has the same rank
		if (matrixA.length != matrixB[0].length) {
			// Show on the screen that is not possible
			System.out.println("The rank of matrices do not match, mathematically, it is not possible the subtration");
			// Call the method quit program
			exitProgram();
			// If the matrix rank is fine, the program continue for the next step
		} else {
			// LoopFor to make a subtraction of matrices
			for (int i = 0; i < matrixA.length; i++) {
				for (int j = 0; j < matrixB[0].length; j++) {
					sub[i][j] = (matrixA[i][j]) - (matrixB[i][j]);
				}
			}
		}
		System.out.println();
		// Show on screen a heading of the result
		showMatrices("The subtration of (MatrixA - MatrixB) is: ", sub);
		// Show on screen a line separator
		System.out.println();
		// Call the specific method who save only integer matrices
		saveIntegerMatrix(sub);
		return sub;
	}

	// Method to make a Addition of matrices
	public int[][] sumMatrices(int[][] matrixA, int[][] matrixB) throws IOException {
		// Declare a matrix to store the result of addition of matrices
		int[][] sum = new int[matrixA.length][matrixB.length];
		// Check if the matrices have the same rank
		if (matrixA.length != matrixB[0].length) {
			// If the matrices have a different rank, show on screen that is not possible to
			// make a addition
			System.out.println("The sizes of matrices do not match");
			// Call the method to quit the program
			exitProgram();
			// If the rank of matrices are fine, start to make the calculation
		} else {
			// LoopFor to make a addition of matrices
			for (int i = 0; i < matrixA.length; i++) {
				for (int j = 0; j < matrixB[0].length; j++) {
					sum[i][j] = (matrixA[i][j]) + (matrixB[i][j]);
				}
			}
		}
		// Show on screen a heading of the addition of matrices
		showMatrices("The sum of (MatrixA + MatrixB) is: ", sum);
		// Show on screen a line separator
		System.out.println();
		// Call the method to save the result
		saveIntegerMatrix(sum);
		return sum;
	}

	// Method make a multiplication of matrices
	public int[][] mutiplyMatrices(int[][] matrixA, int[][] matrixB) throws IOException {
		// Declare a array to store the matrix result of multiplication
		int[][] product = new int[matrixA.length][matrixB.length];
		// Check if column rank of matrix A is equal to the row rank of matrix B
		if (matrixA[0].length != matrixB.length) {
			// If it is not equal, show on screen a message and stop the calculation
			System.out.println("The sizes of matrices do not match");
			// Call the method to quit the program
			exitProgram();
			// If the matrices are OK, then continue to making a multiplication
		} else {
			// Start to make a multiplication. Three LoopFor are necessary
			// j to scroll the rows
			for (int i = 0; i < matrixA.length; i++) {
				// j to scroll the columns
				for (int j = 0; j < matrixB[0].length; j++) {
					product[i][j] = 0;
					// k to jump among the positions in the matrixA and positions in Matrix B to
					// make a multiplication rows by columns
					for (int k = 0; k < matrixA[0].length; k++) {
						product[i][j] = product[i][j] + matrixA[i][k] * matrixB[k][j];
					}
				}
			}
			// Call the method twice to Show the two originals matrices on screen before the
			// result
			showMatrices("Matrix A: ", matrixA);
			showMatrices("Matrix A: ", matrixB);
			System.out.println();
			// Call the method to show the result on screen
			showMatrices("The product of multiplication of (Matrix A x Matrix B) is: ", product);
		}
		// Call the method to save the results in file
		saveIntegerMatrix(product);
		return product;
	}

	// Method to get the second matrix from the file
	public int[][] getMatrixB(String fileName) {
		// Initializing variables on top to have access inside the functions below
		Scanner myScan = null;
		String line = "";
		// Try/catch to check if there any problems in file
		try {
			// Set the variable myScan to get content in the file
			myScan = new Scanner(new FileReader(fileName));
			// if something goes wrong, the Catch says or does something.
		} catch (FileNotFoundException e1) {
			// Show a message on screen
			System.out.println("File not found !!");
			// Call the method to quit the program
			exitProgram();
		}
		// ********************************************************************************
		// The function of this block is to jump the first matrix and @ to start to read
		// the second matrix
		// Read the first line of file to know the indexes of line
		line = myScan.nextLine();
		// Split to know the quantity of rows
		String[] firstSplit = line.split(" ");
		// Store the first part of string split to know how many rows the program should
		// be jumped
		int firstLine = Integer.parseInt(firstSplit[0]);
		// LoopFor to scroll through the lines to the @
		for (int i = 0; i < firstLine + 2; i++) {
			line = myScan.nextLine();
		}
		// ********************************************************************************
		// Declare a variable to store a line split of line
		String[] indexSplitB = line.split(" ");
		// store the first index of line split in a variable to use as a rank of matrix
		int nRowsB = Integer.parseInt(indexSplitB[0]);
		// store the second index of line split in a variable to use as a rank of matrix
		int nColsB = Integer.parseInt(indexSplitB[1]);
		// Declare a array variable using the values above as rank
		int[][] arrayB = new int[nRowsB][nColsB];
		// double LoopFor to read a array line by line and store
		for (int i = 0; i < arrayB.length; i++) {
			// Store the lines in a variable
			line = myScan.nextLine();
			// Split and store the lines in a variable of type array
			String[] lineSplitB = line.split(" ");
			// Comparing if the numbers of items of the split variable match with the
			// variable of column
			if (lineSplitB.length == nColsB) {
				for (int j = 0; j < arrayB[i].length; j++) {
					// Store the values in array converting in a integer
					arrayB[i][j] = Integer.parseInt(lineSplitB[j]);
				}
				// if the numbers of items of the split variable do not match with the variable
				// of column
			} else {
				// Show on Screen a message saying what is happened
				System.out.println("The number of columms do not match with the columms of matrix");
				// Call the method to quit the Program
				exitProgram();
			}

		}
		return arrayB;
	}

	// Method get the first from file
	public int[][] getMatrixA(String file) {
		// Declare a variable to use when read line by line and manipulate
		String line = "";
		// Declare a variable to use with the function Scanner
		Scanner myScan = null;
		// Try/Catch to check if there is no problem in file
		try {
			// Start the Scanner to read the file
			myScan = new Scanner(new FileReader(file));
			// If something happened while the file is being read, the catch stop and do
			// something
		} catch (FileNotFoundException e) {
			// Show on screen something indicates a problem
			System.out.println("File not found");
			// Call the program to quit the program
			exitProgram();
		}
		// read and keep the value inside the Scanner
		line = myScan.nextLine();
		// Split the line to get a index of matrices
		String[] indexSplitA = line.split(" ");
		// declare the variables to store a values of indexes
		int nRowsA = Integer.parseInt(indexSplitA[0]);
		int nColsA = Integer.parseInt(indexSplitA[1]);
		// Use the indexes in the ranks to declare a array matrix
		int[][] arrayA = new int[nRowsA][nColsA];
		// Try/catch for checking error while the file is reading
		try {
			for (int i = 0; i < arrayA.length; i++) {
				line = myScan.nextLine();
				String[] lineSplitA = line.split(" ");
				if (lineSplitA.length == nColsA) {
					for (int j = 0; j < arrayA[i].length; j++) {
						arrayA[i][j] = Integer.parseInt(lineSplitA[j]);
					}
				} else {
					System.out.println(
							"The index of Matrix A do not match with the number Rows or Columms of matrix in the file");
					exitProgram();
				}
			}
		} catch (Exception e) {
			System.out.println("Matrix A is not valid");
			exitProgram();
		}
		return arrayA;
	}

	// Method to ask a integer number and check if the user input it is a valid
	// number
	private int checkScalarNumber(String prompt) {
		// Declare a variable to return
		int number = 0;
		// Declare a boolean variable just for respond the WhileLoop to stop or to keep
		// going
		boolean valid = false;
		// Declare Scanner to wait the user input
		Scanner myScan = new Scanner(System.in);
		// Show on screen the prompt asking to type a number
		System.out.println(prompt);
		// WhileLoop make check in while the valid variable is false
		while (!valid) {
			try {
				// Store a string already converting at the same time
				number = Integer.parseInt(myScan.nextLine());
				// Respond to the WhileLoop to stop the loop
				valid = true;
				// When the input is not a number the Catch complain asking something
			} catch (Exception e) {
				// Show on screen a warning this is not a number
				System.out.println("This is not a number");
				// Show on screen to the user the prompt again
				System.out.println(prompt);
				// The valid variable is not valid to stop the loop then WhileLoop keep going
				valid = false;
			}
		}
		return number;
	}

	// Method to ask to the user to choose a file to load the matrices on memory
	public String chooseFile(String prompt) {
		// Declare a variable and start a Scanner in the same time
		Scanner myScan3 = new Scanner(System.in);
		// Declare a variable to check what the user chose
		String choice = null;
		// Declare a variable to store name of the file chosen
		String file = null;
		// Declare a boolean to work in the WhileLoop
		boolean valid = false;
		// WhileLoop to repeat the menu on screen while valid variable is false
		while (!valid) {
			// Show on screen a line separator
			System.out.println();
			// Show on Screen a prompt to the user asking to choose a file
			System.out.println(prompt);
			// Store the choice from user input in variable choice
			choice = myScan3.nextLine();
			// Check if the user input is among the options presented
			if (choice.equals("1")) {
				file = "matrix.txt";
				valid = true;
			} else if (choice.equals("2")) {
				file = "matrix2.txt";
				valid = true;
			} else if (choice.equals("3")) {
				// I the user wants change the default name
				file = inputSaveFile("Name of Integer numebers file: " + "(Extension is not necessary)");
				valid = true;
			} else if (choice.equals("4")) {
				file = "invalidFile.txt";
				valid = true;
			} else if (choice.equals("5")) {
				file = "invalidFile2.txt";
				valid = true;
			} else if (choice.equals("6")) {
				// Call the method to quit the program
				exitProgram();
				valid = true;
			} else {
				// When the choices is not matching to the options showed that phrase to the
				// screen
				System.out.println("Only the options 1,2,3,4, and 6 are valid");
				// Say to the WhileLoop was not attempted, the WhileLoop keep going
				valid = false;
			}
		}
		return file;
	}

	// Method to check if there is letter in the file instead numbers and at
	public void checkDataFile(String file) {
		// Initialize the variable Scanner
		Scanner scan = null;
		try {
			// Set a Scanner to read a specific file
			scan = new Scanner(new FileReader(file));
			// catch Action if something goes wrong
		} catch (FileNotFoundException e) {
			// Show on screen amicable phrase
			System.out.println("The file is not found");
			// Call the method to quit the program
			exitProgram();
		}
		// Declare boolean variable
		boolean emptyFile = false;
		// Declare variables to store the numbers from the file
		int content = 0, content1 = 0;
		// Declare variable to counter the numbers
		int countA = 0;
		int countB = 0;
		// Declare variable to store the string before validate as number
		String notNumber = "",
				// Declare variable to store the value the at symbol
				at = null;
		// Declare variable to validate the content of matrixB
		boolean validMatriB = false;
		// WhileLoop to read the file while there is content
		while (scan.hasNext()) {
			// If the @ be found then, the second matrix is checked
			if (scan.hasNext("@")) {
				// Keep the at in variable
				at = scan.next();
				// WhileLoop to finish the rest of the file
				while (scan.hasNext()) {
					// Declare variable to validate and stop the WhileLoop
					boolean valid2 = false;
					// WhileLoop to count and check the numbers
					while (!valid2) {
						// try command to check if the user typed a number
						try {
							// If It is not a number keep the value to show on screen after
							notNumber = scan.next();
							// Store the variable and convert to number in the same time
							content1 = Integer.parseInt(notNumber);
							// Counter Just to know how many times the 2th loop has rotated
							countB++;
							// if it is true, keep going
							valid2 = true;
							// Catch command if the user typed something different than number
						} catch (Exception e) {
							// Show on screen a message and the character typed
							System.out.println(
									"In the Second Matrix, This character is not a valid number: " + notNumber);
							// if it is not a number finish the loop and ask to type again
							valid2 = false;
						}
					}
				}
				// While the @ is not found that loop running, it means the number for the first
				// matrix
			} else {
				// boolean variable to validate the WhileLoop(Stop or keep going)
				boolean valid1 = false;
				// WHileLoop to check the numbers in the first matrix
				while (!valid1) {
					// try command to check if the user typed a number
					try {
						// If it is not a number store the value
						notNumber = scan.next();
						// Convert and store in the same time
						content = Integer.parseInt(notNumber);
						// Counter Just to know how many times the 1th loop has rotated
						countA++;
						// if it is true, keep going
						valid1 = true;
						// Catch command if the user type a character that is not a number
					} catch (Exception e) {
						// Show on screen that is not a number
						System.out.println("In the First Matrix, This character is not a valid number: " + notNumber);
						// Call the method to quit the program
						exitProgram();
						// if it is false, stop the loop and ask the user to type again
						valid1 = false;
					}
				}
			}
		}
		// Ifs just to know if the first matrix is valid
		if (countA < 0) {
			// Show on screen the file is empty
			System.out.println("File is empty or invalid format");
			emptyFile = true;
		}
		// that part checks if the second matrix is empty
		if (countB > 0) {
			validMatriB = true;
			emptyFile = false;
		}
		// Here to check if there is a @ in the file
		if (at == null) {
			System.out.println("@ was not found, There is no Matrix B or invalid format");
			validMatriB = false;
			// If everything is good, show on screen a OK
		} else {
			System.out.println("The characteres of the matrices are matching");
		}
	}

	// Method specific to save a matrix in double format
	public void saveDoubleMatrix(double[][] matrix) throws IOException {
		// Variable file to take a name of file
		String file = "";
		// Variable to make unnecessary the user put a extension when they type the name
		// of the file
		String fileExt = ".txt";
		// Variable exclusively to separate the matrix
		String matrixSeparator = "";
		// Variables to read the file and to read the keyboard
		Scanner scanFile = null;
		Scanner scanChar = null;
		// Variable to decide stop or keep going the WhileLoop
		boolean valid = false;
		// Variable to write in a file
		BufferedWriter myWriter = null;
		// Variable to store a option chosen by the user
		String fileOption;
		// try command to take some error
		try {
			// Set the variable to get from the Scanner
			scanChar = new Scanner(System.in);
			// WhileLoop to show to the user some options to save the file
			while (!valid) {
				System.out.println("Choose a option of the name to save the results");
				// Show a menu to the user where they only need to type 1 or 2
				System.out.println("1.Automatic: Answer.txt\n2.Manual: Type a file name");
				// Read the option from the user
				fileOption = scanChar.nextLine();
				// Check and validate the user option
				if (fileOption.equals("1")) {
					// If the number 1 is chosen then save the matrix in file predefined
					file = "Answer";
					// Stop the loop with the user option
					valid = true;
					// If the number 2 be chosen then save the matrix in file defined by the user
				} else if (fileOption.equals("2")) {
					// Show on screen a message to the user asking to type a name with file
					// extension
					System.out.println("Type a name for the file(do not need a file extension): ");
					file = scanChar.nextLine();
					valid = true;
					// If the user type something different than 1 or 2 the loop will ask the user
					// again
				} else {
					// Show to the user that his option was wrong
					System.out.println("Please choose a correct option");
					// Restart the loop based on the condition of the loop
					valid = false;
				}
			}
			// just reset the variable to read the Scanner if it is necessary
			scanChar.reset();
			// Declare a variable to write in the file
			myWriter = new BufferedWriter(new FileWriter(file + fileExt, true));
			// Declare a variable to read a file through the Scanner
			scanFile = new Scanner(new FileReader(file + fileExt));
			// Check if there is something in the file put a value in a variable
			if (scanFile.hasNext()) {
				matrixSeparator = "@";
				// Write a at in the file
				myWriter.write(matrixSeparator);
				myWriter.newLine();
				// If the file is empty, do not put anything in a variable
			} else {
				matrixSeparator = "";
				// Write nothing in the file
				myWriter.write(matrixSeparator);
			}
			// Show on screen a message the file was save it
			System.out.println("The matrix was saved in the file: " + file + fileExt);
			// catch function to check errors
		} catch (Exception e) {
			System.out.println("File not found(" + file + fileExt + ")");
			exitProgram();
		}
		// put the index from matrix in a variable to write in the file
		String matrixIndex = matrix.length + " " + matrix[0].length;
		// Write the index in the file, taking care with spaces trimming the spaces if
		// there is it
		myWriter.write(matrixIndex.trim());
		// Write a new line in the file
		myWriter.newLine();
		// ForLoop to run through the matrix and take the element to put in a variable
		// and write in file after
		for (int i = 0; i < matrix.length; i++) {
			// Variable to make a transition between the matrix in write in a file
			String line = "";
			for (int j = 0; j < matrix[i].length; j++) {
				// Store the element of matrix in a variable
				line = line + matrix[i][j] + " ";
			}
			// Write in the file a variable and trimming the empty spaces
			myWriter.write(line.trim());
			// Write in the file a new line
			myWriter.newLine();
		}
		// Close the writer on file to save and close the file too
		myWriter.close();
	}

	// Method to save the integer Matrix in the file, almost the same as the
	// previous method "saveDoubleMatrix"
	// As it the same code than the previous one, I have not commented it
	public void saveIntegerMatrix(int[][] matrix) throws IOException {
		String file = "";
		String fileExt = ".txt";
		String matrixSeparator = "";
		Scanner scanFile = null;
		Scanner scanChar = null;
		boolean valid = false;
		BufferedWriter myWriter = null;
		String fileOption;
		try {
			scanChar = new Scanner(System.in);
			while (!valid) {
				System.out.println("Choose a option of the name to save the results");
				System.out.println("1.Automatic: Answer.txt\n2.Manual: Typing the file name");
				fileOption = scanChar.nextLine();
				if (fileOption.equals("1")) {
					file = "Answer";
					valid = true;
				} else if (fileOption.equals("2")) {
					System.out.println("Type a name for the file(do not need a file extension): ");
					file = scanChar.nextLine();
					valid = true;
				} else {
					System.out.println("Please choose a correct option");
					valid = false;
				}
			}
			scanChar.reset();
			myWriter = new BufferedWriter(new FileWriter(file + fileExt, true));
			scanFile = new Scanner(new FileReader(file + fileExt));
			if (scanFile.hasNext()) {
				matrixSeparator = "@";
				myWriter.write(matrixSeparator);
				myWriter.newLine();
			} else {
				matrixSeparator = "";
				myWriter.write(matrixSeparator);
			}
			System.out.println("The matrix was saved in the file: " + file + fileExt);
		} catch (FileNotFoundException e) {
			System.out.println("File not found(" + file + fileExt + ")");
			exitProgram();
		}
		String matrixIndex = matrix.length + " " + matrix[0].length;
		myWriter.write(matrixIndex.trim());
		myWriter.newLine();
		for (int i = 0; i < matrix.length; i++) {
			String line = "";
			for (int j = 0; j < matrix[i].length; j++) {
				line = line + matrix[i][j] + " ";
			}
			myWriter.write(line.trim());
			myWriter.newLine();
		}
		myWriter.close();
	}

	// Method to ask the user a name to save a file
	public String inputSaveFile(String prompt) {
		// Start the Scanner to read inputs from keyboard
		Scanner myScan6 = new Scanner(System.in);
		String file = "";
		// Show to the user the the name of the file;
		System.out.println(prompt);
		// take the user input and keep in variable file
		file = myScan6.nextLine();
		// just add extension in the variable file
		file = file + ".txt";
		// Call the method to quit the program
		exitProgram();
		return file;
	}

	// Method to show matrices on screen
	public void showMatrices(String prompt, int[][] matrix) {
		// Show any Matrix on screen
		System.out.println(prompt);
		// LoopFor to read a matrix
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				// Print on screen the values of matrix one by one
				System.out.print(matrix[i][j] + "\t");
			}
			// Print a space after the last values of the column
			System.out.println();
		}
	}

	// Method to ask the user to quit the program or keep going
	public void exitProgram() {
		// Start the Scanner to read from keyboard
		Scanner myScan5 = new Scanner(System.in);
		// Show to the user the question for leaving the program
		System.out.println("<Y>Quit program?\n<N>Try again)");
		try {
			// Keep what the user typed
			String answer = myScan5.nextLine();
			// Check if the users type "Y" no sencitiveCase
			if (answer.equalsIgnoreCase("Y")) {
				// Show on screen a gentle phrase
				System.out.println("Thank you, See you later");
				System.exit(0);
				// Check if the users type "N" no sensitive Case
			} else if (answer.equalsIgnoreCase("N")) {
				// Loop to clear the screen
				for (int i = 0; i < 200; i++) {
					System.out.println();
					// Calling the method again to restart the program
					new calcMatrix();
				}
			} else {
				System.out.println();
				// reCalling the method if it was typed something different of "Y" or "N"
				exitProgram();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Method main just to call the constructors
	public static void main(String[] args) throws Exception {
		// Calling the constructor
		new calcMatrix();
	}
}
