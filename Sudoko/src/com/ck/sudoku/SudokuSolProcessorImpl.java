/**
 * 
 */
package com.ck.sudoku;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.ck.sudoku.exceptions.IllegalInput;
import com.ck.sudoku.exceptions.InvalidSolution;
import com.ck.sudoku.model.Board;
import com.ck.sudoku.utils.SudokuUtils;

/**
 * @author Chinmay
 * 
 */
public class SudokuSolProcessorImpl implements SudokuSolProcessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ck.sudoku.SudokuSolProcessor#isSudokuSolutionValid(java.lang.String)
	 * This is the main checker if a board is a valid solution given a inout
	 * file
	 */

	@Override
	public boolean isSudokuSolutionValid(String filePath) {

		try {

			// The File Reader performs pre-checks while building board for
			// efficiency
			SudokuFileReader sudokuFileReader = new SudokuFileReader(filePath);
			Board sudokuBoard = sudokuFileReader.buildBoardWithPrechecks();

			// Some post board building Checks
			if (!isValidBoardPostBoardBuilding(sudokuBoard)) {
				System.out.println("Invalid Solution : Post Board Build Validations Failed");
				return false;
			}
		} catch (InvalidSolution e) {
			System.out.println("Invalid Solution : " + e.getMessage());
			return false;
		} catch (IllegalInput e) {
			System.out.println("Illegal Input : " + e.getMessage());
			return false;
		} catch (FileNotFoundException e) {
			System.out.println("File Not found : " + e.getMessage());
			return false;
		} catch (IOException e) {
			System.out.println("I/O Exception : " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("Unexpected Exception : " + e.getMessage());
			return false;
		}
		return true;
	}

	/*
	 * Performs checks post board building 
	 * 1) Column Check 
	 * 2) SubBoards Check
	 */
	public boolean isValidBoardPostBoardBuilding(Board board)
			throws IllegalInput, InvalidSolution {

		// CHECK column Check and Suboard Check
		if (!(SudokuUtils.checkColumnsForBoard(board.getBoardLayout(), board.getBoardSize()) 
				&& SudokuUtils.subBoardCheck( board.getBoardLayout(), board.getBoardSize())))
			return false;

		System.out.println("Validations done!");
		return true;

	}

}
