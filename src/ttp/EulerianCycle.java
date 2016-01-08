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
	
	public static LinkedList<Arc> eular(Ville s, HashMap<Ville,Collection<Arc>> doubleMST,int[][] degree) {
		LinkedList<Arc> p1 = new LinkedList<Arc>();
		LinkedList<Arc> p2 = new LinkedList<Arc>();
		while (true) {
			LinkedList<Arc> p = findCycle(s, doubleMST, degree);
			p2.addAll(0,p);
			if (p2.isEmpty())
				return p1;
			else {
				Arc a = p2.remove();
				p1.add(a);
				s = a.target;
			}
		}
	}
	
	public static LinkedList<Ville> TSP(LinkedList<Arc> eularCycle,int numberofNodes) {
		boolean[] isVisited = new boolean[numberofNodes];
		LinkedList<Ville> TSP = new LinkedList<Ville>();
		Arc a = eularCycle.getFirst();
		Ville v = a.source;
		TSP.add(v);
		isVisited[v.getNom()] = true;
		while (!eularCycle.isEmpty()) {
			a = eularCycle.remove();
			v = a.target;
			if (!isVisited[v.getNom()]) {
				TSP.add(v);
				isVisited[v.getNom()] = true;
			}
		}
		return TSP;
	}
}
