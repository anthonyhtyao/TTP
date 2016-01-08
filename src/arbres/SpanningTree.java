package arbres;

import java.util.*;

import ttp.TTPInstance;

import graphe.*;

public class SpanningTree {
	
    public static ArrayList<Arc> touslesArc(TTPInstance test){
    	ArrayList<Arc> lst = new ArrayList<Arc>();
    	int len = test.nodes.length;
    	for (int i=0; i<len; i++) {
    		Ville vi = new Ville(i,test.nodes[i][1],test.nodes[i][2]);
    		for (int j=i+1; j<len; j++) {
    			Ville vj = new Ville(j,test.nodes[j][1],test.nodes[j][2]);
    			double distance = test.distances(i, j);
    			Arc arc1 = new Arc(vi,vj,distance);
    			Arc arc2 = new Arc(vj,vi,distance);
    			lst.add(arc1);
    			lst.add(arc2);
    		}
    	}
    	return lst;
    }
    
    public static Collection<Arc> kruskal(UnionFind u, ArrayList<Arc> g){
    	// Q2
    	ArrayList<Arc> lst = new ArrayList<Arc>();
    	List<Arc> arcs = g;
    	Collections.sort(arcs, new ArcComparator());
    	for (Arc a : arcs) {
    		Ville v1 = a.source;
    		Ville v2 = a.target;
    		Ville p1 = u.find(v1);
    		Ville p2 = u.find(v2);
    		if (!(p1.equals(p2))) {
    			lst.add(a);
    			u.union(v1,v2);
    		}
    	}
    	return lst;
    }
    
    public static HashMap<Ville,Collection<Arc>> doubleMST(ArrayList<Arc> g){
    	// Q3
    	UnionFind u = new UnionFind();
    	Collection<Arc> arcs = kruskal(u,g);
    	HashMap<Ville,Collection<Arc>> arcsMap = new HashMap<Ville, Collection<Arc>>();
    	for (Arc a : arcs) {
    		Ville v1 = a.source;
    		Ville v2 = a.target;
    		Collection<Arc> colonne1 = arcsMap.get(v1);
    		if (colonne1 == null) {
    			colonne1 = new ArrayList<Arc>();
    			colonne1.add(a);
    			arcsMap.put(v1, colonne1);
    		}
    		else {
    			colonne1.add(a);
    		}
    		Collection<Arc> colonne2 = arcsMap.get(v2);
    		Arc b = new Arc(a.target,a.source,a.length);
    		if (colonne2 == null) {
    			colonne2 = new ArrayList<Arc>();
    			colonne2.add(b);
    			arcsMap.put(v1, colonne2);
    		}
    		else {
    			colonne2.add(b);
    		}
    	}
    	return arcsMap;
    }
    
}
