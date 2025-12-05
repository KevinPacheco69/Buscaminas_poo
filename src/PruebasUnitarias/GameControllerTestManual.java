package PruebasUnitarias;

import controller.GameController;

public class GameControllerTestManual {

    public static void main(String[] args) {
        GameControllerTestManual test = new GameControllerTestManual();
        test.testRunArrancaSinErrores();
        test.testGuardarYCargar();
        test.testProcesarComando();
    }

    public void testRunArrancaSinErrores() {
        System.out.println("=== Ejecutando testRunArrancaSinErrores ===");
        try {
            GameController controlador = new GameController();
            System.out.println("✅ GameController creado correctamente\n");
        } catch (Exception e) {
            System.out.println("❌ Error al crear GameController: " + e.getMessage() + "\n");
        }
    }

    public void testGuardarYCargar() {
        System.out.println("=== Ejecutando testGuardarYCargar ===");
        try {
            GameController controlador = new GameController();
            controlador.guardar("test_partida.dat");
            controlador.cargar("test_partida.dat");
            System.out.println("✅ Guardar y cargar ejecutados sin errores\n");
        } catch (Exception e) {
            System.out.println("❌ Error en guardar/cargar: " + e.getMessage() + "\n");
        }
    }

    public void testProcesarComando() {
        System.out.println("=== Ejecutando testProcesarComando ===");
        try {
            GameController controlador = new GameController();
            var metodo = controlador.getClass().getDeclaredMethod("procesarComando", String.class);
            metodo.setAccessible(true);
            boolean resultado = (boolean) metodo.invoke(controlador, "F A1");
            System.out.println("✅ procesarComando ejecutado, resultado: " + resultado + "\n");
        } catch (Exception e) {
            System.out.println("❌ Error al procesar comando: " + e.getMessage() + "\n");
        }
    }
}