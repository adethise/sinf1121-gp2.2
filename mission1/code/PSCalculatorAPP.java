import java.util.ArrayList;
import java.util.List;


public class PSCalculatorAPP {

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
