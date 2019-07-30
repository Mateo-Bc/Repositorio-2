package ventanachatfuncional;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.ClienteChat;

public class Ventana extends JFrame {
    String usuario = "";
    String contraseña = "";
    
    public Ventana(){
        int var = 0;
        while (var == 0){
            JTextField username = new JTextField();
            JTextField password = new JPasswordField();
            Object[] message = {
                "Usuario:", username,
                "Contraseña:", password
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Iniciar Sesión", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                if (username.getText().equals("BCT") && password.getText().equals("12345")) {
                    System.out.println("Sesión iniciada exitosamente");
                    var = 1;
                    usuario = username.getText();
                    contraseña = password.getText();
                    this.iniciarVentana();
                    this.iniciarComponentes();
                    this.setVisible(true);
                } else {
                    System.out.println("Error al iniciar sesión, prueba escribiendo de nuevo el usuario o la contraseña");
                }
            } else {
                System.out.println("Inicio de sesión cancelado");
                var = 1;
            }
        }
        
    }

    private void iniciarVentana() {
        //-Definición de la ventana-//
        this.setTitle("Mi primera ventana de chat funcional");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE);
        this.setLayout(null);
        //--------------------------//
        
    }

    private void iniciarComponentes() {
        
        //----------Colores---------//
        Color violeta = new Color(155, 0, 255);
        Color violeta_claro = new Color(194, 124, 255);
        Color verde = new Color(0, 255, 0);
        //--------------------------//
        
        //Propiedades de la ventana//
        this.setSize(500,525);
        this.getContentPane().setBackground(violeta);
        //-------------------------//
        
        //--Objetos de la ventana--//
        JTextArea texto = new JTextArea();
        texto.setEditable(false);
        texto.setBounds(50, 20, 400, 350);
        texto.setBackground(violeta_claro);
        add(texto);
        
        JScrollPane scroll = new JScrollPane(texto);
        scroll.setBounds(50, 20, 400, 350);
        scroll.setVisible(true);
        
        JTextField campo_de_texto = new JTextField();
        campo_de_texto.setBounds(50, 395, 400, 25);
        campo_de_texto.setBackground(violeta_claro);
        
        JButton enviar = new JButton("Enviar");
        enviar.setBounds(370, 455, 80, 25);
        enviar.setBackground(verde);
        
        JButton borrar = new JButton("Borrar");
        borrar.setBounds(280, 455, 80, 25);
        borrar.setBackground(verde);
        
        JButton salir = new JButton("Salir");
        salir.setBounds(190, 455, 80, 25);
        salir.setBackground(verde);
        
        JCheckBox casilla = new JCheckBox("Agregar texto al final", true);
        casilla.setBounds(45, 430, 225, 15);
        casilla.setBackground(violeta);
        casilla.setVisible(true);
        //-------------------------//
        
        //---------Chat------------//
        ClienteChat clienteChat = new ClienteChat("192.168.61.11", "2000", texto);
        clienteChat.conectar();
        //-------------------------//
        
        //--Inicio de la ventana--//
        
        //this.add(texto); pero ya está definido arriba
        this.add(casilla);
        this.add(campo_de_texto);
        this.add(enviar);
        this.add(borrar);
        this.add(salir);
        this.add(scroll);
        
        //------------------------//
        
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent enviar) {
                String vacio = "";
                String mensaje = "";
                if (casilla.isSelected()){
                    System.out.println("Selected");
                    mensaje = campo_de_texto.getText();
                    texto.append(usuario + ": " + mensaje);
                    texto.append(System.getProperty("line.separator"));
                    campo_de_texto.setText(vacio);
                }else{
                    System.out.println("Not Selected");
                    mensaje = campo_de_texto.getText();
                    String chat = texto.getText();
                    texto.setText("");
                    texto.append(usuario + ": " + mensaje);
                    texto.append(System.getProperty("line.separator"));
                    texto.append(chat);
                    campo_de_texto.setText(vacio);
                    
                    
                }
                   
            }
        });
        
        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent borrar) {
                int optionPane = JOptionPane.showConfirmDialog(rootPane, "¿Seguro que desea borrar mensajes?", "Borrar Mensajes", JOptionPane.YES_NO_OPTION);
                if (optionPane == JOptionPane.YES_OPTION){
                    texto.setText("");
                    campo_de_texto.setText("");
                
                }
            }
        });
        
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent salir) {
                dispose();
                VentanaChatFuncional.main(null);
                   
            }
        });
        
    }
    
}