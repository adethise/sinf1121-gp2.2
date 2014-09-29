import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PSCalculatorAPP_test extends PSCalculator_test {

	public static void main(String[] args) {
		
		int r;
		float a,b;

		// test : isFloat
		r = 0;
		if (!isFloat("1")) r++;
		if (!isFloat("0")) r++;
		if (!isFloat("0.1523")) r++;
		if (!isFloat("158494241.21")) r++;
		if (!isFloat("454685413561681861431.1681531568186")) r++;

		if (isFloat("")) r++;
		if (isFloat("azer")) r++;
		if (isFloat("014.12o")) r++;
		if (isFloat("????")) r++;
		if (isFloat("/pi")) r++;

		System.out.println( r==0 ? "No error detected in isFloat()" : "WARNING: error detected in isFloat()");

		// test : getFloatFromStack
		r = 0;
		for (int _i = 0 ; _i < 10 ; _i++)
		{
			a = ((float) (Math.random()-0.5))*100000;
			PSStack.push(a);
			if (getFloatFromStack() != a) r++;
		}
		PSStack.push(0);
		if (getFloatFromStack() != 0) r++;
		System.out.println( r==0 ? "No error detected in getFloatFromStack()" : "WARNING: error detected in getFloatFromStack()");


		// test : add
		r = 0;
		for (int _i = 0 ; _i < 10 ; _i++)
		{
			a = ((float) (Math.random()-0.5))*100;
			b = ((float) (Math.random()-0.5))*-100;
			PSStack.push(a);
			PSStack.push(b);
			add();
			if (getFloatFromStack() != a+b) r++;
		}
		System.out.println( r==0 ? "No error detected in add()" : "WARNING: error detected in add()");

		// test : sub
		r = 0;
		for (int _i = 0 ; _i < 10 ; _i++)
		{
			a = ((float) (Math.random()-0.5))*100;
			b = ((float) (Math.random()-0.5))*-100;
			PSStack.push(a);
			PSStack.push(b);
			sub();
			if (getFloatFromStack() != b-a) r++;
		}
		System.out.println( r==0 ? "No error detected in sub()" : "WARNING: error detected in sub()");
		
		// test : mul
		r = 0;
		for (int _i = 0 ; _i < 10 ; _i++)
		{
			a = ((float) (Math.random()-0.5))*100;
			b = ((float) (Math.random()-0.5))*-100;
			PSStack.push(a);
			PSStack.push(b);
			mul();
			if (getFloatFromStack() != a*b) r++;
		}
		System.out.println( r==0 ? "No error detected in mul()" : "WARNING: error detected in mul()");

		// test : div
		r = 0;
		for (int _i = 0 ; _i < 10 ; _i++)
		{
			a = ((float) (Math.random()-0.5))*100;
			b = ((float) (Math.random()-0.5))*-100;
			PSStack.push(a);
			PSStack.push(b);
			div();
			if (getFloatFromStack() != b/a) r++;
		}
		System.out.println( r==0 ? "No error detected in div()" : "WARNING: error detected in div()");

	}

}
