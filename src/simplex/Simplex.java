package simplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simplex {

	public static SimplexTuple pivot(SimplexTuple curr, int leaving, int entering){
		SimplexTuple nxt = new SimplexTuple(curr.N, curr.B, curr.A, curr.b, curr.c, curr.v);
		int e = curr.N.indexOf(entering); // The entering var is always going to be a non-basic var
		int l = curr.B.indexOf(leaving); // The leaving var is always going to be a basic var
		// Compute the coefficients of the equation for new basic variable x_e
		nxt.b[l] = curr.b[l]/curr.A[l][e];
		for(int j = 0; j<curr.n; j++) {
			if(j == e) continue;
			nxt.A[l][j] = curr.A[l][j]/curr.A[l][e];
			}
		nxt.A[l][e] = 1/curr.A[l][e];
		
		// Compute the coefficients of the remaining constraints
		for(int i = 0; i<curr.m; i++){
			if(i == l) continue;
			nxt.b[i] = curr.b[i] - curr.A[i][e]*nxt.b[l];
			for(int j = 0; j<curr.n; j++) {
				if(j == e) continue;
				nxt.A[i][j] = curr.A[i][j] - curr.A[i][e]*nxt.A[l][j];
			}
			
			nxt.A[i][e] = -1*curr.A[i][e]*nxt.A[l][e];
			
		}
		
		// Compute the objective function
		nxt.v = curr.v + curr.c[e]*nxt.b[l];
		for(int j = 0; j<curr.n; j++) {
			if(j==e) continue;
			nxt.c[j] = curr.c[j] - curr.c[e]*nxt.A[l][j];	
		}
		nxt.c[e] = -1*curr.c[e]*nxt.A[l][e];
		
		// Compute new sets of basic and non-basic variables
		
		nxt.N.set(e, leaving);
		nxt.B.set(l, entering);
		nxt.printEqn();
		return nxt;
	}
	
	public int hasPos(double[] c) {
		for(int i = 0; i<c.length; i++) {
			if(c[i] > 0) return i;
		}
		
		return -1;
	}
	
	public void simplex(double[][] A, double[] b, double[] c) {
		SimplexTuple st = initializeSimplex(A,b,c);
		double[] del = new double[st.n];
		int e = -1;
		while((e = hasPos(st.c)) != -1) {
			for(int i=0; i<st.m; i++) {
				if(st.A[i][e]>0) {
					del[i] = st.b[i]/st.A[i][e];
				}
				else {
					del[i] = Double.POSITIVE_INFINITY;
				}
			}
			
		}
	}
	
	public static void main(String[] args){
		List<Integer> N = new ArrayList<Integer>();
		List<Integer> B = new ArrayList<Integer>();
		N.addAll(Arrays.asList(new Integer[] {1,2,3}));
		B.addAll(Arrays.asList(new Integer[] {4,5,6}));

		double[][] A = new double[][] {
			{1, 1, 3},
			{2, 2, 5},
			{4, 1, 2}
		};

		double[] b = new double[] {30,24,36};
		double[] c = new double[] {3,1,2};
		double v = 0;
		
		pivot(new SimplexTuple(N,B,A,b,c,v),6,1);
	}
}
