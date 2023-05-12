package kingdomino.domains;

public class Tile {
	
	private Terrain territory;
	private int crownsCount;
	private int ligne;
	private int colonne;
	
	public Tile(Terrain territory, int crownsCount) {
		if (territory == null) {
			this.territory = Terrain.EMPTY;
		} else {
			this.territory = territory;
		}
		 
		if (crownsCount < 0 || crownsCount > 3) {
			crownsCount = 0;
		} else {
			this.crownsCount = crownsCount;  
		}
		
	}
	
	public int getCrownsCount() {
		return crownsCount;
	}
	
	public Terrain getTerritory() {
		return territory;
	}
	
	public int getLigne() {
		return ligne;
	}
	
	public int getColonne() {
		return colonne;
	}
	
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}
	
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
}
