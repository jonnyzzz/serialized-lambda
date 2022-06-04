import org.junit.Test

class Example1Test {

  @Test
  fun testExampleLambda1() {
    val example1 = Example1.exampleLambda()
    printTypeInfo(example1)
  }

  @Test
  fun testExampleLambda2() {
    val example2 = Example1.exampleLambda2()
    printTypeInfo(example2)
  }

}
