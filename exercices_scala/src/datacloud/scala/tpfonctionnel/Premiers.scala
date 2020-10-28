package datacloud.scala.tpfonctionnel

import scala.collection.mutable.ListBuffer

object Premiers {
  
  def premiers(n: Int): List[Int] = {
    var tmp = List.range(2, n);
    for (i <- 2 to n) {
      var func = (a: Int) => a % i != 0 || a == i;
      tmp = tmp.filter(func);
    }
    
    return tmp;
  }
  
  def premiersWithRec(n: Int): List[Int] = {
    def func(l : List[Int]) : List[Int] = {
      val first = l(0);
      if ((first*first) > l(l.size-1)) {
        return l;
      } else {
        var filterFunc = (a: Int) => a % first != 0;
        return List(first) ++ func(l.filter(filterFunc));
      }
    }
    var tmp = List.range(2, n);
    return func(tmp);
  }
}