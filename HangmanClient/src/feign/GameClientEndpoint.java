package feign;

import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import dto.GameDto;

// TODO: Auto-generated Javadoc
/**
 * The Class GameClientEndpoint.
 */
@ApplicationScoped
public class GameClientEndpoint implements IGameClientEndpoint {

	/** The client. */
	Client client = ClientBuilder.newClient();
	
	/** The uri. */
	URI uri = URI.create("http://localhost:8080/HangmanServer/ep/game");
	
	/** The web target. */
	WebTarget webTarget = client.target(uri);
	
	/** The g. */
	Gson g = new Gson();

	/**
	 * Instantiates a new game client endpoint.
	 */
	public GameClientEndpoint() {
		System.out.println("GameClientEndpoint created");
	}
//	
//	public boolean createGameReturnTrueIfGuess(String playerName, String opponentName) {
//		System.out.println("GameClientEndpoint::createGameReturnTrueIfGuess");
//		WebTarget target = webTarget.path(playerName+"/"+opponentName);
//				
//		String response = target.request()
//				.accept(MediaType.TEXT_PLAIN).get(String.class);
//			
//		System.out.println("GameClientEndpoint::createGameReturnTrueIfGuess response = "+response);
//		
//		return "guess".equals(response);
//	}

	/* (non-Javadoc)
 * @see feign.IGameClientEndpoint#createGameReturnTrueIfGuess(long, long)
 */
public boolean createGameReturnTrueIfGuess(long playerId, long opponentId) {
		System.out.println("GameClientEndpoint::createGameReturnTrueIfGuess");
		WebTarget target = webTarget.path(playerId + "/" + opponentId);

		String response = target.request().accept(MediaType.TEXT_PLAIN).get(String.class);

		System.out.println("GameClientEndpoint::createGameReturnTrueIfGuess response = " + response);

		return "guess".equals(response);
	}
	
//	public void endGame(long playerId) {
//		System.out.println("GameClientEndpoint::endGame");
//		WebTarget target = webTarget.path("endGame/"+playerId);
//
//		String response = target.request().accept(MediaType.TEXT_PLAIN).get(String.class);
//
//		System.out.println("GameClientEndpoint::createGameReturnTrueIfGuess response = " + response);
//		
//	}

//	public GameDto sendLetter(String playerName, String letter) {
//		System.out.println("GameClientEndpoint::sendLetter "+playerName+" > "+letter);
//		WebTarget target = webTarget.path("sendLetter/"+playerName+"/"+letter);
//		String response = target.request()
//				.accept(MediaType.TEXT_PLAIN).get(String.class);
//		System.out.println("HangmanClientEndpoint::game/sendLetter response = "+response);
//		
//		GameDto p = g.fromJson(response, GameDto.class);
//			
//		System.out.println("HangmanClientEndpoint::GameDto response.usedletters = "+p.getUsedLetters());
//		
//		return p;
//	}

	/* (non-Javadoc)
 * @see feign.IGameClientEndpoint#sendLetter(long, java.lang.String)
 */
public GameDto sendLetter(long playerId, String letter) {
		System.out.println("GameClientEndpoint::sendLetter " + playerId + " > " + letter);
		WebTarget target = webTarget.path("sendLetter/" + playerId + "/" + letter);
		String response = target.request().accept(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println("HangmanClientEndpoint::game/sendLetter response = " + response);

		GameDto p = g.fromJson(response, GameDto.class);

		System.out.println("HangmanClientEndpoint::GameDto response.usedletters = " + p.getUsedLetters());

		return p;
	}

//	public GameDto updateWord(String playerName, String word) {
//		System.out.println("GameClientEndpoint::updateWord "+playerName+" > "+word);
//		
//		WebTarget target = webTarget.path("updateWord/"+playerName+"/"+word);
//		String response = target.request()
//				.accept(MediaType.TEXT_PLAIN).get(String.class);
//		System.out.println("HangmanClientEndpoint::game/sendLetter response = "+response);
//		
//		GameDto p = g.fromJson(response, GameDto.class);
//			
//		System.out.println("HangmanClientEndpoint::GameDto response.word = "+p.getTheWord());
//		
//		return p;
//	}

	/* (non-Javadoc)
 * @see feign.IGameClientEndpoint#updateWord(long, java.lang.String)
 */
public GameDto updateWord(long playerId, String word) {
		System.out.println("GameClientEndpoint::updateWord " + playerId + " > " + word);

		WebTarget target = webTarget.path("updateWord/" + playerId + "/" + word);
		String response = target.request().accept(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println("HangmanClientEndpoint::game/sendLetter response = " + response);

		GameDto p = g.fromJson(response, GameDto.class);

		System.out.println("HangmanClientEndpoint::GameDto response.word = " + p.getTheWord());

		return p;
	}

	/* (non-Javadoc)
	 * @see feign.IGameClientEndpoint#getGame(long)
	 */
	public GameDto getGame(long playerId) {

		System.out.println("GameClientEndpoint::getGame(EP=gameByPlayerId) playerId=" + playerId);
		WebTarget target = webTarget.path("gameByPlayerId/" + playerId);

		try {
			String response = target.request().accept(MediaType.TEXT_PLAIN).get(String.class);
			System.out.println("HangmanClientEndpoint::getGame response = " + response);
			GameDto p = g.fromJson(response, GameDto.class);
			System.out.println("HangmanClientEndpoint::GameDto response = " + p.getGameStatus());
			return p;
		} catch (javax.ws.rs.NotFoundException e) {
			return null;
		}

	}

}
