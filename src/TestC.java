
public class TestC {

	static void add(int a,int b)
	{
		int c = a + b;
		System.out.println("Sum is "+c);
	}
	
	static int sum(int i,int j)
	{
		int r = i + j;
		return r;
	}
	
	public static void main(String[] args) {
		
		add(10,20);
		add(90,100);
		
		int t = sum(20,40);
		
		System.out.println("Sum is "+t);
		
	}
	
	
}
