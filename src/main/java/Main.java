import vista.VentanaGral;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaGral ventana = new VentanaGral();
            ventana.setVisible(true);
        });
    }
}