package flvq;


import java.util.*;
import java.io.*;

public class FLVQMain {
	
	public static void main(String[] args) throws IOException {

		/** Crime flvq network
		 *		learning rate:	0.9
		 *		iterations:		10000
		 *		neurons:		9
		 *		clusters:		3 (Murder, Rape, Robbery)
		 *		type:			0 			*/
		FLVQNet crime_flvq = new FLVQNet(0.9, 1000, 60, 2, 0, 700, 1);
	
		/** Victim gender flvq network
		 *		learning rate:	0.9
		 *		iterations:		10000
		 *		neurons:		10
		 *		clusters:		2 (Male, Female)
		 *		type:			0 			*/
		// FLVQNet gender_flvq = new FLVQNet(0.9, 10000, 10, 2, 0, 700, 0);

		/** Victim age group flvq network
		 *		learning rate:	0.9
		 *		iterations:		10000
		 *		neurons:		10
		 *		clusters:		7 (AG1, AG2, AG3, AG4, AG5, AG6, AG7)
		 *		type:			0 			*/
		// FLVQNet age_group_flvq = new FLVQNet(0.9, 10000, 10, 2, 0, 700, 0);


		// Network initialization
		crime_flvq.initialize();
		// gender_flvq.initialize();
		// age_group_flvq.initialize();

		
		/* For crime LVQ training data
		 *	Attributes are:
		 *		- crime location (1-300)
		 *		- crime time (0-6)		*/
		double[][] tr_crime_lvq = new double[][] {
			{693,2}, {666,0}, {20,1}, {694,2}, {699,4}, {105,5}, {050,5}, {454,4}, {105,0}, {395,1}, 
			{162,0}, {143,3}, {155,2}, {192,4}, {401,2}, {145,5}, {244,4}, {675,4}, {466,1}, {219,0},
			{651,5}, {216,3}, {668,0}, {450,2}, {651,4}, {004,1}, {669,1}, {669,4}, {020,2}, {92,0}, 
			{467,3}, {52,5}, {232,0}, {674,2}, {653,4}, {668,4}, {209,3}, {469,4}, {238,5}, {105,0}, 
			{660,0}, {681,3}, {676,0}, {699,2}, {219,5}, {20,0}, {50,4}, {659,3}, {106,5}, {149,3}, 
			{239,4}, {163,0}, {123,5}, {88,2}, {178,1}, {20,5}, {682,5}, {48,2}, {404,4}, {217,5}, 
			{133,4}, {655,4}, {686,0}, {105,4}, {479,5}, {128,5}, {10,0}, {9,3}, {464,0}, {696,1}, 
			{213,5}, {61,1}, {655,4}, {251,0}, {46,5}, {395,3}, {669,5}, {96,4}, {699,4}, {6,2}, 
			{478,2}, {189,4}, {699,5}, {105,5}, {454,4}, {521,0}, {073,4}, {105,5}, {469,5}, {189,3}, 
			{660,0}, {109,0}, {96,4}, {669,0}, {224,3}, {227,1}, {251,0}, {224,0}, {684,3}, {651,1}, 
			{454,5}, {460,3}, {119,4}, {685,5}, {651,3}, {666,1}, {101,4}, {110,1}, {39,5}, {666,4}, 
			{181,3}, {257,5}, {687,2}, {471,4}, {651,4}, {232,1}, {651,3}, {057,2}, {697,0}, {478,2}, 
			{651,0}, {500,3}, {454,2}, {694,3}, {218,1}, {123,5}, {052,3}, {651,5}, {479,5}, {666,0}, 
			{2,0}, {666,5}, {696,4}, {660,1}, {201,1}, {479,5}, {479,2}, {670,5}, {685,4}, {464,2}, 
			{2,4}, {105,3}, {103,4}, {654,2}, {450,3}, {656,5}, {669,5}, {219,2}, {7,3}, {151,3}, 
			{123,5}, {209,5}, {419,3}, {669,0}, {194,4}, {020,3}, {138,1}, {212,4}, {043,5}, {201,4}, 
			{513,1}, {699,3}, {159,5}, {675,0}, {172,0}, {114,3}, {687,3}, {476,5}, {6,3}, {180,1}, 
			{18,0}, {674,0}, {257,5}, {675,0}, {689,2}, {196,1}, {666,3}, {659,4}, {151,5}, {658,5}, 
			{249,2}, {95,2}, {209,4}, {90,2}, {23,5}, {71,4}, {699,4}, {669,4}, {669,2}, {92,3}, 
			{660,2}, {470,5}, {208,3}, {151,4}, {651,0}, {699,0}, {671,0}, {199,1}, {36,4}, {58,3}, 
			{470,2}, {478,3}, {675,5}, {167,4}, {689,2}, {259,1}, {397,4}, {95,1}, {667,0}, {104,4}, 
			{254,0}, {184,2}, {699,5}, {651,3}, {187,4}, {653,2}, {203,4}, {51,0}, {191,0}, {404,0}, 
			{105,0}, {450,3}, {651,1}, {228,0}, {101,4}, {651,5}, {656,0}, {170,1}, {684,1}, {159,2}, 
			{522,3}, {655,2}, {678,5}, {660,3}, {139,0}, {100,2}, {667,5}, {120,4}, {49,5}, {20,4}, 
			{123,2}, {152,2}, {152,3}, {95,3}, {666,1}, {660,5}, {203,4}, {128,3}, {669,2}, {699,0}, 
			{669,4}, {454,5}, {668,1}, {107,3}, {79,0}, {666,1}, {95,1}, {677,0}, {95,3}, {175,5}, 
			{694,5}, {5,0}, {226,2}, {189,4}, {61,3}, {659,0}, {4,0}, {128,0}, {96,2}, {48,4}, 
			{669,3}, {20,0}, {33,3}, {20,4}, {52,5}, {161,1}, {246,0}, {466,3}, {401,4}, {61,5}, 
			{20,5}, {98,5}, {669,5}, {20,1}, {454,4}, {659,0}, {57,1}, {75,5}, {466,4}, {13,1}, 
			{061,0}, {016,0}, {669,0}, {673,4}, {149,5}, {651,5}, {020,5}, {156,0}, {698,5}, {659,4}, 
			{032,2}, {230,3}, {666,1}, {699,5}, {110,2}, {006,5}, {396,4}, {257,5}, {118,5}, {056,4}, 
			{119,4}, {189,4}, {672,2}, {692,3}, {199,5}, {651,0}, {656,0}, {200,4}, {675,2}, {185,0}, 
			{521,0}, {92,2}, {20,3}, {395,3}, {95,4}, {699,3}, {660,0}, {673,3}, {201,5}, {244,0}, 
			{404,0}, {105,1}, {187,0}, {9,4}, {113,2}, {105,0}, {264,4}, {32,4}, {698,4}, {16,0}, 
			{101,4}, {476,4}, {006,1}, {466,1}, {659,0}, {586,4}, {659,4}, {38,4}, {666,4}, {106,5}, 
			{680,3}, {669,4}, {669,1}, {676,4}, {202,5}, {668,3}, {201,3}, {48,2}, {659,0}, {209,2}, 
			{699,0}, {39,3}, {85,5}, {397,0}, {682,5}, {651,5}, {181,1}, {669,1}, {676,0}, {476,2}, 
			{651,1}, {128,4}, {466,3}, {676,2}, {213,1}, {521,3}, {464,4}, {90,1}, {42,4}, {187,4}, 
			{12,0}, {186,4}, {556,5}, {167,4}, {669,3}, {476,4}, {697,4}, {48,2}, {666,3}, {699,2}, 
			{249,4}, {694,4}, {120,5}, {7,4}, {651,4}, {651,5}, {409,0}, {95,2}, {699,0}, {660,4}, 
			{123,3}, {651,3}, {159,0}, {156,5}, {101,0}, {164,5}, {674,1}, {207,5}, {684,0}, {651,3}, 
			{675,4}, {061,5}, {156,3}, {229,5}, {034,1}, {205,5}, {051,0}, {060,1}, {659,4}, {675,0}, 
			{651,0}, {190,4}, {681,5}, {020,3}, {110,1}, {192,1}, {197,3}, {699,4}, {539,0}, {432,5}, 
			{667,0}, {669,5}, {404,3}, {141,5}, {396,4}, {466,5}, {460,0}, {119,5}, {675,2}, {208,2}, 
			{7,4}, {216,0}, {666,3}, {666,3}, {116,4}, {263,0}, {466,4}, {659,0}, {656,3}, {164,1}, 
			{259,0}, {105,4}, {666,4}, {416,4}, {48,0}, {419,2}, {55,4}, {3,4}, {651,4}, {196,3}, 
			{99,5}, {226,1}, {669,3}, {18,3}, {219,3}, {203,0}, {3,4}, {98,0}, {20,0}, {264,4}, 
			{664,2}, {654,0}, {006,3}, {105,5}, {123,2}, {129,1}, {471,4}, {517,1}, {658,5}, {698,3}, 
			{123,1}, {691,5}, {666,4}, {659,5}, {667,0}, {666,0}, {200,0}, {186,1}, {547,5}, {256,4}, 
			{219,5}, {675,4}, {103,1}, {666,4}, {43,5}, {699,4}, {95,0}, {20,4}, {670,2}, {666,0},
			{669,4}, {454,5}, {668,1}, {107,3}, {79,0}, {666,1}, {95,1}, {677,0}, {95,3}, {175,5}, {200,0}, {186,1}, {547,5}, {256,4}, {694,5}, 
			{5,0}, {226,2}, {189,4}, {61,3}, {659,0}, {4,0}, {128,0}, {96,2}, {48,4}, {219,5}, {675,4}, {103,1}, {666,4}, {669,3}, {20,0}, 
			{33,3}, {020,4}, {52,5}, {161,1}, {246,0}, {466,3}, {401,4}, {61,5}, {43,5}, {699,4}, {95,0}, {20,4}, {20,5}, {98,5}, {669,5}, 
			{20,1}, {454,4}, {659,0}, {57,1}, {75,5}, {466,4}, {13,1}, {670,2}, {666,0}, {396,4}, {257,5}, {61,0}, {16,0}, {669,0}, {673,4}, 
			{149,5}, {651,5}, {20,5}, {156,0}, {698,5}, {659,4}, {32,2}, {230,3}, {666,1}, {699,5}, {521,0}, {92,2}, {020,3}, {395,3}, {95,4}, 
			{699,3}, {660,0}, {673,3}, {110,2}, {6,5}, {118,5}, {56,4}, {119,4}, {189,4}, {672,2}, {692,3}, {199,5}, {651,0}, {656,0}, {200,4}, 
			{675,2}, {185,0}, {201,5}, {244,0}, {404,0}, {105,1}, {187,0}, {9,4}, {113,2}, {105,0}, {264,4}, {032,4}, {698,4}, {16,0}, {101,4}, 
			{476,4}, {6,1}, {466,1}, {659,0}, {586,4}, {659,4}, {38,4}, {666,4}, {106,5}, {680,3}, {669,4}, {669,1}, {676,4}, {202,5}, {668,3}, 
			{201,3}, {48,2}, {659,0}, {209,2}, {699,0}, {39,3}, {85,5}, {397,0}, {682,5}, {651,5}, {181,1}, {669,1}, {676,0}, {476,2}, {651,1}, 
			{128,4}, {466,3}, {676,2}, {213,1}, {521,3}, {464,4}, {90,1}, {42,4}, {187,4}, {12,0}, {186,4}, {556,5}, {167,4}, {669,3}, {476,4}, 
			{697,4}, {48,2}, {666,3}, {699,2}, {249,4}, {694,4}, {120,5}, {7,4}, {651,4}, {651,5}, {409,0}, {95,2}, {699,0}, {660,4}, {123,3}, 
			{651,3}, {159,0}, {156,5}, {101,0}, {164,5}, {674,1}, {207,5}, {684,0}, {651,3}, {675,4}, {61,5}, {156,3}, {229,5}, {034,1}, {205,5}, 
			{51,0}, {60,1}, {659,4}, {675,0}, {651,0}, {190,4}, {681,5}, {20,3}, {110,1}, {192,1}, {197,3}, {699,4}, {539,0}, {432,5}, {667,0}, 
			{669,5}, {404,3}, {141,5}, {396,4}, {466,5}, {460,0}, {119,5}, {675,2}, {208,2}, {007,4}, {216,0}, {666,3}, {666,3}, {116,4}, {263,0}, 
			{466,4}, {659,0}, {656,3}, {164,1}, {259,0}, {105,4}, {666,4}, {416,4}, {48,0}, {419,2}, {55,4}, {003,4}, {651,4}, {196,3}, {99,5}, 
			{226,1}, {669,3}, {18,3}, {219,3}, {203,0}, {3,4}, {98,0}, {20,0}, {264,4}, {664,2}, {654,0}, {6,3}, {105,5}, {123,2}, {129,1}, 
			{471,4}, {517,1}, {658,5}, {698,3}, {123,1}, {691,5}, {220,1}, {461,5}, {203,4}, {7,0}, {466,1}, {006,1}, {653,1}, {651,0}, {667,5}, 
			{175,0}, {204,4}, {521,2}, {156,4}, {685,1}, {209,5}, {696,3}, {675,2}, {6,4}, {105,4}, {101,4}, {153,1}, {651,3}, {667,0}, {521,5}, 
			{223,5}, {659,2}, {659,2}, {147,0}, {101,4}, {253,0}, {699,4}, {90,4}, {105,1}, {209,3}, {123,0}, {248,4}, {007,3}, {673,4}, {669,3}, 
			{655,0}, {48,0}, {209,0}, {655,1}, {454,3}, {48,3}, {201,4}, {3,5}, {48,5}, {200,0}, {131,2}, {134,4}, {128,4}, {20,5}, {106,2}, 
			{500,3}, {28,4}, {156,0}, {128,4}, {004,2}, {54,3}, {651,4}, {666,1}, {651,3}, {696,1}, {201,4}, {659,0}, {667,0}, {667,5}, {513,1}, 
			{586,3}, {108,4}, {667,2}, {181,3}, {651,0}, {265,1}, {682,3}, {651,4}, {666,5}, {699,5}, {7,3}, {657,4}, {020,1}, {476,0}, {686,3}, 
			{258,0}, {196,4}, {65,0}, {697,2}, {655,1}, {693,5}, {119,5}, {240,4}, {466,3}, {39,5}, {232,0}, {003,1}, {105,2}, {651,0}, {211,4}, 
			{466,0}, {129,4}, {50,0}, {155,5}, {183,0}, {543,5}, {193,0}, {104,2}, {189,5}, {669,1}, {118,3}, {673,3}, {668,0}, {105,3}, {151,4}, 
			{108,5}, {189,0}, {211,4}, {668,4}, {666,3}, {22,3}, {673,0}, {651,5}, {216,4}, {651,0}, {24,5}, {39,0}, {653,0}, {105,3}, {651,2}, 
			{698,5}, {666,5}, {208,1}, {209,5}, {669,2}, {696,3}, {134,3}, {450,3}, {401,4}, {467,3}, {232,5}, {020,5}, {002,1}, {651,0}, {662,4}, 
			{685,4}, {133,0}, {586,0}, {161,4}, {92,3}, {667,4}, {651,1}, {217,3}, {651,3}, {466,1}, {410,4}, {699,3}, {651,5}, {659,3}, {105,1}, 
			{88,5}, {696,5}, {105,0}, {660,4}, {699,3}, {659,4}, {128,3}, {464,5}, {173,0}, {138,0}, {50,1}, {042,5}, {043,5}, {659,4}, {244,2}, 
			{676,1}, {699,4}, {685,4}, {20,3}, {660,2}, {109,4}, {660,5}, {669,0}, {186,2}, {451,2}, {79,1}, {224,5}, {95,4}, {127,4}, {660,1}, 
			{20,3}, {195,5}, {696,3}, {659,4}, {19,4}, {170,2}, {659,4}, {3,4}, {123,1}, {539,0}, {59,0}, {96,3}, {50,2}, {43,0}, {210,3}, 
			{210,4}, {20,3}, {105,0}, {470,4}, {64,5}, {671,2}, {651,3}, {20,3}, {138,5}, {134,1}, {662,0}, {020,0}, {651,5}, {667,0}, {193,2}, 
			{678,1}, {233,3}, {54,4}, {189,4}, {416,1}, {165,3}, {75,1}, {674,0}, {675,1}, {659,2}, {119,1}, {94,3}, {209,4}, {675,4}, {396,2}, 
			{659,3}, {672,5}, {96,1}, {105,0}, {257,4}, {667,0}, {539,4}, {661,2}, {230,0}, {118,0}, {151,2}, {248,2}, {042,2}, {159,3}, {669,5}, 
			{397,3}, {226,4}, {696,3}, {264,4}, {115,4}, {209,5}, {148,2}, {216,2}, {490,1}, {669,5}, {513,3}, {003,0}, {123,1}, {123,4}, {621,5}, 
			{109,2}, {651,2}, {153,1}, {696,4}, {697,5}, {7,3}, {133,4}, {15,5}, {40,0}, {190,5}, {160,5}, {110,2}, {651,5}, {77,5}, {122,3}, 
			{416,0}, {676,2}, {539,3}, {669,1}, {104,5}, {395,1}, {129,1}, {160,0}, {575,1}, {20,1}, {20,1}, {105,0}, {651,5}, {673,3}, {660,1}, 
			{657,4}, {670,3}, {432,1}, {121,5}, {18,0}, {698,4}, {67,0}, {699,4}, {686,5}, {669,4}, {123,2}, {660,3}, {675,5}, {123,1}, {92,5}, 
			{699,5}, {699,3}, {3,5}, {108,3}, {655,1}, {651,4}, {651,0}, {401,1}, {226,1}, {654,2}, {123,5}, {466,5}, {102,2}, {685,4}, {669,4}, 
			{539,3}, {108,4}, {653,0}, {104,0}, {100,4}, {698,4}, {219,5}, {653,5}, {109,0}, {94,0}, {153,2}, {659,4}, {395,2}, {466,4}, {674,0}, 
			{659,3}, {666,5}, {659,2}, {651,1}, {653,2}, {20,4}, {29,4}, {20,5}, {395,4}, {240,1}, {653,1}, {105,4}, {201,0}, {666,1}, {105,2}, 
			{240,0}, {650,1}, {653,5}, {454,4}, {264,0}, {105,4}, {16,0}, {259,5}, {155,1}, {696,0}, {230,3}, {016,0}, {680,0}, {156,0}, {651,0}, 
			{699,4}, {656,2}, {33,0}, {211,2}, {657,1}, {128,5}, {395,1}, {409,1}, {1,5}, {656,2}, {129,1}, {210,1}, {232,2}, {061,1}, {673,3}, 
			{97,1}, {657,1}, {654,0}, {653,4}, {020,4}, {188,5}, {659,4}, {155,1}, {466,5}, {667,4}, {96,5}, {203,5}, {409,2}, {138,1}, {256,1}, 
			{3,2}, {201,2}, {57,3}, {67,3}, {108,4}, {209,5}, {653,3}, {3,2}, {4,4}, {248,5}, {251,3}, {668,0}, {240,4}, {666,5}, {244,0}, 
			{666,5}, {152,5}, {668,5}, {48,3}, {105,4}, {669,5}, {471,2}, {133,0}, {678,0}, {699,1}, {653,5}, {20,0}, {169,1}, {41,1}, {675,0}, 
			{693,0}, {120,5}, {673,0}, {699,5}, {685,1}, {211,4}, {183,1}, {454,0}, {128,4}, {450,4}, {109,0}, {209,2}, {103,3}, {20,0}, {76,4}, 
			{669,3}, {682,0}, {105,1}, {659,4}, {198,1}, {123,5}


		};

		/* For crime LVQ input neuron classes */
		int[] crime_inp_class = new int[] {
			0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 
			1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 
			1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 
			1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 
			1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 
			0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 
			1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
			1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
		};

		System.out.println(tr_crime_lvq.length);
		System.out.println(crime_inp_class.length);

		// Initialize data
		Data[] crime_inputs = new Data[crime_inp_class.length];

		for (int idx = 0; idx < crime_inputs.length; idx ++) {
			crime_inputs[idx] = new Data();
			crime_inputs[idx].set_category(crime_inp_class[idx]);
			crime_inputs[idx].set_attrib(tr_crime_lvq[idx]);
		}

		/* Crime FLVQ Training */
		crime_flvq.train(crime_inputs, crime_inputs.length);
				
		
		/* Testing data */
		Data[] test_input = new Data[56];
		double[][] test_attrib = new double[][] {
			{107,2}, {107,2}, {450,3}, {52,0}, {673,3}, {669,2}, 
			{651,0}, {39,1}, {057,4}, {675,2}, {460,2}, {416,1}, 
			{699,3}, {699,5}, {116,2}, {217,0}, {131,0}, {651,0}, 
			{219,0}, {165,5}, {467,5}, {657,5}, {419,3}, {666,4}, 
			{101,5}, {395,4}, {460,5}, {105,0}, {189,4}, {227,3}, 
			{37,1}, {260,4}, {133,0}, {28,3}, {660,2}, {666,3}, 
			{688,5}, {651,5}, {667,1}, {667,0}, {673,3}, {669,2}, 
			{667,5}, {682,0}, {262,0}, {023,0}, {228,0}, {246,1},
			{521,4}, {52,0}, {658,0}, {651,1}, {410,5}, {107,2}, 
			{107,2}, {450,3}
		};
		int[] test_class = new int[] { 
			0, 0, 0, 1, 1, 1,
			0, 0, 1, 1, 1, 1, 
			1, 1, 0, 0, 0, 1, 
			1, 1, 1, 1, 1, 1, 
			1, 1, 1, 1, 1, 0, 
			0, 0, 1, 0, 0, 0, 
			0, 1, 1, 1, 1, 1, 
			0, 0, 0, 0, 0, 0,
			0, 1, 0, 0, 0, 0, 
			0, 0 
		};

		System.out.println(test_attrib.length);
		System.out.println(test_class.length);

		for (int i = 0; i < test_input.length; i ++) {
			test_input[i] = new Data();
			test_input[i].set_attrib(test_attrib[i]);
			test_input[i].set_category(test_class[i]);
		}
		

		/* Testing */
		for (int x = 0; x < test_input.length; x ++) {
			int pred_val = crime_flvq.predict(test_input[x]);
			System.out.print(pred_val);
			System.out.println(" " + test_class[x]);
		}

	}	

}