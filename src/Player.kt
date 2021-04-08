import java.lang.InterruptedException

class Player  ( private val type: String) {
    //constructor.  requires string to set player type
      //player makes moves and can be human or AI
     // whether the player is human or AI

    private var turn= false //whether or not it's the player's turn
    //player "goes" while it's their turn
    fun go() {
        turn = true
        var row :Int?=null
        var col :Int?
        // if AI, do computery things
        if (type === "AI") {

            //let user know that AI is going
            println("\tThe computer will now make a move..")
            delay( GameSetting.game!!.gridSize) //take a second to go to make it appear as if computer is thinking
            while (turn) {
                //AI selects a random empty cell and places corrosponding mark
                row = (0 until GameSetting.game!!.gridSize).random()
                col =(0 until GameSetting.game!!.gridSize).random()
                move(row,col, GameSetting.game!!)
            }
        } else {
            //while it's the player's turn...
            while (turn) {
                //if human, do human stuff
                println("\tPlease place an X on the grid.  You can\n\tdo this by typing 1A, 1B, 1C, 2A, etc.: ")
                //while it's the player's turn...
                while (turn) {
                    readLine()!!.toUpperCase().takeIf {  it.substring(0,it.lastIndex).toIntOrNull()!=null&&it.last() in 'A'..(65+GameSetting.game!!.gridSize-1).toChar()}?.takeIf { it.substring(0,it.lastIndex).toInt() in 1..GameSetting.game!!.gridSize }?.
                    let {
                        row=it.last().toInt()-65
                        col=it.substring(0,it.lastIndex).toInt()-1
                        //if valid input, and cell isn't taken already,
                        //place mark in selected cell and end turn
                        move(row!!, col!!,GameSetting.game!!)
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
        //encapsulated code for AI delay behavior
        private fun delay( gameSize: Int) {
            try {
                Thread.sleep((3000 / (gameSize * gameSize)).toLong())
            } catch (ex: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }

    }
}