//Vy Nguyen - 1807233
object TopChatter: Runnable, ChatHistoryObserver {
    private var chatterMap = mutableMapOf<String?, Int>()


    override fun newMessage(message: ChatMessage) {
        if (!chatterMap.containsKey(message.username)) {

            chatterMap[message.username] = 1
        } else {
            chatterMap[message.username] = chatterMap[message.username]!!.plus(1)
        }
        chatterMap = chatterMap.toList().sortedByDescending { (_,value) ->value }.toMap().toMutableMap()// sort Map to find Top Chatters

    }

    fun printChatters() {
        var topChatters = mutableMapOf<String?,Int>()
            for(i in chatterMap.keys){
                if (!topChatters.contains(i) && topChatters.size < 4) {
                    topChatters[i] = chatterMap[i] as Int
                }
            }

            println("Active users and the number of messages:")
           topChatters.forEach { (k, v) -> println("User $k : $v messages") }


    }



    override fun run() {
        ChatHistory.registerObserver(this)

    }

    override fun newUser(username: String) {

        chatterMap[username] = 0
    }
}