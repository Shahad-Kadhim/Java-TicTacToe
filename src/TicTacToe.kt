import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception


fun main() {
        //When program starts, user is met with a welcome message
        println("\n\tWelcome to this wonderful and lovely game of TicTacToe.")
        println("\n\tPlease select your Game mode.")
        println("\n\t    (1) Human vs. Computer")
        println("\n\t    (2) Computer vs. Computer")
        println("\n\tWhich mode would you like to play? (1/2): ")

    //Keep asking for an answer from the user until we get a 1 or a 2
    GameSetting.gameMode=checkInputValid(2)
    //gameMode() is defines below
    println("\n\tHow large of a grid would you like to use? ")
    println("\n\tPlease enter an integer between ${GameSetting.minimumGameSize} and ${GameSetting.maximumGameSize}: ")

    //validate user input for game size
    val gameSize = checkInputValid(GameSetting.maximumGameSize)

    //issue warning for game sizes larger than 15
    gameSize.takeIf { it>15 }?.let{
        println("\n\t!!WARNING!!\n\t!!WARNING!!  Games large than 15 will not display correctly if console width is restricted to 80 col (neither will this message)\n\t!!WARNING!!")
        readLine()
    }

    //Create a new Game instance
    GameSetting.game = Game(gameSize)

        //create an array of two players
        val players = arrayOfNulls<Player>(2).apply {
            //set players to AI or Human depending on game mode
            this[1] = Player("AI")
            this[0] = if (GameSetting.gameMode == 1) Player("Human") else Player("AI")
        }

        //Draw the blank board initially to show user which columns and rows to choose from
        println(GameSetting.game!!.output())

        //until the game is over, go back and forth between players in players array
        //output the game map to the screen after each move
    do {
        for (player in players) {
            player!!.go()
            GameSetting.count ++
            GameSetting.game!!.output()
            if (GameSetting.game!!.finished) {
                break
            }
        }
    }while ( !GameSetting.game!!.finished)


    //output an ending message to the game
        if (GameSetting.game!!.draw) {
            println("\n\tCat's game!")
        } else {

            //count variable from earlier is used to decide who went last and therefore won.
            if (GameSetting.count % 2 == 1) {
                println("\n\tX's win!")
            } else {
                println("\n\tO's win!")
            }
        }
    }


    //validates user input  game mode or game size
    private fun checkInputValid(maxValue :Int) :Int{
        val message=if (maxValue==2)"You must enter '1' or '2' for the game mode:" else "You must enter a number between 1 and 26:"
        do {
            readLine()?.takeIf { it.toIntOrNull() in 1..maxValue }?.let{return  it.toInt()}
            println("\n\t$message ")
        }while (true)
    }