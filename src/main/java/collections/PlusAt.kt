package collections

fun <T> List<T>.plusAt(index: Int, element: T): List<T> {
    require(index in 0 .. this.size) { "Index out of bound" }

    // sol 1
    return this.take(index) + element + this.drop(index)

    // sol 2
//    val mutableList = this.toMutableList()
//    mutableList.add(index, element)
//    return mutableList
}
