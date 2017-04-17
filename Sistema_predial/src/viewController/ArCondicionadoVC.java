package viewController;

import javax.swing.*;

import dao.EmpresaDAO;
import model.ArCondicionadoM;
import service.ArCondicionadoService;

import java.util.ResourceBundle;
import java.util.Locale;
import java.util.Scanner;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArCondicionadoVC extends JDialog implements ActionListener
{
   private JTable                tabAresCondicionados;    
   private String[]              titulos;        
   private String[][]            dados; 
   private JButton               bReconfigurar, bCanc;
   private JPanel                pTabela, pBotao;
   private JScrollPane           spTabela;
   private ResourceBundle        bn;
   private ArCondicionadoService aCService;
   public  String[]              usuarioLogado;
   Color corGrid;
   
   //JDialog
   JDialog           input;
   JLabel            lEmp, lTemp;
   EmpresaDAO        eDAO;
   String[]          listaEmpresas;
   JComboBox         cbEmpresa;        
   JTextField        tfTemp;
   JButton           bOk;
   //JDialog          
   
   
   public ArCondicionadoVC( ResourceBundle b )
   {
      super();
      //configurando frame
      setLayout( new BorderLayout() );
      setSize(700, 285);
      setLocationRelativeTo(null);
      setResizable(false);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      //
      
      bn = b;
      aCService = new ArCondicionadoService(bn); 
                      
      setTitle(bn.getString("ac.super"));
      titulos = new String[5];
      titulos[0] = bn.getString("ac.empresa");
      titulos[1] = bn.getString("ac.conjunto");
      titulos[2] = bn.getString("ac.temp.max");
      titulos[3] = bn.getString("ac.temp.atual");
      titulos[4] = bn.getString("ac.status");
      
      pTabela                = new JPanel();
      pBotao                 = new JPanel( new FlowLayout(FlowLayout.RIGHT) );
      
      dados                  = aCService.consultarTodosAC();
      tabAresCondicionados   = new JTable( dados, titulos );
      
      spTabela               = new JScrollPane( tabAresCondicionados );
      
      bReconfigurar          = new JButton(bn.getString("ac.reconfigurar"));
      bCanc                  = new JButton(bn.getString("ac.cancelar"));
   
      bReconfigurar.addActionListener(this);   
      bCanc.addActionListener(this);    
            
      //formatando e configurando objetos
      tabAresCondicionados.setShowGrid(false);
      tabAresCondicionados.setEnabled(false);
      
      spTabela.setPreferredSize( new Dimension( 600, 200 ) );
      spTabela.setBackground( Color.WHITE );
      corGrid  = new Color(32,32,32);
      pTabela.setBackground(corGrid);
      pBotao.setBackground(corGrid);
      //fim formatando e configurando objetos
      
      //montagem frame
      pTabela.add(spTabela);
      pBotao.add(bReconfigurar);
      pBotao.add(bCanc);
      
      add(pTabela, BorderLayout.CENTER);
      add(pBotao, BorderLayout.SOUTH);
   
      //fim montagem frame
      setModal(true);
            
      //JDialog
      input             = new JDialog(this);
      lEmp              = new JLabel( bn.getString("lEmpresa") );
      lTemp             = new JLabel( bn.getString("lTemp") );         
      eDAO              = new EmpresaDAO(); 
      listaEmpresas     = eDAO.selectAllCNPJ();
      cbEmpresa         = new JComboBox(listaEmpresas);
      tfTemp            = new JTextField("00.00", 10);
      bOk               = new JButton( bn.getString("ac.reconfigurar") );
      bOk.addActionListener(this);
      //JDialog
      this.setVisible(true);
      
   }
   
   public void atualizaIdioma(ResourceBundle b)
   {
      this.bn = b;
      
      setTitle(bn.getString("ac.super"));
      
      bReconfigurar.setText(bn.getString("ac.reconfigurar"));
      
      bCanc.setText(bn.getString("ac.cancelar"));
      tabAresCondicionados.getColumnModel().getColumn(0).setHeaderValue(bn.getString("ac.empresa"));   
      tabAresCondicionados.getColumnModel().getColumn(1).setHeaderValue(bn.getString("ac.conjunto"));
      tabAresCondicionados.getColumnModel().getColumn(2).setHeaderValue(bn.getString("ac.temp.max"));
      tabAresCondicionados.getColumnModel().getColumn(3).setHeaderValue(bn.getString("ac.temp.atual"));
      tabAresCondicionados.getColumnModel().getColumn(4).setHeaderValue(bn.getString("ac.status"));   
        
   }
   
   public void actionPerformed( ActionEvent e )
   {
      if(e.getSource()==bCanc)
      {
         dispose();
      }
      if(e.getSource()==bReconfigurar)
      {
         if( usuarioLogado[1].equals("0") ) //Se for Funcion�rio
         {
            if( aCService.permissaoAlterarAC( Long.parseLong(usuarioLogado[0]) ) ) //Verifica se tem permiss�o para alterar a temperatura
            {
               try
               {
                  double temperatura = Double.parseDouble( JOptionPane.showInputDialog(null, bn.getString("ac.msg1"), bn.getString("ac.msg1Titulo"), 1) );
                  aCService.alterarTemperatura( temperatura, Long.parseLong(usuarioLogado[0]) );
                  JOptionPane.showMessageDialog(null, bn.getString("ac.msg2"), bn.getString("ac.msg2Titulo"), 1);
                  
               }
               catch(Exception ex)
               {
                  JOptionPane.showMessageDialog(null, bn.getString("ac.msg3"), bn.getString("ac.msg3Titulo"), 2);
               }
               
            }
            else
            {
               JOptionPane.showMessageDialog(null, bn.getString("ac.msg4"), bn.getString("ac.msg4Titulo"), 2);
            }   
         }
         else
         {
            input.setTitle(bn.getString("ac.msg1Titulo"));
            input.setLayout( new GridLayout(3,1) );
            input.setSize(400, 200);
            input.setResizable(false);
            input.setLocationRelativeTo(null);
            
            input.add(lEmp);
            input.add(cbEmpresa);
            input.add(lTemp);
            input.add(tfTemp);
            input.add(bOk);
            input.pack();
            
            input.setModal(true);
            input.setVisible(true);
         }
      }
      
      if(e.getSource() == bOk)
      {
         try
         {
            long   cnpj        = Long.parseLong( cbEmpresa.getSelectedItem().toString() );
            double temperatura = Double.parseDouble( tfTemp.getText() );
            aCService.alterarTemperatura(cnpj, temperatura);
            JOptionPane.showMessageDialog(null, bn.getString("ac.msg2"), bn.getString("ac.msg2Titulo"), 1);
            input.dispose();
         }
         catch(Exception ex)
         {
            JOptionPane.showMessageDialog(null, bn.getString("ac.msg3"), bn.getString("ac.msg3Titulo"), 2);
         }
      }
   
   }
   public static void main(String[] args)
   {
      ResourceBundle bn = ResourceBundle.getBundle("properties/idioma", new Locale("pt_BR"));
	   ArCondicionadoVC avc = new ArCondicionadoVC(bn);
      
   }
}   
