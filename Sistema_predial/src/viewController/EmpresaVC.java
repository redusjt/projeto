package viewController;

import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.util.ResourceBundle;
import java.util.Locale;
import java.awt.Color;

import service.EmpresaService;
import transferObject.EmpresaTO;

public class EmpresaVC extends JDialog implements ActionListener
{
   //Atributos
   private JPanel pPrincipal, pCentral, pBotoes, pCima;
   private JLabel lCnpj, lRZ, lConj, lHorFunc, lValorPadAC, lHorPadAC;
   private JTextField tCnpj, tRZ, tConj, tHorFunc, tValorPadAC, tHorPadAC;
   private JButton b1, b2, b3, b4, b5;
   private ResourceBundle bn = ResourceBundle.getBundle("properties/idioma", new Locale("pt", "BR"));
   Color corGrid;
   public String msgCadastro, msgNCadastro, msgJaCadastrada, msgErro, msgAlterar, msgExcluir;
     
   
   public EmpresaVC()
   {
      
      setModal(true);
      corGrid  = new Color(32,32,32);
      
      //Labels           
      lCnpj       = new JLabel("");
      lRZ         = new JLabel("");
      lConj       = new JLabel("");
      lHorFunc    = new JLabel("");
      lValorPadAC = new JLabel("");
      lHorPadAC   = new JLabel("");               
      
      //Buttons
      b1  = new JButton("");      
      b2  = new JButton(""); 
      b3  = new JButton("");
      b4  = new JButton("");   
      b5  = new JButton("");
      
      pPrincipal = new JPanel();
      
      msgCadastro = "";
      msgNCadastro = "";
      msgJaCadastrada = "";
      msgErro = "";
      msgAlterar = "";
      msgExcluir = "";
   }
   
   //Cria layout de Cadastrar, parâmetro recebe idioma
   public void cadastrar()
   {            
      pPrincipal.removeAll();
      validate();
      setTitle(bn.getString("super.ca"));
      //Panels
      pPrincipal.setLayout(new BorderLayout());   
      pPrincipal.setBackground(corGrid);    
      pCentral   = new JPanel(new GridLayout(6,2,2,2));
      pCentral.setBackground(corGrid);      
      pBotoes    = new JPanel(new FlowLayout());  
      pBotoes.setBackground(corGrid);     
                 
      //Labels           
      lCnpj.setText(bn.getString("cnpj"));
      lCnpj.setForeground(Color.WHITE);
      lRZ.setText(bn.getString("razao.social"));
      lRZ.setForeground(Color.WHITE);
      lConj.setText(bn.getString("conjunto"));
      lConj.setForeground(Color.WHITE);
      lHorFunc.setText(bn.getString("hor.func"));
      lHorFunc.setForeground(Color.WHITE);
      lValorPadAC.setText(bn.getString("valor.pad.temp.max"));
      lValorPadAC.setForeground(Color.WHITE);
      lHorPadAC.setText(bn.getString("hor.pad.func.ac")); 
      lHorPadAC.setForeground(Color.WHITE);
              
      lCnpj.setHorizontalAlignment(javax.swing.JLabel.CENTER);
      lRZ.setHorizontalAlignment(javax.swing.JLabel.CENTER); 
      lConj.setHorizontalAlignment(javax.swing.JLabel.CENTER); 
      lHorFunc.setHorizontalAlignment(javax.swing.JLabel.CENTER); 
      lValorPadAC.setHorizontalAlignment(javax.swing.JLabel.CENTER);
      lHorPadAC.setHorizontalAlignment(javax.swing.JLabel.CENTER);
      
      //TextFields
      tCnpj       = new JTextField("");      
      tRZ         = new JTextField("");
      tConj       = new JTextField("");
      tHorFunc    = new JTextField("");
      tValorPadAC = new JTextField("");
      tHorPadAC   = new JTextField("");          
      
      //Buttons
      b1.setText(bn.getString("b.cadastrar"));
      b1.addActionListener(this);
      b2.setText(bn.getString("b.cancelar"));
      b2.addActionListener(this);
      
      //Arrumando layout          
      pCentral.add(lCnpj);
      pCentral.add(tCnpj);
      pCentral.add(lRZ);
      pCentral.add(tRZ);
      pCentral.add(lConj);
      pCentral.add(tConj);
      pCentral.add(lHorFunc);
      pCentral.add(tHorFunc);
      pCentral.add(lValorPadAC);
      pCentral.add(tValorPadAC);
      pCentral.add(lHorPadAC);
      pCentral.add(tHorPadAC);          
      
      pBotoes.add(b1);      
      pBotoes.add(b2);               
      
      pPrincipal.add(pCentral, BorderLayout.CENTER);      
      pPrincipal.add(pBotoes, BorderLayout.SOUTH);
      
      //pPrincipal.add(lTitulo);     
      //pPrincipal.add(pCentral);      
      //pPrincipal.add(pBotoes);
      
      add(pPrincipal);                  
            
      setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
       
      setSize(410, 230);      
      setLocation(500,240);
      setVisible(true);  
                
   }   
   
