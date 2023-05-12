package kingdomino.supervisers;

import java.util.Iterator;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import kingdomino.domains.*;
import kingdomino.supervisers.PlayGameView.ViewMode;

public class PlayGameSuperviser extends Superviser {
	private PlayGameView view;
	private KingDominoFactory gameFactory;
	private KingDominoGame dominoGame;
	private DrawLine drawLine;
	private DrawLine nextDrawLine;
	private Player player;
	private int position;

	public PlayGameSuperviser(KingDominoFactory gameFactory) {
		this.gameFactory = gameFactory; 
	}

	public void setView(PlayGameView view) {
		this.view = view; 	
	}

	/**
	 * La pile de domino doit contenir le bon nombre de dominos par rapport au nombre de joueurs. Elle doit ensuite avoir été mélangée et 4 dominos doivent être 
	 * tirés hors de cette pile pour former la drawline.
	 * 
	 * La ligne de tirage actuelle doit être tirée de la pile de domino et elle doit être triée du plus petit id de domino au plus grand.
	 * 
	 * La ligne de tirage suivante doit être tirée de la pile de domino et elle doit être triée du plus petit id de domino au plus grand.
	 * 
	 * Le royaume du joueur qui a la main doit être vide.
	 * 
	 * CTT DU TIRAGE DE N DOMINO
	 * -------------------------
	 * - Une boucle for O(N) : si le nombre de domino est nul, on rempli la drawLine de null.
	 * - A l'intérieur de cette boucle , une méthode .add O(N) : valeur NULL ajouté à la drawLine.
	 * - Une boucle for O(M) : nombre domino tirés pour la ligne de tirage
	 * - A l'intérieur de cette boucle, une méthode .add O(P) : Dominos ajouté à la ligne de tirage
	 * - A l'intérieur de cette méthode .add, une méthode .remove O(M) : Dominos retirés de la pile de domino
	 * - Enfin une méthode de tri est appelée dans lequelle on fait  un .sort O(P) : Domino de la drawLine trié par ordre croissant
	 * 
	 * Ce qui nous donne comme CTT : O(N^2 * M^2 * P^2)
	 */
	@Override
	public void onEnter(String fromScreen) {
		//TODO : créer le jeu quand l'application vient du menu principal
		view.switchMode(ViewMode.KINGDOM);
		dominoGame = new KingDominoGame(gameFactory);
		drawLine = dominoGame.getCurrentDrawLine();
		draw();
	}

	private void draw() {
		//TODO: remplacer le rendu d'exemple par le rendu réel
		view.startDraw();
		drawKingdom();
		drawCurrentDominoOnKingdom();
		drawNextLine();
		drawActualLine();
		view.endDraw();
	}

	private void drawKingdom() {
		//TODO : à remplacer par le rendu du royaume du joueur qui prend la main
		//Les coordonnées des tuiles du royaume sont centrées sur le chateau.
		//Pour éviter des coordonées négative, on fait une translation
		//Ici, le chateau occupe le centre du grille de 10x10 cases
		view.addToKingdom(toString(Terrain.CASTLE), 0, 4, 4);
		//Verif toute le grille et met Terrain.EMPTY si l'espace est vide.
		for (int i = 0; i < dominoGame.getMonRoyaume().getKingdom()[1].length; i++) {
			for (int j = 0; j < dominoGame.getMonRoyaume().getKingdom().length; j++) {
				view.addToKingdom(toString(dominoGame.getMonRoyaume().getKingdom()[i][j].getTerritory()), dominoGame.getMonRoyaume().getKingdom()[i][j].getCrownsCount(), i, j);
			}
		}
		
	}
	
	private void drawCurrentDominoOnKingdom() {
		//TODO : à remplacer par le rendu du domino courant
		view.addToKingdom(toString(dominoGame.getCurrentDomino().getPart1().getTerritory()), dominoGame.getCurrentDomino().getPart1().getCrownsCount(), dominoGame.getCurrentDomino().getPart1().getLigne(), dominoGame.getCurrentDomino().getPart1().getColonne(), dominoGame.getCurrentPlayer().getHexColor());
		view.addToKingdom(toString(dominoGame.getCurrentDomino().getPart2().getTerritory()), dominoGame.getCurrentDomino().getPart2().getCrownsCount(), dominoGame.getCurrentDomino().getPart2().getLigne(), dominoGame.getCurrentDomino().getPart2().getColonne(),dominoGame.getCurrentPlayer().getHexColor());
	}

