package gestionparqueaderotest;

import gestionmotos.Moto;
import gestionpagos.TipoPago;
import gestionparqueadero.Parqueadero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParqueaderoTest {

    @Test
    public void probarCreacionInstancias() {
        Parqueadero parqueadero = new Parqueadero();
        Moto moto = new Moto("XYZ12A", "Suzuki", "10101010"); // Placa adaptada al nuevo formato

        assertEquals(23, parqueadero.obtenerCapacidad());
        assertEquals(40.0, parqueadero.obtenerTarifa());
        assertEquals(23, parqueadero.consultarEspacios());

        assertEquals("XYZ12A", moto.obtenerPlaca());
        assertEquals("Suzuki", moto.obtenerMarca());
        assertEquals("10101010", moto.obtenerCedula());
    }

    @Test
    public void probarIngresoDeMoto() {
        Parqueadero parqueadero = new Parqueadero();
        Moto moto = new Moto("ABC98C", "Yamaha", "12345678"); // Placa adaptada al nuevo formato

        boolean ingresoExitoso = parqueadero.registrarIngreso(moto);

        assertTrue(ingresoExitoso, "El ingreso debería ser exitoso");
        assertEquals(22, parqueadero.consultarEspacios(), "Debe quedar 1 espacio menos disponible");
        assertEquals(1, parqueadero.obtenerRegistrosActivos().size(), "Debe haber 1 registro activo");
    }

    @Test
    public void probarSalidaYPagoDeMoto() {
        Parqueadero parqueadero = new Parqueadero();
        Moto moto = new Moto("QWE45F", "Honda", "98765432"); // Placa adaptada al nuevo formato

        parqueadero.registrarIngreso(moto);

        String resultadoSalida = parqueadero.registrarSalida("QWE45F", TipoPago.NEQUI);

        assertTrue(resultadoSalida.contains("Salida exitosa"), "La salida debería procesarse correctamente");
        assertEquals(23, parqueadero.consultarEspacios(), "El espacio debe volver a estar libre");
        assertEquals(0, parqueadero.obtenerRegistrosActivos().size(), "No deben quedar registros activos");
        assertEquals(1, parqueadero.generarReporteDia() > 0 ? 1 : 0, "El reporte del día debe tener el cobro registrado");
    }
}