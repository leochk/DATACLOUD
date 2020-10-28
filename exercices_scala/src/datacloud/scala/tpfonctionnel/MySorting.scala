package datacloud.scala.tpfonctionnel

object MySorting {
  
  def isSorted[A](l: Array[A], f:(A, A) => Boolean) : Boolean = {
    l.size match {
      case 0 | 1 => true;
      case _ => if (f(l(0), l(1))) isSorted(l.drop(1), f) else false;
    }
  }
  
  def ascending[T](a: T, b: T)(implicit o: Ordering[T]) : Boolean = {
    return o.compare(a, b) < 0;
  }
  
  def descending[T](a: T, b: T)(implicit o: Ordering[T]) : Boolean = {
    return o.compare(a, b) > 0;

  }
}