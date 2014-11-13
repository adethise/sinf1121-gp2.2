import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * Class PublicationMap
 * This class allow the user to put Publication objects
 * in a HashTable and retrieve them, in object or String form.
 *
 * It actually doesn't put the Publication, but only a LightPublication
 * object in the Map in order to save memory space. But that part
 * is invisible to the user.
 */
public class PublicationTree extends PublicationMap {
	private SortedList<Publication> all; // contient toutes les pub pour tout retourner
	private ArrayList<TreeMap<String, SortedList<Publication>>> fields;
		// array de Tree, chaque tree correspond à un rang (cf constructor)
		// chaque noeud de chaque tree a comme clé l'Integer qui correspond au field 
		// 	(cf variables dans PubMap)
		// et comme valeur une liste des publication de ce rang et ce field
	
	
	
	
	/*
	 * Class constructor
	 */
	PublicationTree()
	{
		super();
		all = new SortedList<Publication>();
		fields = new ArrayList<TreeMap<String, SortedList<Publication>>>();
		for(int i=0;i<5;i++)
		{
			TreeMap<String,SortedList<Publication>> arbre = new TreeMap<String, SortedList<Publication>>();
			fields.add(arbre);
		}
		// 0=A* ; 1=A ; 2=B ; 3=C ;...
		
	}

	/*
	 * TODO modify / overwrite parent
	 * Ajoute la publication partout où elle doit être ajoutée
	 *
	 */
	public void put(Publication pub)
	{
		super.put(pub);
		all.add(pub);
		
		//Ajout dans le bon treemap
		if(pub.getRank().compareTo("A*")==0)
		{
			String [][] table = pub.getTable();
			for (int i = 0 ; i<3 && !(table[0][i].equals("")) ; i++)
			{
				SortedList<Publication> publist = fields.get(0).get(table[1][i]);
				if(publist==null)
				{
					SortedList<Publication> newsorted = new SortedList<Publication>();
					newsorted.add(pub);
					fields.get(0).put(table[1][i],newsorted);
				}
				else
				{
					publist.add(pub);
				}
				
			}
			
		}
		else if(pub.getRank().compareTo("A")==0)
		{
			String [][] table = pub.getTable();
			for (int i = 0 ; i<3 && !(table[0][i].equals("")) ; i++)
			{
				SortedList<Publication> publist = fields.get(1).get(table[1][i]);
				if(publist==null)
				{
					SortedList<Publication> newsorted = new SortedList<Publication>();
					newsorted.add(pub);
					fields.get(1).put(table[1][i],newsorted);
				}
				else
				{
					publist.add(pub);
				}
				
			}
		}
		else if(pub.getRank().compareTo("B")==0)
		{
			String [][] table = pub.getTable();
			for (int i = 0 ; i<3 && !(table[0][i].equals("")) ; i++)
			{
				SortedList<Publication> publist = fields.get(2).get(table[1][i]);
				if(publist==null)
				{
					SortedList<Publication> newsorted = new SortedList<Publication>();
					newsorted.add(pub);
					fields.get(2).put(table[1][i],newsorted);
				}
				else
				{
					publist.add(pub);
				}
				
			}
		}
		else if(pub.getRank().compareTo("C")==0)
		{
			String [][] table = pub.getTable();
			for (int i = 0 ; i<3 && !(table[0][i].equals("")) ; i++)
			{
				SortedList<Publication> publist = fields.get(3).get(table[1][i]);
				if(publist==null)
				{
					SortedList<Publication> newsorted = new SortedList<Publication>();
					newsorted.add(pub);
					fields.get(3).put(table[1][i],newsorted);
				}
				else
				{
					publist.add(pub);
				}
				
			}
		}
		else if(pub.getRank().compareTo("D")==0)
		{
			String [][] table = pub.getTable();
			for (int i = 0 ; i<3 && !(table[0][i].equals("")) ; i++)
			{
				SortedList<Publication> publist = fields.get(4).get(table[1][i]);
				if(publist==null)
				{
					SortedList<Publication> newsorted = new SortedList<Publication>();
					newsorted.add(pub);
					fields.get(4).put(table[1][i],newsorted);
				}
				else
				{
					publist.add(pub);
				}
				
			}
		}
		
		
		//L'ajout dans la TreeMap a été fait
		
		
}

	/*
	 *
	 */
	public SortedList<Publication> getAll()
	{
		return all;
	}

