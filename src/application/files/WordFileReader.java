package application.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WordFileReader
{
	private RandomAccessFile fileReader;
	private File file;
	
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
