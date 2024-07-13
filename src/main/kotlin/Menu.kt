import java.util.Scanner

class Menu {
    fun printMenu(
        dataObjects: MutableList<DataType>,
        type: Types,
        archive: Archive ?= null
    ) {
        while(true) {
            println("Choose or create an ${type.third}")
            println("0 - Create new ${type.third}")
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
                            val resultText = inputString(Types.TEXT, dataObjects)
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
                        chosenNote.showNote()
                    }

                }
                (dataObjects.size + 1) -> return
                else -> println("We do not have this item in our menu list.")
            }

        }
    }
}