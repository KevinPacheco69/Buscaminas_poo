package model;

import java.io.Serializable;
import java.util.Random;
import java.util.ArrayDeque;
import java.util.Queue;

public class Tablero implements Serializable {

    private final int filas = 10;
    private final int cols = 10;
    private final int minas = 10;

    private Casilla[][] casillas;

    public Tablero(int i, int j, int k) {
        casillas = new Casilla[filas][cols];
        for (int r = 0; r < filas; r++)
            for (int c = 0; c < cols; c++)
                casillas[r][c] = new Casilla();

        inicializar();
    }

    public void inicializar() {
        colocarMinas();
        calcularAdyacentes();
    }

    private void colocarMinas() {
        Random rnd = new Random();
        int colocadas = 0;

        while (colocadas < minas) {
            int r = rnd.nextInt(filas);
            int c = rnd.nextInt(cols);

            if (!casillas[r][c].isMina()) {
                casillas[r][c].setMina(true);
                colocadas++;
            }
        }
    }

    private boolean enRango(int r, int c) {
        return r >= 0 && r < filas && c >= 0 && c < cols;
    }

    private void calcularAdyacentes() {
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < cols; c++) {

                if (casillas[r][c].isMina()) {
                    casillas[r][c].setAdyacentes(-1);
                    continue;
                }

                int cuenta = 0;
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        int nr = r + dr, nc = c + dc;
                        if (enRango(nr, nc) && casillas[nr][nc].isMina())
                            cuenta++;
                    }
                }
                casillas[r][c].setAdyacentes(cuenta);
            }
        }
    }

    public Casilla getCasilla(int r, int c) {
        return casillas[r][c];
    }

    public boolean revelar(int r, int c) throws CasillaYaDescubiertaException {
        if (!enRango(r, c))
            throw new ArrayIndexOutOfBoundsException("Coordenadas fuera del tablero");

        Casilla casilla = casillas[r][c];

        if (casilla.isDescubierta())
            throw new CasillaYaDescubiertaException("La casilla ya fue descubierta");

        casilla.setDescubierta(true);

        if (casilla.isMina())
            return false; // pierde

        if (casilla.getAdyacentes() == 0)
            floodFill(r, c);

        return true;
    }

    private void floodFill(int sr, int sc) {
        boolean[][] visitado = new boolean[filas][cols];
        Queue<int[]> cola = new ArrayDeque<>();

        cola.add(new int[] { sr, sc });
        visitado[sr][sc] = true;

        while (!cola.isEmpty()) {
            int[] pos = cola.poll();
            int r = pos[0], c = pos[1];

            casillas[r][c].setDescubierta(true);

            if (casillas[r][c].getAdyacentes() > 0)
                continue;

            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    int nr = r + dr, nc = c + dc;

                    if (enRango(nr, nc) && !visitado[nr][nc] &&
                        !casillas[nr][nc].isMina() && !casillas[nr][nc].isDescubierta()) {

                        visitado[nr][nc] = true;
                        cola.add(new int[] { nr, nc });
                    }
                }
            }
        }
    }

    public void marcar(int r, int c) {
        if (!enRango(r, c))
            throw new ArrayIndexOutOfBoundsException("Coordenadas fuera del tablero");

        Casilla casilla = casillas[r][c];

        if (!casilla.isDescubierta()) {
            casilla.setMarcada(!casilla.isMarcada());
        }
    }

    public void revelarTodasMinas() {
        for (int r = 0; r < filas; r++)
            for (int c = 0; c < cols; c++)
                if (casillas[r][c].isMina())
                    casillas[r][c].setDescubierta(true);
    }

    public boolean isVictoria() {
        int descubiertas = 0;

        for (int r = 0; r < filas; r++)
            for (int c = 0; c < cols; c++)
                if (casillas[r][c].isDescubierta())
                    descubiertas++;

        return descubiertas == (filas * cols - minas);
    }

    public int getFilas() { return filas; }
    public int getCols() { return cols; }
}
