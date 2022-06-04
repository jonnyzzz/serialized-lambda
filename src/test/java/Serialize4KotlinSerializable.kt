import org.junit.Test

class Serialize4KotlinSerializable {
  @Test
  fun test1_LambdaFromJava() {
    //setup environment
    ExternalProcessWithSerialization.executeSerializableRunnable { println("This is code in external process") }
    //assertions go here
  }

  @Test
  fun test2_LambdaFromJavaWithLocalVar() {
    //setup environment
    val localVariable = System.currentTimeMillis()
    ExternalProcessWithSerialization.executeSerializableRunnable { println("This is code in external process $localVariable") }
    //assertions go here
  }
}
