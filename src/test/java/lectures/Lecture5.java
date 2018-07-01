package lectures;


import static org.assertj.core.api.Assertions.assertThat;

import beans.Car;
import beans.Person;
import beans.PersonDTO;
import com.google.common.collect.ImmutableList;

import java.security.cert.CollectionCertStoreParameters;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import mockdata.MockData;
import org.junit.Test;

public class Lecture5 {

  @Test
  public void understandingFilter() throws Exception {
    ImmutableList<Car> cars = MockData.getCars();

    final Predicate<Car> carPredicate = car -> car.getPrice() < 10000;

    cars.stream()
            .filter(carPredicate)
            .forEach(System.out::println);
  }



  @Test
  public void ourFirstMapping() throws Exception {
    // transform from one data type to another
    List<Person> people = MockData.getPeople();

    Function<Person, PersonDTO> personPersonDTOFunction = person -> new PersonDTO(person.getId(), person.getFirstName(), person.getAge());

    List<PersonDTO> personDTOs = people.stream()
//            .map(person -> {
//              PersonDTO dto = new PersonDTO(person.getId(), person.getFirstName(), person.getAge());
//              return dto;
//            })
            //.map(PersonDTO::map)
            .map(personPersonDTOFunction)
            .collect(Collectors.toList());Collectors.toList();

    personDTOs.forEach(System.out::println);

    assertThat(personDTOs).hasSize(1000);

  }

  @Test
  public void averageCarPrice() throws Exception {
    // calculate average of car prices
    ImmutableList<Car> cars = MockData.getCars();

    Double average = cars.stream().mapToDouble(Car::getPrice).average().orElse(0);

    System.out.print(average);

  }

  @Test
  public void test() throws Exception {

  }
}



