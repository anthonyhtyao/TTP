package arbres;

import graphe.Ville;

import java.util.HashMap;

// Q1

public class UnionFind {
	//parent relation, parent.put(src,dst) indicates that src points to dst
    private HashMap<Ville,Ville> parent;
    
    public UnionFind( ){
        //TODO
    	parent = new HashMap<Ville,Ville>();
    }
    
    public Ville find( Ville src ){
        //TODO
    	Ville v = src;
    	while(!(parent.get(v) == null)){
    		v = parent.get(v);
    		parent.put(src,v);
    	}
    	return v;
    }
    
    public void union( Ville v0, Ville v1 ){
        //TODO
    	Ville p1 = find(v0);
    	Ville p2 = find(v1);
    	if(!(p1.equals(p2))){
    		parent.put(p2, p1);
    	}
    	return;
    }
}
