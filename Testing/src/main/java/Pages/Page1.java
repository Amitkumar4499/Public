package Pages;

import java.util.List;

public class Page1 {

    public static void main(String[] args) {
	int[] arr = { 1, 1, 2, 4, 4, 5 };
	List<Integer> n;
	for (int i = 0; i < arr.length; i++) {
	    for (int j = 0; j < i; j++) {
		if (arr[i] == arr[j]) {
		    System.out.println(arr[i]);
		}
	    }
	}
    }

}
