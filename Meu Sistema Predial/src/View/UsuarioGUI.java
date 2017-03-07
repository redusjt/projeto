package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import DAO.EmpresaDAO;
import Model.Usuario;

public class UsuarioGUI extends JDialog implements ActionListener, ItemListener {
	private JLabel lTipoUsuario, lNome, lCpf, lDataNascimento, lEndereco, lBairro, lTelefone, lSenha, lEmpresa,
			lHorarioDeAcesso, lEntre, lE;
	private JTextField tfNome, tfEndereco, tfBairro;
	private JFormattedTextField ftfCpf, ftfDataNascimento, ftfTelefone, ftfHorarioDeAcessoInicial,
			ftfHorarioDeAcessoFinal;
	private JPasswordField pfSenha;
	private JComboBox cbTipoUsuario, cbEmpresa;
	private JCheckBox chbPermissaoFuncionario;
	private JButton bCadastrar, bCancelar, bLimpar, bConsultar, bAlterar, bExcluir, bSalvar, bVoltar;
	private JPanel pBase, pDados, pDadosComuns, pDadosFuncionario, pTipoUsuario, pNome, pCpf, pDataNascimento,
			pEndereco, pTelefone, pSenha, pEmpresa, pHorarioDeAcesso, pBotoes;
	private ResourceBundle bundle;
	private int titulo, tipoUsuario;
	Color corGrid;

