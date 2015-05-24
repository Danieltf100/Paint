import java.awt.*;
import java.util.*;

public class Linha extends Figura
{
    protected Ponto p1, p2;
	
    public Linha (int x1, int y1, int x2, int y2)
    {
        this (x1, y1, x2, y2, Color.BLACK, Color.LIGHT_GRAY);
    }
	
    public Linha (int x1, int y1, int x2, int y2, Color cor, Color interior)
    {
        super(cor, interior);

        this.p1 = new Ponto (x1,y1,cor, interior);
        this.p2 = new Ponto (x2,y2,cor, interior);
    }

    public Linha (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x1  = Integer.parseInt(quebrador.nextToken());
        int   y1  = Integer.parseInt(quebrador.nextToken());

        int   x2  = Integer.parseInt(quebrador.nextToken());
        int   y2  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        this.p1  = new Ponto (x1,y1,cor, interior);
        this.p2  = new Ponto (x2,y2,cor, interior);
        this.cor = cor;
    }

    public void setP1 (int x, int y)
    {
        this.p1 = new Ponto (x,y,this.getCor(),this.getInterior());
    }

    public void setP2 (int x, int y)
    {
        this.p2 = new Ponto (x,y,this.getCor(), this.getInterior());
    }

    public Ponto getP1 ()
    {
        return this.p1;
    }

    public Ponto getP2 ()
    {
        return this.p2;
    }

    public void torneSeVisivel (Graphics g)
    {
        g.setColor(this.cor);
        g.drawLine(this.p1.getX(), this.p1.getY(),   // ponto inicial
                   this.p2.getX(), this.p2.getY());  // ponto final
    }

    public String toString()
    {
        return "l:" +
               this.p1.getX() +
               ":" +
               this.p1.getY() +
               ":" +
               this.p2.getX() +
               ":" +
               this.p2.getY() +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }
    
    public boolean contains(int x, int y)
    {
    	int dado = (int) Math.sqrt(Math.pow((this.p1.getX()-x), 2) + Math.pow((this.p1.getY()-y), 2));
    	int existente = (int)Math.sqrt(Math.pow((this.p1.getX()-this.p2.getX()), 2) + Math.pow((this.p1.getY()-this.p2.getY()), 2));
    	if(dado<=existente)
    		return true;
    	return false;
    }
    public void mover(int x, int y)
    {    	
    	this.p1.setX(x);
    	this.p1.setY(y);
    	
    }
}