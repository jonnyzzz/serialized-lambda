import java.io.Serializable

object CtorExternalProcess {
  @JvmStatic
  fun executeCode(action: () -> Unit) {
    executeLambdaRemotely(action) { it() }
  }

  @JvmStatic
  fun executeRunnable(action: Runnable) {
    executeLambdaRemotely(action) { it.run() }
  }

  inline fun executeInlineCode(crossinline action: () -> Unit) {
    val holder = object : Runnable, Serializable {
      override fun run() {
        action()
      }
    }

    executeLambdaRemotely(holder) { it.run() }
  }



  @Suppress("NOTHING_TO_INLINE")
  inline fun <reified R : Any> executeLambdaRemotely(obj: R, runMe: (R) -> Unit) {
    val type = obj.javaClass
    logString {
      appendLine("Class ${type.name}")
      appendTypeHierarchy(type)
      appendConstructors(type)
      appendFields(type)
      appendMethods(type)
      appendLine()
      tryConstructDefaultConstructor(type, runMe)
      appendLine()
    }
  }

  inline fun <reified R: Any> StringBuilder.tryConstructDefaultConstructor(type: Class<R>, runMe: (R) -> Unit) {
    val reloaded = try {
       type.getConstructor().newInstance() as R
    } catch (e: Exception) {
      appendLine("  default constructor failed: ${e.message}")
      throw e
    }
    appendLine("  default constructor works")

    try {
      runMe(reloaded)
      appendLine("  Reloaded run works")
    } catch (e: Exception) {
      appendLine("  Reloaded run failed: $e")
      throw e
    }
  }
}
