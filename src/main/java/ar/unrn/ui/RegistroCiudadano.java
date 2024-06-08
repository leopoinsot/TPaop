package ar.unrn.ui;

import ar.unrn.model.Concurso;
import ar.unrn.model.IApiRegistro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class RegistroCiudadano {
	private JPanel contentPane;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblLastName;
	private JTextField txtLastName;
	private JLabel lblId;
	private JTextField txtId;
	private JLabel lblPhone;
	private JTextField txtPhone;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JComboBox<Concurso> comboBox;
	private JButton btnOk;
	private JLabel lblCompetition;
	private IApiRegistro api;
	public RegistroCiudadano(IApiRegistro api) {
		this.api = api;
		var frame = new JFrame("Inscription to Competition");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 451, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		formElements();
		layout();
		frame.setVisible(true);
	}
	private void formElements() {
		lblName = new JLabel("Nombre:");
		txtName = new JTextField();
		txtName.setColumns(10);
		lblLastName = new JLabel("Apellido:");
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		lblId = new JLabel("Dni:");
		txtId = new JTextField();
		txtId.setColumns(10);
		lblPhone = new JLabel("Telefono:");
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		lblEmail = new JLabel("Email:");
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		lblCompetition = new JLabel("Concurso:");

		List<Concurso> listadoConcursos = api.todosLosConcursos();
		llenarComboboxConNombreConcursos(listadoConcursos);

		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOk.setEnabled(false);
				if(comboBox.getSelectedItem() != null){
					var concurso = (Concurso) comboBox.getSelectedItem();
					api.saveInscription(txtName.getText(),txtLastName.getText(), txtPhone.getText(), txtEmail.getText(), concurso.obtenerId());
				}
				btnOk.setEnabled(true);
			}
		});
	}
	private void llenarComboboxConNombreConcursos(List<Concurso> listadoConcursos){ //Llena el combobox con los nombres de los concursos
		JComboBox<Concurso> comboBoxConcursos = new JComboBox<Concurso>();
		for(Concurso concurso : listadoConcursos){
			comboBoxConcursos.addItem(concurso);
		}
		this.comboBox = comboBoxConcursos;
	}

	private void layout() {
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane
								.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(gl_contentPane
										.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(lblLastName).addComponent(lblId)
												.addComponent(lblPhone).addComponent(lblEmail)
												.addComponent(lblName).addComponent(lblCompetition))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
										.addGroup(
												gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING, false)
														.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(txtEmail, GroupLayout.Alignment.TRAILING)
														.addComponent(txtPhone, GroupLayout.Alignment.TRAILING)
														.addComponent(txtId, GroupLayout.Alignment.TRAILING)
														.addComponent(txtLastName, GroupLayout.Alignment.TRAILING)
														.addComponent(txtName, GroupLayout.Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
								.addComponent(btnOk, GroupLayout.Alignment.TRAILING,
										GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(txtName, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblName))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lblLastName).addComponent(txtLastName,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addComponent(lblId).addComponent(
												txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(
												gl_contentPane.createSequentialGroup().addComponent(lblPhone)
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(lblEmail))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(
														gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
																.addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblCompetition))))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnOk)
								.addContainerGap(67, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}


}
