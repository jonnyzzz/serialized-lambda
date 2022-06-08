import org.junit.Test

class Serialize4KotlinSerializableHighOrder {
  @Test
  fun test1LambdaFromJava() {
    //setup environment
    ExternalProcessWithSerialization.executeSerializableRunnable(HighOrder.aHighOrder { println("This is code in external process") })
    //assertions go here
  }

  @Test
  fun test2LambdaFromJavaWithLocalVar() {
    //setup environment
    val localVariable = System.currentTimeMillis()
    ExternalProcessWithSerialization.executeSerializableRunnable(HighOrder.aHighOrder { println("This is code in external process $localVariable") })
    //assertions go here
  }
}
