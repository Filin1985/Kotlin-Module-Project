import java.util.Scanner

enum class Types(val first: String, val second: String, val third: String) {
    ARCHIVE("Archive name", "archive name", "Archive"),
    NOTE("Note title", "note title", "Note"),
    NOTES_TEXT("Text message", "text", "Text")
}

fun inputString(type: Types, dataObjects: List<DataType>): Pair<String, Boolean> {
    val scanner = Scanner(System.`in`)
    println("Enter the ${type.second}:")
    val result = scanner.nextLine()
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
        Types.NOTES_TEXT -> {
            return Pair(result, false)
        }
    }
    return Pair(result, false)
}
class Menu {
    fun printMenu(
        dataObjects: MutableList<DataType>,
        type: Types,
        archive: Archive ?= null
    ) {
        while(true) {
            println("Choose or create an ${type.third}")
            println("0 - for create the new ${type.third}")
            dataObjects.forEachIndexed() {index, item -> println("${index + 1} - $item")}

            println("${dataObjects.size + 1} - Exit")
            val scanner = Scanner(System.`in`)
            if(!scanner.hasNextInt()) {
                println("Please, enter the integer number from menu list")
                continue
            }
            when(val command = scanner.nextInt()) {
                0 -> {
                    if(type == Types.ARCHIVE) {
                        val result = inputString(Types.ARCHIVE, dataObjects)
                        if(result.second) return
                        val newArchive = Archive(result.first, mutableListOf())
                        dataObjects.add(newArchive)
                    }

                    if(type == Types.NOTE) {
                        val result = inputString(Types.NOTE, dataObjects)
                        if(result.second) return
                        val newNote = Notes(result.first, mutableListOf())
                        while(true) {
                            val resultText = inputString(Types.NOTES_TEXT, dataObjects)
                            if(!resultText.second) {
                                val textNote = Text(resultText.first)
                                newNote.addTextNote(textNote)
                                break
                            }
                        }
                        dataObjects.add(newNote)
                    }

                }

                in 1..dataObjects.size -> {
                    if(type == Types.ARCHIVE) {
                        val chosenArchive = dataObjects[command - 1] as Archive
                        chosenArchive.showNotesMenu()
                    }

                    if(type == Types.NOTE) {
                        val chosenNote = dataObjects[command - 1] as Notes
                        chosenNote.showNote(archive)
                    }

                }
                (dataObjects.size + 1) -> return
                else -> println("We do not have this item in our menu list.")
            }

        }
    }
}

class NoteApp{
    private val archives: MutableList<DataType> = mutableListOf()
    fun startApp() {
        val menu = Menu()
        menu.printMenu(archives, Types.ARCHIVE)
    }
}

fun main(args: Array<String>) {
    val notesApp = NoteApp()
    notesApp.startApp()
}