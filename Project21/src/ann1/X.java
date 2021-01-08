package ann1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class X {
	public static double[][] getX(String file) {  
		List<double[]> xx =  new ArrayList<>();
		double [][] x;
	    try {
	      File myObj = new File(file);
	      Scanner myReader = new Scanner(myObj);  
	      while (myReader.hasNext()) {
	        double[] xi = new double[4];
	        String data = myReader.nextLine();
	        String[] s = data.split(" ");
	        for (int j = 0; j < 4; j++) {
				xi[j] = Double.parseDouble(s[j]);
//				System.out.println(xi[j]);
			}
	        xx.add(xi);
			}
	      
	      x = new double[xx.size()][];
	      for (int i = 0; i < xx.size(); i++) {
	    	  x[i] = (double[]) xx.get(i);
	      }
	    
	      myReader.close();
	      return x;
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    } 
	    return null;
	  }  
	public static void main(String[] args) {
		getX("d5");
	}
}
