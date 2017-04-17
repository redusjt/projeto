package viewController;

import javax.swing.*;

import criptografia.Decypher;
import model.LoginModel;
import service.RegistroAcessoService;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class CatracaVC extends JFrame implements ActionListener {

	private JButton entrar, sair;
	private JLabel oi, lg, sh;
	private JTextField login;
	private JPasswordField senha;
	private boolean status;
	private ResourceBundle bn = ResourceBundle.getBundle("properties/idioma", new Locale("pt", "BR"));
	public String msgErro = bn.getString("l.msgerro"), msgErro1 = bn.getString("l.msgerro1");

	public CatracaVC(int a) {
		// faichada
	}

	public CatracaVC() {
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
				RegistroAcessoService regAceService = new RegistroAcessoService();
				if (regAceService.entrar(Long.parseLong(login.getText()), String.valueOf(senha.getPassword()))) {
					JOptionPane.showMessageDialog(this, "Catraca liberada!", "Catraca", 1);
				} else {
					JOptionPane.showMessageDialog(this, "Catraca bloqueada!", "Catraca", 1);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Catraca bloqueada!");
			}
		}
		if (event.getSource() == sair) {
			try {
				RegistroAcessoService regAceService = new RegistroAcessoService();
				if (regAceService.sair(Long.parseLong(login.getText()), String.valueOf(senha.getPassword()))) {
					JOptionPane.showMessageDialog(this, "Catraca liberada!", "Catraca", 1);
				} else {
					JOptionPane.showMessageDialog(this, "Catraca bloqueada!", "Catraca", 1);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Catraca bloqueada!");
			}
		}

	}

	public static void main(String[] args) {
		CatracaVC c = new CatracaVC();
	}

}