/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Controller.ControllerHomework;
import Controller.ControllerSubject;
import Utilities.SubjectSessionManager;
import Utilities.UserSessionManager;
import Entity.Homework;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author sherr
 */
public class GuiHomework extends javax.swing.JFrame {

    ControllerHomework conHomework = new ControllerHomework();
    private final DefaultTableModel model;

    /**
     * Creates new form GuiHomework
     */
    public GuiHomework() {
        initComponents();
        setLocationRelativeTo(null);

        // Initialize the table model
        model = new DefaultTableModel();

        tabelHomework.setModel(model);

        cmbSubject.setSelectedItem(null);

        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Date");
        model.addColumn("Subject");
        model.addColumn("Note");

        // Inisialisasi combo box
        DefaultComboBoxModel<String> cmbModel = new DefaultComboBoxModel<>();
        cmbSubject.setModel(cmbModel);

        // Mengisi combo box dengan judul mata pelajaran
        ControllerSubject controllerSubject = new ControllerSubject();
        List<String> subjectTitles = controllerSubject.getAllSubjectTitles(UserSessionManager.getCurrentUserId());
        for (String title : subjectTitles) {
            cmbModel.addElement(title);
        }

        getData();
        refreshTable();
    }

    private void getData() {
        DefaultTableModel dtm = (DefaultTableModel) tabelHomework.getModel();
        dtm.setRowCount(0);

        // Get the current user's id_akun
        int userId = UserSessionManager.getCurrentUserId();
        int subjectId = SubjectSessionManager.getCurrentSubjectId();

        List<Homework> listHomework = conHomework.getAllHomeworks(userId, subjectId);

        for (Homework newHomework : listHomework) {
            Object[] data = {
                newHomework.getId_homework(),
                newHomework.getTitle(),
                newHomework.getDate(),
                newHomework.getId_subject(),
                newHomework.getNote()
            };
            dtm.addRow(data);
        }
    }

    private void clearData() {
        txtTitle.setText("");
        dcDate.setCalendar(null);
        cmbSubject.setSelectedItem(null);
        txtNote.setText("");
    }

    // Refresh the table with the latest data
    private void refreshTable() {
        DefaultTableModel dtm = (DefaultTableModel) tabelHomework.getModel();
        dtm.setRowCount(0);

        // Get the current user's id_akun
        int userId = UserSessionManager.getCurrentUserId();
        int subjectId = SubjectSessionManager.getCurrentSubjectId();

        // Retrieve all reminders for the specific user
        List<Homework> homeworks = conHomework.getAllHomeworks(userId, subjectId);

        // Add the reminders to the table model
        for (Homework homework : homeworks) {
            Object[] rowData = {homework.getId_homework(), homework.getTitle(), homework.getDate(), homework.getId_subject(), homework.getNote()};
            dtm.addRow(rowData);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        txtNote = new javax.swing.JTextField();
        cmbSubject = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelHomework = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dcDate = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 0, 255));

        jLabel1.setText("Homework");

        jLabel2.setText("Title");

        jLabel3.setText("Date");

        jLabel4.setText("Subject");

        jLabel5.setText("Note");

        txtTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTitleActionPerformed(evt);
            }
        });

        cmbSubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSave.setText("Save");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });

        btnBack.setText("<-Back");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        tabelHomework.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Title", "Date", "Subject", "Note"
            }
        ));
        tabelHomework.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelHomeworkMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelHomework);

        txtId.setEditable(false);

        jLabel6.setText("ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(txtNote)
                                .addComponent(txtTitle)
                                .addComponent(cmbSubject, 0, 100, Short.MAX_VALUE))
                            .addComponent(dcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(225, 225, 225))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBack)
                    .addComponent(jLabel1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel4)
                        .addGap(11, 11, 11)
                        .addComponent(cmbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnSave)
                        .addContainerGap(177, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTitleActionPerformed

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        // TODO add your handling code here:
        GuiAgenda guiAgenda = new GuiAgenda();
        guiAgenda.setVisible(true);

        dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        // TODO add your handling code here:
        // Disable the button to prevent multiple clicks
        btnSave.setEnabled(false);

        // Collect data from text fields
        String title = txtTitle.getText();
        Date tanggal = dcDate.getDate();
        String note = txtNote.getText();

        // Convert Date to String using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(tanggal);

        // Get the selected subject from the combo box
        String selectedSubject = cmbSubject.getSelectedItem().toString();

        // Get the current user's id_akun
        int id_akun = UserSessionManager.getCurrentUserId();

        // Create an instance of ControllerSubject
        ControllerSubject controllerSubject = new ControllerSubject();

        // Get the subject ID based on the selected subject title
        int id_subject = controllerSubject.getSubjectIdByTitle(id_akun, selectedSubject);

        // Create an Exam object
        Homework homework = new Homework(id_akun, id_subject, title, date, note);

        // Create an instance of ControllerExam
        ControllerHomework controllerHomework = new ControllerHomework();

        // Call the addExam method
        boolean success = controllerHomework.addHomework(homework);

        // Handle success or failure (e.g., show a message to the user)
        if (success) {
            JOptionPane.showMessageDialog(this, "Homework added successfully!");
            // You can add further actions or messages here

            // Refresh the table with the latest data
            refreshTable();

        } else {
            JOptionPane.showMessageDialog(this, "Failed to add homework.", "Error", JOptionPane.ERROR_MESSAGE);
            // Handle failure, show an error message, etc.
        }

        // Enable the button after the operation is complete
        btnSave.setEnabled(true);

        clearData();
    }//GEN-LAST:event_btnSaveMouseClicked

    private void tabelHomeworkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelHomeworkMouseClicked
        // TODO add your handling code here:
        int row = tabelHomework.getSelectedRow();

        TableModel model = tabelHomework.getModel();
        txtId.setText(model.getValueAt(row, 0).toString());
        txtTitle.setText(model.getValueAt(row, 1).toString());
        // Retrieve the date as a String from the table model
        String dateString = model.getValueAt(row, 2).toString();

        try {
            // Parse the String to a java.util.Date
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

            // Set the date in the JDateChooser
            dcDate.setDate(utilDate);
        } catch (ParseException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }

        cmbSubject.setSelectedItem((String) model.getValueAt(row, 3).toString());
        txtNote.setText(model.getValueAt(row, 4).toString());
    }//GEN-LAST:event_tabelHomeworkMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuiHomework.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiHomework.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiHomework.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiHomework.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiHomework().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbSubject;
    private com.toedter.calendar.JDateChooser dcDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelHomework;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
