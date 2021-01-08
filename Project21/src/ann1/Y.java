package ann1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Y {

	public static double[][] getY(String file) {  
		List<double[]> xx =  new ArrayList<>();
		double [][] x;
	    try {
	      File myObj = new File(file);
	      Scanner myReader = new Scanner(myObj);  
	      while (myReader.hasNext()) {
	        double[] xi = new double[1];
	        String data = myReader.nextLine();
	        String[] s = data.split(" ");
			xi[0] = Double.parseDouble(s[5]);
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
		getY("ai7vs3");
	}
}
