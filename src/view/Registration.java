package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import util.DragListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.Cursor;

public class Registration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	public User user;

	public Registration() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		setUndecorated(true);
		DragListener.add(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(51, 51, 204));
		panel.setBounds(0, 0, 400, 600);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("PAINEL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 34));
		lblNewLabel.setBounds(92, 200, 216, 77);
		panel.add(lblNewLabel);
		
		JLabel lblDe = new JLabel("DE");
		lblDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDe.setForeground(Color.WHITE);
		lblDe.setFont(new Font("Verdana", Font.BOLD, 34));
		lblDe.setBounds(92, 261, 216, 77);
		panel.add(lblDe);
		
		JLabel lblControle = new JLabel("CONTROLE");
		lblControle.setHorizontalAlignment(SwingConstants.CENTER);
		lblControle.setForeground(Color.WHITE);
		lblControle.setFont(new Font("Verdana", Font.BOLD, 34));
		lblControle.setBounds(92, 326, 216, 77);
		panel.add(lblControle);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(400, 0, 400, 600);
		panel_1.setBackground(Color.WHITE);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(59, 221, 281, 40);
		panel_1.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblTitle = new JLabel("REGISTRAR-SE");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Verdana", Font.PLAIN, 30));
		lblTitle.setBounds(64, 59, 271, 50);
		panel_1.add(lblTitle);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblUsername.setBounds(59, 195, 91, 27);
		panel_1.add(lblUsername);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblSenha.setBounds(59, 268, 61, 27);
		panel_1.add(lblSenha);
		
		JLabel lblConfimrSenha = new JLabel("Confirm Senha");
		lblConfimrSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfimrSenha.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblConfimrSenha.setBounds(59, 346, 281, 27);
		panel_1.add(lblConfimrSenha);
		
		JButton btnEntrar = new JButton("Registrar");
		btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				char[] pass = passwordField.getPassword();
				String password = String.copyValueOf(pass);
				
				char[] passConfirm = passwordField_1.getPassword();
				String passwordConfirm = String.copyValueOf(passConfirm);
				
				if((usernameField.getText().isEmpty() || password.isEmpty()) && !error) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos !");
					error = true;
				}
				
				if(password.length() <= 3 && !error) {
					JOptionPane.showMessageDialog(null, "A senha precisa conter mais de 3 digitos.");
					error = true;
				}
				
				if(!password.equals(passwordConfirm) && !error) {
					JOptionPane.showMessageDialog(null, "A senha n?o est?o iguais.");
					error = true;
				}
				

				if(!error) {
					UserDao userDao = DaoFactory.createUserDao();
					User user = userDao.findByUser(usernameField.getText());
					
					if(user != null) {
						JOptionPane.showMessageDialog(null, "Username em uso!");
						error = true;
					}
				
					user = new User(null,usernameField.getText(),password,false);
					userDao.create(user);
					JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso !");
					Login login = new Login();
					login.setVisible(true);
					dispose();
				}
			}			
		});
		btnEntrar.setBounds(59, 464, 281, 59);
		panel_1.add(btnEntrar);
		
		JLabel lblCadastro = new JLabel("Login");
		lblCadastro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCadastro.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblCadastro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		lblCadastro.setBounds(59, 424, 85, 14);
		panel_1.add(lblCadastro);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2.setBackground(new Color(255, 0, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_2.setBackground(Color.WHITE);
			}
		});
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(360, 0, 40, 40);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(15, 13, 10, 16);
		panel_2.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(59, 296, 281, 40);
		panel_1.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(59, 373, 281, 40);
		panel_1.add(passwordField_1);
		
	}
}
