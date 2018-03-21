
import lvq.Data;
import lvq.Type;
import lvq.LVQNetwork;

import java.util.Arrays;

/*	DataTest Class
	for UNIT TEST of Data 

	Checks for methods and attributes
*/
public class DataTest {

	public static void main(String[] args) {
		
		System.out.println("\n\nUnit Test for LVQ Data \n");
		System.out.println("Initializing... ");
		
		Data neuron = new Data(Type.NEURON);	// neuron
		Data input = new Data(Type.INPUT);		// input

		Data[] neurons = new Data[5];			// Multiple neurons
		Data[] inputs = new Data[10];			// Multiple inputs
		

		// Single neuron
		System.out.println("Setting values for single neuron START... ");
		neuron.set_attrib(new double[] {2, 1, 2});		// Weight
		neuron.set_category(1);		//	Category Ex:	1 = Apple	2 = Orange
		System.out.println("Setting values for single neuron DONE... \n");

		// Single input
		System.out.println("Setting values for single input START... ");
		input.set_attrib(new double[] {0, 0, 1});
		input.set_category(2);
		System.out.println("Setting values for single input DONE... \n");		

		// Multiple neurons
		System.out.println("Setting values for multiple neurons START...");
		
		for (int idx = 0; idx < 2; idx++) {
			neurons[idx] = new Data(Type.NEURON);
			neurons[idx].set_attrib(new double[] {idx, ++idx, --idx});
			neurons[idx].set_category(1);
		}

		for (int idx = 2; idx < 5; idx++) {
			neurons[idx] = new Data(Type.NEURON);
			neurons[idx].set_attrib(new double[] {idx, ++idx, --idx});
			neurons[idx].set_category(2);
		}
		System.out.println("Setting values for multiple neurons DONE...\n");

		// Multiple inputs
		System.out.println("Setting values for multiple inputs START...");
		
		for (int idx = 0; idx < 5; idx++) {
			inputs[idx] = new Data(Type.INPUT);
			inputs[idx].set_attrib(new double[] {idx, ++idx, --idx});
			inputs[idx].set_category(1);
		}

		for (int idx = 5; idx < 10; idx++) {
			inputs[idx] = new Data(Type.INPUT);
			inputs[idx].set_attrib(new double[] {idx, ++idx, --idx});
			inputs[idx].set_category(2);
		}
		System.out.println("Setting values for multiple inputs DONE...\n");	

		System.out.println("Initialization DONE...\n\n");

		// Print values
		// Single neuron 
		System.out.print("Single neuron weight = " + Arrays.toString(neuron.get_attrib()));
		System.out.println("\nSingle neuron category = " + neuron.get_category() + "\n");

		// Single input 
		System.out.println("Single input point = " + Arrays.toString(input.get_attrib()));
		System.out.println("\nSingle input category = " + input.get_category() + "\n");

		int jx = 0;

		// Multiple neurons
		while (jx < neurons.length) {
			System.out.print("Neuron no." + jx + " weight = " + Arrays.toString(neurons[jx].get_attrib()));
			System.out.println("\nNeuron no." + jx + " category = " + neurons[jx].get_category() + "\n");

			jx ++;
		}

		jx = 0;

		// Multiple inputs
		while (jx < inputs.length) {
			System.out.print("Input no." + jx + " weight = "  + Arrays.toString(inputs[jx].get_attrib()));
			System.out.println("\nInput no." + jx + " category = " + inputs[jx].get_category() + "\n");

			jx ++;
		}

		System.out.println("\n\n TEST DONE...");

		
	}

}