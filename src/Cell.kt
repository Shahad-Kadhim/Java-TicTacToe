class Cell {
    //This is one cell of the game grid
    var contents = " "
    fun placeMark() {
        contents = if (TicTacToe.count % 2 == 0)  "X"  else  "O"
    }
}