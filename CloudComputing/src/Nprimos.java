import java.util.concurrent.Callable;

class Nprimos  implements Callable<Long>{
	 
	 private int limite;
	 private int inferior;
	 

	 public Nprimos(int limite,int inferior) {
		 this.limite = limite;
		 this.inferior = inferior;
	 }
	 @Override
	 public Long call() throws Exception{
		 
		long initialTime = System.currentTimeMillis() ;
		
		for(int i=this.inferior;i<=this.limite;i++)

		{

		if(esPrimo(i))

		{
		System.out.println("Numero Primo:"+i);
	
		}
		}		
		
		return  System.currentTimeMillis()-initialTime;
}
		
	
	 public boolean esPrimo(int numero)
	 {
	 int aux;
	 for(int cont=2;cont<numero;cont++)
	 {
	 aux=numero%cont;
	 if(aux==0)
	 return false;
	 }
	 return true;
	 }
	
}