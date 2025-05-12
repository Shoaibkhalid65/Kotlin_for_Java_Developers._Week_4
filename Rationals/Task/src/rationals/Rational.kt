package rationals

import java.math.BigInteger
fun String.toRational():Rational {
    fun String.toBigIntegerOrFail()=toBigIntegerOrNull()?:throw IllegalArgumentException("Expecting rational in the form of n/d or n "+"was ${this@toRational}}" )
    if(!contains("/"))
        return Rational(toBigIntegerOrFail(),BigInteger.ONE)
    val (num,dNum)=split("/")
    return Rational(num.toBigIntegerOrFail(),dNum.toBigIntegerOrFail())
    }

infix fun Number.divBy(number:Number):Rational{
    fun Number.toBigInteger():BigInteger=when(this){
        is BigInteger-> this
        is Short,is Byte,is Int,is Long-> BigInteger.valueOf(this.toLong())
        is Double,is Float-> {
            if(this.toDouble().isInfinite()){
                    throw IllegalArgumentException("Cannot convert NaN or infinity to BigInteger")
            }
            BigInteger.valueOf(this.toLong())
        }
        else-> throw IllegalArgumentException("Unsupported Number type  ${this::class}")
    }
    return Rational(this.toBigInteger(),number.toBigInteger())
}

class Rational(var n: BigInteger, var d: BigInteger) :Comparable<Rational>{
    init {
        require(d != BigInteger.ZERO) {"Denominator can't be zero"}
        val sign=d.signum().toBigInteger()
        val gcd=n.gcd(d)
        this.n=n/gcd*sign
        this.d=d/gcd*sign.abs()
    }
    operator fun plus(rational:Rational):Rational{
        val num=n*rational.d+d*rational.n
        val dNum=d*rational.d
        return Rational(num,dNum)
    }
    operator fun minus(rational: Rational):Rational{
        val num=n*rational.d-d*rational.n
        val dNum=d*rational.d
        return Rational(num,dNum)
    }
    operator fun times(rational: Rational):Rational{
        val num=n*rational.n
        val dNum=d*rational.d
        return Rational(num,dNum)
    }
    operator fun div(rational: Rational):Rational{
        val num=n*rational.d
        val dNum=d*rational.n
        return Rational(num,dNum)
    }
    operator fun rem(rational: Rational):Rational{
        val division=this/rational
        val k:Int=division.n.toInt()/division.d.toInt()
        val r=this-(rational*k.toString().toRational())
        return r
    }
    operator fun unaryMinus():Rational{
        val num=-n
        return Rational(num,d)
    }
    override operator fun compareTo(rational: Rational):Int{
        val left = n * rational.d
        val right = d * rational.n
        return left.compareTo(right)
    }

    override fun toString(): String {
        val numerator = if (d < BigInteger.ZERO) -n else n
        val denominator = if (d < BigInteger.ZERO) -d else d
        return if (denominator == BigInteger.ONE) {
            numerator.toString()
        } else {
            "$numerator/$denominator"
        }
    }
    override  fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Rational) return false
        return (n * other.d) == (d * other.n)
    }

    override fun hashCode(): Int {
        var result = n.hashCode()
        result = 31 * result + d.hashCode()
        return result
    }
}

fun main() {
    val half = 1 divBy  2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}