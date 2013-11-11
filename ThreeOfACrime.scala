import scala.util.Random
import scala.collection.mutable.ArrayBuffer

object ThreeOfACrime{
   def start( a:List[String]) : String= {
      
     var criminals = Random.shuffle(a)
     var buf = ArrayBuffer[String]()
     var player:String = ""
     var perps = new Array[String](3)
       
     var count :Int = readLine("Enter number of Players:").toInt
     
     for( i <- 1 to count ){
      
       player = readLine("Enter name of Player " + i + " : ")
       buf.append(player)
         
     }
     
     for( i <- 0 to perps.size-1) {
       perps(i) = criminals(i)
     }
     
     return perps.mkString(" ")
   
   }
}

object Main {
  def main(args: Array[String]) {
    
    val criminals = List("Panther", "Cardinal", "Falcon", "Patriot", "Saint", "Packer", "Lion")
    println(ThreeOfACrime.start(criminals))
   
  }
}