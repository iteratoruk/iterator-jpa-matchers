
package iterator.test.matchers.jpa;

import static iterator.test.matchers.jpa.JpaMatchers.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.persistence.*;

import org.junit.Test;

import iterator.test.matchers.type.annotation.AnnotationMap;

@MappedSuperclass
@Entity(name = "foo")
@Table(name = "quix")
@DiscriminatorColumn(name = "bar")
@PrimaryKeyJoinColumn(name = "baz")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners({ String.class, Integer.class, Boolean.class })
public class JpaMatchersTest {

    @Table
    @Entity
    @Inheritance
    @DiscriminatorColumn
    @PrimaryKeyJoinColumn
    public static class OtherClass {}

    @Column(name = "foo")
    private String columnAnnotation;

    @Column
    private String columnAnnotationWithDefaults;

    @JoinColumn(name = "bar")
    private String joinColumnAnnotation;

    @JoinColumn
    private String joinColumnAnnotationWithDefaults;

    @Lob
    private String lobAnnotation;

    @ManyToMany(fetch = FetchType.EAGER)
    private String manyToManyAnnotation;

    @ManyToMany
    private String manyToManyAnnotationWithDefaults;

    @ManyToOne(fetch = FetchType.LAZY)
    private String manyToOneAnnotation;

    @ManyToOne
    private String manyToOneAnnotationWithDefaults;

    @OneToMany(mappedBy = "foo")
    private String oneToManyAnnotation;

    @OneToMany
    private String oneToManyAnnotationWithDefaults;

    @Temporal(TemporalType.TIMESTAMP)
    private String temporalAnnotation;

    @OrderColumn(name = "baz")
    private String orderColumnAnnotation;

    @OrderColumn
    private String orderColumnAnnotationWithDefaults;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String generatedValueAnnotation;

    @GeneratedValue
    private String generatedValueAnnotationWithDefaults;

    @Enumerated(EnumType.STRING)
    private String enumeratedAnnotation;

    @Enumerated
    private String enumeratedAnnotationWithDefaults;

