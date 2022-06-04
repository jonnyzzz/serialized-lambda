import org.junit.Test

class Serialize6KotlinLambda {
  @Test
  fun test1_LambdaFromJava() {
    //setup environment
    ExternalProcessWithSerialization.executeCode { println("This is code in external process") }
    //assertions go here
  }

  @Test
  fun test2_LambdaFromJavaWithLocalVar() {
    //setup environment
    val localVariable = System.currentTimeMillis()
    ExternalProcessWithSerialization.executeCode { println("This is code in external process $localVariable") }
    //assertions go here
  }
}
