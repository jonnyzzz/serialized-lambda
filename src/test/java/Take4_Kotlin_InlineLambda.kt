import ExternalProcess.executeCode
import ExternalProcess.executeInlineCode
import org.junit.Test

class Take4_Kotlin_InlineLambda {
  @Test
  fun test1_LambdaFromJava() {
    //setup environment
    executeInlineCode { println("This is code in external process") }
    //assertions go here
  }

  @Test
  fun test2_LambdaFromJavaWithLocalVar() {
    //setup environment
    val localVariable = System.currentTimeMillis()
    executeInlineCode { println("This is code in external process $localVariable") }
    //assertions go here
  }
}
