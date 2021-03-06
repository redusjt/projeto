package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.Decypher;
import Controller.Login;

public class LoginGUI extends JFrame implements ActionListener{

   private JButton       entrar, sair, ing, por, esp;
   private JLabel        oi, lg, sh;
   private JTextField     login;
   private JPasswordField  senha; 
   private ImageIcon iPort, iIng, iEsp;
   private ResourceBundle bn = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));
   public String[] usuarioLogado;
   Color corGrid;  
   public String msgErro = bn.getString("l.msgerro"), msgErro1 = bn.getString("l.msgerro1"); 
   
   

   public LoginGUI()
   {  
				
      Container principal = getContentPane();
      principal.setLayout (new BorderLayout());
		    
            //Grid Esquerda superior
      entrar        = new JButton (bn.getString("l.entrar"));
      sair          = new JButton (bn.getString("l.cancelar"));
      lg            = new JLabel(bn.getString("l.login"), JLabel.CENTER);
      lg.setForeground(Color.WHITE);
      sh            = new JLabel(bn.getString("l.senha"), JLabel.CENTER);
      sh.setForeground(Color.WHITE);
      senha         = new JPasswordField(15);
      login         = new JTextField(15);
      
      iPort         = new ImageIcon( "src\\resources\\brasil.jpg" ); 
      iEsp          = new ImageIcon( "src\\resources\\esp.jpg" );    
      iIng          = new ImageIcon( "src\\resources\\usa.jpg" );       
      ing           = new JButton (iIng);
      
      ing.setPreferredSize(new Dimension(30, 20));      
      ing.addActionListener(this);      
      por           = new JButton (iPort);
      por.setPreferredSize(new Dimension(30, 20));
      por.addActionListener(this);
      esp           = new JButton (iEsp);
      esp.setPreferredSize(new Dimension(30, 20));
      esp.addActionListener(this);


      corGrid  = new Color(32,32,32);
   			
            //Adicionando A��es
      sair.addActionListener(this);
      entrar.addActionListener(this);
   			

   			

     
         //Grid direita superior
      JPanel grid =  new JPanel();
      grid.setBackground(corGrid);
      GridBagConstraints cons = new GridBagConstraints();				
      grid.setLayout(new GridBagLayout());
      cons.fill = GridBagConstraints.NONE; 
      cons.ipadx = 5;  
      cons.ipady = 5;
      cons.gridy = 2;
      cons.insets = new Insets(10,3,3,3);
      grid.add(lg,cons);
      cons.gridy = 2;
      grid.add(login,cons);
      cons.gridy = 4;
      grid.add(sh,cons);      
      cons.gridy = 4;
      grid.add(senha,cons);
      cons.gridy = 6;
      cons.insets = new Insets(10,35,3,0);
      grid.add(entrar,cons);
      cons.ipadx = 15;  
      cons.ipady = 5;
      cons.gridy      = 6;
      cons.gridheight = 2;
      cons.gridwidth  = 2;
      cons.insets = new Insets(10,3,3,3);
      grid.add(sair,cons);
      JPanel norte = new JPanel(new FlowLayout());
      norte.setBackground(corGrid);
      norte.add(ing);
      norte.add(por);
      norte.add(esp);
      
      
      principal.add(norte, BorderLayout.NORTH);
      principal.add(grid, BorderLayout.CENTER);
      
   			
      setResizable(false);
      setSize(300, 190);
      setLocation(550, 300);
      setVisible(true);
            

		            
   }
   
   public void atualizaIdioma(ResourceBundle n)
   {
      this.bn = n;
      
       entrar.setText(bn.getString("l.entrar"));
       sair.setText(bn.getString("l.cancelar"));
       lg.setText(bn.getString("l.login"));
       sh.setText(bn.getString("l.senha"));
       this.msgErro = bn.getString("l.msgerro");
       this.msgErro1 = bn.getString("l.msgerro1");
       
      
   }


   public void actionPerformed(ActionEvent event)
   
   {    
		                 
               
      if(event.getSource() == entrar ){
         try
         {
         Decypher d = new Decypher();
         Login l = new Login();
         //long cpfLong = Long.parseLong(login.getText());
         if(l.binaria(d.getUsuario(), Long.parseLong(login.getText())))
         {
            if(senha.getText().equals(d.getSenhaAt(l.getPos())))
            {
               HomeGUI home = new HomeGUI();
               home.atualizaIdioma(bn);
               
               usuarioLogado = new String[2]; //Controla as informa��es de CPF e tipo de usu�rio logado para posteriores consultas e restri��es
               usuarioLogado[0] = login.getText(); // CPF
               usuarioLogado[1] = String.valueOf( d.getTipoAt( l.getPos() ) ); // Tipo de Usu�rio
               home.usuarioLogado = this.usuarioLogado;

                                             
               if( d.getTipoAt( l.getPos() ) == 0 )// se usu�rio logado for do tipo funcion�rio, desabilita fun��es das quais n�o tem permiss�o
               {
                  home.addEmp.setEnabled(false);
                  home.altEmp.setEnabled(false);
                  home.addFunc.setEnabled(false);
                  home.altFunc.setEnabled(false);
               }
               dispose();
            }
            else JOptionPane.showMessageDialog(null, msgErro);//("Usu�rio e/ou Senha inv�lido(s)");
         }
         else JOptionPane.showMessageDialog(null, msgErro);
         }catch(Exception e)
         {
         JOptionPane.showMessageDialog(null,msgErro1);
         }
                           
                  
      }
      
      if(event.getSource() == sair ){
         System.exit(0);
      }
      
      if(event.getSource()== ing){
         bn = ResourceBundle.getBundle("idioma", new Locale("en", "US"));
         atualizaIdioma(bn);
      }
      
      if(event.getSource()== esp){
         bn = ResourceBundle.getBundle("idioma", new Locale("es", "ES"));
         atualizaIdioma(bn); 
      }
   
      if(event.getSource()== por){
         bn = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));
         atualizaIdioma(bn); 
      }
   				
   }
   public static void main(String[] args)
   {
      LoginGUI l = new LoginGUI();
   }

}