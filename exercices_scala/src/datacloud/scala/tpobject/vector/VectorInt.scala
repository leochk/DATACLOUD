package datacloud.scala.tpobject.vector

class VectorInt(val elements: Array[Int]) {

  def length: Int = {
    return elements.size;
  }

  def get(i: Int): Int = {
    return elements(i);
  }

  def tostring: String = {
    return "( " + elements.mkString(" ") + " )";
  }

  override def equals(a: Any): Boolean = {
    a match {
      case tmp: VectorInt =>
        val l = length
        l == tmp.length && {
          var i = 0
          while (i < l && elements(i) == tmp.elements(i)) i += 1
          i == l
        }
      case _ => false;
    }
  }

  def +(other: VectorInt): VectorInt = {
    var l = length
    var tmp = elements.clone;
    var i = 0;

    if (l > other.length) {
      l = other.length;
      tmp = other.elements;
    }

    while (i < l) {
      tmp(i) = elements(i) + other.elements(i);
      i = i + 1;
    }

    return new VectorInt(tmp);
  }

  def *(v: Int): VectorInt = {
    var l = length
    var tmp = elements.clone;
    var i = 0;

    while (i < l) {
      tmp(i) = elements(i) * v;
      i = i + 1;
    }

    return new VectorInt(tmp);
  }

  def prodD(other: VectorInt): Array[VectorInt] = {
    if (length != other.length) return null;

    var res = Array[VectorInt]();
    var i = 0;
    var j = 0;

    while (i < length) {
      var vec = Array[Int]();
      j = 0;
      while (j < length) {
        vec = vec :+ (elements(i) * other.elements(j));      
        j = j + 1;
      }
      res = res :+ new VectorInt(vec);
      i = i + 1;
    }

    return res;
  }
}

object VectorInt {
  implicit def arrayIntToVectorInt(value: Array[Int]): VectorInt = new VectorInt(value);
}