package iterator.test.matchers.jpa;

import static iterator.test.matchers.jpa.JpaMatchers.hasColumnAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasDiscriminatorColumnAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasEntityAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasEntityListenersAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasEnumeratedAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasGeneratedValueAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasInheritanceAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasJoinColumnAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasLobAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasManyToManyAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasManyToOneAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasMappedSuperclassAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasOneToManyAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasOrderColumnAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasPrimaryKeyJoinColumnAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasTableAnnotation;
import static iterator.test.matchers.jpa.JpaMatchers.hasTemporalAnnotation;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import iterator.test.matchers.type.annotation.AnnotationMap;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.junit.jupiter.api.Test;

@MappedSuperclass
@Entity(name = "foo")
@Table(name = "quix")
@DiscriminatorColumn(name = "bar")
@PrimaryKeyJoinColumn(name = "baz")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners({String.class, Integer.class, Boolean.class})
class JpaMatchersTest {

  @Table
  @Entity
  @Inheritance
  @DiscriminatorColumn
  @PrimaryKeyJoinColumn
  private static class OtherClass {}

  @Column(name = "foo")
  private String columnAnnotation;

  @Column private String columnAnnotationWithDefaults;

  @JoinColumn(name = "bar")
  private String joinColumnAnnotation;

  @JoinColumn private String joinColumnAnnotationWithDefaults;

  @Lob private String lobAnnotation;

  @ManyToMany(fetch = FetchType.EAGER)
  private String manyToManyAnnotation;

  @ManyToMany private String manyToManyAnnotationWithDefaults;

  @ManyToOne(fetch = FetchType.LAZY)
  private String manyToOneAnnotation;

  @ManyToOne private String manyToOneAnnotationWithDefaults;

  @OneToMany(mappedBy = "foo")
  private String oneToManyAnnotation;

  @OneToMany private String oneToManyAnnotationWithDefaults;

  @Temporal(TemporalType.TIMESTAMP)
  private String temporalAnnotation;

  @OrderColumn(name = "baz")
  private String orderColumnAnnotation;

  @OrderColumn private String orderColumnAnnotationWithDefaults;

  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private String generatedValueAnnotation;

  @GeneratedValue private String generatedValueAnnotationWithDefaults;

  @Enumerated(EnumType.STRING)
  private String enumeratedAnnotation;

  @Enumerated private String enumeratedAnnotationWithDefaults;

  @Test
  void shouldNotBeAbleToInstantiateViaReflection() throws Exception {
    Constructor<JpaMatchers> constructor = JpaMatchers.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    try {
      constructor.newInstance();
    } catch (InvocationTargetException e) {
      assertThat(e.getCause(), instanceOf(IllegalStateException.class));
    }
  }

