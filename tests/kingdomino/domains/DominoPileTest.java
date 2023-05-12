package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import kingdomino.io.CsvDataReader;

class DominoPileTest {

	@Test
	void pileDeDeuxJoueurs() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		var dominoPile = new DominoPile(dominoes, 2);
		assertEquals(24, dominoPile.getPile().size());
	}
	
	@Test
	void pileDeTroisJoueurs() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		var dominoPile = new DominoPile(dominoes, 3);
		assertEquals(36, dominoPile.getPile().size());
	}
	
	@Test
	void pileDeQuatreJoueurs() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		var dominoPile = new DominoPile(dominoes, 4);
		assertEquals(48, dominoPile.getPile().size());
	}
	
	@Test
	void pileNulle() {
		var dominoPile = new DominoPile(null, 4);
		assertNull(dominoPile.getPile());
	}
	
	
	@Test
	void fonctionTirage() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		var dominoPile = new DominoPile(dominoes, 2);
		assertNotNull(dominoPile.tirage());
	}

}
