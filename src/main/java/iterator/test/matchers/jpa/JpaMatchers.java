/**
 * Copyright (C) 2016 Iterator Ltd. (iteratoruk@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package iterator.test.matchers.jpa;

import iterator.test.matchers.type.annotation.AnnotationMap;
import iterator.test.matchers.type.annotation.FieldAnnotationMatcher;
import iterator.test.matchers.type.annotation.TypeAnnotationMatcher;

import java.lang.annotation.Annotation;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Inheritance;
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

import org.hamcrest.Matcher;

public final class JpaMatchers {

    public static <T> Matcher<Class<T>> hasColumnAnnotation(String fieldName, AnnotationMap<Column> columnAnnotation) {
        return hasFieldAnnotation(fieldName, columnAnnotation);
    }

    public static <T> Matcher<Class<T>> hasDiscriminatorColumnAnnotation(AnnotationMap<DiscriminatorColumn> discriminatorColumn) {
        return hasTypeAnnotation(discriminatorColumn);
    }

    public static <T> Matcher<Class<T>> hasEntityAnnotation(AnnotationMap<Entity> entityAnnotation) {
        return hasTypeAnnotation(entityAnnotation);
    }

    public static <T> Matcher<Class<T>> hasEntityListenersAnnotation(AnnotationMap<EntityListeners> entityListenersAnnotation) {
        return hasTypeAnnotation(entityListenersAnnotation);
    }

    public static <T> Matcher<Class<T>> hasEnumeratedAnnotation(String fieldName, AnnotationMap<Enumerated> enumeratedAnnotation) {
        return hasFieldAnnotation(fieldName, enumeratedAnnotation);
    }

    public static <T> Matcher<Class<T>> hasGeneratedValueAnnotation(String fieldName, AnnotationMap<GeneratedValue> generatedValueAnnotation) {
        return hasFieldAnnotation(fieldName, generatedValueAnnotation);
    }

    public static <T> Matcher<Class<T>> hasInheritanceAnnotation(AnnotationMap<Inheritance> inheritanceAnnotation) {
        return hasTypeAnnotation(inheritanceAnnotation);
    }

    public static <T> Matcher<Class<T>> hasJoinColumnAnnotation(String fieldName, AnnotationMap<JoinColumn> columnAnnotation) {
        return hasFieldAnnotation(fieldName, columnAnnotation);
    }

    public static <T> Matcher<Class<T>> hasLobAnnotation(String fieldName) {
        return hasFieldAnnotation(fieldName, AnnotationMap.from(Lob.class));
    }

    public static <T> Matcher<Class<T>> hasManyToManyAnnotation(String fieldName, AnnotationMap<ManyToMany> columnAnnotation) {
        return hasFieldAnnotation(fieldName, columnAnnotation);
    }

    public static <T> Matcher<Class<T>> hasManyToOneAnnotation(String fieldName, AnnotationMap<ManyToOne> columnAnnotation) {
        return hasFieldAnnotation(fieldName, columnAnnotation);
    }

    public static <T> Matcher<Class<T>> hasMappedSuperclassAnnotation(AnnotationMap<MappedSuperclass> mappedSuperclassAnnotation) {
        return hasTypeAnnotation(mappedSuperclassAnnotation);
    }

    public static <T> Matcher<Class<T>> hasOneToManyAnnotation(String fieldName, AnnotationMap<OneToMany> columnAnnotation) {
        return hasFieldAnnotation(fieldName, columnAnnotation);
    }

    public static <T> Matcher<Class<T>> hasOrderColumnAnnotation(String fieldName, AnnotationMap<OrderColumn> orderColumnAnnotation) {
        return hasFieldAnnotation(fieldName, orderColumnAnnotation);
    }

    public static <T> Matcher<Class<T>> hasPrimaryKeyJoinColumnAnnotation(AnnotationMap<PrimaryKeyJoinColumn> primaryKeyJoinColumnAnnotation) {
        return hasTypeAnnotation(primaryKeyJoinColumnAnnotation);
    }

    public static <T> Matcher<Class<T>> hasTableAnnotation(AnnotationMap<Table> tableAnnotation) {
        return hasTypeAnnotation(tableAnnotation);
    }

    public static <T> Matcher<Class<T>> hasTemporalAnnotation(String fieldName, AnnotationMap<Temporal> columnAnnotation) {
        return hasFieldAnnotation(fieldName, columnAnnotation);
    }

    private static <A extends Annotation, T> Matcher<Class<T>> hasFieldAnnotation(String fieldName, AnnotationMap<A> annotationMap) {
        return new FieldAnnotationMatcher<>(fieldName, annotationMap);
    }

    private static <A extends Annotation, T> Matcher<Class<T>> hasTypeAnnotation(AnnotationMap<A> annotationMap) {
        return new TypeAnnotationMatcher<>(annotationMap);
    }

    private JpaMatchers() {
        throw new IllegalStateException();
    }

}
