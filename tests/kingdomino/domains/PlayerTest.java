package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void nomETHexaDUnJoueur() {
		Player unJoueur = new Player("Paul", "ffffff");
		assertEquals("Paul", unJoueur.getName());
		assertEquals("ffffff", unJoueur.getHexColor());
	}

}
