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
import modelo.Alumno;
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

	//Establecemos el modo en el que se va a ejecutar el botón de aceptar
	// 0 --> Será una inserción
	// 1 --> Será una modificación
	//Por defecto lo dejamos a -1, para que no haya problemas
	private int tipoGestion;

	static {
		listaUsuarios = new ArrayList<>();
		listaCur = new ArrayList<>();
	}

	public Administracion() {
		initComponents();
		listenerSeleccion();
		this.valor = null;
		this.comboRoles.setVisible(false);
		this.comboCursos.setVisible(false);
		ocultarElementosGestion();
		this.tipoGestion = -1;
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
        comboCursos = new javax.swing.JComboBox<>();
        btnAsignarCurso = new javax.swing.JButton();
        btnActualizarTabla = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaCursos = new javax.swing.JList<>();
        btnAddCurso = new javax.swing.JButton();
        btnModificarCurso = new javax.swing.JButton();
        btnActualizarListado = new javax.swing.JButton();
        txtCodigoCurso = new javax.swing.JTextField();
        txtNombreCurso = new javax.swing.JTextField();
        btnAceptarGestion = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();

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

        btnAsignarCurso.setText("ASIGNAR CURSO");
        btnAsignarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarCursoActionPerformed(evt);
            }
        });

        btnActualizarTabla.setText("ACTUALIZAR TABLA");
        btnActualizarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTablaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnActivar, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                                .addComponent(btnAsignarCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboCursos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboRoles, 0, 349, Short.MAX_VALUE)
                            .addComponent(btnActualizarTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActivar)
                    .addComponent(comboRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAsignarCurso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnActualizarTabla))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Usuarios", jPanel1);

        jScrollPane3.setViewportView(listaCursos);

        btnAddCurso.setText("CREAR");
        btnAddCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCursoActionPerformed(evt);
            }
        });

        btnModificarCurso.setText("MODIFICAR");
        btnModificarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCursoActionPerformed(evt);
            }
        });

        btnActualizarListado.setText("ACTUALIZAR LISTADO");
        btnActualizarListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarListadoActionPerformed(evt);
            }
        });

        txtCodigoCurso.setText("Codigo del curso");
        txtCodigoCurso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoCursoFocusGained(evt);
            }
        });

        txtNombreCurso.setText("Nombre del curso");
        txtNombreCurso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreCursoFocusGained(evt);
            }
        });

        btnAceptarGestion.setText("ACEPTAR");
        btnAceptarGestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarGestionActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTitulo.setText("Gestión de cursos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtNombreCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodigoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAceptarGestion, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnActualizarListado, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddCurso)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnActualizarListado)
                            .addComponent(btnModificarCurso)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(35, 35, 35)
                        .addComponent(txtCodigoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txtNombreCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnAceptarGestion)))
                .addContainerGap(31, Short.MAX_VALUE))
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
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
		new HiloGestion(CodigoOrden.LISTAR_USUARIOS, jTable1, this).start();
		System.out.println(listaCursos);
		new HiloGestion(CodigoOrden.LISTAR_CURSOS, listaCursos, comboCursos).start();
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

    private void btnAddCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCursoActionPerformed
		mostrarElementosGestion();
		tipoGestion = 0;
    }//GEN-LAST:event_btnAddCursoActionPerformed

    private void btnModificarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCursoActionPerformed
		if (listaCursos.getSelectedIndex() == -1) {
			Utiles.lanzarMensaje("Debes seleccionar un curso para modificarlo");
		} else {
			mostrarElementosGestion();
			tipoGestion = 1;
		}
    }//GEN-LAST:event_btnModificarCursoActionPerformed

    private void btnAceptarGestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarGestionActionPerformed
		if (txtCodigoCurso.getText().isEmpty() || txtNombreCurso.getText().isEmpty()) {
			Utiles.lanzarMensaje("Debes rellenar todos los campos del formulario");
		} else {
			if (tipoGestion == 0) {
				insertarCurso(txtCodigoCurso.getText(), txtNombreCurso.getText());
			} else {
				modificarCurso(txtCodigoCurso.getText(), txtNombreCurso.getText());
			}
		}
    }//GEN-LAST:event_btnAceptarGestionActionPerformed

    private void btnActualizarListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarListadoActionPerformed
		new HiloGestion(CodigoOrden.LISTAR_CURSOS, listaCursos, comboCursos).start();
    }//GEN-LAST:event_btnActualizarListadoActionPerformed

    private void txtCodigoCursoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoCursoFocusGained
        txtCodigoCurso.setText("");
    }//GEN-LAST:event_txtCodigoCursoFocusGained

    private void txtNombreCursoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreCursoFocusGained
       txtNombreCurso.setText("");
    }//GEN-LAST:event_txtNombreCursoFocusGained

    private void btnAsignarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarCursoActionPerformed
		if (valor == null) {
			Utiles.lanzarMensaje("Debes seleccionar un usuario para asignarle un curso");
		}else{
			asignarCurso();
		}
    }//GEN-LAST:event_btnAsignarCursoActionPerformed

    private void btnActualizarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTablaActionPerformed
        new HiloGestion(CodigoOrden.LISTAR_USUARIOS, jTable1, this).start();
    }//GEN-LAST:event_btnActualizarTablaActionPerformed

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
				comboCursos.setVisible(true);
			}
		});
	}

	private void mostrarElementosGestion() {
		this.txtCodigoCurso.setVisible(true);
		this.txtNombreCurso.setVisible(true);
		this.lblTitulo.setVisible(true);
		this.btnAceptarGestion.setVisible(true);
	}
	
	private void ocultarElementosGestion(){
		this.txtCodigoCurso.setVisible(false);
		this.txtNombreCurso.setVisible(false);
		this.lblTitulo.setVisible(false);
		this.btnAceptarGestion.setVisible(false);	
	}
	private void insertarCurso(String codigo, String nombre) {
		Curso curso = new Curso(codigo, nombre);
		new HiloGestion(curso, CodigoOrden.ADD_CURSO).start();
		ocultarElementosGestion();
		restaurarCamposDeTexto();
	}

	private void modificarCurso(String codigo, String nombre) {
		Curso curso = new Curso(codigo, nombre);
		//El índice en la jlist se corresponde con el indice de nuestra lista de cursos
		int idCurso = listaCur.get(listaCursos.getSelectedIndex()).getIdCurso();
		curso.setIdCurso(idCurso);
		new HiloGestion(curso, CodigoOrden.EDITAR_CURSO).start();
		ocultarElementosGestion();
		restaurarCamposDeTexto();
	}
	
	private void asignarCurso(){
		int idCurso = listaCur.get(comboCursos.getSelectedIndex()).getIdCurso();
		Usuario aux = (Usuario)listaUsuarios.get(fila);
		if (aux.getRol() == 2) {
			new HiloGestion(aux, CodigoOrden.ASIGNAR_PROFESOR, idCurso).start();
		}else{
			Alumno alumno = new Alumno();
			alumno.setIdUsuario(aux.getIdUsuario());
			alumno.setIdCurso(idCurso);
			new HiloGestion(alumno, CodigoOrden.ELEGIR_CURSO).start();
		}
	}
	
	private void restaurarCamposDeTexto(){
		txtCodigoCurso.setText("Código del curso");
		txtNombreCurso.setText("Nombre del curso");
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarGestion;
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnActualizarListado;
    private javax.swing.JButton btnActualizarTabla;
    private javax.swing.JButton btnAddCurso;
    private javax.swing.JButton btnAsignarCurso;
    private javax.swing.JButton btnModificarCurso;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> comboCursos;
    private javax.swing.JComboBox<String> comboRoles;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> listaCursos;
    private javax.swing.JTextField txtCodigoCurso;
    private javax.swing.JTextField txtNombreCurso;
    // End of variables declaration//GEN-END:variables
}
