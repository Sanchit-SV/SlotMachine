package com.example.slotmachine

object Utils {


    public val squadList = listOf(
        "Nucleus", "Sigma", "Qubit", "Electrons", "Momentum", "Photon", "Quantum",
        "Delta"
    )

    public val membersList = listOf(
        "Omar", "Prashant", "Sanchit", "Sanjeev", "Ankit", "Abhishek", "Faheem",
        "Ankit Raj", "Sakshi Pruthi", "Aditya Mathur", "Moghira", "Abhilash",
        "Gauri Advani", "Gunjit", "Nitin Bhatia",
        "Vaibhav", "Vatsal", "Yogesh", "Ricky", "Tushar"
    )

    public val teamMap = HashMap<String, String>()
    fun teamMap(){
        teamMap.apply {
            this["Omar"] = "Sigma"
            this["Prashant"] = "Nucleus"
            this["Sanchit"] = "Qubit"
            this["Sanjeev"] = "Electrons"
            this["Ankit"] = "Sigma"
            this["Abhishek"] = "Photon"
            this["Faheem"] = "Photon"
            this["Ankit Raj"] = "Momentum"
            this["Sakshi Pruthi"] = "Qubit"
            this["Aditya Mathur"] = "Nucleus"
            this["Moghira"] = "Momentum"
            this["Abhilash"] = "Delta"
            this["Gauri Advani"] = "Quantum"
            this["Gunjit"] = "Nucleus"
            this["Nitin Bhatia"] = "Nucleus"
            this["Vaibhav"] = "Sigma"
            this["Yogesh"] = "Qubit"
            this["Ricky"] = "Nucleus"
            this["Tushar"] = "Sigma"
        }
    }

    val playerScore = 0
    var clickCount = 0


}