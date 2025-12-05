package PruebasUnitarias;

import model.Casilla;

public class CasillaTestManual {

    public static void main(String[] args) {
        CasillaTestManual test = new CasillaTestManual();
        test.testValoresIniciales();
        test.testSettersYGetters();
    }

    public void testValoresIniciales() {
        System.out.println("=== Ejecutando testValoresIniciales ===");
        Casilla c = new Casilla();

        boolean ok = !c.isMina() && !c.isDescubierta() && !c.isMarcada() && c.getAdyacentes() == 0;

        if (ok) {
            System.out.println("✅ Los valores iniciales son correctos\n");
        } else {
            System.out.println("❌ Los valores iniciales son incorrectos\n");
        }
    }

    public void testSettersYGetters() {
        System.out.println("=== Ejecutando testSettersYGetters ===");
        Casilla c = new Casilla();

        c.setMina(true);
        c.setDescubierta(true);
        c.setMarcada(true);
        c.setAdyacentes(3);

        boolean ok = c.isMina() && c.isDescubierta() && c.isMarcada() && c.getAdyacentes() == 3;

        if (ok) {
            System.out.println("✅ Los setters y getters funcionan correctamente\n");
        } else {
            System.out.println("❌ Error en setters/getters\n");
        }
    }
}