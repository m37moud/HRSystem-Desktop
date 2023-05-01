import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class idmap {
	public int size;
	
	ArrayList<String>arr2 ;
	
	
	public ArrayList<String>[] arr;

	public idmap(int size) {
		this.size = size;
		arr = new ArrayList[size];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new ArrayList();
		}
	}

	public void addEdge(int id, String depart) {
		arr[id].add(depart);

	}

	public void printids(idmap g) {
		for (int i = 0; i < g.size; i++) {
			System.out.println(" head :" + i);
			System.out.print(" ( " + i + " ) -->> ");
			for (String v : g.arr[i]) {
				System.out.print(" " + v);
			}
			System.out.println();

		}
	}

	public int getsize() {

		return this.size;
	}

	public ArrayList<String>getval(int id)
	{
		ArrayList<String> val = new ArrayList<>();
			for(int i = 0; i < arr[id].size(); i++){
			
			val.add(arr[id].get(i));
		}
			
		
		return val;
	}

	@Override
	public String toString() {
		return "idmap [size=" + size + ", arr=" + Arrays.toString(arr) + "]";
	}

}
