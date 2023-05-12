package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DominoTest {

	@Test
	void idNonComprisDansLIntervalle() {
		Tile tuile1 = new Tile(Terrain.CASTLE, 2);
		Tile tuile2 = new Tile(Terrain.FIELD, 1);
		var domino = new Domino(50, tuile1, tuile2);
		
		assertEquals(-1, domino.getId());
	}

	@Test
	void partie1DeTuileNulle() {
		Tile tuile1 = new Tile(Terrain.FIELD, 1);
		var domino = new Domino(24, null, tuile1);
		
		assertEquals(Terrain.FIELD, domino.getPart1().getTerritory());
		assertEquals(0, domino.getPart1().getCrownsCount());
	}
	
	@Test
	void partie2DeTuileNulle() {
		Tile tuile1 = new Tile(Terrain.FIELD, 1);
		var domino = new Domino(24, tuile1, null);
		
		assertEquals(Terrain.FIELD, domino.getPart2().getTerritory());
		assertEquals(0, domino.getPart2().getCrownsCount());
	}
	
	@Test
	void casSansProblème() {
		Tile tuile1 = new Tile(Terrain.CASTLE, 2);
		Tile tuile2 = new Tile(Terrain.FIELD, 1);
		var domino = new Domino(42, tuile1, tuile2);
		
		assertEquals(42, domino.getId());
	}
	
	@Test
	void fonctionCompareTo() {
		Tile tuile1 = new Tile(Terrain.CASTLE, 2);
		Tile tuile2 = new Tile(Terrain.FIELD, 1);
		var domino = new Domino(42, tuile1, tuile2);
		var domino2 = new Domino(44, tuile1, tuile2);
		
		assertEquals(-2,domino.compareTo(domino2));
	}
	
	@Test
	void idHorsBorneTrop() {
		Tile tuile1 = new Tile(Terrain.CASTLE, 2);
		Tile tuile2 = new Tile(Terrain.FIELD, 1);
		var domino = new Domino(58, tuile1, tuile2);
		
		assertEquals(-1, domino.getId());
	}
	
	@Test
	void idHorsBornePeu() {
		Tile tuile1 = new Tile(Terrain.CASTLE, 2);
		Tile tuile2 = new Tile(Terrain.FIELD, 1);
		var domino = new Domino(-18, tuile1, tuile2);
		
		assertEquals(-1, domino.getId());
	}
}
