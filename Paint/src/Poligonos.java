import java.awt.*;
import java.util.*;

public class Poligonos extends Figura {
	protected int[] coordX, coordY;
	protected int qntdLados;
	public Poligonos (int[] coordX, int[] coordY, int qntdLados)
	{
		this (coordX,coordY, qntdLados, Color.BLACK,Color.WHITE);
	}
	
	public Poligonos (int[] coordX, int[] coordY, int qntdLados, Color c, Color i)
	{
		super (c,i);
		this.coordX = coordX;
		this.coordY = coordY;
		this.qntdLados  = qntdLados ;
	}

	public void torneSeVisivel (Graphics g) {
		g.setColor(interior);
		g.fillPolygon(this.coordX, this.coordY, qntdLados);
		g.setColor(cor);
		g.drawPolygon(this.coordX, this.coordY, qntdLados);
	}
	public String toString() {
		String retorno = "k:"; 
		int x=qntdLados;
		retorno += this.qntdLados + ":";
		while (x != 0)
		{
			retorno += this.coordX[x] +":"+ this.coordY[x] +":";
			x--;
		}
		retorno +=this.getCor().getRed()   +
	              ":" +
	              this.getCor().getGreen() +
	              ":" +
	              this.getCor().getBlue() +
	              ":" +
	              this.getInterior().getRed()   +
	              ":" +
	              this.getInterior().getGreen() +
	              ":" +
	              this.getInterior().getBlue();
		return retorno;
	}
	
	public Poligonos (String s)
	{
		StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();
        int qtd = Integer.parseInt(quebrador.nextToken()), x[] = new int[qtd], y[] = new int[qtd];
        for(int i=0;i<=qtd;i++){
        	x[i] = Integer.parseInt(quebrador.nextToken());
        	y[i] = Integer.parseInt(quebrador.nextToken());
        }
        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                			   Integer.parseInt(quebrador.nextToken()),  // G
                			   Integer.parseInt(quebrador.nextToken())); // B
        
        Color interior = new Color (Integer.parseInt(quebrador.nextToken()),  // R
 			                  		Integer.parseInt(quebrador.nextToken()),  // G
 			                  		Integer.parseInt(quebrador.nextToken())); // B
        
        this.qntdLados = qtd;
        this.coordX = new int[qntdLados];
        this.coordY = new int[qntdLados];
        this.coordX = x;
        this.coordY = y;
        this.setCor(cor);
        this.setInterior(interior);
        
	}
	
	public void setCoords(int[] coordX, int[] coordY) throws Exception
	{
		try{
			if(coordX.length == coordY.length){
				this.coordX = coordX;
				this.coordY = coordY;
				this.qntdLados = coordX.length;
			}
		}catch(Exception erro){
			throw new Exception("Quantidade de coordenadas X diferentes de coordenadas Y");
		}
	}
	public void setCoordX(int valor, int posicao) throws Exception
	{
		try{
			if(posicao <= this.qntdLados)
				this.coordX[posicao] = valor;
		}catch(Exception erro)
		{
			throw new Exception("Posição maior do que as existentes, considere usar o método addX()");
		}
	}
	
	public void setCoordY(int valor, int posicao) throws Exception
	{
		try{
			if(posicao <= this.qntdLados)
				this.coordY[posicao] = valor;
		}catch(Exception erro)
		{
			throw new Exception("Posição maior do que as existentes, considere usar o método addY()");
		}
	}
	
	public void addX(int valor)
	{
		this.qntdLados++;
		this.coordX[qntdLados] = valor;
	}
	public void addY(int valor)
	{
		this.qntdLados++;
		this.coordY[qntdLados] = valor;
	}
	public void addXY(int valorX, int valorY){
		this.qntdLados++;
		this.coordX[qntdLados] = valorX;
		this.coordY[qntdLados] = valorY;
	}
	
	public int[] getCoordX()
	{
		return this.coordX;
	}
	public int getCoordX(int posicao)
	{
		if(posicao<=qntdLados)
			return this.coordX[posicao];
		return -1;//-1 = posição não existe.
	}
	
	public int[] getCoordY()
	{
		return this.coordX;
	}
	public int getCoordY(int posicao)
	{
		if(posicao<=qntdLados)
			return this.coordX[posicao];
		return -1;//-1 = posição não existe.
	}
	
	public boolean contains(int x, int y)
	{
		int maiorX=0, maiorY=0;
		for(int i=0;i<=this.qntdLados;i++)
		{
			if(i==0){
				maiorX = this.coordX[i];
				maiorY = this.coordY[i];
			}
			else{
				if(this.coordX[i]>maiorX)
					maiorX = this.coordX[i];
				if(this.coordY[i]>maiorY)
					maiorY = this.coordY[i];
			}
		}
		int atual = ((maiorX-this.coordX[0])*(maiorY-this.coordY[0]));
		int dado = ((maiorX-x)*(maiorY-y));
		if(dado<=atual)
			return true;
		return false;
	}
	public void mover(int x, int y)
	{
		if(x > this.coordX[this.qntdLados])
		{
			for(int i=0;i<=this.qntdLados;i++)		
			{
				this.coordX[i] = this.coordX[i]+8;
			}	
		}
		if(y > this.coordY[this.qntdLados])
		{
			for(int i=0;i<=this.qntdLados;i++)		
			{
				this.coordY[i] = this.coordY[i]+8;
			}	
		}
		if(x < this.coordX[this.qntdLados])
		{
			for(int i=0;i<=this.qntdLados;i++)		
			{
				this.coordX[i] = this.coordX[i]-8;
			}	
		}
		if(y < this.coordY[this.qntdLados])
		{
			for(int i=0;i<=this.qntdLados;i++)		
			{
				this.coordY[i] = this.coordY[i]-8;
			}	
		}
	}
}
