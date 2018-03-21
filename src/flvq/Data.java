package flvq;

import flvq.*;
import java.util.*;

/*	Data Class
		Defines the data inside the feature space/data space

		If the data is of type Neuron, it contains:
			- Category
			- Weight

		If the data is of type Input, it contains:
			- Location

		Also contains the getters and setters
*/
public class Data {
	
	// Holds the type of data (neuron or input)
	private Type type = Type.INPUT;

	//	Holds the weight vector if the data is a neuron 
	//	Holds the location for in the space if an input
	//		Values are determined by the user
	private double[] attrib = null;

	// Holds the category for the data
	//	Utilized both for neurons and inputs
	//	Values are determined by the user
	private int category;

	// Holds value to determins if neuron is winner
	private int is_winner;


	// Constructor for Data
	//	Type must be specified in parameters (required)
	public Data(Type type) {
		this.type = type;
		// this.is_winner = 0;	// Not winner
	}
		
	public Data() {}

	// Method for changing / updating values of the weight
	//	For neurons 
	public void update_attrib_values(FLVQNet net, double[] new_values) {
		this.attrib = new_values;
	}

	// This is to generate random weights
	public void init_weight(int limit1, int limit2) {
		if (this.get_type() != Type.NEURON) {
			System.err.println("Invalid usage");
			System.exit(0);
		}
				
		Random rand = new Random();
		double attr1 = (double) showRandomInteger(0, limit1, rand);
		double attr2 = (double) showRandomInteger(0, limit2, rand);

		this.set_attrib(new double[] {attr1, attr2});
	}

        
        // From http://www.javapractices.com/topic/TopicAction.do?Id=62
	private static int showRandomInteger(int aStart, int aEnd, Random aRandom){
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
	
		//get the range, casting to long to avoid overflow problems
		long range = (long)aEnd - (long)aStart + 1;
	
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long)(range * aRandom.nextDouble());
		int randomNumber =  (int)(fraction + aStart);    
		
		return randomNumber;
	}
	

	// ----- Getters and Setters -----
	// Type 
	public Type get_type() {
		return this.type;
	}

	public void set_type(Type type) {
		this.type = type;
	}

	// attrib 
	public double[] get_attrib() {
		return this.attrib;
	}

	public void set_attrib(double[] attrib) {
		this.attrib = attrib.clone();
				// this.attrib = Arrays.copyOf(attrib, attrib.length);
				// System.arraycopy(attrib, 0, this.attrib, 0, attrib.length);
	}

	// category 
	public int get_category() {
		return this.category;
	}

	public void set_category(int category) {
		this.category = category;
	}
}