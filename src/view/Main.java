package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Employee;
import model.entities.User;
import util.DragListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.SystemColor;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable table;
	
	public Main(User user) {
		setTitle("Employee Control Painel");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		DragListener.add(this);
		
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 49, 604, 540);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id","Nome", "CPF", "Cargo", "Salario","Telefone"
			}
		));
		scrollPane.setViewportView(table);
		
		listar();

		JLabel lblUser = new JLabel("");
		lblUser.setBackground(SystemColor.textText);
		lblUser.setForeground(SystemColor.textText);
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setBounds(657, 56, 117, 14);
		contentPane.add(lblUser);
		lblUser.setText(user.getUsername());
		
		JLabel lblAdmin = new JLabel("");
		lblAdmin.setBackground(SystemColor.textText);
		lblAdmin.setForeground(SystemColor.textText);
		lblAdmin.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdmin.setBounds(624, 81, 79, 14);
		contentPane.add(lblAdmin);
		if(user.getIsAdmin()) {
			lblAdmin.setText("Admin");
		} else {
			lblAdmin.setText("User");
		}
		
		JButton btnNewButton = new JButton("Cadastrar Funcionario");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(624, 129, 150, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insert insert = new Insert();
				insert.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						listar();
					}
				});
				insert.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Editar Funcionario");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBounds(624, 190, 150, 50);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.getIsAdmin()) {
					if(table.getSelectedRow() >= 0 && table.getSelectedRowCount() == 1) {
						int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
						Update update = new Update(id);
						update.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								listar();
							}
						});
						update.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Selecione um Funcionario !");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Apenas Administradores !");
				}
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Excluir Funcionario");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBounds(624, 251, 150, 50);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.getIsAdmin()) {
					if(table.getSelectedRow() >= 0) {
						int confirm = JOptionPane.showConfirmDialog(null, "Confirmar Deletar Funcionario ?", "Deletar Funcionario", JOptionPane.YES_NO_OPTION);
						if(confirm == 0) {
							int[] rows = table.getSelectionModel().getSelectedIndices();
							Integer[] list = new Integer[rows.length];
							
							for(int i = 0; i < rows.length; i++) {
								list[i] = (Integer) table.getModel().getValueAt(rows[i], 0);
							}
							
							EmployeeDao funcDao = DaoFactory.createEmployeeDao();
							funcDao.deleteById(list);
							listar();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Selecione um Funcionario !");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Apenas Administradores !");
				}
			}
		});
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("User:");
		lblNewLabel.setBackground(SystemColor.textText);
		lblNewLabel.setForeground(SystemColor.textText);
		lblNewLabel.setBounds(624, 56, 42, 14);
		contentPane.add(lblNewLabel);
		
		JPanel closePanel = new JPanel();
		closePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closePanel.setBackground(new Color(255, 0, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closePanel.setBackground(SystemColor.window);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		closePanel.setBackground(SystemColor.window);
		closePanel.setBounds(758, 0, 42, 40);
		contentPane.add(closePanel);
		closePanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.setForeground(SystemColor.textText);
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(15, 8, 12, 24);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		closePanel.add(lblNewLabel_1);;
		
		
		
	}
	
	public void listar() {
		EmployeeDao funcDao = DaoFactory.createEmployeeDao();
		List<Employee> list = funcDao.findAll();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for(Employee func: list) {
			model.addRow(new Object[] {
					func.getId(),
					func.getName(),
					func.getCpf(),
					func.getJob(),
					func.getSalary(),
					func.getPhone()
			});
		}
	}
}
