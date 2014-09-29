import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Gil De Grove, Romain Henneton
 *
 */
public class FileAcces {
	public static int WriteInFile(String filePath, String toWrite)
	{

		Path file = Paths.get(filePath);
		if (! file.toFile().exists())
		{
			try {		
				Files.createFile(file);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}	
		if (! file.toFile().canWrite()){		
			return -1;
		}

		try {
			Files.write(file,toWrite.getBytes("US-ASCII"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;

	}
	public static List<String> ReadFromFile(String filePath)
	{
		List<String> text = new ArrayList<String>();
		Path file = Paths.get(filePath);
		if(! file.toFile().exists())
		{
			return text;
		}

		try {
			text = Files.readAllLines(file, Charset.forName("US-ASCII"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
}

