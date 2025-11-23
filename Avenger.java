public class Avenger {
    //Atributos
    private int id;
    private  String nombre;
    private  String mision;
    private int peligrosidad;
    private double pagomensual;
    //Metodos
    public Avenger(int id, String nombre, String mision, int peligrosidad, double pagomensual) {
        this.id = id;
        this.nombre = nombre;
        this.mision = mision;
        this.peligrosidad = peligrosidad;
        this.pagomensual = pagomensual;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public int getPeligrosidad() {
        return peligrosidad;
    }

    public void setPeligrosidad(int peligrosidad) {
        this.peligrosidad = peligrosidad;
    }

    public double getPagomensual() {
        return pagomensual;
    }

    public void setPagomensual(double pagomensual) {
        this.pagomensual = pagomensual;
    }

    //Metodo que calcula el Aporte fondo
    public double calcularAporteFondo(){
        return this.pagomensual * 0.08;
    }
    //Metodo que calcula el impuesto dependiendo del pagoAnual
    public double calcularImpuesto(){
        double pagoAnual = this.pagomensual * 12;
        double exceso;
        double impuesto = 0;

        if (pagoAnual <= 50000){
            return 0;
        } else if (pagoAnual > 50000 && pagoAnual <= 100000) {
            exceso = pagoAnual - 50000;
            impuesto = exceso * 0.10;
        } else if (pagoAnual > 100000 && pagoAnual <= 200000) {
            exceso = pagoAnual - 100000;
            impuesto = exceso * 0.20;
        } else if (pagoAnual > 200000) {
            exceso = pagoAnual - 200000;
            impuesto = exceso * 0.30;
        }

        return impuesto;
    }
    //Metodo que calcula el pago Neto
    public double calcularPagoNeto(){
        return this.pagomensual - calcularAporteFondo() - (calcularImpuesto() / 12);
    }

    @Override
    public String toString() {
        return "Avenger: " +
                " | ID: " + id +
                " | Nombre: " + nombre +
                " | Mision: " + mision +
                " | Peligrosidad: " + peligrosidad +
                " | Pagomensual: " + pagomensual;
    }
}
