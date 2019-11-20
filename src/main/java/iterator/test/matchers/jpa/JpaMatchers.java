/**
 * Copyright © 2016 Iterator Ltd. (iteratoruk@gmail.com)
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package iterator.test.matchers.jpa;

import java.lang.annotation.Annotation;

import javax.persistence.*;

import org.hamcrest.Matcher;

import iterator.test.matchers.type.annotation.AnnotationMap;
import iterator.test.matchers.type.annotation.FieldAnnotationMatcher;
import iterator.test.matchers.type.annotation.TypeAnnotationMatcher;

public final class JpaMatchers {

  private static final String VALUE = "value";

  public static <T> Matcher<Class<T>> hasColumnAnnotation(String fieldName) {
    return hasFieldAnnotation(fieldName, AnnotationMap.from(Column.class));
  }

  public static <T> Matcher<Class<T>> hasColumnAnnotation(
      String fieldName, AnnotationMap<Column> columnAnnotation) {
    return hasFieldAnnotation(fieldName, columnAnnotation);
  }

  public static <T> Matcher<Class<T>> hasDiscriminatorColumnAnnotation() {
    return hasTypeAnnotation(AnnotationMap.from(DiscriminatorColumn.class));
  }

  public static <T> Matcher<Class<T>> hasDiscriminatorColumnAnnotation(
      AnnotationMap<DiscriminatorColumn> discriminatorColumn) {
    return hasTypeAnnotation(discriminatorColumn);
  }

  public static <T> Matcher<Class<T>> hasEntityAnnotation() {
    return hasTypeAnnotation(AnnotationMap.from(Entity.class));
  }

  public static <T> Matcher<Class<T>> hasEntityAnnotation(AnnotationMap<Entity> entityAnnotation) {
    return hasTypeAnnotation(entityAnnotation);
  }

  public static <T> Matcher<Class<T>> hasEntityListenersAnnotation(Class<?>[] entityListeners) {
    return hasTypeAnnotation(AnnotationMap.from(EntityListeners.class).set(VALUE, entityListeners));
  }

  public static <T> Matcher<Class<T>> hasEntityListenersAnnotation(
      AnnotationMap<EntityListeners> entityListenersAnnotation) {
    return hasTypeAnnotation(entityListenersAnnotation);
  }

  public static <T> Matcher<Class<T>> hasEnumeratedAnnotation(String fieldName) {
    return hasFieldAnnotation(fieldName, AnnotationMap.from(Enumerated.class));
  }

  public static <T> Matcher<Class<T>> hasEnumeratedAnnotation(String fieldName, EnumType enumType) {
    return hasFieldAnnotation(fieldName, AnnotationMap.from(Enumerated.class).set(VALUE, enumType));
  }

  public static <T> Matcher<Class<T>> hasEnumeratedAnnotation(
      String fieldName, AnnotationMap<Enumerated> enumeratedAnnotation) {
    return hasFieldAnnotation(fieldName, enumeratedAnnotation);
  }

  public static <T> Matcher<Class<T>> hasGeneratedValueAnnotation(String fieldName) {
    return hasFieldAnnotation(fieldName, AnnotationMap.from(GeneratedValue.class));
  }

  public static <T> Matcher<Class<T>> hasGeneratedValueAnnotation(
      String fieldName, AnnotationMap<GeneratedValue> generatedValueAnnotation) {
    return hasFieldAnnotation(fieldName, generatedValueAnnotation);
  }

  public static <T> Matcher<Class<T>> hasInheritanceAnnotation() {
    return hasTypeAnnotation(AnnotationMap.from(Inheritance.class));
  }

  public static <T> Matcher<Class<T>> hasInheritanceAnnotation(
      AnnotationMap<Inheritance> inheritanceAnnotation) {
    return hasTypeAnnotation(inheritanceAnnotation);
  }

  public static <T> Matcher<Class<T>> hasJoinColumnAnnotation(String fieldName) {
    return hasFieldAnnotation(fieldName, AnnotationMap.from(JoinColumn.class));
  }

  public static <T> Matcher<Class<T>> hasJoinColumnAnnotation(
      String fieldName, AnnotationMap<JoinColumn> joinColumnAnnotation) {
    return hasFieldAnnotation(fieldName, joinColumnAnnotation);
  }

  public static <T> Matcher<Class<T>> hasLobAnnotation(String fieldName) {
    return hasFieldAnnotation(fieldName, AnnotationMap.from(Lob.class));
  }

  public static <T> Matcher<Class<T>> hasManyToManyAnnotation(String fieldName) {
    return hasFieldAnnotation(fieldName, AnnotationMap.from(ManyToMany.class));
  }

  public static <T> Matcher<Class<T>> hasManyToManyAnnotation(
      String fieldName, AnnotationMap<ManyToMany> manyToManyAnnotation) {
    return hasFieldAnnotation(fieldName, manyToManyAnnotation);
  }

  public static <T> Matcher<Class<T>> hasManyToOneAnnotation(String fieldName) {
    return hasFieldAnnotation(fieldName, AnnotationMap.from(ManyToOne.class));
  }

  public static <T> Matcher<Class<T>> hasManyToOneAnnotation(
      String fieldName, AnnotationMap<ManyToOne> manyToOneAnnotation) {
    return hasFieldAnnotation(fieldName, manyToOneAnnotation);
  }

  public static <T> Matcher<Class<T>> hasMappedSuperclassAnnotation() {
    return hasMappedSuperclassAnnotation(AnnotationMap.from(MappedSuperclass.class));
  }

  public static <T> Matcher<Class<T>> hasMappedSuperclassAnnotation(
      AnnotationMap<MappedSuperclass> mappedSuperclassAnnotation) {
    return hasTypeAnnotation(mappedSuperclassAnnotation);
  }

  public static <T> Matcher<Class<T>> hasOneToManyAnnotation(String fieldName) {
    return hasFieldAnnotation(fieldName, AnnotationMap.from(OneToMany.class));
  }

  public static <T> Matcher<Class<T>> hasOneToManyAnnotation(
      String fieldName, AnnotationMap<OneToMany> oneToManyAnnotation) {
    return hasFieldAnnotation(fieldName, oneToManyAnnotation);
  }

  public static <T> Matcher<Class<T>> hasOrderColumnAnnotation(String fieldName) {
    return hasFieldAnnotation(fieldName, AnnotationMap.from(OrderColumn.class));
  }

  public static <T> Matcher<Class<T>> hasOrderColumnAnnotation(
      String fieldName, AnnotationMap<OrderColumn> orderColumnAnnotation) {
    return hasFieldAnnotation(fieldName, orderColumnAnnotation);
  }

  public static <T> Matcher<Class<T>> hasPrimaryKeyJoinColumnAnnotation() {
    return hasTypeAnnotation(AnnotationMap.from(PrimaryKeyJoinColumn.class));
  }

  public static <T> Matcher<Class<T>> hasPrimaryKeyJoinColumnAnnotation(
      AnnotationMap<PrimaryKeyJoinColumn> primaryKeyJoinColumnAnnotation) {
    return hasTypeAnnotation(primaryKeyJoinColumnAnnotation);
  }

  public static <T> Matcher<Class<T>> hasTableAnnotation() {
    return hasTypeAnnotation(AnnotationMap.from(Table.class));
  }

  public static <T> Matcher<Class<T>> hasTableAnnotation(AnnotationMap<Table> tableAnnotation) {
    return hasTypeAnnotation(tableAnnotation);
  }

  public static <T> Matcher<Class<T>> hasTemporalAnnotation(
      String fieldName, TemporalType temporalType) {
    return hasFieldAnnotation(
        fieldName, AnnotationMap.from(Temporal.class).set(VALUE, temporalType));
  }

  public static <T> Matcher<Class<T>> hasTemporalAnnotation(
      String fieldName, AnnotationMap<Temporal> temporalAnnotation) {
    return hasFieldAnnotation(fieldName, temporalAnnotation);
  }

  private static <A extends Annotation, T> Matcher<Class<T>> hasFieldAnnotation(
      String fieldName, AnnotationMap<A> fieldAnnotation) {
    return new FieldAnnotationMatcher<>(fieldName, fieldAnnotation);
  }

  private static <A extends Annotation, T> Matcher<Class<T>> hasTypeAnnotation(
      AnnotationMap<A> typeAnnotation) {
    return new TypeAnnotationMatcher<>(typeAnnotation);
  }

  private JpaMatchers() {
    throw new IllegalStateException();
  }
}
