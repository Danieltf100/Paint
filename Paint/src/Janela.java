import java.awt.*;
import java.awt.event.*;
import static java.awt.event.KeyEvent.VK_DELETE;

import javax.swing.*;
import javax.imageio.*;

import say.swing.JFontChooser;

import java.io.*;
import java.util.*;
import java.lang.Math;

public class Janela extends JFrame // implements Cloneable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton btnPonto       = new JButton ("Ponto"),
                    btnLinha       = new JButton ("Linha"),
                    btnCirculo     = new JButton ("Circulo"),
                    btnElipse      = new JButton ("Elipse"),
                    btnQuadrado    = new JButton ("Quadrado"),
                    btnRetangulo   = new JButton ("Retangulo"),
                    btnPoligono    = new JButton ("Poligono"),
                    btnTexto       = new JButton ("Texto"),
                    btnFontes      = new JButton ("Fontes"),
                    btnCorBorda    = new JButton ("Cor da borda"),
                    btnCorInterior = new JButton ("Cor do Interior"),
                    btnAbrir   = new JButton ("Abrir"),
                    btnSalvar  = new JButton ("Salvar"),
                    btnApagar  = new JButton ("Apagar"),
                    btnSair    = new JButton ("Sair"),
                    btnSelect  = new JButton ("Selecionar"),
                    btnCima    = new JButton ("Levar p/ Cima"),
                    btnBaixo   = new JButton ("Levar p/ Baixo"); //Inicializa botões
	
	public JMenuBar createMenuBar()
	{
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menuBar.add(menu);
		menuItem = new JMenuItem("Um menuItem",KeyEvent.VK_T);
		menu.add(menuItem);
				
		return menuBar;
	}
		
    private MeuJPanel pnlDesenho = new MeuJPanel ();
    
    private JLabel statusBar1 = new JLabel ("Mensagem:"),
    			   statusBar2 = new JLabel ("Coordenada:");
    
    private JFontChooser fontChooser = new JFontChooser();  
    
    boolean esperaPonto, esperaInicioReta, esperaFimReta,
    		esperaCentro, esperaRaio, esperaCentroElipse, esperaRaioElipse,
    		esperaInicioQuadrado, esperaFimQuadrado, iniRetangulo, fimRetangulo,
    		p1Texto, pt1Poligono, pt2Poligono, fimPoligono, confirmSaida = true,
    		selecionar, selecionado, arrastar, pCima, pBaixo;
    Font selecionada;
    String dialogo;
    
    int qntdLados, ptXinicial, ptYinicial, ultPtX, ultPtY, pos;
    int[] coordX = new int[200], coordY = new int[200];
    
    private Color corBorda    = Color.black, 
    		      corInterior = Color.white, 
    		      corAtual    = Color.BLACK;
    private Ponto p1;
    
    private Vector<Figura> figuras = new Vector<Figura>();
    private Figura aux;
    
    public Janela ()
    {
    	super("Editor Gráfico");
    	this.getContentPane().setBackground(Color.white);
        //aqui constroi os botões do programa
        try
        {
            Image btnPontoImg = ImageIO.read(getClass().getResource("resources/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ponto.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnLinhaImg = ImageIO.read(getClass().getResource("resources/linha.jpg"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo linha.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCirculoImg = ImageIO.read(getClass().getResource("resources/circulo.jpg"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo circulo.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnElipseImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo elipse.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnQuadradoImg = ImageIO.read(getClass().getResource("resources/quadrado.jpg"));
            btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo quadrado.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnRetanguloImg = ImageIO.read(getClass().getResource("resources/Retangulo.jpg"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo retangulo.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnPoligonoImg = ImageIO.read(getClass().getResource("resources/Poligono.jpg"));
            btnPoligono.setIcon(new ImageIcon(btnPoligonoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo retangulo.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnTextoImg = ImageIO.read(getClass().getResource("resources/texto.jpg"));
            btnTexto.setIcon(new ImageIcon(btnTextoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo texto.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnFontesImg = ImageIO.read(getClass().getResource("resources/fontes.jpg"));
            btnFontes.setIcon(new ImageIcon(btnFontesImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo fontes.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnCoresImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCorBorda.setIcon(new ImageIcon(btnCoresImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        } 
                
        try
        {
            Image btnCoresImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCorInterior.setIcon(new ImageIcon(btnCoresImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnAbrirImg = ImageIO.read(getClass().getResource("resources/abrir.jpg"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo abrir.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("resources/salvar.jpg"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo salvar.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnApagarImg = ImageIO.read(getClass().getResource("resources/apagar.jpg"));
            btnApagar.setIcon(new ImageIcon(btnApagarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo apagar.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSairImg = ImageIO.read(getClass().getResource("resources/sair.jpg"));
            btnSair.setIcon(new ImageIcon(btnSairImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo sair.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        btnPonto.addActionListener      (new DesenhoDePonto          ());
        btnLinha.addActionListener      (new DesenhoDeReta           ());
        btnCirculo.addActionListener    (new DesenhoDeCirculo        ());
        btnElipse.addActionListener     (new DesenhoDaElipse         ());
        btnQuadrado.addActionListener   (new DesenhoDeQuadrado       ());
        btnRetangulo.addActionListener  (new DesenhoDeRetangulo      ());
        btnPoligono.addActionListener   (new DesenhoDePoligono       ());
        btnTexto.addActionListener      (new DesenhoDeTexto          ());
        btnFontes.addActionListener     (new CaixaDeFontes           ());
        btnCorBorda.addActionListener   (new CaixaDeCoresDaBorda     ());
        btnCorInterior.addActionListener(new CaixaDeCoresDoInterior  ());
        
        btnSalvar.addActionListener     (new SalvarImagem            ());
        btnAbrir.addActionListener      (new AbrirImagem             ());
        btnSelect.addActionListener     (new SelecionarDesenho       ());
        btnApagar.addActionListener     (new SelecionarDesenho       ());
        
        btnCima.addActionListener       (new trazerParaCima          ());
        btnBaixo.addActionListener      (new trazerParaBaixo         ());
        
        JPanel     pnlBotoes = new JPanel(), pnlBotoes2 = new JPanel();
        FlowLayout flwBotoes = new FlowLayout(FlowLayout.LEFT,1,0);
        GridLayout gLayout = new GridLayout(15,1,0,0);
        pnlBotoes2.setLayout(gLayout);
        pnlBotoes.setLayout (flwBotoes);
        
        pnlBotoes2.add (btnAbrir);
        pnlBotoes2.add (btnSalvar);
        pnlBotoes.add (btnPonto);
        pnlBotoes.add (btnLinha);
        pnlBotoes.add (btnCirculo);
        pnlBotoes.add (btnElipse);
        pnlBotoes.add (btnQuadrado);
        pnlBotoes.add (btnRetangulo);
        pnlBotoes.add (btnPoligono);
        pnlBotoes.add (btnTexto);
        pnlBotoes2.add (btnSelect);
        pnlBotoes2.add (btnFontes);
        pnlBotoes2.add (btnCorBorda);
        pnlBotoes2.add (btnCorInterior);
        pnlBotoes2.add (btnApagar);
        pnlBotoes2.add (btnCima);
        pnlBotoes2.add (btnBaixo);
        pnlBotoes2.add (btnSair);
             
        JPanel     pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1,2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);
                
        Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        cntForm.add (pnlBotoes2, BorderLayout.WEST);
        cntForm.add (pnlBotoes,  BorderLayout.NORTH);
        cntForm.add (pnlDesenho, BorderLayout.CENTER);
        cntForm.add (pnlStatus,  BorderLayout.SOUTH);
                
        this.addWindowListener (new FechamentoDeJanela());
        this.setSize (700,500);
        this.setVisible (true);
    }

    private class MeuJPanel extends    JPanel 
    						implements MouseListener,
                                       KeyListener,
                                       MouseMotionListener 
    {
		private static final long serialVersionUID = 1L;

		public MeuJPanel()
        {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
            this.addKeyListener         (this);
        }

        public void paint (Graphics g)
        {
            for (int i=0 ; i<figuras.size(); i++)
                figuras.get(i).torneSeVisivel(g);
        }
                
		public void mousePressed (MouseEvent e)
        {
			if(selecionar)
			{
				boolean find = false;
				for (pos = ((figuras.size())-1); pos>=0 && find!=true;pos--){ 
					if(figuras.get(pos).contains(e.getX(),e.getY()))
						find = true;
				}
				if(find){ 
					arrastar = true;
					selecionado = true;
				}
			}
            if (esperaPonto)
            {
                figuras.add (new Ponto (e.getX(), e.getY(), corBorda, corInterior));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                esperaPonto = false;
            }
            
            else if (esperaInicioReta)
            {
                p1 = new Ponto (e.getX(), e.getY(), corBorda, corInterior);
                esperaInicioReta = false;
                esperaFimReta = true;
                statusBar1.setText("Mensagem: clique o ponto final da reta");    
            }
            
            else if (esperaFimReta)
            {
                esperaInicioReta = true;
                esperaFimReta = false;
                //figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corBorda, corInterior));
                //figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");    
           }
            
           else if(esperaCentro)
           {
       		p1 = new Ponto (e.getX(), e.getY(), corBorda, corInterior);
       		esperaCentro = false;
       		esperaRaio = true;
       		statusBar1.setText("Mensagem: clique o ponto final circulo");
           }
            
           else if(esperaRaio)
           {
   			esperaCentro = true;
   			esperaRaio   = false;
   			//int r =  (int)(Math.sqrt((Math.pow(e.getX()-p1.getX(),2)) + (Math.pow(e.getY()-p1.getY(),2))));
   			//figuras.add (new Circulo(p1.getX(), p1.getY(),r, corBorda, corInterior));
   			//figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
   			statusBar1.setText("Mensagem:");
   		   }
            
           else	if(esperaCentroElipse)
           {
				p1 = new Ponto (e.getX(), e.getY(), corBorda, corInterior);
				esperaCentroElipse = false;
				esperaRaioElipse = true;
				statusBar1.setText("Mensagem: Cliqui o ponto final da elipse");
		   }
            
           else	if(esperaRaioElipse)
           {
				esperaCentroElipse = false;
   			esperaRaioElipse   = false;
   			//int r1 = (int)(Math.sqrt((Math.pow(e.getX()-p1.getX(),2)) + (Math.pow(e.getY()-p1.getY(),2))));
   			//int r2 = (int)(Math.sqrt((Math.pow(p1.getX()-p1.getX(),2)) + (Math.pow(e.getY()-p1.getY(),2))));
   			//int r1 = (int)(Math.sqrt((Math.pow(e.getX()-p1.getX(),2)) + (Math.pow(p1.getY()-p1.getY(),2))));
   			//figuras.add (new Elipse(p1.getX(), p1.getY(), r1, r2, corBorda, corInterior));
   			//figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
   			statusBar1.setText("Mensagem:");
		   }
            
           else	if(esperaInicioQuadrado)
           {
				esperaInicioQuadrado = false;
				esperaFimQuadrado = true;
				p1 = new Ponto(e.getX(), e.getY(), corBorda, corInterior);
				statusBar1.setText("Mensagem: Clique o ponto final do Quadrado");
		   }
            
           else	if(esperaFimQuadrado)
           {	/*			
				int lado;
				if(p1.getX() >= e.getX()){
					lado = (int)Math.sqrt(Math.pow((p1.getX()-e.getX()),2));
					if(e.getX() <= p1.getX() && e.getY() >= p1.getY())
						figuras.add(new Quadrado(e.getX(), p1.getY(), lado, lado, corBorda, corInterior));
					else
						figuras.add(new Quadrado(e.getX(), e.getY(), lado, lado, corBorda, corInterior));
				}
				else{
					lado = (int)Math.sqrt(Math.pow((e.getX()-p1.getX()),2));
					if(e.getX() >= p1.getX() && e.getY() <= p1.getY())
						figuras.add(new Quadrado(p1.getX(), e.getY(), lado, lado, corBorda, corInterior));
					else
						figuras.add(new Quadrado(p1.getX(), p1.getY(), lado, lado, corBorda, corInterior));
				}
				//p4 = new Ponto(p1.getX(), e.getY(), corBorda, corInterior);
				//(int)(Math.sqrt((Math.pow(e.getX()-p1.getX(),2)) + (Math.pow(p1.getY()-p1.getY(),2))));
				*/
				figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
				statusBar1.setText("Mensagem: ");
		   }
            
           else	if(iniRetangulo)
           {
				iniRetangulo = false;
				fimRetangulo = true;
				p1 = new Ponto(e.getX(), e.getY(), corBorda, corInterior);
				statusBar1.setText("Mensagem: Clique o ponto final do Retangulo");
		   }
            
           else	if(fimRetangulo)
           {				
				//int largura = (int)(Math.sqrt((Math.pow(e.getX()-p1.getX(),2)) + (Math.pow(p1.getY()-p1.getY(),2))));
				//int altura  = (int)(Math.sqrt((Math.pow(p1.getX()-p1.getX(),2)) + (Math.pow(e.getY()-p1.getY(),2))));
				/*int base, altura;
				//1º e 4º quadrante:
				if(e.getX() >= p1.getX()){
					base = (int)Math.sqrt(Math.pow((e.getX()-p1.getX()),2));
					if(e.getY() <= p1.getY()){
						altura = (int)Math.sqrt(Math.pow((e.getY()-p1.getY()),2));
						figuras.add(new Retangulo(p1.getX(), e.getY(), base, altura, corBorda, corInterior));						
					}else{
						altura = (int)Math.sqrt(Math.pow((p1.getY()-e.getY()),2));
						figuras.add(new Retangulo(p1.getX(), p1.getY(), base, altura, corBorda, corInterior));
					}
				}//2º e 3º quadrante:
				else{
					base = (int)Math.sqrt(Math.pow((p1.getX()-e.getX()),2));
					if(e.getY() >= p1.getY()){
						altura = (int)Math.sqrt(Math.pow((e.getY()-p1.getY()),2));
						figuras.add(new Retangulo(e.getX(), p1.getY(), base, altura, corBorda, corInterior));						
					}else{
						altura = (int)Math.sqrt(Math.pow((p1.getY()-e.getY()),2));
						figuras.add(new Retangulo(e.getX(), e.getY(), base, altura, corBorda, corInterior));
					}
				}*/
				figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
				statusBar1.setText("Mensagem: ");
		   }   
           
           else if(p1Texto)
           {
        	   p1Texto = false;
        	   dialogo = null;
        	   p1 = new Ponto(e.getX(), e.getY(), corBorda, corInterior);
        	   dialogo = JOptionPane.showInputDialog("Digite o Texto:");
        	   repaint();
        	   rpd();
        	   if(dialogo!=null){
        		   figuras.add(new Texto(dialogo,p1.getX(),p1.getY(), corBorda, corInterior, selecionada));
        		   figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
        	   }
           }
            
           else if(pt1Poligono)
           {
       	   		p1 = new Ponto(e.getX(), e.getY(), corBorda, corInterior);
       	   		coordX[qntdLados] = p1.getX();
       	   		coordY[qntdLados] = p1.getY();
       	   		qntdLados++;
       	   		figuras.add( new Poligonos(coordX, coordY, qntdLados, corBorda, corInterior));
       	   		figuras.get(figuras.size()-1).torneSeVisivel(getGraphics());
       	   		pt1Poligono = false;
       	   		pt2Poligono = true;      
           }
           /* 
           else if(pt2Poligono)
           {
        	   if(p1.getX()!=e.getX() || p1.getY()!=e.getY())
        	   {
        		   coordX[qntdLados] = p1.getX();
        		   coordY[qntdLados] = p1.getY();
        		   qntdLados++;
        		   coordX[qntdLados] = e.getX();
        		   coordY[qntdLados] = e.getY();
        		   qntdLados++;
        		   pt1Poligono = true;
            	   pt2Poligono = false;
        	   }
        	   else
        	   {        
        		   statusBar1.setText("Mensagem: Desenhando Poligono...");
            	   figuras.add(new Poligonos (coordX, coordY, qntdLados, corBorda, corInterior));
    			   figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
    		   	   qntdLados = 0;
    			   statusBar1.setText("Mensagem: ");
    			   pt2Poligono = false;
    			   pt1Poligono = true;
        	   }
       	   }*/
             
        }
        
        @SuppressWarnings("deprecation")
        public void rpd()
        {
        	pnlDesenho.resize(getWidth()-1, getHeight()+1);
    		pnlDesenho.resize(getWidth()+1, getHeight()-1);
        }
        
        public void mouseReleased (MouseEvent e)
        {  
        	if(pCima)
        	{        		
        		if(pos+2<figuras.size())
        		{
        			aux = figuras.get(pos+2);
        			figuras.set(pos+2, figuras.get(pos+1));
        			figuras.set(pos+1,aux);
        			repaint();
        			rpd();
        		}
        	}
        	else if(pBaixo)
        	{
        		if(pos>=0)
        		{
        			aux = figuras.get(pos);
        			figuras.set(pos, figuras.get(pos+1));
        			figuras.set(pos+1,aux);
        			repaint();
        			rpd();
        		}
        	}
        	
        	if(arrastar)
        	{
        		arrastar = false;
        		selecionar = true;
        	}
        	else if(esperaFimReta)
        	{
        		confirmSaida = false;
        		esperaPonto 	   = false;
        		esperaInicioReta   = true;
        		esperaFimReta      = false;
        		esperaCentro	   = false;
        		esperaRaio		   = false;
        		esperaCentroElipse = false;
        		esperaRaioElipse   = false;
        		esperaInicioQuadrado = false;
        		esperaFimQuadrado    = false;
        		iniRetangulo         = false;
        		fimRetangulo         = false;
        		p1Texto              = false;
        		pt1Poligono			 = false;
        		pt2Poligono          = false;
        		fimPoligono          = false;
        		figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corBorda, corInterior));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
        	}
        	else if(esperaRaio)
        	{
        		confirmSaida = false;
        		int r =  (int)(Math.sqrt((Math.pow(e.getX()-p1.getX(),2)) + (Math.pow(e.getY()-p1.getY(),2))));
       			figuras.add (new Circulo(p1.getX(), p1.getY(),r, corBorda, corInterior));
       			figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
       			esperaPonto 	   = false;
        		esperaInicioReta   = false;
        		esperaFimReta      = false;
        		esperaCentro	   = true;
        		esperaRaio		   = false;
        		esperaCentroElipse = false;
        		esperaRaioElipse   = false;
        		esperaInicioQuadrado = false;
        		esperaFimQuadrado    = false;
        		iniRetangulo         = false;
        		fimRetangulo         = false;
        		p1Texto              = false;
        		pt1Poligono			 = false;
        		pt2Poligono          = false;
        		fimPoligono          = false;
        	}
        	else if(esperaRaioElipse)
        	{
        		confirmSaida = false;
        		int r2 = (int)(Math.sqrt((Math.pow(p1.getX()-p1.getX(),2)) + (Math.pow(e.getY()-p1.getY(),2))));
       			int r1 = (int)(Math.sqrt((Math.pow(e.getX()-p1.getX(),2)) + (Math.pow(p1.getY()-p1.getY(),2))));
       			figuras.add (new Elipse(p1.getX(), p1.getY(), r1, r2, corBorda, corInterior));
       			figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
       			esperaPonto 	   = false;
        		esperaInicioReta   = false;
        		esperaFimReta      = false;
        		esperaCentro	   = false;
        		esperaRaio		   = false;
        		esperaCentroElipse = true;
        		esperaRaioElipse   = false;
        		esperaInicioQuadrado = false;
        		esperaFimQuadrado    = false;
        		iniRetangulo         = false;
        		fimRetangulo         = false;
        		p1Texto              = false;
        		pt1Poligono			 = false;
        		pt2Poligono          = false;
        		fimPoligono          = false;
        	}
        	else if(esperaFimQuadrado)
        	{
        		confirmSaida = false;
        		int lado;
				if(p1.getX() >= e.getX()){
					lado = (int)Math.sqrt(Math.pow((p1.getX()-e.getX()),2));
					if(e.getX() <= p1.getX() && e.getY() >= p1.getY())
						figuras.add(new Quadrado(e.getX(), p1.getY(), lado, lado, corBorda, corInterior));
					else
						figuras.add(new Quadrado(e.getX(), e.getY(), lado, lado, corBorda, corInterior));
				}
				else{
					lado = (int)Math.sqrt(Math.pow((e.getX()-p1.getX()),2));
					if(e.getX() >= p1.getX() && e.getY() <= p1.getY())
						figuras.add(new Quadrado(p1.getX(), e.getY(), lado, lado, corBorda, corInterior));
					else
						figuras.add(new Quadrado(p1.getX(), p1.getY(), lado, lado, corBorda, corInterior));
				}
				figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
				esperaPonto 	   = false;
        		esperaInicioReta   = false;
        		esperaFimReta      = false;
        		esperaCentro	   = false;
        		esperaRaio		   = false;
        		esperaCentroElipse = false;
        		esperaRaioElipse   = false;
        		esperaInicioQuadrado = true;
        		esperaFimQuadrado    = false;
        		iniRetangulo         = false;
        		fimRetangulo         = false;
        		p1Texto              = false;
        		pt1Poligono			 = false;
        		pt2Poligono          = false;
        		fimPoligono          = false;
        	}
        	else if(fimRetangulo)
        	{
        		confirmSaida = false;
        		int base, altura;
				//1º e 4º quadrante:
				if(e.getX() >= p1.getX()){
					base = (int)Math.sqrt(Math.pow((e.getX()-p1.getX()),2));
					if(e.getY() <= p1.getY()){
						altura = (int)Math.sqrt(Math.pow((e.getY()-p1.getY()),2));
						figuras.add(new Retangulo(p1.getX(), e.getY(), base, altura, corBorda, corInterior));						
					}else{
						altura = (int)Math.sqrt(Math.pow((p1.getY()-e.getY()),2));
						figuras.add(new Retangulo(p1.getX(), p1.getY(), base, altura, corBorda, corInterior));
					}
				}//2º e 3º quadrante:
				else{
					base = (int)Math.sqrt(Math.pow((p1.getX()-e.getX()),2));
					if(e.getY() >= p1.getY()){
						altura = (int)Math.sqrt(Math.pow((e.getY()-p1.getY()),2));
						figuras.add(new Retangulo(e.getX(), p1.getY(), base, altura, corBorda, corInterior));						
					}else{
						altura = (int)Math.sqrt(Math.pow((p1.getY()-e.getY()),2));
						figuras.add(new Retangulo(e.getX(), e.getY(), base, altura, corBorda, corInterior));
					}
				}
				figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());				
        		esperaPonto 	   = false;
        		esperaInicioReta   = false;
        		esperaFimReta      = false;
        		esperaCentro	   = false;
        		esperaRaio		   = false;
        		esperaCentroElipse = false;
        		esperaRaioElipse   = false;
        		esperaInicioQuadrado = false;
        		esperaFimQuadrado    = false;
        		iniRetangulo         = true;
        		fimRetangulo         = false;
        		p1Texto              = false;
        		pt1Poligono			 = false;
        		pt2Poligono          = false;
        		fimPoligono          = false;        		
        	}
        	else if(pt2Poligono)
        	{	
        		Poligonos p;
        		confirmSaida = false;
        		coordX[qntdLados] = e.getX();
        		coordY[qntdLados] = e.getY();
        		p = new Poligonos(coordX, coordY, qntdLados, corBorda, corInterior);
        		figuras.set(figuras.size()-1, p);
        		qntdLados++;
        		figuras.get(figuras.size()-1).torneSeVisivel(getGraphics());
        		pt1Poligono = false;
        		pt2Poligono = true;
        		fimPoligono = false;
        	}
        	else if(fimPoligono)
        	{
        		confirmSaida = false;
        		figuras.get(figuras.size()-1).torneSeVisivel(getGraphics());
        		qntdLados = 0;
        		esperaPonto 	   = false;
        		esperaInicioReta   = false;
        		esperaFimReta      = false;
        		esperaCentro	   = false;
        		esperaRaio		   = false;
        		esperaCentroElipse = false;
        		esperaRaioElipse   = false;
        		esperaInicioQuadrado = false;
        		esperaFimQuadrado    = false;
        		iniRetangulo         = false;
        		fimRetangulo         = false;
        		p1Texto              = false;
        		pt1Poligono			 = true;
        		pt2Poligono          = false;
        		fimPoligono          = false;       
        	}
        }
        
        public void mouseClicked (MouseEvent e)
        {
        	pnlDesenho.setFocusable(true);
            pnlDesenho.requestFocusInWindow();
        }
        
        public void mouseEntered (MouseEvent e)
        {}

        public void mouseExited (MouseEvent e)
        {}
               
		
		public void mouseDragged(MouseEvent e)
        {
			pnlDesenho.setFocusable(true);
            pnlDesenho.requestFocusInWindow();
        	if(arrastar)
        	{
        		figuras.get(pos+1).mover(e.getX(), e.getY());
				figuras.get(pos+1).torneSeVisivel(pnlDesenho.getGraphics());
        		repaint();
        		rpd();
        	}
        	else if(esperaFimReta){
        		Linha l = new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corBorda, corInterior);
        		l.torneSeVisivel(pnlDesenho.getGraphics());
        		repaint();
        		rpd();        		
        		esperaFimReta = true;
        	}
        	else if(esperaRaio)
        	{        		
        		int r =  (int)(Math.sqrt((Math.pow(e.getX()-p1.getX(),2)) + (Math.pow(e.getY()-p1.getY(),2))));
        		Circulo c = new Circulo(p1.getX(), p1.getY(),r, corBorda, corInterior);
        		c.torneSeVisivel(pnlDesenho.getGraphics());
        		repaint();
        		rpd();
        		esperaRaio = true;
        	}
        	else if(esperaRaioElipse)
        	{
        		int r2 = (int)(Math.sqrt((Math.pow(p1.getX()-p1.getX(),2)) + (Math.pow(e.getY()-p1.getY(),2))));
       			int r1 = (int)(Math.sqrt((Math.pow(e.getX()-p1.getX(),2)) + (Math.pow(p1.getY()-p1.getY(),2))));
       			Elipse el = new Elipse(p1.getX(), p1.getY(), r1, r2, corBorda, corInterior);
       			el.torneSeVisivel(pnlDesenho.getGraphics());
       			repaint();
       			rpd();
       			esperaRaioElipse = true;
        	}
        	else if(esperaFimQuadrado)
        	{
        		Quadrado q;
        		int lado;
				if(p1.getX() >= e.getX()){
					lado = (int)Math.sqrt(Math.pow((p1.getX()-e.getX()),2));
					if(e.getX() <= p1.getX() && e.getY() >= p1.getY())
						q = new Quadrado(e.getX(), p1.getY(), lado, lado, corBorda, corInterior);
					else
						q = new Quadrado(e.getX(), e.getY(), lado, lado, corBorda, corInterior);
				}
				else{
					lado = (int)Math.sqrt(Math.pow((e.getX()-p1.getX()),2));
					if(e.getX() >= p1.getX() && e.getY() <= p1.getY())
						q = new Quadrado(p1.getX(), e.getY(), lado, lado, corBorda, corInterior);
					else
						q = new Quadrado(p1.getX(), p1.getY(), lado, lado, corBorda, corInterior);
				}
				q.torneSeVisivel(pnlDesenho.getGraphics());
				repaint();
				rpd();
				esperaFimQuadrado = true;
        	}
        	else if(fimRetangulo)
        	{
        		Retangulo r;
        		int base, altura;
				//1º e 4º quadrante:
				if(e.getX() >= p1.getX()){
					base = (int)Math.sqrt(Math.pow((e.getX()-p1.getX()),2));
					if(e.getY() <= p1.getY()){
						altura = (int)Math.sqrt(Math.pow((e.getY()-p1.getY()),2));
						r = new Retangulo(p1.getX(), e.getY(), base, altura, corBorda, corInterior);						
					}else{
						altura = (int)Math.sqrt(Math.pow((p1.getY()-e.getY()),2));
						r = new Retangulo(p1.getX(), p1.getY(), base, altura, corBorda, corInterior);
					}
				}//2º e 3º quadrante:
				else{
					base = (int)Math.sqrt(Math.pow((p1.getX()-e.getX()),2));
					if(e.getY() >= p1.getY()){
						altura = (int)Math.sqrt(Math.pow((e.getY()-p1.getY()),2));
						r = new Retangulo(e.getX(), p1.getY(), base, altura, corBorda, corInterior);						
					}else{
						altura = (int)Math.sqrt(Math.pow((p1.getY()-e.getY()),2));
						r = new Retangulo(e.getX(), e.getY(), base, altura, corBorda, corInterior);
					}
				}
				r.torneSeVisivel(pnlDesenho.getGraphics());
				repaint();
				rpd();
				fimRetangulo = true;
        	}
        	else if(pt2Poligono)
        	{
        		   Poligonos p;
         		   repaint();
    			   rpd();
        		   coordX[qntdLados] = e.getX();
        		   coordY[qntdLados] = e.getY();
             	   p = new Poligonos(coordX, coordY, qntdLados, corBorda, corInterior);
             	   figuras.set(figuras.size()-1, p);    			   
    			   pt2Poligono = true;
    			   pt1Poligono = false;
    			   fimPoligono = false;         		   
        	}
        }
        
        public void mouseMoved(MouseEvent e)
        {
            statusBar2.setText("Coordenada: "+e.getX()+","+e.getY()+"px");
        }
		
        @Override
        public void keyTyped(KeyEvent ke) {
               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        	
        @Override
        public void keyPressed(KeyEvent ke) {
             if(selecionado == true && ke.getKeyCode() == VK_DELETE){
                   figuras.remove(pos+1);
                   selecionado = false;
                   repaint();
                   rpd();
             }
        }
        	
        @Override
        public void keyReleased(KeyEvent ke) {
              //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }   
    
    private class trazerParaCima implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)
    	{
    		selecionar = true;
    		pCima = true;
    		pBaixo = false;
    		statusBar1.setText("Mensagem: trazer para cima");
    	}
    }
    
    private class trazerParaBaixo implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)
    	{
    		selecionar = true;
    		pBaixo = true;
    		pCima = false;
    		statusBar1.setText("Mensagem: trazer para baixo");
    	}
    }
    
    private class DesenhoDePonto implements ActionListener
    {
          public void actionPerformed (ActionEvent e)    
          {
              esperaPonto      = true;
              esperaInicioReta = false;
              esperaFimReta    = false;
      		  esperaCentro	   = false;
      		  esperaRaio	   = false;
      		  confirmSaida = false;
      		  arrastar = false;
      		  selecionar = false;
              statusBar1.setText("Mensagem: clique o local do ponto desejado");
          }
    }

    private class DesenhoDeReta implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            esperaPonto      = false;
            esperaInicioReta = true;
            esperaFimReta    = false;
    		esperaCentro	 = false;
    		esperaRaio		 = false;
    		confirmSaida = false;
    		  arrastar = false;
      		  selecionar = false;
            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }
    
    private class DesenhoDeCirculo implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)
    	{
    		esperaPonto 	 = false;
    		esperaInicioReta = false;
    		esperaFimReta    = false;
    		esperaCentro	 = true;
    		esperaRaio		 = false;
    		confirmSaida = false;
    		  arrastar = false;
      		  selecionar = false;
    		statusBar1.setText("Mensagem: clique o ponto inicial do circulo");
    	}
    }
    
    private class DesenhoDaElipse implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)
    	{
    		esperaPonto 	   = false;
    		esperaInicioReta   = false;
    		esperaFimReta      = false;
    		esperaCentro	   = false;
    		esperaRaio		   = false;
    		esperaCentroElipse = true;
    		esperaRaioElipse   = false;
    		confirmSaida = false;
    		  arrastar = false;
      		  selecionar = false;
    		statusBar1.setText("Mensagem: clique o ponto inicial da elipse");
    	}
    }
    
    private class DesenhoDeQuadrado implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)
    	{
    		esperaPonto 	   = false;
    		esperaInicioReta   = false;
    		esperaFimReta      = false;
    		esperaCentro	   = false;
    		esperaRaio		   = false;
    		esperaCentroElipse = false;
    		esperaRaioElipse   = false;
    		esperaInicioQuadrado = true;
    		esperaFimQuadrado    = false;
    		confirmSaida = false;
    		  arrastar = false;
      		  selecionar = false;
    		statusBar1.setText("Mensagem: Clique o ponto inicial do Quadrado");
    	}
    }
    
    private class DesenhoDeRetangulo implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)
    	{
    		esperaPonto 	   = false;
    		esperaInicioReta   = false;
    		esperaFimReta      = false;
    		esperaCentro	   = false;
    		esperaRaio		   = false;
    		esperaCentroElipse = false;
    		esperaRaioElipse   = false;
    		esperaInicioQuadrado = false;
    		esperaFimQuadrado    = false;
    		iniRetangulo         = true;
    		fimRetangulo         = false;
    		confirmSaida = false;
    		  arrastar = false;
      		  selecionar = false;
    		statusBar1.setText("Mensagem: Clique o ponto inicial do Retangulo");
    	}
    }
    
    private class DesenhoDePoligono implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)
    	{
    		esperaPonto 	   = false;
    		esperaInicioReta   = false;
    		esperaFimReta      = false;
    		esperaCentro	   = false;
    		esperaRaio		   = false;
    		esperaCentroElipse = false;
    		esperaRaioElipse   = false;
    		esperaInicioQuadrado = false;
    		esperaFimQuadrado    = false;
    		iniRetangulo         = false;
    		fimRetangulo         = false;
    		p1Texto              = false;
    		pt1Poligono			 = true;
    		pt2Poligono          = false;
    		fimPoligono          = false;
    		confirmSaida = false;
    		qntdLados = 0;
    		  arrastar = false;
      		  selecionar = false;
    		statusBar1.setText("Mensagem: Clique um ponto duas Vezes que faça parte do seu poligono");
    	}
    }
    
    private class DesenhoDeTexto implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)
    	{
    		esperaPonto 	   = false;
    		esperaInicioReta   = false;
    		esperaFimReta      = false;
    		esperaCentro	   = false;
    		esperaRaio		   = false;
    		esperaCentroElipse = false;
    		esperaRaioElipse   = false;
    		esperaInicioQuadrado = false;
    		esperaFimQuadrado    = false;
    		iniRetangulo         = false;
    		fimRetangulo         = false;
    		p1Texto              = true;
    		confirmSaida = false;
    		  arrastar = false;
      		  selecionar = false;
    		statusBar1.setText("Mensagem: Clique o ponto onde deseja inserir o texto");
    	}
    }
    
    private class CaixaDeFontes implements ActionListener
    {    				
		public void actionPerformed (ActionEvent e) 
    	{   
    		int retValue = fontChooser.showDialog(null);
    		
    		if(retValue == JFontChooser.OK_OPTION)
    		{
    			repaint();
    			selecionada = fontChooser.getSelectedFont();//pega fonte
    		}    		
    	}
    }
    
    private class CaixaDeCoresDaBorda implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)
    	{
    		statusBar1.setText("Mensagem: Escolha a cor da Borda");
    		corBorda = JColorChooser.showDialog(null, "Selecione a Cor da Borda", corAtual);
    		corAtual = corBorda;
    		
    		statusBar1.setText("Mensagem:");
    	}
    }

    private class CaixaDeCoresDoInterior implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)
    	{
    		statusBar1.setText("Mensagem: Escolha a cor do Interior");
    		corInterior = JColorChooser.showDialog(null, "Selecione a Cor do Interior", corAtual);
    		corAtual = corInterior;
    		
    		statusBar1.setText("Mensagem:");
    	}
    }
    
    private class SelecionarDesenho implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)
    	{
    		statusBar1.setText("Mensagem: Selecione e Arraste o Deseho");
    		esperaPonto 	   = false;
    		esperaInicioReta   = false;
    		esperaFimReta      = false;
    		esperaCentro	   = false;
    		esperaRaio		   = false;
    		esperaCentroElipse = false;
    		esperaRaioElipse   = false;
    		esperaInicioQuadrado = false;
    		esperaFimQuadrado    = false;
    		iniRetangulo         = false;
    		fimRetangulo         = false;
    		p1Texto              = false;
    		pt1Poligono			 = false;
    		pt2Poligono          = false;
    		fimPoligono          = false;
    		confirmSaida         = false;
    		selecionar           = true;
    		pCima 				 = false;
    		pBaixo 				 = false;
    	}
    }
    
    private class SalvarImagem implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)
    	{
    		statusBar1.setText("Mensagem: Escolha o nome do arquivo");
    		
    		JFileChooser chooserArquivo   = new JFileChooser();
    		
    		int escolha = chooserArquivo.showOpenDialog(getParent()); 
    		String arquivo = chooserArquivo.getSelectedFile().getAbsolutePath();
    		if(!arquivo.endsWith(".pit"))
    			arquivo+=".pit";
    		
    		File file = new File(arquivo);
    		PrintWriter printWriter = null;
    		try{
    			printWriter = new PrintWriter(arquivo);
    		}catch(Exception erro)
    		{
    			statusBar1.setText("Erro ao abrir o arquivo para salvar");
    		}
    		printWriter.println(figuras.toString());
    		printWriter.close();
    		statusBar1.setText("Mensagem: ");
    		confirmSaida = true;
    	}
    }
    
    private class AbrirImagem implements ActionListener
    {
    	@SuppressWarnings("deprecation")
		public void actionPerformed (ActionEvent e)
    	{
    		JFileChooser fileChooser = new JFileChooser();
    		int escolha = fileChooser.showOpenDialog(getParent());
    		repaint();
    		String arquivo = fileChooser.getSelectedFile().getAbsolutePath();
    		if(!arquivo.endsWith(".pit"))
    		{
    			arquivo += ".pit";
    		}
    		if(arquivo.endsWith(".pit"))
    		{
    			try{
    				FileReader fileReader = new FileReader(arquivo);
    				BufferedReader lerArq = new BufferedReader(fileReader);
    				
    				String linha = lerArq.readLine();
    				String bloco[] = linha.split(",");
    				figuras.clear();
    				pnlDesenho.repaint();
    				pnlDesenho.resize(getWidth()-1, getHeight()+1);
    	    		pnlDesenho.resize(getWidth()+1, getHeight()-1);
    				for(int i=0; i<bloco.length; i++)
    				{
    					if(bloco[i].startsWith("[") || bloco[i].startsWith(" ")){
    						bloco[i] = bloco[i].substring(1, bloco[i].length());
    					}
    					
    					if(bloco[i].endsWith("]") || bloco[i].endsWith(" ")){
    						bloco[i] = bloco[i].substring(0, bloco[i].length()-1);
    					}
    					
    					if     (bloco[i].startsWith("c:"))
    					{
    						figuras.add (new Circulo(bloco[i]));    			   			
    					}
    					else if(bloco[i].startsWith("e:"))
    					{
    						figuras.add (new Elipse(bloco[i]));
    					}
    					else if(bloco[i].startsWith("l:"))
    					{
    						figuras.add (new Linha(bloco[i]));
    					}
    					else if(bloco[i].startsWith("k:"))
    					{
    						figuras.add (new Poligonos(bloco[i]));
    					}
    					else if(bloco[i].startsWith("p:"))
    					{
    						figuras.add (new Ponto(bloco[i]));    						
    					}
    					else if(bloco[i].startsWith("q:"))
    					{
    						figuras.add (new Quadrado(bloco[i]));
    					}
    					else if(bloco[i].startsWith("r:"))
    					{
    						figuras.add (new Retangulo(bloco[i]));
    					}
    					else if(bloco[i].startsWith("t:"))
    					{
    						figuras.add (new Texto(bloco[i]));
    					}
    					figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
    				}
    				
    			}catch(IOException erro){
    				erro.getMessage();
    			}
    		}
    	}
    }
    
    private class FechamentoDeJanela extends WindowAdapter
    {
        public void windowClosing (WindowEvent e)
        {
        	int dialogButton = JOptionPane.NO_OPTION;
        	if(confirmSaida==false)
        	{        		
        		dialogButton = JOptionPane.showConfirmDialog (null, "Gostaria de salvar as ultimas alterações?","Aviso",dialogButton);
	            if(dialogButton == JOptionPane.YES_OPTION){
	            	statusBar1.setText("Mensagem: Escolha o nome do arquivo");
	        		
	        		JFileChooser chooserArquivo   = new JFileChooser();
	        		
	        		int escolha = chooserArquivo.showOpenDialog(getParent()); 
	        		String arquivo = chooserArquivo.getSelectedFile().getAbsolutePath();
	        		if(!arquivo.endsWith(".pit"))
	        			arquivo+=".pit";
	        		
	        		File file = new File(arquivo);
	        		PrintWriter printWriter = null;
	        		try{
	        			printWriter = new PrintWriter(arquivo);
	        		}catch(Exception erro)
	        		{
	        			statusBar1.setText("Erro ao abrir o arquivo para salvar");
	        		}
	        		printWriter.println(figuras.toString());
	        		printWriter.close();
	        		statusBar1.setText("Mensagem: ");
	        		confirmSaida = true;
	            }
	            if(dialogButton == JOptionPane.NO_OPTION)
	            	System.exit(0);
        	}
        }
    }

  //public Object  clone    ();
  //public         Janela   (Janela modelo);
  //public boolean equals   (Object obj);
  //public int     hashCode ();
  //public String  toString ();
}
