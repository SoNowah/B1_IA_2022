package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KingdomTest {

	@Test
	void getPlayer() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		
		assertEquals(player, royaume.getPlayer());
		
	}
	
	@Test
	void getRoyaume() {
		Player player = new Player("Jean", "fffff");
		Kingdom monRoyaume = new Kingdom(player);
		
		assertEquals(9, monRoyaume.getKingdom().length);
	}
	
	@Test
	void getScore() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		assertEquals(-1, royaume.getScore());
	}
	
	@Test
	void scoreJuste() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		assertEquals(0, royaume.scoreFinal());
	}
	
	@Test
	void estOccupeFalse() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.CASTLE, 2);
		Tile tuile2 = new Tile(Terrain.FIELD, 1);
		var domino = new Domino(50, tuile1, tuile2);
		
		assertFalse(royaume.estOccupe(domino));
	}
	
	@Test
	void estAdjacentAuRoyaume() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.CASTLE, 2);
		tuile1.setColonne(3);
		tuile1.setLigne(4);
		
		assertTrue(royaume.adjacentKingdom(tuile1));
	}
	
	@Test
	void estAdjacentAuRoyaume2() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 2);
		tuile1.setColonne(4);
		tuile1.setLigne(4);
		
		assertFalse(royaume.adjacentKingdom(tuile1));
	}
	
	@Test
	void estAdjacentAuRoyaume3() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 2);
		tuile1.setColonne(4);
		tuile1.setLigne(3);
		
		assertTrue(royaume.adjacentKingdom(tuile1));
	}
	
	@Test
	void estAdjacentAuRoyaume4() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 2);
		tuile1.setColonne(5);
		tuile1.setLigne(4);
		
		assertTrue(royaume.adjacentKingdom(tuile1));
	}
	
	@Test
	void estAdjacentAuRoyaume5() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 2);
		tuile1.setColonne(4);
		tuile1.setLigne(5);
		
		assertTrue(royaume.adjacentKingdom(tuile1));
	}
	
	@Test
	void voisinDeDroiteHorsRoyaume() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 2);
		tuile1.setColonne(8);
		tuile1.setLigne(5);
		
		assertFalse(royaume.voisinDeDroite(tuile1));
	}
	
	@Test
	void voisinDeGaucheHorsRoyaume() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 2);
		tuile1.setColonne(0);
		tuile1.setLigne(5);
		
		assertFalse(royaume.voisinDeGauche(tuile1));
	}
	
	@Test
	void voisinDuHautHorsRoyaume() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 2);
		tuile1.setColonne(5);
		tuile1.setLigne(0);
		
		assertFalse(royaume.voisinDuHaut(tuile1));
	}
	
	@Test
	void voisinDuBasHorsRoyaume() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 2);
		tuile1.setColonne(6);
		tuile1.setLigne(8);
		
		assertFalse(royaume.voisinDuBas(tuile1));
	}
	
	@Test
	void voisinDeDroite() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 2);
		tuile1.setColonne(6);
		tuile1.setLigne(5);
		royaume.getKingdom()[tuile1.getLigne()][tuile1.getColonne() + 1] = new Tile(Terrain.FIELD, 0);
		
		assertTrue(royaume.voisinDeDroite(tuile1));
	}
	
	@Test
	void voisinDeGauche() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 2);
		tuile1.setColonne(6);
		tuile1.setLigne(5);
		royaume.getKingdom()[tuile1.getLigne()][tuile1.getColonne() - 1] = new Tile(Terrain.FIELD, 0);
		
		assertTrue(royaume.voisinDeGauche(tuile1));
	}
	
	@Test
	void voisinDuBas() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 2);
		tuile1.setColonne(6);
		tuile1.setLigne(5);
		royaume.getKingdom()[tuile1.getLigne() + 1][tuile1.getColonne()] = new Tile(Terrain.FIELD, 0);
		
		assertTrue(royaume.voisinDuBas(tuile1));
	}
	
	@Test
	void voisinDuHaut() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 2);
		tuile1.setColonne(6);
		tuile1.setLigne(5);
		royaume.getKingdom()[tuile1.getLigne() - 1][tuile1.getColonne()] = new Tile(Terrain.FIELD, 0);
		
		assertTrue(royaume.voisinDuHaut(tuile1));
	}
	
	@Test
	void terrainVoisinsDroit() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 0);
		tuile1.setColonne(6);
		tuile1.setLigne(5);
		royaume.getKingdom()[tuile1.getLigne()][tuile1.getColonne() + 1] = new Tile(Terrain.FIELD, 2);
		int[]score = new int[2];
		royaume.terrainVoisins(tuile1, score);
		assertEquals(2,score[1]);
		assertEquals(2, score[0]);
	}
	
	@Test
	void terrainVoisinsGauche() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 0);
		tuile1.setColonne(6);
		tuile1.setLigne(5);
		royaume.getKingdom()[tuile1.getLigne()][tuile1.getColonne() - 1] = new Tile(Terrain.FIELD, 2);
		int[]score = new int[2];
		royaume.terrainVoisins(tuile1, score);
		assertEquals(2,score[1]);
		assertEquals(2, score[0]);
	}
	
	@Test
	void terrainVoisinsBas() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 0);
		tuile1.setColonne(6);
		tuile1.setLigne(5);
		royaume.getKingdom()[tuile1.getLigne() + 1][tuile1.getColonne()] = new Tile(Terrain.FIELD, 2);
		int[]score = new int[2];
		royaume.terrainVoisins(tuile1, score);
		assertEquals(2,score[1]);
		assertEquals(2, score[0]);
	}
	
	@Test
	void terrainVoisinsHaut() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		Tile tuile1 = new Tile(Terrain.FIELD, 0);
		tuile1.setColonne(6);
		tuile1.setLigne(5);
		royaume.getKingdom()[tuile1.getLigne() - 1][tuile1.getColonne()] = new Tile(Terrain.FIELD, 2);
		int[]score = new int[2];
		royaume.terrainVoisins(tuile1, score);
		assertEquals(2,score[1]);
		assertEquals(2, score[0]);
	}
	
	@Test
	void methodeDeCalcul() {
		Player player = new Player("Jean", "fffff");
		Kingdom royaume = new Kingdom(player);
		int scoreFinal;
		Tile tuile1 = new Tile(Terrain.FIELD, 2);
		tuile1.setLigne(0);
		tuile1.setColonne(3);
		Tile tuile2 = new Tile(Terrain.FIELD, 0);
		tuile2.setLigne(0);
		tuile2.setColonne(4);
		Tile tuile3 = new Tile(Terrain.FIELD, 1);
		tuile3.setLigne(0);
		tuile3.setColonne(5);
		
		royaume.getKingdom()[0][3] = tuile1; //Ici je pose une tuile FIELD en 5,6 avec 2 couronnes
		royaume.getKingdom()[0][4] = tuile2; //Ici je pose une tuile FIELD en 6,6 avec 0 couronnes
		royaume.getKingdom()[0][5] = tuile3; //Ici je pose une tuile FIELD en 6,7 avec 1 couronnes
		//DONC 3 FIELD avec 3 couronnes en tout, résultat 9
		scoreFinal = royaume.scoreFinal();
		assertEquals(9, scoreFinal);
	}
}
