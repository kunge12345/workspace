package RmDuplica;

import java.util.Hashtable;

class LinkedListNode {
	LinkedListNode next = null;
	int data;
	
	public LinkedListNode(int d){
		data = d;
	}
	
	void appendToTail(int d){
		LinkedListNode end = new LinkedListNode(d);
		LinkedListNode n = this;
		while (n.next != null){
			n = n.next;
		}
		n.next = end;
	}
	
	public static void	 deleteDups(LinkedListNode n){
		Hashtable table = new Hashtable();
		LinkedListNode previous = null;
		while(n != null){
			if (table.containsKey(n.data)){
				previous.next = n.next;
			}
			else{
				table.put(n.data, true);
				previous = n;
			}
			n = n.next;
		}
	}
	
	public static int nthToLast(LinkedListNode head, int k) {
		if (head == null) {
			return 0;
		}
		int i = nthToLast(head.next, k) + 1;
		if (i==k){
			System.out.println(head.data);
		}
		return i;
	}
	
	
	public static void main(String[] args) {
		LinkedListNode n = new LinkedListNode(4);
		n.appendToTail(4);
		n.appendToTail(4);
		n.appendToTail(5);
		n.appendToTail(5);
		n.appendToTail(6);
		n.appendToTail(6);
		int a = nthToLast(n, 2);
		System.out.println(a);
	}
}
