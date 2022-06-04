import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

interface KSerializableRunnable : Runnable, Serializable

object ExternalProcessWithSerialization {
  inline fun executeInlineCode(crossinline action: () -> Unit) {
    val holder = object : Runnable, Serializable {
      override fun run() {
        action()
      }
    }

    executeLambdaRemotely(holder) { it.run() }
  }

  @JvmStatic
  fun executeCode(action: () -> Unit) {
    executeLambdaRemotely(action) { it() }
  }

  @JvmStatic
  fun executeRunnable(action: Runnable) {
    executeLambdaRemotely(action) { it.run() }
  }

  @JvmStatic
  fun executeSerializableRunnable(action: SerializableRunnable) {
    executeLambdaRemotely(action) { it.run() }
  }

  @JvmStatic
  fun executeKSerializableRunnable(action: KSerializableRunnable) {
    executeLambdaRemotely(action) { it.run() }
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
      trySaveLoadJvm(obj, runMe)
      appendLine()
    }
  }

  inline fun <reified R : Any> StringBuilder.trySaveLoadJvm(obj: R, runMe: (R) -> Unit) {
    val reloaded = try {
      val bytes = ByteArrayOutputStream().use { bos ->
        ObjectOutputStream(bos).use { it.writeObject(obj) }
        bos.toByteArray()
      }

      val reloaded = bytes.inputStream().use { bis ->
        ObjectInputStream(bis).readObject()
      }

      reloaded as R
      appendLine("  Standard Serialization works")
      reloaded
    } catch (e: Exception) {
      appendLine("  Standard Serialization failed: $e")
      throw e
    }

    if (reloaded.javaClass != obj.javaClass) {
      appendLine("  Reloaded type is ${reloaded.javaClass.name} should be the same as ${obj.javaClass.name}")
    }

    try {
      runMe(reloaded)
      appendLine("  Reloaded run works")
    } catch (e: Exception) {
      appendLine("  Reloaded run failed: $e")
      throw e
    }
  }
}

