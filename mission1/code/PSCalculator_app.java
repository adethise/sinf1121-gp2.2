import java.util.ArrayList;
import java.util.List;


public class PSCalculator_app {

	public static void main(String[] args) {		
		
		List<String> file = new ArrayList<String>(); 
		file = FileAcces.ReadFromFile("ps.txt");
		
		System.out.print(file.toString());
	}

}
