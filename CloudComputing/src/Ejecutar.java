
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ejecutar {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
	
		Scanner reader = new Scanner(System.in);
		List<Future<Long>> list = new ArrayList<Future<Long>>();
		ArrayList<Long> ListaTiempo = new ArrayList<Long>();  
		System.out.print("ingrese numeros de hilos a usar:");
		int numeroHilos = reader.nextInt();
		
	int LIMITE = 200000/numeroHilos;
	

	ExecutorService servicio= Executors.newFixedThreadPool(numeroHilos);
	int inferior = 1;
	int superior = LIMITE;
	long initialTime = System.currentTimeMillis() ;
	
	for(int i = 1 ; i<=numeroHilos; i++) {
		
		Future<Long> resultado= servicio.submit(new Nprimos(superior, inferior));
		list.add(resultado);
		inferior = superior+1;
		superior += LIMITE;
		if(superior>LIMITE*numeroHilos) break;
	}
	
	
	// se termina de ejecutar cuando todos los hilos terminan de trabajar.
	for(Future<Long> resultado : list){
        try {        	
        	ListaTiempo.add(resultado.get());        	
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
       
    }
	
	 System.out.print(" tiempo ejecución: "+(System.currentTimeMillis()-initialTime));
	servicio.shutdown();
	

	}
}


