package fastfouriertransform;


public class ComplexNumber {
	
	public final double real;
	public final double complex;
	
	public ComplexNumber (double theta) {
		this(Math.cos(theta), Math.sin(theta));
	}
	
	public ComplexNumber(double realPart,  double complexPart) {
		this.real = realPart;
		this.complex = complexPart;
	}
	
	public static ComplexNumber multiply(ComplexNumber c1, ComplexNumber c2) {
		return new ComplexNumber(c1.real*c2.real - c1.complex * c2.complex, c1.real * c2.complex + c1.complex* c2.real );
	}
	
	public static ComplexNumber addition(ComplexNumber c1, ComplexNumber c2) {
		return new ComplexNumber(c1.real + c2.real, c1.complex + c2.complex);
	}
	
	public static ComplexNumber negate(ComplexNumber c) {
		return new ComplexNumber(-1 * c.real, -1 * c.complex);
	}
	
	public static ComplexNumber scalerMultiply(ComplexNumber c, double alpha) {
		return new ComplexNumber(c.real * alpha , c.complex * alpha);
	}
	
	@Override
	public String toString() {
		return "ComplexNumber [real=" + real + ", complex=" + complex + "]";
	}
}
