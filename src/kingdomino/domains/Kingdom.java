package kingdomino.domains;

import java.util.Iterator;


/**
 * MESSAGE IMPORTANT POUR LES CTT !
 * -------------------------------- 
 * Normalement, je dois calculer les CTT du placement d'un domino dans un royaume dans cette classe, cependant, ma méthode de déplacement se situe dans KingDominoGame
 * parce que, pour moi, cette responsabilité était plus logique pour la classe KingDominoGame.
 * Je vous renvoie donc vers KingDominoGame -> placerDomino.
 * 
 * 
 * 
 * Choix de type pour le royaume : TABLEAU, pourquoi ?
 * Visuellement, je voyais le royaume plutôt sous la forme d'un tableau que d'une collection.
 * Je n'ai pas choisi une Liste parce que j'aurais dû faire une liste d'une liste pour moi, c'était bien plus pratique de 
 * travailler un seul tableau à deux dimensions qu'avec deux listes.
 * Je n'ai pas choisi de Map parce que la seule raison de travailler avec des Map c'est d'utiliser des clés et des valeurs,
 * ici toutes les valeurs dont j'avais besoin étaient déjà encodées dans mes différents objets (DrawLine, Domino, Tuile, Player), 
 * je n'avais plus qu'à placer un Domino dans mon tableau. 
 * 
 * Comparaison du Tableau avec d'autres méthodes : 
 * Je me sentais plus à l'aise d'utiliser un tableau, étant donné le fait qu'une List utilisant la méthode .add à une CTT de O(N), et que, une boucle parcourant un tableau à également une CTT de O(N), 
 * j'ai préféré prendre le tableau. Et, je peux tout autant faire un .size pour une liste qu'on .length pour un tableau.
 *
 *
 * Précondition du royaume :
 * Mon royaume ne peut pas recevoir de tuiles si sa position n'est pas correcte, cela signifie que le score du royaume sera toujours calculable et correcte.
 */
public class Kingdom {
	
	private Tile[][] kingdom;
	private Player player;
	private int score = -1;
	 
	
	/**
	 * 
	 * @param player, un joueur à qui le royaume appartient
	 * 
	 * Constructeur permettant de créer un royaume, sous forme de tableau, qui place le château aux bonnes coordonnées et qui rempli tout le 
	 * terrain autour du château par des parcelles vides.
	 */
	public Kingdom(Player player) {
		this.player = player;
		kingdom = new Tile[9][9];
		for (int i = 0; i < kingdom[0].length; i++) {
			for (int j = 0; j < kingdom.length; j++) {
				if (i == 4 && j == 4) {
					kingdom[i][j] = new Tile(Terrain.CASTLE, 0);
				} else {
					kingdom[i][j] = new Tile(Terrain.EMPTY, 0);
				}	
			}
		}
	}
	
