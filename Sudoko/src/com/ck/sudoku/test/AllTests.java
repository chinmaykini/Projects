package com.ck.sudoku.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//import junit.framework.TestCase;
import org.junit.Test;

import com.ck.sudoku.SudokuSolProcessorImpl;


public class AllTests {
	
	SudokuSolProcessorImpl sudoku = new SudokuSolProcessorImpl();
	
	@Test
	public void testValid4x4() throws Exception{
			assertTrue( sudoku.isSudokuSolutionValid("./data/sampleInput 4x4.txt"));
	}
	
	@Test
	public void testValid1x1() throws Exception{
			assertTrue( sudoku.isSudokuSolutionValid("./data/sampleInput 1x1.txt"));
	}
	
	@Test
	public void testInValidBoard3x3() throws Exception{
			assertFalse( sudoku.isSudokuSolutionValid("./data/sampleInput 3x3.txt"));
	}
	
	@Test
	public void testInValidBoardNonInteger4x4() throws Exception{
		assertFalse( sudoku.isSudokuSolutionValid("./data/sampleInput_nonInt 4x4.txt"));
	}
	
	@Test
	public void testValid9x9() throws Exception{
			assertTrue( sudoku.isSudokuSolutionValid("./data/sampleInput 9x9.txt"));
	}
	
	@Test
	public void testBlankBoard() throws Exception{
			assertFalse( sudoku.isSudokuSolutionValid("./data/sampleInput_blank.txt"));
	}
	
	@Test
	public void testOffRangeNumber() throws Exception{
			assertFalse( sudoku.isSudokuSolutionValid("./data/sampleInput_OffRange 4x4.txt"));
	}
	
	@Test
	public void testFail4x4() throws Exception{
			assertFalse( sudoku.isSudokuSolutionValid("./data/sampleInvSolInput 4x4.txt"));
	}
	@Test
	public void testFail9x9() throws Exception{
			assertFalse( sudoku.isSudokuSolutionValid("./data/sampleInvSolInput 9x9.txt"));
	}
	@Test
	public void testFileNotFound() throws Exception{
			assertFalse( sudoku.isSudokuSolutionValid("./data/iDoNotExist.txt"));
	}
	
	@Test
	public void testInvalidSubBoard() throws Exception{
			assertFalse( sudoku.isSudokuSolutionValid("./data/sampleInputInvSubArr 9x9.txt"));
	}
}
