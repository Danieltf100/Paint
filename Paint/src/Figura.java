import java.awt.*;

public abstract class Figura // implements Cloneable
{
    protected Color cor, interior;
	  
    protected Figura ()
    {
        this (Color.BLACK, Color.GRAY);
    }
	  
    protected Figura (Color cor, Color interior)
    {
        this.setCor (cor);
        this.setInterior(interior);
    }
	
    public void setCor (Color cor)
    {
        this.cor = cor;
    }
	
    public void setInterior (Color cor)
    {
        this.interior = cor;
    }
    
    public Color getCor()
    {
    	return this.cor;
    }
    
    public Color getInterior(){
    	return this.interior;
    }
    
  //public abstract Object  clone          ();
  //public abstract boolean equals         (Object obj);
  //public abstract int     hashCode       ();
    public abstract String  toString       ();
    public abstract void    torneSeVisivel (Graphics g);
    public abstract boolean contains(int x, int y);
    public abstract void    mover(int x, int y);
}