/*
 * Ventana del profesor
 */
package vistas;

import constantes.CodigoOrden;
import hilos.HiloGestion;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.Alumno;
import modelo.Curso;
import modelo.Nota;
import util.Utiles;

/**
 *
 * @author Carlos González
 */
public class VentanaProfesor extends javax.swing.JFrame {

	private static ArrayList<Curso> listCur;
	private static ArrayList<Alumno> listAlu;
	private int idProfesor;

	static {
		listCur = new ArrayList<>();
		listAlu = new ArrayList<>();
	}

	public VentanaProfesor(int idProfesor) {
		initComponents();
		this.idProfesor = idProfesor;
		listenerSeleccionListaCursos();
		ocultarListaAlumnos();
		ocultarPanelCalificacion();
	}

	public static void setListCur(ArrayList<Curso> listCur) {
		VentanaProfesor.listCur = listCur;
	}

	public static void setListAlu(ArrayList<Alumno> listAlu) {
		VentanaProfesor.listAlu = listAlu;
	}
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listCursos = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaAlumnos = new javax.swing.JList<>();
        btnCalificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pnlCalificacion = new javax.swing.JPanel();
        txtNota = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setViewportView(listCursos);

        jScrollPane2.setViewportView(listaAlumnos);

        btnCalificar.setText("CALIFICAR");
        btnCalificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalificarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Mis cursos");

        txtNota.setText("Nota");
        txtNota.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNotaFocusGained(evt);
            }
        });

        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCalificacionLayout = new javax.swing.GroupLayout(pnlCalificacion);
        pnlCalificacion.setLayout(pnlCalificacionLayout);
        pnlCalificacionLayout.setHorizontalGroup(
            pnlCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCalificacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCalificacionLayout.setVerticalGroup(
            pnlCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCalificacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(354, 354, 354)
                        .addComponent(pnlCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                            .addComponent(btnCalificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCalificar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
		new HiloGestion(CodigoOrden.LISTAR_CURSOS_PROFESOR, idProfesor, listCursos).start();
    }//GEN-LAST:event_formWindowOpened

    private void btnCalificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalificarActionPerformed
        if (listaAlumnos.getSelectedIndex() == -1) {
			Utiles.lanzarMensaje("Debes seleccionar un alumno para calificarlo");
		}else{
			mostrarPanelCalificacion();
		}
    }//GEN-LAST:event_btnCalificarActionPerformed

    private void txtNotaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNotaFocusGained
        txtNota.setText("");
    }//GEN-LAST:event_txtNotaFocusGained

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
		if (txtNota.getText().isEmpty()) {
			Utiles.lanzarMensaje("Debes introducir una nota");
		}else{
			float nota = Float.parseFloat(txtNota.getText().replace(",", "."));
			if (nota < 0 || nota > 10) {
				Utiles.lanzarMensaje("Fallo en la nota");
			}else{
				Nota aux = new Nota();
				aux.setIdAlumno(listAlu.get(listaAlumnos.getSelectedIndex()).getIdUsuario());
				aux.setIdProfesor(idProfesor);
				aux.setNota(nota);
				new HiloGestion(aux, CodigoOrden.PONER_NOTA).start();
				ocultarPanelCalificacion();
			}
		}
    }//GEN-LAST:event_btnAceptarActionPerformed

	private void listenerSeleccionListaCursos() {
		listCursos.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mostrarCursosSeleccion(listCur.get(listCursos.getSelectedIndex()).getIdCurso());
			}
		});
	}
	
	private void mostrarCursosSeleccion(int seleccionado){
		mostrarListaAlumnos();
		new HiloGestion(CodigoOrden.LISTAR_ALUMNOS_CURSO, seleccionado, listaAlumnos).start();	
	}
	
	private void mostrarListaAlumnos(){
		listaAlumnos.setVisible(true);
		btnCalificar.setVisible(true);	
		
	}
	
	private void ocultarListaAlumnos(){
		listaAlumnos.setVisible(false);
		btnCalificar.setVisible(false);
	}
	
	private void mostrarPanelCalificacion(){
		pnlCalificacion.setVisible(true);
	}
	
	private void ocultarPanelCalificacion(){
		pnlCalificacion.setVisible(false);
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCalificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listCursos;
    private javax.swing.JList<String> listaAlumnos;
    private javax.swing.JPanel pnlCalificacion;
    private javax.swing.JTextField txtNota;
    // End of variables declaration//GEN-END:variables
}
