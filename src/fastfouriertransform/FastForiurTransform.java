package fastfouriertransform;


import java.util.ArrayList;
import java.util.List;

public class FastForiurTransform {
	
	public static List<ComplexNumber> disctereFourierTransform(List<ComplexNumber> coefficient, boolean isInverse) {
		
		int size = coefficient.size();
		
		if(size == 1)  {
			List<ComplexNumber> arrayList = new ArrayList<ComplexNumber>();
			arrayList.add(coefficient.get(0));
			if(!isInverse)
				arrayList.add(coefficient.get(0));
			return  arrayList;
		}

		ComplexNumber w = new ComplexNumber(1,0);
		ComplexNumber wo = new ComplexNumber(Math.PI/size);
		if(isInverse){
			wo = new ComplexNumber(-2  * Math.PI/size);
		}
		
		// this can be further optimized !
		List<ComplexNumber> evenTerms = disctereFourierTransform(makeEvenList(coefficient), isInverse);
		List<ComplexNumber> oddTerms = disctereFourierTransform(makeOddList(coefficient),isInverse);
		
		List<ComplexNumber> fftTransform = new ArrayList<ComplexNumber>();
		
		for(int i = 0; i < evenTerms.size() ; i ++) {
			fftTransform.add(ComplexNumber.addition(evenTerms.get(i), ComplexNumber.multiply(w, oddTerms.get(i))));
			w = ComplexNumber.multiply(w, wo);
		}
		
		wo = new ComplexNumber(Math.PI/size);
		
		if(isInverse) {
			wo = new ComplexNumber(-1 * 2*Math.PI/size);
		}
		
		w = new ComplexNumber(1,0);
		for(int i = 0; i < oddTerms.size() ; i ++) {
			fftTransform.add(ComplexNumber.addition(evenTerms.get(i), ComplexNumber.multiply(w, ComplexNumber.negate(oddTerms.get(i)))));
			w = ComplexNumber.multiply(w, wo);
		}		
		return fftTransform;
	}	

	private static List<ComplexNumber> makeOddList(List<ComplexNumber> coefficient) {
		List<ComplexNumber> tempList = new ArrayList<ComplexNumber>();
		for(int i = 0; i < coefficient.size() / 2 ; i ++) {
			tempList.add(coefficient.get(2*i + 1));
		}
		return tempList;
	}
	
	private static List<ComplexNumber> makeEvenList(List<ComplexNumber> coefficient) {
		List<ComplexNumber> tempList = new ArrayList<ComplexNumber>();
		for(int i = 0; i < coefficient.size() / 2 ; i ++) {
			tempList.add(coefficient.get(2*i));
		}
		return tempList;
	}
}	
