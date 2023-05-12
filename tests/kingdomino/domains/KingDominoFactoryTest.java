package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import kingdomino.io.CsvDataReader;

class KingDominoFactoryTest {

	@Test
	void getNumberOfPlayer() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		
		factory.setNumberOfPlayer(4);
		
		assertEquals(4, factory.getNumberOfPlayer());
		
	}
	
	@Test
	void getNumberOfTwoPlayer() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		
		factory.setNumberOfPlayer(2);
		assertEquals(4, factory.getTwoPlayer().size());
	}
	
	@Test
	void getAllPlayers() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		
		factory.setNumberOfPlayer(4);
		
		assertEquals(4, factory.getAllPlayers().size());
		
	}
	
	@Test
	void getAllDominoes() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
	
		
		assertEquals(48, factory.getAllDominoes().size());
	}
	
	
	
	
	
	

}
