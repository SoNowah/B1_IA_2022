package kingdomino.supervisers;

import java.util.*;
import kingdomino.domains.*;

public class GameOverSuperviser extends Superviser {
	private GameOverView view;
	private KingDominoFactory gameFactory;
	private KingDominoGame dominoGame;
	
	//TODO : modifier au besoin le constructeur 
	public GameOverSuperviser(KingDominoFactory gameFactory) {
		this.gameFactory = gameFactory;
	}
	
	public void setView(GameOverView view) {
		this.view = view;
	}
	
	@Override
	public void onEnter(String fromScreen) {
		//TODO : si on vient de la vue "PlayGame", calculer les scores des royaumes, trier les joueurs et les afficher
		draw();
	}
	
	private void draw() {
		//TODO : mettre à jour les instruction
		view.startDraw();
		dominoGame = gameFactory.getDominoGame();
		for (Player player : dominoGame.getPlayerSort()) {
			view.addScore(player.getName(), player.getRoyaume().scoreFinal(), player.getHexColor());
			view.setWinner( "J" + dominoGame.getPlayerSort().get(0).getName() + " remporte la partie.");
		}
		
		view.endDraw();
	}

	public void onGoToMainMenu() {
		//TODO : naviguer vers le menu principal
		view.goTo("MainMenu");
	}
}
