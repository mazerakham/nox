package nox;

import java.text.DecimalFormat;

/**
 * Represents a complex number a + bi.
 */
public class Complex {

  public static final Complex TAU = new Complex(Math.PI * 2.0, 0);

  public static final Complex I = new Complex(0.0, 1.0);

  private static final DecimalFormat df = new DecimalFormat("#.00");

  private final double a, b;

  public Complex(double a, double b) {
    this.a = a;
    this.b = b;
  }

  public Complex add(Complex c) {
    return new Complex(this.a + c.a, this.b + c.b);
  }

  public Complex mult(Complex c) {
    return new Complex(this.a * c.a - this.b * c.b, this.a * c.b + this.b * c.a);
  }

  public Complex mult(double r) {
    return new Complex(r * a, r * b);
  }

  public Complex(double r) {
    this.a = r;
    this.b = 0.0;
  }

  @Override
  public String toString() {
    return df.format(a) + " + i" + df.format(b);
  }

  public static Complex exp(Complex c) {
    return new Complex(Math.cos(c.b), Math.sin(c.b)).mult(Math.exp(c.a));
  }
}
