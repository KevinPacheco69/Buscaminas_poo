package PruebasUnitarias;

import model.CasillaYaDescubiertaException;

public class CasillaYaDescubiertaExceptionTestManual {

    public static void main(String[] args) {
        CasillaYaDescubiertaExceptionTestManual test = new CasillaYaDescubiertaExceptionTestManual();
        test.testLanzarYCapturarExcepcion();
    }

    public void testLanzarYCapturarExcepcion() {
        System.out.println("=== Ejecutando testLanzarYCapturarExcepcion ===");

        try {
            // Simulamos una situación donde se lanza la excepción
            throw new CasillaYaDescubiertaException("La casilla ya fue descubierta");
        } catch (CasillaYaDescubiertaException e) {
            // Validamos que el mensaje se recibe correctamente
            System.out.println("Excepción capturada: " + e.getMessage());
            System.out.println("✅ testLanzarYCapturarExcepcion ejecutado\n");
            return;
        }
    }
}