/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADMIN;

import DAOs.GenreDAO;
import DAOs.MovieDAO;
import DAOs.staffDAO;
import DTOs.GenreDTO;
import DTOs.MovieDTO;
import DTOs.staffDTO;
import com.sun.prism.paint.Color;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author So Kai Con
 */
public class Admin extends javax.swing.JFrame {

    DefaultTableModel tblStaff = null;
    DefaultTableModel tblMovie = null;

    public Admin() {
        initComponents();
        txtMovieID.setEditable(false);
        tblStaff = (DefaultTableModel) tblStaffView.getModel();
        tblMovie = (DefaultTableModel) tblMovieView.getModel();

        //Combo Box
        GenreDAO gdao = new GenreDAO();
        try {
            List<GenreDTO> list = gdao.getAllGenre();
            list.forEach((genreDTO) -> {
                cbbMovieGenre.addItem(genreDTO.toString());
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private void resetStaffFieldAfterInsertOrUpdate()
    {
        txtStaffID.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
        txtStaffFullName.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
        txtStaffAge.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
        txtStaffPhone.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
        txtStaffSex.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
        labelStaffValid.setText("");
    }
    
    private void resetMovieFieldAfterInsertOrUpdate()
    {
        txtMovieName.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
        txtMovieLength.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
        labelMovieValid.setText("");
    }
    
    private boolean staffValid() {

        String usernamesValid = txtStaffID.getText().trim();
        String password = new String(txtStaffPassword.getPassword());
        String fullnameValid = txtStaffFullName.getText().trim();
        String phoneValid = txtStaffPhone.getText().trim();
        String ageValid = txtStaffAge.getText().trim();
        String sexValid = txtStaffSex.getText().trim();

        if (!usernamesValid.matches("^[a-zA-Z\\d]+$")) {
            labelStaffValid.setText("Username is required and can be have whitespace");
            txtStaffID.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
            return false;
        }
        else
        {
            txtStaffID.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
        }
        
        if (password.length()==0)
        {
            labelStaffValid.setText("Password can't be blank");
            txtStaffPassword.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
            return false;
        }
        else
        {
            txtStaffPassword.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
        }
        
        if (!fullnameValid.matches("^[a-zA-Z\\s]+$")) {
            labelStaffValid.setText("Fullname can't be blank and can be have digits");
            txtStaffFullName.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
            return false;
        }
        else
        {
            txtStaffFullName.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
        }
            

        if (!phoneValid.matches("^\\d{10,11}")) {
            labelStaffValid.setText("Phone Number must be 10 or 11 digits");
            txtStaffPhone.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
            return false;
        }
        else
        {
            txtStaffPhone.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
        }

        if (!ageValid.matches("^\\d+$")) {
            labelStaffValid.setText("Age must be an integer");
            txtStaffAge.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
            return false;
        } else {
            int n = Integer.parseInt(txtStaffAge.getText());
            if (n < 18 || n > 35) {
                labelStaffValid.setText("Age must between 18 and 30");
                txtStaffAge.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
                return false;
            }
            else
            {
                txtStaffAge.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
            }
        }
        

        if (!sexValid.equalsIgnoreCase("male") && !sexValid.equalsIgnoreCase("female")) {
            labelStaffValid.setText("Sex must be 'Male' or 'Female'");
            txtStaffSex.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
            return false;
        }
        else
        {
            txtStaffSex.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
        }
        return true;
    }
    
    private boolean movieValid()
    {
        String name = txtMovieName.getText().trim();
        String movieLength = txtMovieLength.getText().trim();
        
        if (!name.matches("^[a-zA-Z\\s]+"))
        {
           labelMovieValid.setText("Name can't blank and don't have digit");
           txtMovieName.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
           return false;
        }
        else
        {
            txtMovieName.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
        }
        
        if (!movieLength.matches("^\\d{2,3}"))
        {
            labelMovieValid.setText("Length can't be have character");
            txtMovieLength.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
            return false;
        }
        else
        {
            int n = Integer.parseInt(movieLength);
            if (n <= 0 || n > 200)
            {
                labelMovieValid.setText("Length must larger than 0 and smaller than 200");
                txtMovieLength.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
                return false;
            }
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStaffView = new javax.swing.JTable();
        lbSearching = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMovieID = new javax.swing.JTextField();
        txtMovieName = new javax.swing.JTextField();
        txtMovieLength = new javax.swing.JTextField();
        btnMovieNew = new javax.swing.JButton();
        btnMoveInsert = new javax.swing.JButton();
        btnMovieDelete = new javax.swing.JButton();
        btnMovieUpdate = new javax.swing.JButton();
        btnMovieSearch = new javax.swing.JButton();
        cbbMovieGenre = new javax.swing.JComboBox<>();
        labelMovieValid = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtStaffID = new javax.swing.JTextField();
        txtStaffFullName = new javax.swing.JTextField();
        txtStaffAge = new javax.swing.JTextField();
        txtStaffPhone = new javax.swing.JTextField();
        txtStaffSex = new javax.swing.JTextField();
        btnStaffNew = new javax.swing.JButton();
        btnStaffInsert = new javax.swing.JButton();
        btnStaffDelete = new javax.swing.JButton();
        btnStaffUpdate = new javax.swing.JButton();
        btnStaffSearch = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtStaffPassword = new javax.swing.JPasswordField();
        labelStaffValid = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMovieView = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "STAFF", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tblStaffView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "FullName", "Age"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblStaffView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStaffViewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStaffView);

        lbSearching.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lbSearching.setForeground(new java.awt.Color(51, 0, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSearching, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSearching, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movie Details:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setText("Genre:");

        jLabel2.setText("MovieID:");

        jLabel3.setText("Name:");

        jLabel5.setText("Length:");

        txtMovieLength.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        btnMovieNew.setText("New");
        btnMovieNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovieNewActionPerformed(evt);
            }
        });

        btnMoveInsert.setText("Insert");
        btnMoveInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveInsertActionPerformed(evt);
            }
        });

        btnMovieDelete.setText("Delete");
        btnMovieDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovieDeleteActionPerformed(evt);
            }
        });

        btnMovieUpdate.setText("Update");
        btnMovieUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovieUpdateActionPerformed(evt);
            }
        });

        btnMovieSearch.setText("Search");
        btnMovieSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovieSearchActionPerformed(evt);
            }
        });

        cbbMovieGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMovieGenreActionPerformed(evt);
            }
        });

        labelMovieValid.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btnMovieNew, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMoveInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMovieUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnMovieDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMovieID)
                            .addComponent(txtMovieLength)
                            .addComponent(txtMovieName)
                            .addComponent(cbbMovieGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(btnMovieSearch))
                    .addComponent(labelMovieValid, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(labelMovieValid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMovieID, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMovieName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMovieSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMovieGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMovieLength, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMovieDelete)
                    .addComponent(btnMovieUpdate)
                    .addComponent(btnMoveInsert)
                    .addComponent(btnMovieNew))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Staff Details:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel6.setText("Phone:");

        jLabel7.setText("Username:");

        jLabel8.setText("FullName:");

        jLabel9.setText("Sex:");

        jLabel10.setText("Age:");

        btnStaffNew.setText("New");
        btnStaffNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffNewActionPerformed(evt);
            }
        });

        btnStaffInsert.setText("Insert");
        btnStaffInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffInsertActionPerformed(evt);
            }
        });

        btnStaffDelete.setText("Delete");
        btnStaffDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffDeleteActionPerformed(evt);
            }
        });

        btnStaffUpdate.setText("Update");
        btnStaffUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffUpdateActionPerformed(evt);
            }
        });

        btnStaffSearch.setText("Search");
        btnStaffSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffSearchActionPerformed(evt);
            }
        });

        jLabel11.setText("Password:");

        labelStaffValid.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStaffNew, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(8, 8, 8))))
                .addGap(18, 18, 18)
                .addComponent(btnStaffInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnStaffUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnStaffDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtStaffPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtStaffPhone)
                                .addComponent(txtStaffID)
                                .addComponent(txtStaffSex)
                                .addComponent(txtStaffAge)
                                .addComponent(txtStaffFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnStaffSearch)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(labelStaffValid, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(labelStaffValid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtStaffID, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtStaffPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStaffFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStaffSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStaffPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStaffAge, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStaffSex, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStaffDelete)
                    .addComponent(btnStaffUpdate)
                    .addComponent(btnStaffInsert)
                    .addComponent(btnStaffNew))
                .addGap(26, 26, 26))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MOVIES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tblMovieView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Length"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblMovieView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMovieViewMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblMovieView);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStaffSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffSearchActionPerformed
        resetStaffFieldAfterInsertOrUpdate();
        String search = txtStaffFullName.getText();
        staffDAO sDAO = new staffDAO();

        try {
            List<staffDTO> result = (List<staffDTO>) sDAO.findByStaffName(search);
            showStaff(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnStaffSearchActionPerformed

    private void tblStaffViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStaffViewMouseClicked
        resetStaffFieldAfterInsertOrUpdate();
        int row = tblStaffView.getSelectedRow();
        String username = (String) tblStaff.getValueAt(row, 0);
        
        try {
            staffDAO sdao = new staffDAO();
            staffDTO sdto = sdao.findByPrimaryKey(username);

            txtStaffID.setText(sdto.getUsername());
            txtStaffFullName.setText(sdto.getFullname());
            txtStaffAge.setText(sdto.getAge() + "");
            txtStaffPhone.setText(sdto.getPhone());
            txtStaffSex.setText(sdto.getSex());
            txtStaffPassword.setText("");
            txtStaffID.setEditable(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_tblStaffViewMouseClicked

    private void btnStaffNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffNewActionPerformed
        txtStaffID.setText("");
        txtStaffPassword.setText("");
        txtStaffFullName.setText("");
        txtStaffAge.setText("");
        txtStaffPhone.setText(" ");
        txtStaffSex.setText("");
        txtStaffID.setEditable(true);
        resetStaffFieldAfterInsertOrUpdate();
    }//GEN-LAST:event_btnStaffNewActionPerformed

    private void btnStaffUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffUpdateActionPerformed
        if (!staffValid()) {
            return;
        }
        try {
            int row = tblStaffView.getSelectedRow();
            String username = txtStaffID.getText().trim();
            String password = new String(txtStaffPassword.getPassword());
            String fullname = txtStaffFullName.getText().trim();

            String phone = txtStaffPhone.getText();
            int age = Integer.parseInt(txtStaffAge.getText().trim());
            String sex = txtStaffSex.getText().trim();

            staffDAO sdao = new staffDAO();
            staffDTO sdto = new staffDTO(username, password, fullname, phone, age, sex);
            boolean check = sdao.update(sdto);
            if (check) {
                JOptionPane.showMessageDialog(null, "Update success");
                tblStaff.setValueAt(fullname, row, 1);
                tblStaff.setValueAt(age, row, 2);
                resetStaffFieldAfterInsertOrUpdate();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnStaffUpdateActionPerformed

    private void btnStaffInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffInsertActionPerformed
        if (!staffValid()) {
            return;
        }
        String username = txtStaffID.getText().trim();
        String password = new String(txtStaffPassword.getPassword());
        String fullname = txtStaffFullName.getText().trim();
        String phone = txtStaffPhone.getText();
        int age = Integer.parseInt(txtStaffAge.getText());
        String sex = txtStaffSex.getText().trim();
        int roleID = 1;

        try {
            staffDAO sdao = new staffDAO();
            staffDTO sdto = new staffDTO(username, password, fullname, phone, age, roleID, sex);
            boolean insert = sdao.insert(sdto);
            if (insert == true) {
                JOptionPane.showMessageDialog(null, "Insert Success.");
                resetStaffFieldAfterInsertOrUpdate();
            } else {
                JOptionPane.showMessageDialog(null, "Insert Fail!");
            }
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate")) {
                JOptionPane.showMessageDialog(null, "Username can't be blank or Username is existed!!");
            }

        }
    }//GEN-LAST:event_btnStaffInsertActionPerformed

    private void btnStaffDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffDeleteActionPerformed
        int index = tblStaffView.getSelectedRow();
        String username = txtStaffID.getText().trim();
        
        if (username.length()==0)
        {
            labelStaffValid.setText("Please enter the Username if you want to delete");
            txtStaffID.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
        }
        else
        {
        if (JOptionPane.showConfirmDialog(null, "Are you sure?") == 0) {
            staffDAO dao = new staffDAO();
            try {
                boolean check = dao.delete(username);
                if (check) {
                    JOptionPane.showMessageDialog(null, "Delete Success!");
                    tblStaff.removeRow(index);
                    resetStaffFieldAfterInsertOrUpdate();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Delete Fail!");
            }
        }
        }
    }//GEN-LAST:event_btnStaffDeleteActionPerformed

    private void btnMovieSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovieSearchActionPerformed
        resetMovieFieldAfterInsertOrUpdate();
        try {
            String search = txtMovieName.getText().trim();

            MovieDAO mdao = new MovieDAO();
            List<MovieDTO> result = mdao.findByMovieName(search);
            showMovie(result);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnMovieSearchActionPerformed

    private void btnMovieUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovieUpdateActionPerformed
        if (!movieValid()) return;
        try {
            int row = tblMovieView.getSelectedRow();
            String name = txtMovieName.getText().trim();
            int length = Integer.parseInt(txtMovieLength.getText());
            int genreID = cbbMovieGenre.getSelectedIndex();

            MovieDAO mdao = new MovieDAO();
            MovieDTO mdto = new MovieDTO(length, name, genreID);
            boolean check = mdao.update(mdto);

            if (check) {
               JOptionPane.showMessageDialog(null, "Update Success.");
               tblMovie.setValueAt(name, row, 1);
               tblMovie.setValueAt(length, row, 2);
               resetMovieFieldAfterInsertOrUpdate();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnMovieUpdateActionPerformed

    private void btnMovieNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovieNewActionPerformed
        txtMovieID.setText("");
        txtMovieLength.setText("");
        txtMovieName.setText("");
        cbbMovieGenre.setSelectedIndex(0);
        resetMovieFieldAfterInsertOrUpdate();
        txtMovieID.setEditable(false);
    }//GEN-LAST:event_btnMovieNewActionPerformed

    private void tblMovieViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMovieViewMouseClicked
        int row = tblMovieView.getSelectedRow();
        resetMovieFieldAfterInsertOrUpdate();
        
        int id = (int) tblMovie.getValueAt(row, 0);
        String name = (String) tblMovie.getValueAt(row, 1);
        int length =  (int) tblMovie.getValueAt(row, 2);
        
        try {
            MovieDAO mdao = new MovieDAO();
            MovieDTO mdto = mdao.findByPrimaryKey(id);
            int genreID = mdto.getGenreID();
        
            txtMovieID.setText(id + "");
            txtMovieName.setText(name);
            cbbMovieGenre.setSelectedIndex(genreID);
            txtMovieLength.setText(length + "");
            txtMovieID.setEditable(false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
       
    }//GEN-LAST:event_tblMovieViewMouseClicked

    private void cbbMovieGenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMovieGenreActionPerformed
        
    }//GEN-LAST:event_cbbMovieGenreActionPerformed

    private void btnMoveInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveInsertActionPerformed
        if (!movieValid()) return;
        String name = txtMovieName.getText().trim();
        int length = Integer.parseInt(txtMovieLength.getText().trim());
        int genreID = cbbMovieGenre.getSelectedIndex();
        
        try {
            MovieDTO mdto = new MovieDTO(length, name, genreID);
            MovieDAO mdao = new MovieDAO();
            boolean insert = mdao.insert(mdto);
            if (insert)
            {
                JOptionPane.showMessageDialog(null, "Insert Success.");
                resetMovieFieldAfterInsertOrUpdate();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Insert Fail!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnMoveInsertActionPerformed

    private void btnMovieDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovieDeleteActionPerformed
       String validMovieID = txtMovieID.getText().trim();
       int index = tblMovieView.getSelectedRow();
       if (validMovieID.length()==0)
       {
           labelMovieValid.setText("You hasn't select the Movie!!");
       }
       else
       {
            if (JOptionPane.showConfirmDialog(null, "Are you sure?") == 0)
            {
                int id = Integer.parseInt(txtMovieID.getText());
                MovieDAO mdao = new MovieDAO();
                try {
                    boolean check = mdao.delete(id);
                    System.out.println(check);
                    if (check)
                    {
                        JOptionPane.showMessageDialog(null, "Delete Success.");
                        tblMovie.removeRow(index);
                        resetMovieFieldAfterInsertOrUpdate();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Delete Fail!");
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
       }
    }//GEN-LAST:event_btnMovieDeleteActionPerformed

    private void showStaff(List<staffDTO> list) {
        tblStaff.setRowCount(0);
        for (staffDTO staffDTO : list) {
            tblStaff.addRow(staffDTO.toVector());
        }
    }

    private void showMovie(List<MovieDTO> list) {
        tblMovie.setRowCount(0);
        for (MovieDTO movieDTO : list) {
            tblMovie.addRow(movieDTO.toVector());
        }
    }

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Admin form = new Admin();
                form.setLocationRelativeTo(null);
                form.setVisible(true);
                form.setTitle("Staff Manage");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoveInsert;
    private javax.swing.JButton btnMovieDelete;
    private javax.swing.JButton btnMovieNew;
    private javax.swing.JButton btnMovieSearch;
    private javax.swing.JButton btnMovieUpdate;
    private javax.swing.JButton btnStaffDelete;
    private javax.swing.JButton btnStaffInsert;
    private javax.swing.JButton btnStaffNew;
    private javax.swing.JButton btnStaffSearch;
    private javax.swing.JButton btnStaffUpdate;
    private javax.swing.JComboBox<String> cbbMovieGenre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelMovieValid;
    private javax.swing.JLabel labelStaffValid;
    private javax.swing.JLabel lbSearching;
    private javax.swing.JTable tblMovieView;
    private javax.swing.JTable tblStaffView;
    private javax.swing.JTextField txtMovieID;
    private javax.swing.JTextField txtMovieLength;
    private javax.swing.JTextField txtMovieName;
    private javax.swing.JTextField txtStaffAge;
    private javax.swing.JTextField txtStaffFullName;
    private javax.swing.JTextField txtStaffID;
    private javax.swing.JPasswordField txtStaffPassword;
    private javax.swing.JTextField txtStaffPhone;
    private javax.swing.JTextField txtStaffSex;
    // End of variables declaration//GEN-END:variables
}
