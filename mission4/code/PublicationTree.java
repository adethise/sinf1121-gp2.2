import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	private TreeMap<Integer, SortedList<Publication>> fields[];
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
		fields = new TreeMap<Integer, SortedList<Publication>>[5];
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
		// TODO ajouter pub dans all et fields
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
	public SortedList<Publication> getField(String rofName)
	{
		// TODO getField() for each rank and merge results
	}

	/*
	 *
	 */
	public SortedList<Publication> getRank(String rank)
	{
		// TODO get fields[rank], flatten, convert to SortedList
	}

	/*
	 *
	 */
	public SortedList<Publication> getFieldRank(String field, String rank)
	{
		// TODO getField() in fields[rank]
	}

	

	





/////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	public static void main(String[]args)
	{
		PublicationMap pubmap = new PublicationMap();

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
				pubmap.put(s);
			}
		}

		// Tests
		System.out.println(pubmap.getString("Neuroimage"));
		System.out.println(pubmap.getString("Cardozo Public Law, Policy and Ethics Journal"));
	}
}
