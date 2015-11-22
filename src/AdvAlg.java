
public class AdvAlg {

	//	Simple Recursion version
	public static double cutRod(double[] p, int n){
		if(n==0) {
			return 0;
		}
		double q = -1000;
		// Using any negative value since it is assumed that no size of the 
		// rod will have a negative value.
		for(int i=0; i<n; i++) {
			q = Math.max(q, p[i] + cutRod(p, (n-i-1)));
		}
		return q;
	}

	//	Top down memoization
	private static double memoizedCutRodAux(double[] p, int n, double[] r) {
		double q = -1000;
		if(r[n]>= 0) {
			return r[n];
		}
		if (n==0){
			q = 0;
		}
		else {
			for (int i = 1; i<=n; i++) {
				q = Math.max(q, p[i-1] + memoizedCutRodAux(p, (n-i),r));
			}
		}
		r[n] = q;
		return q;
	}
	public static double memoizedCutRod(double[] p, int n) {
		double[] r = new double[n+1];
		for(int i = 1; i<=n; i++) {
			r[i] = -1000;
		}

		return memoizedCutRodAux(p,n,r);
	}

	//	Bottom up
	public static double bottomUpCutRod(double[] p, int n) {
		double[] r = new double[n+1];
		r[0] = 0;
		for(int j=1;j<=n;j++) {
			double q = -1000;
			for (int i=1;i<j; i++) {
				q = Math.max(p[i], r[j-1]);
			}
			r[j] = q;
		}
		return r[n];
	}

	//	memoized LCS algorithm which prints LCS and returns
	//	length of LCS

	public static int lcs(String a, String b) {
		int lenA = a.length();
		int lenB = b.length();
		int[][] L = new int[lenA+1][lenB+1];
		
		int result = 0;

		for(int i=0;i<lenA; i++) {
			for(int j=0; j<lenB; j++) {
				if (a.charAt(i) == b.charAt(j)) {
					L[i+1][j+1] = L[i][j] + 1;	
				}
				else {
					L[i+1][j+1] = Math.max(L[i+1][j], L[i][j+1]);
				}
			}
		}
		
		String seq = "";
		for(int seqi = lenA, seqj = lenB; seqi!= 0 && seqj!= 0;) {
			if(L[seqi][seqj] == L[seqi-1][seqj]) {
				seqi--;
			}
			else if(L[seqi][seqj] == L[seqi][seqj-1]) {
				seqj--;
			}
			else{
				seq += a.charAt(seqi-1);
				seqi--;
				seqj--;
			}
		}
		
		result = seq.length();
		System.out.println("LCS:\n"+ seq + " length:" + result);
		return result;

	}

	public static void main(String[] args) {
		double[] cutRodValues = {1,5,8,9,10,17,17,20,24,30};
		int n = 10;
		System.out.println("Simple recursion: " + cutRod(cutRodValues,n));
		System.out.println("Top down memoization: "+ memoizedCutRod(cutRodValues,n));
		System.out.println("Bottom up method: "+ bottomUpCutRod(cutRodValues, n));
		
		String a = "thisisatest";
		String b = "testing123testing";
		
		int result = lcs(a,b);


	}

}
