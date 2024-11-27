package ut6.ut6_pd5;

import java.util.Collections;
import java.util.LinkedList;

public class TArbolTrieTelefonos implements IArbolTrieTelefonos {

    private TNodoTrieTelefonos raiz;

    public TArbolTrieTelefonos() {
        raiz = new TNodoTrieTelefonos();
    }

    @Override
    public LinkedList<TAbonado> buscarTelefonos(String pais, String area) {
        LinkedList<TAbonado> abonados = new LinkedList<>();
        if (raiz != null) {
            raiz.buscarTelefonos(pais + area, abonados);
        }
        Collections.sort(abonados);
        return abonados;
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        if (raiz == null) {
            raiz = new TNodoTrieTelefonos();
        }
        raiz.insertar(unAbonado);
    }
}
