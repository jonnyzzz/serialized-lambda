import org.junit.Test;

public class Ctor1JavaSimple {

  @Test
  public void test1LambdaFromJava() {
    //setup environment
    CtorExternalProcess.executeRunnable(() -> {
      System.out.println("This is code in external process");
    });
    //assertions go here
  }

  @Test
  public void test2LambdaFromJavaWithLocalVar() {
    //setup environment
    var localVariable = System.currentTimeMillis();
    CtorExternalProcess.executeRunnable(() -> {
      System.out.println("This is code in external process " + localVariable);
    });
    //assertions go here
  }
}
