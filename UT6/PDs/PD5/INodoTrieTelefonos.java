package ut6.ut6_pd5;

import java.util.LinkedList;

public interface INodoTrieTelefonos {

    void buscarTelefonos(String primerosDigitos, LinkedList<TAbonado> abonados);

    void insertar(TAbonado unAbonado);
    
}
