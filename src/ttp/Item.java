package ttp;

public class Item {
	public final int index, profit, weight, node;
	public double score, t_dxi, t_totaldistance, u;
	
	public Item(int i, int p, int w, int n) {
		index =i;
		profit = p;
		weight = w;
		node = n;
		score = 0;
		t_dxi = 0;
		t_totaldistance = 0;
		u = 0;
	}
}
