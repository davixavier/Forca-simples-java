package application.panes;

import java.io.File;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

//Mostra a forca e as partes do corpo da forca
public class ForcaPane extends Pane
{
	private int index;
	
	private ImageView forca;
	private ImageView cabeça;
	private ImageView corpo;
	private ImageView braço;
	private ImageView perna;
	
	//Inicializa as imagens e deixa as partes do corpo invisíveis
	public ForcaPane()
	{
		forca = new ImageView(new Image(File.separatorChar + "application" + File.separatorChar + "img" + File.separatorChar + "forca.png"));
		
		cabeça = new ImageView(new Image(File.separatorChar + "application" + File.separatorChar + "img" + File.separatorChar + "cabeça.png"));
		corpo = new ImageView(new Image(File.separatorChar + "application" + File.separatorChar + "img" + File.separatorChar + "corpo.png"));
		braço = new ImageView(new Image(File.separatorChar + "application" + File.separatorChar + "img" + File.separatorChar + "braços.png"));
		perna = new ImageView(new Image(File.separatorChar + "application" + File.separatorChar + "img" + File.separatorChar + "pernas.png"));
	
		cabeça.setX(160);
		corpo.xProperty().bind(cabeça.xProperty());
		braço.xProperty().bind(cabeça.xProperty());
		perna.xProperty().bind(cabeça.xProperty());
		
		cabeça.setY(38);
		corpo.yProperty().bind(cabeça.yProperty());
		braço.yProperty().bind(cabeça.yProperty());
		perna.yProperty().bind(cabeça.yProperty());
		
		cabeça.setVisible(false);
		corpo.setVisible(false);
		braço.setVisible(false);
		perna.setVisible(false);
		
		getChildren().addAll(forca, cabeça, corpo, braço, perna);
		
		index = 1;
	}
	
	//Deixa a próxima parte do corpo do enforcado visível
	public void next()
	{
		if (index >= getChildren().size())
			return;
		
		getChildren().get(index).setVisible(true);
		index++;
	}
	
	//Retorna a quantidade de partes do corpo do enforcado que ainda restam ser mostradas
	//Ou seja, se retornar 0, quer dizer que o jogador perdeu
	public int remainingParts()
	{
		int ret = 0;
		
		for (Node node : getChildren())
		{
			if (!node.isVisible())
			{
				ret++;
			}
		}
		
		return ret;
	}

	public ImageView getForca()
	{
		return forca;
	}

	public void setForca(ImageView forca)
	{
		this.forca = forca;
	}

	public ImageView getCabeça()
	{
		return cabeça;
	}

	public void setCabeça(ImageView cabeça)
	{
		this.cabeça = cabeça;
	}

	public ImageView getCorpo()
	{
		return corpo;
	}

	public void setCorpo(ImageView corpo)
	{
		this.corpo = corpo;
	}

	public ImageView getBraço()
	{
		return braço;
	}

	public void setBraço(ImageView braço)
	{
		this.braço = braço;
	}

	public ImageView getPerna()
	{
		return perna;
	}

	public void setPerna(ImageView perna)
	{
		this.perna = perna;
	}
}
