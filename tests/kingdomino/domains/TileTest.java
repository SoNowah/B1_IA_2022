package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TileTest {

	@Test
	void terrainVideEtCouronnesNégatives() {
		var tile = new Tile(null, -2);
		assertEquals(Terrain.EMPTY, tile.getTerritory());
		assertEquals(0, tile.getCrownsCount());
	}
	
	@Test
	void terrainEMPTYEtCouronnesSupérieuresA3() {
		var tile = new Tile(Terrain.EMPTY, 5);
		assertEquals(Terrain.EMPTY, tile.getTerritory());
		assertEquals(0, tile.getCrownsCount());
	}
	
	@Test
	void casNormal() {
		var tile = new Tile(Terrain.CASTLE, 2);
		assertEquals(Terrain.CASTLE, tile.getTerritory());
		assertEquals(2, tile.getCrownsCount());
	}
	
	@Test
	void setLigneEtGetLigne() {
		
		var tile = new Tile(Terrain.CASTLE, 2);
		tile.setLigne(2);
		assertEquals(2, tile.getLigne());
	}
	
	@Test
	void setColonneEtGetColonne() {
		
		var tile = new Tile(Terrain.CASTLE, 2);
		tile.setColonne(2);
		assertEquals(2, tile.getColonne());
	}

}
