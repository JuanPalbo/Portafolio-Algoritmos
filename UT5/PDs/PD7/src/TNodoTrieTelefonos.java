import java.util.LinkedList;

public class TNodoTrieTelefonos implements INodoTrieTelefonos {

    private TNodoTrieTelefonos[] hijos;
    private static final int CANT_CHR_NUMEROS = 10; 
    protected boolean esAbonado;
    protected TAbonado abonado;

    public TNodoTrieTelefonos() {
        hijos = new TNodoTrieTelefonos[CANT_CHR_NUMEROS];
        esAbonado = false;
        abonado = null;
    }

    @Override
    public void buscarTelefonos(String primerosDigitos, LinkedList<TAbonado> abonados) {
        if (primerosDigitos.isEmpty()) {
            return;
        }
        TNodoTrieTelefonos nodo = this;
        for (int i = 0; i < primerosDigitos.length(); i++) {
            int indice = primerosDigitos.charAt(i) - '0';
            if (nodo.hijos[indice] == null) {
                return;
            }
            nodo = nodo.hijos[indice];
        }

        buscarAbonados(nodo, abonados);
    }

    private void buscarAbonados(TNodoTrieTelefonos nodo, LinkedList<TAbonado> abonados) {
        if (nodo == null) {
            return;
        }
        if (nodo.esAbonado && nodo.abonado != null) {
            abonados.add(nodo.abonado);
        }
        for (int i = 0; i < CANT_CHR_NUMEROS; i++) {
            if (nodo.hijos[i] != null) {
                nodo.hijos[i].buscarAbonados(nodo.hijos[i], abonados);
            }
        }
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        TNodoTrieTelefonos nodo = this;
        String telefono = unAbonado.getTelefono();

        for (int c = 0; c < telefono.length(); c++) {
            int indice = telefono.charAt(c) - '0';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrieTelefonos();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esAbonado = true;
        nodo.abonado = unAbonado;
    }
}
