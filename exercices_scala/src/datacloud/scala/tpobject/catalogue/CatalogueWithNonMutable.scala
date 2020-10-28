package datacloud.scala.tpobject.catalogue

class CatalogueWithNonMutable extends Catalogue {
  protected var map: Map[String, Double] = Map();
   
   override def getPrice(produit: String): Double = {
     if (!map.contains(produit)) return -1.0
     return map(produit);
   }
   
   override def removeProduct(produit: String): Unit = {
     map -= produit;
   }
   
   override def selectProducts(min: Double, max: Double): List[String] = {
     var res = List[String]();
     for ((k,v) <- map)
       if (v <= max && v >= min) res = res.+:(k);
     return res.toList;
   }   
   
   override def storeProduct(produit: String, prix: Double): Unit = {
      if (map.contains(produit)) {
        map -= produit;
      }
      map += (produit -> prix);
   }
}