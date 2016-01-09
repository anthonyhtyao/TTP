package ttp;
import graphe.*;
import java.util.*;

public class SampleHeuristic {

/*SH*/
	public static int[] SH(TTPInstance test, LinkedList<Ville> TSP) {
		
		int[] P = new int[test.numberOfItems];
		for (int i =0; i < test.numberOfItems; i++) {
			P[i] = 0;
		}
		
		double[] D = distance(test, TSP);
		Ville start = TSP.getFirst();
		double totaletime = (double) D[start.getNom()]/test.maxSpeed; /*D[start.getnom()] is total distance*/
		int totalweight = 0;
		for (int i = 0; i < test.numberOfItems; i++) {
			totalweight += test.items[i][2];
		}
		double speedratio = (double) (test.maxSpeed-test.minSpeed)/totalweight; 
		
		/*ItemInfo(t,t',score,u)*/
		ArrayList<Item> ItemInfo = new ArrayList<Item>();
		for (int i = 0; i < test.numberOfItems; i++) {
			int profit = test.items[i][1];
			int weight = test.items[i][2];
			int node = test.items[i][3];
			double d = D[node];
			Item o = new Item(i,profit,weight,node);
			o.t_dxi = (double) d/(test.maxSpeed - speedratio*weight);
			o.t_totaldistance = (double) D[start.getNom()]/(test.maxSpeed - speedratio*weight);
			o.score = profit - test.rentingRatio*o.t_dxi;
			o.u = test.rentingRatio*totaletime + profit - test.rentingRatio*o.t_dxi;
			ItemInfo.add(o);
		}
		
		/*Sort Item by score*/
		Collections.sort(ItemInfo, new ItemComparator());
		int Wc = 0;
		for (Item o:ItemInfo) {
			if (Wc+o.weight < test.capacityOfKnapsack && o.u > 0){
				P[o.index] = 1;
				Wc += o.weight;
			}
			if (Wc == test.capacityOfKnapsack)
				break;
		}
		return P;
	}
	
	/*Calcule d_xi*/
	public static double[] distance(TTPInstance test, LinkedList<Ville> TSP) {
		double[] D = new double[test.numberOfNodes];
		Ville[] lst = new Ville[test.numberOfNodes];
		double distancetotale = 0;
		Ville start = TSP.getFirst();
		int i = 0;
		for (Ville v:TSP) {
			lst[i] = v;
			i++;
		}
		for (int j = test.numberOfNodes-1; j >= 0; j--) {
			if (j == test.numberOfNodes-1) {
				Ville v = lst[j];
				double d = test.distances(v.getNom(), start.getNom());
				D[v.getNom()] = d;
				distancetotale += d;
			}
			else {
				Ville v1 = lst[j];
				Ville v2 = lst[j+1];
				double d = test.distances(v1.getNom(), v2.getNom());
				distancetotale += d;
				D[v1.getNom()] = distancetotale;
			}
		}
		return D;
	}
}
