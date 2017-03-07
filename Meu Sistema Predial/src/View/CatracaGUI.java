package View;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.Decypher;
import Controller.Login;
import DAO.CatracaDAO;

public class CatracaGUI extends JFrame implements ActionListener {

	private JButton entrar, sair;
	private JLabel oi, lg, sh;
	private JTextField login;
	private JPasswordField senha;
	private boolean status;
	private ResourceBundle bn = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));
	public String msgErro = bn.getString("l.msgerro"), msgErro1 = bn.getString("l.msgerro1");

	public CatracaGUI(int a) {
		// faichada
	}

	public CatracaGUI() {
		super("Catraca");

		Container principal = getContentPane();
		principal.setLayout(new GridLayout(1, 1));

		// Grid Esquerda superior
		entrar = new JButton("Entrar");
		sair = new JButton("Sair");
		lg = new JLabel("Login: ", JLabel.CENTER);
		sh = new JLabel("Senha: ", JLabel.CENTER);
		senha = new JPasswordField(15);
		login = new JTextField(15);

		// Adicionando Ações
		sair.addActionListener(this);
		entrar.addActionListener(this);

		// Grid direita superior
		JPanel grid = new JPanel();
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

		principal.add(grid);

		setResizable(false);
		setSize(300, 180);
		setLocation(550, 300);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent event)

	{

		if (event.getSource() == entrar) {

			try {
				CatracaDAO c = new CatracaDAO();
				Decypher d = new Decypher();
				Login l = new Login();
				// long cpfLong = Long.parseLong(login.getText());
				if (l.binaria(d.getUsuario(), Long.parseLong(login.getText()))) {
					if (senha.getText().equals(d.getSenhaAt(l.getPos()))) {

						c.carregarDadosUsuario(Long.parseLong(login.getText()));

						if (status == false) {
							status = true;
							c.cadastrarEntrada(Long.parseLong(login.getText()));

							JOptionPane.showMessageDialog(null, "catraca liberada");

						} else {
							JOptionPane.showMessageDialog(null, "usuario esta dentro");
						}

					}

					else
						JOptionPane.showMessageDialog(null, msgErro);// ("Usuário
																		// e/ou
																		// Senha
																		// inválido(s)");

				}

				else
					JOptionPane.showMessageDialog(null, msgErro);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, msgErro1);
			}

		}

		if (event.getSource() == sair) {
			try {
				CatracaDAO c = new CatracaDAO();
				Decypher d = new Decypher();
				Login l = new Login();
				// long cpfLong = Long.parseLong(login.getText());
				if (l.binaria(d.getUsuario(), Long.parseLong(login.getText()))) {
					if (senha.getText().equals(d.getSenhaAt(l.getPos()))) {

						if (status == true) {
							status = false;
							c.cadastrarSaida(Long.parseLong(login.getText()));
							JOptionPane.showMessageDialog(null, "catraca liberada");
						} else {
							JOptionPane.showMessageDialog(null, "usuario não entrou");
						}
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

	}

	public static void main(String[] args) {
		CatracaGUI l = new CatracaGUI();
	}

}