   public void consultar()
   {
      pPrincipal.removeAll();
      validate();
      setTitle(bn.getString("super.co"));
      //Panels
      pPrincipal.setLayout(new BorderLayout()); 
      pPrincipal.setBackground(corGrid);      
      pCentral   = new JPanel(new GridLayout(5,2,2,2)); 
      pCentral.setBackground(corGrid);          
      pBotoes    = new JPanel(new FlowLayout());   
      pBotoes.setBackground(corGrid);
      pCima      = new JPanel(new FlowLayout());
      pCima.setBackground(corGrid); 
      
      //Labels
         
      lCnpj.setText(bn.getString("cnpj"));
      lCnpj.setForeground(Color.WHITE);
      lRZ.setText(bn.getString("razao.social"));
      lRZ.setForeground(Color.WHITE);
      lConj.setText(bn.getString("conjunto"));
      lConj.setForeground(Color.WHITE);
      lHorFunc.setText(bn.getString("hor.func"));
      lHorFunc.setForeground(Color.WHITE);
      lValorPadAC.setText(bn.getString("valor.pad.temp.max"));
      lValorPadAC.setForeground(Color.WHITE);
      lHorPadAC.setText(bn.getString("hor.pad.func.ac")); 
      lHorPadAC.setForeground(Color.WHITE);      
       
      lCnpj.setHorizontalAlignment(javax.swing.JLabel.CENTER);
      lRZ.setHorizontalAlignment(javax.swing.JLabel.CENTER); 
      lConj.setHorizontalAlignment(javax.swing.JLabel.CENTER); 
      lHorFunc.setHorizontalAlignment(javax.swing.JLabel.CENTER); 
      lValorPadAC.setHorizontalAlignment(javax.swing.JLabel.CENTER);
      lHorPadAC.setHorizontalAlignment(javax.swing.JLabel.CENTER);     
      
      //TextFields
      tCnpj       = new JTextField(15);      
      tRZ         = new JTextField("");      
      tConj       = new JTextField("");      
      tHorFunc    = new JTextField("");      
      tValorPadAC = new JTextField("");      
      tHorPadAC   = new JTextField("");
      
      
      //Buttons
      b3.setText(bn.getString("b.alterar"));
      b3.addActionListener(this);
      b5.setText(bn.getString("b.excluir"));
      b5.addActionListener(this);
      b4.setText(bn.getString("b.consultar"));
      b4.addActionListener(this);
      b2.setText(bn.getString("b.cancelar"));
      b2.addActionListener(this);
      
      //Arrumando layout
      pCima.add(lCnpj);
      pCima.add(tCnpj);
      pCima.add(b4);
      pCentral.add(lRZ);
      pCentral.add(tRZ);
      pCentral.add(lConj);
      pCentral.add(tConj);
      pCentral.add(lHorFunc);
      pCentral.add(tHorFunc);
      pCentral.add(lValorPadAC);
      pCentral.add(tValorPadAC);
      pCentral.add(lHorPadAC);
      pCentral.add(tHorPadAC); 
      
      
      pBotoes.add(b3);
      b3.setEnabled(false);
      pBotoes.add(b5);
      b5.setEnabled(false);                       
      pBotoes.add(b2); 
           
      pPrincipal.add(pCima, BorderLayout.NORTH); 
      pPrincipal.add(pCentral, BorderLayout.CENTER);      
      pPrincipal.add(pBotoes, BorderLayout.SOUTH);
      
      add(pPrincipal);                  
            
      setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
       
      setResizable(false);
      setSize(410, 230);      
      setLocation(500,240);
      setVisible(true);
      this.setFocusableWindowState(true); 
   }   
   
   
   public void atualizaIdioma(ResourceBundle b)
   {
      this.bn = b;
      
      setTitle(bn.getString("super.ca"));
      setTitle(bn.getString("super.co"));              
       
      lCnpj.setText(bn.getString("cnpj"));
      lRZ.setText(bn.getString("razao.social"));
      lConj.setText(bn.getString("conjunto"));
      lHorFunc.setText(bn.getString("hor.func"));
      lValorPadAC.setText(bn.getString("valor.pad.temp.max"));
      lHorPadAC.setText(bn.getString("hor.pad.func.ac")); 
      
      b1.setText(bn.getString("b.cadastrar"));      
      b2.setText(bn.getString("b.cancelar"));
      b3.setText(bn.getString("b.alterar"));
      b4.setText(bn.getString("b.consultar"));
      b5.setText(bn.getString("b.excluir"));
      msgCadastro = bn.getString("e.msgcadastro");
      msgNCadastro = bn.getString("e.msgncadastro");
      msgJaCadastrada = bn.getString("e.msgjacadastrada");
      msgErro = bn.getString("e.msgerro");
      msgAlterar = bn.getString("e.msgalterar");
      msgExcluir = bn.getString("e.msgexcluir");
   }
   
