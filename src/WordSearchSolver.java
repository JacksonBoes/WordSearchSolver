import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * A program to read in a word search from a file and solve it for the user.
 * @author Jackson Boes
 *
 */
public class WordSearchSolver {
	
	/**
	 * This will solve the word search by locating each word.
	 * @param wordSearch
	 * 		The array with the word search to solve.
	 * @param wordsToFind
	 * 		The array with the words to find in the word search.
	 */
	public static void solveWordSearch(String[][] wordSearch, String[] wordsToFind) {
		/*
		 * iterate through every letter in the array and until it finds the correct first letter to check if the
		 * word is there
		 */
		for (int n = 0; n < wordsToFind.length; n++) {
			boolean beenFound = false; //boolean to hold whether the current word has been found
			String word = wordsToFind[n];
			for (int i = 0; !beenFound && i < wordSearch.length; i++) {
				for (int j = 0; !beenFound && j < wordSearch[i].length; j++) {
					if (wordSearch[i][j].compareToIgnoreCase(word.substring(0,1)) == 0) {
						beenFound = checkSurroundings(wordSearch, word.substring(1), i, j);
					}
				}
			}
		}
	}
	
	/**
	 * Will check the surroundings of each starting letter to attempt to find the word
	 * @param wordSearch
	 * 		The array with the word search to solve.
	 * @param word
	 * 		The array with the words to find in the word search.
	 * @param row
	 * 		The current row of the word search.
	 * @param column
	 * 		The current column of the word search.
	 * @return If the current placement of the word is correct.
	 */
	public static boolean checkSurroundings(String[][] wordSearch, String word, int row, int column) { 
		/*
		 * this will determine if the correct first letter has the correct second letter in its surroundings
		 * and will pass the direction the this correct letter is from the first letter to the isWord method
		 * it will return true if it has found the word it was checking for and false otherwise
		 */
		boolean beenFound = false; //boolean for if the word has been found
		if (row > 0 && wordSearch[row-1][column].compareToIgnoreCase(word.substring(0,1)) == 0) {
			if (isWord(wordSearch, word.substring(1), row-1, column, "Up")) {
				wordSearch[row][column] = wordSearch[row][column].toUpperCase();
				wordSearch[row-1][column] = wordSearch[row-1][column].toUpperCase();
			}
		} else if (row < wordSearch.length-1 && wordSearch[row+1][column].compareToIgnoreCase(word.substring(0,1)) == 0) {
			if (isWord(wordSearch, word.substring(1), row+1, column, "Down")) {
				wordSearch[row][column] = wordSearch[row][column].toUpperCase();
				wordSearch[row+1][column] = wordSearch[row+1][column].toUpperCase();
			}
		} else if (column < wordSearch[row].length-1 && wordSearch[row][column+1].compareToIgnoreCase(word.substring(0,1)) == 0) {
			if (isWord(wordSearch, word.substring(1), row, column+1, "Right")) {
				wordSearch[row][column] = wordSearch[row][column].toUpperCase();
				wordSearch[row][column+1] = wordSearch[row][column+1].toUpperCase();
			}
		} else if (column > 0 && wordSearch[row][column-1].compareToIgnoreCase(word.substring(0,1)) == 0) {
			if (isWord(wordSearch, word.substring(1), row, column-1, "Left")) {
				wordSearch[row][column] = wordSearch[row][column].toUpperCase();
				wordSearch[row][column-1] = wordSearch[row][column-1].toUpperCase();
			}
		} else if (row > 0 && column > 0 && wordSearch[row-1][column-1].compareToIgnoreCase(word.substring(0,1)) == 0) {
			if (isWord(wordSearch, word.substring(1), row-1, column-1, "Up Left")) {
				wordSearch[row][column] = wordSearch[row][column].toUpperCase();
				wordSearch[row-1][column-1] = wordSearch[row-1][column-1].toUpperCase();
			}
		} else if (row > 0 && column < wordSearch[row].length-1 && wordSearch[row-1][column+1].compareToIgnoreCase(word.substring(0,1)) == 0) {
			if (isWord(wordSearch, word.substring(1), row-1, column+1, "Up Right")) {
				wordSearch[row][column] = wordSearch[row][column].toUpperCase();
				wordSearch[row-1][column+1] = wordSearch[row-1][column+1].toUpperCase();
			}
		} else if (row < wordSearch.length-1 && column > 0 && wordSearch[row+1][column-1].compareToIgnoreCase(word.substring(0,1)) == 0) {
			if (isWord(wordSearch, word.substring(1), row+1, column-1, "Down Left")) {
				wordSearch[row][column] = wordSearch[row][column].toUpperCase();
				wordSearch[row+1][column-1] = wordSearch[row+1][column-1].toUpperCase();
			}
		} else if (row < wordSearch.length-1 && column < wordSearch[row].length-1 && wordSearch[row+1][column+1].compareToIgnoreCase(word.substring(0,1)) == 0) {
			if (isWord(wordSearch, word.substring(1), row+1, column+1, "Down Right")) {
				wordSearch[row][column] = wordSearch[row][column].toUpperCase();
				wordSearch[row+1][column+1] = wordSearch[row+1][column+1].toUpperCase();
			}
		}
		return beenFound;
	}
	
