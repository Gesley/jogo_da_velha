import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jogodavelha.models.Cell
import com.example.jogodavelha.models.Player
import java.lang.NullPointerException

class Game(playerOne: String, playerTwo: String) {

    var player1: Player? = null
    var player2: Player? = null

    var currentPlayer = player1

    var cells: Array<Array<Cell?>> = Array(BOARD_SIZE) { arrayOfNulls<Cell>(BOARD_SIZE) }

    var winner: MutableLiveData<Player> = MutableLiveData()

    init {
        player1 = Player(playerOne, "X")
        player2 = Player(playerTwo, "O")
        currentPlayer = player1
    }

    val isBoardFull: Boolean
        get() {
            for (row in cells)
                for (cell in row)
                    if (cell == null || cell.isEmpty) return false
            return true
        }

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
    }

    fun reset() {
        player1 = null
        player2 = null
        currentPlayer = player1
        cells = Array(BOARD_SIZE) { arrayOfNulls<Cell>(BOARD_SIZE) }
    }

    private fun areEqual(vararg cells: Cell?): Boolean {

        for (cell in cells) if (cell?.player?.value == null)
            return false

        val comparisonBase = cells[0]

        for (i in 1 until cells.size)
            if (!comparisonBase?.player?.value.equals(cells[i]?.player?.value))
                return false

        return true
    }


    fun hasThreeSameHorizontalCells(): Boolean {
        try {
            for (i in 0 until BOARD_SIZE)
                if (areEqual(cells[i][0], cells[i][1], cells[i][2]))
                    return true

            return false
        } catch (e: NullPointerException) {
            Log.e(TAG, e.message ?: "Erro")
            return false
        }
    }

    fun hasVertical(): Boolean {
        try {
            for (i in 0 until BOARD_SIZE)
                if (areEqual(cells[i][0], cells[1][1], cells[i][2]))
                    return true

            return false
        } catch (e: NullPointerException) {
            Log.e(TAG, e.message ?: "Erro")
            return false
        }
    }

    fun hasThreeSameVerticalCells(): Boolean {
        return try {
            for (i in 0 until BOARD_SIZE)
                if(areEqual(cells[0][i], cells[1][i], cells[2][i]))
                    return true
            false
        } catch (e: NullPointerException) {
            Log.e(TAG, e.message ?: "Erro")
            return false
        }
    }

    fun hasThreeSameCruzadoCells(): Boolean {
        return try {
            for (i in 0 until BOARD_SIZE)
                if(areEqual(cells[i][i], cells[i][i], cells[i][i]))
                    return true
            false
        } catch (e: NullPointerException) {
            Log.e(TAG, e.message ?: "Erro")
            return false
        }
    }

    fun hasThreeSameDiagonalCells(): Boolean {
        return try {
            (areEqual(cells[0][0], cells[1][1], cells[2][2]) || areEqual(
                cells[0][2],
                cells[1][1],
                cells[2][0]
            ))
        } catch (e: NullPointerException) {
            Log.e(TAG, e.message!!)
            false
        }
    }

    fun hasGameEnded(): Boolean {
        return if (hasThreeSameDiagonalCells() || hasThreeSameVerticalCells() || hasThreeSameHorizontalCells()) {
            winner.value = currentPlayer
            true
        } else {
            if(isBoardFull) {
                winner.value = null
                return true
            }
            false
        }
    }

    companion object {
        private val TAG = Game::class.java.simpleName
        private val BOARD_SIZE = 3
    }

}