import org.junit.Test

class Ctor3KotlinLambda {
  @Test
  fun test1LambdaFromJava() {
    //setup environment
    CtorExternalProcess.executeCode { println("This is code in external process") }
    //assertions go here
  }

  @Test
  fun test2LambdaFromJavaWithLocalVar() {
    //setup environment
    val localVariable = System.currentTimeMillis()
    CtorExternalProcess.executeCode { println("This is code in external process $localVariable") }
    //assertions go here
  }
}
