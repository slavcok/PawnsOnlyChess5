package chess

fun main() {
    val board = Array(8) { Array(8) { ' ' } }
    for (file in 0 until 8) {
        board[file][6] = 'B'
        board[file][1] = 'W'
    }
    println("Pawns-Only Chess")
    println("First Player's name:")
    val firstPlayer = readLine()!!.toString()
    println("Second Player's name:")
    val secondPlayer = readLine()!!.toString()
    gameFieldPrint(board)
    val round = true
    var player = firstPlayer
    val regex = "[a-h][1-8][a-h][1-8]".toRegex()
    var moving: String
    var a1 = 0
    var a2: Int
    var b1 = 0
    var b2: Int
    var c2 = 0
    var enPassant = false
    var testW: Int
    var testB: Int
    var stalemate: Boolean


    do {
        println("$player's turn:")
        moving = readLine()!!.toString()
        if (moving == "exit") {
            break
        }
        when (moving[0]) {
            'a' -> a1 = 0
            'b' -> a1 = 1
            'c' -> a1 = 2
            'd' -> a1 = 3
            'e' -> a1 = 4
            'f' -> a1 = 5
            'g' -> a1 = 6
            'h' -> a1 = 7
        }
        a2 = moving[1].toString().toInt() - 1
        when (moving[2]) {
            'a' -> b1 = 0
            'b' -> b1 = 1
            'c' -> b1 = 2
            'd' -> b1 = 3
            'e' -> b1 = 4
            'f' -> b1 = 5
            'g' -> b1 = 6
            'h' -> b1 = 7
        }
        b2 = moving[3].toString().toInt() - 1


        if (!moving.matches(regex)) {
            println("Invalid Input")
        }else if (player == firstPlayer && board[a1][a2] != 'W') {
            println("No white pawn at ${moving[0]}${moving[1]}")
        }else if (player == secondPlayer && board[a1][a2] != 'B') {
            println("No black pawn at ${moving[0]}${moving[1]}")
        }else if (a1 == b1 && board[b1][b2] != ' '){
            println("Invalid Input")
        }else if (b2 >= a2 + 2 && (a2 != 1 )) {
            println("Invalid Input")
        }else if (b2 <= a2 - 2 && (a2 != 6 )) {
            println("Invalid Input")
        }else if (b2 == a2) {
            println("Invalid Input")
        }else if (player == firstPlayer && a2 > b2) {
            println("Invalid Input")
        }else if (player == secondPlayer && b2 > a2) {
            println("Invalid Input")
        }else if (player == firstPlayer && b2 > a2 + 2) {
            println("Invalid Input")
        }else if (player == secondPlayer && b2 < a2 - 2) {
            println("Invalid Input")
        }else if (player == firstPlayer && (!( a1 == b1 || (a1 == b1 + 1 && board[b1][b2] == 'B') || (a1 == b1 - 1 && board[b1][b2] == 'B') ||
                    (enPassant && a1 == b1 + 1 && c2 == b2 - 1) || (enPassant && a1 == b1 - 1 && c2 == b2 - 1) ))) {
            println("Invalid Input")
        }else if (player == secondPlayer && (!(a1 == b1 || (a1 == b1 + 1 && board[b1][b2] == 'W') || (a1 == b1 - 1 && board[b1][b2] == 'W') ||
                    (enPassant && a1 == b1 + 1 && b2 == c2 + 1) || (enPassant && a1 == b1 - 1 && b2 == c2 + 1) ))) {
            println("Invalid Input")
        } else {
            if (board[a1][a2] == 'W' && ((b2 - a2 == 1) || (b2 - a2 == 2 && a2 == 1))) {
                board[a1][a2] = ' '
                board[b1][b2] = 'W'
                if (player == firstPlayer && ((enPassant && a1 == b1 + 1 && c2 == b2 - 1) || (enPassant && a1 == b1 - 1 && c2 == b2 - 1) )) {
                    board[b1][b2 - 1] = ' '
                }
                gameFieldPrint(board)
                player = if (player == firstPlayer) {
                    secondPlayer
                } else {
                    firstPlayer
                }
            } else if (board[a1][a2] == 'B' && ((a2 - b2 == 1) || (a2 - b2 == 2 && a2 == 6))) {
                board[a1][a2] = ' '
                board[b1][b2] = 'B'
                if (player == secondPlayer && ((enPassant && a1 == b1 + 1 && b2 == c2 + 1) || (enPassant && a1 == b1 - 1 && b2 == c2 + 1))) {
                    board[b1][b2 + 1] = ' '
                }
                gameFieldPrint(board)
                player = if (player == firstPlayer) {
                    secondPlayer
                } else {
                    firstPlayer
                }
            }
        }

        enPassant = false
        c2 = 10
        if (player == secondPlayer && a2 == 1 && b2 == 3) {
            enPassant = true
            c2 = b2
        } else if (player == firstPlayer && a2 == 6 && b2 == 4) {
            enPassant = true
            c2 = b2
        }

        testB = 0
        testW = 0
        for (i in 0..7){
            for (j in 0..7){
                if (board[i][j] == 'B') {
                    testB += 1
                }
                if (board[i][j] == 'W') {
                    testW += 1
                }
            }
        }
        if (b2 == 7) {
            println("White Wins!")
            break
        } else if (b2 == 0) {
            println("Black Wins!")
            break
        } else if (testB == 0) {
            println("White Wins!")
            break
        } else if (testW == 0) {
            println("Black Wins!")
            break
        }
        stalemate = true
        for (i in 0..7){
            for (j in 1..6){

                    if (i == 0 && player == secondPlayer) {
                        if (board[i][j] == 'B' && (board[i][j - 1] == ' ' || board[i + 1][j - 1] == 'W')) {
                            stalemate = false
                        }
                    } else if (i in 1..6 && player == secondPlayer) {
                        if (board[i][j] == 'B' && (board[i][j - 1] == ' ' || (board[i - 1][j - 1] == 'W' || board[i + 1][j - 1] == 'W')))  {
                            stalemate = false
                        }
                    } else if (i == 7 && player == secondPlayer) {
                        if (board[i][j] == 'B' && (board[i][j - 1] == ' ' || board[i - 1][j - 1] == 'W')) {
                        stalemate = false
                        }
                    }

                    if (i == 0 && player == firstPlayer) {
                        if (board[i][j] == 'W' && (board[i][j + 1] == ' ' || board[i + 1][j + 1] == 'B')) {
                            stalemate = false
                        }
                    } else if (i in 1..6 && player == firstPlayer) {
                        if (board[i][j] == 'W' && (board[i][j + 1] == ' ' || (board[i - 1][j + 1] == 'B' || board[i + 1][j + 1] == 'W')))  {
                            stalemate = false
                        }
                    } else if (i == 7 && player == firstPlayer) {
                        if (board[i][j] == 'W' && (board[i][j + 1] == ' ' || board[i - 1][j + 1] == 'B'))  {
                            stalemate = false
                        }
                    }
            }
        }
        if (stalemate) {
            println("Stalemate!")
            break
        }

    } while (round)
    println("Bye!")
}

fun gameFieldPrint(board: Array<Array<Char>>)  {
    for (i in 8 downTo 1 ) {
        linePrint()
        print("$i")
        print(" | ")
        for (file in 0 until 8) {
            print(board[file][i - 1])
            print(" | ")
        }
        println()
    }
    linePrint()
    println("    a   b   c   d   e   f   g   h")
    println()
}

fun linePrint() {
    println("  +---+---+---+---+---+---+---+---+")
}