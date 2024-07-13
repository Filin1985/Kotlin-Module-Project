interface DataType {
    val name: String
}
data class Archive (
    override val name: String,
    val notes: MutableList<DataType>
) : DataType {

    fun showNotesMenu() {
        val notesMenu = Menu()
        notesMenu.printMenu(notes, Types.NOTE, this)
    }
}

data class Notes(
    override val name: String,
    val text: MutableList<Text>
) : DataType {
    fun showNote() {
        println("Note title: ${this.name}")
        println("Note text: ${this.text[0].text}")
    }

    fun addTextNote(text: Text) {
        this.text.add(text)
    }
}

data class Text(
    val text: String,
)