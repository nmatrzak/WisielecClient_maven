package dto;

import java.io.Serializable;

import utils.WordCodeDecode;

// TODO: Auto-generated Javadoc
/**
 * The Class GameDto.
 */
public class GameDto implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 75264711556227767L;
	
	/** The word. */
	private String theWord;
	
	/** The gapped word. */
	private String gappedWord;	
	
	/** The count missed. */
	private int countMissed = 0;
	
	/** The used letters. */
	private String usedLetters;	
	
	/** The game status. */
	private String gameStatus;
	
	/** The winner. */
	private String winner;
	
	/** The player word name. */
	private String playerWordName;
	
	/** The player guess name. */
	private String playerGuessName;	
	
	/**
	 * Instantiates a new game dto.
	 */
	public GameDto() {		
	}

	/**
	 * Instantiates a new game dto.
	 *
	 * @param theWord the the word
	 * @param gappedWord the gapped word
	 * @param countMissed the count missed
	 * @param usedLetters the used letters
	 * @param gameStatus the game status
	 * @param winner the winner
	 */
	public GameDto(String theWord, String gappedWord, int countMissed, String usedLetters, String gameStatus, String winner) {		
		this.theWord = theWord;
		this.gappedWord = gappedWord;
		this.countMissed = countMissed;
		this.usedLetters = usedLetters;
		this.gameStatus = gameStatus;
		this.winner = gameStatus;
	}

	/**
	 * Gets the the word.
	 *
	 * @return the the word
	 */
	public String getTheWord() {
		return theWord;
	}

	/**
	 * Sets the the word.
	 *
	 * @param theWord the new the word
	 */
	public void setTheWord(String theWord) {
		this.theWord = theWord;
	}

	/**
	 * Gets the gapped word.
	 *
	 * @return the gapped word
	 */
	public String getGappedWord() {
		return gappedWord;
	}

	/**
	 * Sets the gapped word.
	 *
	 * @param gappedWord the new gapped word
	 */
	public void setGappedWord(String gappedWord) {
		this.gappedWord = gappedWord;
	}

	/**
	 * Gets the count missed.
	 *
	 * @return the count missed
	 */
	public int getCountMissed() {
		return countMissed;
	}

	/**
	 * Sets the count missed.
	 *
	 * @param countMissed the new count missed
	 */
	public void setCountMissed(int countMissed) {
		this.countMissed = countMissed;
	}

	/**
	 * Gets the used letters.
	 *
	 * @return the used letters
	 */
	public String getUsedLetters() {
		return usedLetters;
	}

	/**
	 * Sets the used letters.
	 *
	 * @param usedLetters the new used letters
	 */
	public void setUsedLetters(String usedLetters) {
		this.usedLetters = usedLetters;
	}

	/**
	 * Gets the game status.
	 *
	 * @return the game status
	 */
	public String getGameStatus() {
		return gameStatus;
	}

	/**
	 * Sets the game status.
	 *
	 * @param gameStatus the new game status
	 */
	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	/**
	 * Gets the winner.
	 *
	 * @return the winner
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * Sets the winner.
	 *
	 * @param winner the new winner
	 */
	public void setWinner(String winner) {
		this.winner = winner;
	}	
		
	/**
	 * Gets the player word name.
	 *
	 * @return the player word name
	 */
	public String getPlayerWordName() {
		return playerWordName;
	}

	/**
	 * Sets the player word name.
	 *
	 * @param playerWordName the new player word name
	 */
	public void setPlayerWordName(String playerWordName) {
		this.playerWordName = playerWordName;
	}

	/**
	 * Gets the player guess name.
	 *
	 * @return the player guess name
	 */
	public String getPlayerGuessName() {
		return playerGuessName;
	}

	/**
	 * Sets the player guess name.
	 *
	 * @param playerGuessName the new player guess name
	 */
	public void setPlayerGuessName(String playerGuessName) {
		this.playerGuessName = playerGuessName;
	}

	/**
	 * Convert.
	 */
	public void convert() {
		this.theWord = WordCodeDecode.decodeWordWithSpecsToPolishWord(this.theWord);
		this.usedLetters = WordCodeDecode.decodeWordWithSpecsToPolishWord(this.usedLetters);
		this.winner = WordCodeDecode.decodeWordWithSpecsToPolishWord(this.winner);
		this.playerWordName = WordCodeDecode.decodeWordWithSpecsToPolishWord(this.playerWordName);
		this.playerGuessName = WordCodeDecode.decodeWordWithSpecsToPolishWord(this.playerGuessName);
	}
}
