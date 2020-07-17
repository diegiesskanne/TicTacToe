package TicTacToe

var playground = Array(3) {Array(3) {" "} }

class Playground() {

    fun draw_current_playground(){
        for(i in 0..4) {
            for (j in 1..17) {
                val k: Int = j / 6
                val l: Int = i / 2
                if(i % 2 == 0) {
                    val p: Int = j + 3
                    when {
                        p % 6 == 0 -> print(playground[l][k])
                        j % 6 == 0 -> print("|")
                        else -> print(" ")
                    }
                }else if(i % 2 != 0) {
                    print('=')
                }
                if(j == 17) {
                    print("\n")
                }
            }
        }
    }

     fun sete(ind: Int, inde: Int, sym: String){
        playground[ind][inde] = sym
    }
}