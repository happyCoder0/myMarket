package com.happycoder.mymarket

 /**
  * Defines a behavior of an object inside which a search can be performed without accessing its
  * members directly
  * @param <T> describes a type of criteria
  * @param criteria represents a search key
  * @author happy_coder
 */

interface Searchable<T> {
     fun search(criteria: T)
}