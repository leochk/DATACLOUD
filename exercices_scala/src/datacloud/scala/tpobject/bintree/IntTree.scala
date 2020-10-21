package datacloud.scala.tpobject.bintree

sealed abstract class IntTree
case object EmptyIntTree extends IntTree
case class NodeInt(elem : Int, left : IntTree, right : IntTree) extends IntTree

object BinTrees {
  
  def contains(intTree: IntTree, i: Int): Boolean = {
    intTree match {
      case EmptyIntTree => false
      case node: NodeInt if node.elem == i => true
      case node: NodeInt => contains(node.left, i) || contains(node.right, i)
    }
  }
  
  def size(intTree: IntTree): Int = {
    intTree match {
      case EmptyIntTree => 0
      case node: NodeInt => 1 + size(node.left) + size(node.right)
    }  
  }
  
  def insert(intTree: IntTree, i: Int): IntTree = {
    intTree match {
      case EmptyIntTree 
        => NodeInt(i, EmptyIntTree, EmptyIntTree)
      case node: NodeInt if size(node.left) < size(node.right) 
        => NodeInt(node.elem, insert(node.left, i), node.right)
      case node: NodeInt
        => NodeInt(node.elem, node.left, insert(node.right, i))
    }
  }
  
  def contains[A](tree: Tree[A], i: A): Boolean = {
    tree match {
      case EmptyTree => false
      case node: Node[A] if node.elem == i => true
      case node: Node[A] => contains(node.left, i) || contains(node.right, i)
    }
  }
  
  def size[A](tree: Tree[A]): Int = {
    tree match {
      case EmptyTree => 0
      case node: Node[A] => 1 + size(node.left) + size(node.right)
    }  
  }
  
  def insert[A](tree: Tree[A], i: A): Tree[A] = {
    tree match {
      case EmptyTree 
        => Node[A](i, EmptyTree, EmptyTree)
      case node: Node[A] if size(node.left) < size(node.right) 
        => Node[A](node.elem, insert(node.left, i), node.right)
      case node: Node[A]
        => Node[A](node.elem, node.left, insert(node.right, i))
    }
  }
  
}