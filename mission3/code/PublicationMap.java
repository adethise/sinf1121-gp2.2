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
	
	/*
	 * Class constructor
	 */
	PublicationMap()
	{
		principal = new HashMap<String,LightPublication>();
		references = new HashMap<Integer,String>();
	}

	/*
	 * Put the publication represented by <line>
	 * in the Map (see instructions for format).
	 */
	public void put(String line)
	{
		put(new Publication(line));
	}

	/*
	 * Put a light version of <pub> in the Map
	 * and memorize the FoR names.
	 */
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
	
	/*
	 * Get the publication with name <key>
	 * from the Map.
	 */
	public Publication get(String key)
	{
		return new Publication(getString(key));
	}

	/*
	 * Get a string representation of the
	 * publication with name <key> from the Map.
	 */
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

		List<String> liste = FileAccess.ReadFromFile("Journals.csv");
		liste.remove(0);
		System.out.println(liste.size());
		for(String s : liste)
		{
			System.out.println(s);
			pubmap.put(s);
		}

		System.out.println(pubmap.getString("Neuroimage"));
	}
}
