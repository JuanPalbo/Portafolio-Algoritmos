import java.util.LinkedList;

public class TArbolTrieTelefonos implements IArbolTrieTelefonos {

    private TNodoTrieTelefonos raiz;

    @Override
    public LinkedList<TAbonado> buscarTelefonos(String pais, String area) {
        LinkedList<TAbonado> listaAbonados = new LinkedList<>();
        if (raiz != null) {
            raiz.buscarTelefonos(pais + area, listaAbonados);
        }
        return listaAbonados;
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        if (raiz == null) {
            raiz = new TNodoTrieTelefonos();
        }
        raiz.insertar(unAbonado);
    }
}
