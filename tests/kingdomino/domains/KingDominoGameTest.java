package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import kingdomino.io.CsvDataReader;

class KingDominoGameTest {

	@Test
	void getDrawLine() {
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
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		
		assertEquals(drawLine.getDrawLine().size(), game.getCurrentDrawLine().getDrawLine().size());
	}
	
	@Test
	void getNextDrawLine() {
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
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		
		assertEquals(drawLine.getDrawLine().size(), game.getNextDrawLine().getDrawLine().size());
	}
	
	@Test
	void getDomino() {
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
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		
		assertEquals(drawLine.getDrawLine().get(0).getPart1().getTerritory(), game.getCurrentDomino().getPart1().getTerritory());
	}

	@Test
	void getKingdom() {
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
		Kingdom royaume = new Kingdom(players.get(0));
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		
		assertEquals(royaume.getKingdom().length, game.getMonRoyaume().getKingdom().length);
	}

	@Test
	void constructeurAvec2Joueurs() {
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
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		factory.setNumberOfPlayer(2);
		KingDominoGame game = new KingDominoGame(factory);
		
		assertEquals(drawLine.getDrawLine().size(), game.getCurrentDrawLine().getDrawLine().size());
	}
	
	@Test
	void deplacementTuileDcPositif() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		Domino curDominoTest = game.getCurrentDomino();
		int posCol1 = curDominoTest.getPart1().getColonne() + 1;
		int posLig1 = curDominoTest.getPart1().getLigne();
		int posCol2 = curDominoTest.getPart2().getColonne() + 1;
		int posLig2 = curDominoTest.getPart2().getLigne();

