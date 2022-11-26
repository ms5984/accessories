package io.github.ms5984.retrox.accessories.internal
/*
 *  Copyright 2022 ms5984, Retrox
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import io.github.ms5984.retrox.accessories.model.Category
import io.github.ms5984.retrox.accessories.model.CategoryData
import org.bukkit.Material

data class CategoryDataImpl(val categoryImpl: () -> CategoryImpl,
                            val placeholderTemplate: PlaceholderTemplate,
                            val data: MutableMap<String, Any> = mutableMapOf()): CategoryData {
    data class PlaceholderTemplate(
        val material: Material,
        val displayName: String,
        val customModelData: Int,
        val lore: List<String>
    ) { constructor() : this(DEFAULT_MATERIAL, parseDisplayName(null), DEFAULT_CUSTOM_MODEL_DATA, parseLore(null)) }

    override fun getCategory(): Category = categoryImpl()
    override fun getDisplayName(): String = data["display-name"]?.let { it as? String } ?: categoryImpl().id
    override fun getProperties(): Map<String, Any> = data.toMap()
}