import java.util.ArrayList;
import java.util.Random;

/**
 * 25-3-2020
 * @author Tom Harris
 * 
 */
public class Main {
	
	public static int idNext;
	
	public static ArrayList<Integer> elems;
	
	public static ArrayList<Operation> ops;

	public static void main(String[] args) {
		
	//	To run an individual experiment, comment out other experiments
		System.out.println("EXP 1:");
		Experiment1();
		System.out.println("EXP 2:");
		Experiment2();
		System.out.println("EXP 3:");
		Experiment3();
		System.out.println("EXP 4:");
		Experiment4();

	}
	
	public static void Experiment1() {
		
	//	Lins: length of inputs to be tested
		
		int[] Lins = new int[5];
		
		Lins[0] = 100000;
		Lins[1] = 200000;
		Lins[2] = 500000;
		Lins[3] = 800000;
		Lins[4] = 1000000;
		
	//	Run operation generation and Treap/Dynamic array testing for each specified length to be tested		

		for (int Lin:Lins) {
				
		//	Generate new data structures
			
			Treap treap = new Treap();
			DynamicArray comp = new DynamicArray();
			
		//	Initialize structure to hold element history (elems), id counter (idNext) and operations ArrayList (ops)
			
			elems = new ArrayList<Integer>();
			idNext = 1;
			ops = new ArrayList<Operation>();
			
			
		//	Generate Lin insertions and add to ops
			
			for (int i=0;i<Lin;i++) {
				ops.add(gen_insertion());
			}
			
		//	Run Treap over operation list and measure time taken to process
			
			long startTimeTreap = System.currentTimeMillis();
			for (Operation op:ops) {
				treap.route(op);
			}
			long stopTimeTreap = System.currentTimeMillis();
			System.out.println("TREAP " + Lin + " :" + (stopTimeTreap - startTimeTreap));
			
		//	Run Dynamic array over operation list and measure time taken to process
			
			long startTimeDyn = System.currentTimeMillis();
			for (Operation op:ops) {
				comp.route(op);
			}
			long stopTimeDyn = System.currentTimeMillis();
			System.out.println("DYNAMIC ARRAY " + Lin + " :" + (stopTimeDyn - startTimeDyn));
			
		}
	}
	
	public static void Experiment2() {
		
	//	L: length of operations to be generated
		
		int L = 1000000;
		
	//	perDel: percentage of deletions to be generated in particular sequence
		
		double[] perDel = new double[5];
		
		perDel[0] = 0.001;
		perDel[1] = 0.005;
		perDel[2] = 0.01;
		perDel[3] = 0.05;
		perDel[4] = 0.1;
		
	//	Run operation generation and Treap/Dynamic array testing for each specified deletion percentage to be tested		
		
		for (double per:perDel) {
	
		//	Generate new data structures
				
			Treap treap = new Treap();
			DynamicArray comp = new DynamicArray();
			
		//	Initialize structure to hold element history (elems), id counter (idNext) and operations ArrayList (ops)
			
			elems = new ArrayList<Integer>();
			idNext = 1;
			ops = new ArrayList<Operation>();
			
		//	Generate L insertions/deletions and add to ops
			
			for (int i=0;i<L;i++) {
				
				Random rand = new Random();
				
			// 	Generate deletion if randomly generated value between [0,1] < percentage
				
				if (per>rand.nextDouble()) {
					ops.add(gen_deletion());
				} else {
					ops.add(gen_insertion());
				}
			}
				
		//	Run Treap over operation list and measure time taken to process
			
			long startTimeTreap = System.currentTimeMillis();
			for (Operation op:ops) {
				treap.route(op);
			}
			long stopTimeTreap = System.currentTimeMillis();
			System.out.println("TREAP " + per + " :" + (stopTimeTreap - startTimeTreap));
			
		//	Run Dynamic array over operation list and measure time taken to process
			
			long startTimeDyn = System.currentTimeMillis();
			for (Operation op:ops) {
				comp.route(op);
			}
			long stopTimeDyn = System.currentTimeMillis();
			System.out.println("DYNAMIC ARRAY " + per + " :" + (stopTimeDyn - startTimeDyn));
		}
	}
	
