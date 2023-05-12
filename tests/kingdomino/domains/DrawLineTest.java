package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.sun.nio.sctp.Association;

import kingdomino.io.CsvDataReader;

class DrawLineTest {

	@Test
	void nombreCarteComprisDansLIntervalle() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		var dominoPile = new DominoPile(dominoes, 3);
		var drawLine = new DrawLine(dominoPile, 3);
		assertEquals(3, drawLine.getDrawLine().size());
	}
	
	@Test
	void nombreDeCartesVaut2() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		var dominoPile = new DominoPile(dominoes, 2);
		var drawLine = new DrawLine(dominoPile, 2);
		assertEquals(4, drawLine.getDrawLine().size());
	}
	
	@Test
	void fonctionAssociation() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		var dominoPile = new DominoPile(dominoes, 4);
		var drawLine = new DrawLine(dominoPile, 4);
		drawLine.associationJoueur(players);
		assertEquals(4, drawLine.getPlayer().size());
		
	}
	
	@Test
	void nombreCarteNonComprisDansLIntervalle() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		var dominoPile = new DominoPile(dominoes, 3);
		var drawLine = new DrawLine(dominoPile, 8);
		List<Domino> drawLineTest = new ArrayList<Domino>();
		for (int i = 0; i < 8; i++) {
			drawLineTest.add(null);
		}
		assertEquals(drawLineTest, drawLine.getDrawLine());
		
	}
	
	@Test
	void pileDeDominoNull() {
		var drawLine = new DrawLine(null, 8);
		List<Domino> drawLineTest = new ArrayList<Domino>();
		for (int i = 0; i < 8; i++) {
			drawLineTest.add(null);
		}
		assertEquals(drawLineTest, drawLine.getDrawLine());
	}
}
