
package TicTacToe

var player1inputs = mutableListOf<String>()

class Main {
    /*val player1: Player
        get() {
            return Player("noname", "X")
        }
    val player2: Player
        get() {
            return Player()
        }*/
    var player1inputs = mutableListOf<String>()
}

    fun main() {
        println("Wilkommen bei TictacToe")
        println("Wie heisst Spieler1: ")
        val name1 = readLine()
        val player1: Player
        val player2: Player
        player1 = Player(name1?: "noname", "X")
        println("Spieler 1 heisst " + player1.name + " und hat das Symbol " + player1.symbol)
        val name2 = readLine()
        player2 = Player(name2?: "noname2", "O")
        println("Spieler 2 heisst " + player2.name + " und hat das Symbol " + player2.symbol)
        //call userinput until state = finished

        userInput(player1, player2)
    }

fun userInput(player1: Player, player2: Player){
    println("Eingabe ${player1.name}:")
    player1inputs.add(readLine()?: "no valid input");
    println(player1inputs)
}


