import org.junit.Test;

public class Take1_Java_Simple {

  @Test
  public void test1_LambdaFromJava() {
    //setup environment
    ExternalProcess.executeRunnable(() -> {
      System.out.println("This is code in external process");
    });
    //assertions go here
  }

  @Test
  public void test2_LambdaFromJavaWithLocalVar() {
    //setup environment
    var localVariable = System.currentTimeMillis();
    ExternalProcess.executeRunnable(() -> {
      System.out.println("This is code in external process " + localVariable);
    });
    //assertions go here
  }
}
