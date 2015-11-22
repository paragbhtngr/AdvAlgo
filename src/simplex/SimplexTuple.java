package simplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimplexTuple {

	List<Integer> N = new ArrayList<Integer>(); //List of Non-Basic Variables N
	List<Integer> B = new ArrayList<Integer>(); //List of Basic Variables B
	double[][] A = null; // coefficient matrix A
	double[] b  = null; // coefficient matrix b
	double[] c = null; // coefficient matrix c
	double v = 0; // coefficient v
	int m = 0; // m = |B|
	int n = 0; // n = |N|

	public SimplexTuple(List<Integer> _N, List<Integer>_B, double[][] _A, double[] _b, double[] _c, double _v){
		this.N = _N; 
		this.B = _B; 
		this.A = _A; 
		this.b  = _b; 
		this.c = _c;
		this.v = 0;

		this.m = _B.size(); 
		this.n = _N.size(); 
	}

	public void printEqn(){
		System.out.print("z = "+this.v);
		for(int i=0; i< this.c.length; i++){
			if(this.c[i] != 0) {
				System.out.print(" + "+this.c[i]+"x"+this.N.get(i));
			}
		}
		System.out.println();
		System.out.println();

		for(int i=0; i<this.B.size(); i++){
			System.out.print("x"+ this.B.get(i) + "= "+ this.b[i]);
			for(int j=0; j<this.N.size(); j++){
				System.out.print(" + "+ (-1*this.A[i][j]) + "x" + this.N.get(j));
			}
			System.out.println();
		}
		System.out.println();
	}

	public void print() {
		System.out.println("N: "+ this.N);
		System.out.println("B: "+ this.B);
		System.out.println("A: ");
		for(int i = 0; i<this.m; i++){
			for(int j = 0; j<this.n; j++){
				System.out.print(this.A[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println("b: ");
		for(int i = 0; i<this.b.length; i++){
			System.out.print(this.b[i] + "\t");
		}
		System.out.println();
		
		System.out.println("c: ");
		for(int i = 0; i<this.c.length; i++){
			System.out.print(this.c[i] + "\t");
		}
		System.out.println();
		
		System.out.println("v: "+ this.v);
	}

	//	Testing the tuple

	public static void main(String[] args){
		List<Integer> N = new ArrayList<Integer>();
		List<Integer> B = new ArrayList<Integer>();
		N.addAll(Arrays.asList(new Integer[] {3,5,6}));
		B.addAll(Arrays.asList(new Integer[] {1,2,4}));

		double[][] A = new double[][] {
			{-1.0/6, -1.0/6, -1.0/3},
			{8.0/3, 2.0/3, -1.0/3},
			{1.0/2, -1.0/2, 0.0}
		};

		double[] b = new double[] {8,4,18};
		double[] c = new double[] {-1.0/6, -1.0/6, -2.0/3};
		double v = 28;

		SimplexTuple test = new SimplexTuple(N,B,A,b,c,v);
		test.print();
	}
}
