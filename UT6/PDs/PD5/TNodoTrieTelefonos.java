package ut6.ut6_pd5;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TNodoTrieTelefonos implements INodoTrieTelefonos {

    private Map<Character, TNodoTrieTelefonos> hijos;
    private LinkedList<TAbonado> abonados;

    public TNodoTrieTelefonos() {
        hijos = new HashMap<>();
        abonados = new LinkedList<>();
    }

    @Override
    public void buscarTelefonos(String primerosDigitos, LinkedList<TAbonado> abonados) {
        if (primerosDigitos.isEmpty()) {
            abonados.addAll(this.abonados);
            for (TNodoTrieTelefonos hijo : hijos.values()) {
                hijo.buscarTelefonos(primerosDigitos, abonados);
            }
        } else {
            char primerCaracter = primerosDigitos.charAt(0);
            TNodoTrieTelefonos hijo = hijos.get(primerCaracter);
            if (hijo != null) {
                hijo.buscarTelefonos(primerosDigitos.substring(1), abonados);
            }
        }
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        TNodoTrieTelefonos nodo = this;
        String telefono = unAbonado.getTelefono();
        for (int i = 0; i < telefono.length(); i++) {
            char caracter = telefono.charAt(i);
            nodo.hijos.putIfAbsent(caracter, new TNodoTrieTelefonos());
            nodo = nodo.hijos.get(caracter);
        }
        nodo.abonados.add(unAbonado);
    }
}
