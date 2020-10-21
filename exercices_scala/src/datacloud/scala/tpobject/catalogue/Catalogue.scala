package datacloud.scala.tpobject.catalogue

trait Catalogue {
   def getPrice(produit: String): Double
   def removeProduct(produit: String): Unit
   def selectProducts(min: Double, max: Double): Traversable[String]
   def storeProduct(produit: String, prix: Double): Unit
}