	public Tile[][] getKingdom() {
		return kingdom;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public int getScore() {
		return score;
	}
	 
	/**
	 * 
	 * @param dominoAVerifier, le domino dont on doit vérifier les positions
	 * @return renvoie TRUE si les positions du domino à vérifier sont déjà des positions qui ont été stochées dans le royaume.
	 * 		   renvoie FALSE si les positions du domino à vérifier ne sont pas des positions existantes dans le royaume.
	 */
	public boolean estOccupe(Domino dominoAVerifier) {
		boolean estOccupe = false;
			if (kingdom[dominoAVerifier.getPart1().getLigne()][dominoAVerifier.getPart1().getColonne()].getTerritory() != Terrain.EMPTY || kingdom[dominoAVerifier.getPart2().getLigne()][dominoAVerifier.getPart2().getColonne()].getTerritory() != Terrain.EMPTY ) {
				estOccupe = true;
			}
		return estOccupe;
	}
	
	/**
	 * 
	 * @param dominoAVerifier, le domino dont on doit vérifier les positions
	 * @return renvoie TRUE si les positions du domino respectent les conditions de placage selon les règles du jeu, sinon renvoie FALSE.
	 */
	public boolean estPlacable(Tile tuile) {
		boolean estPlacable = false;
		
			if (adjacentKingdom(tuile)) {
				estPlacable = true;
			}
			if (voisinDeDroite(tuile)) {
				estPlacable = true;
			}
			if (voisinDeGauche(tuile)) {
				estPlacable = true;
			}
			if (voisinDuBas(tuile)) {
				estPlacable = true;
			}
			if (voisinDuHaut(tuile)) {
				estPlacable = true;
			}
			return estPlacable;
		}

	/**
	 * 
	 * @param dominoAVerifier, le domino dont on doit vériifier les positions
	 * @return TRUE si le domino est adjacent au tableau
	 */
	public boolean adjacentKingdom(Tile tuile) {
		boolean estPlacable = false;
		if (kingdom[tuile.getLigne()][tuile.getColonne()] != kingdom[4][4] &&
				((kingdom[tuile.getLigne()][tuile.getColonne()] == kingdom[4][3])
				|| (kingdom[tuile.getLigne()][tuile.getColonne()] == kingdom[3][4])
				|| (kingdom[tuile.getLigne()][tuile.getColonne()] == kingdom[4][5])
				|| (kingdom[tuile.getLigne()][tuile.getColonne()] == kingdom[5][4]))) {
			estPlacable = true;
		}
		return estPlacable;
	}
	
	/**
	 * 
	 * @param dominoAVerifier, le domino dont on doit vériifier les positions
	 * @return TRUE si le voisin de droite du domino est du même territoire.
	 */
	public boolean voisinDeDroite(Tile tuile) {
		boolean estPlacable = false;
		if (tuile.getColonne() + 1 > 8 ) {
			estPlacable = false;
		} else if (kingdom[tuile.getLigne()][tuile.getColonne() + 1].getTerritory() == tuile.getTerritory()) {
				estPlacable = true;
			}
		return estPlacable;
	}
	
	/**
	 * 
	 * @param dominoAVerifier, le domino dont on doit vériifier les positions
	 * @return TRUE si le voisin de gauche du domino est du même territoire.
	 */
	public boolean voisinDeGauche(Tile tuile) {
		boolean estPlacable = false;
		if (tuile.getColonne() - 1 < 0) {
			estPlacable = false;
		} else if (kingdom[tuile.getLigne()][tuile.getColonne() - 1].getTerritory() == tuile.getTerritory()) {
				estPlacable = true;
			}
		return estPlacable;
	}
	
	/**
	 * 
	 * @param dominoAVerifier, le domino dont on doit vériifier les positions
	 * @return TRUE si le voisin du haut du domino est du même territoire.
	 */
	public boolean voisinDuHaut(Tile tuile) {
		boolean estPlacable = false;
		if (tuile.getLigne() - 1 < 0) {
			estPlacable = false;
		} else if (kingdom[tuile.getLigne() - 1][tuile.getColonne()].getTerritory() == tuile.getTerritory()) {
				estPlacable = true;
			}
		return estPlacable;
	}
	
	/**
	 * 
	 * @param dominoAVerifier, le domino dont on doit vériifier les positions
	 * @return TRUE si le voisin du bas du domino est du même territoire.
	 */
	public boolean voisinDuBas(Tile tuile) {
		boolean estPlacable = false;
		if (tuile.getLigne() + 1 > 8) {
			estPlacable = false;
		} else if (kingdom[tuile.getLigne() + 1][tuile.getColonne()].getTerritory() == tuile.getTerritory()) {
				estPlacable = true;
			}
		return estPlacable;
	}
	
	
	/**
	 * PAS DE METHODE QUI STOCK LES TUILES DEJA VISITEES NI DE METHODE QUI STOCK LES TUILES A ETENDRE
	 * 
	 * Ici, je n'ai pas de collections pour ces deux méthodes, j'ai une seule méthode qui remplace les tuiles visitées par un terrain vide, et,
	 * la suite de la méthode qui vérifie chaque voisin afin de comptabiliser le nombre de couronnes et le nombre de tuile d'un même territoire voisin. 
	 * 
	 * CTT de la méthode :
	 * - Dans le pire des cas, la méthode effectuera UNE étape par méthode voisin
	 * - Donc dans le pire des cas, c'est UNE étape FOIS 4 ETAPES pour la méthode terrainVoisins.
	 * 
	 * @param tuile la tuile à vérifier
	 * @param score, un tableau, dont le premier élément est le nombre de tuiles d'un même territoire voisin, et le deuxième élément est le nombre de couronnes
	 * @return le tableau de int 
	 */
	public int[] terrainVoisins(Tile tuile, int[] score) {
		score[0]++;
		score[1] += tuile.getCrownsCount();
		kingdom[tuile.getLigne()][tuile.getColonne()] = new Tile(Terrain.EMPTY, 0);
		
		if (voisinDeDroite(tuile)) {
			terrainVoisins(kingdom[tuile.getLigne()][tuile.getColonne() + 1], score);
		}
		if (voisinDeGauche(tuile)) {
			terrainVoisins(kingdom[tuile.getLigne()][tuile.getColonne() - 1], score);
		}
		if (voisinDuHaut(tuile)) {
			terrainVoisins(kingdom[tuile.getLigne() - 1][tuile.getColonne()], score);
		}
		if (voisinDuBas(tuile)) {
			terrainVoisins(kingdom[tuile.getLigne() + 1][tuile.getColonne()], score);
		}
		
		return score;
	}
	
	/**
	 * Méthode qui permet de calculer le score final d'un joueur
	 * @return le score final d'un joueur
	 */
	public int scoreFinal() {
		if (score == -1) {
			score = 0;
			int[] tabScore = {0, 0};
			for (int i = 0; i < kingdom[0].length; i++) {
				for (int j = 0; j < kingdom.length; j++) {
					if (kingdom[i][j].getTerritory() != Terrain.EMPTY && kingdom[i][j].getTerritory() != Terrain.CASTLE) {
						terrainVoisins(kingdom[i][j], tabScore);
						score += tabScore[0] * tabScore[1];
						tabScore[0] = 0;
						tabScore[1] = 0;
					}
				}
			}
		}
		return score;
	}
}
