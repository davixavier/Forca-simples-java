package application.panes;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class WordViewPane extends HBox
{
	private ObservableList<String> letters;
	private ArrayList<VBox> vBoxes;
	
	public WordViewPane()
	{
		letters = FXCollections.observableArrayList();
		
		init();
	}
	
	public WordViewPane(String[] letras)
	{
		this.letters = FXCollections.observableArrayList();
		init();
		
		this.letters.addAll(letras.toString().toLowerCase());
	}
	
	public WordViewPane(CharSequence letras)
	{
		letters = FXCollections.observableArrayList();
		
		for (int i = 0; i < letras.length(); i++)
		{
			letters.add(Character.toString(letras.charAt(i)).toLowerCase());
		}
	}
	
	private void init()
	{
		vBoxes = new ArrayList<VBox>();
		
		this.letters.addListener((ListChangeListener.Change<? extends String> l) ->
		{
			l.next();
			
			l.getAddedSubList().forEach(c ->
			{
				VBox vBox = new VBox();
				vBox.setSpacing(1);
				vBox.setAlignment(Pos.CENTER);
				
				Rectangle subLine = new Rectangle();
				subLine.setWidth(6);
				subLine.setHeight(1);
				
				Label letterLabel = new Label(c);
				letterLabel.setVisible(false);
				
				vBox.getChildren().addAll(letterLabel, subLine);
				getChildren().add(vBox);
				vBoxes.add(vBox);
				
				if (c.equals(" "))
					vBox.setVisible(false);
			});
		});
		
		setSpacing(5);
		setAlignment(Pos.CENTER);
	}
	
	public boolean testLetter(String letter)
	{
		boolean ret = false;
		
		for(int i = 0; i < letters.size(); i++)
		{
			if (letters.get(i).equals(letter))
			{
				vBoxes.get(i).getChildren().get(0).setVisible(true);
				ret = true;
			}
		}
		
		return ret;
	}
	
	public int getRemainingLetters()
	{
		int ret = 0;
		
		for (VBox vBox : vBoxes)
		{
			if (!vBox.getChildren().get(0).isVisible() && !((Label)vBox.getChildren().get(0)).getText().equals(" "))
				ret++;
		}
		
		return ret;
	}
	
	public void setLetters(CharSequence letters)
	{
		for (int i = 0; i < letters.length(); i++)
		{
			this.letters.add(Character.toString(letters.charAt(i)).toLowerCase());
		}
	}

	public ObservableList<String> getLetters()
	{
		return letters;
	}

	public void setLetters(ObservableList<String> letras)
	{
		this.letters = letras;
	}
}
