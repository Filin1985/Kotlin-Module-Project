import java.util.Scanner

enum class Types(val first: String, val second: String, val third: String) {
    ARCHIVE("Archive name", "archive name", "Archive"),
    NOTE("Note title", "note title", "Note"),
    TEXT("Text message", "text", "Text")
}

fun inputString(type: Types, dataObjects: List<DataType>): Pair<String, Boolean> {
    val scanner = Scanner(System.`in`)
    println("Enter the ${type.second}:")
    val result = scanner.nextLine().lowercase()
    if(result.isEmpty()){
        println("${type.first} can't be empty")
        return Pair(result, true)
    }
    when (type){
        Types.ARCHIVE -> {
            if (dataObjects.any { item -> item.name == result }) {
                println("${type.third} with this name already exists")
                return Pair(result, true)
            }
        }
        Types.NOTE -> {
            if (dataObjects.any { item -> item.name == result }) {
                println("${type.third} with this name already exists")
                return Pair(result, true)
            }
        }
        Types.TEXT -> {
            return Pair(result, false)
        }
    }
    return Pair(result, false)
}