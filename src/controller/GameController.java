package controller;

import model.*;
import View.ConsoleView;

import java.io.*;

public class GameController {

    private Tablero tablero;
    private ConsoleView view;

    public GameController() {
        tablero = new Tablero();
        view = new ConsoleView();
    }

    public void run() {
        boolean terminado = false;
        boolean juegoFinalizado = false;

        view.mostrarMensaje("=== Ex_Buscaminas (POO - Java) ===");
        view.mostrarMensaje("10x10 | 10 minas\n");

        while (!terminado) {

            view.mostrarTablero(tablero, juegoFinalizado);

            if (juegoFinalizado) {
                view.mostrarMensaje("\nJuego finalizado. (R = Reiniciar | Q = Salir)");
                String opcion = view.leerEntrada().trim();

                if (opcion.equalsIgnoreCase("R")) {
                    view.mostrarMensaje("🔄 Reiniciando el juego...");
                    tablero = new Tablero();
                    juegoFinalizado = false;
                    continue;
                } else if (opcion.equalsIgnoreCase("Q")) {
                    view.mostrarMensaje("👋 ¡Hasta luego!");
                    terminado = true;
                    continue;
                } else {
                    view.mostrarMensaje("⚠ Opción inválida.");
                    continue;
                }
            }

            String comando = view.leerEntrada().trim();

            if (comando.equalsIgnoreCase("Q")) {
                view.mostrarMensaje("Saliendo...");
                break;
            }

            if (comando.equalsIgnoreCase("G")) {
                guardar("partida.dat");
                continue;
            }

            if (comando.equalsIgnoreCase("C")) {
                cargar("partida.dat");
                continue;
            }

            try {
                boolean resultado = procesarComando(comando);

                if (!resultado) { // Perdiste
                    view.mostrarMensaje("\n💥 BOOM! Pisaste una mina.");
                    tablero.revelarTodasMinas();
                    juegoFinalizado = true;
                } else if (tablero.isVictoria()) {
                    view.mostrarMensaje("\n🎉 ¡Felicidades! Ganaste.");
                    tablero.revelarTodasMinas();
                    juegoFinalizado = true;
                }

            } catch (CasillaYaDescubiertaException e) {
                view.mostrarMensaje("⚠ " + e.getMessage());
            } catch (Exception e) {
                view.mostrarMensaje("❌ Comando inválido (Ej: A5 o F B7)");
            }
        }
    }

    private boolean procesarComando(String input) throws Exception {

        String[] partes = input.split("\\s+");

        String cmd;
        String coord;

        if (partes.length == 1) {
            cmd = "R";  // revelar
            coord = partes[0];
        } else {
            cmd = partes[0];
            coord = partes[1];
        }

        int fila = Character.toUpperCase(coord.charAt(0)) - 'A';
        int col = Integer.parseInt(coord.substring(1)) - 1;

        if (cmd.equalsIgnoreCase("F")) {
            tablero.marcar(fila, col);
            return true;
        }

        return tablero.revelar(fila, col);
    }


    public void guardar(String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(tablero);
            view.mostrarMensaje("💾 Partida guardada.");
        } catch (IOException e) {
            view.mostrarMensaje("Error al guardar: " + e.getMessage());
        }
    }

    public void cargar(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            tablero = (Tablero) ois.readObject();
            view.mostrarMensaje("📂 Partida cargada.");
        } catch (Exception e) {
            view.mostrarMensaje("Error al cargar: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new GameController().run();
    }
}
