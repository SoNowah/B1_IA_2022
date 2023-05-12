package kingdomino.supervisers;

import java.util.List;

import kingdomino.domains.*;

public final class MainMenuSuperviser extends Superviser {
	public static final String QUIT = "Quitter";
	public static final String NEW_4_PLAYERS = "Jouer à 4";
	public static final String NEW_3_PLAYERS = "Jouer à 3";
	public static final String NEW_2_PLAYERS = "Jouer à 2";
	
	private MainMenuView view;
	private KingDominoFactory gameFactory;
	
	public MainMenuSuperviser(KingDominoFactory gameFactory) {
		this.gameFactory = gameFactory;
	}

	public void setView(MainMenuView view) {
		//TODO : définir les items à afficher par la vue
		this.view = view;
		view.setItems(List.of(NEW_2_PLAYERS, NEW_3_PLAYERS, NEW_4_PLAYERS, QUIT));
	}

	public void onItemSelected(int selected) {
		//TODO : réagir à l'événement utilisateur item sélectionné
		switch (selected) {
		case 0:
			gameFactory.setNumberOfPlayer(2);
			view.goTo("PlayGame");
			break;
			
		case 1:
			gameFactory.setNumberOfPlayer(3);
			view.goTo("PlayGame");
			break;
			
		case 2:
			gameFactory.setNumberOfPlayer(4);
			view.goTo("PlayGame");
			break;
			
		case 3:
			view.onQuitConfirmed("MainMenu");
			break;

		default:
			break;
		}
		
	}

}
