package colas;
import interfaces.ColaPrioTDA;
import objects.Cliente;

public class ColaPrio implements ColaPrioTDA{
    Cliente[] elementos;
	int[] prioridades;
	int index;

	public void inicializarCola () {
		elementos = new Cliente[100];
		prioridades = new int[100];
		index = 0;
	}
	
	public void acolarPrioridad (Cliente x, int prioridad) {
		int j;
		for(j = index; j > 0 && prioridades[j-1] <= prioridad; j--) {
			elementos[j] = elementos[j-1];
			prioridades[j] = prioridades[j-1];
		}
		elementos[j] = x;
		prioridades[j] = prioridad;
		index++;
	}

	public void desacolar () {
		index--;
	}
	
	public boolean colaVacia () {
		return (index==0);
	}
	
	public Cliente primero () {
		return elementos[index-1];
	}
	
	public int prioridad () {
		return prioridades[index-1];
	}
}
