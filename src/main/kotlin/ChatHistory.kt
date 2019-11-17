//Vy Nguyen - 1807233
interface ChatHistoryObservable {
    fun registerObserver(observer: ChatHistoryObserver)
    fun deregisterObserver(observer: ChatHistoryObserver)
    fun notifyObservers (message:ChatMessage)
    fun notifyNewUser(username:String)
}

object ChatHistory : ChatHistoryObservable {
    var observers =HashSet<ChatHistoryObserver>()
    override fun registerObserver(observer: ChatHistoryObserver) {
        observers.add(observer)

    }

    override fun deregisterObserver(observer: ChatHistoryObserver) {
        observers.remove(observer)
    }

    override fun notifyObservers(message: ChatMessage) {
        for(observer in observers){
            observer.newMessage(message)
        }

    }
    val chatHistory = mutableListOf<ChatMessage>()

    fun insert(message: ChatMessage){
        chatHistory.add(message)

    }
    override fun toString(): String{
        var result =""
        for (message in chatHistory){
           result += message.toString() + "\n"
        }
        return result
    }

    override fun notifyNewUser(username: String) {
        for(observer in observers){
            observer.newUser(username)
        }
    }
}