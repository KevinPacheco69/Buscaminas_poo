package View;

import model.Tablero;
import model.Casilla;

import java.util.Scanner;

public class ConsoleView {

    private Scanner sc = new Scanner(System.in);

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void mostrarTablero(Tablero t, boolean mostrarMinas) {

        System.out.print("    ");
        for (int c = 1; c <= t.getCols(); c++)
            System.out.printf("%3d", c);
        System.out.println();

        for (int r = 0; r < t.getFilas(); r++) {

            char fila = (char) ('A' + r);
            System.out.printf("%3c ", fila);

            for (int c = 0; c < t.getCols(); c++) {
                Casilla cs = t.getCasilla(r, c);
                String val = "□";  // Casilla vacía y no descubierta

                if (cs.isDescubierta()) {
                    if (cs.isMina()) {
                        val = "X"; // Bomba
                    } else if (cs.getAdyacentes() == 0) {
                        val = " "; // Casilla sin minas alrededor
                    } else {
                        val = String.valueOf(cs.getAdyacentes());
                    }
                } 
                else if (cs.isMarcada()) {
                    val = "⚑"; // Bandera
                } 
                else if (mostrarMinas && cs.isMina()) {
                    val = "X"; // Mostrar minas al perder
                }

                System.out.printf("%3s", val);
            }
            System.out.println();
        }
    }

    public String leerEntrada() {
        System.out.println("\n COMANDOS:");
        System.out.println("  - Destapar:   Filas(abcd...),columnas(123..) A5");     
        System.out.println("  - Guardar:    G");
        System.out.println("  - Cargar:     C");
        System.out.println("  - Salir:      Q");

        System.out.print("\n Ingresa un comando: ");

        return sc.nextLine().trim();
    }
}
