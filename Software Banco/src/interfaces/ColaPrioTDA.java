package interfaces;
import objects.Cliente;

public interface ColaPrioTDA {
	public void inicializarCola();
	
	public void acolarPrioridad (Cliente x, int prioridad);

	public void desacolar ();
	
	public boolean colaVacia ();
	
	public Cliente primero ();
	
	public int prioridad ();
}