package kingdomino.domains;

public class Domino implements Comparable<Domino>  {
	
	private int id;
	private Tile part1;
	private Tile part2;
	
	/**
	 * 
	 * @param id, numéro du domino
	 * @param part1, Tuile numéro 1 qui compose le domino 
	 * @param part2, Tuile numéro 2 qui compose le domino
	 * 
	 * Le constructeur permet d'assembler l'id, la tui
	 */
	public Domino(int id, Tile part1, Tile part2) {
		if (id < 1 || id > 48) {
			this.id = -1;
		} else {
			this.id = id;
		}
		
		if (part1 == null) {
			part1 = new Tile(Terrain.FIELD, 0); 
		}
		this.part1 = part1;
		
		if (part2 == null) {  
			part2 = new Tile(Terrain.FIELD, 0);
		}
		this.part2 = part2;
		
	}
	
	public int getId() {
		return id;
	}
	
	public Tile getPart1() {
		return part1;
	}
	
	public Tile getPart2() {
		return part2;
	}

	/**
	 * Méthode permettant de comparer deux id de domino.
	 */
	@Override
	public int compareTo(Domino o) {
		
		return id - o.getId();
	}
}
