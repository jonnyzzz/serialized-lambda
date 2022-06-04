import org.junit.Test
import java.nio.file.Path

class InlineFunctionJavaP {

  inline fun showLambdaBytecode(crossinline action: () -> Unit) {
    val holder = object : Runnable {
      override fun run() {
        action()
      }
    }

    showClassBytecode(holder.javaClass)
  }


  @Test
  fun show_inline_bytecode_simple() {
    showLambdaBytecode {
      println("test")
    }
  }

  @Test
  fun show_inline_bytecode_with_closure_local() {
    val p = "!23"
    showLambdaBytecode {
      println("test$p")
    }
  }

  val external = "123"

  @Test
  fun show_inline_bytecode_with_closure_external() {
    val p = "!23"
    showLambdaBytecode {
      println("test$p$external")
    }
  }

}


fun showClassBytecode(clazz: Class<*>) {
  val javap = System.getProperty("java.home").let(Path::of).resolve("bin").resolve("javap")

  ProcessBuilder()
    .command(javap.toString(), "-c", findClassFile(clazz).toString())
    .inheritIO()
    .start()
    .waitFor()
}

private fun findClassFile(clazz: Class<*>): Path {
  val resourceName = clazz.name.replace(".", "/") + ".class"
  val classLoader = clazz.classLoader ?: error("No classloader for ${clazz.name}")
  val url = classLoader.getResource(resourceName) ?: error("Failed to find resource $resourceName for ${clazz.name}")
  return Path.of(url.toURI())
}
