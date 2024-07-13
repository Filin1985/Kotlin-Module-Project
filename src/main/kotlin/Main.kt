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