val PI = 3.14
var x = 0

fun main(args: Array<String>) {
    val a = 4
    var b : Int = 22
    println("summ a, b : ${summ(a, b)}")
    println("diff a, b : ${diff(a, b)}")
    incrementX()
    println("X is $x")
    println("max is ${max(a, b)}")
    val str = "Something"
    isString(str)
    isString(b)
    println("ret is String: ${cases(str)}")
    println("ret is Int: ${cases(b)}")
    println("4 in 0..22 : ${isNumInThatRange(4, 22)}" )
    println("in collection: ${isValInCollection("yo!")}")
    lambdaTest(listOf(1, 2, 3, 9, 20))

    val customer = Customer("Johnny", "someMail")
    // деструктуризация, как в js
    val (name, email) = customer
    println("name is $name")
    customer.name = "Peter"
    println("new name is ${customer.name}")

    val someMap = mapOf(customer.name to customer, "Diana" to Customer("Diana", "mail"))
    iterateMap(someMap)

    customer.sendEmail()

    val x = null
    //x ?: throw IllegalStateException("x is null!")

    val some = 4
    println("${some?.let { it * 2 }}")

    with (customer) {
        sendEmail()
        println(levelUp())
    }

    val liza = Human("pragmatic", "Liza", 11)

    liza.grow()

    liza.dream("about something")

    // используем синглетон
    println(SingletonObject.intValue)

    // используем функциональный интерфейс (сразу пишем реализацию)
    liza.doSomething(object : Walk {
        override fun keepWalking() {
            println("liza keep walking")
        }
    })

    // можно объявлять и создавать объекты на лету, прямо как в js
    val lizasBag = object {
        var primaryPartition = "some"
    }

    // вызов функции с varargs
    println(countVarargs(1, 5, 20, 187, 2548, 11))
}

open class Person(val firstName: String, var age: Int) {

    init {
        println("This is initialization block for $this. It executes together with primary constructor")
    }
    constructor(firstName: String) : this(firstName, 22) {
        println("Secondary constructor had been called")
    }

    open fun grow() {
        this.age++
    }

    // внутрненние объекты
    companion object Home {
        val address: String = "some address"

        fun openDoor() {
            println("the door of $address is open")
        }
    }
}

interface Dreamer {
    fun dream(dreams: String) {
        println("now dreaming $dreams")
    }
}

class Human(val character: String, firstName: String, age: Int) : Person(firstName, age), Dreamer {
    public var lifeGoal: String = ""
        set(value) {
            field = "$value!"
        }
    public var somethingWithImplictType: Int = 4
        set(value) = this.parseData(value)

    private fun parseData(value: Int) {
        this.somethingWithImplictType = value * 2
    }

    override fun grow() {
        super.grow()
        println("now called grow in human named ${this.firstName}")
    }

    // используем функциональный интерфейс
    fun doSomething(action: Walk) {
        action.keepWalking()
    }
}

// функции расширения
fun Customer.sendEmail() = println("sending email from ${this.email}...")

// свойства-расширения
var Human.strength: Int
    get() = strength
    set(value) {
        strength = value
    }
// функции и свойства-расширения объявляются сразу под пакетами
// однако их можно импортировать

class Work(val companyName: String) {

    fun workFlow() = println("$companyName workflow")
    // функции-расширения можно объявлять внутри другого класса
    fun Human.doWork() {
        workFlow()
        println("${this.firstName} do work")
    }
}

fun Customer.levelUp() = this.name.length + 1

// data-классы служат для транспортировки даты
// они содержат ряд полезных функций (напр. copy())
// но обязаны объявлять свои свойства в конструкторе
data class Customer(var name: String, var email: String)

// изолированные классы - продвинутый enum
sealed class Transport(val train: String, val plain: String)

// при этом обычные enum тоже есть
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

// а вот так объявляются синглетоны
object SingletonObject {
    val intValue = 22
}

interface Walk {
    fun keepWalking()
}

// класс-делегадор, который генерирует у WalkDelegator
// соответствующие методы из Walk
class WalkDelegator(walk: Walk) : Walk by walk

// дженеразированные классы
class Box<T>(insideIs: T)

// для безопасного приведения типов
// https://kotlinlang.ru/docs/reference/generics.html
class AnotherBox<out T>(content: T)
class ThirdBox<in T>(content: T)

fun iterateMap(someMap: Map<String, Customer>) {
    for ((k, v) in someMap) {
        println("$k: $v")
    }
}

fun lambdaTest(coll: List<Int>) {
    coll.filter { it > 9 }
            .sortedBy { it }
            .map { it * 2 }
            .forEach { println(it) }
}


fun summ(a: Int, b: Int) = a + b
fun diff(a: Int, b: Int) = a - b
fun max(a: Int, b: Int) = if (a > b) a else b
fun incrementX() {
    x += 1
}
fun trueOrNull(a: Int) : Boolean? = if (a > 0) true else null
fun isString(a: Any) : Boolean? {
    return if (a is String) {
        println("this is a String! Length is ${a.length}")
        true
    }
    else {
        println("This is not the String! Returning null...")
        null
    }
}
fun cases(obj: Any): String? {
    var returner : String? = null
    when (obj) {
        is String   -> returner = "String"
        is Long     -> returner = "Long"
        is Boolean  -> returner = "Bool"
    }
    return returner
}
fun isNumInThatRange(num: Int, upper: Int) : Boolean {
    return num in 0..upper
}
fun isValInCollection(value: Any, coll: List<Any> = listOf("yo!", "yoyo!")) : Boolean {
    return when (value) {
        in coll -> true
        !in coll -> false
        else -> throw RuntimeException("$coll is wrong")
    }
}

fun countVarargs(vararg values: Int): Int {
    var counter = 0
    for (value in values) {
        counter += value
    }
    return counter
}