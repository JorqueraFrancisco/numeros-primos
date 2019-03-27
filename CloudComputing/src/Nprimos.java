import java.util.concurrent.Callable;

class Nprimos implements Callable<Long> {
	
	/*
	 * Limite maximo de números.                  
	 */	
	private int limite;
	
	/*
	 * Limite inferior de números.                  
	 */
	
	private int inferior;
	
	/*
	 * Constructor.         
	 */
	public Nprimos(int limite, int inferior) {
		this.limite = limite;
		this.inferior = inferior;
	}
	
	// método para funcionar en paralelo que recorre los límites de números dados.
	@Override
	public Long call() throws Exception {
		long initialTime = System.currentTimeMillis();
		for (int i = this.inferior; i <= this.limite; i++) {
			if (Primo(i)) {				
			}
		}
		return System.currentTimeMillis() - initialTime;
	}
	
	
	// Metodo para calcular numeros primos.
	public boolean Primo(int numero) {
		int aux;
		for (int cont = 2; cont < numero; cont++) {
			aux = numero % cont;
			if (aux == 0) {
			return false;
			}
		}
		return true;
	}
}