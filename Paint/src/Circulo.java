import java.awt.*;
import java.util.*;

public class Circulo extends Figura
{
    protected Ponto centro;
    protected int   raio;
	
    public Circulo (int x, int y, int r)
    {
        this (x, y, r, Color.BLACK, Color.GRAY);
    }
	
    public Circulo (int x, int y, int r, Color cor, Color interior)
    {
        super (cor, interior);

        this.centro = new Ponto (x,y);
        this.raio   = r;
    }
    
    public Circulo (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());

        int   r   = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
        
        Color interior = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
        this.setCor(cor);
        this.setInterior(interior);
        this.centro = new Ponto (x,y);
        this.raio = r;
    }

    public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y,this.getCor(), this.getInterior());
    }

    public void setRaio (int r)
    {
        this.raio = r;
    }

    public Ponto getCentro ()
    {
        return this.centro;
    }

    public int getRaio ()
    {
        return this.raio;
    }

    public void torneSeVisivel (Graphics g)
    {
        g.setColor (this.interior);        
        g.fillOval (this.centro.getX()-raio, this.centro.getY()-raio, 2*raio, 2*raio);
        g.setColor (this.cor);
        g.drawOval (this.centro.getX()-raio, this.centro.getY()-raio, 2*raio, 2*raio);
    }

    public String toString()
    {
        return "c:" +
               this.centro.getX() +
               ":" +
               this.centro.getY() +
               ":" +
               this.raio +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue() +
        	   ":" +
               this.getInterior().getRed() +
               ":" +
               this.getInterior().getGreen() +
               ":" +
               this.getInterior().getBlue();
    }
    
    public boolean contains(int x, int y)
    {
    	int raioDado =  (int)(Math.sqrt((Math.pow(x-centro.getX(),2)) + (Math.pow(y-centro.getY(),2))));
    	if((this.raio >= raioDado))
    		return true;
    	return false;
    }
    
    public void mover(int x, int y)
    {
    	this.centro.setX(x);
    	this.centro.setY(y);
    }
}