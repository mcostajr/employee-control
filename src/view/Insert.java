package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Employee;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.text.ParseException;

public class Insert extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JFormattedTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JFormattedTextField textField_4;

	
	public Insert() {
		setTitle("Cadastrar Funcionairo");
		setSize(450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dados do Funcionario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setBounds(76, 32, 306, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(86, 79, 33, 20);
		contentPane.add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(144, 81, 150, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(86, 105, 33, 20);
		contentPane.add(lblCpf);
		
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(86, 136, 48, 20);
		contentPane.add(lblCargo);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(144, 136, 150, 20);
		contentPane.add(textField_2);
		
		JLabel lblSalario = new JLabel("Salario");
		lblSalario.setBounds(86, 162, 48, 20);
		contentPane.add(lblSalario);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(144, 163, 150, 20);
		contentPane.add(textField_3);
		
		
		JLabel lblPhone = new JLabel("Telefone");
		lblPhone.setBounds(86, 190, 48, 20);
		contentPane.add(lblPhone);
		
		try {
			MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
			textField_1 = new JFormattedTextField(cpfMask);
			textField_1.setColumns(10);
			textField_1.setBounds(144, 105, 150, 20);
			contentPane.add(textField_1);
			
			MaskFormatter phoneMask = new MaskFormatter("(##) #####-####");
			textField_4 = new JFormattedTextField(phoneMask);
			textField_4.setColumns(10);
			textField_4.setBounds(144, 190, 150, 20);
			contentPane.add(textField_4);
		} catch (ParseException e) {
			System.out.println(e);
		}
		
		JButton btnNewButton = new JButton("Inserir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty() 
					|| textField_1.getText().isEmpty()
					|| textField_2.getText().isEmpty()
					|| textField_3.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");
				} else {
					EmployeeDao funcDao = DaoFactory.createEmployeeDao();
					Employee func = new Employee(null,textField.getText(),textField_1.getText(),textField_2.getText(),Integer.parseInt(textField_3.getText()),textField_4.getText(),new Date());
					funcDao.insert(func);
					JOptionPane.showMessageDialog(btnNewButton, "Inserido com Sucesso!");
					dispose();
				}
			}
		});
		btnNewButton.setBounds(144, 220, 150, 30);
		contentPane.add(btnNewButton);
	}
	
	public String formatCPF(String cpf) {

		
		
		return "";
	}
}
