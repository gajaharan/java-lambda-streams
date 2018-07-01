package lectures;


import static org.assertj.core.api.Assertions.assertThat;

import beans.Car;
import com.google.common.collect.ImmutableList;
import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import mockdata.MockData;
import org.junit.Test;

public class Lecture7 {

  @Test
  public void count() throws Exception {

    long count = MockData.getPeople()
            .stream()
            .filter(person -> person.getGender().equalsIgnoreCase("female"))
            .count();
    System.out.print(count);

  }

  @Test
  public void min() throws Exception {
    double min = MockData.getCars()
            .stream()
            .filter(car -> car.getColor().equalsIgnoreCase("blue"))
            .mapToDouble(Car::getPrice)
            .min()
            .getAsDouble();

    System.out.print(min);
  }

  @Test
  public void max() throws Exception {
    double max = MockData.getCars()
            .stream()
            .filter(car -> car.getColor().equalsIgnoreCase("blue"))
            .mapToDouble(Car::getPrice)
            .max()
            .orElse(0);

    System.out.print(max);
  }


  @Test
  public void average() throws Exception {
    List<Car> cars = MockData.getCars();

    double average = cars.stream()
            .mapToDouble(Car::getPrice)
            .average()
            .orElse(0);

    System.out.print(average);

  }

  @Test
  public void sum() throws Exception {
    List<Car> cars = MockData.getCars();
    double sum = cars.stream()
        .mapToDouble(Car::getPrice)
        .sum();
    BigDecimal bigDecimalSum = BigDecimal.valueOf(sum);
    System.out.println(sum);
    System.out.println(bigDecimalSum);

  }

  @Test
  public void statistics() throws Exception {
    List<Car> cars = MockData.getCars();
    DoubleSummaryStatistics statistics = cars.stream()
        .mapToDouble(Car::getPrice)
        .summaryStatistics();
    System.out.println(statistics);
    System.out.println(statistics.getAverage());
    System.out.println(statistics.getCount());
    System.out.println(statistics.getMax());
    System.out.println(statistics.getMin());
    System.out.println(statistics.getSum());
  }

}