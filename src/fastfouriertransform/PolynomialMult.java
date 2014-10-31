package fastfouriertransform;

import java.util.List;

public interface PolynomialMult {
	/**
	 * 
	 * @param First polynomial in form of a list with list[i] = coefficient of ith term in polynomial1 
	 * @param Second polynomial: poly2
	 * @return Product of the two polynomials. 
	 */
	public List<Integer> multiply(List<Integer> polynomail1, List<Integer> polynomial2);
}
