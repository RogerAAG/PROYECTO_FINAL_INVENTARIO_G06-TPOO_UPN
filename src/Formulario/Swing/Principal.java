package Formulario.Swing;
import AccesoBD.ConexionBD;
import java.sql.Connection;
import javax.swing.JOptionPane;
import Clases.Productos;
import Clases.Proveedores;
import Clases.Imprimir;
import java.math.BigDecimal;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

public class Principal extends javax.swing.JFrame {
    private final Productos obj;
    private final Proveedores proveedores;
    int num = 0;
    
    //Limpiar FormProductos
    private void limpiar_controles(){
        this.txtId.setText("");
        this.txtDescripcion.setText("");
        this.cmbCategoria.setSelectedIndex(0);
        this.txtStock.setText("");
        this.txtPrecio.setText("");
    }
    
    private void guardar() {
        //recopilar la informacion del formulario
        if (num==1)
            obj.setIdproducto(Integer.parseInt(txtId.getText()));
        
        obj.setDescripcion(txtDescripcion.getText());
        obj.setCategoria(cmbCategoria.getSelectedItem().toString());
        obj.setStock(Integer.parseInt(this.txtStock.getText()));
        obj.setPrecio(new BigDecimal(this.txtPrecio.getText()));
        
        //escoger insercion o actualizacion
        if (num==0) {
            int regs = obj.InsertarDatos(obj.getDescripcion(), 
                                         obj.getCategoria(), 
                                         obj.getStock(),
                                         obj.getPrecio());
            if (regs>0) {
                listarDatos();
            }
        } else {
            int regs = obj.ActualizarDatos(obj.getIdproducto(),
                                           obj.getDescripcion(), 
                                           obj.getCategoria(), 
                                           obj.getStock(),
                                           obj.getPrecio());
            if (regs>0) {
                listarDatos();
                num=0;
            }
        }
        limpiar_controles();
    }
    
    private void listarDatos() {
        tblProductos.setModel(obj.obtenerDatos());
    }
    
    
    public Principal() {
        initComponents();
        obj = new Productos();
        proveedores = new Proveedores();
        listarProveedores();
        listarDatos();
        TpnlPrincipal.setSelectedIndex(0);
        setLocationRelativeTo(null);
    }
    
    //Limpiar FormProveedores
    private void limpiarControles() {
        txtIdProveedores.setText("");
        txtRuc.setText("");
        txtRazonSocial.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        num = 0;
    }
    
    private void listarProveedores() {
        tblProveedores.setModel(proveedores.obtenerProveedores());
    }
    
