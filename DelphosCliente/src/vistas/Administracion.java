/*
 * Ventana de administración, en ella el administrador podrá activar a los usuarios, dándoles roles y
 * asignandoles cursos
 */
package vistas;

import constantes.CodigoOrden;
import hilos.HiloGestion;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Curso;
import modelo.Usuario;
import util.Utiles;

/**
 *
 * @author Carlos González
 */
public class Administracion extends javax.swing.JFrame {

	private String valor;
	private static ArrayList<Usuario> listaUsuarios;
	private static ArrayList<Curso> listaCur;
	private int fila;

	static {
		listaUsuarios = new ArrayList<>();
		listaCur = new ArrayList<>();
	}

	public Administracion() {
		initComponents();
		listenerSeleccion();
		this.valor = null;
		this.comboRoles.setVisible(false);
	}

	public static void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		Administracion.listaUsuarios = listaUsuarios;
	}

	public static void setListaCursos(ArrayList<Curso> listaCursos) {
		Administracion.listaCur = listaCursos;
	}
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnActivar = new javax.swing.JButton();
        comboRoles = new javax.swing.JComboBox<>();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaCursos = new javax.swing.JList<>();
        btnAddCurso = new javax.swing.JButton();
        btnModificarCurso = new javax.swing.JButton();
        btnBorrarCurso = new javax.swing.JButton();
        btnVolverCursos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setViewportView(jScrollPane1);

        btnActivar.setText("ACTIVAR");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        comboRoles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin asignar", "Alumno", "Profesor", "Administrador", "Administrador y profesor" }));

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnActivar, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActivar)
                    .addComponent(comboRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Usuarios", jPanel1);

        jScrollPane3.setViewportView(listaCursos);

        btnAddCurso.setText("CREAR");

        btnModificarCurso.setText("MODIFICAR");

        btnBorrarCurso.setText("BORRAR");

        btnVolverCursos.setText("VOLVER");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificarCurso, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addComponent(btnBorrarCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVolverCursos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(19, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAddCurso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificarCurso)
                        .addGap(86, 86, 86)
                        .addComponent(btnBorrarCurso)
                        .addGap(99, 99, 99)
                        .addComponent(btnVolverCursos)
                        .addGap(27, 27, 27))))
        );

        jTabbedPane1.addTab("Cursos", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
		new HiloGestion(CodigoOrden.LISTAR_USUARIOS, jTable1, this).start();
		System.out.println(listaCursos);
		new HiloGestion(CodigoOrden.LISTAR_CURSOS, listaCursos).start();
    }//GEN-LAST:event_formWindowOpened

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
		if (valor == null) {
			Utiles.lanzarMensaje("Debes seleccionar un usuario de la tabla");
		} else {
			String seleccion = comboRoles.getSelectedItem().toString();
			if (seleccion.equals("Sin asignar")) {
				Utiles.lanzarMensaje("Debes seleccionar un rol que asignar al usuario");
			} else {
				listaUsuarios.get(fila).setRol((byte) Utiles.gestionRolInversa(seleccion));
				new HiloGestion(listaUsuarios.get(fila), CodigoOrden.ACTIVAR_USUARIO).start();
			}
		}
    }//GEN-LAST:event_btnActivarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
		Login login = new Login();
		login.setVisible(true);
		this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

	private void listenerSeleccion() {
		ListSelectionModel cell = jTable1.getSelectionModel();
		cell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cell.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				fila = jTable1.getSelectedRow();
				valor = (String) jTable1.getValueAt(fila, 0);
				btnActivar.setEnabled(true);
				comboRoles.setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnAddCurso;
    private javax.swing.JButton btnBorrarCurso;
    private javax.swing.JButton btnModificarCurso;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVolverCursos;
    private javax.swing.JComboBox<String> comboRoles;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JList<String> listaCursos;
    // End of variables declaration//GEN-END:variables
}
