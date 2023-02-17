package ejecucion;
import interfaces.*;
import colas.*;
import objects.Cliente;
import java.util.Scanner;

public class SimulacroBanco {
    
    public static void main (String[] args) {
		
		ColaObjecto colaJubilados = new ColaObjecto();
		ColaObjecto colaClientes = new ColaObjecto();
		ColaObjecto colaNoClientes = new ColaObjecto();
		
		ColaPrioTDA colaServicios = new ColaPrio();
		ColaPrioTDA colaMix1 = new ColaPrio();
		ColaPrioTDA colaMix2 = new ColaPrio();

		colaJubilados.inicializarCola();
		colaClientes.inicializarCola();
		colaNoClientes.inicializarCola();
		
		colaServicios.inicializarCola();
		colaMix1.inicializarCola();
		colaMix2.inicializarCola();
		
		AgregarClientes(colaJubilados, colaClientes, colaNoClientes, colaServicios, colaMix1, colaMix2);
		AsignarEspera(colaJubilados, colaClientes, colaNoClientes, colaServicios, colaMix1, colaMix2);
		
	}

	public static void AgregarClientes(ColaObjecto colaJubilados, ColaObjecto colaClientes, ColaObjecto colaNoClientes, ColaPrioTDA colaServicios, ColaPrioTDA colaMix1, ColaPrioTDA colaMix2) {
		Scanner sc = new Scanner(System.in);
		int nroTurno = 1;
		int cantColaMix1 = 0;
		int cantColaMix2 = 0;
		int cantidadServicios = 0;
		Boolean finalizador = true;
		int esperaServicios = 0;
		int esperaColaMix1 = 0;
		int esperaColaMix2 = 0;
		
		do{

			System.out.println("Ingrese el tipo de cliente (J, C, P, PF, CG, CH) o escriba -1 para finalizar: ");
			String tipoCliente = sc.nextLine();
			System.out.println();

			if(tipoCliente.equals("J")){
				
				System.out.println("Cantidad Clientes Jubilados: ");
				int cantJubilados = sc.nextInt();
				int espera = 0;

				if(colaJubilados.index != 7){
					do{
						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(30);
						client.setTiempoDeEspera(espera);
				
						espera = (espera + client.getTiempoDeTramite());
						colaJubilados.acolar(client);
						nroTurno++;
						cantJubilados --;

					} while(cantJubilados != 0 && colaJubilados.index != 7);						
				}
				if (colaJubilados.index == 7){
					
					espera = 0;

					while(cantColaMix1 != 7 && cantJubilados != 0) {

						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(30);
						client.setTiempoDeEspera(esperaColaMix1);
			
						esperaColaMix1 = (espera + client.getTiempoDeTramite() + esperaColaMix1);

						colaMix1.acolarPrioridad(client, 30);
						cantColaMix1 ++;
						nroTurno++;
						cantJubilados --;
					} 
					
					espera = 0;
					while (cantColaMix2 != 7 && cantJubilados != 0) {

						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(30);
						client.setTiempoDeEspera(esperaColaMix2);
			
						esperaColaMix2 = (espera + client.getTiempoDeTramite() + esperaColaMix2);
					
						colaMix2.acolarPrioridad(client, 30);
						cantColaMix2 ++;
						nroTurno++;
						cantJubilados --;
					}
					if(cantJubilados != 0){
						System.out.println();
						System.out.println("Las colas Extra estan llenas, " + cantJubilados + " clientes deben esperar.");	
						System.out.println();	
					}
				}
			}
		
		
			else if(tipoCliente.equals("C")){
				System.out.println("Cantidad Clientes: ");
				int cantClientes = sc.nextInt();
				int espera = 0;

				if(colaClientes.index != 7) {
					do{
						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(40);
						client.setTiempoDeEspera(espera);
				
						espera = (espera + client.getTiempoDeTramite());
						colaClientes.acolar(client);
						nroTurno++;
						cantClientes --;

					} while(cantClientes != 0 && colaClientes.index != 7);			
				}
				if (colaClientes.index == 7){

					espera = 0;
					
					while(cantColaMix1 != 7 && cantClientes != 0) {
						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(40);
						client.setTiempoDeEspera(esperaColaMix1);
			
						esperaColaMix1 = (espera + client.getTiempoDeTramite() + esperaColaMix1);

						colaMix1.acolarPrioridad(client, 40);
						cantColaMix1 ++;
						nroTurno++;
						cantClientes --;
					}

					espera = 0;
					while(cantColaMix2 != 7 && cantClientes != 0) {
						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(40);
						client.setTiempoDeEspera(esperaColaMix2);
			
						esperaColaMix2 = (espera + client.getTiempoDeTramite() + esperaColaMix2);
					
						colaMix2.acolarPrioridad(client, 40);
						cantColaMix2 ++;
						nroTurno++;
						cantClientes --;
					} 
				
					if(cantClientes != 0){
						System.out.println();
						System.out.println("Las colas Extra estan llenas, " + cantClientes + " clientes deben esperar.");		
						System.out.println();
					}
				}	
			}

			else if(tipoCliente.equals("P")){
				System.out.println("Cantidad Clientes No Clientes: ");
				int cantNoClientes = sc.nextInt();
				int espera = 0;

				if(colaNoClientes.index != 7) {
					do{
						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(10);
						client.setTiempoDeEspera(espera);
				
						espera = (espera + client.getTiempoDeTramite());
						colaNoClientes.acolar(client);
						nroTurno++;
						cantNoClientes --;

					} while(cantNoClientes != 0 && colaNoClientes.index != 7);			
				}
				if (colaNoClientes.index == 7){

					espera = 0;
						
					while(cantColaMix1 != 7 && cantNoClientes != 0) {

						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(10);
						client.setTiempoDeEspera(esperaColaMix1);
			
						esperaColaMix1 = (espera + client.getTiempoDeTramite() + esperaColaMix1);
						
						colaMix1.acolarPrioridad(client, 10);
						cantColaMix1 ++;
						nroTurno++;
						cantNoClientes --;
					}
					
					espera = 0;
					while(cantColaMix2 != 7 && cantNoClientes != 0) {

						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(10);
						client.setTiempoDeEspera(esperaColaMix2);
			
						esperaColaMix2 = (espera + client.getTiempoDeTramite() + esperaColaMix2);

						colaMix2.acolarPrioridad(client, 10);
						cantColaMix2 ++;
						nroTurno++;
						cantNoClientes --;
					} 
				
					if(cantNoClientes != 0){
						System.out.println();
						System.out.println("Las colas Extra estan llenas, " + cantNoClientes + " clientes deben esperar.");	
						System.out.println();	
					}
				}	
			}
			
			else if(tipoCliente.equals("PF")){
			
				System.out.println("Cantidad Clientes ¨Plazo Fijo¨: ");
				int cantPlazoFijo = sc.nextInt();
				int espera = 0;

				if(cantidadServicios != 7) {
					do{
						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(10);
						client.setTiempoDeEspera(esperaServicios);
				
						esperaServicios = (espera + client.getTiempoDeTramite() + esperaServicios);
						colaServicios.acolarPrioridad(client, client.getTiempoDeEspera());
						nroTurno ++;
						cantPlazoFijo --;
						cantidadServicios ++;

					} while(cantPlazoFijo != 0 && cantidadServicios != 7 );
				}
				if(cantidadServicios == 7){

					espera = 0;

					while(cantColaMix1 != 7 && cantPlazoFijo != 0) {

						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(10);
						client.setTiempoDeEspera(esperaColaMix1);
			
						esperaColaMix1 = (espera + client.getTiempoDeTramite() + esperaColaMix1);

						colaMix1.acolarPrioridad(client, client.getTiempoDeEspera());
						nroTurno ++;
						cantPlazoFijo --;
						cantColaMix1 ++;
					}

					espera = 0;
					while(cantColaMix2 != 7 && cantPlazoFijo != 0){
						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(10);
						client.setTiempoDeEspera(esperaColaMix2);
			
						esperaColaMix2 = (espera + client.getTiempoDeTramite() + esperaColaMix2);
						
						colaMix2.acolarPrioridad(client, client.getTiempoDeEspera());
						nroTurno ++;
						cantPlazoFijo --;
						cantColaMix2 ++;
					}		
					if (cantPlazoFijo != 0){
						System.out.println();
						System.out.println("Las colas Extra estan llenas, " + cantPlazoFijo + " clientes deben esperar.");	
						System.out.println();	
					}
				}					
			}	

			else if(tipoCliente.equals("CH")){

				System.out.println("Cantidad Clientes ¨CreditosHipotecarios¨: ");
				int cantCreditosHipotecarios = sc.nextInt();
				int espera = 0;

				if(cantidadServicios != 7) {
					do{
						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(10);
						client.setTiempoDeEspera(esperaServicios);
				
						esperaServicios = (espera + client.getTiempoDeTramite() + esperaServicios);
						colaServicios.acolarPrioridad(client, client.getTiempoDeEspera());
						nroTurno ++;
						cantCreditosHipotecarios --;
						cantidadServicios ++;

					} while(cantCreditosHipotecarios != 0 && cantidadServicios != 7 );
				}
				if(cantidadServicios == 7){

					espera = 0;

					while(cantColaMix1 != 7 && cantCreditosHipotecarios != 0) {

						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(10);
						client.setTiempoDeEspera(esperaColaMix1);
			
						esperaColaMix1 = (espera + client.getTiempoDeTramite() + esperaColaMix1);

						colaMix1.acolarPrioridad(client, client.getTiempoDeEspera());
						nroTurno ++;
						cantCreditosHipotecarios --;
						cantColaMix1 ++;
					}

					espera = 0;
					while(cantColaMix2 != 7 && cantCreditosHipotecarios != 0){
						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(10);
						client.setTiempoDeEspera(esperaColaMix2);
			
						esperaColaMix2 = (espera + client.getTiempoDeTramite() + esperaColaMix2);
						
						colaMix2.acolarPrioridad(client, client.getTiempoDeEspera());
						nroTurno ++;
						cantCreditosHipotecarios --;
						cantColaMix2 ++;
					}		
					if (cantCreditosHipotecarios != 0){
						System.out.println();
						System.out.println("Las colas Extra estan llenas, " + cantCreditosHipotecarios + " clientes deben esperar.");	
						System.out.println();
					} 
				}					
			}
			
			else if(tipoCliente.equals("CG")){
			
				System.out.println("Cantidad Clientes ¨CreditosGenerales¨: ");
				int cantCreditosGenerales = sc.nextInt();
				int espera = 0;

				if(cantidadServicios != 7) {
					do{
						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(10);
						client.setTiempoDeEspera(esperaServicios);
				
						esperaServicios = (espera + client.getTiempoDeTramite() + esperaServicios);
						colaServicios.acolarPrioridad(client, client.getTiempoDeEspera());
						nroTurno ++;
						cantCreditosGenerales --;
						cantidadServicios ++;

					} while(cantCreditosGenerales != 0 && cantidadServicios != 7 );
				}
				if(cantidadServicios == 7){

					espera = 0;

					while(cantColaMix1 != 7 && cantCreditosGenerales != 0) {

						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(10);
						client.setTiempoDeEspera(esperaColaMix1);
			
						esperaColaMix1 = (espera + client.getTiempoDeTramite() + esperaColaMix1);

						colaMix1.acolarPrioridad(client, client.getTiempoDeEspera());
						nroTurno ++;
						cantCreditosGenerales --;
						cantColaMix1 ++;
					}

					espera = 0;
					while(cantColaMix2 != 7 && cantCreditosGenerales != 0){
						Cliente client= new Cliente();
						client.setNroTurno(nroTurno);
						client.setNomenclatura(tipoCliente);
						client.setTiempoDeTramite(10);
						client.setTiempoDeEspera(esperaColaMix2);
			
						esperaColaMix2 = (espera + client.getTiempoDeTramite() + esperaColaMix2);
						
						colaMix2.acolarPrioridad(client, client.getTiempoDeEspera());
						nroTurno ++;
						cantCreditosGenerales --;
						cantColaMix2 ++;
					}		
					if (cantCreditosGenerales != 0){
						System.out.println();
						System.out.println("Las colas Extra estan llenas, " + cantCreditosGenerales + " clientes deben esperar.");	
						System.out.println();
					}
				}					
			} 
			/*else                             CODIGO PARA VALIDAR QUE EL USUARIO INGRESE UNA NOMENCLATURA CORRECTA. OCURRE ALGUN ERROR CON JAVA PORQUE SIEMPRE ENTRA AL ELSE POR MAS DE QUE HAYA ENTRADO EN UN ELSE IF.
				System.out.println();
				System.out.println("ERROR. Ha ingresado una nomeclantura incorrecta. Utilice Mayusculas.");
			System.out.println();*/

			if(tipoCliente.equals("-1"))
				finalizador = false;

		} while(finalizador); 
	} 

