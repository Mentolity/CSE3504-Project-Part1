import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		/*System.out.println("Practice 1: ");
		Practice1();
		System.out.println("\nPractice 2: ");
		Practice2();*/
		
		Lattice lattice;
		lattice = new Lattice(100, false);
		while(lattice.walk());
		lattice.display("TEST DISPLAY", 30);
	}

	public static void Practice1(){ //Bad copy pasta is bad
		ArrayList<Integer> listOfPathLengths = new ArrayList<Integer>();
		
		Lattice lattice;
		lattice = new Lattice(100, false);
		while(lattice.walk()); //walk until stuck
		/*listOfPathLengths.add(lattice.getPathLength());
                FiniteLattice FL = new FiniteLattice();
                FL.FiniteLat (1000);*/
		
		System.out.println("1. Calculate the path length (the number of steps travelled until being trapped).");
		System.out.println("	Path Length: " + mean( listOfPathLengths));

		for(int i=0; i<100; i++){
			lattice = new Lattice(100, false);
			while(lattice.walk()); //walk until stuck
			listOfPathLengths.add(lattice.getPathLength());
		}
		System.out.println("2. Run the simulation 100 times and calculate the path length at each time. Calculate:");
		System.out.println("	Average Path Length: " + mean( listOfPathLengths));
		System.out.println("	Std Path Length: " + standardDeviation(listOfPathLengths));
		
		for(int i=0; i<100; i++){
			lattice = new Lattice(1000, false);
			while(lattice.walk()); //walk until stuck
			listOfPathLengths.add(lattice.getPathLength());
		}
		System.out.println("3. Increase the lattice size to 1000×1000 and answer the same questions as in point 2");
		System.out.println("	Average Path Length: " + mean(listOfPathLengths));
		System.out.println("	Std Path Length: " + standardDeviation(listOfPathLengths));
	}

	public static void Practice2(){//icky yucky copy pasta but refactoring would take more time and the assignment isn't graded on best practices
		Lattice lattice;
		
		lattice = new Lattice(); //new infinite lattice
		for(int i=0; i<5000; i++){//take 5000 walks
			if(!lattice.walk())//if were stuck there's no point in continuing to walk
				break;
		}
		System.out.println("1. What is the distance between the start point and the end point?");
		System.out.println("	Distance: " + latticeDistance(lattice));
		
		System.out.println("2. What is the lattice size?");
		System.out.println("	Size: " + latticeSize(lattice));
		
		ArrayList<Integer> listOfDistances;
		ArrayList<Integer> listOfSizes;
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		listOfDistances = new ArrayList<Integer>();
		listOfSizes = new ArrayList<Integer>();
		for(int x=0; x<10; x++){//run sim 10x
			lattice = new Lattice(); //new infinite lattice
			for(int i=0; i<5000; i++){//take 5000 walks
				if(!lattice.walk())//if were stuck there's no point in continuing to walk
					break;
			}
			listOfDistances.add(latticeDistance(lattice)); //reset the lists
			listOfSizes.add(latticeSize(lattice));
		}
		
		System.out.println("3. Repeat the simulation 10 times and calculate:");
		System.out.println("	Distance Mean: " + mean(listOfDistances));
		System.out.println("	Distance Median: " + median(listOfDistances));
		System.out.println("	Distance Variance: " + variance(listOfDistances));
		System.out.println("	Distance Standard Deviation: " + standardDeviation(listOfDistances));
		System.out.println("	Size Mean: " + mean(listOfSizes));
		System.out.println("	Size Median: " + median(listOfSizes));
		System.out.println("	Size Variance: " + variance(listOfSizes));
		System.out.println("	Size Standard Deviation: " + standardDeviation(listOfSizes));
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		listOfDistances = new ArrayList<Integer>(); //reset the lists
		listOfSizes = new ArrayList<Integer>();
		for(int x=0; x<100; x++){//run sim 100x
			lattice = new Lattice(); //new infinite lattice
			for(int i=0; i<5000; i++){//take 5000 walks
				if(!lattice.walk())//if were stuck there's no point in continuing to walk
					break;
			}
			listOfDistances.add(latticeDistance(lattice));
			listOfSizes.add(latticeSize(lattice));
		}
		
		System.out.println("4. Run the simulation 100 times and answer the same questions as in point 3.");
		System.out.println("	Distance Mean: " + mean(listOfDistances));
		System.out.println("	Distance Median: " + median(listOfDistances));
		System.out.println("	Distance Variance: " + variance(listOfDistances));
		System.out.println("	Distance Standard Deviation: " + standardDeviation(listOfDistances));
		System.out.println("	Size Mean: " + mean(listOfSizes));
		System.out.println("	Size Median: " + median(listOfSizes));
		System.out.println("	Size Variance: " + variance(listOfSizes));
		System.out.println("	Size Standard Deviation: " + standardDeviation(listOfSizes));
		
////////////////////////////////////////////////////////////////////////////////////////////////////////
		listOfDistances = new ArrayList<Integer>(); //reset the lists
		listOfSizes = new ArrayList<Integer>();
		for(int x=0; x<1000; x++){//run sim 1000x
			lattice = new Lattice(); //new infinite lattice
			for(int i=0; i<5000; i++){//take 5000 walks
				if(!lattice.walk())//if were stuck there's no point in continuing to walk
					break;
			}
			listOfDistances.add(latticeDistance(lattice));
			listOfSizes.add(latticeSize(lattice));
		}
		
		System.out.println("5. Run the simulation 1000 times and answer the same questions as in point 3.");
		System.out.println("	Distance Mean: " + mean(listOfDistances));
		System.out.println("	Distance Median: " + median(listOfDistances));
		System.out.println("	Distance Variance: " + variance(listOfDistances));
		System.out.println("	Distance Standard Deviation: " + standardDeviation(listOfDistances));
		System.out.println("	Size Mean: " + mean(listOfSizes));
		System.out.println("	Size Median: " + median(listOfSizes));
		System.out.println("	Size Variance: " + variance(listOfSizes));
		System.out.println("	Size Standard Deviation: " + standardDeviation(listOfSizes));
		
		
		System.out.println("6. Write a comment on how the results change with changing the number of simulations.");
	}
	
	
	public static int latticeDistance(Lattice lattice){
		return Math.abs(lattice.xStart() - lattice.xEnd()) + Math.abs(lattice.yStart() - lattice.yEnd());
	}
	public static int latticeSize(Lattice lattice){
		return (lattice.xMax() - lattice.xMin()) * (lattice.yMax() - lattice.yMin());
	}
	
	public static double median(ArrayList<Integer> list){
		Collections.sort(list);
		if(list.size() % 2 == 0){
			return (list.get((list.size()/2)) + list.get((list.size()/2) - 1))/2;
		}else{
			return list.get((list.size()-1)/2);
		}
	}
	
	public static double mean(ArrayList<Integer> list){
		int sum = 0;
		for(Integer i : list){
			sum += i;
		}
		return (double)sum/(double)list.size();
	}
	
	public static double variance(ArrayList<Integer> list){
		double mean = mean(list);
		double temp = 0;
		for(Integer i : list){
			temp += (i-mean)*(i-mean);
		}
		return temp/list.size();
	}
	
	public static double standardDeviation(ArrayList<Integer> list){
		return Math.sqrt(variance(list));
	}
}