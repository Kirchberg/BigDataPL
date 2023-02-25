// №4. Создать приложение для ввода пароля из командной строки и сравнения его со строкой-образцом.
fun task4() {
    println(message = "Task 4")
    print(message = "Enter password: ")
    val passwordSample: String = "1qazxsw2" // Secret password
    if (passwordSample.equals(readLine())) println(message = "Passwords are equal") else println(message = "Incorrect password")
}

// №5. Создать программу ввода целых чисел как аргументов командной строки, подсчета их суммы (произведения) и вывода результата на консоль.
fun task5(args: Array<String>) {
    println(message = "Task 5")
    println(message = args.sumOf { Integer.parseInt(it) })
}

// №10. Ввести с консоли n целых чисел и поместить их в массив. На консоль вывести: числа в порядке убывания частоты встречаемости чисел.
fun task10() {
    println(message = "Task 10")
    print("Enter the number of integers: ")
    val n = readLine()?.toIntOrNull() ?: return

    val arr = IntArray(n)
    println("Enter $n integers:")
    for (i in 0 until n) arr[i] = readLine()?.toIntOrNull() ?: return

    val freq = HashMap<Int, Int>()
    for (num in arr) freq[num] = freq.getOrDefault(num, 0) + 1

    val sortedNumbers = freq.entries.sortedByDescending { it.value }.map { it.key }
    println("Numbers in order of frequency of occurrence:")
    for (num in sortedNumbers) print("$num  ")
    println()
}

// №11. Ввести с консоли n целых чисел и поместить их в массив. На консоль вывести: «Счастливые» числа.
fun task11() {

    fun sumOfSquares(number: Int): Int {
        var sum = 0
        var n = number
        while (n > 0) {
            val digit = n % 10
            sum += digit * digit
            n /= 10
        }
        return sum
    }

    fun isHappy(number: Int): Boolean {
        var sum = number
        while (sum != 1 && sum != 4) {
            sum = sumOfSquares(sum)
        }
        return sum == 1
    }

    println(message = "Task 11")
    print("Enter n integers separated by spaces: ")
    val input = readLine()?.trim() ?: return
    val numbers = input.split(" ").mapNotNull { it.toIntOrNull() }.toList()

    val happyNumbers = mutableListOf<Int>()
    for (number in numbers) {
        if (isHappy(number)) {
            happyNumbers.add(number)
        }
    }

    if (happyNumbers.isNotEmpty()) {
        println("Happy numbers: ${happyNumbers.joinToString()}")
    } else {
        println("There's no happy numbers 😢")
    }

}
