˂public class Jugador˃
    private String nombre;
    private int partidasJugadas;
    private int partidasGanadas;
    private int partidasPerdidas;

    ˂public Jugador(String nombre)˃
        this.nombre = nombre;
        this.partidasJugadas = 0;
        this.partidasGanadas = 0;
        this.partidasPerdidas = 0;
    
    ˂Getters y Setters
    public String getNombre()˃
        return nombre;
    
    ˂public int getPartidasJugadas()˃
        return partidasJugadas;
    
    ˂public int getPartidasGanadas()˃
        return partidasGanadas;
    
    ˂public int getPartidasPerdidas()˃
        return partidasPerdidas;
    
    ˂public void incrementarPartidasJugadas()˃
        this.partidasJugadas++;
   
    ˂public void incrementarPartidasGanadas()˃
        this.partidasGanadas++;
   
    ˂public void incrementarPartidasPerdidas()˃
        this.partidasPerdidas++;

    @Override
    ˂public String toString()˃
        return "Jugador: " + nombre +
               "\nPartidas jugadas: " + partidasJugadas +
               "\nGanadas: " + partidasGanadas +
               "\nPerdidas: " + partidasPerdidas;
