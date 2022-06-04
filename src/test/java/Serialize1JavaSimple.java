import org.junit.Test;

public class Serialize1JavaSimple {
  @Test
  public void test1LambdaFromJava() {
    //setup environment
    ExternalProcessWithSerialization.executeRunnable(() -> {
      System.out.println("This is code in external process");
    });
    //assertions go here
  }

  @Test
  public void test2LambdaFromJavaWithLocalVar() {
    //setup environment
    var localVariable = System.currentTimeMillis();
    ExternalProcessWithSerialization.executeRunnable(() -> {
      System.out.println("This is code in external process " + localVariable);
    });
    //assertions go here
  }
}
