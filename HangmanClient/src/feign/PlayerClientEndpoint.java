package feign;

import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import dto.PlayerDto;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerClientEndpoint.
 */
@ApplicationScoped
public class PlayerClientEndpoint implements IPlayerClientEndpoint {
	
	/** The client. */
	Client client = ClientBuilder.newClient();
	
	/** The uri. */
	URI uri = URI.create("http://localhost:8080/HangmanServer/ep/players");
	
	/** The web target. */
	WebTarget webTarget = client.target(uri);
	
	/** The g. */
	Gson g = new Gson();
	
	/**
	 * Instantiates a new player client endpoint.
	 */
	public PlayerClientEndpoint() {
		System.out.println("PlayerClientEndpoint created");
	}
	 
//	public boolean createGameReturnTrueIfGuess(String playerName, String opponentName) {
//		
//		System.out.println("HangmanClientEndpoint::createGameReturnTrueIfGuess");
//		WebTarget target = webTarget.path("game/"+playerName+"/"+opponentName);
//				
//		String response = target.request()
//				.accept(MediaType.TEXT_PLAIN).get(String.class);
//			
//		System.out.println("HangmanClientEndpoint::createGameReturnTrueIfGuess response = "+response);
//		
//		return "guess".equals(response);
//	}
/* (non-Javadoc)
 * @see feign.IPlayerClientEndpoint#getPlayerByName(java.lang.String)
 */
//	
	public PlayerDto getPlayerByName(String playerName) {
		System.out.println("HangmanClientEndpoint::getPlayerByName > "+playerName);
		WebTarget target = webTarget.path("byName/"+playerName);
		String response = target.request()
				.accept(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println("HangmanClientEndpoint::getPlayer response = "+response);
		PlayerDto p = g.fromJson(response, PlayerDto.class);
		return p;
	}
	
	/* (non-Javadoc)
	 * @see feign.IPlayerClientEndpoint#getPlayerById(long)
	 */
	public PlayerDto getPlayerById(long playerId) {
		System.out.println("HangmanClientEndpoint::getPlayerById > "+playerId);
		WebTarget target = webTarget.path("byId/"+playerId);
		String response = target.request()
				.accept(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println("HangmanClientEndpoint::getPlayer response = "+response);		
		PlayerDto p = g.fromJson(response, PlayerDto.class);
		System.out.println("HangmanClientEndpoint::getPlayer response = "+p.getName());
		
		return p;
	}
	


}
