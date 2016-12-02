import java.util.ArrayList;
import java.util.List;


public class DKNAP {
	
	private int n;
	private int M;
	private int[] p;
	private int[] w;
	private int[] F;
	
	private List<Integer> P = new ArrayList<>();
	private List<Integer> W = new ArrayList<>();
	

    public DKNAP(int[] p, int[] w, int M) {
        set(p, w, M);
    }

    private void set(int[] p, int[] w, int M) {
        this.n = p.length;
        this.p = p;
        this.w = w;
        this.M = M;
        F = new int[n + 1];
        P.clear();
        W.clear();
    }
    
    private int max(int l, int h, int M, int i) {
        int max = 0;
        int r = 0;
        for (int j = l; j <= h; j++) {
            int temp = W.get(j) + w[i];
            if (temp > max && temp <= M) {
                max = temp;
                r = j;
            }

        }
        return r;
    }
    
    public int[] dknap() {
    	int i, j, k, l, h, next;
    	int pp, ww;
    	
    	F[0] = 0;
    	P.add(0);
    	W.add(0);
    	l = h = 0;
    	F[1] = next = 1;
    	for(i = 1;i <= n;i++) {
    		k = l;
    		int u = max(l, h, M, i - 1);
    		for(j = l;j <= u;j++) {
    			pp = P.get(j) + p[i - 1];
    			ww = W.get(j) + w[i - 1];
    			while(k <= h && W.get(k) < ww) {
    				P.add(P.get(k));
    				W.add(W.get(k));
    				next++;
    				k++;
    			}
    			if(k <= h && W.get(k) == ww) {
    				pp = Math.max(pp, P.get(k));
    				k++;
    			}
			    if (pp > P.get(next - 1)) {
                    P.add(pp);
                    W.add(ww);
                    next++;
                }
                //清除
                while (k <= h && P.get(k) <= P.get(next - 1)) {
                    k++;
                }
    		}
    		
    		//将S^{i-1} 中的剩余元素并入S^i
            while (k <= h) {
                P.add(P.get(k));
                W.add(W.get(k));
                next++;
                k++;
            }
            // 对S^{i+1}置初值
            l = h + 1;
            h = next - 1;
            if(i + 1 < F.length) {
            	F[i + 1] = next;
            }
            
    	}
    	
    	int[] X = new int[n];
    	int px = P.get(P.size() - 1);
    	int wx = W.get(W.size() - 1);
    	
    	for(i = n - 1; i >= 0; --i) {
    		int high = F[i + 1],
    		low = F[i];
    		for (j = high - 1; j >= low; --j) {//在前一个S中寻找是否有px，wx，若有，则x置为0，若无则是新加入的，x置为1
    			X[i] = 1;
    			if(px == P.get(j) && wx == W.get(j)){//在前一个S中
    				X[i] = 0;
    				break;
    			  }
    		}
            if(X[i] == 1){
                px = px - p[i];
                wx -= w[i];
            }
    	}
    	
    	return X;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] X1 = new DKNAP(new int[]{1, 2, 5}, new int[]{2, 3, 4}, 6).dknap();
		for(int i = 0;i < X1.length;i++) {
			System.out.print(X1[i] + " ");
		}
		
		System.out.println();
		
		int[] X2 = new DKNAP(new int[]{2, 5, 8, 1}, new int[]{10, 15, 6, 9}, 15).dknap();
		for(int i = 0;i < X2.length;i++) {
			System.out.print(X2[i] + " ");
		}
	}

}
