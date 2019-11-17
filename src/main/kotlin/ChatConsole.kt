//Vy Nguyen - 1807233
object ChatConsole : Runnable, ChatHistoryObserver {
    override fun run() {
        ChatHistory.registerObserver(this)
    }

    override fun newMessage(message: ChatMessage) {
        println(message)
    }



}