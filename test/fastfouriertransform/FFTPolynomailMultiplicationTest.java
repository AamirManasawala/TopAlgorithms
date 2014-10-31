package fastfouriertransform;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class FFTPolynomailMultiplicationTest {
	private final NaivePolynomialMultiplication naivePolynomialMultiplication = new NaivePolynomialMultiplication();
	private final FFTPolynomailMultiplication fftPolynomailMultiplication = new FFTPolynomailMultiplication();
	
	@Test
	public void smallOutputCompare() {
		List<Integer> fa = Arrays.asList(1,1,4,7,3,2,-9,22);
		List<Integer> fb = Arrays.asList(1,1,-5,17,3,2,100,4);
		List<Integer> naiveAnswer = naivePolynomialMultiplication.multiply(fa, fb);
		List<Integer> fftAnswer = fftPolynomailMultiplication.multiply(fa, fb);
		for (int i = 0 ; i < 15 ; i++) {
			assertEquals(naiveAnswer.get(i), fftAnswer.get(i));
		}
	}
	
	@Test
	public void mediumOutputCompare() {
		List<Integer> fa = new ArrayList<Integer>();
		List<Integer> fb = new ArrayList<Integer>();
		
		for(int i = 0 ; i < 64 ; i++) {
			fa.add((int)Math.random() % 1000);
			fb.add((int)Math.random() % 1000);
		}
		List<Integer> naiveAnswer = naivePolynomialMultiplication.multiply(fa, fb);
		List<Integer> fftAnswer = fftPolynomailMultiplication.multiply(fa, fb);
		
		for (int i = 0 ; i < 63 ; i++) {
			assertEquals(naiveAnswer.get(i), fftAnswer.get(i));
		}
	}
	
	@Test
	public void timeDifferenceCheck() {
		List<Integer> fa = new ArrayList<Integer>();
		List<Integer> fb = new ArrayList<Integer>();
		
		for(int i = 0 ; i < 32768 ; i++) {
			fa.add((int)Math.random() % 1000);
			fb.add((int)Math.random() % 1000);
		}
		long startTime;
		long endTime;
		
		startTime = System.currentTimeMillis();
		List<Integer> fftAnswer = fftPolynomailMultiplication.multiply(fa, fb);
		endTime = System.currentTimeMillis();		

		System.out.println("fft took: " + (endTime -startTime)) ;

		startTime = System.currentTimeMillis();
		List<Integer> naiveAnswer = naivePolynomialMultiplication.multiply(fa, fb);
		endTime = System.currentTimeMillis();		

		System.out.println("naive took: " + (endTime -startTime));
		
		for (int i = 0 ; i < 32768 -1  ; i++) {
			assertEquals(naiveAnswer.get(i), fftAnswer.get(i));
		}
	}
	
}
