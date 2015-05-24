import java.awt.*;
import java.util.*;

public class Quadrado extends Figura
{

	protected Ponto centro;
	protected int largura, altura;
	
	public Quadrado(int x, int y, int l, int a)
	{
		this(x,y,l,a,Color.BLACK,Color.WHITE);
	}
	
	public Quadrado(int x, int y, int l, int a, Color b, Color i)
	{
		super(b,i);
		this.centro = new Ponto(x,y);
		this.largura = l;
		this.altura  = a;
	}
	
	public Quadrado(String s)
	{
		StringTokenizer quebrador = new StringTokenizer(s,":");
		
		quebrador.nextToken();
		int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());

        int   l   = Integer.parseInt(quebrador.nextToken());
        int   a   = Integer.parseInt(quebrador.nextToken());
        
        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
        
        Color interior = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                			   Integer.parseInt(quebrador.nextToken()),  // G
                			   Integer.parseInt(quebrador.nextToken())); // B
        
        this.centro = new Ponto (x,y,cor, interior);
        this.largura   = l;
        this.altura    = a;
        this.setCor(cor);
        this.setInterior(interior);
	}
	
	public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y,this.getCor(), this.getInterior());
    }
	
	public void setLargura(int l)
	{
		this.largura = l;
	}
	
	public void setAltura(int a)
	{
		this.altura = a;
	}
	
	public Ponto getCentro ()
    {
        return this.centro;
    }

    public int getLargura ()
    {
        return this.largura;
    }
	
    public int getAltura ()
    {
    	return this.altura;
    }
    
	public String toString(){
		return "q:" + 
				this.centro.getX() + 
				":" +
				this.centro.getY() + 
				":" +
				this.largura +
				":" +
				this.altura +
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
	
	public void torneSeVisivel (Graphics g) {
		g.setColor(interior);
		g.fillRect(this.centro.getX(), this.centro.getY(), largura, altura);
		g.setColor(cor);
		g.drawRect(this.centro.getX(), this.centro.getY(), largura, altura);
	}
	
	public boolean contains(int x, int y)
	{
		int lado;
		if(this.getCentro().getX() >= x){
			lado = (int)Math.sqrt(Math.pow((this.getCentro().getX()-x),2));			
		}
		else{
			lado = (int)Math.sqrt(Math.pow((x-this.getCentro().getX()),2));
			}	
		int area = largura*altura;
		int dado = lado*lado;
		if(dado<=area)
			return true;
		return false;
	}
	public void mover(int x, int y)
	{
		this.setCentro(x, y);
	}
}
