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
	private SortedList all; // contient toutes les pub pour tout retourner
	private ArrayList<TreeMap<String, SortedList>> fields;
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
		all = new SortedList();
		fields = new ArrayList<TreeMap<String, SortedList>>();
		for(int i=0;i<5;i++)
		{
			TreeMap<String,SortedList> arbre = new TreeMap<String, SortedList>();
			fields.add(arbre);
		}
		// 0=A* ; 1=A ; 2=B ; 3=C ;...
		
	}

	/*
	 
	 * Ajoute la publication partout où elle doit être ajoutée
	 *
	 */
	public void putInTree(Publication pub,TreeMap<String,SortedList> treemap)
	{
		String [][] table = pub.getTable();
		for (int i = 0 ; i<3 && !(table[0][i].equals("")) ; i++)
		{
			SortedList publist = treemap.get(table[1][i]);
			
			if(publist==null)
			{
				SortedList newsorted = new SortedList();
				newsorted.add(pub);
				treemap.put(table[1][i],newsorted);
			
			}
			else
			{
				publist.add(pub);
				
			}

			
			
		}
	}
	public void put(Publication pub)
	{
		//Création de la HashMap récupérée du projet précédent
		super.put(pub);
		all.add(pub);
		
		//Ajout dans la nouvelle TreeMap en fonction du rang correspondant
		if(pub.getRank().compareTo("A*")==0)
		{
			putInTree(pub,fields.get(0));
		}
		else if(pub.getRank().compareTo("A")==0)
		{
			putInTree(pub,fields.get(1));
		}
		else if(pub.getRank().compareTo("B")==0)
		{
			putInTree(pub,fields.get(2));
		}
		else if(pub.getRank().compareTo("C")==0)
		{
			putInTree(pub,fields.get(3));
		}
		else if(pub.getRank().compareTo("D")==0)
		{
			putInTree(pub,fields.get(4));
		}

		
		
}

	/*
	 *Retourne toutes les publications
	 */
	public ArrayList<Publication> getAll()
	{
		return all.getall();
	}

	/*
	 * Retourne toutes les publications associees au champs de recherche forName (Law,Science,...)
	 */
	public ArrayList<Publication> getField(String forName)
	{
		SortedList answer = new SortedList();
		for(int i=0;i<5;i++)
		{
			SortedList ans;
			if(( ans=fields.get(i).get(forName))!=null)
			{
				System.out.println("Taille de la liste :"+ans.size());
				ArrayList<Publication> sousliste = ans.getall();
				for(int j =0;j<sousliste.size();j++)
				{
					answer.add(sousliste.get(j));
				}
			}
		}
		return answer.getall();
	}
	
	//Sous fonction nécessaire au bon fonctionnement de getRank. Ajoute à une liste l'ensemble des publications
	//présente dans la treemap passée en argument
	public void getRanksub(SortedList liste,TreeMap<String,SortedList> treemap)
	{
		for(Map.Entry<String,SortedList> entry : treemap.entrySet()) 
		{
			
			SortedList li = treemap.get(entry.getKey());
			
			ArrayList<Publication> array =  li.getall();
			for(int i=0;i<array.size();i++)
			{
				liste.add(array.get(i));
			}
			
		}
	}
	/*
	 * Renvoie l'ensemble des publications ayant un certain rang (A*,A,B,C,D)
	 */
	public ArrayList<Publication> getRank(String rank)
	{
		SortedList answer = new SortedList();
		if(rank.compareTo("A*")==0)
		{
			getRanksub(answer,fields.get(0));
		}
		else if(rank.compareTo("A")==0)
		{
			getRanksub(answer,fields.get(1));
		}
		else if(rank.compareTo("B")==0)
		{
			getRanksub(answer,fields.get(2));
		}
		else if(rank.compareTo("C")==0)
		{
			getRanksub(answer,fields.get(3));
		}
		else if(rank.compareTo("D")==0)
		{
			getRanksub(answer,fields.get(4));
		}
		else
		{
			return null;
		}
		
	
		return answer.getall();
	}

	/*
	 * Retourne l'ensemble des publications correspondant à un certain domaine ET ayant un certain rank
	 */
	public ArrayList<Publication> getFieldRank(String field, String rank)
	{
		if(rank.compareTo("A*")==0)
		{
			return fields.get(0).get(field).getall();
		}
		else if(rank.compareTo("A")==0)
		{
			return fields.get(1).get(field).getall();
		}
		else if(rank.compareTo("B")==0)
		{
			return fields.get(2).get(field).getall();
		}
		else if(rank.compareTo("C")==0)
		{
			return fields.get(3).get(field).getall();
		}
		else if(rank.compareTo("D")==0)
		{
			return fields.get(4).get(field).getall();
		}
		return null;
	}

	

	





/////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	public static void main(String[]args)
	{
		PublicationTree pubtree = new PublicationTree();
		
		List<String> liste;
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
		}

		/*
		 * 
		 * Ces tests étaient réalisés afin de pouvoir analyser plus en détail le bon fonctionnement de l'implémentation
		 * et de ne pas commencer directement les tests avec 20000 éléments.
		 * 
		pubtree.put("B,American Journal of Criminal Law,1801,Law,,,,");
		pubtree.put("A*,Monthly Notices of the Royal Astronomical Society: Letters,201,Astronomical and Space Sciences,,,,");
		pubtree.put("C,Journal of Global Business Management,1503,Business and Management,,,,");
		
		pubtree.put("C,European Journal of Protistology,605,Microbiology ,1108,Medical Microbiology,,");
		pubtree.put("A,Institut Fourier. Annales,101,Pure Mathematics,,,,");
		pubtree.put("B,Ming Studies,1699,Other Studies In Human Society,,,,");
		pubtree.put("B,Statistics Education Research Journal,104,Statistics ,,,,");
		pubtree.put("B,Journal of Law and Policy,1801,Law,,,,");
		
		ArrayList<Publication> all = pubtree.getAll();
		System.out.println("Printing all Publications : ");
		for(int i=0;i<all.size();i++)
		{
			System.out.println(all.get(i).getName());
		}
		*/
		
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n TEST GETRANK\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		ArrayList<Publication> rank = pubtree.getRank("B");
		System.out.println("Ensemble des publications avec le rang B :");
		for(int i=0;i<rank.size();i++)
		{
			System.out.println(pubtree.getString(rank.get(i).getName()));
		}
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n TEST GETFIELD\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		ArrayList<Publication> field =pubtree.getField("Law"); 
		for(int i=0;i<field.size();i++)
		{
			System.out.println(pubtree.getString(field.get(i).getName()));
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n TEST GETFIELDRANK\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		ArrayList<Publication> fieldrank =pubtree.getFieldRank("Law","B"); 
		for(int i=0;i<fieldrank.size();i++)
		{
			System.out.println(pubtree.getString(fieldrank.get(i).getName()));
		}
		
		System.out.println("Done !");
	
	}
}
