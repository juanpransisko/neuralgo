package lvq;

import java.util.*;
import java.util.logging.*;
import java.io.*;
import java.lang.Math;

/*	LVQNetwork Class
	This is an implementation of Learning Vector Quantization

	** NOT YET OPTIMIZED **
	** For Thesis/Research utilization and purposes **
			
*/
public class LVQNetwork {
	
	// This holds the value for the learning rate of the lvq network holds values between 0.0 and 1.0
	//	Default is 0.5
	private double learning_rate;

	// This holds the number of iterations for training the network
	//	Default is 100
	private int iterations = 100;

	// Iteration counter
	private int itr = 0;	
		
	// The cluster n_neurons
	private Data[] neurons;

	// Holds the number of n_neurons
	private int n_neurons;

	// Number of clusters (outputs of the neural net)
	private int clusters;
		
	// Category type
	//  Default is 0
	private int category_type;

	// Holds the chosen random input
	private Data rand_input = null;

	// Holds the new weight
	private double[] new_weight;

	private int limit1, limit2;

	// Constructor for LVQNetworks ----------------------------------------
	//	Constructor to set the learning rate and iterations
	public LVQNetwork(double learn_rate, int iter) {
		this.learning_rate = learn_rate;
		this.iterations = iter;
		this.category_type = 0;
				
		System.out.println("LVQ Network initialized with:");
		System.out.println("Learning rate: " + this.learning_rate);
		System.out.println("Iterations: " + this.iterations);
		System.out.println("No. of Neurons: " + this.n_neurons);
		System.out.println("No. of Clusters: " + this.clusters + "\n");
	}

	// 	This requires the number of n_neurons and clusters, 
	//	default learn rate and iterations
	public LVQNetwork(int n_neurons, int clusters) {
		this.learning_rate = 0.5;
		this.iterations = 100;
		this.category_type = 0;
				
		System.out.println("LVQ Network initialized with:");
		System.out.println("Learning rate: " + this.learning_rate);
		System.out.println("Iterations: " + this.iterations);
		System.out.println("No. of Neurons: " + this.n_neurons);
		System.out.println("No. of Clusters: " + this.clusters + "\n");
	}

	//	This requires the number of iterations and learning rate
	//		nuber of n_neurons and cluster
	public LVQNetwork(double learn_rate, int iter, int n_neurons, int n_clusters, int cat_type, int lim1, int lim2) {
		this.learning_rate = learn_rate;
		this.iterations = iter;
		this.n_neurons = n_neurons;
		this.clusters = n_clusters;
		this.category_type = cat_type;
		this.limit1 = lim1;
		this.limit2 = lim2;
				
		System.out.println("LVQ Network initialized with:");
		System.out.println("Learning rate: " + this.learning_rate);
		System.out.println("Iterations: " + this.iterations);
		System.out.println("No. of Neurons: " + this.n_neurons);
		System.out.println("No. of Clusters: " + this.clusters + "\n");
	}
		// Constructor for LVQNetworks ----------------------------------------

	// This is to initialize the LVQ network
	public void initialize() {
		neurons = this.init_neurons();

		for (int i = 0; i < neurons.length; i ++) {
			System.out.print("Neuron no. " + i);
			System.out.print("| Category: " + neurons[i].get_category());
			System.out.println("| Weights: " + Arrays.toString(neurons[i].get_attrib()));
		}
	}
		
	//  This is for initializing the LVQ neurons
	//		Check all parameters, place the n_neurons, initialize weights, specify output
	//		Instantiate the n_neurons, normalize the data inputs
	//      Param for specifying if data reaches zero (0) or not (1)
	public Data[] init_neurons() {

		// Check neuron count and cluster count
		if (n_neurons <= 0) {
			System.err.println("Invalid neuron count.\n");
			System.exit(0);
		}
		else if (clusters <= 0) {
			System.err.println("Invalid cluster count.\n");
			System.exit(0);
		}
		
		// Neurons
		Data[] neu = new Data[this.get_neuron_count()];
		for (int idx = 0; idx < neu.length; idx ++) {
			neu[idx] = new Data(Type.NEURON);
			neu[idx].init_weight(this.get_limit1(), this.get_limit2());
		}
		
		// Assign Category to neuron 	OPTIMIZE THIS PART
		int idx = 0;
		int icx = this.get_category_type();
		
		if (icx == 1) {
			while (idx < (this.get_neuron_count())) {
				if (icx == this.get_cluster_count()) icx = 1;
				neu[idx].set_category(icx);
				icx ++; idx ++;
			}
		}
		else if (icx == 0) {
			while (idx < (this.get_neuron_count())) {
				if (icx == this.get_cluster_count()) icx = 0;
				neu[idx].set_category(icx);
				icx ++; idx ++;
			}
		}

		return neu;
	}


