package com.ck.sudoku.utils;

import java.util.ArrayList;
import java.util.List;

import com.ck.sudoku.exceptions.IllegalInput;
import com.ck.sudoku.exceptions.InvalidSolution;

public class SudokuUtils {

	/*
	 * This function does the following checks 
	 * 1) Each number should be an integer 
	 * 2) each number should be in the range 1 to n, both inclusive 
	 * 3) each number should be present exactly once, is is done using a numCount array 
	 * 4) the sum of the numbers should be n(n+1)/2 
	 * 5) if the size of array equals board Size, 
	 * This is needs as it should be nxn and not nxm
	 */
	public static boolean check1DArrayHasAllNumbers(int[] elements,
			int boardSize) throws IllegalInput {

		int[] numCount = new int[boardSize];
		int expectedSum = boardSize * (boardSize + 1) / 2;

		if (elements.length != boardSize)
			throw new IllegalInput("Board Size is illegal");

		for (int i = 0; i < elements.length; i++) {
			int element = elements[i];

			// CHECK : Each entry should be in the range of 1 to n.
			if (element > boardSize || element < 1)
				throw new IllegalInput("out of range number present");
			
			// if a number has been repeated return right away, save some extra processing
			if(numCount[element - 1] != 0 )
				return false;
							
			numCount[element - 1] += 1;
			expectedSum -= element;			// subtracting from the total expected sum, at the end of the loop the sum should be 0
		}

		// CHECK : The row sum if not equal to n(n+1)/2 then the Sum Check Fails
		if (expectedSum != 0)
			return false;

		// CHECK : A Valid array should have each number once
		for (int i = 0; i < boardSize; i++) {
			if (numCount[i] != 1)
				return false;
		}
		return true;
	}

	/*
	 * This Function performs the following columns check It calls the same
	 * function used for Row Checks
	 */

	public static boolean checkColumnsForBoard(int[][] arr, int boardSize)
			throws IllegalInput, InvalidSolution {

		// temp buffer to turn column into an array so that same 1D array check
		// can be called
		int[] colArray = new int[boardSize];

		for (int col = 0; col < boardSize; col++) {
			for (int row = 0; row < boardSize; row++) {
				colArray[row] = arr[row][col];
			}

			if (!SudokuUtils.check1DArrayHasAllNumbers(colArray, boardSize))
				throw new InvalidSolution("Columns Check Failed");
		}
		return true;
	}

	/*
	 * Checks if a number is perfect sqaure Root Using Binary Search Algorithm
	 * to achieve that
	 */

	public static boolean isPerfectSqaureRoot(int number) {

		int low = 0;
		int high = number;
		double precision = 1; // since perfect squareRoot

		if (number < 1)
			return false; // do not allow such cases
		if (number == 1)
			return true;

		// using Binary Search to check if perfect Square
		while ((high - low) > precision) {
			double mid = (low + high) / 2;
			double current = mid * mid;
			if (current == number) {
				return true;
			} else if (current > number) {
				high = (int) mid;
			} else {
				low = (int) mid;
			}
		}
		return false;

	}

	/*
	 * // CHECK : Any floating number would Error out here, hence handeled
	 * accordingly
	 */

	public static Integer checkInteger(String numString) throws IllegalInput {

		try {
			return (Integer.parseInt(numString));
		} catch (NumberFormatException e) {
			throw new IllegalInput("Invalid number format found " + e.getMessage());
		}

	}

	/*
	 * This function checks the sub board for the valid solution Build a 1D
	 * array by traversing the subBoard and use the same 1D array checker
	 */

	public static boolean subBoardCheck(int[][] board, int boardSize)
			throws IllegalInput, InvalidSolution {

		int offset = (int) Math.sqrt(boardSize);

		// offset variables for the subarray traversal
		int minRow = 0, minCol = 0;
		int maxRow = offset, maxCol = offset;

		while (maxRow <= boardSize) {
			while (maxCol <= boardSize) {

				// All elements from the subarray will be filled in this
				// buffer row by row and we recall the 1D array check
				List<Integer> subBoardList = new ArrayList<Integer>();
				for (int row = minRow; row < maxRow; row++) {
					for (int col = minCol; col < maxCol; col++) {
						subBoardList.add(board[row][col]);
					}
				}

				// CHECK : SubBoard Check
				// This is a 1D check problem now
				if (!check1DArrayHasAllNumbers(toIntArray(subBoardList), boardSize))
					throw new InvalidSolution("Sub Board Check Failed");

				// update the offset for
				// left to right traversal of subarray
				minCol += offset;
				maxCol += offset;
			}

			// Reset the column offsets
			minCol = 0;
			maxCol = offset;

			// update the offset for
			// top to bottom traversal of subarray
			minRow += offset;
			maxRow += offset;
		}
		return true;
	}

	/*
	 * Helper function to convert List<Integer> to int[]
	 */

	public static int[] toIntArray(List<Integer> list) {
		int[] ret = new int[list.size()];
		
		for (int i = 0; i < ret.length; i++)
			ret[i] = list.get(i);
		
		return ret;
	}
}
