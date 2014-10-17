import java.util.ArrayList;
import java.util.List;


public class DerivationTest {
	public static void main(String[] args) 
	{		
		List<String> file = new ArrayList<String>(); 
		file = FileAccess.ReadFromFile("expression.txt");
		String derivees = new String();
		
		for (String x : file)
		{
			OperationTree OT = new OperationTree(x);
			OT.derivate();
			derivees = derivees + OT.toString() + " "; //Les derivees sont mises dans un string a la suite l'une de l'autre
			                                           //HELP: Je n'arrive pas a les mettre l'une en-dessous de l'autre dans le fichier
		}
		FileAccess.WriteInFile("fin.txt", derivees); 
	}
}
