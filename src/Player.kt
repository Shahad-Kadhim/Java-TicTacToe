import java.lang.InterruptedException

class Player     //constructor.  requires string to set player type
    (  //player makes moves and can be human or AI
    private val type // whether the player is human or AI
    : String
) {
    private var index = 0
    private var column = 0
    private var row = 0
    private var turn //whether or not it's the player's turn
            = false

    //player "goes" while it's their turn
    fun go() {
        turn = true
        var row :Int?=null
        var col :Int?
        // if AI, do computery things
        if (type === "AI") {

            //let user know that AI is going
            println("\tThe computer will now make a move..")
            delay(1000, TicTacToe.game!!.gridSize) //take a second to go to make it appear as if computer is thinking
            while (turn) {
                //AI selects a random empty cell and places corrosponding mark
                row = (0 until TicTacToe.game!!.gridSize).random()
                col =(0 until TicTacToe.game!!.gridSize).random()
                move(row,col, TicTacToe.game!!)
            }
        } else {
            //while it's the player's turn...
            while (turn) {
                //if human, do human stuff
                println("\tPlease place an X on the grid.  You can\n\tdo this by typing 1A, 1B, 1C, 2A, etc.: ")
                //while it's the player's turn...
                while (turn) {
                    readLine()!!.toUpperCase().takeIf {  it.substring(0,it.lastIndex).toIntOrNull()!=null&&it.last() in 'A'..(65+TicTacToe.game!!.gridSize-1).toChar()}?.takeIf { it.substring(0,it.lastIndex).toInt() in 1..TicTacToe.game!!.gridSize }?.
                    let {
                        row=it.last().toInt()-65
                        col=it.substring(0,it.lastIndex).toInt()-1
                        //if valid input, and cell isn't taken already,
                        //place mark in selected cell and end turn
                        move(row!!, col!!,TicTacToe.game!!)
                        if(turn) println("That space is already in play!  Please choose another spot: ")
                    }
                    if(row==null) println("That's not valid input.  Please choose another spot: ")
                }
            }
         }
    }

    //player places mark
    private fun move(row: Int,col:Int, game: Game) {
        if (game.setCell(row, col)) {
            turn = false
        }
    }

    companion object {
        //encapsulated code for user input validation
        //it checks to make sure the input was two or three characters long,
        //and that it contained one or two digits, followed by one lower
        //case or upper case letter
        private fun valid_input(user_input: String): Boolean {
            var output = false
            if (user_input.length == 2) {
                output = user_input.substring(0, 1).matches(Regex("[0-9]")) && user_input.substring(1, 2).matches(Regex("[a-zA-Z]"))
            } else if (user_input.length == 3) {
                output =
                    user_input.substring(0, 2).matches(Regex("[1-2][0-9]")) && user_input.substring(2, 3).matches(Regex("[a-zA-Z]"))
                if (user_input.substring(0, 2).toInt() > TicTacToe.game!!.gridSize) {
                    output = false
                }
            }
            return output
        }

        //encapsulated code for AI delay behavior
        private fun delay(amount: Int, gameSize: Int) {
            try {
                Thread.sleep((amount * 3 / (gameSize * gameSize)).toLong())
            } catch (ex: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }

        //converts the letter input for row/column selection into a usable number
        private fun letterToNumber(str: String): Int {
            return "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".indexOf(str) % 26 + 1
        }
    }
}