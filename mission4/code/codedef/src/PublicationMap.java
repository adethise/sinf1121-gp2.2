import java.util.HashMap;

/*
 * Class PublicationMap
 * This class allow the user to put Publication objects
 * in a HashTable and retrieve them, in object or String form.
 *
 * It actually doesn't put the Publication, but only a LightPublication
 * object in the Map in order to save memory space. But that part
 * is invisible to the user.
 */
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
		
		for(int i = 0 ; i<3 && !(refs[0][i].equals("")) ; i++)
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
	
	
	
	}
