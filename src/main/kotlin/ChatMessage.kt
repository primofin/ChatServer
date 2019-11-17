//Vy Nguyen - 1807233
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
class ChatMessage (val message: String, val username: String?){

    override fun toString():String{
        return "${username}:${message} "
    }
    //Parse all incoming messages into objects of ChatMessage type
    fun serialize(): String{
        return Json.stringify(serializer(),ChatMessage(message,username))
    }
    fun deserialize(): ChatMessage{
        return Json.parse(serializer(),serialize())
    }
}