	/**
	 * Checks if it has found the word, capitalizes the word if it has.
	 * @param wordSearch
	 * 		The array with the word search to solve.
	 * @param word
	 * 		The array with the words to find in the word search.
	 * @param row
	 * 		The current row of the word search.
	 * @param column
	 * 		The current column of the word search.
	 * @return Whether or not the program has found the word.
	 */ 
	public static boolean isWord(String[][] wordSearch, String word, int row, int column, String direction ) {
		/*
		 * this method will check each letter sequentially in the direction determined in checkSurroundings method
		 * and will only stop once it finds an incorrect letter and will then return false but if it never finds an incorrect
		 * letter then it will return true and will capitalize all of the letters of the word in the word search
		 * so that the user can find it
		 */
		boolean isWord = true; //boolean to hold whether the word is correct
		if (!(word.length() == 0)) {
			if (direction.equals("Up") && row > 0 && wordSearch[row-1][column].compareToIgnoreCase(word.substring(0,1)) == 0) {
				if (isWord(wordSearch, word.substring(1), row-1, column, "Up")) {
					wordSearch[row-1][column] = wordSearch[row-1][column].toUpperCase();
				}
			} else if (direction.equals("Down") && row < wordSearch.length-1 && wordSearch[row+1][column].compareToIgnoreCase(word.substring(0,1)) == 0) {
				if (isWord(wordSearch, word.substring(1), row+1, column, "Down")) {
					wordSearch[row+1][column] = wordSearch[row+1][column].toUpperCase();
				}
			} else if (direction.equals("Right") && column < wordSearch[row].length-1 && wordSearch[row][column+1].compareToIgnoreCase(word.substring(0,1)) == 0) {
				if (isWord(wordSearch, word.substring(1), row, column+1, "Right")) {
					wordSearch[row][column+1] = wordSearch[row][column+1].toUpperCase();
				}
			} else if (direction.equals("Left") && column > 0 && wordSearch[row][column-1].compareToIgnoreCase(word.substring(0,1)) == 0) {
				if (isWord(wordSearch, word.substring(1), row, column-1, "Left")) {
					wordSearch[row][column-1] = wordSearch[row][column-1].toUpperCase();
				}
			} else if (direction.equals("Up Left") && row > 0 && column > 0 && wordSearch[row-1][column-1].compareToIgnoreCase(word.substring(0,1)) == 0) {
				if (isWord(wordSearch, word.substring(1), row-1, column-1, "Up Left")) {
					wordSearch[row-1][column-1] = wordSearch[row-1][column-1].toUpperCase();
				}
			} else if (direction.equals("Up Right") && row > 0 && column < wordSearch[row].length-1 && wordSearch[row-1][column+1].compareToIgnoreCase(word.substring(0,1)) == 0) {
				if (isWord(wordSearch, word.substring(1), row-1, column+1, "Up Right")) {
					wordSearch[row-1][column+1] = wordSearch[row-1][column+1].toUpperCase();
				}
			} else if (direction.equals("Down Left") && row < wordSearch.length-1 && column > 0 && wordSearch[row+1][column-1].compareToIgnoreCase(word.substring(0,1)) == 0) {
				if (isWord(wordSearch, word.substring(1), row+1, column-1, "Down Left")) {
					wordSearch[row+1][column-1] = wordSearch[row+1][column-1].toUpperCase();
				}
			} else if (direction.equals("Down Right") && row < wordSearch.length-1 && column < wordSearch[row].length-1 && wordSearch[row+1][column+1].compareToIgnoreCase(word.substring(0,1)) == 0) {
				if (isWord(wordSearch, word.substring(1), row+1, column+1, "Down Right")) {
					wordSearch[row+1][column+1] = wordSearch[row+1][column+1].toUpperCase();
				}
			} else {
				isWord = false;
			}
		}
		return isWord;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		File newFile = new File("WordSearch");
		Scanner fileIn = new Scanner(newFile);
		
		//get the size of the word search
		System.out.print("How many rows are in your word seach: ");
		int rows = in.nextInt();
		System.out.print("How many columns are in your word search: ");
		int columns = in.nextInt();
		in.nextLine();
		
		//declare an array that can hold a word search of the appropriate size
		String[][] wordSearch = new String[rows][columns];
		
		//fill in the array with the letters from the word search
		for (int j = 0; j < wordSearch.length; j++) {
			String line = fileIn.nextLine();
			for (int i = 0; i < wordSearch[j].length; i++) {
				wordSearch[j][i] = line.substring(i, i+1);
			}
		}
		
		System.out.print("How many words do you need to find? "); //get the number of words the user needs to find
		int numWords = in.nextInt();
		in.nextLine();
		
		String[] wordsToFind = new String[numWords]; //create an array to hold the words
		
		System.out.println("Enter each word on its own line (no capitals)");
		
		for (int i = 0; i < wordsToFind.length; i++) { //fill array with the words to find
			wordsToFind[i] = in.nextLine();
		}
		
		solveWordSearch(wordSearch, wordsToFind); //call method to solve the word search
		
		for (String[] x : wordSearch) { //print word search
			for (String y : x) {
				System.out.print(y);
			}
			System.out.println();
		}
		
		fileIn.close();
		in.close();
	}

}
