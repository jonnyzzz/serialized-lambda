public class HighOrder {
  public static SerializableRunnable aHighOrder(SerializableRunnable r) {
    var something = System.currentTimeMillis() + "123";
    return () -> {
      System.out.println("Hello. " + something);
      r.run();
      System.out.println("Bye");
    };
  }
}
