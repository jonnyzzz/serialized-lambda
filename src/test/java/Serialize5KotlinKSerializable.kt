import org.junit.Test

class Serialize5KotlinKSerializable {
  @Test
  fun test1LambdaFromJava() {
    //setup environment
    ExternalProcessWithSerialization.executeKSerializableRunnable( object: KSerializableRunnable {
      override fun run() {
        println("This is code in external process")
      }
    })
    //assertions go here
  }

  @Test
  fun test2LambdaFromJavaWithLocalVar() {
    //setup environment
    val localVariable = System.currentTimeMillis()
    ExternalProcessWithSerialization.executeKSerializableRunnable(
      object: KSerializableRunnable {
        override fun run() {
          println("This is code in external process $localVariable")
        }
      }
    )
    //assertions go here
  }
}