   public void actionPerformed( ActionEvent e)
   {
      if(e.getSource()== b1)
      {
                   
         
         try
         {
           
            EmpresaService empresaService = new EmpresaService();
        	boolean resultado = empresaService.cadastrar( Long.parseLong(tCnpj.getText()),
        			tRZ.getText(),
        			tConj.getText(),
        			tHorFunc.getText(),
        			tHorPadAC.getText(),
        			Integer.parseInt(tValorPadAC.getText()));
            
            if(resultado)
            {
               JOptionPane.showMessageDialog(null,msgCadastro);
               tCnpj.setText("");
               tRZ.setText("");
               tConj.setText("");
               tHorFunc.setText("");
               tHorPadAC.setText("");
               tValorPadAC.setText("");
            } 
           
            
         }
         catch(Exception er)
         {
            JOptionPane.showMessageDialog(null,msgErro);
         }   
        
      }
      
      if(e.getSource()== b2)
      {
         dispose();
                   
      }
      
      if(e.getSource()== b3)
      {
    	  EmpresaService empresaService = new EmpresaService();
    	  System.out.println("Classe VC");      			
        	System.out.println(Long.parseLong(tCnpj.getText()));
        					System.out.println(tRZ.getText());
        					System.out.println(tConj.getText());
        					System.out.println(tHorFunc.getText());
        					System.out.println(tHorPadAC.getText());
        					System.out.println(Integer.parseInt(tValorPadAC.getText()));
          
    	  boolean resultado = empresaService.alterar( Long.parseLong(tCnpj.getText()),
      			tRZ.getText(),
      			tConj.getText(),
      			tHorFunc.getText(),
      			tHorPadAC.getText(),
      			Integer.parseInt(tValorPadAC.getText()));
      	
      	  
          if(resultado) JOptionPane.showMessageDialog(null, msgAlterar);
              
      }
      
      if(e.getSource()== b4)
      {
         try{
        	 EmpresaService empresaService = new EmpresaService();
        	 EmpresaTO empTO = empresaService.consultar(Long.parseLong(tCnpj.getText()));
            tRZ.setText(empTO.getRazaoSocial());
               tConj.setText(empTO.getConjunto());
               tHorFunc.setText(empTO.getHorFunc());
               tHorPadAC.setText(empTO.getHorFuncAC());
               tValorPadAC.setText(""+empTO.getValorMaxAC());
                
               if(Integer.parseInt(tValorPadAC.getText())==-1)
               {
                  JOptionPane.showMessageDialog(null,msgNCadastro);
                  
                  tRZ.setText("");
                  tConj.setText("");
                  tHorFunc.setText("");
                  tHorPadAC.setText("");
                  tValorPadAC.setText("");
                  b5.setEnabled(false);
                  b3.setEnabled(false);
               }
               else 
               {
                  b5.setEnabled(true);
                  b3.setEnabled(true);
               }
         
         }
         catch(Exception p)
         {
            JOptionPane.showMessageDialog(null,msgErro); 
         } 
              
                    
      }
      
      if(e.getSource()== b5)
      {
    	 EmpresaService empresaService = new EmpresaService();
         if( empresaService.excluir(Long.parseLong(tCnpj.getText()))) JOptionPane.showMessageDialog(null,msgExcluir);
         b5.setEnabled(false);
         b3.setEnabled(false);   
         tCnpj.setText("");
         tRZ.setText("");
         tConj.setText("");
         tHorFunc.setText("");
         tHorPadAC.setText("");
         tValorPadAC.setText("");
                  
      }      
   }
   
   public static void main(String[] args)
   {            
      EmpresaVC a = new EmpresaVC();           
      a.cadastrar();
   }  
}
