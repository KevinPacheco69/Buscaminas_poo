package PruebasUnitarias;

import View.ConsoleView;
import model.Tablero;
import model.Casilla;

public class ConsoleViewTestManual {

    public static void main(String[] args) {
        ConsoleViewTestManual test = new ConsoleViewTestManual();
        test.testMostrarMensaje();
        test.testMostrarTableroConMina();
        test.testMostrarTableroSinMinas();
    }

    public void testMostrarMensaje() {
        System.out.println("=== Ejecutando testMostrarMensaje ===");
        ConsoleView view = new ConsoleView();
        view.mostrarMensaje("Hola desde prueba manual");
        System.out.println("✅ testMostrarMensaje ejecutado\n");
    }

    public void testMostrarTableroConMina() {
        System.out.println("=== Ejecutando testMostrarTableroConMina ===");
        Tablero tablero = new Tablero(1, 1, 1); // tablero 1x1 con mina
        Casilla casilla = tablero.getCasilla(0, 0);
        casilla.setDescubierta(true);

        ConsoleView view = new ConsoleView();
        view.mostrarTablero(tablero, false);
        System.out.println("✅ testMostrarTableroConMina ejecutado\n");
    }

    public void testMostrarTableroSinMinas() {
        System.out.println("=== Ejecutando testMostrarTableroSinMinas ===");
        Tablero tablero = new Tablero(1, 1, 0); // tablero 1x1 sin minas
        Casilla casilla = tablero.getCasilla(0, 0);
        casilla.setDescubierta(true);

        ConsoleView view = new ConsoleView();
        view.mostrarTablero(tablero, false);
        System.out.println("✅ testMostrarTableroSinMinas ejecutado\n");
    }
}