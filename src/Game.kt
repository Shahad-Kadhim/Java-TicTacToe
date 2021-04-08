
class Game(var gridSize: Int) {
    //This is the Game class.  It hold the current state of the game
    //with the help of the Cell class.
    var finished = false
    var draw = false
    private val grid = arrayOfNulls<Array<Cell?>>(gridSize)
    init {
        grid.forEachIndexed { index, _ ->
            grid[index] = arrayOfNulls(gridSize)
            grid[index]!!.forEachIndexed { col, _ -> grid[index]?.set(col, Cell()) }
        }
    }
    //checks to see if a win condition has been met and
    //outputs the current game map to the console 
    fun output() {
        checkForTicTacToe()
        drawMap()
    }

    //places an X or an O in a cell ont he game map
    fun setCell(row: Int,col:Int): Boolean {
        return if (grid[row]!![col]!!.contents.isBlank()) {
            grid[row]!![col]!!.placeMark()
            true
        } else {
            false
        }
    }

    //checks to see if a win condition has been met
    private fun checkForTicTacToe() {

        //if every cell is filled, end the game
        if(grid.all { it!!.all{cell-> cell!!.contents.isNotBlank() } }){
            finished=true
            draw=true
        }
        //if a row has all the same content and isn't empty
        //then the game is over
        if(grid.any { it!!.all {cell -> cell!!.contents=="X" } }||grid.any { it!!.all { cell ->cell!!.contents=="O" } })
        {
            finished=true
            draw=false
        }
        //if a column has all the same content and isn't empty
        //then the game is over
        grid.forEachIndexed { row, chars ->
            val m= mutableListOf<String>()
            chars!!.forEachIndexed { col, _ -> m.add(grid[col]!![row]!!.contents)}
            if(m.all { it=="X" }||m.all { it=="O" })
            {
                finished=true
                draw=false
            }
        }

        //if a diagonal has all the same content and isn't empty
        //then the game is over
        if(grid[0]!![0]!!.contents.isNotBlank()){
            if(mutableListOf<String>().apply { grid.forEachIndexed { index, _ -> add(grid[index]!![index]!!.contents) } }.all { it==grid[0]!![0]!!.contents })
            {
                finished=true
                draw=false
            }
        }
        if(grid[0]!![gridSize-1]!!.contents.isNotBlank() ){
            if(mutableListOf<String>().apply { grid.forEachIndexed { index, _ -> add(grid[index]!![gridSize-1-index]!!.contents) } }.all { it==grid[0]!![gridSize-1]!!.contents })
            {
                finished=true
                draw=false
            }
        }
    }

    //draws the current game state in perfect proportion
    //
    private fun drawMap() {
        for (i in 1..gridSize) { print("\t\t${i}") }
        println()
        for (i in 0 until gridSize) {
            print("${(i + 65).toChar()}\t")
            grid[i]!!.forEach { print("|\t${it!!.contents}\t") }
            println("|\n\t${"_".repeat(gridSize * (8))}")
        }
    }
}