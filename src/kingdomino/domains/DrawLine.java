package kingdomino.domains;

import java.util.*;
import kingdomino.domains.DominoPile;

/**
 * 
 * Interface de collection choisie : List parce qu'elle permet une grande liberté d'opérations et d'ajout d'éléments à l'intérieur de cette dernière. On peut facilement
 * naviguer entre les éléments de la List. Utiliser la collection List m'a permis d'associer aisément un domino à un joueur.
 * 
 * Implémentation de collection choisie : ArrayList, pourquoi ?
 * La seule méthode que j'utilise ici est la méthode .add, qui ici est en O(N) puisque je l'utilise dans une boucle.
 * N'utilisant pas d'autres méthodes spécifiques aux Listes ici, j'ai choisi d'utiliser une ArrayList à la place d'une LinkedList parce que je peux directement
 * accéder à un élément sur base de son index. Alors qu'avec une LinkedList, j'aurais du parcourir toute la liste pour séléctionner un élément.
 * De plus il est plus intéressant d'utiliser une ArrayList lorsque l'on souhaite stocker une liste de petite taille.
 * 
 * Choix de collection pour mémoriser les lignes de tirage
 * -------------------------------------------------------
 * Interface de collection choisie : Je n'ai pas changé de collection pour mes lignes de tirage, j'ai conservé la liste parce qu'elle me permet facilement d'accéder à l'index du domino que je souhaite
 * et , pour la prochaine ligne de tirage, je peux facilement associé le joueur qui sélectionne le domino à son domino grâce à l'index.
 * 
 * Implémentation de collection choisie : Je prend toujours une ArrayList parce que je n'utilise pas spécialement de méthodes relatives aux Listes excepté .add et .get,
 * le .get étant bien optimisé pour une ArrayList contrairement à une LinkedList, mon choix s'est orienté directement vers une ArrayList.
 *
 */
 
public class DrawLine {

	private List<Domino> drawLine = new ArrayList<Domino>();
	private List<Player> player = new ArrayList<Player>();
	
	/**
	 * 
	 * @param domino, un domino composé d'un id et de deux tuiles.
	 * @param nbCard,le nombre de cartes qui vont être tirée de la pile de domino pour la ligne de tirage
	 * 
	 * On forme l'objet drawLine qui sera composé d'autant de domino que du nombre de carte.
	 */
	public DrawLine(DominoPile domino, int nbCard) {
		if (nbCard == 2) {
			nbCard = 4;
		}
		if (nbCard < 3 || nbCard > 4 || domino == null) {
			for (int j = 0; j < nbCard; j++) {
				drawLine.add(null);
			}
		} else {
			for (int i = 0; i < nbCard; i++) {
				drawLine.add(domino.tirage());
				player.add(null);
			}
			tri();
			
		}		
	}
	
	public List<Domino> getDrawLine() {
		if (drawLine == null) {
			return null;
		} else {
			List<Domino> copie = new ArrayList<>(drawLine); 
			return copie;
		}
		
	}
	
	/**
	 * Méthode qui permet de trier la ligne de tirage.
	 */
	public void tri() {
		Collections.sort(drawLine); 
	}
	
	/**
	 * Méthode permettant d'associer un domino de la ligne de tirage à un joueur.
	 * @param player, un joueur à qui on doit assigner un domino
	 */
	public void associationJoueur(List<Player> players) {
		for (int i = 0; i < players.size(); i++) {
			this.player.set(i, players.get(i)); 
		}
	}
	
	/**
	 * 
	 * @param pos, la position du joueur dans la liste
	 * @param player, le joueur actuel
	 * @return TRUE si on set bien le joueur à sa position, sinon return FALSE
	 */
	public boolean associationJoueurNextDrawLine(int pos, Player player) {
		if (this.player.get(pos) == null) {
			this.player.set(pos, player);
			return true;
		} else {
			return false;
		}
		
	} 	
	
	public List<Player> getPlayer() {
		List<Player> copie = new ArrayList<>(player);
		return copie;
	}
	
	/**
	 * Méthode permettant de retirer le premier élément de la drawLine et le premier élément de la liste de joueurs.
	 */
	public void removeElement() {
		drawLine.remove(0);
		player.remove(0);
	}
	
}