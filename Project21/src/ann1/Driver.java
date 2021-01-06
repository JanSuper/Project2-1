package ann1;
import java.util.List;

public class Driver {
	
	/**
	 * Train set: 
	 * 		X[i] = {coefficients of the evaluation function in game i}
	 * 		Y[i] = {result of the game i} (1 for a win, 0 for a lose)
	 * 
	 */
	
	
	static double [][] X= {
			{0,0},
			{1,0},
			{0,1},
			{1,1}
	};
	static double [][] Y= {
			{0},{1},{1},{0}
	};

	static int getIndexOfLargest( double[] array )
	{
	  if ( array == null || array.length == 0 ) return -1; // null or empty

	  int largest = 0;
	  for ( int i = 1; i < array.length; i++ )
	  {
	      if ( array[i] > array[largest] ) largest = i;
	  }
	  return largest; // position of the first largest found
	}
	
	
	public static void main(String[] args) {
		// i=2, h=10, o=1
		NeuralNetwork nn = new NeuralNetwork(2,10,1);
		
		
		List<Double>output;
		
		nn.fit(X, Y, 50000);
		
		int N = 1000;
		double [][] input = new double[N][];
		double [] out = new double[N];
		
		
		for (int i = 0; i < N; i++) {
			double [] coeff = new double[] {Math.random()*2-1, Math.random()*2-1};
			input[i] = coeff;
		}
		
		int i = 0;
		for(double d[]:input)
		{
			output = nn.predict(d);
			out[i] = output.get(0);
			i++;
			//System.out.println(output.toString());
		}		

		double[] result = input[getIndexOfLargest(out)];
		for (int j = 0; j < result.length; j++) {
			System.out.println("coeff_" + j  + " = " + result[j]);
		}
	}

}