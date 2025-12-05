package PruebasUnitarias;

import BuscaminasApp.App;

public class AppTestManual {

    public static void main(String[] args) {
        AppTestManual test = new AppTestManual();
        test.testMainEjecutaSinErrores();
    }

    public void testMainEjecutaSinErrores() {
        System.out.println("=== Ejecutando testMainEjecutaSinErrores ===");
        try {
            // Llamamos al método main de App
            App.main(new String[]{});
            System.out.println("✅ El método main se ejecutó sin errores\n");
        } catch (Exception e) {
            System.out.println("❌ El método main lanzó una excepción: " + e.getMessage() + "\n");
        }
    }
}