	// This method is to train the lvq network
	public void train(Data[] enpot, int bound) {

		while (this.itr != this.iterations) {
			// Get random input
			int index = this.get_rand_input(bound);	// 1000 inputs
			rand_input = enpot[index];

			// Get winner neuron
			int winner_pos = this.get_winner_neuron(rand_input, neurons); 

			// Log
                        
			System.out.print("Iter: " + (this.itr + 1));
			System.out.print(" | R.INP_ATR: " + Arrays.toString(rand_input.get_attrib()) + " | INP_CAT: " + rand_input.get_category());
			System.out.println(" | W.NEU_ATR: " + Arrays.toString(neurons[winner_pos].get_attrib()) + " | NEU_CAT: " + neurons[winner_pos].get_category());
			
			// Check category, get new weights
			if (neurons[winner_pos].get_category() == rand_input.get_category()) {
				new_weight = attraction(rand_input.get_attrib(), neurons[winner_pos].get_attrib(), this.get_learn_rate());
				System.out.print("\t attraction ");
			} else {
				new_weight = repulsion(rand_input.get_attrib(), neurons[winner_pos].get_attrib(), this.get_learn_rate());
				System.out.print("\t repulsion ");
			}
			
			// Update weight
			neurons[winner_pos].set_attrib(new_weight);

			System.out.println("\t| Updated attrib: " + Arrays.toString(neurons[winner_pos].get_attrib()) + "");

			this.itr ++;	// increment iteration counter
			// this.learning_rate = (this.learning_rate * 0.5);	// decrease learning rate
		}
		
		for (int i = 0; i < neurons.length; i ++) {
			System.out.print("Neuron no. " + i);
			System.out.print("| Category: " + neurons[i].get_category());
			System.out.println("| Weights: " + Arrays.toString(neurons[i].get_attrib()));
		}
	}

	// This is to get random input vector
	public int get_rand_input(int bound) {
		return new Random().nextInt(bound);
	}

	// to compute metric distance
	public double euclidean_dist(double[] input, double[] neuron) {
		return Math.sqrt(Math.pow((input[0] - neuron[0]), 2) + Math.pow((input[1] - neuron[1]), 2));
	}
		
	// To get the winner neuron [neuron closest to the random input]
	public int get_winner_neuron(Data input, Data[] neurons) {
		if (input.get_type() != Type.INPUT|| neurons[0].get_type() != Type.NEURON) 
			System.err.println("Invalid parameters");

		//Data winner = null;
		int winner_pos = 0;
		double curr_dist;
		double min_dist = 1000;

		// Get the distance from each neuron
		for (int pos = 0; pos < neurons.length; pos ++) {
			curr_dist = this.euclidean_dist(input.get_attrib(), neurons[pos].get_attrib());
			// System.out.println("Current distance from input neuron: " + curr_dist);
			
			if (curr_dist < min_dist) {
				min_dist = curr_dist;
				winner_pos = pos;
			}
		}
		return winner_pos;
	}

	// Move the winner neuron nearer from the random input
	public double[] attraction(double[] input, double[] neuron, double learning_rate) {
		double[] new_values = new double[input.length];
		new_values[0] = neuron[0] + (learning_rate * (input[0] - neuron[0]));
		new_values[1] = neuron[1] + (learning_rate * (input[1] - neuron[1]));

		return new_values;
	}

	// Move the winner neuron farther from the random input
	public double[] repulsion(double[] input, double[] neuron, double learning_rate) {
		double[] new_values = new double[input.length];
		new_values[0] = neuron[0] - (learning_rate * (input[0] - neuron[0]));
		new_values[1] = neuron[1] - (learning_rate * (input[1] - neuron[1]));
		
		return new_values;	
	}
	
	// ----- Getters and Setters -----
	// Learning rate
	public double get_learn_rate() {
		return this.learning_rate;
	}

	public void set_learn_rate(double learning_rate) {
		this.learning_rate = learning_rate;
	}

	// Iterations
	public int get_iterations() {
		return this.iterations;
	}

	public void set_iterations(int iterations) {
		this.iterations = iterations;
	}

	// N Neurons
	public int get_neuron_count() {
		return this.n_neurons;
	}

	public void set_neuron_count(int n) {
		this.n_neurons = n;
	}

	// Clusters
	public int get_cluster_count() {
		return this.clusters;
	}

	public void set_cluster_count(int c) {
		this.clusters = c;
	}
		
	// Category type
	public int get_category_type() {
		return this.category_type;
	}
		
	public void set_category_type(int type) {
		this.category_type = type;
	}

	// Limits
	public int get_limit1() {
		return this.limit1;
	}
		
	public void set_limit1(int limit1) {
		this.limit1 = limit1;
	}

	public int get_limit2() {
		return this.limit2;
	}
		
	public void set_limit2(int limit2) {
		this.limit2 = limit2;
	}


}