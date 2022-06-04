import org.junit.Test;

public class ExampleJavaTest {

  @Test
  public void testLambdaFromJava() {
    //setup environment

    ExternalProcess.executeRunnable(() -> {
      System.out.println("This is code in external process");
    });

    //do some test asserts
  }

  @Test
  public void testLambdaFromJavaWithLocalVar() {
    //setup environment

    var localVariable = System.currentTimeMillis();

    ExternalProcess.executeRunnable(() -> {
      System.out.println("This is code in external process " + localVariable);
    });

    //do some test asserts
  }
}
