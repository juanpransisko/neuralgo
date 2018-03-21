package lvq;

import java.util.*;
import java.io.*;
import java.util.logging.*;

public class InputData {

	// This is to normalize inputs (double) save it in ArrayList
	//	Takes a file as input; Each line represents a coordinate for an input
	//	Parses each line, separates by comma, stores in a double array
	//	Stores each array of double numbers in an ArrayList of inputs
	public static Data[] get_input(File file_input) throws IOException {
		
		// Holds the data for each line in the file
		String line;

		// Holds the data for all of the inputs in the file
		Data[] inputs = null;
		
		try {
			System.out.println(file_input.getCanonicalPath());
			int lines = 0;
						
			Scanner scan;
			FileReader file_reader= new FileReader(file_input);
			BufferedReader buff_reader = new BufferedReader(file_reader);

			while (buff_reader.readLine() != null) lines ++;
			inputs = new Data[lines];

			double[] data;
			int i = 0;
			int idx = 0;

			while((line = buff_reader.readLine()) != null) {
                                System.out.println("HOY!");
                            
//				String[] points = line.split(",");
//				System.out.println("HOY!");
//
//				data = new double[points.length];
//				for(String str : points) {
//					data[i] = Double.parseDouble(str);
//				}
//				
//				inputs[idx] = new Data(Type.INPUT);
//				inputs[idx++].set_attrib(data);
//						
//				System.out.println(Arrays.toString(inputs[idx].get_attrib()));
			}
			
			buff_reader.close();
			System.out.println("Hoy");
		}
		catch (FileNotFoundException ex) {
			Logger.getLogger(LVQNetwork.class.getName()).log(Level.SEVERE, null, ex);
		} 

		return inputs;
	}
        
        // This is to normalize inputs (double) save it in ArrayList
	//	Takes a file as input; Each line represents a coordinate for an input
	//	Parses each line, separates by comma, stores in a double array
	//	Stores each array of double numbers in an ArrayList of inputs
	public ArrayList<double[]> get_input() throws IOException {
		File file_input = new File("");
		// Holds the data for each line in the file
		String line;

		// Holds the data for all of the inputs in the file
		ArrayList<double[]> inputs = new ArrayList<>();
		
		try {
			System.out.println(file_input.getCanonicalPath());
			
			Scanner scan;
			FileReader file_reader = new FileReader(file_input);
			BufferedReader buff_reader = new BufferedReader(file_reader);
			
			double[] data;
			int size = 0;
			int i = 0;

			while((line = buff_reader.readLine()) != null) {
				String[] points = line.split(",");

				data = new double[points.length];
				for(String str : points) {
					data[i] = Double.parseDouble(str);
				}
				inputs.add(data);
			}
			
			
			
            
            System.out.println(inputs.size());
			file_reader.close();
			buff_reader.close();
		}
		catch (FileNotFoundException ex) {
			Logger.getLogger(LVQNetwork.class.getName()).log(Level.SEVERE, null, ex);
		} 

		return inputs;
	}

	
}
