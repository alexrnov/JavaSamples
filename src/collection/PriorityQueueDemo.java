package collection;

import java.util.PriorityQueue;

class PriorityQueueDemo {
	public static void main(String... args) {    
		PriorityQueue<Integer> q = new PriorityQueue<>();
		q.add(5);
		q.add(3);
		q.add(7);
		q.add(8);
		q.forEach(e -> System.out.println(e));
		Integer i = q.remove();
		
		System.out.println("--------------------");
		System.out.println(i);
		System.out.println("--------------------");
		q.forEach(e -> System.out.println(e));
	}
}



		

    
    
    
    
    
    
    
    