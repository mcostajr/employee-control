package application;

import java.awt.EventQueue;

import view.Login;


/*
* União Metropolitana de Educação e Cultura
* Bacharelado em Sistemas de Informação
* Programação Orientada a Objetos II
* Prof. Pablo Ricardo Roxo Silva
* Mauro Freire Costa Junior, Luan Ventura
*/

public class program {

	private Login frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					program window = new program();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public program() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new Login();
	}

}
