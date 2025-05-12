package board

import board.Direction.*

open class SquareBoardImp(override val width: Int) :SquareBoard{
    private val boardWidth=width
    val cells= mutableListOf<Cell>()
    init {
        for (i in 1..boardWidth){
            for(j in 1..boardWidth){
                cells.add(Cell(i,j))
            }
        }
    }


    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return cells.find { it==Cell(i,j) }
    }

    override fun getCell(i: Int, j: Int): Cell {
        return cells.first{ it==Cell(i,j) }
    }

    override fun getAllCells(): Collection<Cell> {
        return cells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return cells.filter { i == it.i && it.j in jRange }.sortedBy { if(jRange.step<0) -it.j else it.j }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return cells.filter {j == it.j && it.i in iRange }.sortedBy { if(iRange.step<0) -it.i else it.i }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when(direction){
            LEFT-> getCellOrNull(i,j-1)
            RIGHT->getCellOrNull(i,j+1)
            UP->   getCellOrNull(i-1,j)
            DOWN-> getCellOrNull(i+1,j)
        }
    }
}
fun createSquareBoard(width: Int): SquareBoard = SquareBoardImp(width)
class GameBoardImp <T>(width: Int): SquareBoardImp(width),GameBoard<T>{
    val map= mutableMapOf<Cell,T?>()
    init {
        for (i in 1..width){
            map[cells[i]] = null
        }
    }
    override fun get(cell: Cell): T? {
        return map.get(cell)
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return cells.all { predicate(get(it)) }
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return cells.any{predicate(get(it))}
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return cells.find { predicate(get(it)) }
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return cells.filter { predicate(get(it)) }
    }

    override fun set(cell: Cell, value: T?) {
        map[cell] = value
    }

}
fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImp(width)

