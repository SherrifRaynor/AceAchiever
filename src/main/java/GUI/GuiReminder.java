/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Controller.ControllerReminder;
import Utilities.UserSessionManager;
import Entity.Reminder;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.TableModel;

/**
 *
 * @author sherr
 */
public class GuiReminder extends javax.swing.JFrame {

    ControllerReminder conReminder = new ControllerReminder();
    private DefaultTableModel model;

    /**
     * Creates new form GuiReminder
     */
    public GuiReminder() {
        initComponents();
        setLocationRelativeTo(null);

        // Initialize the table model
        model = new DefaultTableModel();

        tabelReminder.setModel(model);

        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Date");
        model.addColumn("Note");

        getData();
        refreshTable();
    }

    private void getData() {
        DefaultTableModel dtm = (DefaultTableModel) tabelReminder.getModel();

        dtm.setRowCount(0);

        // Get the current user's id_akun
        int userId = UserSessionManager.getCurrentUserId();

        List<Reminder> listReminder = conReminder.getRemindersForUser(userId);
        String[] data = new String[4];
        for (Reminder newReminder : listReminder) {
            data[0] = Integer.toString(newReminder.getId_reminder());
            data[1] = newReminder.getTitle();
            data[2] = newReminder.getDate();
            data[3] = newReminder.getNote();
            dtm.addRow(data);
        }
    }

    private void clearData() {
        txtTitle.setText("");
        dcDate.setCalendar(null);
        txtNote.setText("");
    }

    // Refresh the table with the latest data
    private void refreshTable() {
        // Clear the existing data in the table
        model.setRowCount(0);

        // Get the current user's id_akun
        int userId = UserSessionManager.getCurrentUserId();

        // Retrieve all reminders for the specific user
        List<Reminder> listReminder = conReminder.getRemindersForUser(userId);

        // Add the reminders to the table model
        for (Reminder reminder : listReminder) {
            Object[] rowData = {reminder.getId_reminder(), reminder.getTitle(), reminder.getDate(), reminder.getNote()};
            model.addRow(rowData);
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
        txtTitle = new javax.swing.JTextField();
        txtNote = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        dcDate = new com.toedter.calendar.JDateChooser();
        txtId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelReminder = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 0, 255));

        jLabel1.setText("Reminder");

        jLabel2.setText("Title");

        jLabel3.setText("Date");

        jLabel5.setText("Note");

        btnUpdate.setText("Update");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnBack.setText("<- Back");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtId.setEditable(false);

        jLabel6.setText("id");

        tabelReminder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Title", "Date", "Note"
            }
        ));
        tabelReminder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelReminderMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelReminder);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnBack))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete))
                            .addComponent(btnUpdate)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcDate, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4))
                                .addComponent(txtTitle)
                                .addComponent(txtNote)))))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnBack)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(dcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave)
                            .addComponent(btnDelete))
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(2, 2, 2)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        int row = tabelReminder.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(btnUpdate, "Choose at least 1 data", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idUpdate = Integer.parseInt(tabelReminder.getModel().getValueAt(row, 0).toString());
        String newTitle = txtTitle.getText();
        Date newDate = dcDate.getDate();
        String newDateStr = null;

        if (newDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            newDateStr = dateFormat.format(newDate);
        }

        String newNote = txtNote.getText();

        Reminder newReminder = new Reminder();
        newReminder.setId_reminder(idUpdate);
        newReminder.setTitle(newTitle);
        newReminder.setDate(newDateStr);
        newReminder.setNote(newNote);

        // Update the reminder using the newReminder object
        boolean updateSuccess = conReminder.updateReminder(newReminder);

        if (updateSuccess) {
            JOptionPane.showMessageDialog(null, "Data has been successfully updated");
            getData();
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        // TODO add your handling code here:
        GuiAgenda guiAgenda = new GuiAgenda();
        guiAgenda.setVisible(true);

        dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        // Disable the button to prevent multiple clicks
        btnSave.setEnabled(false);

        // Collect data from text fields
        String title = txtTitle.getText();
        Date tanggal = dcDate.getDate();
        String note = txtNote.getText();

        // Validate that all required fields are filled
        if (title.isEmpty() || tanggal == null) {
            JOptionPane.showMessageDialog(this, "Title and Date are required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            btnSave.setEnabled(true);
            return;
        }

        // Convert Date to String using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(tanggal);

        // Get the current user's id_akun
        int id_akun = UserSessionManager.getCurrentUserId();

        // Create a new Reminder object
        Reminder newReminder = new Reminder();
        newReminder.setId_akun(id_akun);
        newReminder.setTitle(title);
        newReminder.setDate(date);
        newReminder.setNote(note);

        // Create an instance of ControllerReminder
        ControllerReminder controllerReminder = new ControllerReminder();

        // Call the addReminder method
        boolean success = controllerReminder.addReminder(newReminder);

        // Handle success or failure
        if (success) {
            JOptionPane.showMessageDialog(this, "Reminder added successfully!");
            // You can add further actions or messages here

            // Refresh the table with the latest data
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add reminder.", "Error", JOptionPane.ERROR_MESSAGE);
            // Handle failure, show an error message, etc.
        }

        // Enable the button after the operation is complete
        btnSave.setEnabled(true);

        clearData();
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // TODO add your handling code here:
        int row = tabelReminder.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(btnUpdate, "Choose at least 1 data", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int idDelete = Integer.parseInt(tabelReminder.getModel().getValueAt(row, 0).toString());
        conReminder.deleteReminder(idDelete);
        
        JOptionPane.showMessageDialog(null, "Data has been Deleted");
        
        getData();
        refreshTable();
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tabelReminderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelReminderMouseClicked
        // TODO add your handling code here:
        int row = tabelReminder.getSelectedRow();

        TableModel model = tabelReminder.getModel();
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

        txtNote.setText(model.getValueAt(row, 3).toString());
    }//GEN-LAST:event_tabelReminderMouseClicked

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
            java.util.logging.Logger.getLogger(GuiReminder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiReminder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiReminder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiReminder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiReminder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private com.toedter.calendar.JDateChooser dcDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelReminder;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
