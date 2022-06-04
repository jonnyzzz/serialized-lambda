import org.junit.Test

class Serialize6KotlinInlineLambda {
  @Test
  fun test1_LambdaFromJava() {
    //setup environment
    ExternalProcessWithSerialization.executeInlineCode { println("This is code in external process") }
    //assertions go here
  }

  @Test
  fun test2_LambdaFromJavaWithLocalVar() {
    //setup environment
    val localVariable = System.currentTimeMillis()
    ExternalProcessWithSerialization.executeInlineCode { println("This is code in external process $localVariable") }
    //assertions go here
  }
}
