package servlet;

import static java.util.Optional.ofNullable;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.GameDto;
import dto.PlayerDto;
import feign.IGameClientEndpoint;
import feign.IPlayerClientEndpoint;
import utils.WordCodeDecode;

// TODO: Auto-generated Javadoc
/**
 * The Class PageDispacherServlet.
 */
public class PageDispacherServlet extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant OPERATION_SET_PLAYER_NAME. */
	private static final String OPERATION_SET_PLAYER_NAME = "setPlayerName";
	
	/** The Constant OPERATION_START_GAME. */
	private static final String OPERATION_START_GAME = "playGame";
	
	/** The Constant OPERATION_DISCONNECT. */
	private static final String OPERATION_DISCONNECT = "disconnect";
	
	/** The Constant OPERATION_SEND_LETTER. */
	private static final String OPERATION_SEND_LETTER = "letter";

/** The Constant OPERATION_UPDATE_WORD. */
//	private static final String OPERATION_WORD_UPDATED = "word_updated";
	private static final String OPERATION_UPDATE_WORD = "update_word";
	
	/** The Constant OPERATION_GOTO_PAGE. */
	private static final String OPERATION_GOTO_PAGE = "goto_page";
//	private static final String OPERATION_END_GAME = "end_game";

	/** The Constant PAGE_INDEX. */
