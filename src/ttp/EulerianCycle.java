package ttp;
import graphe.*;

import java.util.*;

public class EulerianCycle {
	
	/*Calcul degree of each node*/
	public static int[][] degree(HashMap<Ville,Collection<Arc>> doubleMST, int numberofNodes) {
		int[][] degree = new int[numberofNodes][2];
		Collection<Collection<Arc>> lst = doubleMST.values();
		for(Collection<Arc> c:lst) {
			for(Arc a:c) {
				degree[a.source.getNom()][0] += 1;
				degree[a.target.getNom()][1] += 1;
			}
		}
		return degree;
	}
	
	/*Find a cycle started by Ville s*/
	public static LinkedList<Arc> findCycle(Ville s, HashMap<Ville,Collection<Arc>> doubleMST,int[][] degree) {
		LinkedList<Arc> p = new LinkedList<Arc>();
		Ville v =s;
		while (degree[v.getNom()][0] != 0) {
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
	
	/*Find Eularian cycle started by Ville s (random?)*/
	public static LinkedList<Arc> eular(Ville s, HashMap<Ville,Collection<Arc>> doubleMST, int numberofNodes) {
		int[][] degree = degree(doubleMST,numberofNodes);
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
	
	/*Return TSP*/
	public static LinkedList<Ville> TSP(HashMap<Ville,Collection<Arc>> doubleMST,int numberofNodes) {
		Ville s = doubleMST.values().iterator().next().iterator().next().source; /*Get a start Ville. Need a random function*/
		LinkedList<Arc> eularCycle = eular(s,doubleMST,numberofNodes);
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
