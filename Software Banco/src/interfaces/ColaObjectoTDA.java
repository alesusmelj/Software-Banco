package interfaces;
import objects.Cliente;

public interface ColaObjectoTDA {

    public void inicializarCola ();

    public void acolar (Cliente x);

    public void desacolar ();

    public boolean colaVacia() ;

    public Cliente primero();
}
