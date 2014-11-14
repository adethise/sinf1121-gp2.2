/*
 * Class Publication
 *
 * This class represent a publication, as defined in the
 * Journals.csv file available in the instructions.
 *
 * It is represented by a name, a rank and up to 3
 * fields of research, each one also defined by a number.
 */
public class Publication {
	private String name;
	private String rank;
	private String[][] table;
	
	Publication(String line)
	{
		int index = -1;
		int newIndex = nextCommaIndex(line, index);
		setRank(line.substring(index+1, newIndex));

		index = newIndex;
		newIndex = nextCommaIndex(line, index);
		setName(line.substring(index+1, newIndex).replace("\"",""));
		

		table = new String[2][3];

		for (int i = 0 ; i < 3 ; i++) {
			index = newIndex;
			newIndex = nextCommaIndex(line, index);
			table[0][i] = line.substring(index+1, newIndex);
			if ( table[0][i].equals("MD") ) {
				table[0][i] = "0";
			}
			index = newIndex;
			newIndex = nextCommaIndex(line, index);
			table[1][i] = line.substring(index+1, ( newIndex == -1 ? line.length() : newIndex )).replace("\"","");
		}
	}

	/*
	 * Return the index of the first comma not between "" after
	 * index <start>.
	 */
	private static int nextCommaIndex(String s, int start)
	{ 
		if ( s.indexOf('"', start+1) == -1 || s.indexOf('"', start+1) > s.indexOf(',', start+1) ) {
			return s.indexOf(',', start+1);
		}
		else {
			return s.indexOf(',', s.indexOf('"', s.indexOf('"', start+1)+1));
		}
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String[][] getTable()
	{
		return table;
	}
	public int compareTo(Publication publi)
	{
		return name.compareTo(publi.getName());
	}
}
