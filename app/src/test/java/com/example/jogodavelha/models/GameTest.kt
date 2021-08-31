package com.example.jogodavelha.models

import Game
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.experimental.theories.suppliers.TestedOn

class GameTest {

    private lateinit var game: Game
    private var player1 : Player? = null
    private var player2 : Player? = null

    @Before
    fun setup() {
        game = Game("Heider", "Android")
        player1 = game.player1
        player2 = game.player2
    }

    @Test
    fun returnTrueIfHasThreeSameHorizonalCellAtRow1() {

        val cell = Cell(player1)

        game.cells[0][0] = cell
        game.cells[0][1] = cell
        game.cells[0][2] = cell

        // Acao
        val hasTreeSameHorizontalCells = game.hasThreeSameHorizontalCells()

        // resultado
        assertTrue(hasTreeSameHorizontalCells)
    }

    @Test
    fun returnTrueIfHasThreeSameHorizonalCellAtRow2() {

        val cell = Cell(player1)

        game.cells[1][0] = cell
        game.cells[1][1] = cell
        game.cells[1][2] = cell

        // Acao
        val hasTreeSameHorizontalCells = game.hasThreeSameHorizontalCells()

        // resultado
        assertTrue(hasTreeSameHorizontalCells)
    }

    @Test
    fun returnTrueIfHasThreeSameHorizonalCellAtRow3() {
        val cell = Cell(player1)

        game.cells[2][0] = cell
        game.cells[2][1] = cell
        game.cells[2][2] = cell

        // Acao
        val hasTreeSameHorizontalCells = game.hasThreeSameHorizontalCells()

        // resultado
        assertTrue(hasTreeSameHorizontalCells)
    }

    @Test
    fun returnTrueIfHasThreeSameVerticalCellsAtColumn1() {
        // Cenario
        val cell = Cell(player1)
        game.cells[0][0] = cell
        game.cells[1][0] = cell
        game.cells[2][0] = cell

        // Ação
        val hasThreeSameVerticalCells = game.hasThreeSameVerticalCells()

        //RESULTADO
        assertTrue(hasThreeSameVerticalCells)
    }

    @Test
    fun returnTrueIfHasThreeSameVerticalCellsAtColumn2() {
        // Cenario
        val cell = Cell(player1)
        game.cells[0][1] = cell
        game.cells[1][1] = cell
        game.cells[2][1] = cell

        // Ação
        val hasThreeSameVerticalCells = game.hasThreeSameVerticalCells()

        //RESULTADO
        assertTrue(hasThreeSameVerticalCells)
    }

    @Test
    fun returnTrueIfHasThreeSameVerticalCellsAtColumn3() {
        // Cenario
        val cell = Cell(player1)
        game.cells[0][2] = cell
        game.cells[1][2] = cell
        game.cells[2][2] = cell

        // Ação
        val hasThreeSameVerticalCells = game.hasThreeSameVerticalCells()

        //RESULTADO
        assertTrue(hasThreeSameVerticalCells)
    }

    @Test
    fun returnTrueXtest1() {
        // Cenario
        val cell = Cell(player1)
        game.cells[0][0] = cell
        game.cells[1][2] = cell
        game.cells[2][2] = cell

        // Ação
        val hasThreeSameCruzadoCells = game.hasThreeSameCruzadoCells()

        //RESULTADO
        assertTrue(hasThreeSameCruzadoCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameDiagonalCellsFromLeft() {
        val cell = Cell(player1)
        game.cells[0][0] = cell
        game.cells[1][1] = cell
        game.cells[2][2] = cell
        val hasThreeSameDiagonalCells = game.hasThreeSameDiagonalCells()
        assertTrue(hasThreeSameDiagonalCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameDiagonalCellsFromRight() {
        val cell = Cell(player1)
        game.cells[0][2] = cell
        game.cells[1][1] = cell
        game.cells[2][0] = cell
        val hasThreeSameDiagonalCells = game.hasThreeSameDiagonalCells()
        assertTrue(hasThreeSameDiagonalCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnFalseIfDoesNotHaveThreeSameDiagonalCells() {
        val cell = Cell(player1)
        val anotherCell = Cell(player2)
        game.cells[0][2] = cell
        game.cells[1][1] = cell
        game.cells[2][0] = anotherCell
        val hasThreeSameDiagonalCells = game.hasThreeSameDiagonalCells()
        assertFalse(hasThreeSameDiagonalCells)
    }

    @Test
    fun endGameIfHasThreeSameDiagonalCells() {
        val cell = Cell(player1)
        game.cells[0][0] = cell
        game.cells[1][1] = cell
        game.cells[2][2] = cell
        val hasGameEnded = game.hasGameEnded()
        assertTrue(hasGameEnded)
    }

    @Test
    fun endGameIfHasThreeSameVerticalCells() {
        val cell = Cell(player1)
        game.cells[0][0] = cell
        game.cells[1][0] = cell
        game.cells[2][0] = cell
        val hasGameEnded = game.hasGameEnded()
        assertTrue(hasGameEnded)
    }

    @Test
    fun endGameIfBoardIsFull() {

        game.cells[0][0] = Cell(player1)
        game.cells[0][1] = Cell(player1)
        game.cells[0][2] = Cell(player2)

        game.cells[1][0] = Cell(player2)
        game.cells[1][1] = Cell(player1)
        game.cells[1][2] = Cell(player1)

        game.cells[2][0] = Cell(player2)
        game.cells[2][1] = Cell(player1)
        game.cells[2][2] = Cell(player2)

        val hasGameEnded = game.hasGameEnded()
        assertTrue(hasGameEnded)
    }
}