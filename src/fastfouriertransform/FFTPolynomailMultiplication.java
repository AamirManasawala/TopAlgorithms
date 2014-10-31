package fastfouriertransform;

import java.util.ArrayList;
import java.util.List;


public class FFTPolynomailMultiplication implements PolynomialMult {
	/**
	 * 
	 * @param fa
	 * @param fb
	 * @return multiplication of the two polynomials.
	 */
	
	public List<Integer> multiply(List<Integer> fa, List<Integer> fb) {
	
		List<ComplexNumber>  answer = fftMultiplication(fa , fb );
		
		List<Integer> finalAnswer = new ArrayList<Integer>();
		int size = answer.size();
		for (ComplexNumber complex : answer) {
			finalAnswer.add((int)Math.round(ComplexNumber.scalerMultiply(complex, 1.0/size).real));
		}
		return finalAnswer;
	}

	private static List<ComplexNumber>  fftMultiplication(List<Integer> fa, List<Integer> fb) {
		List<ComplexNumber> cfa = new ArrayList<ComplexNumber>();
		List<ComplexNumber> cfb = new ArrayList<ComplexNumber>();
		
		for(int i = 0 ; i < fa.size() ; i ++) {
			cfa.add(new ComplexNumber(fa.get(i), 0));
		}
		
		for(int i = 0; i < fb.size() ; i ++) {
			cfb.add(new ComplexNumber(fb.get(i),0));
		}	
		List<ComplexNumber> ffta = FastForiurTransform.disctereFourierTransform(cfa, false);
		List<ComplexNumber> fftb = FastForiurTransform.disctereFourierTransform(cfb,  false);
		
		List<ComplexNumber> mul = new ArrayList<ComplexNumber>();
		
		for(int i = 0 ; i < ffta.size() ; i++ ) {
			mul.add(ComplexNumber.multiply(ffta.get(i), fftb.get(i)));
		}
		return FastForiurTransform.disctereFourierTransform(mul, true);
	}
}
