/**
 * 
 */
package com.ck.sudoku.model;

/**
 * @author Chinmay
 *
 */
public class Board {
	
	public int[][] boardLayout;
	public int boardSize;
	
	public Board(int boardSize) {
		this.boardSize = boardSize;
		this.boardLayout = new int[boardSize][boardSize];
	}
	public int getBoardSize() {
		return boardSize;
	}
	public int[][] getBoardLayout() {
		return boardLayout;
	}
}
