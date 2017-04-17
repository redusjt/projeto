package viewController;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;
import java.util.Locale;

public class HomeVC extends JFrame implements ActionListener{

   public  JButton       addEmp, altEmp, addFunc, altFunc;
   private JButton       gerenciarAcs, logOff, arCondicionado, ing, por, esp;
   private JLabel        emp, func, gerencia, fund;
   private ImageIcon iPort, iIng, iEsp, fundo, botao;      
   private Image background;
   private ResourceBundle bn = ResourceBundle.getBundle("properties/idioma", new Locale("pt", "BR"));
   public String[] usuarioLogado = {"0000", "0"};
   Color corGrid, corBotao;
   ArCondicionadoVC arCond; 
   RegistroDeAcessoVC ra; 
   EmpresaVC empresa = new EmpresaVC(); 
   UsuarioVC usuario;                                                   

   public HomeVC()
   {  
      super("Home");
   	     
      Container principal = getContentPane();
       
      corGrid  = new Color(32,32,32);
      corBotao = new Color(160,160,160);
       
      
      principal.setLayout (new GridLayout(2,2));                             
            		    
            //Grid Esquerda superior
      addEmp        = new JButton (bn.getString("e.emp.add")); 
      addEmp.setFont( new Font("Gisha", Font.BOLD, 12));     
      addEmp.setPreferredSize(new Dimension(60, 25));      
      altEmp        = new JButton (bn.getString("e.emp.cons"));
      altEmp.setFont( new Font("Gisha", Font.BOLD, 12));
      altEmp.setPreferredSize(new Dimension(60, 25));      
      emp           = new JLabel(bn.getString("e.emp"), JLabel.CENTER);
      emp.setFont( new Font("Gisha", Font.BOLD, 12));
      emp.setForeground(Color.WHITE);
   
           //Grid Esquerda Inferior
      gerenciarAcs       = new JButton (bn.getString("e.gerenciar.acesso"));      
      gerenciarAcs.setPreferredSize(new Dimension(60, 25));
      gerenciarAcs.setFont( new Font("Gisha", Font.BOLD, 12));
      arCondicionado     = new JButton (bn.getString("e.ar.condic"));
      arCondicionado.setFont( new Font("Gisha", Font.BOLD, 12));
      arCondicionado.setPreferredSize(new Dimension(60, 25));      
      gerencia           = new JLabel(bn.getString("e.gerenciamento"), JLabel.CENTER);
      gerencia.setFont( new Font("Gisha", Font.BOLD, 12));
      gerencia.setForeground(Color.WHITE);
            
           //Grid Direita Superior
      addFunc        = new JButton (bn.getString("e.func.add"));
      addFunc.setFont( new Font("Gisha", Font.BOLD, 12));
      addFunc.setPreferredSize(new Dimension(60, 25));
      altFunc        = new JButton (bn.getString("e.func.cons"));
      altFunc.setFont( new Font("Gisha", Font.BOLD, 12));
      altFunc.setPreferredSize(new Dimension(60, 25));
      func           = new JLabel(bn.getString("e.func"), JLabel.CENTER);
      func.setFont( new Font("Gisha", Font.BOLD, 12));
      func.setForeground(Color.WHITE);
      
           //Grid Direita Inferior
      iPort         = new ImageIcon( "img/brasil.jpg" ); 
      iEsp          = new ImageIcon( "img/esp.jpg" );    
      iIng          = new ImageIcon( "img/usa.jpg" ); 
      logOff        = new JButton (bn.getString("e.sair"));  
      logOff.setFont( new Font("Gisha", Font.BOLD, 12));          
      logOff.setPreferredSize(new Dimension(68, 20));
      ing           = new JButton (iIng);
      ing.setPreferredSize(new Dimension(30, 20));
      por           = new JButton (iPort);
      por.setPreferredSize(new Dimension(30, 20));
      esp           = new JButton (iEsp);
      esp.setPreferredSize(new Dimension(30, 20));
   
   
   			
            //Adicionando Ações
      addEmp.addActionListener (this);
      altEmp.addActionListener (this);
      addFunc.addActionListener (this);
      altFunc.addActionListener (this);
      gerenciarAcs.addActionListener (this);
      arCondicionado.addActionListener(this);
      logOff.addActionListener (this);
      ing.addActionListener(this);
      por.addActionListener(this);
      esp.addActionListener(this);
   			
   
   			
            // Grid esquerda superior
      JPanel gridE1 =  new JPanel();
      GridBagConstraints cons = new GridBagConstraints();         
      gridE1.setLayout(new GridBagLayout());
      gridE1.setBackground(corGrid);
      cons.insets = new Insets(0,3,20,3);  
      cons.ipadx = 100;  
      cons.ipady = 10;
      cons.gridy = 0;  
      cons.gridx = 0;    
      gridE1.add(emp,cons);
      cons.insets = new Insets(0,3,3,3);
      cons.gridy = 2;
      gridE1.add(addEmp,cons);
      cons.ipadx = 100;           
      cons.gridy = 4;
      gridE1.add(altEmp,cons);
      
         // Grid esquerda inferior
      JPanel gridE2 =  new JPanel();
      GridBagConstraints cons1 = new GridBagConstraints();				
      gridE2.setLayout(new GridBagLayout());
      gridE2.setBackground(corGrid);
      cons1.insets = new Insets(0,3,20,3); 
      cons1.ipadx = 100;  
      cons1.ipady = 10;
      cons1.gridy = 0;  
      cons1.gridx = 0;      
      gridE2.add(gerencia,cons1);
      cons1.insets = new Insets(0,3,3,3);
      cons1.gridy = 2;
      gridE2.add(gerenciarAcs,cons1);
      cons1.ipadx = 100;
      cons1.gridy = 4;           
      gridE2.add(arCondicionado,cons1);
   			
            //Grid direita superior
      JPanel gridD1 =  new JPanel();
      GridBagConstraints cons2 = new GridBagConstraints();				
      gridD1.setLayout(new GridBagLayout());
      gridD1.setBackground(corGrid);
      cons2.insets = new Insets(0,3,20,3); 
      cons2.ipadx = 100;  
      cons2.ipady = 10;
      cons2.gridy = 0;  
      cons2.gridx = 0; 
      gridD1.add(func,cons2);
      cons2.insets = new Insets(0,3,3,3);
      cons2.gridy = 2;
      gridD1.add(addFunc,cons2);
      cons2.ipadx = 100;
      cons2.gridy = 4;
      gridD1.add(altFunc,cons2);
     
         //Grid direita superior
      JPanel gridD2 =  new JPanel();
      GridBagConstraints cons3 = new GridBagConstraints();				
      gridD2.setLayout(new GridBagLayout());
      gridD2.setBackground(corGrid);
      cons3.insets = new Insets(100,3,3,3); 
      cons3.ipadx = 5;  
      cons3.ipady = 5;
      cons3.gridy = 0;  
      cons3.gridx = 0; 
      gridD2.add(ing,cons3);
      cons3.gridx = 2;
      gridD2.add(por,cons3);
      cons3.gridx = 4;
      gridD2.add(esp,cons3);      
      cons3.gridx = 6;
      gridD2.add(logOff,cons3);
   
   
      principal.add(gridE1);
      principal.add(gridD1);
      principal.add(gridE2);
      principal.add(gridD2); 
       		
      
      setResizable(false);
      setSize(430, 300);
      setLocation(450, 200);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      System.out.println("CPF: " + usuarioLogado[0] + "   Tipo: " + usuarioLogado[1] + "--HOME");                  	            
   }
   
