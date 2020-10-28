package datacloud.scala.tpfonctionnel

object FunctionParty {
  def curryfie[A,B,C](f: (A, B) => C): A => B => C = {
    def curry = (a: A) => (b: B) => f(a,b);
    return curry;
  }
  
  def decurryfie[A,B,C](f: A => B => C):(A, B) => C = {
    def curry = (a: A, b: B) => f(a)(b);
    return curry;
  }
  
  def compose[A,B,C](f: B => C, g: A => B): A => C = {
    def curry = (a: A) => f(g(a));
    return curry;
  }
  
  def axplusb(a:Int,b:Int):Int => Int = {
    def add = (a: Int, b: Int) => a+b;
    def mult = (a: Int, b: Int) => a*b;
    
    var f1 = (x: Int) => curryfie(add)(x);
    var f2 = (x: Int) => curryfie(mult)(x);
    return (x: Int) => compose(f1(b), f2(a))(x);
  }
}
