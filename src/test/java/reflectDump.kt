import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

inline fun <reified R : Any> printTypeInfo(obj: R) {
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
    appendLine("  Standard Serialization works")
  } catch (e: Exception) {
    appendLine("  Standard Serialization failed: ${e.message}")
  }
}
