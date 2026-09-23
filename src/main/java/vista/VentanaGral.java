package vista;

import gestionmotos.Moto;
import gestionpagos.TipoPago;
import gestionparqueadero.Parqueadero;

import javax.swing.*;
import java.awt.*;

public class VentanaGral extends JFrame {
    private Parqueadero parqueadero;

    // Componentes Ingreso
    private JTextField txtPlacaIngreso, txtMarca, txtCedula;
    // Componentes Salida
    private JTextField txtPlacaSalida;
    private JComboBox<TipoPago> cmbTipoPago;
    private JLabel lblEspacios;

    public VentanaGral() {
        parqueadero = new Parqueadero();

        setTitle("Sistema de Parqueadero - Motos");
        setSize(550, 450); // Un poco más alta para que quepa el reporte completo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        lblEspacios = new JLabel("Espacios disponibles: " + parqueadero.consultarEspacios(), SwingConstants.CENTER);
        lblEspacios.setFont(new Font("Arial", Font.BOLD, 16));
        lblEspacios.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblEspacios, BorderLayout.NORTH);

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.add("Registrar Ingreso", crearPanelIngreso());
        pestañas.add("Registrar Salida", crearPanelSalida());
        pestañas.add("Reporte Diario", crearPanelReporte());

        add(pestañas, BorderLayout.CENTER);
    }

    private JPanel crearPanelIngreso() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Placa (Ej: QHJ34E o QHJ12):"));
        txtPlacaIngreso = new JTextField();
        panel.add(txtPlacaIngreso);

        panel.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        panel.add(txtMarca);

        panel.add(new JLabel("Cédula:"));
        txtCedula = new JTextField();
        panel.add(txtCedula);

        JButton btnIngreso = new JButton("Registrar Ingreso");
        btnIngreso.addActionListener(e -> {
            String placa = txtPlacaIngreso.getText().trim().toUpperCase().replace(" ", "");
            String marca = txtMarca.getText().trim();
            String cedula = txtCedula.getText().trim();

            if(placa.isEmpty() || marca.isEmpty() || cedula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Llene todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!placa.matches("^[A-Z]{3}\\d{2}[A-Z]?$")) {
                JOptionPane.showMessageDialog(this, "Error de registro: La placa ingresada no es una placa valida).\nEjemplos válidos: QHJ34E, QHJ12.", "Formato Inválido", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!cedula.matches("^\\d+$")) {
                JOptionPane.showMessageDialog(this, "Error de registro: La cédula solo debe contener números.", "Formato Inválido", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Moto moto = new Moto(placa, marca, cedula);
            if(parqueadero.registrarIngreso(moto)) {
                JOptionPane.showMessageDialog(this, "Ingreso registrado exitosamente.");
                txtPlacaIngreso.setText(""); txtMarca.setText(""); txtCedula.setText("");
                actualizarEspacios();
            } else {
                JOptionPane.showMessageDialog(this, "Parqueadero lleno.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(new JLabel(""));
        panel.add(btnIngreso);

        return panel;
    }

    private JPanel crearPanelSalida() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Placa a buscar:"));
        txtPlacaSalida = new JTextField();
        panel.add(txtPlacaSalida);

        panel.add(new JLabel("Método de Pago:"));
        cmbTipoPago = new JComboBox<>(TipoPago.values());
        panel.add(cmbTipoPago);

        JButton btnSalida = new JButton("Registrar Salida y Cobrar");
        btnSalida.addActionListener(e -> {
            String placa = txtPlacaSalida.getText().trim().toUpperCase().replace(" ", "");
            if(placa.isEmpty()){
                JOptionPane.showMessageDialog(this, "Ingrese la placa.");
                return;
            }
            TipoPago tipo = (TipoPago) cmbTipoPago.getSelectedItem();
            String resultado = parqueadero.registrarSalida(placa, tipo);

            JOptionPane.showMessageDialog(this, resultado);
            txtPlacaSalida.setText("");
            actualizarEspacios();
        });
        panel.add(new JLabel(""));
        panel.add(btnSalida);

        return panel;
    }

    private JPanel crearPanelReporte() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnGenerar = new JButton("Generar Reporte del Día");

        JLabel lblResultado = new JLabel("<html><center>Motos Ingresadas: 0<br>Motos Salidas: 0<br><br><b>Motos en Parqueadero (Placas):</b><br>Ninguna<br><br><b>Total recaudado: $0.0</b></center></html>", SwingConstants.CENTER);
        lblResultado.setFont(new Font("Arial", Font.PLAIN, 15));

        // Se agrega un scroll por si ingresan las 23 motos y la lista de placas se hace muy larga
        JScrollPane scrollPane = new JScrollPane(lblResultado);
        scrollPane.setBorder(null);

        btnGenerar.addActionListener(e -> {
            double total = parqueadero.generarReporteDia();
            int ingresadas = parqueadero.obtenerTotalMotosIngresadas();
            int salidas = parqueadero.obtenerTotalMotosSalidas();
            String placasActivas = parqueadero.obtenerPlacasActivas();

            lblResultado.setText("<html><center>Motos Ingresadas: " + ingresadas +
                    "<br>Motos Salidas: " + salidas +
                    "<br><br><b>Motos en Parqueadero (Placas):</b><br><span style='color:blue;'>" + placasActivas + "</span>" +
                    "<br><br><b>Total recaudado: $" + total + "</b></center></html>");
        });

        panel.add(btnGenerar, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void actualizarEspacios() {
        lblEspacios.setText("Espacios disponibles: " + parqueadero.consultarEspacios());
    }
}