    @Test
    public void shouldNotBeAbleToInstantiateViaReflection() throws Exception {
        Constructor<JpaMatchers> constructor = JpaMatchers.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance(new Object[0]);
        } catch (InvocationTargetException e) {
            assertThat(e.getCause(), instanceOf(IllegalStateException.class));
        }
    }

    @Test
    public void shouldMatchColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasColumnAnnotation("columnAnnotation", AnnotationMap.from(Column.class).set("name", "foo")));
    }

    @Test
    public void shouldMatchColumnAnnotationWithDefaults() throws Exception {
        assertThat(JpaMatchersTest.class, hasColumnAnnotation("columnAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasColumnAnnotation("columnAnnotation", AnnotationMap.from(Column.class).set("name", "bar"))));
    }

    @Test
    public void shouldMatchLobAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasLobAnnotation("lobAnnotation"));
    }

    @Test
    public void shouldNotMatchLobAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasLobAnnotation("columnAnnotation")));
    }

    @Test
    public void shouldMatchJoinColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasJoinColumnAnnotation("joinColumnAnnotation", AnnotationMap.from(JoinColumn.class).set("name", "bar")));
    }

    @Test
    public void shouldMatchJoinColumnAnnotationWithDefaults() throws Exception {
        assertThat(JpaMatchersTest.class, hasJoinColumnAnnotation("joinColumnAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchJoinColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasJoinColumnAnnotation("joinColumnAnnotation", AnnotationMap.from(JoinColumn.class).set("name", "baz"))));
    }

    @Test
    public void shouldMatchManyToManyAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasManyToManyAnnotation("manyToManyAnnotation", AnnotationMap.from(ManyToMany.class).set("fetch", FetchType.EAGER)));
    }

    @Test
    public void shouldMatchManyToManyAnnotationWithDefaults() throws Exception {
        assertThat(JpaMatchersTest.class, hasManyToManyAnnotation("manyToManyAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchManyToManyAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasManyToManyAnnotation("manyToManyAnnotation", AnnotationMap.from(ManyToMany.class).set("fetch", FetchType.LAZY))));
    }

    @Test
    public void shouldMatchManyToOneAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasManyToOneAnnotation("manyToOneAnnotation", AnnotationMap.from(ManyToOne.class).set("fetch", FetchType.LAZY)));
    }

    @Test
    public void shouldMatchManyToOneAnnotationWithDefaults() throws Exception {
        assertThat(JpaMatchersTest.class, hasManyToOneAnnotation("manyToOneAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchManyToOneAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasManyToOneAnnotation("manyToOneAnnotation", AnnotationMap.from(ManyToOne.class).set("fetch", FetchType.EAGER))));
    }

    @Test
    public void shouldMatchOneToManyAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasOneToManyAnnotation("oneToManyAnnotation", AnnotationMap.from(OneToMany.class).set("mappedBy", "foo")));
    }

    @Test
    public void shouldMatchOneToManyAnnotationWithDefaults() throws Exception {
        assertThat(JpaMatchersTest.class, hasOneToManyAnnotation("oneToManyAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchOneToManyAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasOneToManyAnnotation("oneToManyAnnotation", AnnotationMap.from(OneToMany.class).set("mappedBy", "bar"))));
    }

    @Test
    public void shouldMatchTableAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasTableAnnotation(AnnotationMap.from(Table.class).set("name", "quix")));
    }

    @Test
    public void shouldMatchTableAnnotationWithDefaults() throws Exception {
        assertThat(OtherClass.class, hasTableAnnotation());
    }

    @Test
    public void shouldNotMatchTableAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasTableAnnotation(AnnotationMap.from(Table.class).set("name", "foo"))));
    }

    @Test
    public void shouldMatchTemporalAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasTemporalAnnotation("temporalAnnotation", AnnotationMap.from(Temporal.class).set("value", TemporalType.TIMESTAMP)));
    }

    @Test
    public void shouldMatchTemporalAnnotationGivenTemporalType() throws Exception {
        assertThat(JpaMatchersTest.class, hasTemporalAnnotation("temporalAnnotation", TemporalType.TIMESTAMP));
    }

    @Test
    public void shouldNotMatchTemporalAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasTemporalAnnotation("temporalAnnotation", AnnotationMap.from(Temporal.class).set("value", TemporalType.DATE))));
    }

    @Test
    public void shouldMatchOrderColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasOrderColumnAnnotation("orderColumnAnnotation", AnnotationMap.from(OrderColumn.class).set("name", "baz")));
    }

    @Test
    public void shouldMatchOrderColumnAnnotationWithDefaults() throws Exception {
        assertThat(JpaMatchersTest.class, hasOrderColumnAnnotation("orderColumnAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchOrderColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasOrderColumnAnnotation("orderColumnAnnotation", AnnotationMap.from(OrderColumn.class).set("name", "quix"))));
    }

    @Test
    public void shouldMatchEntityAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasEntityAnnotation(AnnotationMap.from(Entity.class).set("name", "foo")));
    }

    @Test
    public void shouldMatchEntityAnnotationWithDefaults() throws Exception {
        assertThat(OtherClass.class, hasEntityAnnotation());
    }

    @Test
    public void shouldNotMatchEntityAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasEntityAnnotation(AnnotationMap.from(Entity.class).set("name", "bar"))));
    }

    @Test
    public void shouldMatchMappedSuperclassAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasMappedSuperclassAnnotation(AnnotationMap.from(MappedSuperclass.class)));
    }

    @Test
    public void shouldMatchMappedSuperclassAnnotationWithNoArguments() throws Exception {
        assertThat(JpaMatchersTest.class, hasMappedSuperclassAnnotation());
    }

    @Test
    public void shouldNotMatchMappedSuperclassAnnotation() throws Exception {
        assertThat(JpaMatchers.class, not(hasMappedSuperclassAnnotation(AnnotationMap.from(MappedSuperclass.class))));
    }

    @Test
    public void shouldMatchEntityListenersAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasEntityListenersAnnotation(AnnotationMap.from(EntityListeners.class).set("value", new Class[] { String.class, Integer.class, Boolean.class })));
    }

    @Test
    public void shouldMatchEntityListenersAnnotationGivenValue() throws Exception {
        assertThat(JpaMatchersTest.class, hasEntityListenersAnnotation(new Class[] { String.class, Integer.class, Boolean.class }));
    }

    @Test
    public void shouldNotMatchEntityListenersAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasEntityListenersAnnotation(AnnotationMap.from(EntityListeners.class).set("value", new Class[] { String.class, Integer.class, Long.class }))));
    }

    @Test
    public void shouldMatchInheritanceAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasInheritanceAnnotation(AnnotationMap.from(Inheritance.class).set("strategy", InheritanceType.JOINED)));
    }

    @Test
    public void shouldMatchInheritanceAnnotationWithDefaults() throws Exception {
        assertThat(OtherClass.class, hasInheritanceAnnotation());
    }

    @Test
    public void shouldNotMatchInheritanceAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasInheritanceAnnotation(AnnotationMap.from(Inheritance.class).set("strategy", InheritanceType.TABLE_PER_CLASS))));
    }

    @Test
    public void shouldMatchGeneratedValueAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasGeneratedValueAnnotation("generatedValueAnnotation", AnnotationMap.from(GeneratedValue.class).set("strategy", GenerationType.SEQUENCE)));
    }

    @Test
    public void shouldMatchGeneratedValueAnnotationWithDefaults() throws Exception {
        assertThat(JpaMatchersTest.class, hasGeneratedValueAnnotation("generatedValueAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchGeneratedValueAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasGeneratedValueAnnotation("generatedValueAnnotation", AnnotationMap.from(GeneratedValue.class).set("strategy", GenerationType.TABLE))));
    }

    @Test
    public void shouldMatchEnumeratedAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasEnumeratedAnnotation("enumeratedAnnotation", AnnotationMap.from(Enumerated.class).set("value", EnumType.STRING)));
    }

    @Test
    public void shouldMatchEnumeratedAnnotationGivenEnumType() throws Exception {
        assertThat(JpaMatchersTest.class, hasEnumeratedAnnotation("enumeratedAnnotation", EnumType.STRING));
    }

    @Test
    public void shouldMatchEnumeratedAnnotationWithDefaults() throws Exception {
        assertThat(JpaMatchersTest.class, hasEnumeratedAnnotation("enumeratedAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchEnumeratedAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasEnumeratedAnnotation("enumeratedAnnotation", AnnotationMap.from(Enumerated.class).set("value", EnumType.ORDINAL))));
    }

    @Test
    public void shouldMatchDiscriminatorColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasDiscriminatorColumnAnnotation(AnnotationMap.from(DiscriminatorColumn.class).set("name", "bar")));
    }

    @Test
    public void shouldMatchDiscriminatorColumnAnnotationWithDefaults() throws Exception {
        assertThat(OtherClass.class, hasDiscriminatorColumnAnnotation());
    }

    @Test
    public void shouldNotMatchDiscriminatorColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasDiscriminatorColumnAnnotation(AnnotationMap.from(DiscriminatorColumn.class).set("name", "baz"))));
    }

    @Test
    public void shouldMatchPrimaryKeyJoinColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasPrimaryKeyJoinColumnAnnotation(AnnotationMap.from(PrimaryKeyJoinColumn.class).set("name", "baz")));
    }

    @Test
    public void shouldMatchPrimaryKeyJoinColumnAnnotationWithDefaults() throws Exception {
        assertThat(OtherClass.class, hasPrimaryKeyJoinColumnAnnotation());
    }

    @Test
    public void shouldNotMatchPrimaryKeyJoinColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasPrimaryKeyJoinColumnAnnotation(AnnotationMap.from(PrimaryKeyJoinColumn.class).set("name", "quix"))));
    }

}
