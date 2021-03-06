import org.junit.Test;

public class Serialize2JavaSerializable {
  @Test
  public void test1LambdaFromJava() {
    //setup environment
    ExternalProcessWithSerialization.executeSerializableRunnable(() -> {
      System.out.println("This is code in external process");
    });
    //assertions go here
  }

  @Test
  public void test2LambdaFromJavaWithLocalVar() {
    //setup environment
    var localVariable = System.currentTimeMillis();
    ExternalProcessWithSerialization.executeSerializableRunnable(() -> {
      System.out.println("This is code in external process " + localVariable);
    });
    //assertions go here
  }
}
