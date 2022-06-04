import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable


object ExternalProcess {
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
}


@Suppress("NOTHING_TO_INLINE")
inline fun <reified R : Any> executeLambdaRemotely(obj: R, runMe: (R) -> Unit) {
  val type = obj.javaClass
  println(buildString {
    appendLine("Class ${type.name}")
    appendTypeHierarchy(type)
    appendConstructors(type)
    appendFields(type)
    appendMethods(type)
    appendLine()
    tryConstructDefaultConstructor(type)
    trySaveLoadJvm(obj, runMe)
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

fun StringBuilder.appendFields(type: Class<*>) {
  type.declaredFields.forEach {
    appendLine("  field ${it.name}: ${it.type.name}")
  }
}

fun StringBuilder.appendMethods(type: Class<*>) {
  type.declaredMethods.forEach {
    val params = it.parameters.joinToString(", ") { p -> "${p.name}: ${p.type.name}" }
    appendLine("  method ${it.name}($params): ${it.returnType.name}")
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
    return
  }

  if (reloaded.javaClass != obj.javaClass) {
    appendLine("  Reloaded type is ${reloaded.javaClass.name} should be the same as ${obj.javaClass.name}")
  }

  try {
    runMe(reloaded)
    appendLine("  Reloaded run works")
  } catch (e: Exception) {
    appendLine("  Reloaded run failed: $e")
  }
}
