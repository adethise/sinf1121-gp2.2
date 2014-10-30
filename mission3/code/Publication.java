

public class Publication {
		private String name;
		private String rank;
		private String[][] table;
		
		Publication(String line)
		{
			String[] splitted = line.split(",");
			
			setName(splitted[1]);
			setRank(splitted[0]);
			table = new String[2][3];
			
			for (int i=1 ; 2*i< splitted.length; i++)
			{
				
				
				table[0][i-1] = splitted[2*i];
				
				table[1][i-1] = splitted[2*i+1];
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
}
