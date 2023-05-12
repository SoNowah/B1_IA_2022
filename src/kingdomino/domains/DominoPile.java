package kingdomino.domains;

import java.util.*;



/**
 * 
 * @author N.Claus
 *
 *Choix de mon type de collection : Set. Pour moi ce type de collection était le plus simple à utiliser parce que je n'ai pas besoin de connaitre l'ordre des dominos
 *vu qu'on les mélange. Le type Set n'autorise pas de doublons,exactement ce que l'on veut car il n'y a pas deux dominos identiques. De plus, elle adapte sa taille au contenu
 *donc au nombre de dominos par rapport au nombre de joueurs.
 *
 *Implémentation de collection : HashSet, pourquoi ?
 *Ici, la construction de la pile de domino ne demande que très peu d'étapes, une fonction tirage qui ne nécessite pas de collection et une autre fonction
 *getPile dans laquelle je fais une simple copie de ma pile déjà faite.
 *Le constructeur nécessite l'utilisation d'une list parce que j'avais besoin de la méthode .subList pour séléctionner facilement le nombre de domino nécessaires
 *pour jouer. 
 */

public class DominoPile {

	private HashSet<Domino> pile = null;
	
	/**
	 * 
	 * @param fullPile la pile complète des 48 dominos
	 * @param nbPlayer le nombre de joueurs pour la partie
	 * 
	 * Ici,on prend un certain nombre de cartes en fonctions du nombre de joueur pouor former la pile de domino.
	 */
	public DominoPile(List<Domino> fullPile, int nbPlayer) {
		if (fullPile == null) {
			this.pile = null;
		} else {
			Collections.shuffle(fullPile);
			switch (nbPlayer) {
			case 2: // On enlève 24 cartes
				 fullPile = fullPile.subList(0, 24);
				break;
				
			case 3: // On enlève 12 cartes
				fullPile = fullPile.subList(0, 36);
				break;
				
			case 4: // On enlève aucune carte
				break;

			default:
				this.pile = null;
				break;
			}
			
			if(fullPile != null) {
				pile = new HashSet<Domino>(fullPile);
			}
			
		}
		
	}
	
	/**
	 * Méthode de tirage qui retire le domino stocker dans la variable "tire".
	 * @return un domino de la pile de domino.
	 */
	public Domino tirage() {
		Domino tire = pile.iterator().next();
		pile.remove(tire);
		return tire;
	}
	
	public HashSet<Domino> getPile() {
		if (pile == null) { 
			return null;
		} else {
			HashSet<Domino> copie = new HashSet<Domino>(pile);
			return copie;
		}
		
	}
}