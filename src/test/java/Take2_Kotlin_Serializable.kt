import ExternalProcess.executeSerializableRunnable
import org.junit.Test

class Take2_Kotlin_Serializable {
  @Test
  fun testLambdaFromJava() {
    //setup environment
    executeSerializableRunnable { println("This is code in external process") }
    //assertions go here
  }

  @Test
  fun testLambdaFromJavaWithLocalVar() {
    //setup environment
    val localVariable = System.currentTimeMillis()
    executeSerializableRunnable { println("This is code in external process $localVariable") }
    //assertions go here
  }
}
