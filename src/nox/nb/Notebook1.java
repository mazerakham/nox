package nox.nb;

import static ox.util.Utils.propagate;

import java.util.Random;

import com.google.common.base.Stopwatch;

import nox.Complex;
import nox.FFT;
import nox.Vector;
import ox.Log;

public class Notebook1 {

  public void run2() {
    Random random = new Random(42);
    for (int i = 0; i < 20; i++) {
      Stopwatch watch = Stopwatch.createStarted();
      int size = (int) Math.round(Math.pow(2.0, i));
      Vector input = Vector.fromFunction(size, (j) -> new Complex(random.nextDouble(), random.nextDouble()));
      try {
        Vector fft = FFT.of(input);
      } catch (Exception e) {
        Log.debug("We are here.");
        throw propagate(e);
      }
      Log.debug(watch);
    }
  }

  public void run1() {
    Vector vector = Vector.of(1.0, -3.0, -5.0, -4.0, 4.0, 2.0, 5.0, 5.0);
    Log.debug(vector);
    Vector fft = FFT.of(vector);
    Log.debug(fft);
  }

  public static void main(String... args) {
    new Notebook1().run2();
  }
}
