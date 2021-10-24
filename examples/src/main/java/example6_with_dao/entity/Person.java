package example6_with_dao.entity;

import org.jetbrains.annotations.NotNull;

public final class Person {

  private int id;
  private @NotNull String name;

  public Person(int id, @NotNull String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public @NotNull String getName() {
    return name;
  }

  public void setName(@NotNull String name) {
    this.name = name;
  }

  @Override
  public @NotNull String toString() {
    return "Person{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }
}