	/*
	 *
	 */
	public SortedList<Publication> getField(String forName)
	{
		SortedList<Publication> answer = new SortedList<Publication>();
		for(int i=0;i<5;i++)
		{
			SortedList<Publication> ans;
			if(( ans=fields.get(i).get(forName))!=null)
			{
				for(int j=0;j<ans.size();j++)
				{
					answer.add(ans.get(j));
				}
			}
		}
		return answer;
	}

	/*
	 *
	 */
	public SortedList<Publication> getRank(String rank)
	{
		// TODO get fields[rank], flatten, convert to SortedList
		SortedList<Publication> answer = new SortedList<Publication>();
		if(rank.compareTo("A*")==0)
		{
			TreeMap<String,SortedList<Publication>> treeMap=fields.get(0);
			for(Map.Entry<String,SortedList<Publication>> entry : treeMap.entrySet()) {
				for(int j =0;j<entry.getValue().size();j++)
				{
				
					answer.add(entry.getValue().get(j));
				}
			}
			return answer;
		}
		else if(rank.compareTo("A")==0)
		{
			TreeMap<String,SortedList<Publication>> treeMap=fields.get(1);
			for(Map.Entry<String,SortedList<Publication>> entry : treeMap.entrySet()) {
				for(int j =0;j<entry.getValue().size();j++)
				{
				
					answer.add(entry.getValue().get(j));
				}
			}
			return answer;
		}
		else if(rank.compareTo("B")==0)
		{
			TreeMap<String,SortedList<Publication>> treeMap=fields.get(2);
			for(Map.Entry<String,SortedList<Publication>> entry : treeMap.entrySet()) {
				for(int j =0;j<entry.getValue().size();j++)
				{
				
					answer.add(entry.getValue().get(j));
				}
			}
			return answer;
		}
		else if(rank.compareTo("C")==0)
		{
			TreeMap<String,SortedList<Publication>> treeMap=fields.get(3);
			for(Map.Entry<String,SortedList<Publication>> entry : treeMap.entrySet()) {
				for(int j =0;j<entry.getValue().size();j++)
				{
				
					answer.add(entry.getValue().get(j));
				}
			}
			return answer;
		}
		else if(rank.compareTo("D")==0)
		{
			TreeMap<String,SortedList<Publication>> treeMap=fields.get(4);
			for(Map.Entry<String,SortedList<Publication>> entry : treeMap.entrySet()) {
				for(int j =0;j<entry.getValue().size();j++)
				{
				
					answer.add(entry.getValue().get(j));
				}
			}
			return answer;
		}
		else
		{
			return null;
		}
	}

	/*
	 *
	 */
	public SortedList<Publication> getFieldRank(String field, String rank)
	{
		if(rank.compareTo("A*")==0)
		{
			return fields.get(0).get(field);
		}
		else if(rank.compareTo("A")==0)
		{
			return fields.get(1).get(field);
		}
		else if(rank.compareTo("B")==0)
		{
			return fields.get(2).get(field);
		}
		else if(rank.compareTo("C")==0)
		{
			return fields.get(3).get(field);
		}
		else if(rank.compareTo("D")==0)
		{
			return fields.get(4).get(field);
		}
		return null;
	}

	

	





/////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	public static void main(String[]args)
	{
		PublicationTree pubtree = new PublicationTree();
		
		/*List<String> liste;
		System.out.println(args.length);
	       	if ( args.length > 0 ) {
			liste = FileAccess.ReadFromFile(args[0]);
		}
		else {
			liste = FileAccess.ReadFromFile("Journals.csv");
			liste.remove(0);
		}

		System.out.println(liste.size());
		for(String s : liste)
		{
			if ( s.length() > 0 ) {
				//System.out.println(s);
				pubtree.put(s);
			}
		}*/

		pubtree.put("A*,Monthly Notices of the Royal Astronomical Society: Letters,201,Astronomical and Space Sciences,,,,");
		pubtree.put("C,Journal of Global Business Management,1503,Business and Management,,,,");
		pubtree.put("B,American Journal of Criminal Law,1801,Law,,,,");
		pubtree.put("C,European Journal of Protistology,605,Microbiology ,1108,Medical Microbiology,,");
		pubtree.put("A,Institut Fourier. Annales,101,Pure Mathematics,,,,");
		pubtree.put("B,Ming Studies,1699,Other Studies In Human Society,,,,");
		pubtree.put("B,Statistics Education Research Journal,104,Statistics ,,,,");

		// Tests
		System.out.println(pubtree.getString("Monthly Notices of the Royal Astronomical Society: Letters"));
		SortedList<Publication>rankedC = pubtree.getRank("C");
		for(int i=0;i<rankedC.size();i++)
		{
			System.out.println(rankedC.get(i).getName());
		}
	}
}
