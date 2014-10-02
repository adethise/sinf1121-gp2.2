import java.util.ArrayList;
import java.util.List;


public class PSCalculatorAPP {
	/*
	 * @pre: 2 arguments, le premier est le chemin d'accès du fichier qu'il faut lire.
	 * 		 Le second est le nom du fichier dans lequel il faut écrire les résultats.
	 * @post: prends les insctructions et valeurs dans le fichier 1, interprete le language Post-script
	 * 		  et ensuite écrit le résultat dans un fichier.
	 */
	public static void main(String[] args) {		
		
		List<String> file = new ArrayList<String>(); 
		file = FileAccess.ReadFromFile(args[0]);
		PSCalculator PS = new PSCalculator();
		
		for (String x : file)
		{
			PS.feedStackFromLine(x);		    
		}
		FileAccess.WriteInFile(args[1], PS.getOutString());
	}

}