   public void atualizaIdioma(ResourceBundle b)
   {
      this.bn = b;
      
      addEmp.setText(bn.getString("e.emp.add"));      
      altEmp.setText(bn.getString("e.emp.cons"));      
      emp.setText(bn.getString("e.emp"));
      
      gerenciarAcs.setText(bn.getString("e.gerenciar.acesso"));//"Gerenciar acesso"      
      arCondicionado.setText(bn.getString("e.ar.condic"));//"ar-condicionado"            
      gerencia.setText(bn.getString("e.gerenciamento"));//"Gerenciamento"
            
           
      addFunc.setText(bn.getString("e.func.add"));      
      altFunc.setText(bn.getString("e.func.cons"));      
      func.setText(bn.getString("e.func"));
      
      logOff.setText(bn.getString("e.sair"));
      
   }


   public void actionPerformed(ActionEvent event)
   
   {    
                    
               
      if(event.getSource() == addEmp ){
         EmpresaVC empresa1 = new EmpresaVC(); 
         empresa1.atualizaIdioma(bn);
         empresa1.cadastrar();
                  
      }
   				
      if(event.getSource() == altEmp ){   
         EmpresaVC empresa2 = new EmpresaVC(); 
         empresa2.atualizaIdioma(bn);
         empresa2.consultar();
      }
   				
      if(event.getSource() == addFunc ){ 
         if( usuarioLogado[1].equals("2") ) //testa se o tipo de usuario logado é um síndico
         {
            usuario = new UsuarioVC(0); //carrega o cadastrar no modo síndico
         }
         else
         {
            usuario = new UsuarioVC(1); //carrega o cadastrar no modo atendente
         }
            
         usuario.atualizaIdioma(bn);		
      }
      if(event.getSource() == altFunc ){
         usuario = new UsuarioVC(2);
         usuario.atualizaIdioma(bn);
      }
                 
   				
      if(event.getSource() == gerenciarAcs ){  
         ra = new RegistroDeAcessoVC();
         ra.atualizaIdioma(bn);
      }
              			
      if(event.getSource() == arCondicionado ){  
         arCond = new ArCondicionadoVC(bn);
         arCond.usuarioLogado = this.usuarioLogado;
         arCond.atualizaIdioma(bn);
         arCond.usuarioLogado = this.usuarioLogado;
         arCond.show();

      }
       
      if(event.getSource()== ing){ 
         bn = ResourceBundle.getBundle("properties/idioma", new Locale("en", "US"));
         atualizaIdioma(bn);
      }
      
      if(event.getSource()== esp){   
         bn = ResourceBundle.getBundle("properties/idioma", new Locale("es", "ES"));
         atualizaIdioma(bn); 
      }
   
      if(event.getSource()== por){  
         bn = ResourceBundle.getBundle("properties/idioma", new Locale("pt", "BR"));
         atualizaIdioma(bn); 
      }
      
      if(event.getSource() == logOff ){
         System.exit(0);
      }
   				
   }
   
   public static void main(String[] args)
   {
      HomeVC h = new HomeVC();
      h.usuarioLogado[0]=String.valueOf(1234);
      h.usuarioLogado[1]=String.valueOf(1);
      
   }

}