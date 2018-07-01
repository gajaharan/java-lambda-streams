package lectures;

import static org.assertj.core.api.Assertions.assertThat;

import beans.Person;

import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mockdata.MockData;
import org.assertj.core.util.Lists;
import org.junit.Test;


public class Lecture1 {

  @Test
  public void imperativeApproach() throws IOException {
    List<Person> people = MockData.getPeople();
    List<Person> peopleAged18 = new ArrayList<>();
    // 1. Find people aged less or equal 18
    // 2. Then change implementation to find first 10 people

    int count = 0;
    int limit = 10;
    for(Person person : people) {
      if (person.getAge() <= 18 && count < limit) {
        peopleAged18.add(person);
        count++;
        System.out.println(person.getAge());
      }
    }

  }

  @Test
  public void declarativeApproachUsingStreams() throws Exception {
    ImmutableList<Person> people = MockData.getPeople();

    int limit = 10;

    people.stream().
            filter(person -> person.getAge() <= 18)
            .limit(limit)
            .collect(Collectors.toList())
            .forEach(System.out::println);

  }
}