	private void drawActualLine() {
		//TODO : à remplacer par le rendu de la ligne courante
		drawLine = dominoGame.getCurrentDrawLine();
		List<Domino> listeDomino = drawLine.getDrawLine();
		List<Player> listePlayer = drawLine.getPlayer();
		for (int i = 0; i < listeDomino.size(); i++) {
			view.addToActualLine(toString(listeDomino.get(i).getPart1().getTerritory()), listeDomino.get(i).getPart1().getCrownsCount(), toString(listeDomino.get(i).getPart2().getTerritory()), listeDomino.get(i).getPart2().getCrownsCount(), listePlayer.get(i).getHexColor());
		}
		view.addMessage("C'est au tour du joueur " + listePlayer.get(0).getName() + " de jouer.");
	}

	private void drawNextLine() {
		//TODO : à remplacer par le rendu de la ligne suivante
		String couleur = "#ffffff";
		nextDrawLine = dominoGame.getNextDrawLine();
		if (nextDrawLine == null) {
			
		} else {
			List<Domino> listeDomino = nextDrawLine.getDrawLine();
			List<Player> listePlayer = nextDrawLine.getPlayer();
			for (int i = 0; i < listeDomino.size(); i++) {
				if (position == i) {
					couleur = dominoGame.getCurrentPlayer().getHexColor();
				}
				else if(listePlayer.get(i) != null) {
					couleur = listePlayer.get(i).getHexColor();
				} else {
					couleur = "#ffffff";
				}
				view.addToNextLine(toString(listeDomino.get(i).getPart1().getTerritory()), listeDomino.get(i).getPart1().getCrownsCount(), toString(listeDomino.get(i).getPart2().getTerritory()), listeDomino.get(i).getPart2().getCrownsCount(), couleur);
			}
		}
	}
	
	private String toString(Terrain t) {
		return t.toString().toLowerCase();
	}

	public void onMove(int dr, int dc) {
		//TODO : Gérer les déplacement du domino courant, si ce dernier n'a pas été posé
		dominoGame.deplacementTuile(dr, dc, dominoGame.getCurrentDomino() );
		draw();
	}

	public void onRotate() {
		//TODO : Faire pivoter le domino courant de 90°, si ce dernier n'a pas été posé
		dominoGame.rotationDomino(dominoGame.getCurrentDomino()); 
		draw();
	}

	public void onConfirm() {
		//TODO : vérifier que le domino est affectable au royaume au coordonnée courantes
		//TODO : affecter le domino au royaume
		if (dominoGame.placerDomino(dominoGame.getCurrentDomino())) {
			view.switchMode(ViewMode.LINES);
			position = 0;
		}
		draw();
	}

	public void onSelectNextPiece() {
		view.addMessage("onSelectNextPiece");
		//TODO : choisir le domino suivant, si le domino courant a été posé
		position = dominoGame.positionSuivante(position);
		draw();
	}
	
	public void onPieceSelected() {
		//TODO : passer au joueur suivant, si le domino courant a été posé
		//TODO : décidé de la fin de la partie
		if (nextDrawLine == null && drawLine.getDrawLine().size() == 1 ) {
			gameFactory.setDominoGame(dominoGame);
			view.addMessage("Fin de la partie");
			view.goTo("GameOver");
		}
		player = dominoGame.getCurrentPlayer();
		if (nextDrawLine == null) {
			dominoGame.joueurSuivant(gameFactory);
			view.switchMode(ViewMode.KINGDOM);
		} else if (nextDrawLine.associationJoueurNextDrawLine(position, player)) {
			dominoGame.joueurSuivant(gameFactory);
			view.switchMode(ViewMode.KINGDOM);
			drawLine = dominoGame.getCurrentDrawLine();
			if (drawLine.getDrawLine().size() == 0) {
				dominoGame.getNewDrawLine(gameFactory);
			}
		} else {
			view.addMessage("Veuillez sélectionner un autre domino");
		}
		draw();
	}

	public void onPass() {
		// TODO Auto-generated method stub
		view.switchMode(ViewMode.LINES);
		position = 0;
	}
	
	
}
