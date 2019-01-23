package dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import utils.WordCodeDecode;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerDto.
 */
@XmlRootElement
public class PlayerDto implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7526471155622776147L;
	
	/** The player id. */
	private long playerId = -1;
	
	/** The name. */
	private String name = "";
	
	/** The points. */
	private long points = 0;
	
	/** The count wins. */
	private long countWins = 0;
	
	/** The count losts. */
	private long countLosts = 0;
	
	/** The status. */
	private String status;
	
	/**
	 * Instantiates a new player dto.
	 */
	public PlayerDto() { }
	
	/**
	 * Instantiates a new player dto.
	 *
	 * @param playerId the player id
	 * @param name the name
	 * @param points the points
	 * @param countWins the count wins
	 * @param countLosts the count losts
	 * @param status the status
	 */
	public PlayerDto(long playerId, String name, long points, long countWins, long countLosts,  String status) {
		this.playerId = playerId;
		this.name=name;
		this.points=points;
		this.countWins=countWins;
		this.countLosts=countLosts;
		this.status = status;
	}	
	
	/**
	 * Gets the player id.
	 *
	 * @return the player id
	 */
	public long getPlayerId() {
		return playerId;
	}

	/**
	 * Sets the player id.
	 *
	 * @param playerId the new player id
	 */
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public long getPoints() {
		return points;
	}
	
	/**
	 * Sets the points.
	 *
	 * @param points the new points
	 */
	public void setPoints(long points) {
		this.points = points;
	}
	
	/**
	 * Gets the count wins.
	 *
	 * @return the count wins
	 */
	public long getCountWins() {
		return countWins;
	}
	
	/**
	 * Sets the count wins.
	 *
	 * @param countWins the new count wins
	 */
	public void setCountWins(long countWins) {
		this.countWins = countWins;
	}
	
	/**
	 * Gets the count losts.
	 *
	 * @return the count losts
	 */
	public long getCountLosts() {
		return countLosts;
	}
	
	/**
	 * Sets the count losts.
	 *
	 * @param countLosts the new count losts
	 */
	public void setCountLosts(long countLosts) {
		this.countLosts = countLosts;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Convert.
	 */
	public void convert() {
		this.name = WordCodeDecode.decodeWordWithSpecsToPolishWord(this.name);
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("id: %d > name: %s ", playerId, name);
	}

}
