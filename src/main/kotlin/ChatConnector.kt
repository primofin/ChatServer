//Vy Nguyen - 1807233
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.util.*

interface ChatHistoryObserver {
    fun newMessage(message: ChatMessage){}
    fun newUser(username:String){}

}
class ChatConnector(inputStream : InputStream, outputStream : OutputStream) : Runnable, ChatHistoryObserver {
    private val reader = Scanner(inputStream)
    private val printer = PrintStream(outputStream)
    var myUserName : String  = ""


    private var isRunning: Boolean = true
    override fun run() {
        ChatHistory.registerObserver(this)
        while(myUserName ==""){
            printer.println("Please enter your username ")
            val usernameInput = reader.nextLine()
            //Checking if the username exist
            if (!Users.isUserExit(usernameInput)) {
                Users.insertUser(usernameInput)
                myUserName = usernameInput
                ChatHistory.notifyNewUser(myUserName)
                TopChatter.printChatters()


            }else{
                printer.println("Your user is already existed.")
            }
        }
        while(isRunning) {
            val input = reader.nextLine()
            //val chatMessage = ChatMessage(input, myUserName)
            //TopChatter.chatters(chatMessage)
            if (input.startsWith(":")) {// this is the command
                when (input) {
                    ":Exit" -> {
                        isRunning = false
                        //Writes to console list of active users including the number of messages sent
                        //every time the list changes(once one user left the chat)
                        TopChatter.printChatters()
                        printer.println("User $myUserName left the chat")
                        println("User $myUserName left the chat")
                        ChatHistory.deregisterObserver(this)

                        Users.removeUser(myUserName)
                        reader.close()

                    }
                    ":Users" ->{ // print the list of Users
                    printer.println(Users.listAllUsers())
                    }

                    ":History" ->{
                        printer.println(ChatHistory.toString())
                    }
                    ":TopChatter" ->{
                        printer.println(TopChatter.printChatters())
                    }
                    else -> {
                        printer.println("You have typed the wrong command. Please type a valid command")
                    }
                }


            } else {
                val chatMessage = ChatMessage(input, myUserName)
                ChatHistory.insert(chatMessage)
                ChatHistory.notifyObservers(chatMessage.deserialize())//Parse all incoming messages into objects of ChatMessage type

            }

        }
    }
    override fun newMessage(message: ChatMessage) {
        printer.println("[Message]$message")



    }



}