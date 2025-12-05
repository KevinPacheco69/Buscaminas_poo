package PruebasUnitarias;

import model.Tablero;
import model.Casilla;

public class TableroTestManual {

    public static void main(String[] args) {
        TableroTestManual test = new TableroTestManual();
        test.testInicializacion();
        test.testRevelarCasilla();
        test.testMarcarCasilla();
        test.testVictoria();
    }

    public void testInicializacion() {
        System.out.println("=== Ejecutando testInicializacion ===");
        Tablero tablero = new Tablero(10, 10, 10);

        int minas = 0;
        for (int r = 0; r < tablero.getFilas(); r++) {
            for (int c = 0; c < tablero.getCols(); c++) {
                if (tablero.getCasilla(r, c).isMina()) {
                    minas++;
                }
            }
        }

        System.out.println("Minas colocadas: " + minas);
        System.out.println("✅ testInicializacion ejecutado\n");
    }

    public void testRevelarCasilla() {
        System.out.println("=== Ejecutando testRevelarCasilla ===");
        Tablero tablero = new Tablero(10, 10, 10);

        try {
            boolean resultado = tablero.revelar(0, 0);
            System.out.println("Resultado de revelar (0,0): " + resultado);
        } catch (Exception e) {
            System.out.println("❌ Error al revelar casilla: " + e.getMessage());
        }

        System.out.println("✅ testRevelarCasilla ejecutado\n");
    }

    public void testMarcarCasilla() {
        System.out.println("=== Ejecutando testMarcarCasilla ===");
        Tablero tablero = new Tablero(10, 10, 10);

        tablero.marcar(0, 1);
        boolean marcada = tablero.getCasilla(0, 1).isMarcada();
        System.out.println("Casilla (0,1) marcada: " + marcada);

        tablero.marcar(0, 1); // desmarcar
        marcada = tablero.getCasilla(0, 1).isMarcada();
        System.out.println("Casilla (0,1) marcada después de alternar: " + marcada);

        System.out.println("✅ testMarcarCasilla ejecutado\n");
    }

    public void testVictoria() {
        System.out.println("=== Ejecutando testVictoria ===");
        Tablero tablero = new Tablero(10, 10, 10);

        // Revelar todas las casillas que no son minas
        for (int r = 0; r < tablero.getFilas(); r++) {
            for (int c = 0; c < tablero.getCols(); c++) {
                Casilla casilla = tablero.getCasilla(r, c);
                if (!casilla.isMina()) {
                    casilla.setDescubierta(true);
                }
            }
        }

        boolean victoria = tablero.isVictoria();
        System.out.println("¿Victoria?: " + victoria);

        System.out.println("✅ testVictoria ejecutado\n");
    }
}