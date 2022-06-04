import Take2_Java_Serializable.SerializableRunnable
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


object ExternalProcess {
  @JvmStatic
  fun executeCode(action: () -> Unit) {
    executeLambdaRemotely(action)
  }

  @JvmStatic
  fun executeRunnable(action: Runnable) {
    executeLambdaRemotely(action)
  }

  @JvmStatic
  fun executeSerializableRunnable(action: SerializableRunnable) {
    executeLambdaRemotely(action)
  }
}


inline fun <reified R : Any> executeLambdaRemotely(obj: R) {
  val type = R::class.java
  println(buildString {
    appendLine("Class ${type.name}")
    appendConstructors(type)
    appendTypeHierarchy(type)
    appendLine()
    tryConstructDefaultConstructor(type)
    trySaveLoadJvm(obj)
    appendLine()
  })
}

fun StringBuilder.tryConstructDefaultConstructor(type: Class<*>) {
  try {
    type.getConstructor().newInstance()
    appendLine("  default constructor works")
  } catch (e: Exception) {
    appendLine("  default constructor failed: ${e.message}")
  }
}

fun StringBuilder.appendConstructors(type: Class<*>) {
  type.declaredConstructors.forEach {
    val args = it.parameters.joinToString(", ") { p -> "${p.name}: ${p.type.name}" }
    appendLine("  ctor($args)")
  }
}

fun StringBuilder.appendTypeHierarchy(type: Class<*>, prefix: String = "") {
  type.superclass?.let {
    appendLine("$prefix  ${it.name}")
    appendTypeHierarchy(it, "  $prefix")
  }

  for (it in type.interfaces) {
    appendLine("$prefix  ${it.name}")
    appendTypeHierarchy(it, "  $prefix")
  }
}

fun StringBuilder.trySaveLoadJvm(obj: Any) {
  try {
    val bytes = ByteArrayOutputStream().use { bos ->
      ObjectOutputStream(bos).use { it.writeObject(obj) }
      bos.toByteArray()
    }

    val reloaded = bytes.inputStream().use { bis ->
      ObjectInputStream(bis).readObject()
    }

    require(reloaded.javaClass == obj.javaClass) {
      "Reloaded type is ${reloaded.javaClass.name} should be the same as ${obj.javaClass.name}"
    }

    appendLine("  Standard Serialization works")
  } catch (e: Exception) {
    appendLine("  Standard Serialization failed: $e")
  }
}
