package method;

public class TestArrayFindDiff {
	public static void main(String [] args){
		byte[]a={-8, -8, -8, 72, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -8, -8, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -40, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, -8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 104, 104, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, -72, -72, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, -8, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 104, -8, 72, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -8, -8, 88, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -8, -8, -8, -120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -8, -8, -8, -8, -56, 8, 0, 0, 0, 0, 0, 0, 0, 0, 56, -8, -8, -8, -8, -8, -8, 88, 0, 0, 0, 0, 0, 0, 8, -40};
		byte[]b={-8, -8, -8, 72, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -8, -8, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -40, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 0, 0, 0, 0, 0, 0, 8, 8, 0, 0, 0, 0, 0, -120, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 40, -8, 0, 0, 8, 8, 8, 8, 16, 16, 8, 8, 8, 0, 0, 104, 104, 0, 0, 8, 8, 8, 16, 16, 16, 16, 8, 8, 0, 0, 0, 0, 0, 0, 8, 8, 8, 16, 16, 16, 16, 16, 8, 0, 8, -72, -72, 0, 0, 0, 8, 8, 8, 16, 16, 16, 16, 8, 0, 0, 40, -8, 40, 0, 0, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 104, -8, 72, 0, 0, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0, -8, -8, 88, 0, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, -8, -8, -8, -120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -8, -8, -8, -8, -56, 8, 0, 0, 0, 0, 0, 0, 0, 0, 56, -8, -8, -8, -8, -8, -8, 88, 0, 0, 0, 0, 0, 0, 8, -40};
		int count = 0;
		for(int i=0;i<a.length;i++){
			if(a[i]==b[i])
				count++;
		}
		System.out.println(a.length+"   "+count);
	
	}
}
