package kingdomino.domains;

import java.util.*;



/**
 * 
 * @author N.Claus
 *
 *Condition de fin d'une partie :
 *	- La pile de domino doit être vide
 *	- Les derniers domino de la dernière drawLine doivent avoir été sélecionner par les joueurs
 *	- Les derniers domino de la dernière drawLine doivent avoir été retiré de la drawLine actuelle 
 *	(soit ils ont été posé dans le royaume du joueur ayant sélectionner le domino, 
 *	soit il est simplement retiré car le joueur ne pouvait pas le poser).
 *	- Tous les joueurs ont joué leur dernier tour.
 */
public class KingDominoGame {

	private DominoPile dominoPile;
	private DrawLine drawLine;
	private DrawLine nextDrawLine;
	private List<Domino> maDrawLine;
	private List<Player> players;
	private Domino currentDomino;
	private Kingdom monRoyaume;
	private Player currentPlayer;
	
	/**
	 * 
	 * @param gameFactory, l'ensemble des domino du jeu et des joueurs qui vont participer à la partie.
	 * 
	 * On initialise tous les éléments dont on aura besoin dans PlayGameSuperviser
	 */
	public KingDominoGame(KingDominoFactory gameFactory) {
		this.dominoPile = new DominoPile(gameFactory.getAllDominoes() ,gameFactory.getNumberOfPlayer());
		this.drawLine = new DrawLine(this.dominoPile, gameFactory.getNumberOfPlayer());
		this.nextDrawLine = new DrawLine(this.dominoPile, gameFactory.getNumberOfPlayer());
		this.players = new ArrayList<Player>();
		if (gameFactory.getNumberOfPlayer() == 2) { 
			for (int i = 0; i < 4; i++) {
				this.players.add(gameFactory.getTwoPlayer().get(i));
			}
		} else {
			for (int i = 0; i < gameFactory.getNumberOfPlayer(); i++) {
				this.players.add(gameFactory.getAllPlayers().get(i));
			}
		}
		
		Collections.shuffle(players);
		maDrawLine = drawLine.getDrawLine();
		drawLine.associationJoueur(players);
		currentDomino = maDrawLine.get(0);
		this.monRoyaume = new Kingdom(players.get(0));
		currentDomino.getPart1().setColonne(0);
		currentDomino.getPart1().setLigne(0);
		currentDomino.getPart2().setColonne(1);
		currentDomino.getPart2().setLigne(0);	
		miseAJour();
	}

	public DrawLine getCurrentDrawLine() {
		return drawLine;
	}
	
	public DrawLine getNextDrawLine() {
		return nextDrawLine;
	}
	
	public Domino getCurrentDomino() {
		return currentDomino; 
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer; 
	}
	 
	public Kingdom getMonRoyaume() {
		return monRoyaume;
	}
	
	public List<Player> getPlayerSort() {
		List<Player> joueurMisAJour = new ArrayList<Player>();
		for (Player player : players) {
			if (!joueurMisAJour.contains(player)) {
				joueurMisAJour.add(player);
			}
		}
		Collections.sort(joueurMisAJour);
		return joueurMisAJour;
	}
	
	/**
	 * Méthode permettant de faire la mise à jour des éléments en cours, c'est à dire, de mettre à jour le joueur actuel, le domino actuel et le royaume actuel.
	 */
	public void miseAJour() {
		currentPlayer = drawLine.getPlayer().get(0);
		currentDomino = drawLine.getDrawLine().get(0);
		monRoyaume = currentPlayer.getRoyaume();
		currentDomino.getPart1().setLigne(0);
		currentDomino.getPart1().setColonne(0);
		currentDomino.getPart2().setLigne(0);
		currentDomino.getPart2().setColonne(1);
	}
	
	/**
	 * 
	 * @param gameFactory, un objet de la classe KingDominoFactory
	 * 
	 * Méthode permettant de passer au joueur suivant dans ma liste de joueurs.
	 */
	public void joueurSuivant(KingDominoFactory gameFactory) {
		drawLine.removeElement();
		if (drawLine.getDrawLine().size() == 0) {
			getNewDrawLine(gameFactory);
		}
		miseAJour();
	}
	
	/**
	 * Méthode permettant de passer son tour.
	 */
	public void onPass() {
		monRoyaume = currentPlayer.getRoyaume();
		drawLine.removeElement();
	}
	
