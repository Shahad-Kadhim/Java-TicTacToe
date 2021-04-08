class Cell {
    //This is one cell of the game grid
    var contents = " "
    private set
    fun placeMark() {
        contents = if (GameSetting.count % 2 == 0)  "X"  else  "O"
    }
}