  @Test
  void shouldMatchColumnAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasColumnAnnotation(
            "columnAnnotation", AnnotationMap.from(Column.class).set("name", "foo")));
  }

  @Test
  void shouldMatchColumnAnnotationWithDefaults() {
    assertThat(JpaMatchersTest.class, hasColumnAnnotation("columnAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchColumnAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(
            hasColumnAnnotation(
                "columnAnnotation", AnnotationMap.from(Column.class).set("name", "bar"))));
  }

  @Test
  void shouldMatchLobAnnotation() {
    assertThat(JpaMatchersTest.class, hasLobAnnotation("lobAnnotation"));
  }

  @Test
  void shouldNotMatchLobAnnotation() {
    assertThat(JpaMatchersTest.class, not(hasLobAnnotation("columnAnnotation")));
  }

  @Test
  void shouldMatchJoinColumnAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasJoinColumnAnnotation(
            "joinColumnAnnotation", AnnotationMap.from(JoinColumn.class).set("name", "bar")));
  }

  @Test
  void shouldMatchJoinColumnAnnotationWithDefaults() {
    assertThat(JpaMatchersTest.class, hasJoinColumnAnnotation("joinColumnAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchJoinColumnAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(
            hasJoinColumnAnnotation(
                "joinColumnAnnotation", AnnotationMap.from(JoinColumn.class).set("name", "baz"))));
  }

  @Test
  void shouldMatchManyToManyAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasManyToManyAnnotation(
            "manyToManyAnnotation",
            AnnotationMap.from(ManyToMany.class).set("fetch", FetchType.EAGER)));
  }

  @Test
  void shouldMatchManyToManyAnnotationWithDefaults() {
    assertThat(JpaMatchersTest.class, hasManyToManyAnnotation("manyToManyAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchManyToManyAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(
            hasManyToManyAnnotation(
                "manyToManyAnnotation",
                AnnotationMap.from(ManyToMany.class).set("fetch", FetchType.LAZY))));
  }

  @Test
  void shouldMatchManyToOneAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasManyToOneAnnotation(
            "manyToOneAnnotation",
            AnnotationMap.from(ManyToOne.class).set("fetch", FetchType.LAZY)));
  }

  @Test
  void shouldMatchManyToOneAnnotationWithDefaults() {
    assertThat(JpaMatchersTest.class, hasManyToOneAnnotation("manyToOneAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchManyToOneAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(
            hasManyToOneAnnotation(
                "manyToOneAnnotation",
                AnnotationMap.from(ManyToOne.class).set("fetch", FetchType.EAGER))));
  }

  @Test
  void shouldMatchOneToManyAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasOneToManyAnnotation(
            "oneToManyAnnotation", AnnotationMap.from(OneToMany.class).set("mappedBy", "foo")));
  }

  @Test
  void shouldMatchOneToManyAnnotationWithDefaults() {
    assertThat(JpaMatchersTest.class, hasOneToManyAnnotation("oneToManyAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchOneToManyAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(
            hasOneToManyAnnotation(
                "oneToManyAnnotation",
                AnnotationMap.from(OneToMany.class).set("mappedBy", "bar"))));
  }

  @Test
  void shouldMatchTableAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasTableAnnotation(AnnotationMap.from(Table.class).set("name", "quix")));
  }

  @Test
  void shouldMatchTableAnnotationWithDefaults() {
    assertThat(OtherClass.class, hasTableAnnotation());
  }

  @Test
  void shouldNotMatchTableAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(hasTableAnnotation(AnnotationMap.from(Table.class).set("name", "foo"))));
  }

  @Test
  void shouldMatchTemporalAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasTemporalAnnotation(
            "temporalAnnotation",
            AnnotationMap.from(Temporal.class).set("value", TemporalType.TIMESTAMP)));
  }

  @Test
  void shouldMatchTemporalAnnotationGivenTemporalType() {
    assertThat(
        JpaMatchersTest.class, hasTemporalAnnotation("temporalAnnotation", TemporalType.TIMESTAMP));
  }

  @Test
  void shouldNotMatchTemporalAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(
            hasTemporalAnnotation(
                "temporalAnnotation",
                AnnotationMap.from(Temporal.class).set("value", TemporalType.DATE))));
  }

  @Test
  void shouldMatchOrderColumnAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasOrderColumnAnnotation(
            "orderColumnAnnotation", AnnotationMap.from(OrderColumn.class).set("name", "baz")));
  }

  @Test
  void shouldMatchOrderColumnAnnotationWithDefaults() {
    assertThat(
        JpaMatchersTest.class, hasOrderColumnAnnotation("orderColumnAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchOrderColumnAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(
            hasOrderColumnAnnotation(
                "orderColumnAnnotation",
                AnnotationMap.from(OrderColumn.class).set("name", "quix"))));
  }

  @Test
  void shouldMatchEntityAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasEntityAnnotation(AnnotationMap.from(Entity.class).set("name", "foo")));
  }

  @Test
  void shouldMatchEntityAnnotationWithDefaults() {
    assertThat(OtherClass.class, hasEntityAnnotation());
  }

  @Test
  void shouldNotMatchEntityAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(hasEntityAnnotation(AnnotationMap.from(Entity.class).set("name", "bar"))));
  }

  @Test
  void shouldMatchMappedSuperclassAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasMappedSuperclassAnnotation(AnnotationMap.from(MappedSuperclass.class)));
  }

  @Test
  void shouldMatchMappedSuperclassAnnotationWithNoArguments() {
    assertThat(JpaMatchersTest.class, hasMappedSuperclassAnnotation());
  }

  @Test
  void shouldNotMatchMappedSuperclassAnnotation() {
    assertThat(
        JpaMatchers.class,
        not(hasMappedSuperclassAnnotation(AnnotationMap.from(MappedSuperclass.class))));
  }

  @Test
  void shouldMatchEntityListenersAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasEntityListenersAnnotation(
            AnnotationMap.from(EntityListeners.class)
                .set("value", new Class[] {String.class, Integer.class, Boolean.class})));
  }

  @Test
  void shouldMatchEntityListenersAnnotationGivenValue() {
    assertThat(
        JpaMatchersTest.class,
        hasEntityListenersAnnotation(new Class[] {String.class, Integer.class, Boolean.class}));
  }

  @Test
  void shouldNotMatchEntityListenersAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(
            hasEntityListenersAnnotation(
                AnnotationMap.from(EntityListeners.class)
                    .set("value", new Class[] {String.class, Integer.class, Long.class}))));
  }

  @Test
  void shouldMatchInheritanceAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasInheritanceAnnotation(
            AnnotationMap.from(Inheritance.class).set("strategy", InheritanceType.JOINED)));
  }

  @Test
  void shouldMatchInheritanceAnnotationWithDefaults() {
    assertThat(OtherClass.class, hasInheritanceAnnotation());
  }

  @Test
  void shouldNotMatchInheritanceAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(
            hasInheritanceAnnotation(
                AnnotationMap.from(Inheritance.class)
                    .set("strategy", InheritanceType.TABLE_PER_CLASS))));
  }

  @Test
  void shouldMatchGeneratedValueAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasGeneratedValueAnnotation(
            "generatedValueAnnotation",
            AnnotationMap.from(GeneratedValue.class).set("strategy", GenerationType.SEQUENCE)));
  }

  @Test
  void shouldMatchGeneratedValueAnnotationWithDefaults() {
    assertThat(
        JpaMatchersTest.class, hasGeneratedValueAnnotation("generatedValueAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchGeneratedValueAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(
            hasGeneratedValueAnnotation(
                "generatedValueAnnotation",
                AnnotationMap.from(GeneratedValue.class).set("strategy", GenerationType.TABLE))));
  }

  @Test
  void shouldMatchEnumeratedAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasEnumeratedAnnotation(
            "enumeratedAnnotation",
            AnnotationMap.from(Enumerated.class).set("value", EnumType.STRING)));
  }

  @Test
  void shouldMatchEnumeratedAnnotationGivenEnumType() {
    assertThat(
        JpaMatchersTest.class, hasEnumeratedAnnotation("enumeratedAnnotation", EnumType.STRING));
  }

  @Test
  void shouldMatchEnumeratedAnnotationWithDefaults() {
    assertThat(JpaMatchersTest.class, hasEnumeratedAnnotation("enumeratedAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchEnumeratedAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(
            hasEnumeratedAnnotation(
                "enumeratedAnnotation",
                AnnotationMap.from(Enumerated.class).set("value", EnumType.ORDINAL))));
  }

  @Test
  void shouldMatchDiscriminatorColumnAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasDiscriminatorColumnAnnotation(
            AnnotationMap.from(DiscriminatorColumn.class).set("name", "bar")));
  }

  @Test
  void shouldMatchDiscriminatorColumnAnnotationWithDefaults() {
    assertThat(OtherClass.class, hasDiscriminatorColumnAnnotation());
  }

  @Test
  void shouldNotMatchDiscriminatorColumnAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(
            hasDiscriminatorColumnAnnotation(
                AnnotationMap.from(DiscriminatorColumn.class).set("name", "baz"))));
  }

  @Test
  void shouldMatchPrimaryKeyJoinColumnAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        hasPrimaryKeyJoinColumnAnnotation(
            AnnotationMap.from(PrimaryKeyJoinColumn.class).set("name", "baz")));
  }

  @Test
  void shouldMatchPrimaryKeyJoinColumnAnnotationWithDefaults() {
    assertThat(OtherClass.class, hasPrimaryKeyJoinColumnAnnotation());
  }

  @Test
  void shouldNotMatchPrimaryKeyJoinColumnAnnotation() {
    assertThat(
        JpaMatchersTest.class,
        not(
            hasPrimaryKeyJoinColumnAnnotation(
                AnnotationMap.from(PrimaryKeyJoinColumn.class).set("name", "quix"))));
  }
}
