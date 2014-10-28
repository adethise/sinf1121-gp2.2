import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PublicationMap {
	private HashMap<String,LightPublication> principal;
	private HashMap<Integer,String> references;
	
	PublicationMap()
	{
		principal = new HashMap<String,LightPublication>();
		references = new HashMap<Integer,String>();
		/*
		List<String> liste = ReadFromFile("journals.txt");
		System.out.println(liste.size());
		for(String s:liste)
		{
			System.out.println(s);
			put(s);
		}
		*/
		put("A*,Monthly Notices of the Royal Astronomical Society: Letters,201,Astronomical and Space Sciences,,,,");
		put("C,Journal of Global Business Management,1503,Business and Management,,,,");
		put("B,American Journal of Criminal Law,1801,Law,,,,");
		put("A,Neuroimage,1103,Clinical Sciences,1702,Cognitive Science,1109,Neurosciences");
		
	}
	public void put(String line)
	{

		put(new Publication(line));
	}
	public void put(Publication pub)
	{
		LightPublication lp = new LightPublication(pub);
		
		principal.put(pub.getName(),lp);
		
		String[][] refs = pub.getTable();
		
		for(int i = 0;i<3 && refs[0][i]!=null;i++)
		{
			
			references.put(Integer.parseInt(refs[0][i]), refs[1][i]);
			
		}
		
	}
	
	public Publication get(String key)
	{
		
		
		return new Publication(getString(key));
	}
	public String getString(String key)
	{
		LightPublication lp = principal.get(key);
		
		int[] refs = lp.getReference();
		
		String answer = lp.getRank()+","+lp.getName();
		for(int i =0;i<3;i++)
		{
			if(refs[i]!=0)
			{
				answer = answer +","+refs[i]+","+references.get(refs[i]);
			}
			else
			{
				answer = answer+",,";
			}
		}
		return answer;
	}
	
	
	
	
	public static void main(String[]args)
	{
		PublicationMap pubmap = new PublicationMap();
		System.out.println(pubmap.getString("Neuroimage"));
		
		
		
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
