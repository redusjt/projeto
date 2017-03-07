package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import DAO.RegistroDeAcessoDAO;


public class RegistroDeAcessoGUI extends JFrame implements ActionListener, ItemListener {
	private JTextArea tabRegistros;
	private String[] titulos, listaFiltros;// = {"Data", "CPF", "Nome", "Horário
											// Entrada", "Horário Saída"};
	private String[][] dados;
	private JButton bConsultar, bVoltar;
	private JFormattedTextField ftfDataInicio, ftfDataFim;
	private JTextField tfNomeEmpresa;
	private JLabel lDataInicio, lDataFim;
	private JComboBox cbFiltros;
	private JPanel pBase, pFiltros, pTabela, pBotao;
	private JScrollPane spTabela;
	private ResourceBundle bn = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));
	Color corGrid;

	public RegistroDeAcessoGUI() {

		setTitle(bn.getString("ra.super"));

		corGrid = new Color(32, 32, 32);

		titulos = new String[5];
		titulos[0] = bn.getString("ra.t.data");
		titulos[1] = bn.getString("ra.cpf");
		titulos[2] = bn.getString("ra.nome");
		titulos[3] = bn.getString("ra.horario.e");
		titulos[4] = bn.getString("ra.horario.s");

		listaFiltros = new String[2];
		listaFiltros[0] = bn.getString("ra.data");
		listaFiltros[1] = bn.getString("ra.emp");
		String mascaraData = "####-##-##";

		pBase = new JPanel(new BorderLayout());
		pBase.setBackground(corGrid);
		pFiltros = new JPanel();
		pFiltros.setBackground(corGrid);
		pTabela = new JPanel();
		pTabela.setBackground(corGrid);
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
		RegistroDeAcessoDAO registro = new RegistroDeAcessoDAO();
		dados = new String[5][5];
		tabRegistros = new JTextArea();
		tabRegistros.setEditable(false);
		tabRegistros.setText(registro.getAllAcessos());

		spTabela = new JScrollPane(tabRegistros);

		bConsultar = new JButton(bn.getString("ra.consultar"));
		bVoltar = new JButton(bn.getString("ra.voltar"));

		bConsultar.addActionListener(this);
		bVoltar.addActionListener(this);
		cbFiltros.addItemListener(this);

		// formatando e configurando objetos
		ftfDataInicio.setPreferredSize(new Dimension(80, 25));
		ftfDataFim.setPreferredSize(new Dimension(80, 25));

		spTabela.setPreferredSize(new Dimension(600, 200));
		spTabela.setBackground(Color.WHITE);

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

	public void construirJanelaPadrao() {
		pTabela.add(spTabela);
		pBotao.add(bVoltar);

		pBase.add(pTabela, BorderLayout.CENTER);
		pBase.add(pBotao, BorderLayout.SOUTH);

		add(pBase);
	}

	public void construirPainelFiltrosData() {
		pFiltros.add(cbFiltros);
		pFiltros.add(lDataInicio);
		pFiltros.add(ftfDataInicio);
		pFiltros.add(lDataFim);
		pFiltros.add(ftfDataFim);
		pFiltros.add(bConsultar);

		pBase.add(pFiltros, BorderLayout.NORTH);
	}

	public void construirPainelFiltrosEmpresa() {
		pFiltros.add(cbFiltros);
		pFiltros.add(tfNomeEmpresa);
		pFiltros.add(bConsultar);

		pBase.add(pFiltros, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bConsultar) {

			if (cbFiltros.getSelectedIndex() == 1) {
				tabRegistros.setText("");
				RegistroDeAcessoDAO registro = new RegistroDeAcessoDAO();
				tabRegistros.setText(registro.getAcessosEmp(tfNomeEmpresa.getText()));
			} else if (cbFiltros.getSelectedIndex() == 0) {
				tabRegistros.setText("");
				RegistroDeAcessoDAO registro = new RegistroDeAcessoDAO();
				tabRegistros.setText(registro.getAcessosData(lDataInicio.getText(), lDataFim.getText()));
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

	// criar Máscara de Formatação
	public MaskFormatter criarMascara(String mascara) {
		MaskFormatter formatacao = null;
		try {
			formatacao = new MaskFormatter(mascara);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
		}
		return formatacao;
	}
	// fim criar Máscara de Formatação

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
		RegistroDeAcessoGUI r = new RegistroDeAcessoGUI();
	}
}