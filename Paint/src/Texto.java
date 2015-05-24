import java.awt.*;
import java.util.*;

public class Texto extends Figura
{

	protected Ponto centro;
	protected String texto;
	protected Font   fontStyle;
	
	public Texto(String texto, int x, int y)
	{
		this(texto,x,y,Color.BLACK,Color.WHITE,Font.getFont("TimesRoman"));
	}
	
	public Texto(String texto, int x, int y, Color b, Color i,Font font)
	{
		super(b,i);
		this.centro = new Ponto(x,y);
		this.texto  = texto;
		this.fontStyle = font;
	}
	
	public Texto(String s)
	{
		StringTokenizer quebrador = new StringTokenizer(s,":");
		
		quebrador.nextToken();
		int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());
        
        Font font = Font.getFont(quebrador.nextToken());
        
        String   t   = quebrador.nextToken();
        
        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
        
        this.centro = new Ponto (x,y,cor, interior);
        this.texto = t;
        this.fontStyle = font;
        this.cor    = cor;
	}
	
	public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y,this.getCor(), this.getInterior());
    }
	
	public void setTexto(String t)
	{
		this.texto = t;
	}
	
	public Ponto getCentro ()
    {
        return this.centro;
    }

    public String getTexto()
    {
    	return this.texto;    	
    }
    
	public String toString(){
		return "t:" + 
				this.centro.getX() + 
				":" +
				this.centro.getY() + 
				":"+
				this.fontStyle +
				":" +
				this.texto +
				":" +
				this.getCor().getRed() +
	            ":" +
	            this.getCor().getGreen() +
	            ":" +
	            this.getCor().getBlue();
	}
	
	public void torneSeVisivel (Graphics g) {
		g.setColor(cor);
		g.setFont(fontStyle);
		g.drawString(this.texto, this.centro.getX(), this.centro.getY());
	}
	
	public boolean contains(int x, int y)
	{		
		
		if(x>=this.centro.getX() && x<=this.centro.getX()+50 && y<=this.centro.getY() && y>=this.centro.getY()-10)
			return true;
		return false;
	}
	public void mover(int x, int y)
	{
		this.setCentro(x, y);
	}
}
