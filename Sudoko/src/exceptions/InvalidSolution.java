/**
 * 
 */
package com.ck.sudoku.exceptions;

/**
 * @author Chinmay
 * 
 * The need for the exception is to highlight where exactly the 
 * solutions turns Invalid. 
 * The solution instead of just returning false now throws an exception highlighting 
 * the check on inavlidity
 *
 */
public class InvalidSolution extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7231587880348159888L;

	public InvalidSolution(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
