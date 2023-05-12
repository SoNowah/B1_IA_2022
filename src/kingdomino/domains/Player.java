package kingdomino.domains;

public class Player  implements Comparable<Player>{
	private final String name;
	private final String hexColor;
	private Kingdom royaume;
	
	/**
	 * 
	 * @param name, le nom du joueur
	 * @param hexColor, la couleur du joueur
	 * 
	 * Assemble le nom et la couleur pour former l'objet joueur.
	 */
	public Player(String name, String hexColor) { 
		this.name = name;
		this.hexColor = hexColor;
		royaume = new Kingdom(this);
	}
	
	public String getHexColor() {
		return hexColor;
	}

	public String getName() {
		return name;
	}
	
	public Kingdom getRoyaume() {
		return royaume;
	}
	
	/**
	 * Méthode permettant de comparer le score de deux joueurs.
	 */
	@Override
	public int compareTo(Player o) {
		
		return  o.getRoyaume().scoreFinal() - royaume.scoreFinal();
	}
}
