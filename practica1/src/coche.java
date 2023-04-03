public class coche {

    int numeroP=0;

    coche(){

        System.out.println("Constructor Coche");
    }

    public void agregarP(){
        numeroP++;
        System.out.println("Se agrego una puerta");
    }

    public void mostrarP(){
        System.out.println("La cantidad de puertas es:"+numeroP);
    }
}
