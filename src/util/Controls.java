package util;

import application.MainGame;
import application.panes.ForcaPane;
import application.panes.WordViewPane;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

//Controla e entrada feita pelo usu�rio
public class Controls extends VBox
{
	private Label playerLabel;
	private Label statusLabel;
	private HBox answerBox;
	
	private SimpleBooleanProperty dead;
	
	public Controls(Pane root, WordViewPane wordViewPane, ForcaPane forcaPane)
	{
		//Inicializa��o de gr�ficos mostrados em caso de perda ou ganho e texto da letra atual digitada pelo jogador
		playerLabel = new Label();
		statusLabel = new Label("Voc� errou demais e morreu na forca!");
		statusLabel.setVisible(false);
		getChildren().add(playerLabel);
		getChildren().add(statusLabel);
		setAlignment(Pos.CENTER);
		
		answerBox = new HBox();
		answerBox.setSpacing(2);
		answerBox.setAlignment(Pos.CENTER);
		getChildren().add(answerBox);
		
		dead = new SimpleBooleanProperty(false);
		
		statusLabel.widthProperty().addListener(l ->
		{
			setWidth(statusLabel.getWidth());
			setTranslateX(root.getWidth()/2 - getWidth()/2);
		});
		
		setSpacing(20);
		
		//Gerenciamento de entrada do jogador
		//Mostra uma letra digitada pelo usu�rio no componente gr�fico destinado a isso
		//Checa para quando a tecla enter for apertada, se sim checa se a letra existe no painel de letras,
		//se sim deixa a letra vis�vel, se n�o adiciona uma parte do corpo a mais do enforcado
		
		//A cada aperto da tecla ENTER checa se o usu�rio ganhou ou perdeu
		root.getScene().setOnKeyPressed(e ->
		{
			if (e.getCode() == KeyCode.BACK_SPACE)
			{
				playerLabel.setText("");
				return;
			}
			
			if (e.getCode() == KeyCode.ENTER && playerLabel.getText().length() > 0)
			{
				if (!wordViewPane.testLetter(playerLabel.getText().toLowerCase()))
				{
					forcaPane.next();
					
					if (forcaPane.remainingParts() == 0)
					{
						statusLabel.setText("Voc� errou demais e morreu na forca!");
						statusLabel.setVisible(true);
						
						dead.set(true);
					}
				}
				
				if (wordViewPane.getRemainingLetters() == 0)
				{
					statusLabel.setText("Voc� acertou a palavra!");
					statusLabel.setVisible(true);
					
					root.getScene().setOnKeyPressed(event ->
					{
						((MainGame)root.getUserData()).init(root);
					});
					
					dead.set(true);
				}
				answerBox.getChildren().add(new Label(playerLabel.getText()));
				
				playerLabel.setText("");
				return;
			}
			
			if (playerLabel.getText().length() < 1 && !dead.get())
			{
				playerLabel.setText(playerLabel.getText() + e.getText());
				
				if (e.getCode() == KeyCode.ENTER)
					playerLabel.setText("");
			}
		});
	}
}
