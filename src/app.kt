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
    val name = customer.name
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

    val pers = Person("Liza")
}

fun Customer.sendEmail() = println("sending email from ${this.email}...")

fun Customer.levelUp() = this.name.length + 1

data class Customer(var name: String, var email: String)

class Person(val firstName: String, val age: Int) {
    init {
        println("This is initialization block for $this. It executes together with primary constructor")
    }
    constructor(firstName: String) : this(firstName, 22) {
        println("Secondary constructor had been called")
    }
}

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