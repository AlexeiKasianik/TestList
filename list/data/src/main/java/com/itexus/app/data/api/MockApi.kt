package com.itexus.app.data.api

import com.itexus.app.data.model.Item
import kotlin.reflect.KClass

class MockApi : ItemsApi {

    private val itemsList = mutableListOf<Item>()

    init {
        repeat((lowRandomBorder..highRandomBorder).random()) {
            itemsList.add(
                getRandomItem(
                    itemClass = Item::class.sealedSubclasses.random(),
                    id = itemsList.size
                )
            )
        }
    }

    override suspend fun loadItems(): List<Item> {
        return itemsList
    }

    private fun getRandomItem(itemClass: KClass<out Item>, id: Int): Item {
        return when (itemClass) {
            Item.Event::class -> Item.Event(id)
            Item.Notice::class -> Item.Notice(id)
            Item.Move::class -> Item.Move(id)
            else -> error("Unknown item type")
        }
    }

    companion object {
        const val lowRandomBorder = 10
        const val highRandomBorder = 100
    }
}
