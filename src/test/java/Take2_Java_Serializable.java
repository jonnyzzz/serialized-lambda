import org.junit.Test;

import java.io.Serializable;

public class Take2_Java_Serializable {
  @Test
  public void testLambdaFromJava() {
    //setup environment
    ExternalProcess.executeSerializableRunnable(() -> {
      System.out.println("This is code in external process");
    });
    //assertions go here
  }

  @Test
  public void testLambdaFromJavaWithLocalVar() {
    //setup environment
    var localVariable = System.currentTimeMillis();
    ExternalProcess.executeSerializableRunnable(() -> {
      System.out.println("This is code in external process " + localVariable);
    });
    //assertions go here
  }
}
