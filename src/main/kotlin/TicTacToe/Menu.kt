package TicTacToe


//val player3: Player? = null

class Menu(val player1: Player, val player2: Player, var score1: Int, var score2: Int, val curplayer: Player) {

    fun showmenu(){
        println("1. Back")
        println("2. Score")
        println("3. New Game")
        println("4. Exit")
        println("5. Info")
        val inp: String? = readLine()
        inputhandler(inp)
    }

    fun inputhandler(input: String?){
        when {
            input == "1" -> userInput(curplayer, this)
            input == "2" -> {
                showscore(score1, score2, player1, player2)
                showmenu()
            }
            input == "3" -> newgame()
            input == "4" -> endgame()
            input == "5" -> {
                showinfo()
                showmenu()
            }
            else -> {
                println("Try Again!")
                showmenu()
            }
        }
    }

    fun showscore(score1: Int, score2: Int, player1: Player, player2: Player){
        println("${player1.name} $score1 - $score2 ${player2.name} ")
    }

    fun showinfo(){
        println("Pretty Good Game by die_giesskanne")
    }
}