import ExternalProcess.executeRunnable
import org.junit.Test

class Take1_Kotlin_Simple {
  @Test
  fun testLambdaFromJava() {
    //setup environment
    executeRunnable { println("This is code in external process") }
    //assertions go here
  }

  @Test
  fun testLambdaFromJavaWithLocalVar() {
    //setup environment
    val localVariable = System.currentTimeMillis()
    executeRunnable { println("This is code in external process $localVariable") }
    //assertions go here
  }
}
