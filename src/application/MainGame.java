package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import application.files.WordFileReader;
import application.panes.ForcaPane;
import application.panes.WordViewPane;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import util.Controls;

public class MainGame
{
	private WordViewPane wordViewPane;
	
	//Inicializando os componentes principais do jogo
	public MainGame(Pane root)
	{
		root.setUserData(this);
		
		init(root);
	}
	
	//Inicialização do painel que mostra a forca, do painel das letras e do selecionador de palavras aleatórias
	//Método pode ser chamado para reinicialização do jogo
	public void init(Pane root)
	{
		root.getChildren().clear();
		
		Label temaLabel = new Label("O tema das palavras é times de futebol brasileiros.");
		root.getChildren().add(temaLabel);
		
		wordViewPane = new WordViewPane();
		
		try
		{
			WordFileReader fileReader = new WordFileReader();
			wordViewPane.setLetters(fileReader.getRandomWord());
		}
		catch (IOException e)
		{
			e.printStackTrace();
			
			wordViewPane.setLetters("ERRO");
		}
		
		root.getChildren().add(wordViewPane);
		
		ForcaPane forcaPane = new ForcaPane();
		root.getChildren().add(forcaPane);
		
		Controls controls = new Controls(root, wordViewPane, forcaPane);
		root.getChildren().add(controls);
		
		Platform.runLater(() ->
		{
			forcaPane.setTranslateX(root.getWidth()/2 - forcaPane.getWidth()/2);
			forcaPane.setTranslateY(50);
			
			wordViewPane.setTranslateX(root.getWidth()/2 - wordViewPane.getWidth()/2);
			wordViewPane.setTranslateY(forcaPane.getTranslateY() + forcaPane.getHeight() + 50);
			
			controls.setTranslateY(wordViewPane.getTranslateY()+40);
		});
	}
}
