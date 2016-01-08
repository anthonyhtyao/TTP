package ttp;
import graphe.*;

import java.util.*;

/*class EularianVille {
	Ville v;
	int d1;
	int d2;
	
	public EularianVille(Ville o) {
		v = o;
		d1 = 0;
		d2 = 0;
	}
	
	public EularianVille(Ville o, int i, int j) {
		v = o;
		d1 = i;
		d2 = j;
	}
}*/

public class EulerianCycle {
	
	public static LinkedList<Arc> findCycle(Ville s, HashMap<Ville,Collection<Arc>> doubleMST,int[][] degree) {
		LinkedList<Arc> p = new LinkedList<Arc>();
		Ville v =s;
		while (degree[s.getNom()][0] != 0) {
			Collection<Arc> arcs = doubleMST.get(v);
			Arc a = arcs.iterator().next();
			p.add(a);
			arcs.remove(a);
			degree[v.getNom()][0] -= 1;
			v = a.target;
			degree[v.getNom()][1] -= 1;
		}
		return p;
	}
	
	public static LinkedList<Arc> eular(HashMap<Ville,Collection<Arc>> doubleMST) {
		LinkedList<Arc> p1 = new LinkedList<Arc>();
		LinkedList<Arc> p2 = new LinkedList<Arc>();
		
		return p1;
	}
}
