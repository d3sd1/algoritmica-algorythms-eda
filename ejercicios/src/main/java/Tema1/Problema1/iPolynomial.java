package Tema1.Problema1;

public interface iPolynomial {
    public int getDegree();
    public int getCoeficient(int n);
    public void setCoeficient(int n, int newValue);
    public int getValue(int x);
    public iPolynomial suma(iPolynomial p);
}
