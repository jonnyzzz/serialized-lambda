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

inline fun logString(builder: StringBuilder.() -> Unit) {
  val buff = java.lang.StringBuilder()

  try {
    buff.builder()
  } finally {
    println(buff.toString())
  }
}
