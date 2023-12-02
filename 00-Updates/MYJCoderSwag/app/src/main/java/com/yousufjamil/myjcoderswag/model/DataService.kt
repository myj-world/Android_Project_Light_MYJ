package com.yousufjamil.myjcoderswag.model

object DataService {
    val categories = listOf(
        Category("HATS", "hatimage"),
        Category("HOODIE", "hoodieimage"),
        Category("SHIRTS", "shirtimage"),
        Category("DIGITAL GOODS", "digitalgoodsimage")
    )

    val hats = listOf(
        Product("Devslopes Graphic Beanie", "\$18", "hat1"),
        Product("Devslopes Hat Black", "\$20", "hat2"),
        Product("Devslopes Hat White", "\$18", "hat3"),
        Product("Devslopes Hat Snapback", "\$22", "hat4")
    )

    val hoodies = listOf(
        Product("Devslopes Hoodie Gray", "\$28", "hoodie1"),
        Product("Devslopes Hoodie Red", "\$32", "hoodie2"),
        Product("Devslopes Gray Hoodie", "\$28", "hoodie3"),
        Product("Devslopes Gray Hoodie", "\$32", "hoodie4")
    )

    val shirts = listOf(
        Product("Devslopes Shirt Black", "\$18", "shirt1"),
        Product("Devslopes Badge Light Gray", "\$20", "shirt2"),
        Product("Devslopes Logo Shirt Red", "\$22", "shirt3"),
        Product("Devslopes Hustle", "\$22", "shirt4"),
        Product("Kickflip Studios", "\$18", "shirt5")
    )

    val digitalGood = listOf<Product>()

    fun getProducts(category: String?) : List<Product> {
        return when (category) {
            "SHIRTS" -> shirts
            "HATS" -> hats
            "HOODIE" -> hoodies
            else -> digitalGood
        }
    }
}