	public static void AsignarEspera(ColaObjecto colaJubilados, ColaObjecto colaClientes, ColaObjecto colaNoClientes, ColaPrioTDA colaServicios, ColaPrioTDA colaMix1, ColaPrioTDA colaMix2) {
		
		System.out.println("PUESTO JUBILADOS");
		while(!colaJubilados.colaVacia()) {
			System.out.println("----------------------------------");
			System.out.println("Ticket: " + colaJubilados.primero().getnroTurno());
			System.out.println("Espera: " + colaJubilados.primero().getTiempoDeEspera() + " Minutos");
			System.out.println("Sera atendido en el puesto: " + colaJubilados.primero().getNomenclatura());
			colaJubilados.desacolar();
		}
		
		System.out.println("");
		
		System.out.println("PUESTO CLIENTES");
		while(!colaClientes.colaVacia()) {
			System.out.println("----------------------------------");
			System.out.println("Ticket: " + colaClientes.primero().getnroTurno());
			System.out.println("Espera: " + colaClientes.primero().getTiempoDeEspera() + " Minutos");
			System.out.println("Sera atendido en el puesto: " + colaClientes.primero().getNomenclatura());
			colaClientes.desacolar();
		}
		
		System.out.println("");
		
		System.out.println("PUESTO NO CLIENTES");
		while(!colaNoClientes.colaVacia()) {
			System.out.println("----------------------------------");
			System.out.println("Ticket: " + colaNoClientes.primero().getnroTurno());
			System.out.println("Espera: " + colaNoClientes.primero().getTiempoDeEspera() + " Minutos");
			System.out.println("Sera atendido en el puesto: " + colaNoClientes.primero().getNomenclatura());
			colaNoClientes.desacolar();
		}
		
		System.out.println("");
		
		System.out.println("PUESTO SERVICIOS");
		while(!colaServicios.colaVacia()) {
			System.out.println("----------------------------------");
			System.out.println("Ticket: " + colaServicios.primero().getnroTurno());
			System.out.println("Espera: " + colaServicios.primero().getTiempoDeEspera() + " Minutos");
			System.out.println("Sera atendido en el puesto: " + colaServicios.primero().getNomenclatura());
			colaServicios.desacolar();
		}
		
		System.out.println("");
		
		System.out.println("PUESTO MIX 1");
		while(!colaMix1.colaVacia()) {
			System.out.println("----------------------------------");
			System.out.println("Ticket: " + colaMix1.primero().getnroTurno());
			System.out.println("Espera: " + colaMix1.primero().getTiempoDeEspera() + " Minutos");
			System.out.println("Sera atendido en el puesto: " + colaMix1.primero().getNomenclatura());
			colaMix1.desacolar();

		}
		
		System.out.println("");
		
		System.out.println("PUESTO MIX 2");
		while(!colaMix2.colaVacia()) {
			System.out.println("----------------------------------");
			System.out.println("Ticket: " + colaMix2.primero().getnroTurno());
			System.out.println("Espera: " + colaMix2.primero().getTiempoDeEspera() + " Minutos");
			System.out.println("Sera atendido en el puesto: " + colaMix2.primero().getNomenclatura());
			colaMix2.desacolar();
			}
		}
	}

