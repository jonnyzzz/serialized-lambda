

fun printTypeInfo(type: Class<*>) {
  println(buildString {
    appendLine("Class ${type.name}")
    appendConstructors(type)
    tryConstructDefaultConstructor(type)
    appendTypeHierarchy(type)
    appendLine()
  })
}

private fun StringBuilder.tryConstructDefaultConstructor(type: Class<*>) {
  try {
    type.getConstructor().newInstance()
    appendLine("  default constructor works")
  } catch (e: Exception) {
    appendLine("  default constructor failed: ${e.message}")
  }
}

private fun StringBuilder.appendConstructors(type: Class<*>) {
  type.declaredConstructors.forEach {
    val args = it.parameters.joinToString(", ") { p -> "${p.name}: ${p.type.name}" }
    appendLine("  ctor($args)")
  }
}

private fun StringBuilder.appendTypeHierarchy(type: Class<*>, prefix: String = "") {
  type.superclass?.let {
    appendLine("$prefix  ${it.name}")
    appendTypeHierarchy(it, "  $prefix")
  }

  for (it in type.interfaces) {
    appendLine("$prefix  ${it.name}")
    appendTypeHierarchy(it, "  $prefix")
  }
}
