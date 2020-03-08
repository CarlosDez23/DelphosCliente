/*
 * Ventana del alumno, en la que puede seleccionar al profesor y ver la nota que le ha puesto
 */
package vistas;

import constantes.CodigoOrden;
import hilos.HiloGestion;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.Nota;
import modelo.Usuario;

/**
 *
 * @author cgonz
 */
public class VentanaAlumno extends javax.swing.JFrame {

	private int idAlumno;
	private static int idCurso; 
	private static ArrayList<Usuario> listProfesores;
	
	public VentanaAlumno(int idAlumno) {
		initComponents();
		this.idAlumno = idAlumno;
		listenerSeleccionProfesor();
		ocultarPanelNota();		
	}

	public static void setListProfesores(ArrayList<Usuario> listProfesores) {
		VentanaAlumno.listProfesores = listProfesores;
	}

	public static void setIdCurso(int idCurso) {
		VentanaAlumno.idCurso = idCurso;
	}
	

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaProfes = new javax.swing.JList<>();
        pnlNota = new javax.swing.JPanel();
        btnConsultar = new javax.swing.JButton();
        txtNota = new javax.swing.JTextField();
        checkFirma = new javax.swing.JCheckBox();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setViewportView(listaProfes);

        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        txtNota.setEnabled(false);

        checkFirma.setText("Firma correcta");

        javax.swing.GroupLayout pnlNotaLayout = new javax.swing.GroupLayout(pnlNota);
        pnlNota.setLayout(pnlNotaLayout);
        pnlNotaLayout.setHorizontalGroup(
            pnlNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNotaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNotaLayout.createSequentialGroup()
                        .addComponent(btnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlNotaLayout.createSequentialGroup()
                        .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addComponent(checkFirma)
                        .addGap(15, 15, 15))))
        );
        pnlNotaLayout.setVerticalGroup(
            pnlNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNotaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConsultar)
                .addGap(18, 18, 18)
                .addGroup(pnlNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkFirma))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlNota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolver))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
		System.out.println("id "+idAlumno);
		new HiloGestion(CodigoOrden.LISTAR_PROFESORES_ALUMNO, idAlumno, listaProfes).start();
		
    }//GEN-LAST:event_formWindowOpened

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        consultarNota();	
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        new Login().setVisible(true);
		this.dispose(); 
    }//GEN-LAST:event_btnVolverActionPerformed

	private void ocultarPanelNota(){
		pnlNota.setVisible(false);
	}
	
	private void mostrarPanelNota(){
		pnlNota.setVisible(true);
	}
	
	private void listenerSeleccionProfesor() {
		listaProfes.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mostrarPanelNota();
			}
		});
	}
	
	private void consultarNota() {
		int idProfesor = listProfesores.get(listaProfes.getSelectedIndex()).getIdUsuario();
		Nota nota = new Nota();
		nota.setIdAlumno(idAlumno);
		nota.setIdProfesor(idProfesor);
		System.out.println(nota);
		new HiloGestion(CodigoOrden.VER_NOTA, nota, txtNota).start();
	}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JCheckBox checkFirma;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaProfes;
    private javax.swing.JPanel pnlNota;
    private javax.swing.JTextField txtNota;
    // End of variables declaration//GEN-END:variables

	
}
