import org.junit.Test;

public class Take1_Java_Simple {

  @Test
  public void testLambdaFromJava() {
    //setup environment

    ExternalProcess.executeRunnable(() -> {
      System.out.println("This is code in external process");
    });

    //assertions go here
  }

  @Test
  public void testLambdaFromJavaWithLocalVar() {
    //setup environment

    var localVariable = System.currentTimeMillis();

    ExternalProcess.executeRunnable(() -> {
      System.out.println("This is code in external process " + localVariable);
    });

    //assertions go here
  }
}
