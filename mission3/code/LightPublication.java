/*
 * This class is a lighter version of the class
 * Publication.
 * The name of the fields of research is not present in this
 * one, in order to save memory space.
 * They can be found by looking at their numeric representation.
 */
public class LightPublication {
	private String name;
	private String rank;
	private int [] reference;
	
	
	LightPublication(Publication publication)
	{
		this.setName(publication.getName());
		this.setRank(publication.getRank());
		String[][] ref = publication.getTable();
		reference = new int[3];
		for (int i = 0 ; i<3 && !(ref[0][i].equals("")) ; i++)
		{
			reference[i] = Integer.parseInt(ref[0][i]);
		}
	}

	LightPublication(String name,String rank, int[] reference)
	{
		this.setName(name);
		this.setRank(rank);
		this.reference = reference;
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
	public int[] getReference()
	{
		return reference;
		
	}
}
