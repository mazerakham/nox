package nox;

import java.util.function.Function;

import ox.x.XList;

public class Vector {

  private final XList<Complex> vals;

  public int size() {
    return vals.size();
  }

  public Complex get(int ix) {
    return vals.get(ix);
  }

  public Vector slice(int start, int step) {
    XList<Complex> retVals = XList.create();
    for (int i = start; i < size(); i += step) {
      retVals.add(vals.get(i));
    }
    return Vector.of(retVals);
  }

  private Vector(XList<Complex> vals) {
    this.vals = vals;
  }

  private Vector(Complex... vals) {
    this(XList.of(vals));
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for (Complex c : vals) {
      sb.append(c.toString() + ", ");
    }
    sb.append(']');
    return sb.toString();
  }

  public static Vector fromFunction(int size, Function<Integer, Complex> fn) {
    XList<Complex> vals = XList.create();
    for (int i = 0; i < size; i++) {
      vals.add(fn.apply(i));
    }
    return Vector.of(vals);
  }

  public static Vector of(XList<Complex> vals) {
    return new Vector(vals);
  }

  public static Vector of(Double... vals) {
    return new Vector(XList.of(vals).map(Complex::new));
  }
}
