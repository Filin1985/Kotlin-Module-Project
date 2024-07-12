interface DataType {

    val name: String
}
data class Archive (
    override val name: String,
    val notes: MutableList<DataType>
) : DataType {
    fun addArchive(name: String) {
        val newArchive = Archive(name, mutableListOf())
    }

    fun showNotesMenu() {
        val notesMenu = Menu()
        notesMenu.printMenu(notes, Types.NOTE, this)
    }
}

data class Notes(
    override val name: String,
    val note: MutableList<Text>
) : DataType {
    fun showNote(archive: Archive ?= null) {
        println(this.name)
    }

    fun addTextNote(text: Text) {
        this.note.add(text)
    }
}

data class Text(
    val text: String,
) {

}