    private void guardarProveedores() {
        try {
            if (num == 1)  // Modo Edición
                proveedores.setIdproveedores(Integer.parseInt(txtIdProveedores.getText()));
            
            // Setea los datos del proveedor
            proveedores.setRuc(txtRuc.getText());
            proveedores.setRazonsocial(txtRazonSocial.getText());
            proveedores.setDireccion(txtDireccion.getText());
            proveedores.setTelefono(txtTelefono.getText());
            
            int resultado;
            if (num == 0) {  // Modo Agregar
                resultado = proveedores.insertarProveedores(
                    proveedores.getRuc(),
                    proveedores.getRazonsocial(),
                    proveedores.getDireccion(),
                    proveedores.getTelefono()
                );
                listarProveedores();
            } else {  // Modo Edición
                resultado = proveedores.actualizarProveedores(
                    proveedores.getIdproveedores(),
                    proveedores.getRuc(),
                    proveedores.getRazonsocial(),
                    proveedores.getDireccion(),
                    proveedores.getTelefono()
                );
                listarProveedores();
                num = 0;  // Regresa al modo agregar
            }

            if (resultado > 0) {
                listarProveedores();
                limpiarControles();
                JOptionPane.showMessageDialog(this, "Proveedor guardado correctamente.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa valores numéricos válidos para ID, RUC y Teléfono.");
        }
    }
    
    private void buscarProveedores() {
        try {
            int idProveedor = Integer.parseInt(txtIdProveedores.getText());
            DefaultTableModel modelo = proveedores.obtenerProveedores();
            
            for (int i = 0; i < modelo.getRowCount(); i++) {
                if (Integer.parseInt(modelo.getValueAt(i, 0).toString()) == idProveedor) {
                    txtRuc.setText(modelo.getValueAt(i, 1).toString());
                    txtRazonSocial.setText(modelo.getValueAt(i, 2).toString());
                    txtDireccion.setText(modelo.getValueAt(i, 3).toString());
                    txtTelefono.setText(modelo.getValueAt(i, 4).toString());
                    
                    num = 1;  // Cambia a modo edición
                    return;
                }
            }
            
            JOptionPane.showMessageDialog(this, "Proveedor no encontrado.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un ID de proveedor válido.");
        }
    }

    private void eliminarProveedores() {
        int fila = tblProveedores.getSelectedRow();
        
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona un proveedor para eliminar.");
            return;
        }

        int idProveedores = Integer.parseInt(tblProveedores.getValueAt(fila, 0).toString());
        int resultado = proveedores.eliminarProveedores(idProveedores);
        
        if (resultado > 0) {
            listarProveedores();
            limpiarControles();
            JOptionPane.showMessageDialog(this, "Proveedor eliminado correctamente.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();
        btnComingSoon = new javax.swing.JButton();
        btnFormProveedores = new javax.swing.JButton();
        btnFormProductos = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnConexion = new javax.swing.JButton();
        TpnlPrincipal = new javax.swing.JTabbedPane();
        pnlInventario = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        pnlProveedores = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtRuc = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProveedores = new javax.swing.JTable();
        btnAgregarProveedores = new javax.swing.JButton();
        btnEditarProveedores = new javax.swing.JButton();
        btnEliminarProveedores = new javax.swing.JButton();
        btnBuscarProveedores = new javax.swing.JButton();
        btnReporteProveedores = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtIdProveedores = new javax.swing.JTextField();
        pnlNext = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Main Inventory");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 90));

        pnlMenu.setBackground(new java.awt.Color(0, 153, 153));

        btnComingSoon.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnComingSoon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Soon Arrow.png"))); // NOI18N
        btnComingSoon.setText("COMING SOON...");
        btnComingSoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComingSoonActionPerformed(evt);
            }
        });

