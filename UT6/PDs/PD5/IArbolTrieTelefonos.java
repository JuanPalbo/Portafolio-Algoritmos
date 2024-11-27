package ut6.ut6_pd5;

import java.util.LinkedList;

public interface IArbolTrieTelefonos {

    LinkedList<TAbonado> buscarTelefonos(String pais, String area);

    void insertar(TAbonado unAbonado);
    
}