	/**
	 * 
	 * @param dr, la différence de ligne
	 * @param dc, la différence de colonne 
	 * @param currentDomino, le domino actuel
	 * 
	 * Méthode permettant de déplacer le domino dans toutes les directions linéaires possibles
	 */
	public void deplacementTuile(int dr, int dc, Domino currentDomino) {
		if (dr == 1) {
			currentDomino.getPart1().setLigne(currentDomino.getPart1().getLigne() + 1);
			currentDomino.getPart2().setLigne(currentDomino.getPart2().getLigne() + 1);
		} 
		if (dr == -1) {
			currentDomino.getPart1().setLigne(currentDomino.getPart1().getLigne() - 1);
			currentDomino.getPart2().setLigne(currentDomino.getPart2().getLigne() - 1);
		} 
		if (dc == 1) {
			currentDomino.getPart1().setColonne(currentDomino.getPart1().getColonne() + 1);
			currentDomino.getPart2().setColonne(currentDomino.getPart2().getColonne() + 1);
		} 
		if (dc == -1) {
			currentDomino.getPart1().setColonne(currentDomino.getPart1().getColonne() - 1);
			currentDomino.getPart2().setColonne(currentDomino.getPart2().getColonne() - 1);
		}
	}
	
	/**
	 * 
	 * @param currentDomino, le domino actuel
	 * 
	 * Méthode permettant d'effectuer une rotation du domino actuel
	 */
	public void rotationDomino(Domino currentDomino) {
		if (currentDomino.getPart2().getColonne() > currentDomino.getPart1().getColonne()) {
			currentDomino.getPart2().setLigne(currentDomino.getPart2().getLigne() + 1);
			currentDomino.getPart2().setColonne(currentDomino.getPart2().getColonne() - 1);
			
		} else if (currentDomino.getPart2().getLigne() > currentDomino.getPart1().getLigne()) {
			currentDomino.getPart2().setLigne(currentDomino.getPart2().getLigne() - 1);
			currentDomino.getPart2().setColonne(currentDomino.getPart2().getColonne() - 1);
			
		} else if (currentDomino.getPart2().getColonne() < currentDomino.getPart1().getColonne()) {
			currentDomino.getPart2().setLigne(currentDomino.getPart2().getLigne() - 1); 
			currentDomino.getPart2().setColonne(currentDomino.getPart2().getColonne() + 1);
			
		} else if (currentDomino.getPart2().getLigne() < currentDomino.getPart1().getLigne()) {
			currentDomino.getPart2().setLigne(currentDomino.getPart2().getLigne() + 1);
			currentDomino.getPart2().setColonne(currentDomino.getPart2().getColonne() + 1);
		}
		
	}
	
	/**
	 * 
	 * @param currentDomino, le domino actuel
	 * 
	 * Méthode permettant de placer un domino au sein du royaume
	 * 
	 * CTT du placement d'un domino
	 * ----------------------------
	 * - Méthode estOccupe, qui permet de savoir si les positions d'un royaume sont occupées dans laquelle il y a deux boucles imbriquées donc O(N^2).
	 */
	public boolean placerDomino(Domino currentDomino) {
		if (monRoyaume.estOccupe(currentDomino) == false && (monRoyaume.estPlacable(currentDomino.getPart1()) == true || monRoyaume.estPlacable(currentDomino.getPart2()) == true)) {
			monRoyaume.getKingdom()[currentDomino.getPart1().getLigne()][currentDomino.getPart1().getColonne()] = currentDomino.getPart1();
			monRoyaume.getKingdom()[currentDomino.getPart2().getLigne()][currentDomino.getPart2().getColonne()] = currentDomino.getPart2();
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param pos, un int représentant une position dans une liste
	 * @return renvoie la position du joueur, sauf si la position est égal à la taille de la liste de joueurs, alors là, la position retombe à 0.
	 */
	public int positionSuivante(int pos) {
			pos++;
			if (pos == players.size()) {
				pos = 0;
			}
		return pos;
		
	}
	
	public void getNewDrawLine(KingDominoFactory gameFactory) {
		if (nextDrawLine.getDrawLine().size() != 0 &&  nextDrawLine != null) {
			drawLine = nextDrawLine;
			if (dominoPile.getPile().size() == 0) {
				nextDrawLine = null;
			} else {
				nextDrawLine = new DrawLine(dominoPile, gameFactory.getNumberOfPlayer());
			}
			
			miseAJour();
		}
	}
	
}