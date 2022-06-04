import java.io.Serializable;
import java.util.function.Consumer;

public class Example1 {

  public static Consumer<Object> exampleLambda() {
    return (x) -> { };
  }

  public interface SerializableConsumer extends Serializable {
    void consume();
  }

  public static SerializableConsumer exampleLambda2() {
    return () -> { };
  }
}
