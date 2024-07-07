data class DataStorage(val name: String = "") {
    val ARCHIVE: List<String> = listOf("Archive name", "archive name", "archive")
}

class Menu {
    fun printMenu(
        menuElements: MutableSet<String>,
        archive: Archive
    ) {
        while(true) {
            println("Choose or select an archive")
            println("0 - for create the new archive")
            menuElements.toList().forEachIndexed() {index, item -> println("${index + 1} - $item")}

            println("${menuElements.size + 1} - Exit")

        }
    }
}

class Archive(val archive: MutableMap<String, MutableMap<String, String>>) {

}

fun main(args: Array<String>) {
    val archive = Archive(mutableMapOf())
    println("Hello World!")
}