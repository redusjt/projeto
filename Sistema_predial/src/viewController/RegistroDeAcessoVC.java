package viewController;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

import java.util.ResourceBundle;
import java.util.Locale;
import java.util.ArrayList;

import javax.swing.text.MaskFormatter;

import model.RegistroDeAcessoM;
import service.RegistroAcessoService;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.text.ParseException;
import transferObject.RegistroAcessoTO;

public class RegistroDeAcessoVC extends JFrame implements ActionListener, ItemListener {
	private JTable tabRegistros;
	private String[] titulos, listaFiltros;// = {"Data", "CPF", "Nome", "Hor�rio
											// Entrada", "Hor�rio Sa�da"};
	private String[][] dados;
	private JButton bConsultar, bVoltar;
	private JFormattedTextField ftfDataInicio, ftfDataFim;
	private JTextField tfNomeEmpresa;
	private JLabel lDataInicio, lDataFim;
	private JComboBox cbFiltros;
	private JPanel pBase, pFiltros, pTabela, pBotao;
	private JScrollPane spTabela;
	private ResourceBundle bn = ResourceBundle.getBundle("properties/idioma", new Locale("pt", "BR"));
	Color corGrid;

	public RegistroDeAcessoVC() {

		setTitle(bn.getString("ra.super"));

		corGrid = new Color(32, 32, 32);

		/*
		 * titulos = new String[5]; titulos[0] = bn.getString("ra.t.data");
		 * titulos[1] = bn.getString("ra.cpf"); titulos[2] =
		 * bn.getString("ra.nome"); titulos[3] = bn.getString("ra.horario.e");
		 * titulos[4] = bn.getString("ra.horario.s");
		 */

		listaFiltros = new String[2];
		listaFiltros[0] = bn.getString("ra.data");
		listaFiltros[1] = bn.getString("ra.emp");
		String mascaraData = "####-##-##";

		pBase = new JPanel(new BorderLayout());
		pBase.setBackground(corGrid);
		pFiltros = new JPanel();
		pFiltros.setBackground(corGrid);
		// pTabela = new JPanel();
		// pTabela.setBackground(corGrid);
		pBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pBotao.setBackground(corGrid);

		cbFiltros = new JComboBox(listaFiltros);

		lDataInicio = new JLabel(bn.getString("ra.inicio"));
		lDataInicio.setForeground(Color.WHITE);
		lDataFim = new JLabel(bn.getString("ra.fim"));
		lDataFim.setForeground(Color.WHITE);

		ftfDataInicio = new JFormattedTextField(criarMascara(mascaraData));
		ftfDataFim = new JFormattedTextField(criarMascara(mascaraData));

		tfNomeEmpresa = new JTextField(30);
		// RegistroDeAcessoM registro = new RegistroDeAcessoM();
		dados = new String[5][5];
		tabRegistros = new JTable();
		/*
		 * tabRegistros.setEditable(false);
		 * tabRegistros.setText(registro.getAllAcessos());
		 */

		// spTabela = new JScrollPane( tabRegistros );

		bConsultar = new JButton(bn.getString("ra.consultar"));
		bVoltar = new JButton(bn.getString("ra.voltar"));

		bConsultar.addActionListener(this);
		bVoltar.addActionListener(this);
		cbFiltros.addItemListener(this);

		// formatando e configurando objetos
		ftfDataInicio.setPreferredSize(new Dimension(80, 25));
		ftfDataFim.setPreferredSize(new Dimension(80, 25));

		// spTabela.setPreferredSize( new Dimension( 600, 200 ) );
		// spTabela.setBackground( Color.WHITE );

		setSize(700, 320);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		// fim formatando e configurando objetos

		// montagem frame
		construirPainelFiltrosData();
		construirJanelaPadrao();
		// fim montagem frame

	}

	private void construirJanelaPadrao() {
		// pTabela.add( spTabela );
		pBotao.add(bVoltar);

		// pBase.add( pTabela, BorderLayout.CENTER );
		pBase.add(pBotao, BorderLayout.SOUTH);

		add(pBase);
	}

	private void construirPainelFiltrosData() {
		pFiltros.add(cbFiltros);
		pFiltros.add(lDataInicio);
		pFiltros.add(ftfDataInicio);
		pFiltros.add(lDataFim);
		pFiltros.add(ftfDataFim);
		pFiltros.add(bConsultar);

		pBase.add(pFiltros, BorderLayout.NORTH);
	}

	private void construirPainelFiltrosEmpresa() {
		pFiltros.add(cbFiltros);
		pFiltros.add(tfNomeEmpresa);
		pFiltros.add(bConsultar);

		pBase.add(pFiltros, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bConsultar) {

			if (cbFiltros.getSelectedIndex() == 1) {
				RegistroAcessoService regAceService = new RegistroAcessoService();
				tabRegistros = regAceService.consultarPorEmpresa(tfNomeEmpresa.getText());
				JOptionPane.showMessageDialog(null, tabRegistros);

				spTabela = new JScrollPane(tabRegistros);
				spTabela.setPreferredSize(new Dimension(650, 200));
				pTabela = new JPanel();
				pTabela.add(spTabela);
				pTabela.setBackground(corGrid);
				pBase.add(pTabela, 2);
				pBase.validate();
				this.validate();
			}
			
			if (cbFiltros.getSelectedIndex() == 0) {
				String dataInicial, dataFinal;
				dataInicial = ftfDataInicio.getText();
				dataFinal = ftfDataFim.getText();
				RegistroAcessoService regAceService = new RegistroAcessoService();
				tabRegistros = regAceService.consultarPorData(dataInicial, dataFinal);
				JOptionPane.showMessageDialog(null, tabRegistros);

				spTabela = new JScrollPane(tabRegistros);
				spTabela.setPreferredSize(new Dimension(650, 200));
				pTabela = new JPanel();
				pTabela.add(spTabela);
				pTabela.setBackground(corGrid);
				pBase.add(pTabela, 2);
				pBase.validate();
				this.validate();
			}

		}
		if (e.getSource() == bVoltar) {
			dispose();
		}
	}

	public void itemStateChanged(ItemEvent itemE) {
		if (itemE.getStateChange() == ItemEvent.SELECTED) {
			pFiltros.removeAll();
			pBase.remove(pFiltros);
			validate();

			if (cbFiltros.getSelectedIndex() == 0) {
				construirPainelFiltrosData();
			} else if (cbFiltros.getSelectedIndex() == 1) {
				construirPainelFiltrosEmpresa();
			}
			validate();
		}
	}

	// criar M�scara de Formata��o
	public MaskFormatter criarMascara(String mascara) {
		MaskFormatter formatacao = null;
		try {
			formatacao = new MaskFormatter(mascara);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
		}
		return formatacao;
	}
	// fim criar M�scara de Formata��o

	public void atualizaIdioma(ResourceBundle b) {
		this.bn = b;

		setTitle(bn.getString("ra.super"));

		bConsultar.setText(bn.getString("ra.consultar"));
		bVoltar.setText(bn.getString("ra.voltar"));

		lDataInicio.setText(bn.getString("ra.inicio"));
		lDataFim.setText(bn.getString("ra.fim"));

		cbFiltros.removeAllItems();
		cbFiltros.addItem(bn.getString("ra.data"));
		cbFiltros.addItem(bn.getString("ra.emp"));

	}

	public static void main(String[] args) {
		RegistroDeAcessoVC r = new RegistroDeAcessoVC();
	}
}