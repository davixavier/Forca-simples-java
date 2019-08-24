package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application 
{
	//M�todo de inicializa��o de janela do JavaFX
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			//Inicializa��o da janela principal do programa
			Pane root = new Pane();
			Scene scene = new Scene(root, 400, 400);
			
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Jogo da Forca");
			
			primaryStage.show();
			
			//Inicializa��o dos componentes principais do jogo
			MainGame loop = new MainGame(root);
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
