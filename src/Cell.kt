class Cell {
    //This is one cell of the game grid
    var contents = " "
    fun placeMark() {
        if (TicTacToe.count % 2 == 0) {
            contents = "X"
        } else {
            contents = "O"
        }
    }
}