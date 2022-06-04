import ExternalProcessWithSerialization.executeCode
import org.junit.Test

class Serialize5KotlinLambda {
  @Test
  fun test1_LambdaFromJava() {
    //setup environment
    executeCode { println("This is code in external process") }
    //assertions go here
  }

  @Test
  fun test2_LambdaFromJavaWithLocalVar() {
    //setup environment
    val localVariable = System.currentTimeMillis()
    executeCode { println("This is code in external process $localVariable") }
    //assertions go here
  }
}
