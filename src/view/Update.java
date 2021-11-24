package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Employee;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Update extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	private JTextField textField;
	private JFormattedTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JFormattedTextField textField_4;
	private JButton btnNewButton;
	
	public Update(Integer id) {
		EmployeeDao employeeDao = DaoFactory.createEmployeeDao();
		
		setTitle("Editar Funcionario");
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Editar Funcionario");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(126, 27, 160, 23);
		contentPane.add(lblTitle);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(71, 83, 46, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(138, 80, 120, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CPF");
		lblNewLabel_1.setBounds(71, 114, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cargo");
		lblNewLabel_2.setBounds(71, 145, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(138, 142, 120, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Salario");
		lblNewLabel_3.setBounds(71, 177, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(138, 174, 120, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Telefone");
		lblNewLabel_4.setBounds(71, 208, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		try {
			MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
			textField_1 = new JFormattedTextField(cpfMask);
			textField_1.setColumns(10);
			textField_1.setBounds(138, 111, 120, 20);
			contentPane.add(textField_1);
			
			MaskFormatter phoneMask = new MaskFormatter("(##) #####-####");
			textField_4 = new JFormattedTextField(phoneMask);
			textField_4.setColumns(10);
			textField_4.setBounds(138, 205, 120, 20);
			contentPane.add(textField_4);
		} catch (ParseException e) {
			System.out.println(e);
		}
		
		btnNewButton = new JButton("Editar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee employee = new Employee(id,textField.getText(),textField_1.getText(),textField_2.getText(),Integer.parseInt(textField_3.getText()),textField_4.getText(), null);
				employeeDao.update(employee);
				JOptionPane.showMessageDialog(btnNewButton, "Editado com Sucesso!");
				dispose();
			}
		});
		btnNewButton.setBounds(135, 227, 123, 23);
		contentPane.add(btnNewButton);
		

		Employee employee = employeeDao.findById(id);
		
		textField.setText(employee.getName());
		textField_1.setText(employee.getCpf());
		textField_2.setText(employee.getJob());
		textField_3.setText(Integer.toString(employee.getSalary()));
		textField_4.setText(employee.getPhone());
		
		
	}
	
	public String formatPhone(String lblNewLabel_4) {
		
		
		return "";
	}
}
