/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umag.gui;

import javax.swing.JPanel;

/**
 *
 * @author carla
 */
public class PanelPersonalizado extends javax.swing.JPanel{
        
    javax.swing.JLabel lblTitulo = new javax.swing.JLabel();
    javax.swing.JLabel lblDescripcion = new javax.swing.JLabel();
    javax.swing.JLabel lblPrecio = new javax.swing.JLabel();

    public PanelPersonalizado() {
        
        this.setBackground(new java.awt.Color(215, 164, 204));

        lblTitulo.setText("Arroz");

        lblDescripcion.setText("yuca");

        lblPrecio.setText("Tajaa");

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(this);
        this.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(lblDescripcion)
                    .addComponent(lblPrecio))
                .addContainerGap(556, Short.MAX_VALUE))
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(lblPrecio)
                .addContainerGap())
        );
    }
        
        
}
