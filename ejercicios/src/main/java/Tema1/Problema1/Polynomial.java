package Tema1.Problema1;

public class Polynomial implements iPolynomial {

	private int[] coefs;
	

	public Polynomial (int[] coefs) {
		this.coefs = coefs;
	}
	
	@Override
	public int getDegree() {
//		int degree = 0;		
//		for (int i = 0; i < coefs.length; i++) {
//			if (coefs[i] != 0) {
//				degree = i;
//			}			
//		}		
//		return degree;

		for (int i = coefs.length-1; i >= 0; i--) {
			if (coefs[i] != 0) {
				return i;
			}			
		}
		
		return 0;
	}

	@Override
	public int getCoeficient(int n) {
		if (n>= coefs.length) {
			return 0;
		}
		return coefs[n];
	}

	@Override
	public void setCoeficient(int n, int newValue) {
		coefs[n] = newValue;
	}

	@Override
	public int getValue(int x) {
		int valor = 0;
		for (int i = 0; i < coefs.length; i++) {
			valor += coefs[i] * Math.pow(x, i);			
		}
		return valor;
	}

	@Override
	public iPolynomial suma(iPolynomial p) {
		int resDegree = Math.max(getDegree(), p.getDegree());		
		int[] result = new int[resDegree + 1];
		for (int i = 0; i < result.length; i++) {
			result[i] = getCoeficient(i) + p.getCoeficient(i);
		}
		
		return new Polynomial(result);
	}

	public String toString() {
		int deg = this.getDegree();
		String result = "";
		result += this.coefs[0] + " + ";
		if(deg>=1) {
			for (int i = 1; i< deg; i++) {
				 result += (this.coefs[i] + "(X^" + i + ") + ");
			}
			result += this.coefs[deg] + "(X^" + deg + ")";
		}
		return result;
	}
	
	public static void main(String[] args) {
		iPolynomial p1 = new Polynomial(new int[] {2,0,5,1,1});
		iPolynomial p2 = new Polynomial(new int[] {2,0,5,1});
		//
		System.out.println("p1.getDegree() = " + p1.getDegree());
		System.out.println("p2.getDegree() = " + p2.getDegree());
		//
		System.out.println("p1.getCoeficient(0) = " + p1.getCoeficient(0));
		System.out.println("p1.getCoeficient(1) = " + p1.getCoeficient(1));
		System.out.println("p1.getCoeficient(2) = " + p1.getCoeficient(2));
		//
		p1.setCoeficient(1, 8);
		System.out.println("p1.getCoeficient(0) = " + p1.getCoeficient(0));
		System.out.println("p1.getCoeficient(1) = " + p1.getCoeficient(1));
		System.out.println("p1.getCoeficient(2) = " + p1.getCoeficient(2));
		//
		System.out.println("p1.getValue(0) = " + p1.getValue(0));
		System.out.println("p1.getValue(1) = " + p1.getValue(1));
		System.out.println("p1.getValue(2) = " + p1.getValue(2));
		//
//		p1 = p1.suma(p2);
//		System.out.println("p1.getCoeficient(0) = " + p1.getCoeficient(0));
//		System.out.println("p1.getCoeficient(1) = " + p1.getCoeficient(1));
//		System.out.println("p1.getCoeficient(2) = " + p1.getCoeficient(2));
//		System.out.println("p1.getCoeficient(3) = " + p1.getCoeficient(3));
//		System.out.println("p1.getCoeficient(4) = " + p1.getCoeficient(4));
//		System.out.println("p1.getCoeficient(5) = " + p1.getCoeficient(5));
//		System.out.println("p1.getCoeficient(6) = " + p1.getCoeficient(6));
		//
		System.out.println("p1 = " + p1.toString());
		System.out.println("p2 = " + p2.toString());
		System.out.println("p1+p2 = " + p1.suma(p2).toString());
	}
}
