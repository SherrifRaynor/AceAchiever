/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Controller.ControllerTeacher;
import Entity.Teacher;
import Utilities.UserSessionManager;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.util.Base64;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author sherr
 */
public class GuiTeacher extends javax.swing.JFrame {

    ControllerTeacher conTeacher = new ControllerTeacher();
    private ImageIcon selectedImageIcon;

    private DefaultTableModel model;

    /**
     * Creates new form GuiTeacher
     */
    public GuiTeacher() {
        initComponents();
        setLocationRelativeTo(null);
        txtImagePath.setVisible(false);

        

        // Initialize the table model
        model = (DefaultTableModel) tabelTeacher.getModel();

        tabelTeacher.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelTeacher.setRowHeight(100);
        

        

        getData();
        refreshTable();
        
        
        
        // Add FocusListener to clear the text field when it gains focus
        txtName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String currentText = txtName.getText();
                if (currentText.equals("Name") || currentText.isEmpty()) {
                    txtName.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String currentText = txtName.getText();
                if (currentText.isEmpty()) {
                    txtName.setText("Name");
                }
            }
        });
        
        txtPhone.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String currentText = txtPhone.getText();
                if (currentText.equals("Phone") || currentText.isEmpty()) {
                    txtPhone.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String currentText = txtPhone.getText();
                if (currentText.isEmpty()) {
                    txtPhone.setText("Phone");
                }
            }

        });
        
        txtEmail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String currentText = txtEmail.getText();
                if (currentText.equals("Email") || currentText.isEmpty()) {
                    txtEmail.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String currentText = txtEmail.getText();
                if (currentText.isEmpty()) {
                    txtEmail.setText("Email");
                }
            }

        });
        
        txtAddress.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String currentText = txtAddress.getText();
                if (currentText.equals("Address") || currentText.isEmpty()) {
                    txtAddress.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String currentText = txtAddress.getText();
                if (currentText.isEmpty()) {
                    txtAddress.setText("Address");
                }
            }

        });

    }

    private void clearData() {
        txtId.setText("");
        txtName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        lblImage.setIcon(null);
        txtImagePath.setText("");
        teacherIcon = null;
        teacherImage = null;
    }

    private void getData() {
        DefaultTableModel dtm = (DefaultTableModel) tabelTeacher.getModel();

        dtm.setRowCount(0);

        // Get the current user's id_akun
        int userId = UserSessionManager.getCurrentUserId();

        List<Teacher> listTeacher = conTeacher.getAllTeachers(userId);
        String[] data = new String[7]; //karena ada 6 data
        for (Teacher newTeacher : listTeacher) {
            data[0] = Integer.toString(newTeacher.getId_teacher());
            data[1] = newTeacher.getName();
            data[2] = newTeacher.getPhone();
            data[3] = newTeacher.getEmail();
            data[4] = newTeacher.getAddress();

            if (newTeacher.getImage() != null) {
                byte[] imageBytes = newTeacher.getImage();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                data[5] = base64Image;
            } else {
                data[5] = null;
            }

            dtm.addRow(data);
        }

    }

    private void refreshTable() {
        model.setRowCount(0);

        // Get the current user's id_akun
        int userId = UserSessionManager.getCurrentUserId();

        List<Teacher> listTeacher = conTeacher.getAllTeachers(userId);

        for (Teacher teacher : listTeacher) {
            Object[] rowData = {teacher.getId_teacher(), teacher.getName(), teacher.getPhone(), teacher.getEmail(), teacher.getAddress(), teacher.getImage()};
            model.addRow(rowData);
        }

        int imageColumnIndex = 5;
        TableColumn imageColumn = tabelTeacher.getColumnModel().getColumn(imageColumnIndex);
        imageColumn.setCellRenderer(new ImageRenderer());

        // Adjust column widths
        int[] columnWidths = {50, 150, 100, 150, 150, 200}; // Adjust the widths as needed

        for (int i = 0; i < model.getColumnCount(); i++) {
            TableColumn column = tabelTeacher.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }
    }

    class ImageRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel();

            // Assuming the image data is stored as a byte array
            byte[] imageData = (byte[]) value;

            if (imageData != null) {
                // Convert the byte array to an ImageIcon
                ImageIcon icon = new ImageIcon(imageData);

                // Scale the image to fit the cell size
                Image scaledImage = icon.getImage().getScaledInstance(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight(), Image.SCALE_SMOOTH);

                // Create a new ImageIcon with the scaled image
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                // Set the scaled ImageIcon to the JLabel
                label.setIcon(scaledIcon);
            }

            return label;
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
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        lblImage = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnUpload = new javax.swing.JButton();
        txtImagePath = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTeacher = new javax.swing.JTable();
        lblBack = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        lblUpdate = new javax.swing.JLabel();
        lblDelete = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        lblClear = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(126, 123, 158));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        jLabel1.setText("Teacher");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel2.setText("ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\Semester 3\\Projek UAS OOP\\icons library\\Icon Pack User Interface (Flat Gradient)\\teacher 24px ( UICONS).png")); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, 30));

        jLabel4.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 180, -1, -1));

        jLabel5.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon("D:\\Semester 3\\Projek UAS OOP\\icons library\\Icon Pack User Interface (Flat Gradient)\\email 24px (Basic Rounded Lineal).png")); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1, -1));

        jLabel6.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon("D:\\Semester 3\\Projek UAS OOP\\icons library\\Icon Pack User Interface (Flat Gradient)\\address 24px(Vitaliy Gorbachev Lineal).png")); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, -1, -1));

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(126, 123, 158));
        txtId.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 50, -1));

        txtName.setBackground(new java.awt.Color(126, 123, 158));
        txtName.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        txtName.setText("Name");
        txtName.setBorder(null);
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 131, 30));

        txtPhone.setBackground(new java.awt.Color(126, 123, 158));
        txtPhone.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        txtPhone.setText("Phone");
        txtPhone.setBorder(null);
        txtPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneActionPerformed(evt);
            }
        });
        jPanel1.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 131, 30));

        txtEmail.setBackground(new java.awt.Color(126, 123, 158));
        txtEmail.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        txtEmail.setText("Email");
        txtEmail.setBorder(null);
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 131, 30));

        txtAddress.setBackground(new java.awt.Color(126, 123, 158));
        txtAddress.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        txtAddress.setText("Address");
        txtAddress.setBorder(null);
        jPanel1.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 131, 30));

        lblImage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 177, 236));

        btnSave.setBackground(new java.awt.Color(56, 63, 104));
        btnSave.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(0, 0, 0));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, 30));

        btnUpload.setBackground(new java.awt.Color(56, 63, 104));
        btnUpload.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        btnUpload.setIcon(new javax.swing.ImageIcon("D:\\Semester 3\\Projek UAS OOP\\icons library\\Icon Pack User Interface (Flat Gradient)\\image ( Graphic style).png")); // NOI18N
        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpload, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));

        txtImagePath.setEditable(false);
        jPanel1.add(txtImagePath, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 177, -1));

        tabelTeacher.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        tabelTeacher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Phone", "Email", "Address", "Photo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelTeacherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelTeacher);
        if (tabelTeacher.getColumnModel().getColumnCount() > 0) {
            tabelTeacher.getColumnModel().getColumn(0).setResizable(false);
            tabelTeacher.getColumnModel().getColumn(0).setPreferredWidth(1);
            tabelTeacher.getColumnModel().getColumn(2).setPreferredWidth(60);
            tabelTeacher.getColumnModel().getColumn(3).setPreferredWidth(60);
            tabelTeacher.getColumnModel().getColumn(4).setPreferredWidth(60);
            tabelTeacher.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 417, 688, 333));

        lblBack.setIcon(new javax.swing.ImageIcon("D:\\Semester 3\\Projek UAS OOP\\icons library\\Icon Pack User Interface (Flat Gradient)\\back icon (bharat icons basic).png")); // NOI18N
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
        });
        jPanel1.add(lblBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 15, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(250, 236, 226));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 430, 10));

        jSeparator2.setForeground(new java.awt.Color(250, 236, 226));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 370, 10));

        jSeparator3.setForeground(new java.awt.Color(250, 236, 226));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 370, 10));

        jSeparator4.setForeground(new java.awt.Color(250, 236, 226));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 370, 10));

        lblUpdate.setIcon(new javax.swing.ImageIcon("D:\\Semester 3\\Projek UAS OOP\\icons library\\Icon Pack User Interface (Flat Gradient)\\pencil 24px(special flat).png")); // NOI18N
        lblUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUpdateMouseClicked(evt);
            }
        });
        jPanel1.add(lblUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 370, -1, -1));

        lblDelete.setIcon(new javax.swing.ImageIcon("D:\\Semester 3\\Projek UAS OOP\\icons library\\Icon Pack User Interface (Flat Gradient)\\trash 24px( bqlqn lineal).png")); // NOI18N
        lblDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDeleteMouseClicked(evt);
            }
        });
        jPanel1.add(lblDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 370, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon("D:\\Semester 3\\Projek UAS OOP\\icons library\\Icon Pack User Interface (Flat Gradient)\\phone 24px (Slidicon Detailed Outline).png")); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, -1, -1));

        jSeparator5.setForeground(new java.awt.Color(250, 236, 226));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 370, 10));

        jSeparator6.setForeground(new java.awt.Color(250, 236, 226));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 370, 10));

        lblClear.setBackground(new java.awt.Color(126, 123, 158));
        lblClear.setForeground(new java.awt.Color(0, 0, 0));
        lblClear.setIcon(new javax.swing.ImageIcon("D:\\Semester 3\\Projek UAS OOP\\icons library\\Icon Pack User Interface (Flat Gradient)\\Clear 24px.png")); // NOI18N
        lblClear.setText("Clear Field");
        lblClear.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblClearMouseClicked(evt);
            }
        });
        jPanel1.add(lblClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 90, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        // Get the values from the text fields
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();

        // Get the current user's id_akun
        int id_akun = UserSessionManager.getCurrentUserId();

        ControllerTeacher controllerTeacher = new ControllerTeacher();

        Teacher teacher = new Teacher();
        teacher.setId_akun(id_akun);
        teacher.setName(name);
        teacher.setPhone(phone);
        teacher.setEmail(email);
        teacher.setAddress(address);
        teacher.setImage(teacherImage);

        // Save the Teacher object to the database
        boolean success = controllerTeacher.addTeacher(teacher);

        if (success) {
            JOptionPane.showMessageDialog(this, "Teacher information saved successfully!");
            // Clear the form or perform any other necessary actions

            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Error saving teacher information. Please try again.");
        }
        clearData();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed

        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        namaFilePhoto = file.getAbsolutePath();
        txtImagePath.setText(namaFilePhoto);

        try {
            File imageFile = new File(namaFilePhoto);

            BufferedImage originalImage = ImageIO.read(imageFile);

            Image scaledImage = originalImage.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

            lblImage.setIcon(scaledImageIcon);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpeg", bos);
            teacherImage = bos.toByteArray();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnUploadActionPerformed

    private void tabelTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTeacherMouseClicked
        // TODO add your handling code here:
        int row = tabelTeacher.getSelectedRow();

        TableModel model = tabelTeacher.getModel();
        txtId.setText(model.getValueAt(row, 0).toString());
        txtName.setText(model.getValueAt(row, 1).toString());
        txtPhone.setText(model.getValueAt(row, 2).toString());
        txtEmail.setText(model.getValueAt(row, 3).toString());
        txtAddress.setText(model.getValueAt(row, 4).toString());

        Object imageDataObj = model.getValueAt(row, 5);

        if (imageDataObj != null && imageDataObj instanceof byte[]) {
            byte[] imageData = (byte[]) imageDataObj;

            // Convert the byte array to an ImageIcon
            ImageIcon imageIcon = new ImageIcon(imageData);

            // Scale the image to fit lblImage
            Image scaledImage = imageIcon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);

            // Create a new ImageIcon with the scaled image
            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

            // Set the ImageIcon to lblImage
            lblImage.setIcon(scaledImageIcon);
        } else {
            // Handle the case where there is no image
            System.out.println("No image");
            lblImage.setIcon(null); // Set lblImage to null or another default image
        }

    }//GEN-LAST:event_tabelTeacherMouseClicked

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        // TODO add your handling code here:
        GuiMainMenu guiMainMenu = new GuiMainMenu();
        guiMainMenu.setVisible(true);

        dispose();
    }//GEN-LAST:event_lblBackMouseClicked

    private void lblUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdateMouseClicked
        // TODO add your handling code here:
        int row = tabelTeacher.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(lblUpdate, "Choose at least 1 data", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idUpdate = Integer.parseInt(tabelTeacher.getModel().getValueAt(row, 0).toString());
        String newName = txtName.getText();
        String newPhone = txtPhone.getText();
        String newEmail = txtEmail.getText();
        String newAddress = txtAddress.getText();

        if (namaFilePhoto != null) {
            try {
                File newImageFile = new File(namaFilePhoto);

                BufferedImage newImage = ImageIO.read(newImageFile);

                Image scaledNewImage = newImage.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);

                ImageIcon scaledNewImageIcon = new ImageIcon(scaledNewImage);

                lblImage.setIcon(scaledNewImageIcon);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(newImage, "jpeg", bos);
                teacherImage = bos.toByteArray();

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        Teacher newTeacher = new Teacher();
        newTeacher.setId_teacher(idUpdate);
        newTeacher.setName(newName);
        newTeacher.setPhone(newPhone);
        newTeacher.setEmail(newEmail);
        newTeacher.setAddress(newAddress);
        newTeacher.setImage(teacherImage);

        boolean success = conTeacher.updateTeacher(newTeacher);

        if (success) {
            JOptionPane.showMessageDialog(null, "Data has been successfully updated");
            getData();
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lblUpdateMouseClicked

    private void lblDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDeleteMouseClicked
        // TODO add your handling code here:
        int row = tabelTeacher.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(lblUpdate, "Choose at least 1 data", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idDelete = Integer.parseInt(tabelTeacher.getModel().getValueAt(row, 0).toString());
        conTeacher.deleteTeacher(idDelete);

        JOptionPane.showMessageDialog(null, "Data has been Deleted");

        getData();
        refreshTable();
    }//GEN-LAST:event_lblDeleteMouseClicked

    private void lblClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblClearMouseClicked
        // TODO add your handling code here:
        clearData();
    }//GEN-LAST:event_lblClearMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuiTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiTeacher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpload;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblClear;
    private javax.swing.JLabel lblDelete;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblUpdate;
    private javax.swing.JTable tabelTeacher;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtImagePath;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
    String namaFilePhoto = null;
    private ImageIcon teacherIcon = null;
    byte[] teacherImage = null;
}
