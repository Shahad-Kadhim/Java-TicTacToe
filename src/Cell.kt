class Cell {
    //This is one cell of the game grid
    var contents = " "
    var empty = true
    fun placeMark() {
        if (TicTacToe.count % 2 == 0) {
            contents = "X"
        } else {
            contents = "O"
        }
        empty = false
    }
}