	public static void Experiment3() {
		
	//	L: length of operations to be generated
		
		int L = 1000000;
		
	//	perSearch: percentage of searchs to be generated in particular sequence
		
		double[] perSearch = new double[5];
		
		perSearch[0] = 0.001;
		perSearch[1] = 0.005;
		perSearch[2] = 0.01;
		perSearch[3] = 0.05;
		perSearch[4] = 0.1;
		
	//	Run operation generation and Treap/Dynamic array testing for each specified search percentage to be tested
		
		for (double per:perSearch) {
				
		//	Generate new data structures
	
			Treap treap = new Treap();
			DynamicArray comp = new DynamicArray();
			
		//	Initialize structure to hold element history (elems), id counter (idNext) and operations ArrayList (ops)
						
			elems = new ArrayList<Integer>();
			idNext = 1;
			ops = new ArrayList<Operation>();
			
		//	Generate L insertions/searchs and add to ops
			
			for (int i=0;i<L;i++) {
				Random rand = new Random();
				
			//	Generate search if randomly generated value between [0,1] < percentage
				
				if (per>rand.nextDouble()) {
					ops.add(gen_search());
				} else {
					ops.add(gen_insertion());
				}
			}
			
		//	Run Treap over operation list and measure time taken to process
				
			long startTimeTreap = System.currentTimeMillis();
			for (Operation op:ops) {
				treap.route(op);
			}
			long stopTimeTreap = System.currentTimeMillis();
			System.out.println("TREAP " + per + " :" + (stopTimeTreap - startTimeTreap));
			
		//	Run Dynamic array over operation list and measure time taken to process
			
			long startTimeDyn = System.currentTimeMillis();
			for (Operation op:ops) {
				comp.route(op);
			}
			long stopTimeDyn = System.currentTimeMillis();
			System.out.println("DYNAMIC ARRAY " + per + " :" + (stopTimeDyn - startTimeDyn));
		}
	}
	
	public static void Experiment4() {
		
	//	Lins: length of inputs to be tested
		
		int[] Lins = new int[5];
		
		Lins[0] = 100000;
		Lins[1] = 200000;
		Lins[2] = 500000;
		Lins[3] = 800000;
		Lins[4] = 1000000;
		
	//	perDelq4: percentage boundary for deletion operations to be generated
	//	perSearchq4: percentage boundary for search operations to be generated
		
		double perDelq4 = 0.1;
		double perSearchq4 = 0.05;
		
	//	Run operation generation and Treap/Dynamic array testing for each specified length to be tested	
		
		for (int Lin:Lins) {
				
		//	Generate new data structures
			
			Treap treap = new Treap();
			DynamicArray comp = new DynamicArray();
			
		//	Initialize structure to hold element history (elems), id counter (idNext) and operations ArrayList (ops)
			
			elems = new ArrayList<Integer>();
			idNext = 1;
			ops = new ArrayList<Operation>();
			
		//	Generate Lin insertions/deletions/searchs and add to ops
			
			for (int i=0;i<Lin;i++) {
				Random rand = new Random();
				
				double randGen = rand.nextDouble();
				
			//	Generate search if randomly generated value between [0,1] < perSearchq4
			//  If not, generate search if randomly generated value between [0,1] < perDelq4
			// 	If not, generate insertion
				
				if (perSearchq4>randGen) {
					ops.add(gen_search());
				} else if (perDelq4>randGen) {
					ops.add(gen_deletion());
				} else {
					ops.add(gen_insertion());
				}
			}
			
		//	Run Treap over operation list and measure time taken to process
			
			long startTimeTreap = System.currentTimeMillis();
			for (Operation op:ops) {
				treap.route(op);
			}
			long stopTimeTreap = System.currentTimeMillis();
			System.out.println("TREAP " + Lin + " :" + (stopTimeTreap - startTimeTreap));
			
		//	Run Dynamic array over operation list and measure time taken to process
			
			long startTimeDyn = System.currentTimeMillis();
			for (Operation op:ops) {
				comp.route(op);
			}
			long stopTimeDyn = System.currentTimeMillis();
			System.out.println("DYNAMIC ARRAY " + Lin + " :" + (stopTimeDyn - startTimeDyn));
		}
	}


	
	public static Element gen_element() {
		
		Random rand = new Random();
		
	//	new_key: randomly generated key between [0,10000000]
		
		int new_key = rand.nextInt(10000001);
		
	//	elem: new Element object with unique id and newly generated key 
		
		Element elem = new Element(idNext, new_key);
		
		idNext ++;
		
		elems.add(new_key);
		
		return elem;
	}
	
	
	public static Insertion gen_insertion() {
		
	//	x: newly generated element
		
		Element x = gen_element();
		
		return new Insertion(x);
	}
	
	
	public static Deletion gen_deletion() {
		
		Random rand = new Random();
		
	// idDel: id to be deleted
		
		int idDel = -1;
		
	//	Check data structure is not empty
		
		if (idNext != 1) {
			
		//	Generate random id to be deleted
			
			idDel = rand.nextInt(idNext - 1);
			
		//	Check if id has already been deleted
		// 	If yes, generate deletion for associated key and mark id as deleted (val = -1)
			
			if (elems.get(idDel) != -1) {
				int keyDel = elems.get(idDel);
				elems.set(idDel, -1);
				return new Deletion(keyDel);
			}
		}
		
		// If no or data structure empty, randomly generate key to be deleted between [0,10000000]
		
		int keyDel = rand.nextInt(10000001);
		if (elems.contains(keyDel)) {
			elems.set(elems.indexOf(keyDel), -1);
		}
		return new Deletion(keyDel);
	}

	
	public static Search gen_search() {
		Random rand = new Random();
		
	//	q: random key between [0,10000000] to be searched for
		
		int q = rand.nextInt(10000001);
		
		return new Search(q);
	}
	
}
