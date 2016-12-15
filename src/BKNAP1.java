
public class BKNAP1 {
	int[] W;
	int[] P;
	int n;
	
	public BKNAP1(int[] P, int[] W) {
		this.n = W.length;
		this.W = new int[n + 1];
		this.P = new int[n + 1];
		for(int i = 0;i < n;i++) {
			this.W[i + 1] = W[i];
			this.P[i + 1] = P[i];
		}
	}
	
	private double BOUND(int p, int w, int k, int M) {
		int i;
		double b = p;
		double c = w;
		
		for(i = k + 1;i <= n;i++) {
			c += W[i];
			if(c < M) {
				b += P[i];
			}
			else {
				return (b + (1 - (c - M) / W[i]) * P[i]);
			}
		}
		return b;
	}
	
	
	public int[] bknap1(int M) {
		int[] X = new int[n + 1], Y = new int[n + 1];
		int k = 1, fw = 0, fp = -1, cw = 0, cp = 0;
		
		do {
			while(k <= n && cw + W[k] <= M) {
				cw = cw + W[k];
				cp += P[k];
				Y[k] = 1;
				k++;
			}
			if(k > n) {
				fp = cp;
				fw = cw;
				k = n;
				for (int i = 1; i < X.length; i++) {
		            X[i] = Y[i];
		        }
			}
			else {
				Y[k] = 0;
			}
			while(BOUND(cp, cw, k, M) <= fp) {
				while(k != 0 && Y[k] != 1) {
					k--;
				}
				if(k == 0) {
					return X;
				}
				Y[k] = 0;
				cw -= W[k];
				cp -= P[k];
			}
			k++;
		} while(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 BKNAP1 bknap1 = new BKNAP1(new int[]{0, 11, 21, 31, 33, 43, 53, 55, 65}, new int[]{0, 1, 11, 21, 23, 33, 43, 45, 55});
		 int X[] = bknap1.bknap1(110);
		 for(int i = 1;i < X.length;i++) {
			 System.out.print(X[i] + " ");
		 }
	}

}
