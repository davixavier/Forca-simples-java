package application.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

//Painel que lê palavras de um arquivo e seleciona uma aleatória
public class WordFileReader
{
	private RandomAccessFile fileReader;
	private File file;
	
	//Arquivo usado é um arquivo no caminho base do programa com nome de "palavras.txt"
	public WordFileReader() throws IOException
	{
		String filePath = "palavras.txt";
		
		file = new File(filePath);
		if (!file.exists())
		{
			file.createNewFile();
		}
		
		fileReader = new RandomAccessFile(file, "r");
	}
	
	//Retorna a palavra no índice "index" do arquivo
	//Sabe o índice certo contando quantos ';' foram lidos
	public String getWord(int index) throws IOException
	{
		fileReader.seek(0);
		
		String ret = "";
		
		char temp = 'a';
		int i = 0;
		while(i < index)
		{
			temp = (char)fileReader.read();
			if (temp == ';')
			{
				i++;
			}
		}
		
		if (temp == ';' || index == 0)
			temp = (char)fileReader.read();
		
		while (temp == '\n')
		{
			temp = (char)fileReader.read();
		}
		while (temp != ';')
		{
			if (temp != ';')
				ret = ret + temp;
			
			temp = (char)fileReader.read();
		}
		
		fileReader.seek(0);
		return ret;
	}
	
	//Conta todas as palavras do arquivo por meio dos caracteres ';'
	//Ou seja, existe uma palavra antes de cada ';'
	public int getTotalWordCount() throws IOException
	{
		int ret = 0;
		char temp;
		temp = (char)fileReader.read();
		
		while (fileReader.getFilePointer() < fileReader.length())
		{
			temp = (char)fileReader.read();

			if (temp == ';')
				ret++;
		}
		
		return ret;
	}
	
	//Seleciona uma palavra aleatória e retorna ela
	//Usa o Math.random() para selecionar um inteiro entre 0 e o total de palavras do arquivo
	public String getRandomWord() throws IOException
	{
		return getWord((int)((Math.random() * ((getTotalWordCount()-1 - 0) + 1)) + 0));
	}

	public RandomAccessFile getFileReader()
	{
		return fileReader;
	}

	public void setFileReader(RandomAccessFile fileReader)
	{
		this.fileReader = fileReader;
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}
}
