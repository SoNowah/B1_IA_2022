package kingdomino.domains;

import java.util.*;

public class KingDominoFactory {

	
	private List<Domino> allDominoes;
	private List<Player> allPlayers;
	private int numberOfPlayer;
	private KingDominoGame dominoGame;
	/**
	 * 
	 * @param dominoes, la liste de domino avec lesquels on va jouer
	 * @param players, la liste des joueurs qui vont jouer
	 * 
	 */
	public KingDominoFactory(List<Domino> dominoes, List<Player> players) {
		allDominoes = new ArrayList<>(dominoes);
		allPlayers = new ArrayList<>(players);
		numberOfPlayer = allPlayers.size();
	}
	
	public int getNumberOfPlayer() {
		return numberOfPlayer;
	}

	public void setNumberOfPlayer(int numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
	}
	public void setDominoGame(KingDominoGame dominoGame) {
		this.dominoGame = dominoGame;
	}
	
	public KingDominoGame getDominoGame() {
		return dominoGame;
	}
	
	public List<Domino> getAllDominoes() {
		return new ArrayList<Domino>(allDominoes);
	}
	
	public List<Player> getAllPlayers() {
		return new ArrayList<Player>(allPlayers);
	}
	
	public List<Player> getTwoPlayer() {
		List<Player> copie = new ArrayList<Player>(allPlayers);
		if (numberOfPlayer == 2) {
			copie.set(2, allPlayers.get(0));
			copie.set(3, allPlayers.get(1));
		}
		return copie;
	}
	
}
