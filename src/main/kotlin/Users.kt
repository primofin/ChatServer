//Vy Nguyen - 1807233

object Users {
    val users = HashSet<String>()
    fun insertUser(username: String){
        users.add(username)


    }
    fun removeUser(username: String){
        users.remove(username)

    }
    fun isUserExit(username: String): Boolean{
        return users.contains(username)



    }
    fun listAllUsers(): String{
        var userlist = ""
        for(user in users){
            userlist += user + ","
        }
        return userlist
    }




}