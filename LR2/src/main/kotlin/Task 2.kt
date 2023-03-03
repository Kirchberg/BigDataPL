import java.time.LocalDateTime
import kotlin.random.Random

// Variant №1
// Task №1

/*
№1. Ввести n строк с консоли, найти самую короткую и самую длинную строки. Вывести найденные строки и их длину
 */

fun task1V1() {
    println("Enter the number of strings:")
    val n = readLine()?.toInt() ?: 0

    var shortest: String? = null
    var longest: String? = null

    for (i in 0 until n) {
        println("Enter string #$i:")
        val str = readLine() ?: ""
        if (shortest == null || str.length < shortest.length) {
            shortest = str
        }
        if (longest == null || str.length > longest.length) {
            longest = str
        }
    }

    println("Shortest string: $shortest, length: ${shortest?.length ?: 0}")
    println("Longest string: $longest, length: ${longest?.length ?: 0}")
}

// Variant №1
// Task №10

/*
№10. Необходимо вывести внизу фамилию разработчика, дату и время получения задания, а также дату и время сдачи задания.
Для получения последней даты и времени следует использовать класс Date.
Используя оператор switch, написать программу, которая выводит на экран сообщения о принадлежности некоторого значения
k интервалам (-10k, 5], [0, 10], [5, 15], [10, 10k].
 */
fun task10V1() {
    println(message = "Task 1 Var. 10")

    val jobReceivedTime = LocalDateTime.now()

    print(message = "Enter your developer name: ")
    val developerName = readlnOrNull() ?: return

    print(message = "Enter k: ")
    var k = readlnOrNull()?.toIntOrNull() ?: return

    when (k) {
        in -10000..5 -> println("$k belongs to the interval (-10000, 5]")
        in 0..10 -> println("$k belongs to the interval [0, 10]")
        in 5..15 -> println("$k belongs to the interval [5, 15]")
        in 10..10000 -> println("$k belongs to the interval [10, 10000]")
        else -> println("$k does not belong to any interval")
    }

    val jobCompletedTime = LocalDateTime.now()
    println(message = "Developer name: $developerName")
    println(message = "Job received time: $jobReceivedTime")
    println(message = "Job completed time: $jobCompletedTime")
}

// Variant №2
// Task №1

/*
№1. Ввести с консоли n – размерность матрицы a[n][n]. Задать значения элементов матрицы в интервале значений от -n до n
с помощью датчика случайных чисел. Упорядочить строки (столбцы) матрицы в порядке возрастания значений элементов k-го
столбца (строки).
 */

// Variant №2
// Task №11

fun task1V2() {

    print("Введите размерность матрицы: ")
    val n = readLine()?.toIntOrNull() ?: return
    val matrix = Array(n) { IntArray(n) }

    // заполняем матрицу случайными числами от -n до n
    for (i in 0 until n) {
        for (j in 0 until n) {
            matrix[i][j] = Random.nextInt(-n, n + 1)
        }
    }

    println("Начальная матрица:")
    for (row in matrix) {
        println(row.joinToString(separator = " "))
    }

    print("Введите номер столбца (строки) для сортировки: ")
    val k = readLine()?.toIntOrNull() ?: return
    val ascendingComparator = compareBy<Int> { it }
    // для сортировки столбцов по возрастанию значений k-го столбца
    if (k < n) {
        matrix.sortBy { it[k] }
    }
    // для сортировки строк по возрастанию значений k-й строки
    else if (k < n * 2) {
        val row = k - n
        matrix.sortBy { it[row] }
    }
    println("Отсортированная матрица:")
    for (row in matrix) {
        println(row.joinToString(separator = " "))
    }

}

/*
№10. Ввести с консоли n – размерность матрицы a[n][n]. Задать значения элементов матрицы в интервале значений от -n до n
с помощью датчика случайных чисел.
Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие
 */
fun task10V2() {

    fun printMatrix(a: Array<IntArray>) {
        for (i in a.indices) {
            for (j in a[i].indices) {
                print("${a[i][j]}\t")
            }
            println()
        }
    }

    println(message = "Task 2 Var. 10")

    // Read the dimensionality of the matrix from the console
    print("Enter the dimensionality of the matrix: ")
    val n = readLine()?.toInt() ?: 0

    // Initialize the matrix with random values in the range [0, n]
    val matrix = Array(n) { i ->
        IntArray(n) { j ->
            Random.nextInt(-n, n)
        }
    }

    // Find the maximal element(s) in the matrix
    var maxElement = Int.MIN_VALUE
    val maxElements = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (matrix[i][j] > maxElement) {
                maxElement = matrix[i][j]
                maxElements.clear()
                maxElements.add(Pair(i, j))
            } else if (matrix[i][j] == maxElement) {
                maxElements.add(Pair(i, j))
            }
        }
    }

    // Delete rows and columns containing the maximal element(s)
    val rowsToDelete = maxElements.map { it.first }.toSet()
    val colsToDelete = maxElements.map { it.second }.toSet()
    val newMatrix = Array(n - rowsToDelete.size) { i ->
        IntArray(n - colsToDelete.size) { j ->
            val row = if (i < rowsToDelete.first()) i else i + 1
            val col = if (j < colsToDelete.first()) j else j + 1
            matrix[row][col]
        }
    }

    // Print the original matrix and the modified matrix
    println("Original matrix:")
    printMatrix(matrix)
    println("Modified matrix:")
    printMatrix(newMatrix)

}