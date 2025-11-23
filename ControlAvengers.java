import java.util.ArrayList;
import java.util.List;

public class ControlAvengers {
    //Atributos
    private List<Avenger> listaAvengers;
    //Metodos
    public ControlAvengers() {
        this.listaAvengers = new ArrayList<Avenger>();
    }
    public List<Avenger> getListaAvengers() {
        return listaAvengers;
    }

    //metodo de agregar nuevo avenger
    public void agregarAvenger(Avenger av){
        listaAvengers.add(av);
        ordenarListAvengersID();

    }

    //Metodo de busqueda binaria para poder buscar y eliminar un Avenger segun su id
    public boolean eliminarAvenger(int id){
        int i = 0;
        int f = listaAvengers.size() - 1;
        int m;
        while (i<=f){
            m = (i+f)/2;
            if (listaAvengers.get(m).getId() == id){
                listaAvengers.remove(m);
                return  true;
            } else if (id<listaAvengers.get(m).getId()) {
                f = m - 1;
            }else {
                i = m + 1;
            }
        }
        return false;
    }


    //Metodo de Ordenamiento de algoritmo de burbuja, ordenamos por ID
    public void ordenarListAvengersID(){
        Avenger aux;
        for (int i = 0; i<listaAvengers.size() - 1; i++){
            for (int j = i + 1; j<listaAvengers.size(); j++){
                if (listaAvengers.get(i).getId()>listaAvengers.get(j).getId()){
                    aux = listaAvengers.get(i);
                    listaAvengers.set(i, listaAvengers.get(j));
                    listaAvengers.set(j, aux);
                }
            }
        }
    }


    //Metoto que suma el total de aportes de todos los Avengers
    public double totalAportes(){
        double totalAportes = 0;
        for (Avenger av: listaAvengers){
            totalAportes = totalAportes + av.calcularAporteFondo();
        }

        return totalAportes;
    }

    //Metoto que suma el total de los impuestos de todos los Avengers
    public double totalImpuestos(){
        double totalImpuestos = 0;
        for (Avenger av: listaAvengers){
            totalImpuestos = totalImpuestos + av.calcularImpuesto();
        }

        return totalImpuestos;
    }

    //Metoto que suma el total de los pagos netos de todos los Avengers
    public double totalPagosNetos(){
        double totalPagosNetos = 0;
        for (Avenger av: listaAvengers){
            totalPagosNetos = totalPagosNetos + av.calcularPagoNeto();
        }

        return totalPagosNetos;
    }

    //
}
