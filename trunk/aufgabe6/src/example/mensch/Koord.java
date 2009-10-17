package mensch;  

class Koord {
    /**
     * Geopaket zur Adressierung von Punkten auf dem Canvas
     */

    // Daten

    private double x,y;

    // Methoden zur Kapselung

    private void setX(double a) {
        x=a;
    }

    private void setY(double a) {
        y=a;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Konstruktor

    Koord(double a, double b) {
         x=a;
         y=b;
    }

    // öffentliche Methoden

    public double skalarP(Koord k) {
        return getX()*k.getX()+getY()*k.getY();
    }

    public Koord mal(double d) {
        return new Koord(getX()*d,getY()*d);
    }

    public double betrag() {
        return Math.sqrt( getX()*getX()+getY()*getY() );
    }

    public Koord minus(Koord k) {
        return new Koord(getX()-k.getX(),getY()-k.getY());
    }

    public Koord plus(Koord k) {
        return new Koord(getX()+k.getX(),getY()+k.getY());
    }

    public Koord orthogonal() {
        return new Koord(-getY(),getX());
    }

    // wird von System.out.println() verwendet

    public String toString() {
        String s = "("+getX()+","+getY()+")";
        return s;
    }

    public boolean equals(Object o) {
        if (this.getClass() == o.getClass())
            return ( ((Koord)o).getX()==getX() )&&( ((Koord)o).getY()==getY() );
        else return false;
    }

    public Object clone() {
        return new Koord(getX(),getY());
    }
}