	/*
	 * O parâmetro neste construtor indica qual a forma que será construida a
	 * janela deste frame quando este for instanciado 0 -> janela para cadastro
	 * de funcionário 1 -> janela para cadastro de atendente ou síndico Qualquer
	 * outro inteiro -> janela para consulta de usuários
	 */
	public UsuarioGUI(int modo) {

		EmpresaDAO eDAO = new EmpresaDAO();
		tipoUsuario = 0;
		bundle = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));
		String[] listaEmpresas = eDAO.selectAllCNPJ();
		String[] tipoUsuario = { bundle.getString("tuFuncionario"), bundle.getString("tuAtendente"),
				bundle.getString("tuSindico") };
		String mascaraCpf = "###.###.###-##";
		String mascaraData = "####/##/##";
		String mascaraTelefone = "(##)####-####";
		String mascaraHorario = "##:##";

		pBase = new JPanel(new BorderLayout());
		pDados = new JPanel(new BorderLayout());
		pDadosComuns = new JPanel(new GridLayout(6, 1));
		pDadosFuncionario = new JPanel(new GridLayout(3, 1));
		pTipoUsuario = new JPanel();
		pNome = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCpf = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pDataNascimento = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pEndereco = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pTelefone = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pSenha = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pEmpresa = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pHorarioDeAcesso = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pBotoes = new JPanel();

		lTipoUsuario = new JLabel(bundle.getString("lTipoDeUsuario"));
		lNome = new JLabel(bundle.getString("lNome"));
		lCpf = new JLabel(bundle.getString("lCPF"));
		lDataNascimento = new JLabel(bundle.getString("lDataDeNascimento"));
		lEndereco = new JLabel(bundle.getString("lEndereco"));
		lBairro = new JLabel(bundle.getString("lBairro"));
		lTelefone = new JLabel(bundle.getString("lTelefone"));
		lSenha = new JLabel(bundle.getString("lSenha"));
		lEmpresa = new JLabel(bundle.getString("lEmpresa"));
		lHorarioDeAcesso = new JLabel(bundle.getString("lHorarioDeAcesso"));
		lEntre = new JLabel(bundle.getString("lEntre"));
		lE = new JLabel(bundle.getString("lE"));

		tfNome = new JTextField(30);
		tfEndereco = new JTextField(30);
		tfBairro = new JTextField(25);

		pfSenha = new JPasswordField(7);

		ftfCpf = new JFormattedTextField(criarMascara(mascaraCpf));
		ftfDataNascimento = new JFormattedTextField(criarMascara(mascaraData));
		ftfTelefone = new JFormattedTextField(criarMascara(mascaraTelefone));
		ftfHorarioDeAcessoInicial = new JFormattedTextField(criarMascara(mascaraHorario));
		ftfHorarioDeAcessoFinal = new JFormattedTextField(criarMascara(mascaraHorario));

		cbTipoUsuario = new JComboBox(tipoUsuario);
		cbEmpresa = new JComboBox(listaEmpresas);
		chbPermissaoFuncionario = new JCheckBox(bundle.getString("chbPermissaoFuncionario"));

		bCadastrar = new JButton(bundle.getString("bCadastrar"));
		bSalvar = new JButton(bundle.getString("bSalvar"));
		bLimpar = new JButton(bundle.getString("bLimpar"));
		bCancelar = new JButton(bundle.getString("bCancelar"));
		bConsultar = new JButton(bundle.getString("bConsultar"));
		bAlterar = new JButton(bundle.getString("bAlterar"));
		bExcluir = new JButton(bundle.getString("bExcluir"));
		bVoltar = new JButton(bundle.getString("bVoltar"));

		// adicionando ouvintes aos objetos
		cbTipoUsuario.addItemListener(this);

		bCadastrar.addActionListener(this);
		bSalvar.addActionListener(this);
		bLimpar.addActionListener(this);
		bCancelar.addActionListener(this);
		bVoltar.addActionListener(this);
		bConsultar.addActionListener(this);
		bAlterar.addActionListener(this);
		bExcluir.addActionListener(this);
		// fim adicionando ouvintes aos objetos

		// formatando Componentes
		ftfCpf.setPreferredSize(new Dimension(100, 20));
		ftfDataNascimento.setPreferredSize(new Dimension(100, 20));
		ftfTelefone.setPreferredSize(new Dimension(100, 20));
		ftfHorarioDeAcessoInicial.setPreferredSize(new Dimension(50, 20));
		ftfHorarioDeAcessoFinal.setPreferredSize(new Dimension(50, 20));
		ftfHorarioDeAcessoInicial.setText("0000");
		ftfHorarioDeAcessoFinal.setText("0000");

		corGrid = new Color(32, 32, 32);

		pBase.setBackground(corGrid);
		pDados.setBackground(corGrid);
		pDadosComuns.setBackground(corGrid);
		pDadosFuncionario.setBackground(corGrid);
		pTipoUsuario.setBackground(corGrid);
		pNome.setBackground(corGrid);
		pCpf.setBackground(corGrid);
		pDataNascimento.setBackground(corGrid);
		pEndereco.setBackground(corGrid);
		pSenha.setBackground(corGrid);
		pTelefone.setBackground(corGrid);
		pEmpresa.setBackground(corGrid);
		pHorarioDeAcesso.setBackground(corGrid);
		pBotoes.setBackground(corGrid);

		lTipoUsuario.setForeground(Color.WHITE);
		lNome.setForeground(Color.WHITE);
		lCpf.setForeground(Color.WHITE);
		lDataNascimento.setForeground(Color.WHITE);
		lEndereco.setForeground(Color.WHITE);
		lBairro.setForeground(Color.WHITE);
		lTelefone.setForeground(Color.WHITE);
		lSenha.setForeground(Color.WHITE);
		lEmpresa.setForeground(Color.WHITE);
		lHorarioDeAcesso.setForeground(Color.WHITE);
		lEntre.setForeground(Color.WHITE);
		lE.setForeground(Color.WHITE);

		chbPermissaoFuncionario.setBackground(corGrid);
		chbPermissaoFuncionario.setForeground(Color.WHITE);

		// setModalityType(DEFAULT_MODALITY_TYPE);
		validate();
		setTitle(bundle.getString("frameTituloCadastrar"));
		setSize(800, 380);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// fim formatando Componentes

		// montagem do Frame
		construirFrame(modo);
		// fim montagem do Frame

		setModal(true);
		setVisible(true);
	}

	// construir janela para cadastro de um novo atendente ou síndico
	public void construirPainelPadrao() {
		titulo = 1;

		pTipoUsuario.add(lTipoUsuario);
		pTipoUsuario.add(cbTipoUsuario);
		pNome.add(lNome);
		pNome.add(tfNome);
		pCpf.add(lCpf);
		pCpf.add(ftfCpf);
		pDataNascimento.add(lDataNascimento);
		pDataNascimento.add(ftfDataNascimento);
		pEndereco.add(lEndereco);
		pEndereco.add(tfEndereco);
		pEndereco.add(lBairro);
		pEndereco.add(tfBairro);
		pTelefone.add(lTelefone);
		pTelefone.add(ftfTelefone);
		pSenha.add(lSenha);
		pSenha.add(pfSenha);
		pBotoes.add(bCadastrar);
		pBotoes.add(bLimpar);
		pBotoes.add(bCancelar);

		pDadosComuns.add(pNome);
		pDadosComuns.add(pCpf);
		pDadosComuns.add(pDataNascimento);
		pDadosComuns.add(pEndereco);
		pDadosComuns.add(pTelefone);
		pDadosComuns.add(pSenha);

		pDados.add(pDadosComuns, BorderLayout.CENTER);

		pBase.add(pTipoUsuario, BorderLayout.NORTH);
		pBase.add(pDados, BorderLayout.CENTER);
		pBase.add(pBotoes, BorderLayout.SOUTH);

		add(pBase);

		cbTipoUsuario.setSelectedIndex(1);
	}
	// fim construir janela para cadastro de um novo atendente ou síndico

	// construir janela para cadastro de um novo funcionario
	public void construirPainelFuncionario() {
		pEmpresa.add(lEmpresa);
		pEmpresa.add(cbEmpresa);

		pHorarioDeAcesso.add(lHorarioDeAcesso);
		pHorarioDeAcesso.add(lEntre);
		pHorarioDeAcesso.add(ftfHorarioDeAcessoInicial);
		pHorarioDeAcesso.add(lE);
		pHorarioDeAcesso.add(ftfHorarioDeAcessoFinal);

		pDadosFuncionario.add(pEmpresa);
		pDadosFuncionario.add(pHorarioDeAcesso);
		pDadosFuncionario.add(chbPermissaoFuncionario);

		pDados.add(pDadosFuncionario, BorderLayout.SOUTH);

		cbTipoUsuario.setSelectedIndex(0);

	}
	// fim construir janela para cadastro de um novo funcionario

	// construir janela para consulta de usuarios
	public void construirJanelaConsulta() {
		titulo = 2;
		setTitle(bundle.getString("frameTituloConsultar"));

		pCpf = new JPanel();
		pCpf.setBackground(corGrid);

		bAlterar.setEnabled(false);
		bExcluir.setEnabled(false);

		construirPainelCPFConsultar();

		pBotoes.add(bAlterar);
		pBotoes.add(bExcluir);
		pBotoes.add(bCancelar);

		add(pCpf, BorderLayout.NORTH);
		add(pBotoes, BorderLayout.SOUTH);

		validate();

		// Desabilita as opções de entrada, só sendo acessível a edição quando o
		// botão bAlterar for clicado
		tfNome.setEnabled(false);
		ftfDataNascimento.setEnabled(false);
		tfEndereco.setEnabled(false);
		tfBairro.setEnabled(false);
		cbEmpresa.setEnabled(false);

		pfSenha.setEnabled(false);

		ftfTelefone.setEnabled(false);
		ftfHorarioDeAcessoInicial.setEnabled(false);
		ftfHorarioDeAcessoFinal.setEnabled(false);

		chbPermissaoFuncionario.setEnabled(false);
		//

	}
	// fim construir janela para consulta de usuários

	private void construirPainelCPFConsultar() {
		pCpf.add(lCpf);
		pCpf.add(ftfCpf);
		pCpf.add(bConsultar);
	}

	public void construirFrame(int modo) {
		if (modo == 0) // modo de cadastro para síndico. Pode cadastrar todos
						// tipos de usuários
		{
			construirPainelPadrao();
			construirPainelFuncionario();
		} else if (modo == 1) // modo de cadastro para atendente. Pode cadastrar
								// somente atendentes ou funcionários
		{
			construirPainelPadrao();
			construirPainelFuncionario();

			cbTipoUsuario.removeItemAt(2);
		} else {
			construirJanelaConsulta();
		}
	}

	// Action Performed
	public void actionPerformed(ActionEvent actionE) {
		if (actionE.getSource() == bCadastrar) {
			// Validando algumas informações antes de prosseguir para o cadastro
			long cpf = converterParaLong(ftfCpf.getText().replace(".", "").replace("-", ""));
			if (validarCPF(cpf) == false)
				return;

			String d = ftfDataNascimento.getText();
			if (validarData(d) == false)
				return;

			String s = String.valueOf(pfSenha.getPassword());
			if (s.equals("")) {
				JOptionPane.showMessageDialog(null, bundle.getString("msg1"), bundle.getString("msgTitulo1"), 2);
				return;
			}

			if (verificarCamposVazios()) {
				int resp = JOptionPane.showConfirmDialog(null, bundle.getString("msg2"), bundle.getString("msgTitulo2"),
						JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.NO_OPTION)
					return;
			}
			//

			// verificando se já existe algum cadastro com o CPF informado
			Usuario usuarioConsulta = new Usuario();
			usuarioConsulta.consultarUsuario(cpf);
			tipoUsuario = usuarioConsulta.getTipo();

			if (tipoUsuario == 0 || tipoUsuario == 1 || tipoUsuario == 2) {
				JOptionPane.showMessageDialog(null, bundle.getString("msg3"), bundle.getString("msgTitulo3"), 1);
				return;
			}

			long tel = converterParaLong(ftfTelefone.getText().replace("(", "").replace(")", "").replace("-", ""));
			if (s.length() > 7)
				s = s.substring(0, 6);

			Usuario usuarioM = new Usuario(cbTipoUsuario.getSelectedIndex(), tfNome.getText(), d,
					tfEndereco.getText(), tfBairro.getText(), tel, s, cbEmpresa.getSelectedItem().toString(),
					ftfHorarioDeAcessoInicial.getText(), ftfHorarioDeAcessoFinal.getText(), cpf,
					chbPermissaoFuncionario.isSelected());

			// Desassocia o usuário de uma empresa caso este for um atendente ou
			// síndico
			if (cbTipoUsuario.getSelectedIndex() != 0)
				usuarioM.setEmpresa("");

			if (usuarioM.cadastrarUsuario()) {
				JOptionPane.showMessageDialog(null, bundle.getString("msg4"), bundle.getString("msgTitulo4"), 1);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, bundle.getString("msg5"), bundle.getString("msgTitulo5"), 2);
			}
		}
		if (actionE.getSource() == bLimpar) {
			tfNome.setText("");
			ftfCpf.setText("");
			ftfDataNascimento.setText("");
			tfEndereco.setText("");
			tfBairro.setText("");
			ftfTelefone.setText("");
			pfSenha.setText("");
			ftfHorarioDeAcessoInicial.setText("0000");
			ftfHorarioDeAcessoFinal.setText("0000");
			chbPermissaoFuncionario.setSelected(false);
		}
		if (actionE.getSource() == bCancelar) {
			dispose();
		}
		if (actionE.getSource() == bConsultar) {
			// Validando entrada de CPF
			long cpf = converterParaLong(ftfCpf.getText().replace(".", "").replace("-", ""));
			if (validarCPF(cpf) == false)
				return;

			Usuario usuarioM = new Usuario();
			usuarioM.consultarUsuario(cpf);

			/*
			 * A varíavel local tipoUsuario referencia um tipo de usuário a um
			 * número inteiro. 0 -> funcionário 1 -> atendente 2 -> síndico
			 * 
			 * O tipo de usuario deve ser cadastrado no banco como um Inteiro e
			 * NUNCA deve ser nulo. Quando a classe model não tiver encontrado
			 * nenhum Usuário com o CPF passado, ela deve retornar -1 para
			 * informar a view que não foi encontrado um usuário.
			 */
			tipoUsuario = usuarioM.getTipo();

			if (tipoUsuario == -1) {
				JOptionPane.showMessageDialog(null, bundle.getString("msg6"), bundle.getString("msgTitulo6"), 1);
				return;
			} else if (tipoUsuario == 0) {
				construirFrame(0); // a construção do frame vai se alterar de
									// acordo com o tipo de usuário retornado

				cbEmpresa.setSelectedItem((Object) usuarioM.getEmpresa());
				ftfHorarioDeAcessoInicial.setText(usuarioM.getHorEntrada());
				ftfHorarioDeAcessoFinal.setText(usuarioM.getHorSaida());
				chbPermissaoFuncionario.setSelected(usuarioM.getPermissaoAC());
			} else {
				construirFrame(1);
			}

			// Carregando as informações do usuário consultado, quando
			// encontrado alguém
			tfNome.setText(usuarioM.getNome());
			ftfDataNascimento.setText(usuarioM.getDataNasc().replace("-", ""));
			tfEndereco.setText(usuarioM.getEndereco());
			tfBairro.setText(usuarioM.getBairro());
			ftfTelefone.setText(String.valueOf(usuarioM.getTelefone()));
			pfSenha.setText(usuarioM.getSenha());
			//

			// retirando componentes desnecessários para tal janela
			pBase.remove(pTipoUsuario);
			pBotoes.remove(bCadastrar);
			pBotoes.remove(bLimpar);

			construirPainelCPFConsultar();
			add(pCpf, BorderLayout.NORTH);

			bAlterar.setEnabled(true);
			bExcluir.setEnabled(true);

			validate();

		}
		if (actionE.getSource() == bAlterar) {
			tfNome.setEnabled(true);
			ftfDataNascimento.setEnabled(true);
			tfEndereco.setEnabled(true);
			tfBairro.setEnabled(true);
			cbEmpresa.setEnabled(true);
			pfSenha.setEnabled(true);
			ftfTelefone.setEnabled(true);
			ftfHorarioDeAcessoInicial.setEnabled(true);
			ftfHorarioDeAcessoFinal.setEnabled(true);
			chbPermissaoFuncionario.setEnabled(true);

			ftfCpf.setEnabled(false);
			bConsultar.setEnabled(false);

			pBotoes.removeAll();
			validate();
			pBotoes.add(bSalvar);
			pBotoes.add(bCancelar);
			validate();
		}
		if (actionE.getSource() == bSalvar)// só aparece depois de apertado o
											// botão bAlterar
		{
			// Validando algumas informações antes de prosseguir para o cadastro
			String d = ftfDataNascimento.getText();
			if (validarData(d) == false)
				return;

			String s = String.valueOf(pfSenha.getPassword());
			if (s.equals("")) {
				JOptionPane.showMessageDialog(null, bundle.getString("msg1"), bundle.getString("msgTitulo1"), 2);
				return;
			}

			if (verificarCamposVazios()) {
				int resp = JOptionPane.showConfirmDialog(null, bundle.getString("msg2"), bundle.getString("msgTitulo2"),
						JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.NO_OPTION)
					return;
			}
			//

			long tel = converterParaLong(ftfTelefone.getText().replace("(", "").replace(")", "").replace("-", ""));
			if (s.length() > 7)
				s = s.substring(0, 6);

			Usuario usuarioM = new Usuario();

			usuarioM.setNome(tfNome.getText());
			usuarioM.setDataNasc(d);
			usuarioM.setEndereco(tfEndereco.getText());
			usuarioM.setBairro(tfBairro.getText());
			usuarioM.setTelefone(tel);
			usuarioM.setSenha(s);

			if (tipoUsuario == 0) {
				usuarioM.setEmpresa(cbEmpresa.getSelectedItem().toString());
				usuarioM.setHorEntrada(ftfHorarioDeAcessoInicial.getText());
				usuarioM.setHorSaida(ftfHorarioDeAcessoFinal.getText());
				usuarioM.setCpf(Long.parseLong(ftfCpf.getText().replace(".", "").replace("-", "")));
				usuarioM.setPermissaoAC(chbPermissaoFuncionario.isSelected());
			}

			System.out.println(usuarioM.getTipo());
			System.out.println(usuarioM.getNome());
			System.out.println(usuarioM.getDataNasc());
			System.out.println(usuarioM.getEndereco());
			System.out.println(usuarioM.getBairro());
			System.out.println(usuarioM.getTelefone());
			System.out.println(usuarioM.getSenha());
			System.out.println(usuarioM.getEmpresa());
			System.out.println(usuarioM.getHorEntrada());
			System.out.println(usuarioM.getHorSaida());
			System.out.println(usuarioM.getPermissaoAC());

			if (usuarioM.alterarUsuario(Long.parseLong(ftfCpf.getText().replace(".", "").replace("-", "")))) {
				JOptionPane.showMessageDialog(null, bundle.getString("msg7"), bundle.getString("msgTitulo7"), 1);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, bundle.getString("msg8"), bundle.getString("msgTitulo8"), 2);
			}
		}
		if (actionE.getSource() == bExcluir) {
			int resp = JOptionPane.showConfirmDialog(null, bundle.getString("msg9"), bundle.getString("msgTitulo9"),
					JOptionPane.YES_NO_OPTION);

			if (resp == JOptionPane.YES_OPTION) {
				// Validando entrada de CPF
				long cpf = converterParaLong(ftfCpf.getText().replace(".", "").replace("-", ""));
				if (validarCPF(cpf) == false)
					return;

				Usuario usuarioM = new Usuario();
				String msg = "";
				int n = 0;
				if (usuarioM.excluirUsuario(cpf)) {
					msg = bundle.getString("msg10");
					n = 1;

					bLimpar.doClick();
					bAlterar.setEnabled(false);
					bExcluir.setEnabled(false);
				} else {
					msg = bundle.getString("msg11");
					n = 2;
				}
				JOptionPane.showMessageDialog(null, msg, bundle.getString("msgTitulo10"), n);
			}

		}
	}

	// fim Action Performed
	// Item State Changed
	public void itemStateChanged(ItemEvent itemE) {
		if (itemE.getStateChange() == ItemEvent.SELECTED) {
			pDados.remove(pDadosFuncionario);
			validate();

			if (cbTipoUsuario.getSelectedItem() == bundle.getString("tuFuncionario")) {
				construirPainelFuncionario();
			}
			validate();
		}
	}
	// fim Item State Changed

	public void atualizaIdioma(ResourceBundle b) {
		this.bundle = b;

		if (titulo == 2)
			setTitle(bundle.getString("frameTituloConsultar"));
		else
			setTitle(bundle.getString("frameTituloCadastrar"));

		cbTipoUsuario.removeAllItems();
		cbTipoUsuario.addItem(bundle.getString("tuFuncionario"));
		cbTipoUsuario.addItem(bundle.getString("tuAtendente"));
		cbTipoUsuario.addItem(bundle.getString("tuSindico"));

		lTipoUsuario.setText(bundle.getString("lTipoDeUsuario"));
		lNome.setText(bundle.getString("lNome"));
		lCpf.setText(bundle.getString("lCPF"));
		lDataNascimento.setText(bundle.getString("lDataDeNascimento"));
		lEndereco.setText(bundle.getString("lEndereco"));
		lBairro.setText(bundle.getString("lBairro"));
		lTelefone.setText(bundle.getString("lTelefone"));
		lEmpresa.setText(bundle.getString("lEmpresa"));
		lHorarioDeAcesso.setText(bundle.getString("lHorarioDeAcesso"));
		lEntre.setText(bundle.getString("lEntre"));
		lE.setText(bundle.getString("lE"));

		bSalvar.setText(bundle.getString("bSalvar"));
		bLimpar.setText(bundle.getString("bLimpar"));
		bCancelar.setText(bundle.getString("bCancelar"));
		bConsultar.setText(bundle.getString("bConsultar"));
		bAlterar.setText(bundle.getString("bAlterar"));
		bExcluir.setText(bundle.getString("bExcluir"));
		bVoltar.setText(bundle.getString("bVoltar"));

		chbPermissaoFuncionario.setText(bundle.getString("chbPermissaoFuncionario"));

		validate();
	}

	// criar Máscara de Formatação
	private MaskFormatter criarMascara(String mascara) {
		MaskFormatter formatacao = null;
		try {
			formatacao = new MaskFormatter(mascara);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
		}
		return formatacao;
	}
	// fim criar Máscara de Formatação

	private boolean validarCPF(long cpf) {
		if (cpf == 0) {
			JOptionPane.showMessageDialog(null, bundle.getString("msg12"), bundle.getString("msgTitulo12"), 2);
			return false;
		} else {
			return true;
		}

	}

	private boolean validarData(String data) {
		String d = data.replace("/", "");
		String msg = "";

		if (d.equals("        ")) {
			msg = bundle.getString("msg13");
		} else if (d.length() < 8) {
			msg = bundle.getString("msg14");
		} else if (Integer.parseInt(d.substring(0, 4)) < 1900 || Integer.parseInt(d.substring(0, 4)) > 2999) {
			msg = bundle.getString("msg15");
		} else if (Integer.parseInt(d.substring(4, 6)) > 12) {
			msg = bundle.getString("msg16");
		} else if (Integer.parseInt(d.substring(6, 8)) > 31) {
			msg = bundle.getString("msg17");
		} else
			return true;

		JOptionPane.showMessageDialog(null, msg, bundle.getString("msgTitulo13"), 2);
		return false;
	}

	private boolean verificarCamposVazios() {
		if (tfNome.getText().equals("") || ftfDataNascimento.getText().equals("//")
				|| ftfTelefone.getText().equals("()-") || tfEndereco.getText().equals("")
				|| tfBairro.getText().equals("")) {
			return true;
		} else if (tipoUsuario == 0
				&& (ftfHorarioDeAcessoInicial.getText().equals(":") || ftfHorarioDeAcessoFinal.getText().equals(":"))) {
			return true;
		} else {
			return false;
		}
	}

	private long converterParaLong(String l) {
		long numberParsed;

		try {
			numberParsed = Long.parseLong(l);
		} catch (NullPointerException | NumberFormatException e) {
			numberParsed = 0;
		}

		return numberParsed;
	}

}