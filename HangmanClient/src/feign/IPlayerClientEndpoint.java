package feign;

//import dto.GameDto;
import dto.PlayerDto;

// TODO: Auto-generated Javadoc
/**
 * The Interface IPlayerClientEndpoint.
 */
public interface IPlayerClientEndpoint {
	
	/**
	 * Gets the player by name.
	 *
	 * @param playerName the player name
	 * @return the player by name
	 */
	PlayerDto getPlayerByName(String playerName);
	
	/**
	 * Gets the player by id.
	 *
	 * @param playerId the player id
	 * @return the player by id
	 */
	PlayerDto getPlayerById(long playerId);
		
}
