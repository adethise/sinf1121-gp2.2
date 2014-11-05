import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Class PublicationTree
 * This class allow the user to put Publication objects
 * in a TreeMap and retrieve them, in object or String form.
 *
 * Note that the TreeMap class use a Red-Black tree based implementation
 */
public class PublicationTree extends PublicationMap {
	
	private TreeMap<String, Publication> principal;

	/*
	 * Class constructor
	 */
	PublicationTree()
	{
		principal = new TreeMap<String,Publication>();
	}

	/*
	 * Add pub in the tree
	 */
	public void put(Publication pub)
	{
		// TODO
	}
	
	/*
	 *
	 */
	public Publication get(String key)
	{
		// TODO
	}

	/*
	 * Get a string representation of the
	 * publication with name <key> from the Map.
	 */
	public String getString(String key)
	{
		return get(key).toString();
	}

	// TODO : add new methods (subMap, allEntries, ...)
	
	
	public static void main(String[]args)
	{
		PublicationTree pubmap = new PublicationTree();

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
