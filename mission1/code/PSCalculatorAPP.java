import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PSCalculatorAPP {

	public static void main(String[] args) {		
		
		DNodeStack<Object>  stack = new DNodeStack<Object>();
		List<String> file = new ArrayList<String>(); 
		file = FileAccess.ReadFromFile("ps.txt");
		PSCalculator PS = new PSCalculator();
		
		for (String x : file)
		{
			PS.feedStackFromLine(x);		    
		}
		FileAccess.WriteInFile("ps_Result.txt", PS.getOutString());
	}

}
