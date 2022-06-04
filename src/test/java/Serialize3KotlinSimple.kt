import ExternalProcessWithSerialization.executeRunnable
import org.junit.Test

class Serialize3KotlinSimple {
  @Test
  fun test1LambdaFromJava() {
    //setup environment
    executeRunnable { println("This is code in external process") }
    //assertions go here
  }

  @Test
  fun test2LambdaFromJavaWithLocalVar() {
    //setup environment
    val localVariable = System.currentTimeMillis()
    executeRunnable { println("This is code in external process $localVariable") }
    //assertions go here
  }
}
