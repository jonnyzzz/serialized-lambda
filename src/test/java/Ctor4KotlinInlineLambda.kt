import org.junit.Test

class Ctor4KotlinInlineLambda {
  @Test
  fun test1LambdaFromJava() {
    //setup environment
    CtorExternalProcess.executeInlineCode { println("This is code in external process") }
    //assertions go here
  }

  @Test
  fun test2LambdaFromJavaWithLocalVar() {
    //setup environment
    val localVariable = System.currentTimeMillis()
    CtorExternalProcess.executeInlineCode { println("This is code in external process $localVariable") }
    //assertions go here
  }
}
