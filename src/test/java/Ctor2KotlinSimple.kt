import org.junit.Test

class Ctor2KotlinSimple {
  @Test
  fun test1LambdaFromJava() {
    //setup environment
    CtorExternalProcess.executeRunnable { println("This is code in external process") }
    //assertions go here
  }

  @Test
  fun test2LambdaFromJavaWithLocalVar() {
    //setup environment
    val localVariable = System.currentTimeMillis()
    CtorExternalProcess.executeRunnable { println("This is code in external process $localVariable") }
    //assertions go here
  }
}
