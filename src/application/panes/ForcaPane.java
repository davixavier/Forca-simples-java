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
	private ImageView cabe�a;
	private ImageView corpo;
	private ImageView bra�o;
	private ImageView perna;
	
	//Inicializa as imagens e deixa as partes do corpo invis�veis
	public ForcaPane()
	{
		forca = new ImageView(new Image(File.separatorChar + "application" + File.separatorChar + "img" + File.separatorChar + "forca.png"));
		
		cabe�a = new ImageView(new Image(File.separatorChar + "application" + File.separatorChar + "img" + File.separatorChar + "cabe�a.png"));
		corpo = new ImageView(new Image(File.separatorChar + "application" + File.separatorChar + "img" + File.separatorChar + "corpo.png"));
		bra�o = new ImageView(new Image(File.separatorChar + "application" + File.separatorChar + "img" + File.separatorChar + "bra�os.png"));
		perna = new ImageView(new Image(File.separatorChar + "application" + File.separatorChar + "img" + File.separatorChar + "pernas.png"));
	
		cabe�a.setX(160);
		corpo.xProperty().bind(cabe�a.xProperty());
		bra�o.xProperty().bind(cabe�a.xProperty());
		perna.xProperty().bind(cabe�a.xProperty());
		
		cabe�a.setY(38);
		corpo.yProperty().bind(cabe�a.yProperty());
		bra�o.yProperty().bind(cabe�a.yProperty());
		perna.yProperty().bind(cabe�a.yProperty());
		
		cabe�a.setVisible(false);
		corpo.setVisible(false);
		bra�o.setVisible(false);
		perna.setVisible(false);
		
		getChildren().addAll(forca, cabe�a, corpo, bra�o, perna);
		
		index = 1;
	}
	
	//Deixa a pr�xima parte do corpo do enforcado vis�vel
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

	public ImageView getCabe�a()
	{
		return cabe�a;
	}

	public void setCabe�a(ImageView cabe�a)
	{
		this.cabe�a = cabe�a;
	}

	public ImageView getCorpo()
	{
		return corpo;
	}

	public void setCorpo(ImageView corpo)
	{
		this.corpo = corpo;
	}

	public ImageView getBra�o()
	{
		return bra�o;
	}

	public void setBra�o(ImageView bra�o)
	{
		this.bra�o = bra�o;
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
