/**
 * 
 */
package com.ck.sudoku.checker;

import com.ck.sudoku.SudokuSolProcessor;
import com.ck.sudoku.SudokuSolProcessorImpl;

/**
 * @author Chinmay
 * 
 */
public class SudokuValidator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("Invalid Command line");
			System.out.println("Usage : java SudokuValidator \"<file-path>\"");
			return;
		}

		SudokuSolProcessor sudoku = new SudokuSolProcessorImpl();

		if (sudoku.isSudokuSolutionValid(args[0])){
			System.out.println("Valid Solution!");
		}else{
			System.out.println("Invalid Solution!");
		}
			

		System.out.println("Done!");
	}

}
