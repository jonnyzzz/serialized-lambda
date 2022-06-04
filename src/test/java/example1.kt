import org.junit.Test

class Example1Test {

  @Test
  fun testExampleLambda1() {
    val example1 = Example1.exampleLambda()

    println(example1.javaClass.name)
    example1.javaClass.genericInterfaces.forEach { println("  ${it.typeName}") }
    println()
  }

  @Test
  fun testExampleLambda2() {
    val example2 = Example1.exampleLambda2()

    println(example2.javaClass.name)
    example2.javaClass.genericInterfaces.forEach { println("  ${it.typeName}") }
    println()
  }

}
