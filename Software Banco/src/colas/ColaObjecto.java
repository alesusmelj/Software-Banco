package colas;
import objects.Cliente;
import interfaces.ColaObjectoTDA;

public class ColaObjecto implements ColaObjectoTDA{
    Cliente[] a;
    public int index;

    public void inicializarCola () {
        a = new Cliente[100];
        index = 0;
    }

    public void acolar (Cliente x) {
        a[index] = x;
        index++;
    }

    public void desacolar () {
        for (int i = 0; i < index - 1; i++) {
            a[i] = a[i+1];
        }
        index--;        
    }

    public boolean colaVacia() {
        return (index == 0);
    }

    public Cliente primero() {
        return a[0];
    }    
}
