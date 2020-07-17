
package TicTacToe

import kotlin.system.exitProcess

var scorep1: Int = 0
var scorep2: Int = 0
var namef: String = ""
var names: String = ""
var player1inputs: MutableList<String> = mutableListOf<String>()
var player2inputs: MutableList<String> = mutableListOf<String>()
var finished: Boolean = false
var winner: Player? = null
val winlists: List<List<String>> = listOf(listOf("a1", "a2", "a3"), listOf("b1", "b2", "b3"), listOf("c1", "c2", "c3"), listOf("a1", "b1", "c1"), listOf("a2", "b2", "b2"), listOf("a3", "b3", "c3"), listOf("a1", "b2", "c3"), listOf("a3", "b2", "c1"))
val validcombinations: List<String> = listOf("a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3", "m")

class Main {

}

    fun main() {
        var player1: Player = Player(namef, "X")
        var player2: Player = Player(names, "O")
        if(scorep1 == 0 && scorep2 == 0) {
            println("Wilkommen bei TictacToe")
            println("Wie heisst Spieler1: ")
            val name1 = readLine()
            player1 = Player(name1 ?: "noname", "X")
            if (name1 != null) {
                namef = name1
            }
            println("Spieler 1 heisst " + player1.name + " und hat das Symbol " + player1.symbol)
            println("Wie heisst Spieler2: ")
            val name2 = readLine()
            player2 = Player(name2 ?: "noname2", "O")
            if (name2 != null) {
                names = name2
            }
            println("Spieler 2 heisst " + player2.name + " und hat das Symbol " + player2.symbol)
        }

        val playground: Playground = Playground()
        var currentplayer: Player
        var menu: Menu
        while(check_win(player1, player2) == -1) {
            currentplayer = player1
            menu = Menu(player1, player2, scorep1, scorep2, currentplayer)
            playground.draw_current_playground()
            userInput(player1, menu)
            add_elements(player1, playground)
            playground.draw_current_playground()
            if(check_win(player1, player2) != -1){
                break
            }
            currentplayer = player2
            menu = Menu(player1, player2, scorep1, scorep2, currentplayer)
            userInput(player2, menu)
            add_elements(player2, playground)
        }
        println("${winner?.name} won the game!")
        when {
            winner == player1 -> scorep1++
            winner == player2 -> scorep2++
        }
        println("The new Score is ${player1.name} $scorep1 - ${player2.name} $scorep2")
        newgame()
    }

fun userInput(player: Player, menu: Menu){
    println("Eingabe ${player.name}:")
    val inp: String? = readLine()
    if(!validcombinations.contains(inp) || player1inputs.contains(inp) || player2inputs.contains(inp)){
        println("no valid input")
        userInput(player, menu)
    }else if(inp == "m"){
        menu.showmenu()
    } else {
        if (player.symbol == "X") {
            player1inputs.add(inp ?: "no input")
        } else if (player.symbol == "O") {
            player2inputs.add(inp ?: "no input")
        } else {
            println("FAILURE")
        }
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
        list1 = player1inputs.map{element -> convert_first_coordinate(element)}
        list2 = player1inputs.map{ele -> convert_second_coordinate(ele)}
    }else if(player.symbol == "O"){
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

fun endgame(){
    exitProcess(0)
}

fun newgame(){
    player1inputs.clear()
    player2inputs.clear()
    finished = false
    winner = null
    playground = Array(3) {Array(3) {" "} }
    main()
}
