package viewController;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Color;

import criptografia.Decypher;
import model.LoginModel;
import viewController.HomeVC;

public class LoginVC extends JFrame implements ActionListener {

	private JButton entrar, sair, ing, por, esp;
	private JLabel oi, lg, sh;
	private JTextField login;
	private JPasswordField senha;
	private ImageIcon iPort, iIng, iEsp;
	private ResourceBundle bn = ResourceBundle.getBundle("properties/idioma", new Locale("pt", "BR"));
	public String[] usuarioLogado;
	Color corGrid;
	public String msgErro = bn.getString("l.msgerro"), msgErro1 = bn.getString("l.msgerro1");

	public LoginVC() {

		Container principal = getContentPane();
		principal.setLayout(new BorderLayout());

		// Grid Esquerda superior
		entrar = new JButton(bn.getString("l.entrar"));
		sair = new JButton(bn.getString("l.cancelar"));
		lg = new JLabel(bn.getString("l.login"), JLabel.CENTER);
		lg.setForeground(Color.WHITE);
		sh = new JLabel(bn.getString("l.senha"), JLabel.CENTER);
		sh.setForeground(Color.WHITE);
		senha = new JPasswordField(15);
		login = new JTextField(15);

		iPort = new ImageIcon("img/brasil.jpg");
		iEsp = new ImageIcon("img/esp.jpg");
		iIng = new ImageIcon("img/usa.jpg");
		ing = new JButton(iIng);

		ing.setPreferredSize(new Dimension(30, 20));
		ing.addActionListener(this);
		por = new JButton(iPort);
		por.setPreferredSize(new Dimension(30, 20));
		por.addActionListener(this);
		esp = new JButton(iEsp);
		esp.setPreferredSize(new Dimension(30, 20));
		esp.addActionListener(this);

		corGrid = new Color(32, 32, 32);

		// Adicionando Ações
		sair.addActionListener(this);
		entrar.addActionListener(this);

		// Grid direita superior
		JPanel grid = new JPanel();
		grid.setBackground(corGrid);
		GridBagConstraints cons = new GridBagConstraints();
		grid.setLayout(new GridBagLayout());
		cons.fill = GridBagConstraints.NONE;
		cons.ipadx = 5;
		cons.ipady = 5;
		cons.gridy = 2;
		cons.insets = new Insets(10, 3, 3, 3);
		grid.add(lg, cons);
		cons.gridy = 2;
		grid.add(login, cons);
		cons.gridy = 4;
		grid.add(sh, cons);
		cons.gridy = 4;
		grid.add(senha, cons);
		cons.gridy = 6;
		cons.insets = new Insets(10, 35, 3, 0);
		grid.add(entrar, cons);
		cons.ipadx = 15;
		cons.ipady = 5;
		cons.gridy = 6;
		cons.gridheight = 2;
		cons.gridwidth = 2;
		cons.insets = new Insets(10, 3, 3, 3);
		grid.add(sair, cons);
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

	public void atualizaIdioma(ResourceBundle n) {
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

		if (event.getSource() == entrar) {
			try {
				Decypher d = new Decypher();
				LoginModel l = new LoginModel();

				if (l.binaria(d.getUsuario(), Long.parseLong(login.getText()))) {
					System.out.println("Achou usuario" + d.getUsuarioAt(l.getPos()));
					
					String senhaInserida = String.valueOf(senha.getPassword());
					
					if (senhaInserida.equals(d.getSenhaAt(l.getPos()))) {
						System.out.println("Achou senha" + d.getSenhaAt(l.getPos()));
						HomeVC home = new HomeVC();
						home.atualizaIdioma(bn);

						usuarioLogado = new String[2]; // Controla as
														// informações de CPF e
														// tipo de usuário
														// logado para
														// posteriores consultas
														// e restrições
						usuarioLogado[0] = login.getText(); // CPF
						usuarioLogado[1] = String.valueOf(d.getTipoAt(l.getPos())); // Tipo
																					// de
																					// Usuário
						home.usuarioLogado = this.usuarioLogado;
						

						if (d.getTipoAt(l.getPos()) == 0)// se usuário logado
															// for do tipo
															// funcionário,
															// desabilita
															// funções das quais
															// não tem permissão
						{
							home.addEmp.setEnabled(false);
							home.altEmp.setEnabled(false);
							home.addFunc.setEnabled(false);
							home.altFunc.setEnabled(false);
						}
						dispose();
					} else
						JOptionPane.showMessageDialog(null, msgErro);// ("Usuário
																		// e/ou
																		// Senha
																		// inválido(s)");
				} else
					JOptionPane.showMessageDialog(null, msgErro);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, msgErro1);
			}

		}

		if (event.getSource() == sair) {
			System.exit(0);
		}

		if (event.getSource() == ing) {
			bn = ResourceBundle.getBundle("properties/idioma", new Locale("en", "US"));
			atualizaIdioma(bn);
		}

		if (event.getSource() == esp) {
			bn = ResourceBundle.getBundle("properties/idioma", new Locale("es", "ES"));
			atualizaIdioma(bn);
		}

		if (event.getSource() == por) {
			bn = ResourceBundle.getBundle("properties/idioma", new Locale("pt", "BR"));
			atualizaIdioma(bn);
		}

	}

	public static void main(String[] args) {
		LoginVC l = new LoginVC();
	}

}