		game.deplacementTuile(0, 1, curDominoTest);
		assertEquals(posCol1, game.getCurrentDomino().getPart1().getColonne());
		assertEquals(posLig1, game.getCurrentDomino().getPart1().getLigne());
		assertEquals(posCol2, game.getCurrentDomino().getPart2().getColonne());
		assertEquals(posLig2, game.getCurrentDomino().getPart2().getLigne());
	}
	
	@Test
	void deplacementTuileDcNegatif() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		Domino curDominoTest = game.getCurrentDomino();
		int posCol1 = curDominoTest.getPart1().getColonne() - 1;
		int posLig1 = curDominoTest.getPart1().getLigne();
		int posCol2 = curDominoTest.getPart2().getColonne() - 1;
		int posLig2 = curDominoTest.getPart2().getLigne();

		game.deplacementTuile(0, -1, curDominoTest);
		assertEquals(posCol1, game.getCurrentDomino().getPart1().getColonne());
		assertEquals(posLig1, game.getCurrentDomino().getPart1().getLigne());
		assertEquals(posCol2, game.getCurrentDomino().getPart2().getColonne());
		assertEquals(posLig2, game.getCurrentDomino().getPart2().getLigne());
	}
	
	@Test
	void deplacementTuileDrPositif() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		Domino curDominoTest = game.getCurrentDomino();
		int posCol1 = curDominoTest.getPart1().getColonne();
		int posLig1 = curDominoTest.getPart1().getLigne() + 1;
		int posCol2 = curDominoTest.getPart2().getColonne();
		int posLig2 = curDominoTest.getPart2().getLigne() + 1;

		game.deplacementTuile(1, 0, curDominoTest);
		assertEquals(posCol1, game.getCurrentDomino().getPart1().getColonne());
		assertEquals(posLig1, game.getCurrentDomino().getPart1().getLigne());
		assertEquals(posCol2, game.getCurrentDomino().getPart2().getColonne());
		assertEquals(posLig2, game.getCurrentDomino().getPart2().getLigne());
	}
	
	@Test
	void deplacementTuileDrNegatif() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		Domino curDominoTest = game.getCurrentDomino();
		int posCol1 = curDominoTest.getPart1().getColonne();
		int posLig1 = curDominoTest.getPart1().getLigne() - 1;
		int posCol2 = curDominoTest.getPart2().getColonne();
		int posLig2 = curDominoTest.getPart2().getLigne() - 1;

		game.deplacementTuile(-1, 0, curDominoTest);
		assertEquals(posCol1, game.getCurrentDomino().getPart1().getColonne());
		assertEquals(posLig1, game.getCurrentDomino().getPart1().getLigne());
		assertEquals(posCol2, game.getCurrentDomino().getPart2().getColonne());
		assertEquals(posLig2, game.getCurrentDomino().getPart2().getLigne());
	}
	
	@Test
	void rotationTuileColonnePart2SupPart1() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		Domino curDominoTest = game.getCurrentDomino();
		
		curDominoTest.getPart1().setColonne(0);
		curDominoTest.getPart2().setColonne(1);
		
		int posCol1 = curDominoTest.getPart1().getColonne();
		int posLig1 = curDominoTest.getPart1().getLigne();
		int posCol2 = curDominoTest.getPart2().getColonne() - 1;
		int posLig2 = curDominoTest.getPart2().getLigne() + 1;
		
		game.rotationDomino(curDominoTest);
		assertEquals(posCol1, game.getCurrentDomino().getPart1().getColonne());
		assertEquals(posLig1, game.getCurrentDomino().getPart1().getLigne());
		assertEquals(posCol2, game.getCurrentDomino().getPart2().getColonne());
		assertEquals(posLig2, game.getCurrentDomino().getPart2().getLigne());
	}
	
	@Test
	void rotationTuileColonnePart2InfPart1() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		Domino curDominoTest = game.getCurrentDomino();
		
		curDominoTest.getPart1().setColonne(1);
		curDominoTest.getPart2().setColonne(0);
		
		int posCol1 = curDominoTest.getPart1().getColonne();
		int posLig1 = curDominoTest.getPart1().getLigne();
		int posCol2 = curDominoTest.getPart2().getColonne() + 1;
		int posLig2 = curDominoTest.getPart2().getLigne() - 1 ;

		game.rotationDomino(curDominoTest);
		assertEquals(posCol1, game.getCurrentDomino().getPart1().getColonne());
		assertEquals(posLig1, game.getCurrentDomino().getPart1().getLigne());
		assertEquals(posCol2, game.getCurrentDomino().getPart2().getColonne());
		assertEquals(posLig2, game.getCurrentDomino().getPart2().getLigne());
	}
	
	@Test
	void rotationTuileLignePart2InfPart1() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		Domino curDominoTest = game.getCurrentDomino();
		
		curDominoTest.getPart1().setLigne(0);
		curDominoTest.getPart1().setColonne(0);
		curDominoTest.getPart2().setLigne(-1);
		curDominoTest.getPart2().setColonne(0);
		
		int posCol1 = curDominoTest.getPart1().getColonne();
		int posLig1 = curDominoTest.getPart1().getLigne();
		int posCol2 = curDominoTest.getPart2().getColonne() + 1;
		int posLig2 = curDominoTest.getPart2().getLigne() + 1 ;

		game.rotationDomino(curDominoTest);
		assertEquals(posCol1, game.getCurrentDomino().getPart1().getColonne());
		assertEquals(posLig1, game.getCurrentDomino().getPart1().getLigne());
		assertEquals(posCol2, game.getCurrentDomino().getPart2().getColonne());
		assertEquals(posLig2, game.getCurrentDomino().getPart2().getLigne());
	}
	
	@Test
	void rotationTuileLignePart2SupPart1() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		Domino curDominoTest = game.getCurrentDomino();
		
		curDominoTest.getPart1().setLigne(0);
		curDominoTest.getPart1().setColonne(0);
		curDominoTest.getPart2().setLigne(1);
		curDominoTest.getPart2().setColonne(0);
		
		int posCol1 = curDominoTest.getPart1().getColonne();
		int posLig1 = curDominoTest.getPart1().getLigne();
		int posCol2 = curDominoTest.getPart2().getColonne() - 1;
		int posLig2 = curDominoTest.getPart2().getLigne() - 1 ;

		game.rotationDomino(curDominoTest);
		assertEquals(posCol1, game.getCurrentDomino().getPart1().getColonne());
		assertEquals(posLig1, game.getCurrentDomino().getPart1().getLigne());
		assertEquals(posCol2, game.getCurrentDomino().getPart2().getColonne());
		assertEquals(posLig2, game.getCurrentDomino().getPart2().getLigne());
	}
	
	@Test
	void positionSuivanteEgaleATaillePlayer() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		int pos = players.size() - 1;
		assertEquals(0, game.positionSuivante(pos));
	}
	
	@Test
	void positionSuivante() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		int pos = 1;
		assertEquals(2, game.positionSuivante(pos));
	}
	
	@Test
	void currentPlayer() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		Player currentPlayer = game.getCurrentPlayer();
		assertEquals(currentPlayer, game.getCurrentPlayer());
	}
	
	@Test
	void listPlayer() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		List<Player> listePlayer = game.getPlayerSort();
		assertEquals(listePlayer, game.getPlayerSort());
	}
	
	@Test
	void playerScoreTwo() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		List<Player> playerScore = game.getPlayerSort();
		assertEquals(playerScore, game.getPlayerSort());
	}
	
	@Test
	void placerDomino() {
		List<Domino> dominoes = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
				.map(dto -> new Player(dto.getName(), dto.getHexColor()))
				.collect(Collectors.toList());
		KingDominoFactory factory = new KingDominoFactory(dominoes, players);
		KingDominoGame game = new KingDominoGame(factory);
		assertFalse(game.placerDomino(game.getCurrentDomino()));
	}
	
}
