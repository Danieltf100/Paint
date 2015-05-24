import java.awt.*;
import java.util.*;

public class Elipse extends Figura
{
    protected Ponto centro;

    protected int raio1, raio2;
	
    public Elipse (int x, int y, int r1, int r2)
    {
        this (x, y, r1, r2, Color.BLACK, Color.GRAY);
    }
	
    public Elipse (int x, int y, int r1, int r2, Color cor, Color interior)
    {
        super (cor, interior);

        this.centro = new Ponto (x,y);

        this.raio1  = r1;
        this.raio2  = r2;
    }

    public Elipse (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());

        int   r1  = Integer.parseInt(quebrador.nextToken());
        int   r2  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
        
        Color interior = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                		Integer.parseInt(quebrador.nextToken()),  // G
                		Integer.parseInt(quebrador.nextToken())); // B
        
        this.centro = new Ponto (x,y,cor, interior);
        this.raio1  = r1;
        this.raio2  = r2;
        this.setCor(cor);
        this.setInterior(interior);
    }

    public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y,this.getCor(), this.getInterior());
    }

    public void setRaio1 (int r1)
    {
        this.raio1 = r1;
    }

    public void setRaio2 (int r2)
    {
        this.raio2 = r2;
    }

    public Ponto getCentro ()
    {
        return this.centro;
    }

    public int getRaio1 ()
    {
        return this.raio1;
    }

    public int getRaio2 ()
    {
        return this.raio2;
    }

    public void torneSeVisivel (Graphics g)
    {
        g.setColor (this.interior);
        g.fillOval (this.centro.getX()-raio1, this.centro.getY()-raio2, 2*raio1, 2*raio2);
        g.setColor (this.cor);
        g.drawOval (this.centro.getX()-raio1, this.centro.getY()-raio2, 2*raio1, 2*raio2);
			
    }

    public String toString()
    {
        return "e:" +
               this.centro.getX() +
               ":" +
               this.centro.getY() +
               ":" +
               this.raio1 +
               ":" +
               this.raio2 +
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
    	int r2 = (int)(Math.sqrt((Math.pow(centro.getX()-centro.getX(),2)) + (Math.pow(y-centro.getY(),2))));
		int r1 = (int)(Math.sqrt((Math.pow(x-centro.getX(),2)) + (Math.pow(centro.getY()-centro.getY(),2))));
		if(r1 <= this.raio1 && r2 <= this.raio2)
			return true;
    	return false;
    }
    public void mover(int x, int y)
    {
    	this.setCentro(x, y);
    }
}