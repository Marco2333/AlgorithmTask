import java.util.Arrays;
import java.util.Comparator;

class Job {
	int id, deadline, profit;
	
	public Job(int id, int profit,int deadline) {
		this.id = id;
		this.deadline = deadline;
		this.profit = profit;
	}
}

public class JS {
	public static int[] js(int[] D) {
		int[] J = new int[D.length];
		int i;
		for(i = 0;i < D.length;i++) {
			J[i] = -1;
		}
		D[0] = 0;
		J[0] = 0;
		
		int k = 1, n = D.length, r;
		J[1] = 1;
		for(i = 2;i < n;i++) {
			r = k;
			while(D[J[r]] > D[i] && D[J[r]] > r) {
				r = r - 1;
			}
			if(D[J[r]] <= D[i] && D[i] > r) {
				for(int j = k;j >= r + 1;j--) {
					J[j + 1] = J[j];
				}
				J[r + 1] = i;
				k = k + 1;
			}
		}
		
		return J;
	}
	
	public static int[] exeJS(Job[] job) {
		Arrays.sort(job, new Comparator<Job>(){
			@Override
			public int compare(Job o1, Job o2) {
				// TODO Auto-generated method stub
				if(o1.profit > o2.profit) {
					return -1;
				}
				else if(o1.profit < o2.profit) {
					return 1;
				}
				return 0;
			}
		});
		
		int i, k = 0, len = job.length;
		int[] D = new int[len + 1];
		D[0] = 0;
		
		for(i = 0;i < len;i++) {
			D[++k] = job[i].deadline;
		}
		
		int[] j = js(D);
		len = j.length;
		
		for(i = 0;i < len;i++) {
			if(j[i] == -1) {
				break;
			}
		}
		
		int[] out = new int[i - 1];
		k = 0;
		for(i = 1;i < out.length + 1;i++) {
			out[k++] = job[j[i] - 1].id;
		}
		
		return out;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Job[] jobs = new Job[]{new Job(1, 100, 2), new Job(2, 10, 1), 
				new Job(3, 15, 2), new Job(4, 20, 1)};
		int[] out = exeJS(jobs);
		for(int i = 0;i < out.length;i++) {
			System.out.println(out[i]);
		}
	}

}
