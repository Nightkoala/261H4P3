/**
 * KnapSackSolution.java
 * 
 * @author	Derek Brown <djb3718@rit.edu>
 *
 * Purpose	Reconstruct the answer to the knapsack problem and return the
 * 			results.
 */

import java.util.Scanner;

public class KnapSackSolution {

	// Attributes
	
	private int size;
	private int weight;
	private int[] costs;
	private int[] weights;
	private int[][] S;
	
	// Constructor

	/**
	 * Constructor for creating an instance of the KnapSackSolution.
	 * 
	 * @param n	The number of items in the "store"
	 * @param c	An array associating an item to its cost
	 * @param w	An array associating an item with its weight
	 * @param W	The weight that the knapsack can hold
	 */
	public KnapSackSolution( int n, int[] c, int[] w, int W ) {
		this.size = n;
		this.weight = W;
		this.costs = c;
		this.weights = w;
		S = new int[n+1][W+1];
		for( int i = 0 ; i <= W ; i++ ) {
			S[0][i] = 0;
		}//end for
		for( int i = 0 ; i <= n ; i++ ) {
			S[i][0] = 0;
		}//end for
	}//end KnapSackSolution constructor
	
	// Methods
	
	/**
	 * The algorithm for finding the greatest cost of items that can fit in 
	 * the knapsack, DOES NOT RECONSTRUCT SOLUTIONS.
	 * 
	 * @param K	Object containing all usefull information needed to solve 
	 * 			problem, including, the number of items, the size of the 
	 * 			knapsack, the costs of each item, and the weight of each item.
	 */
	public void knapSackSolver( KnapSackSolution K ) {
		for( int v = 1 ; v <= K.weight ; v++ ) {
			for( int k = 1 ; k <= K.size ; k++ ) {
				S[k][v] = S[k-1][v];
				if( ( K.weights[k-1] <= v ) && ( K.S[k-1][v-K.weights[k-1]] + K.costs[k-1] > K.S[k][v] ) ) {
					K.S[k][v] = K.S[k-1][v-K.weights[k-1]] + K.costs[k-1];
				}//end if
			}//end for k
		}//end for v
	}//end knapSackSolver
	
	/**
	 * Algorithm for reconstructing the solution, Can only be run after
	 * knapSackSolver has been run.
	 * 
	 * @param K	Object containing all useful information needed to solve the
	 * 			problem, including, the number of items, the size of the 
	 * 			knapsack, the costs of each item, and the weight of each item.
	 * 
	 * @return	An array of numbers associated to the item number.
	 */
	public int[] knapSackSolution( KnapSackSolution K ) {
		int[] solution = new int[K.size];
		int n = K.size;
		int v = K.weight;
		int i = 0;
		while( K.S[n][v] != 0 ) {
			if( K.S[n][v] != K.S[n-1][v] ) {
				solution[i] = n;
				v = v - K.weights[n-1];
				n -= 1;
				i++;
			}//end if
			else {
				n -= 1;
			}//end else
		}//end while
		return solution;
	}//end knapSackSolution
	
	/**
	 * The main logic for the program, Reads input from the user and then 
	 * executes the algorithm for solving the knapsack problem, and then
	 * executes the algorithm for reconstructing the solution, The result is
	 * then displayed to the user before exiting.
	 * 
	 * @param args	Command line arguments, unused.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner( System.in );
		String input = sc.next();
		int numItems = Integer.parseInt( input );
		input = sc.next();
		int KSweight = Integer.parseInt( input );
		int iWeight;
		int iCost;
		int[] weights = new int[numItems];
		int[] costs = new int[numItems];
		for( int i = 0 ; i < numItems ; i++ ) {
			input = sc.next();
			iWeight = Integer.parseInt( input );
			input = sc.next();
			iCost = Integer.parseInt( input );
			weights[i] = iWeight;
			costs[i] = iCost;
		}//end for
		sc.close();
		KnapSackSolution K = new KnapSackSolution( numItems, costs, weights, KSweight );
		K.knapSackSolver( K );
		int[] solution = K.knapSackSolution( K );
		for( int item : solution ) {
			if( item != 0 ) {
				System.out.printf("%d ", item);
			}//end if
			//System.out.println();
		}//end for item
		System.out.println();
	}//end main
}//end KnapSackSolution