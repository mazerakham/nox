package nox;

import static com.google.common.base.Preconditions.checkState;
import static nox.Complex.I;
import static nox.Complex.TAU;

import ox.x.XList;

public class FFT {

  private final int size;
  private final Vector roots;

  private FFT(int size) {
    this.size = size;
    roots = getRootsOfUnity(size);
  }

  /**
   * Computes the fast fourier transform of v.
   */
  public static Vector of(Vector v) {
    checkState(isPowerOf2(v.size()));

    return new FFT(v.size()).fft(v, v.size());
  }

  private Vector fft(Vector v, int size) {
    if (size == 1) {
      return v;
    }
    Vector vefft = fft(v.slice(0, 2), size/2);
    Vector vofft = fft(v.slice(1, 2), size/2);
    XList<Complex> retC = XList.create();
    for (int i = 0; i < size; i++) {
      retC.add(zip(vefft, vofft, i, size));
    }
    return Vector.of(retC);
  }

  private Complex zip(Vector fftE, Vector fftO, int ix, int size) {
    Complex valE = fftE.get(ix % (size / 2));
    Complex valO = fftO.get(ix % (size / 2));
    Complex zeta = roots.get(ix * (this.size / size));
    return valO.mult(zeta).add(valE);
  }

  private Vector getRootsOfUnity(int n) {
    XList<Complex> rootsc = XList.create();
    Complex curr = new Complex(1.0);
    Complex zeta = Complex.exp(TAU.mult(I).mult(1.0 / size));
    for (int i = 0; i < size; i++) {
      rootsc.add(curr);
      curr = curr.mult(zeta);
    }

    return Vector.of(rootsc);
  }

  private static boolean isPowerOf2(int size) {
    if (size <= 0) {
      return false;
    }

    int a = size;
    while (a != 1) {
      if (a % 2 != 0) {
        return false;
      }
      a = a / 2;
    }

    return true;
  }
}
