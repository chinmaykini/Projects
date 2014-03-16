/**
 * 
 */
package com.ck.sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.ck.sudoku.exceptions.IllegalInput;
import com.ck.sudoku.exceptions.InvalidSolution;
import com.ck.sudoku.model.Board;
import com.ck.sudoku.utils.SudokuUtils;

/**
 * @author Chinmay
 * 
 */
public class SudokuFileReader {

	public String filePath;
	public Board board;

	public SudokuFileReader(String filePath) {
		this.filePath = filePath;
	}

	/*
	 * This function initializes the board with the first line from the file as
	 * input 1) ChecKS : It Doesnt build the board if the size of column is not
	 * a perfect square Root
	 */

	private void initializeWithPreChecks(String firstRow) throws IllegalInput {

		System.out.println("Initializing and performing iilegal input Checks..");

		if (firstRow == null || firstRow.isEmpty())
			throw new IllegalInput("INVALID BOARD SIZE");

		String[] elements = firstRow.split(",");

		// CHECK : The board should be of Valid Size 1,4,9 being valid
		if (!SudokuUtils.isPerfectSqaureRoot(elements.length))
			throw new IllegalInput("INVALID BOARD SIZE");

		board = new Board(elements.length);
	}

	/*
	 * This Function Reads the file line by line Checks if the inputs are valid,
	 * if illegal inputs have been made For Efficiency 
	 * 1) Row Validations are performed here 
	 * 2) Square root validations 3) Number Validations
	 */
	public Board buildBoardWithPrechecks() throws IllegalInput,
			InvalidSolution, FileNotFoundException, IOException {

		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(filePath));
			String line = br.readLine();
			int row = 0;

			// CHECK :initializes the board along with squareRoot Check
			initializeWithPreChecks(line);

			while (line != null) {

				String[] elements = line.split(",");

				// CHECK : Any floating number would Error out here
				for (int i = 0; i < elements.length; i++)
					board.boardLayout[row][i] = SudokuUtils.checkInteger(elements[i]);

				// CHECK : Row Checks ( Column Checks are done post board building )
				if (!SudokuUtils.check1DArrayHasAllNumbers( board.boardLayout[row], board.boardSize))
					throw new InvalidSolution("Row Check Failed");

				line = br.readLine();
				row++;
			}

			System.out.println("Board Built and pre-Checks Done!");
			return board;

		} finally {
			if (br != null)
				br.close();
		}

	}

}
