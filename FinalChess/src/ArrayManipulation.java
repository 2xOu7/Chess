
public class ArrayManipulation {
	
	public static void right_rotation(int[] x, int r) {
		int prev = x[x.length-1];
		for (int index = 0; index < r; index++){
			for (int i = 0; i < x.length; i++) {
				int tmp = x[i];
				x[i] = prev;
				prev = tmp;
			}
			prev = x[x.length-1];
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] x = new int[] {1,2,3,4,5};
		right_rotation(x,1);
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i]);
		}
		
	}

}
