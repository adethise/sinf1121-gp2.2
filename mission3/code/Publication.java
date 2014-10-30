

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
			System.out.println(name);

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

		/*
			String[] splitted = line.split(",");
			
			setName(splitted[1]);
			setRank(splitted[0]);
			table = new String[2][3];
			
			for (int i=1 ; 2*i< splitted.length; i++)
			{
				
				
				table[0][i-1] = splitted[2*i];
				
				table[1][i-1] = splitted[2*i+1];
			}
		*/
			
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
}
