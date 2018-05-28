/*
 * Copyright 2016-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("NOTHING_TO_INLINE")

package kotlinx.collections.immutable

import kotlinx.collections.immutable.implementations.immutableList.persistentVectorOf
import kotlinx.collections.immutable.implementations.immutableMap.persistentHashMapOf

//@Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
//inline fun <T> @kotlin.internal.Exact ImmutableCollection<T>.mutate(mutator: (MutableCollection<T>) -> Unit): ImmutableCollection<T> = builder().apply(mutator).build()
// it or this?
inline fun <T> ImmutableSet<T>.mutate(mutator: (MutableSet<T>) -> Unit): ImmutableSet<T> = builder().apply(mutator).build()
inline fun <T> ImmutableList<T>.mutate(mutator: (MutableList<T>) -> Unit): ImmutableList<T> = builder().apply(mutator).build()

inline fun <K, V> ImmutableMap<K, V>.mutate(mutator: (MutableMap<K, V>) -> Unit): ImmutableMap<K, V> = builder().apply(mutator).build()


inline operator fun <E> ImmutableCollection<E>.plus(element: E): ImmutableCollection<E> = add(element)
inline operator fun <E> ImmutableCollection<E>.minus(element: E): ImmutableCollection<E> = remove(element)


operator fun <E> ImmutableCollection<E>.plus(elements: Iterable<E>): ImmutableCollection<E>
        = if (elements is Collection) addAll(elements) else builder().also { it.addAll(elements) }.build()
operator fun <E> ImmutableCollection<E>.plus(elements: Array<out E>): ImmutableCollection<E>
        = builder().also { it.addAll(elements) }.build()
operator fun <E> ImmutableCollection<E>.plus(elements: Sequence<E>): ImmutableCollection<E>
        = builder().also { it.addAll(elements) }.build()


operator fun <E> ImmutableCollection<E>.minus(elements: Iterable<E>): ImmutableCollection<E>
        = if (elements is Collection) removeAll(elements) else builder().also { it.removeAll(elements) }.build()
operator fun <E> ImmutableCollection<E>.minus(elements: Array<out E>): ImmutableCollection<E>
        = builder().also { it.removeAll(elements) }.build()
operator fun <E> ImmutableCollection<E>.minus(elements: Sequence<E>): ImmutableCollection<E>
        =  builder().also { it.removeAll(elements) }.build()


inline operator fun <E> ImmutableList<E>.plus(element: E): ImmutableList<E> = add(element)
inline operator fun <E> ImmutableList<E>.minus(element: E): ImmutableList<E> = remove(element)


operator fun <E> ImmutableList<E>.plus(elements: Iterable<E>): ImmutableList<E>
        = if (elements is Collection) addAll(elements) else mutate { it.addAll(elements) }
operator fun <E> ImmutableList<E>.plus(elements: Array<out E>): ImmutableList<E>
        = mutate { it.addAll(elements) }
operator fun <E> ImmutableList<E>.plus(elements: Sequence<E>): ImmutableList<E>
        = mutate { it.addAll(elements) }


operator fun <E> ImmutableList<E>.minus(elements: Iterable<E>): ImmutableList<E>
        = if (elements is Collection) removeAll(elements) else mutate { it.removeAll(elements) }
operator fun <E> ImmutableList<E>.minus(elements: Array<out E>): ImmutableList<E>
        = mutate { it.removeAll(elements) }
operator fun <E> ImmutableList<E>.minus(elements: Sequence<E>): ImmutableList<E>
        = mutate { it.removeAll(elements) }


inline operator fun <E> ImmutableSet<E>.plus(element: E): ImmutableSet<E> = add(element)
inline operator fun <E> ImmutableSet<E>.minus(element: E): ImmutableSet<E> = remove(element)

operator fun <E> ImmutableSet<E>.plus(elements: Iterable<E>): ImmutableSet<E>
        = if (elements is Collection) addAll(elements) else mutate { it.addAll(elements) }
operator fun <E> ImmutableSet<E>.plus(elements: Array<out E>): ImmutableSet<E>
        = mutate { it.addAll(elements) }
operator fun <E> ImmutableSet<E>.plus(elements: Sequence<E>): ImmutableSet<E>
        = mutate { it.addAll(elements) }


operator fun <E> ImmutableSet<E>.minus(elements: Iterable<E>): ImmutableSet<E>
        = if (elements is Collection) removeAll(elements) else mutate { it.removeAll(elements) }
operator fun <E> ImmutableSet<E>.minus(elements: Array<out E>): ImmutableSet<E>
        = mutate { it.removeAll(elements) }
operator fun <E> ImmutableSet<E>.minus(elements: Sequence<E>): ImmutableSet<E>
        = mutate { it.removeAll(elements) }


inline operator fun <K, V> ImmutableMap<out K, V>.plus(pair: Pair<K, V>): ImmutableMap<K, V>
        = (this as ImmutableMap<K, V>).put(pair.first, pair.second)
inline operator fun <K, V> ImmutableMap<out K, V>.plus(pairs: Iterable<Pair<K, V>>): ImmutableMap<K, V> = putAll(pairs)
inline operator fun <K, V> ImmutableMap<out K, V>.plus(pairs: Array<out Pair<K, V>>): ImmutableMap<K, V> = putAll(pairs)
inline operator fun <K, V> ImmutableMap<out K, V>.plus(pairs: Sequence<Pair<K, V>>): ImmutableMap<K, V> = putAll(pairs)
inline operator fun <K, V> ImmutableMap<out K, V>.plus(map: Map<out K, V>): ImmutableMap<K, V>
        = (this as ImmutableMap<K, V>).putAll(map)

public fun <K, V> ImmutableMap<out K, V>.putAll(pairs: Iterable<Pair<K, V>>): ImmutableMap<K, V>
        = (this as ImmutableMap<K, V>).mutate { it.putAll(pairs) }

public fun <K, V> ImmutableMap<out K, V>.putAll(pairs: Array<out Pair<K, V>>): ImmutableMap<K, V>
        = (this as ImmutableMap<K, V>).mutate { it.putAll(pairs) }

public fun <K, V> ImmutableMap<out K, V>.putAll(pairs: Sequence<Pair<K, V>>): ImmutableMap<K, V>
        = (this as ImmutableMap<K, V>).mutate { it.putAll(pairs) }


public operator fun <K, V> ImmutableMap<out K, V>.minus(key: K): ImmutableMap<K, V>
        = (this as ImmutableMap<K, V>).remove(key)

public operator fun <K, V> ImmutableMap<out K, V>.minus(keys: Iterable<K>): ImmutableMap<K, V>
        = (this as ImmutableMap<K, V>).mutate { it.minusAssign(keys) }

public operator fun <K, V> ImmutableMap<out K, V>.minus(keys: Array<out K>): ImmutableMap<K, V>
        = (this as ImmutableMap<K, V>).mutate { it.minusAssign(keys) }

public operator fun <K, V> ImmutableMap<out K, V>.minus(keys: Sequence<K>): ImmutableMap<K, V>
        = (this as ImmutableMap<K, V>).mutate { it.minusAssign(keys) }


fun <E> immutableListOf(vararg elements: E): ImmutableList<E> = persistentVectorOf<E>().addAll(elements.asList())
fun <E> immutableListOf(): ImmutableList<E> = persistentVectorOf()

fun <E> immutableSetOf(vararg elements: E): ImmutableSet<E> = ImmutableOrderedSet.emptyOf<E>().addAll(elements.asList())
fun <E> immutableSetOf(): ImmutableSet<E> = ImmutableOrderedSet.emptyOf<E>()

fun <E> immutableHashSetOf(vararg elements: E): ImmutableSet<E> = ImmutableHashSet.emptyOf<E>().addAll(elements.asList())

fun <K, V> immutableMapOf(vararg pairs: Pair<K, V>): ImmutableMap<K, V> = ImmutableOrderedMap.emptyOf<K,V>().mutate { it += pairs }
fun <K, V> immutableHashMapOf(vararg pairs: Pair<K, V>): ImmutableMap<K, V> = persistentHashMapOf<K,V>().mutate { it += pairs }

fun <T> Iterable<T>.toImmutableList(): ImmutableList<T> =
        this as? ImmutableList
        ?: (this as? ImmutableList.Builder)?.build()
        ?: immutableListOf<T>() + this


// fun <T> Array<T>.toImmutableList(): ImmutableList<T> = immutableListOf<T>() + this.asList()

fun CharSequence.toImmutableList(): ImmutableList<Char> =
        immutableListOf<Char>().mutate { this.toCollection(it) }

fun <T> Iterable<T>.toImmutableSet(): ImmutableSet<T> =
        this as? ImmutableSet<T>
        ?: (this as? ImmutableSet.Builder)?.build()
        ?: immutableSetOf<T>() + this

fun <T> Set<T>.toImmutableHashSet(): ImmutableSet<T>
    = this as? ImmutableHashSet
        ?: (this as? ImmutableHashSet.Builder)?.build()
        ?: ImmutableHashSet.emptyOf<T>() + this


fun <K, V> Map<K, V>.toImmutableMap(): ImmutableMap<K, V>
    = this as? ImmutableMap
        ?: (this as? ImmutableMap.Builder)?.build()
        ?: ImmutableOrderedMap.emptyOf<K, V>().putAll(this)

fun <K, V> Map<K, V>.toImmutableHashMap(): ImmutableMap<K, V>
    = this as? ImmutableMap
        ?: (this as? ImmutableMap.Builder)?.build()
        ?: immutableHashMapOf<K, V>().putAll(this)
