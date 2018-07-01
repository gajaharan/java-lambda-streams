package lambdas;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class LambdaTest {

  @Test
  public void removeStringsWithMoreThanThreeCharacters(){
    List<String> input = asList("This", "is", "java", "8");
    input = Lambda.filter(input, s -> s.length()<3);
    assertThat(input, contains("is", "8"));
  }

  @Test
  public void shouldBeExecutedWitingATransaction(){
    TransactionLambda lambda = new TransactionLambda();
    Lambda.processWithinTransaction(() -> {
      System.out.println("hello");
      lambda.run();
    });
    assertTrue(lambda.isConsumed());
  }

  @Test
  public void shouldCreateAString(){
    String bigString = Lambda.create(() -> "Hello");
    System.out.println(bigString);
    assertTrue(bigString.length()>0);
  }

  @Test
  public void extractStringSize(){
    String myString = "This is great";
    int length = Lambda.getStringLength(myString, String::length);
    assertTrue(length==13);
  }

  @Test
  public void multiply(){
    int a = 5;
    int b = 6;
    int result = Lambda.multiply(a,b, (inta, intb) -> inta * intb);
    assertTrue(result==30);
  }

  @Test
  public void shouldSortStrings() throws Exception {
    List<String> input = Arrays.asList("C", "F", "A", "D", "B", "E");
    List<String> result = Lambda.sortStrings(input, Comparator.comparing(Function.identity()));
    assertThat(result, is(equalTo(Arrays.asList("A", "B", "C", "D", "E", "F"))));
  }
}

class TransactionLambda implements Runnable{

  private boolean consumed = false;

  public boolean isConsumed() {
    return consumed;
  }

  @Override
  public void run() {
    consumed = true;
  }
}

class Lambda {

  public static List<String> filter(List<String> strings, Predicate<String> condition){
    return strings.stream().filter(condition).collect(Collectors.toList());
  }

  public static void processWithinTransaction(Runnable runnable){
    Transaction transaction = new Transaction();
    transaction.start();
    runnable.run();
    transaction.stop();
  }

  public static String create(Supplier<String> supplier){
    return supplier.get();
  }

  public static Integer getStringLength(String s, Function<String, Integer> func){
    return func.apply(s);
  }

  public static int multiply(int a, int b, BinaryOperator<Integer> operator){
    return operator.apply(a,b);
  }

  public static List<String> sortStrings(List<String> strings, Comparator<String> comparator){
    strings.sort(comparator);
    return strings;
  }

}

class Transaction {

  public void start() {
    System.out.println("Start");
  }

  public void stop() {
    System.out.println("Stop");
  }
}
