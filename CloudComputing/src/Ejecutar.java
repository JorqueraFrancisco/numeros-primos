
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ejecutar {

	public static void main(String[] args) throws InterruptedException, ExecutionException {		
		/*
		 * Objeto para leer por consola.                  
		 */	
		Scanner reader = new Scanner(System.in);
		/*
		 * Limite maximo de números.                  
		 */	
		List<Future<Long>> list = new ArrayList<Future<Long>>();
		/*
		 * Lista que guarda los tiempos de ejecución de cada hilo.                  
		 */	
		ArrayList<Long> ListaTiempo = new ArrayList<Long>();
		System.out.print("ingrese numeros limite de numeros para analizar:");
		/*
		 * Variable que guarda el numero captado por consola.                  
		 */	
		int numeros = reader.nextInt();
		/*
		 * Variable que incrementa para generar los hilos.                  
		 */	
		int hilos = 1;
		/*
		 * Variable que guarda el tiempo de ejecucion del hilo uno.                  
		 */	
		long tiempoHiloUno = 0;
		
		//while que itera hasta 16 hilos.
		while (hilos <= 16) {
			//Calculo del límite.
			int limite = numeros / hilos;
			/*
			 * Se crea el pool de hilos.                
			 */	
			ExecutorService servicio = Executors.newFixedThreadPool(hilos);
			int inferior = 1;
			int superior = limite;
			long initialTime = System.currentTimeMillis();
			//Itera segun el numero de hilos.
			for (int i = 1; i <= hilos; i++) {
				Future<Long> resultado = servicio.submit(new Nprimos(superior, inferior));
				list.add(resultado);
				inferior = superior + 1;
				superior += limite;
				//Si supera el límite rompe el ciclo.
				if (superior > limite * hilos) {
					break;
				}
			}

			// se termina de ejecutar cuando todos los hilos terminan de trabajar.
			for (Future<Long> resultado : list) {
				try {
					ListaTiempo.add(resultado.get());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
			
			if(hilos==1) {
				tiempoHiloUno = System.currentTimeMillis() - initialTime; 
			}
			System.out.print(" \n " + "(" + hilos + ")" + "tiempo de ejecución: "+ (System.currentTimeMillis() - initialTime) + " ms\n");
			System.out.print(" speed up = " + (double)(tiempoHiloUno /(System.currentTimeMillis() - initialTime)) + " \n");
			servicio.shutdown();
			//Se incrementa variable hilos.
			hilos++;
		}

	}
}
