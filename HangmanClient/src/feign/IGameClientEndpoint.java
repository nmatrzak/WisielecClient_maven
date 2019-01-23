package feign;

import dto.GameDto;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGameClientEndpoint.
 */
public interface IGameClientEndpoint {
	
//	boolean createGameReturnTrueIfGuess(String playerName, String opponentName);
//	GameDto updateWord(String playerName, String word);
//	GameDto sendLetter(String playerName, String letter);
//	GameDto getGame(String playerName);
	
	/**
 * Creates the game return true if guess.
 *
 * @param playerId the player id
 * @param opponentId the opponent id
 * @return true, if successful
 */
boolean createGameReturnTrueIfGuess(long playerId, long opponentId);
	
	/**
	 * Update word.
	 *
	 * @param playerId the player id
	 * @param word the word
	 * @return the game dto
	 */
	GameDto updateWord(long playerId, String word);
	
	/**
	 * Send letter.
	 *
	 * @param playerId the player id
	 * @param letter the letter
	 * @return the game dto
	 */
	GameDto sendLetter(long playerId, String letter);
	
	/**
	 * Gets the game.
	 *
	 * @param playerId the player id
	 * @return the game
	 */
	GameDto getGame(long playerId);
	
//	void endGame(long playerId);

}
