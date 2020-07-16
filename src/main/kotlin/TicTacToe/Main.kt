
package TicTacToe

var player1inputs = mutableListOf<String>()
var player2inputs = mutableListOf<String>()
var finished: Boolean = false
var winner: Player? = null
val winlists: List<List<String>> = listOf(listOf("a1", "a2", "a3"), listOf("b1", "b2", "b3"), listOf("c1", "c2", "c3"), listOf("a1", "b1", "c1"), listOf("a2", "b2", "b3"), listOf("a3", "b3", "c3"), listOf("a1", "b2", "c3"), listOf("a3", "b2", "c1"))

class Main {

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
        val playground: Playground = Playground()
        while(check_win(player1, player2) == -1) {
            playground.draw_current_playground()
            userInput(player1)
            add_elements(player1, playground)
            playground.draw_current_playground()
            if(check_win(player1, player2) != -1){
                break
            }
            userInput(player2)
            add_elements(player2, playground)
        }
        println("${winner?.name} won the game!")
    }

fun userInput(player: Player){
    println("Eingabe ${player.name}:")
    if(player.symbol == "X") {
        player1inputs.add(readLine() ?: "no valid input")
    }else if(player.symbol == "O"){
        player2inputs.add(readLine()?: "no valid input")
    }else{
        println("FAILURE")
    }

    //println(player1inputs)
}

fun convert_first_coordinate(input: String): Int {
    val tmp_array = input.toCharArray()
    return when {
        tmp_array[0] == 'a' -> 0
        tmp_array[0] == 'b' -> 1
        tmp_array[0] == 'c' -> 2
        else -> -1
    }
}
fun convert_second_coordinate(input: String): Int {
    val tmp_array = input.toCharArray()
    return when {
        tmp_array[1] == '1' -> 0
        tmp_array[1] == '2' -> 1
        tmp_array[1] == '3' -> 2
        else -> -1
    }
}

fun add_elements(player: Player, playground: Playground){
    var list1: List<Int> = listOf(0)
    var list2: List<Int> = listOf(0)
    if(player.symbol == "X"){
        println("YAM")
        list1 = player1inputs.map{element -> convert_first_coordinate(element)}
        list2 = player1inputs.map{ele -> convert_second_coordinate(ele)}
    }else if(player.symbol == "O"){
        println("YUUM")
        list1 = player2inputs.map{element -> convert_first_coordinate(element)}
        list2 = player2inputs.map{ele -> convert_second_coordinate(ele)}
    }else{
        println("FAILURE")
    }
    val w = list1.size -1
    for(i in  0..w){
        playground.sete(list1[i], list2[i], player.symbol)
    }
}

fun check_win(player1: Player, player2: Player): Int{
    for(winlist in winlists) {
        if (player1inputs.containsAll(winlist)){
            finished = true
            winner = player1
            return 1
        }
    }
    for(winlist in winlists) {
        if (player2inputs.containsAll(winlist)){
            finished = true
            winner = player2
            return 2
        }
    }
    if(player1inputs.size == 5 && player2inputs.size == 4){
        winner = Player("Nobody", "#")
        return 0
    }else{
        return -1
    }
}