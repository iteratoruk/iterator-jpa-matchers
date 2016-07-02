
package iterator.test.matchers.jpa;

import static iterator.test.matchers.jpa.JpaMatchers.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
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

import org.junit.Test;

@MappedSuperclass
@Entity(name = "foo")
@Table(name = "quix")
@DiscriminatorColumn(name = "bar")
@PrimaryKeyJoinColumn(name = "baz")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners({ String.class, Integer.class, Boolean.class })
public class JpaMatchersTest {

    @Column(name = "foo")
    private String columnAnnotation;

    @JoinColumn(name = "bar")
    private String joinColumnAnnotation;

    @Lob
    private String lobAnnotation;

    @ManyToMany(fetch = FetchType.EAGER)
    private String manyToManyAnnotation;

    @ManyToOne(fetch = FetchType.LAZY)
    private String manyToOneAnnotation;

    @OneToMany(mappedBy = "foo")
    private String oneToManyAnnotation;

    @Temporal(TemporalType.TIMESTAMP)
    private String temporalAnnotation;

    @OrderColumn(name = "baz")
    private String orderColumnAnnotation;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String generatedValueAnnotation;

    @Enumerated(EnumType.STRING)
    private String enumeratedAnnotation;

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
    public void shouldNotMatchJoinColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasJoinColumnAnnotation("joinColumnAnnotation", AnnotationMap.from(JoinColumn.class).set("name", "baz"))));
    }

    @Test
    public void shouldMatchManyToManyAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasManyToManyAnnotation("manyToManyAnnotation", AnnotationMap.from(ManyToMany.class).set("fetch", FetchType.EAGER)));
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
    public void shouldNotMatchManyToOneAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasManyToOneAnnotation("manyToOneAnnotation", AnnotationMap.from(ManyToOne.class).set("fetch", FetchType.EAGER))));
    }

    @Test
    public void shouldMatchOneToManyAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasOneToManyAnnotation("oneToManyAnnotation", AnnotationMap.from(OneToMany.class).set("mappedBy", "foo")));
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
    public void shouldNotMatchTableAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasTableAnnotation(AnnotationMap.from(Table.class).set("name", "foo"))));
    }

    @Test
    public void shouldMatchTemporalAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasTemporalAnnotation("temporalAnnotation", AnnotationMap.from(Temporal.class).set("value", TemporalType.TIMESTAMP)));
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
    public void shouldNotMatchOrderColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasOrderColumnAnnotation("orderColumnAnnotation", AnnotationMap.from(OrderColumn.class).set("name", "quix"))));
    }

    @Test
    public void shouldMatchEntityAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasEntityAnnotation(AnnotationMap.from(Entity.class).set("name", "foo")));
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
    public void shouldNotMatchMappedSuperclassAnnotation() throws Exception {
        assertThat(JpaMatchers.class, not(hasMappedSuperclassAnnotation(AnnotationMap.from(MappedSuperclass.class))));
    }

    @Test
    public void shouldMatchEntityListenersAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasEntityListenersAnnotation(AnnotationMap.from(EntityListeners.class).set("value", new Class[] { String.class, Integer.class, Boolean.class })));
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
    public void shouldNotMatchInheritanceAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasInheritanceAnnotation(AnnotationMap.from(Inheritance.class).set("strategy", InheritanceType.TABLE_PER_CLASS))));
    }

    @Test
    public void shouldMatchGeneratedValueAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasGeneratedValueAnnotation("generatedValueAnnotation", AnnotationMap.from(GeneratedValue.class).set("strategy", GenerationType.SEQUENCE)));
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
    public void shouldNotMatchEnumeratedAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasEnumeratedAnnotation("enumeratedAnnotation", AnnotationMap.from(Enumerated.class).set("value", EnumType.ORDINAL))));
    }

    @Test
    public void shouldMatchDiscriminatorColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, hasDiscriminatorColumnAnnotation(AnnotationMap.from(DiscriminatorColumn.class).set("name", "bar")));
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
    public void shouldNotMatchPrimaryKeyJoinColumnAnnotation() throws Exception {
        assertThat(JpaMatchersTest.class, not(hasPrimaryKeyJoinColumnAnnotation(AnnotationMap.from(PrimaryKeyJoinColumn.class).set("name", "quix"))));
    }

}
