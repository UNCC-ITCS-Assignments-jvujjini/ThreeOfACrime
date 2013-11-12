import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Set
import scala.util.control._

class Player(name:String) {
  
  var playerName: String = name
  var flag: Boolean = false
  
  def getName : String = { return playerName }
  
  def setFlag(bool: Boolean) = { flag = bool }
  
  def getFlag : Boolean = { return flag }  
  
}


object ThreeOfACrime{
  
  var perps = new ArrayBuffer[String](3)
  var players = new ArrayBuffer[Player]()
  var allCriminals = new ArrayBuffer[String]()
  var threeRandomCriminals: Set[String] = Set()
  
  
  
  var answer: String = "no" 
  var winner: Boolean = false
  var guessedCriminals = new ArrayBuffer[String](3)
  
  def initializeGame( a:ArrayBuffer[String] ) = {
      
    allCriminals = Random.shuffle(a)
    var player:String = ""
 
	var count :Int = readLine("Enter number of Players:").toInt
	 
	for( i <- 1 to count ){ 
	  player = readLine("Enter name of Player " + i + " : ")
	  players.append(new Player(player))
	  println("Hi " + player + ", Welcome to Three of A Crime!")
	}
	 
	for( i <- 0 to 2) {
	  perps.append(allCriminals(i))
	}
	
	println(" The Perpetrators have been set ")
	
	while( !winner && players.size != 0) {
	  startGame
	}
    
  }
  
  def startGame = {
    
    var perpsCount = 0
    
    val loop = new Breaks;
    
    allCriminals = Random.shuffle(allCriminals)
    perps = Random.shuffle(perps)
    threeRandomCriminals.clear()
    threeRandomCriminals.add(perps(0))
    
    while(threeRandomCriminals.size < 3) {
      allCriminals = Random.shuffle(allCriminals)
      threeRandomCriminals.add(allCriminals(0))
    }
    
    threeRandomCriminals = Random.shuffle(threeRandomCriminals)
    
    println("The Three Random Criminals are:" + threeRandomCriminals.mkString(" "))
    
    for(i <- 0 to 2) {
      if(threeRandomCriminals contains perps(i)) { perpsCount += 1 }
      if(perpsCount == 3) { 
        threeRandomCriminals.clear()
        threeRandomCriminals.add(perps(0))
        
        while(threeRandomCriminals.size < 3) {
        	allCriminals = Random.shuffle(allCriminals)
        	threeRandomCriminals.add(allCriminals(0))
        }
        
      }
    }
    
    println("\n" + perpsCount + " out of these are perpetrators")
    for(i <- 0 to players.size-1) {  
      if(!players(i).getFlag){
        answer = readLine(players(i).getName + " it is your turn! Do you want to guess the perpetrators (Yes/No)?: ")
      }
      if(answer.toLowerCase() == "yes"){
       players(i).setFlag(true)
       guessedCriminals = makeGuess
       loop.breakable{
	       for(j <- 0 to 2) {
	         if(perps(j).toLowerCase() != guessedCriminals(j).toLowerCase()) { 
	           println(players(i).getName +" you loose!")
	           loop.break
	           players.remove(i)
	         } else {
	           println(players(i).getName + "you win!")
	           winner = true
	         }
	         
	         if(players.size == 0) { 
	           println("You both loose!")
	           println("Perps are: " +perps.mkString(" ")) 
	           }
	         
	       }
       }
      }
    }
  }
  
  def makeGuess : ArrayBuffer[String]  = { 
    
    guessedCriminals.clear()
    
    for (i <- 0 to 2) {    
      println("Guess #" + (i+1) + " :")
      guessedCriminals.append(readLine().toLowerCase())
    }
    
    return guessedCriminals
  }
  
  
}


object Main {
  
  def main(args: Array[String]) {
    val criminals = ArrayBuffer("Panther", "Cardinal", "Falcon", "Patriot", "Saint", "Packer", "Lion")
    println(ThreeOfACrime.initializeGame(criminals))
  }

}