package exercicejava6BForm;

import java.util.ArrayList;
import java.util.Collections;

public class App {

	public static void main(String[] args) {
		Interval intA = new Interval(5, 12);
		System.out.println(intA);
		
		for (int i : intA) {
			System.out.println(i);
		}
		
		ArrayList<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(4, 8));
		intervals.add(new Interval(4, 12));
		intervals.add(new Interval(4, -2));
		intervals.add(new Interval(4, 9));
		intervals.add(new Interval(6, 8));
		intervals.add(new Interval(8, 15));
		
		for( Interval it : intervals)
			System.out.println(it);
		System.out.println("------------------------");
		Collections.sort(intervals);
		for( Interval it : intervals)
			System.out.println(it);
		
	}

}
