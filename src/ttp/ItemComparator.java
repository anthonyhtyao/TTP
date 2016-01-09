package ttp;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item>{
	@Override
	public int compare(Item i1, Item i2) {
        double s1 = i1.score;
        double s2 = i2.score;
        if( s1 < s2 )
            return 1;
        else if( s1 > s2 )
            return -1;
        else
            return 0;
    }
	
	
}
