import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception


fun main() {
        val gameSize: Int
        val minimumGameSize = 1
        val maximumGameSize = 26

        //When program starts, user is met with a welcome message
        println("\n\tWelcome to this wonderful and lovely game of TicTacToe.")
        println("\n\tPlease select your Game mode.")
        println("\n\t    (1) Human vs. Computer")
        println("\n\t    (2) Computer vs. Computer")
        println("\n\tWhich mode would you like to play? (1/2): ")
        GameSetting.user_input = readLine()

        //Keep asking for an answer from the user until we get a 1 or a 2
        gameMode(GameSetting.user_input) //gameMode() is defines below
        println("\n\tHow large of a grid would you like to use? ")
        println("\n\tPlease enter an integer between $minimumGameSize and $maximumGameSize: ")
        GameSetting.user_input = readLine()

        //validate user unput for game size
        GameSetting.valid_input = false
        while (!GameSetting.valid_input) {
            if (GameSetting.user_input!!.length > 0 && GameSetting.user_input!!.substring(0, 1)
                    .matches(Regex("[1-9]")) && minimumGameSize <= GameSetting.user_input!!.toInt() && GameSetting.user_input!!.toInt() <= maximumGameSize
            ) {
                GameSetting.valid_input = true
            } else {
                println("\n\tYou must enter a number between $minimumGameSize and $maximumGameSize: ")
                GameSetting.user_input = readLine()
            }
        }

        //issue warning for game sizes larger than 15
        if (GameSetting.user_input!!.toInt() > 15) {
            println("\n\t!!WARNING!!\n\t!!WARNING!!  Games large than 15 will not display correctly if console width is restricted to 80 col (neither will this message)\n\t!!WARNING!!")
            readLine()
        }
        gameSize = GameSetting.user_input!!.toInt()

        //Create a new Game instance
        GameSetting.game = Game(gameSize)

        //create an array of two players
        val players = arrayOfNulls<Player>(2)

        //set players to AI or Human depending on game mode
        if (GameSetting.gameMode == 1) {
            players[0] = Player("Human")
            players[1] = Player("AI")
        } else {
            players[0] = Player("AI")
            players[1] = Player("AI")
        }

        //Draw the blank board initially to show user which columns and rows to choose from
        println(GameSetting.game!!.output())

        //until the game is over, go back and forth between players in players array
        //output the game map to the screen after each move
        while (!GameSetting.game!!.finished) {
            for (player in players) {
                player!!.go()
                println(
                    """
    
    ${GameSetting.game!!.output()}
    """.trimIndent()
                )
                GameSetting.count += 1
                if (GameSetting.game!!.finished) {
                    break
                }
            }
        }

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


    //validates user input and sets the game mode
    private fun gameMode(user_input: String?) {
        var userInput = user_input
        GameSetting.valid_input = false
        while (!GameSetting.valid_input) {
            if (userInput!!.length == 1 && userInput.substring(0, 1).matches(Regex("[1-2]"))) {
                GameSetting.valid_input = true
            } else {
                println("\n\tYou must enter '1' or '2' for the game mode: ")
                userInput = readLine()
            }
        }

        //Set user input to gameMode for use later
        GameSetting.gameMode = userInput!!.toInt()
    }
