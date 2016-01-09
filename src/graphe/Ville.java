package graphe;

import java.awt.Graphics;

public class Ville {

    private final int nom;

   
    private final double latitude, longitude;
    static int precision=2;

    public Ville(int s, double lat,double longi) {
    	nom = s;
    	latitude = lat;
    	longitude = longi; 
    }

    public Ville() {
		// TODO Auto-generated constructor stub
    	this(0,0,0);
	}

	public String toString() {
    	return nom+"\tLatitude: "+latitude+"\t"+"Longitude: "+longitude;
    }

    public int getNom() {
	return nom;
    }

    public double getLatitude() {
	return latitude;
    }

    public double getLongitude() {
	return longitude;
    }

    private long getRoundLatitude() {
	return Math.round(latitude*(Math.pow(10.,precision)));
    }

    private long getRoundLongitude() {
	return Math.round(longitude*(Math.pow(10.,precision)));
    }

    private int tLat(double latitude, double latmin, double latmax, int h) {
	return h-(int) ((latitude-latmin)/(latmax-latmin)*h);
    }
    private int tLong(double longitude, double lonmin, double lonmax, int w) {
	return (int) ((longitude-lonmin)/(lonmax-lonmin)*w);
    }

    private double sqr(double a) {
	    return a*a;
		}

    public double distance(Ville dest) {
	// utilise la distance ellipsoidale de vincenty
	double R = 6371000; // rayon de la terre
	return R*Math.sqrt(sqr((getLatitude()-dest.getLatitude()))+sqr((getLongitude()-dest.getLongitude())))/180.0*Math.PI; 
    }
      
    public boolean equals(Object obj){
	return (obj instanceof Ville)&&(getRoundLatitude()==((Ville) obj).getRoundLatitude())&&(getRoundLongitude()==((Ville) obj).getRoundLongitude());
    }

    public int hashCode(){
	return nom;
    }
}

