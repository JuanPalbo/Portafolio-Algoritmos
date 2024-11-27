public class Marcapasos {
    // Variables para almacenar la información con la menor cantidad de memoria posible
    private short presionSanguinea;          // Presión Sanguínea (0-250), tipo short (16 bits)
    private byte frecuenciaCardiaca;         // Frecuencia cardíaca (0-226), tipo byte (8 bits)
    private int nivelAzucarSangre;           // Nivel de azúcar en sangre (0 - 1000), tipo int (32 bits)
    private long maximaFuerza;               // Máxima fuerza a la que fue expuesto (0-3.000.000.000), tipo long (64 bits)
    private float minimoTiempoEntreLatidos;  // Mínimo tiempo entre latidos (0-100 con decimales), tipo float (32 bits)
    private double bateriaRestante;          // Batería restante con decimales (máxima precisión), tipo double (64 bits)
    private String codigoFabricante;         // Código del Fabricante (máximo 8 caracteres), tipo String

    public Marcapasos(short presionSanguinea, byte frecuenciaCardiaca, int nivelAzucarSangre, long maximaFuerza,
                      float minimoTiempoEntreLatidos, double bateriaRestante, String codigoFabricante) {
        this.presionSanguinea = presionSanguinea;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.nivelAzucarSangre = nivelAzucarSangre;
        this.maximaFuerza = maximaFuerza;
        this.minimoTiempoEntreLatidos = minimoTiempoEntreLatidos;
        this.bateriaRestante = bateriaRestante;
        this.codigoFabricante = codigoFabricante;
    }

    public short getPresionSanguinea() {
        return presionSanguinea;
    }

    public byte getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public int getNivelAzucarSangre() {
        return nivelAzucarSangre;
    }

    public long getMaximaFuerza() {
        return maximaFuerza;
    }

    public float getMinimoTiempoEntreLatidos() {
        return minimoTiempoEntreLatidos;
    }

    public double getBateriaRestante() {
        return bateriaRestante;
    }

    public String getCodigoFabricante() {
        return codigoFabricante;
    }

    // Método main para crear un objeto de prueba
    public static void main(String[] args) {
        Marcapasos marcapasos = new Marcapasos((short) 120, (byte) 80, 90, 1500000000L, 0.85f, 99.99, "ABC12345");
        System.out.println("Presión Sanguínea: " + marcapasos.getPresionSanguinea());
        System.out.println("Frecuencia Cardíaca: " + marcapasos.getFrecuenciaCardiaca());
        System.out.println("Nivel de Azúcar en Sangre: " + marcapasos.getNivelAzucarSangre());
        System.out.println("Máxima Fuerza: " + marcapasos.getMaximaFuerza());
        System.out.println("Mínimo Tiempo entre Latidos: " + marcapasos.getMinimoTiempoEntreLatidos());
        System.out.println("Batería Restante: " + marcapasos.getBateriaRestante());
        System.out.println("Código del Fabricante: " + marcapasos.getCodigoFabricante());
    }
}