private static final String PAGE_INDEX = "index";
	
	/** The Constant PAGE_LIST. */
	private static final String PAGE_LIST = "list";
	
	/** The Constant PAGE_WORD. */
	private static final String PAGE_WORD = "word";
	
	/** The Constant PAGE_GUESS. */
	private static final String PAGE_GUESS = "guess";

	/** The Constant ATTR_NAME_PLAYERNAME. */
	private static final String ATTR_NAME_PLAYERNAME = "username";
	
	/** The Constant ATTR_NAME_PLAYER. */
	private static final String ATTR_NAME_PLAYER = "player";
	
	/** The Constant ATTR_NAME_GAME. */
	private static final String ATTR_NAME_GAME = "game";

	/** The player client endpoint. */
	@Inject
	private IPlayerClientEndpoint playerClientEndpoint;

	/** The game client endpoint. */
	@Inject
	private IGameClientEndpoint gameClientEndpoint;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("HttpServlet::doPost");

		String operation = request.getParameter("operation");
		String data = decode(request.getParameter("data"));
		String username = request.getParameter("username");

		String nextPage = currentPageJsp(request);
		boolean setGameAttr = true;
		boolean setPlayerAttr = true;
		long playerId = getPlayerId(request);
		System.out.println(String.format("operation: %s, data: %s, username: %s", operation, data, username));

		if (OPERATION_SET_PLAYER_NAME.equals(operation)) {
			username = decode(data);
			System.out.println("HttpServlet::doPost > OPERATION_SET_PLAYER_NAME: " + username);
			request.setAttribute(ATTR_NAME_PLAYERNAME, username);
			playerId = getPlayerId(request, username);
			setPlayerAttr = false;
			nextPage = pageJsp(PAGE_LIST);
		} else {
			request.setAttribute(ATTR_NAME_PLAYERNAME, getUserName(request));
			if (OPERATION_DISCONNECT.equals(operation)) {
				request.setAttribute(ATTR_NAME_PLAYERNAME, null);
				request.setAttribute(ATTR_NAME_GAME, null);
				setGameAttr = false;
				setPlayerAttr = false;
				nextPage = pageJsp(PAGE_INDEX);
			} else if (OPERATION_START_GAME.equals(operation)) {
				nextPage = pageJsp(getPlayerPage(playerId, Long.valueOf(data)));
			} else if (OPERATION_SEND_LETTER.equals(operation)) {
				sendLetter(request, playerId, data);
				setGameAttr = false;
			} else if (OPERATION_UPDATE_WORD.equals(operation)) {
				updateWord(request, playerId, data);
				setGameAttr = false;
			} else if (OPERATION_GOTO_PAGE.equals(operation)) {
//				if (PAGE_LIST.equals(data)) { endGame(playerId); }
				nextPage = pageJsp(data);
			}
//			else if (OPERATION_END_GAME.equals(operation)) {
//				endGame(playerId);
//				nextPage = pageJsp(PAGE_LIST);
//			}				
		}

		if (playerId != -1) {
			if (setPlayerAttr) {
				setPlayerAttribute(request, playerId);
			}
			if (setGameAttr) {
				setGameAttribute(request, playerId);
			}
		}

		forwardToPage(nextPage, request, response);
	}

	/**
	 * Decode.
	 *
	 * @param data the data
	 * @return the string
	 */
	private String decode(String data) {
		if (data != null && !data.isEmpty()) {
			return WordCodeDecode.decode(WordCodeDecode.decodeWordWithSpecsToPolishWord(data));
		}
		return "";
	}

	/**
	 * Gets the player id.
	 *
	 * @param request the request
	 * @return the player id
	 */
	private long getPlayerId(HttpServletRequest request) {
		return (request.getParameter("playerId") != null) ? Long.valueOf(request.getParameter("playerId")) : -1;
	}

	/**
	 * Gets the player id.
	 *
	 * @param request the request
	 * @param username the username
	 * @return the player id
	 */
	private long getPlayerId(HttpServletRequest request, String username) {
		PlayerDto pdto = playerClientEndpoint.getPlayerByName(username);
		if (pdto != null) {
			request.setAttribute(ATTR_NAME_PLAYER, pdto);
			return pdto.getPlayerId();
		} else
			return -1;
	}

	/**
	 * Gets the user name.
	 *
	 * @param request the request
	 * @return the user name
	 */
	private String getUserName(HttpServletRequest request) {
		return decode(request.getParameter("username"));
	}

	/**
	 * Sets the player attribute.
	 *
	 * @param request the request
	 * @param playerId the player id
	 */
	private void setPlayerAttribute(HttpServletRequest request, long playerId) {
		System.out.println("HttpServlet::setPlayerAttribute");
		PlayerDto pdto = playerClientEndpoint.getPlayerById(playerId);
		if (pdto != null) {
			System.out.println("setPlayerAttribute:PlayerDto = " + pdto);
			request.setAttribute(ATTR_NAME_PLAYER, pdto);
		}
	}

	/**
	 * Sets the game attribute.
	 *
	 * @param request the request
	 * @param playerId the player id
	 */
	private void setGameAttribute(HttpServletRequest request, long playerId) {
		System.out.println("HttpServlet::setGameAttribute");
		GameDto gdto = gameClientEndpoint.getGame(playerId);
		if (gdto != null) {
			System.out.println("setGameAttribute:GameDto = " + gdto);
			request.setAttribute(ATTR_NAME_GAME, gdto);
		}
	}

	/**
	 * Forward to page.
	 *
	 * @param pageNameJsp the page name jsp
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void forwardToPage(String pageNameJsp, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("HttpServlet::forwardToPage > " + pageNameJsp);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(pageNameJsp);
		requestDispatcher.forward(request, response);
	}

	/**
	 * Send to server play game return if guess.
	 *
	 * @param playerId the player id
	 * @param opponentId the opponent id
	 * @return true, if successful
	 */
	private boolean sendToServerPlayGameReturnIfGuess(long playerId, long opponentId) {
		System.out.println("HttpServlet::sendToServerPlayGameReturnIfGuess > " + playerId + " vs " + opponentId);
		return gameClientEndpoint.createGameReturnTrueIfGuess(playerId, opponentId);
	}

	/**
	 * Gets the player page.
	 *
	 * @param playerId the player id
	 * @param opponentId the opponent id
	 * @return the player page
	 */
	private String getPlayerPage(long playerId, long opponentId) {
		if (sendToServerPlayGameReturnIfGuess(playerId, opponentId)) {
			return PAGE_GUESS;
		} else {
			return PAGE_WORD;
		}
	}

	/**
	 * Send letter.
	 *
	 * @param request the request
	 * @param playerId the player id
	 * @param letter the letter
	 */
	private void sendLetter(HttpServletRequest request, long playerId, String letter) {
		System.out.println("HttpServlet::sendLetter > " + playerId + " > " + letter);
		GameDto game = gameClientEndpoint.sendLetter(playerId, letter);
		if (game != null) {
			request.setAttribute(ATTR_NAME_GAME, game);
		}
	}

//	private void endGame(long playerId) {
//		System.out.println("HttpServlet::endGame");
//		gameClientEndpoint.endGame(playerId);
//	} 

	/**
 * Update word.
 *
 * @param request the request
 * @param playerId the player id
 * @param word the word
 */
private void updateWord(HttpServletRequest request, long playerId, String word) {
		System.out.println("HttpServlet::sendLetter > " + playerId + " > " + word);
		GameDto game = gameClientEndpoint.updateWord(playerId, word);
		if (game != null) {
			request.setAttribute(ATTR_NAME_GAME, game);
		}
	}

	/**
	 * Page jsp.
	 *
	 * @param pageName the page name
	 * @return the string
	 */
	private String pageJsp(String pageName) {
		return pageName.concat(".jsp");
	}

	/**
	 * Current page jsp.
	 *
	 * @param request the request
	 * @return the string
	 */
	private String currentPageJsp(HttpServletRequest request) {
		return pageJsp(ofNullable(request.getParameter("currentPage")).orElse(""));
	}

}
