package fastfouriertransform;


import java.util.ArrayList;
import java.util.List;

public class NaivePolynomialMultiplication implements PolynomialMult {
	
	public List<Integer> multiply(List<Integer> fa, List<Integer> fb) {
		List<Integer> result = new ArrayList<Integer>();
		for(int i = 0 ; i < 2* fa.size() - 1 ; i++)
			result.add(i,0);
		for(int i = 0 ; i < fa.size() ; i ++) {
			for(int j = 0 ; j< fb.size() ; j++) {
				result.set(i + j, fa.get(i) * fb.get(j) + result.get(i+j));
			}
		}
		return result;
	}
	
	public static List<ComplexNumber>  FFTMultiplication(List<Integer> fa, List<Integer> fb) {

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