        btnFormProveedores.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnFormProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Shopping Cart.png"))); // NOI18N
        btnFormProveedores.setText("PROVEEDORES");
        btnFormProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormProveedoresActionPerformed(evt);
            }
        });

        btnFormProductos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnFormProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Clipboard List.png"))); // NOI18N
        btnFormProductos.setText("GESTION DE INVENTARIO");
        btnFormProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormProductosActionPerformed(evt);
            }
        });

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Close Window.png"))); // NOI18N
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnConexion.setText("Prueba de Conexión");
        btnConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConexionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConexion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnFormProductos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnComingSoon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFormProveedores, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(btnFormProductos)
                .addGap(18, 18, 18)
                .addComponent(btnFormProveedores)
                .addGap(18, 18, 18)
                .addComponent(btnComingSoon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                .addComponent(btnConexion)
                .addGap(18, 18, 18)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(pnlMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 220, 590));

        TpnlPrincipal.setBackground(new java.awt.Color(0, 153, 153));
        TpnlPrincipal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        pnlInventario.setBackground(new java.awt.Color(255, 255, 255));

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel2.setText("PRODUCTOS");

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "DESCRIPCIÓN", "CATEGORIA", "STOCK", "PRECIO"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel5.setText("Descripción:");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Precio:");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel7.setText("Cantidad:");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.setText("ID Producto");

        btnAgregar.setBackground(new java.awt.Color(0, 204, 204));
        btnAgregar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Checked Checkbox.png"))); // NOI18N
        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(0, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Trash Can.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnImprimir.setBackground(new java.awt.Color(0, 204, 204));
        btnImprimir.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR TABLA");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(0, 204, 204));
        btnEditar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Edit.png"))); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(0, 204, 204));
        btnBuscar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel9.setText("Categoria:");

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----Seleccionar Categoria----", "Gaseosas", "Cervezas", "Aguas", "Abarrotes", "Licores", "Golosinas", "Snacks" }));
        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInventarioLayout = new javax.swing.GroupLayout(pnlInventario);
        pnlInventario.setLayout(pnlInventarioLayout);
        pnlInventarioLayout.setHorizontalGroup(
            pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInventarioLayout.createSequentialGroup()
                        .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInventarioLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel9))
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addComponent(cmbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlInventarioLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlInventarioLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel2)
                    .addGroup(pnlInventarioLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlInventarioLayout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar))
                            .addGroup(pnlInventarioLayout.createSequentialGroup()
                                .addGap(267, 267, 267)
                                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        pnlInventarioLayout.setVerticalGroup(
            pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInventarioLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImprimir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TpnlPrincipal.addTab("GESTION DE INVENTARIO", pnlInventario);

        pnlProveedores.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel10.setText("PROVEEDORES");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setText("RUC:");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel13.setText("Razón Social:");

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel14.setText("Dirección:");

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel15.setText("Teléfono:");

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N

        txtRazonSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazonSocialActionPerformed(evt);
            }
        });

        txtRuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRucActionPerformed(evt);
            }
        });

        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        tblProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "IDProveedor", "RUC", "RAZÓN SOCIAL", "DIRECCIÓN", "TELÉFONO"
            }
        ));
        jScrollPane2.setViewportView(tblProveedores);
        if (tblProveedores.getColumnModel().getColumnCount() > 0) {
            tblProveedores.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblProveedores.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblProveedores.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblProveedores.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblProveedores.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        btnAgregarProveedores.setBackground(new java.awt.Color(0, 204, 204));
        btnAgregarProveedores.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAgregarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        btnAgregarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProveedoresActionPerformed(evt);
            }
        });

        btnEditarProveedores.setBackground(new java.awt.Color(0, 204, 204));
        btnEditarProveedores.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnEditarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Edit.png"))); // NOI18N
        btnEditarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProveedoresActionPerformed(evt);
            }
        });

        btnEliminarProveedores.setBackground(new java.awt.Color(0, 204, 204));
        btnEliminarProveedores.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnEliminarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Trash Can.png"))); // NOI18N
        btnEliminarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedoresActionPerformed(evt);
            }
        });

        btnBuscarProveedores.setBackground(new java.awt.Color(0, 204, 204));
        btnBuscarProveedores.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnBuscarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        btnBuscarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProveedoresActionPerformed(evt);
            }
        });

        btnReporteProveedores.setBackground(new java.awt.Color(0, 204, 204));
        btnReporteProveedores.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnReporteProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        btnReporteProveedores.setText("IMPRIMIR DATOS PROVEEDORES");
        btnReporteProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteProveedoresActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel17.setText("ID Proveedor:");

        txtIdProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProveedoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProveedoresLayout = new javax.swing.GroupLayout(pnlProveedores);
        pnlProveedores.setLayout(pnlProveedoresLayout);
        pnlProveedoresLayout.setHorizontalGroup(
            pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProveedoresLayout.createSequentialGroup()
                .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProveedoresLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10))
                    .addGroup(pnlProveedoresLayout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(btnAgregarProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlProveedoresLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlProveedoresLayout.createSequentialGroup()
                                .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRazonSocial)
                                    .addComponent(txtDireccion)
                                    .addComponent(txtTelefono)
                                    .addComponent(txtRuc)
                                    .addGroup(pnlProveedoresLayout.createSequentialGroup()
                                        .addComponent(txtIdProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(64, 64, 64)
                                .addComponent(jLabel16))
                            .addGroup(pnlProveedoresLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 30, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlProveedoresLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(btnReporteProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlProveedoresLayout.setVerticalGroup(
            pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProveedoresLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel16))
                    .addGroup(pnlProveedoresLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtIdProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBuscarProveedores, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnEliminarProveedores)
                        .addComponent(btnEditarProveedores))
                    .addComponent(btnAgregarProveedores))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReporteProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        TpnlPrincipal.addTab("GESTION DE PROVEEDORES", pnlProveedores);

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel11.setText("EN DESARROLLO...");

        javax.swing.GroupLayout pnlNextLayout = new javax.swing.GroupLayout(pnlNext);
        pnlNext.setLayout(pnlNextLayout);
        pnlNextLayout.setHorizontalGroup(
            pnlNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNextLayout.createSequentialGroup()
                .addContainerGap(198, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );
        pnlNextLayout.setVerticalGroup(
            pnlNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNextLayout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );

        TpnlPrincipal.addTab("COMING SOON...", pnlNext);

        getContentPane().add(TpnlPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 830, 680));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 800, 100));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFormProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormProductosActionPerformed
        TpnlPrincipal.setSelectedIndex(0);
    }//GEN-LAST:event_btnFormProductosActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtRazonSocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazonSocialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonSocialActionPerformed

    private void txtRucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void btnConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConexionActionPerformed
        ConexionBD conexion = new ConexionBD();
        Connection con = conexion.Conectar();
        JOptionPane.showMessageDialog(null, "Conexión establecida con Exito!!");
        
    }//GEN-LAST:event_btnConexionActionPerformed

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblProductos.getSelectedRow();
       if (fila<1)
           JOptionPane.showMessageDialog(null, "Seleccione un registro");
       else {
           obj.EliminarDatos(Integer.parseInt(tblProductos.getValueAt(fila,0).toString()));
           limpiar_controles();
           listarDatos();
       }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        num =1;
        guardar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        num =0;
        guardar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
    Imprimir obj;

        try {
            obj = new Imprimir();
            obj.exportarExcel(tblProductos);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }

    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // Cierra completamente la aplicación
    System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
        int idProducto = Integer.parseInt(txtId.getText());
        Productos producto = obj.buscarProducto(idProducto);
        
        if (producto != null) {
            // Rellena los campos del formulario
            txtDescripcion.setText(producto.getDescripcion());
            cmbCategoria.setSelectedItem(producto.getCategoria());
            txtStock.setText(String.valueOf(producto.getStock()));
            txtPrecio.setText(producto.getPrecio().toString());
            
            // Cambia a modo edición
            num = 1;
            JOptionPane.showMessageDialog(this, "Producto encontrado.");
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            limpiar_controles();
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID de producto válido.");
    }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtIdProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProveedoresActionPerformed

    private void btnAgregarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProveedoresActionPerformed
        num = 0;
        guardarProveedores();
    }//GEN-LAST:event_btnAgregarProveedoresActionPerformed

    private void btnEditarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProveedoresActionPerformed
       num = 1;
       guardarProveedores();
    }//GEN-LAST:event_btnEditarProveedoresActionPerformed

    private void btnEliminarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedoresActionPerformed
        eliminarProveedores();
    }//GEN-LAST:event_btnEliminarProveedoresActionPerformed

    private void btnBuscarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProveedoresActionPerformed
       buscarProveedores();
    }//GEN-LAST:event_btnBuscarProveedoresActionPerformed

    private void btnReporteProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteProveedoresActionPerformed
        Imprimir obj;

        try {
            obj = new Imprimir();
            obj.exportarExcel(tblProveedores);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }  
    }//GEN-LAST:event_btnReporteProveedoresActionPerformed

    private void btnFormProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormProveedoresActionPerformed
        TpnlPrincipal.setSelectedIndex(1);
    }//GEN-LAST:event_btnFormProveedoresActionPerformed

    private void btnComingSoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComingSoonActionPerformed
        TpnlPrincipal.setSelectedIndex(2);
    }//GEN-LAST:event_btnComingSoonActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TpnlPrincipal;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarProveedores;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarProveedores;
    private javax.swing.JButton btnComingSoon;
    private javax.swing.JButton btnConexion;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditarProveedores;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarProveedores;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFormProductos;
    private javax.swing.JButton btnFormProveedores;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnReporteProveedores;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlInventario;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlNext;
    private javax.swing.JPanel pnlProveedores;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblProveedores;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdProveedores;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
