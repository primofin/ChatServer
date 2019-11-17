//Vy Nguyen - 1807233
import java.net.ServerSocket

class ChatServer {
    val serverSocket = ServerSocket(30001, 3)
    fun serve() {
        while (true) {
            try {

                val s = serverSocket.accept()
                println("new connection " + s.inetAddress.hostAddress + " " + s.port)
                val thread = Thread(ChatConnector(s.getInputStream(), s.getOutputStream()))
                thread.start()
                val chatConsoleThread = Thread(ChatConsole)
                chatConsoleThread.start()
                val topChatterThread = Thread(TopChatter)
                topChatterThread.start()
            } catch (e: Exception) {
                println("Got exception: ${e.message}")
            } finally {
                println("All serving done.")
            }
        }
    }
}